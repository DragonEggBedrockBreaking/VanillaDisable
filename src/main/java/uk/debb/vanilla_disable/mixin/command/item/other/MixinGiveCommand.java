package uk.debb.vanilla_disable.mixin.command.item.other;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.GiveCommand;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.Collection;

@Mixin(GiveCommand.class)
public abstract class MixinGiveCommand {
    @Inject(method = "giveItem", at = @At("HEAD"))
    private static void giveItem(CommandSourceStack commandSourceStack, ItemInput itemInput, Collection<ServerPlayer> collection, int i, CallbackInfoReturnable<Integer> cir) throws CommandSyntaxException {
        String item = CommandDataHandler.getKeyFromItemRegistry(itemInput.getItem());
        if (!CommandDataHandler.getCachedBoolean("items", item, "can_be_given_by_command")) {
            throw new SimpleCommandExceptionType(Component.translatable("vd.commands.give.disabled")).create();
        }
    }
}
