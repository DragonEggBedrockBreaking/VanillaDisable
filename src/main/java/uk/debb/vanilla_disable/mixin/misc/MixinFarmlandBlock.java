package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(FarmlandBlock.class)
public abstract class MixinFarmlandBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @reason disable crop trampling
     * @param state the state of the block
     * @param world the world
     * @param pos the position of the block
     * @param theWorld the world
     * @param blockState the state of the block
     * @param blockPos the position of the block
     * @param entity the entity falling on the block
     * @param fallDistance how far the entity fell on the block
     */
    @Redirect(
        method = "onLandedUpon",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/FarmlandBlock;setToDirt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"
        )
    )
    private void cancelSettingToDirt(BlockState state, World world, BlockPos pos, World theWorld, BlockState blockState, BlockPos blockPos, Entity entity, float fallDistance) {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.CROP_TRAMPLING)) {
            world.setBlockState(pos, FarmlandBlock.pushEntitiesUpBeforeBlockChange(state, Blocks.DIRT.getDefaultState(), world, pos));
        }
    }
}
