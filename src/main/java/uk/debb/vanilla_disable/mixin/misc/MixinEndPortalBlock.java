package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(EndPortalBlock.class)
public abstract class MixinEndPortalBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent people from going through end portals
     * @param state the state
     * @param level the level
     * @param pos the position of the portal
     * @param entity the entity going through the portal
     * @param ci the callback info
     */
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void cancelEntityInside(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.END_PORTALS_ENABLED)) {
            ci.cancel();
        }
    }
}
