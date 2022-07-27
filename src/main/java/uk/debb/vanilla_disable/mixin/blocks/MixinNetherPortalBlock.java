package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(NetherPortalBlock.class)
public abstract class MixinNetherPortalBlock {
    /**
     * @param state  the state
     * @param level  the level
     * @param pos    the position of the portal
     * @param entity the entity going through the portal
     * @param ci     the callback info
     * @author DragonEggBedrockBreaking
     * @reason prevent people from going through nether portals
     */
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void cancelInsideEntity(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (!GameruleHelper.getBool(Gamerules.NETHER_PORTALS_ENABLED)) {
            ci.cancel();
        }
    }
}