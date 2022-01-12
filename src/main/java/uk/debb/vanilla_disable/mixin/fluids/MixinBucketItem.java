package uk.debb.vanilla_disable.mixin.fluids;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(BucketItem.class)
public abstract class MixinBucketItem {
    @Shadow @Final Fluid fluid;

    /**
     * @author DragonEggBedrockBreaking
     * @reason whether or not nether water evaporation should be considered
     * @param type the type of dimension
     * @param player the player placing the water
     * @param world the world
     * @param pos the position to place the water
     * @param hitResult the result of using the water bucket
     * @return whether or not the biome should be counted as ultrawarm
     */
    @Redirect(
        method = "placeFluid",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/dimension/DimensionType;isUltrawarm()Z"
        )
    )
    public boolean isNotUltrawarm(DimensionType type, @Nullable PlayerEntity player, World world, BlockPos pos, @Nullable BlockHitResult hitResult) {
        if (world.getGameRules().getBoolean(RegisterGamerules.WATER_PLACEABLE_IN_NETHER) && this.fluid.isIn(FluidTags.WATER)) {
            // run all vanilla code, except instead of quitting out of the method, continue on running 
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5f, 2.6f + (world.random.nextFloat() - world.random.nextFloat()) * 0.8f);
            for (int l = 0; l < 8; ++l) {
                world.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0, 0.0, 0.0);
            }
            return false;
        }
        return type.isUltrawarm();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason don't play bucket sound if placing in the nether
     * @param player the player placing the water
     * @param world the world
     * @param pos the position at which the water is being placed
     * @param ci the callback info
     */
    @Inject(method = "playEmptyingSound", at = @At(value = "HEAD"))
    protected void cancelPlayingEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, CallbackInfo ci) {
        if (world.getDimension().isUltrawarm() && this.fluid.isIn(FluidTags.WATER) &&
            ((ServerWorldAccess)world).toServerWorld().getGameRules().getBoolean(RegisterGamerules.WATER_PLACEABLE_IN_NETHER)) {
            ci.cancel();
        }
    }
}
