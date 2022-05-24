package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

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
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void cancelInsideEntity(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.NETHER_PORTALS_ENABLED)) {
            ci.cancel();
        }
    }
}
