package uk.debb.vanilla_disable.mixin.util.command;

import net.minecraft.ChatFormatting;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList {
    @Shadow
    public abstract void broadcastSystemMessage(Component component, boolean bl);

    @Inject(method = "placeNewPlayer", at = @At("RETURN"))
    private void placeNewPlayer(Connection netManager, ServerPlayer player, CallbackInfo ci) {
        if (CommandDataHandler.migrated) {
            CommandDataHandler.migrated = false;
            this.broadcastSystemMessage(
                    Component.translatable("vd.migration.done")
                            .withStyle(ChatFormatting.GOLD)
                            .append(
                                    Component.translatable("vd.migration.done.wiki")
                                            .withStyle(ChatFormatting.GOLD)
                                            .withStyle(ChatFormatting.UNDERLINE)
                                            .withStyle(Style.EMPTY.withClickEvent(
                                                    new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/DragonEggBedrockBreaking/VanillaDisable/wiki/GameRules#migration")
                                            ))
                            ),
                    false
            );
        }
    }
}
