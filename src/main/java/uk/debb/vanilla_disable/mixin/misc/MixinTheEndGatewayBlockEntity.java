package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(TheEndGatewayBlockEntity.class)
public abstract class MixinTheEndGatewayBlockEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent entities from going through end gateways
     * @param world the world
     * @param pos the position of the gateway
     * @param state the state
     * @param entity the entity going through the gateway
     * @param blockEntity the gateway
     * @param ci the callback info
     */
    @Inject(method = "teleportEntity", at = @At("HEAD"), cancellable = true)
    private static void cancelTeleportingEntity(Level level, BlockPos pos, BlockState state, Entity entity, TheEndGatewayBlockEntity blockEntity, CallbackInfo ci) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.END_GATEWAYS_ENABLED)) {
            ci.cancel();
        }
    }
}
