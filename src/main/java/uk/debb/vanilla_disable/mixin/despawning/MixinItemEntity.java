package uk.debb.vanilla_disable.mixin.despawning;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity {
    @Shadow private int age;
    @Shadow private int pickupDelay;
    @Unique
    final int MAX = RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.ITEM_DESPAWN_TIME);

    /**
     * @author DragonEggBedrockBreaking
     * @reason delete the item when necessary
     * @param ci
     */
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void discardItem(CallbackInfo ci) {    
        if (this.age >= MAX * 20 &&
            !((Entity) (Object) (this)).getLevel().isClientSide) {
            ((Entity) (Object) (this)).discard();
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason cancel deleting the item if too early
     * @param ci
     */
    @Inject(
        method = "tick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/item/ItemEntity;discard()V",
            ordinal = 1
        ),
        cancellable = true
    )
    private void cancelDiscard(CallbackInfo ci) {
        if (this.age < MAX * 20 &&
            this.pickupDelay != Short.MAX_VALUE) {
            ci.cancel();
        }
    }
}
