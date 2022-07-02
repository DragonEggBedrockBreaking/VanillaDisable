package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(BeaconBlockEntity.class)
public abstract class MixinBeaconBlockEntity {
    /**
     * @param level      the level
     * @param blockPos   the block position
     * @param i          ???
     * @param mobEffect  the effect to be applied
     * @param mobEffect2 the other effect to be applied
     * @param ci         the callback info
     * @author DragonEggBedrockBreaking
     * @reason prevent beacons from applying effects
     */
    @Inject(method = "applyEffects", at = @At("HEAD"), cancellable = true)
    private static void cancelEffects(Level level, BlockPos blockPos, int i, MobEffect mobEffect, MobEffect mobEffect2, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.BEACONS_ENABLED)) {
            ci.cancel();
        }
    }
}