package uk.debb.vanilla_disable.command.mixin.rule.block.piston;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class MixinBlockStateBase {
    @Shadow public abstract Block getBlock();

    @ModifyReturnValue(method = "getPistonPushReaction", at = @At("RETURN"))
    private PushReaction getPistonPushReaction(PushReaction original) {
        Block block = this.getBlock();
        if (DataHandler.isConnectionNull()) {
            if (block.equals(Blocks.OBSIDIAN) || block.equals(Blocks.CRYING_OBSIDIAN) || block.equals(Blocks.RESPAWN_ANCHOR) ||
                    block.equals(Blocks.REINFORCED_DEEPSLATE)) {
                return PushReaction.BLOCK;
            }
            return original;
        }
        String name = DataHandler.getKeyFromBlockRegistry(block);
        String reaction = DataHandler.getCachedString("blocks", name, "push_behaviour");
        return PushReaction.valueOf(reaction);
    }
}
