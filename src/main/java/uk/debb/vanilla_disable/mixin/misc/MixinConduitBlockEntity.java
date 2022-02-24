package uk.debb.vanilla_disable.mixin.misc;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ConduitBlockEntity.class)
public abstract class MixinConduitBlockEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent conduits from applying effects
     * @param level the level
     * @param blockPos the block position
     * @param list the list of block positions
     * @param ci the callback info
     */
    @Inject(method = "applyEffects", at = @At("HEAD"), cancellable = true)
    private static void cancelEffects(Level level, BlockPos blockPos, List<BlockPos> list, CallbackInfo ci) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.CONDUITS_ENABLED)) {
            ci.cancel();
        }
    }
}
