package uk.debb.vanilla_disable.mixin.command.block.piston;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class MixinBlockStateBase {
    @Shadow
    public abstract Block getBlock();

    @ModifyReturnValue(method = "getPistonPushReaction", at = @At("RETURN"))
    private PushReaction vanillaDisable$getPistonPushReaction(PushReaction original) {
        Block block = this.getBlock();
        if (CommandDataHandler.isConnectionNull()) {
            if (block.equals(Blocks.OBSIDIAN) || block.equals(Blocks.CRYING_OBSIDIAN) || block.equals(Blocks.RESPAWN_ANCHOR) ||
                    block.equals(Blocks.REINFORCED_DEEPSLATE)) {
                return PushReaction.BLOCK;
            }
            return original;
        }
        String name = CommandDataHandler.getKeyFromBlockRegistry(block);
        String reaction = CommandDataHandler.getCachedString("blocks", name, "push_behaviour");
        return PushReaction.valueOf(reaction);
    }
}
