package uk.debb.vanilla_disable.command.mixin.rule.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.chat.Component;
import net.minecraft.server.PlayerAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(PlayerAdvancements.class)
public abstract class MixinPlayerAdvancements {
    @ModifyReturnValue(method = "getOrStartProgress", at = @At("RETURN"))
    private AdvancementProgress getOrStartProgress(AdvancementProgress original, Advancement advancement) {
        String adv = advancement.getId().toString();
        if (!adv.contains("recipe") && !CommandDataHandler.getCachedBoolean("others", adv, "enabled")) {
            CommandDataHandler.server.getPlayerList().broadcastSystemMessage(Component.translatable("advancements.disabled.by.vd").withStyle(ChatFormatting.RED), false);
            return new AdvancementProgress();
        }
        return original;
    }
}
