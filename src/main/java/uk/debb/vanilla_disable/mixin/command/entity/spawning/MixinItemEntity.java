package uk.debb.vanilla_disable.mixin.command.entity.spawning;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity {
    @Shadow
    private int age;
    @Shadow
    private int pickupDelay;

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        if (this.age >= CommandDataHandler.getCachedInt("entities", "minecraft:item", "despawn_time") && !entity.level().isClientSide()) {
            entity.discard();
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
    private void discard(CallbackInfo ci) {
        if (this.pickupDelay != Short.MAX_VALUE && this.age < CommandDataHandler.getCachedInt("entities", "minecraft:item", "despawn_time")) {
            ci.cancel();
        }
    }
}
