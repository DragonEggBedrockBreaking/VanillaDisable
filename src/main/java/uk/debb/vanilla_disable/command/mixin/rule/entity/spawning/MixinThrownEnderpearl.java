package uk.debb.vanilla_disable.command.mixin.rule.entity.spawning;

import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

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
