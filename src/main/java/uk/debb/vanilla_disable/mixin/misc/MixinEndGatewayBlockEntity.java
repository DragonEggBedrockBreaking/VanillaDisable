package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(EndGatewayBlockEntity.class)
public abstract class MixinEndGatewayBlockEntity {
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
    @Inject(method = "tryTeleportingEntity", at = @At("HEAD"), cancellable = true)
    private static void cancelTeleportingEntity(World world, BlockPos pos, BlockState state, Entity entity, EndGatewayBlockEntity blockEntity, CallbackInfo ci) {
        if (!world.getGameRules().getBoolean(RegisterGamerules.END_GATEWAYS_ENABLED)) {
            ci.cancel();
        }
    }
}
