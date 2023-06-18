package uk.debb.vanilla_disable.command.mixin.rule.block.other;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(FarmBlock.class)
public abstract class MixinFarmBlock {
    @WrapWithCondition(
            method = "fallOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/FarmBlock;turnToDirt(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"
            ),
            require = 0
    )
    private boolean turnToDirt(Entity entity, BlockState blockState, Level level, BlockPos blockPos, Level level2, BlockState blockState2, BlockPos blockPos2, Entity entity2, float f) {
        return DataHandler.getCachedBoolean("blocks", "minecraft:farmland", "can_be_trampled");
    }
}
