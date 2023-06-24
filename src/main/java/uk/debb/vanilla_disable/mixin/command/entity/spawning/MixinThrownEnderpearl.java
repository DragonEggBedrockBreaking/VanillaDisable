package uk.debb.vanilla_disable.mixin.command.entity.spawning;

import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(ThrownEnderpearl.class)
public abstract class MixinThrownEnderpearl {
    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/projectile/ThrownEnderpearl;discard()V"
            ),
            cancellable = true
    )
    private void discard(CallbackInfo ci) {
        if (!CommandDataHandler.getCachedBoolean("entities", "minecraft:ender_pearl", "despawn_on_player_death")) {
            ci.cancel();
        }
    }
}
