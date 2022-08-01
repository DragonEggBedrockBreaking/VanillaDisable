package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(TheEndGatewayBlockEntity.class)
public abstract class MixinTheEndGatewayBlockEntity {
    @Inject(method = "teleportEntity", at = @At("HEAD"), cancellable = true)
    private static void cancelTeleportingEntity(CallbackInfo ci) {
        if (!Gamerules.END_GATEWAYS_ENABLED.getValue(Boolean::parseBoolean)) {
            ci.cancel();
        }
    }
}