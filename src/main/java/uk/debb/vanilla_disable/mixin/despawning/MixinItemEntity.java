package uk.debb.vanilla_disable.mixin.despawning;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity {
    @Shadow
    private int age;
    @Shadow
    private int pickupDelay;

    @Inject(method = "tick", at = @At("HEAD"))
    private void discardItem(CallbackInfo ci) {
        if (this.age >= Gamerules.ITEM_DESPAWN_TIME.getInt() * 20 && !(((Entity) (Object) this).level().isClientSide())) {
            ((Entity) (Object) this).discard();
        }
    }

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
        if (this.age < Gamerules.ITEM_DESPAWN_TIME.getInt() * 20 &&
                this.pickupDelay != Short.MAX_VALUE) {
            ci.cancel();
        }
    }
}