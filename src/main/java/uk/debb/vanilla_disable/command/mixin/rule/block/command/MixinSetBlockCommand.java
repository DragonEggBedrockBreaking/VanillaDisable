package uk.debb.vanilla_disable.command.mixin.rule.block.command;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.SetBlockCommand;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

import java.util.function.Predicate;

@Mixin(SetBlockCommand.class)
public abstract class MixinSetBlockCommand {
    @Inject(method = "setBlock", at = @At("HEAD"))
    private static void setBlock(CommandSourceStack commandSourceStack, BlockPos blockPos, BlockInput blockInput, SetBlockCommand.Mode mode, @Nullable Predicate<BlockInWorld> predicate, CallbackInfoReturnable<Integer> cir) throws CommandSyntaxException {
        String block = CommandDataHandler.getKeyFromBlockRegistry(blockInput.getState().getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "can_be_placed_by_command")) {
            throw new SimpleCommandExceptionType(Component.translatable("commands.setblock.disabled")).create();
        }
    }
}
