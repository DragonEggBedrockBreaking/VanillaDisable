/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.advancement;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.commands.AdvancementCommands;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataDefinitions;
import uk.debb.vanilla_disable.config.data.SqlManager;

import java.util.Optional;

@Mixin(PlayerAdvancements.class)
public abstract class MixinPlayerAdvancements {
    @WrapMethod(method = "award")
    private boolean vanillaDisable$award(AdvancementHolder advancement, String criterionKey, Operation<Boolean> original) {
        String adv = advancement.id().toString();
        if (adv.contains("recipe") || SqlManager.getBoolean("advancements", adv, "enabled")) {
            return original.call(advancement, criterionKey);
        } else if (Thread.currentThread().getStackTrace()[5].getClassName().equals(AdvancementCommands.class.getName())) {
            DataDefinitions.server.getPlayerList().broadcastSystemMessage(Component.translatable("vd.advancements.disabled.by.vd").withStyle(ChatFormatting.RED), false);
        }
        return false;
    }

    @ModifyReceiver(
            method = "award",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/advancements/AdvancementRewards;grant(Lnet/minecraft/server/level/ServerPlayer;)V"
            )
    )
    private AdvancementRewards vanillaDisable$award(AdvancementRewards instance, ServerPlayer player, AdvancementHolder advancement, String criterionKey) {
        String adv = advancement.id().toString();
        if (adv.contains("recipe")) return instance;
        int experience = SqlManager.getInt("advancements", adv, "xp");
        return new AdvancementRewards(experience, instance.loot(), instance.recipes(), instance.function());
    }

    @ModifyReceiver(
            method = "checkForAutomaticTriggers",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/advancements/AdvancementRewards;grant(Lnet/minecraft/server/level/ServerPlayer;)V"
            )
    )
    private AdvancementRewards vanillaDisable$award(AdvancementRewards instance, ServerPlayer player, @Local Advancement advancement) {
        Optional<ResourceLocation> parent = advancement.parent();
        if (parent.isPresent()) {
            String adv = parent.get().toString();
            if (adv.contains("recipe")) return instance;
            int experience = SqlManager.getInt("advancements", adv, "xp");
            return new AdvancementRewards(experience, instance.loot(), instance.recipes(), instance.function());
        }
        return instance;
    }
}
