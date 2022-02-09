package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(NetherPortalBlock.class)
public abstract class MixinNetherPortalBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent people from going through nether portals
     * @param state the state
     * @param world the world
     * @param pos the position of the portal
     * @param entity the entity going through the portal
     * @param ci the callback info
     */
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void cancelEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.NETHER_PORTALS_ENABLED)) {
            ci.cancel();
        }
    }
}
