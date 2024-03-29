package uk.debb.vanilla_disable.mixin.command.advancement;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.PlayerAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(PlayerAdvancements.class)
public abstract class MixinPlayerAdvancements {
    @Inject(method = "award", at = @At("RETURN"), cancellable = true)
    private void vanillaDisable$award(AdvancementHolder advancementHolder, String string, CallbackInfoReturnable<Boolean> cir) {
        String adv = advancementHolder.id().toString();
        if (!adv.contains("recipe") && !CommandDataHandler.getCachedBoolean("advancements", adv, "enabled")) {
            CommandDataHandler.server.getPlayerList().broadcastSystemMessage(Component.translatable("vd.advancements.disabled.by.vd").withStyle(ChatFormatting.RED), false);
            cir.setReturnValue(false);
        }
    }
}
