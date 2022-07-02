package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

import java.util.List;

@Mixin(ConduitBlockEntity.class)
public abstract class MixinConduitBlockEntity {
    /**
     * @param level    the level
     * @param blockPos the block position
     * @param list     the list of block positions
     * @param ci       the callback info
     * @author DragonEggBedrockBreaking
     * @reason prevent conduits from applying effects
     */
    @Inject(method = "applyEffects", at = @At("HEAD"), cancellable = true)
    private static void cancelEffects(Level level, BlockPos blockPos, List<BlockPos> list, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.CONDUITS_ENABLED)) {
            ci.cancel();
        }
    }
}