package uk.debb.vanilla_disable.mixin.despawning;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity extends Entity {
    public MixinItemEntity(EntityType<? extends ItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow private int itemAge;
    @Shadow private int pickupDelay;
    @Unique
    final int MAX = this.world.getGameRules().getInt(RegisterGamerules.ITEM_DESPAWN_TIME);

    /**
     * @author DragonEggBedrockBreaking
     * @reason delete the item when necessary
     * @param ci
     */
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void discardItem(CallbackInfo ci) {    
        if (this.itemAge >= MAX * 20 &&
            !this.world.isClient) {
            this.discard();
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
            target = "Lnet/minecraft/entity/ItemEntity;discard()V",
            ordinal = 1
        ),
        cancellable = true
    )
    private void cancelDiscard(CallbackInfo ci) {
        if (this.itemAge < MAX * 20 &&
            this.pickupDelay != Short.MAX_VALUE) {
            ci.cancel();
        }
    }
}
