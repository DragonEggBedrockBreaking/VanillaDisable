package uk.debb.vanilla_disable.mixin.util.worldgen;

import net.minecraft.ChatFormatting;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.config.global.VanillaDisableConfig;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList {
    @Shadow
    public abstract void broadcastSystemMessage(Component component, boolean bl);

    @Inject(method = "placeNewPlayer", at = @At("RETURN"))
    private void vanillaDisable$placeNewPlayer(Connection connection, ServerPlayer serverPlayer, CommonListenerCookie commonListenerCookie, CallbackInfo ci) {
        if (WorldgenDataHandler.updated && VanillaDisableConfig.worldgenUpdateMessage) {
            WorldgenDataHandler.updated = false;
            this.broadcastSystemMessage(
                    Component.translatable("vd.worldgen.updated")
                            .withStyle(ChatFormatting.GOLD)
                            .append(
                                    Component.translatable("vd.worldgen.updated.file")
                                            .withStyle(ChatFormatting.GOLD)
                                            .withStyle(ChatFormatting.UNDERLINE)
                                            .withStyle(Style.EMPTY.withClickEvent(
                                                    new ClickEvent(ClickEvent.Action.OPEN_FILE, WorldgenDataHandler.propertiesFile.getAbsolutePath())
                                            ))
                            ),
                    false
            );
        }
    }
}
