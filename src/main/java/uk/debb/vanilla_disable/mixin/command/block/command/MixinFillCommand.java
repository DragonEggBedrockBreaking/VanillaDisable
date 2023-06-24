package uk.debb.vanilla_disable.mixin.command.block.command;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.FillCommand;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.function.Predicate;

@Mixin(FillCommand.class)
public abstract class MixinFillCommand {
    @Inject(method = "fillBlocks", at = @At("HEAD"))
    private static void fillBlocks(CommandSourceStack commandSourceStack, BoundingBox boundingBox, BlockInput blockInput, FillCommand.Mode mode, @Nullable Predicate<BlockInWorld> predicate, CallbackInfoReturnable<Integer> cir) throws CommandSyntaxException {
        String block = CommandDataHandler.getKeyFromBlockRegistry(blockInput.getState().getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "can_be_placed_by_command")) {
            throw new SimpleCommandExceptionType(Component.translatable("vd.commands.setblock.disabled")).create();
        }
    }
}
