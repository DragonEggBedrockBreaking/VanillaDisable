package uk.debb.vanilla_disable.mixin.stats;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.lists.Lists;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer implements Lists {
    @Unique
    private boolean shouldCancelStat(Stat<?> stat) {
        if (!Gamerules.STATS_ENABLED.getBool()) {
            return true;
        }
        if (stat.getType().getRegistry().equals(BuiltInRegistries.ITEM)) {
            return !Gamerules.ITEM_STATS.getBool();
        }
        if (stat.formatter.equals(StatFormatter.TIME)) {
            return !Gamerules.TIME_STATS.getBool();
        }
        if (stat.formatter.equals(StatFormatter.DISTANCE)) {
            return !Gamerules.DISTANCE_STATS.getBool();
        }
        if (stat.formatter.equals(StatFormatter.DIVIDE_BY_TEN)) {
            return !Gamerules.DAMAGE_STATS.getBool();
        }
        if (stat.getName().contains("interact")) {
            return !Gamerules.GUI_BLOCK_INTERACTION_STATS.getBool();
        }
        if (stat.getName().contains("mob")) {
            return !Gamerules.ENTITY_KILL_STATS.getBool();
        }
        for (String section : serverPlayerStringList) {
            if (stat.getName().contains(section)) {
                return !Gamerules.GENERAL_STATS.getBool();
            }
        }
        return !Gamerules.GENERAL_BLOCK_INTERACTION_STATS.getBool();
    }

    @Inject(method = "awardStat", at = @At("HEAD"), cancellable = true)
    private void cancelAwardingStat(Stat<?> stat, int i, CallbackInfo ci) {
        if (shouldCancelStat(stat)) {
            ci.cancel();
        }
    }
}
