package uk.debb.vanilla_disable.mixin.fluids;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(BucketItem.class)
public abstract class MixinBucketItem {
    @Shadow
    @Final
    private Fluid content;

    @ModifyExpressionValue(
            method = "emptyContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/dimension/DimensionType;ultraWarm()Z"
            )
    )
    private boolean isNotUltraWarm(boolean original) {
        if (!GameruleHelper.getBool(Gamerules.WATER_PLACEABLE_IN_NETHER) && (this.content == Fluids.WATER || this.content == Fluids.FLOWING_WATER)) {
            return original;
        }
        return false;
    }

    @Inject(method = "playEmptySound", at = @At(value = "HEAD"), cancellable = true)
    protected void cancelPlayingEmptySound(@Nullable Player player, LevelAccessor levelAccessor, BlockPos blockPos, CallbackInfo ci) {
        if (levelAccessor.dimensionType().ultraWarm() && (this.content == Fluids.WATER || this.content == Fluids.FLOWING_WATER) &&
                GameruleHelper.getBool(Gamerules.WATER_PLACEABLE_IN_NETHER)) {
            levelAccessor.playSound(player, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5f, 2.6f + (levelAccessor.getRandom().nextFloat() - levelAccessor.getRandom().nextFloat()) * 0.8f);
            for (int l = 0; l < 8; ++l) {
                levelAccessor.addParticle(ParticleTypes.SMOKE, (double) blockPos.getX() + Math.random(), (double) blockPos.getY() + Math.random(), (double) blockPos.getZ() + Math.random(), 0.0, 0.0, 0.0);
            }
            ci.cancel();
        }
    }
}