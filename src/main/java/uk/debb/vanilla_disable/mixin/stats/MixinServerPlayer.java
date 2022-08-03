package uk.debb.vanilla_disable.mixin.stats;

import net.minecraft.core.Registry;
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
        Registry<?> registry = stat.getType().getRegistry();
        if (registry.equals(Registry.BLOCK)) {
            return !Gamerules.BLOCK_STATS.getBool();
        } else if (registry.equals(Registry.ITEM)) {
            return !Gamerules.ITEM_STATS.getBool();
        } else if (registry.equals(Registry.ENTITY_TYPE)) {
            return !Gamerules.ENTITY_KILL_STATS.getBool();
        }
        StatFormatter formatter = stat.formatter;
        if (formatter.equals(StatFormatter.TIME)) {
            return !Gamerules.TIME_STATS.getBool();
        } else if (formatter.equals(StatFormatter.DISTANCE)) {
            return !Gamerules.DISTANCE_STATS.getBool();
        } else if (formatter.equals(StatFormatter.DIVIDE_BY_TEN)) {
            return !Gamerules.DAMAGE_STATS.getBool();
        }
        String string = stat.getName();
        if (string.contains("interact")) {
            return !Gamerules.GUI_BLOCK_INTERACTION_STATS.getBool();
        }
        for (String section : serverPlayerStringList) {
            if (string.contains(section)) {
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
