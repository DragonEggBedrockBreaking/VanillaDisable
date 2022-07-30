package uk.debb.vanilla_disable.mixin.stats;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer {
    @Unique
    ObjectList<String> generalList = new ObjectArrayList<>() {{
        add("jump");
        add("drop");
        add("bred");
        add("fish_caught");
        add("target_hit");
        add("trade");
    }};
    @Unique
    private boolean shouldCancelStat(Stat<?> stat) {
        if (!GameruleHelper.getBool(BooleanGamerules.STATS_ENABLED)) {
            return true;
        }
        Registry<?> registry = stat.getType().getRegistry();
        if (registry.equals(Registry.BLOCK)) {
            return !GameruleHelper.getBool(BooleanGamerules.BLOCK_STATS);
        } else if (registry.equals(Registry.ITEM)) {
            return !GameruleHelper.getBool(BooleanGamerules.ITEM_STATS);
        } else if (registry.equals(Registry.ENTITY_TYPE)) {
            return !GameruleHelper.getBool(BooleanGamerules.ENTITY_KILL_STATS);
        }
        StatFormatter formatter = stat.formatter;
        if (formatter.equals(StatFormatter.TIME)) {
            return !GameruleHelper.getBool(BooleanGamerules.TIME_STATS);
        } else if (formatter.equals(StatFormatter.DISTANCE)) {
            return !GameruleHelper.getBool(BooleanGamerules.DISTANCE_STATS);
        } else if (formatter.equals(StatFormatter.DIVIDE_BY_TEN)) {
            return !GameruleHelper.getBool(BooleanGamerules.DAMAGE_STATS);
        }
        String string = stat.getName();
        if (string.contains("interact")) {
            return !GameruleHelper.getBool(BooleanGamerules.GUI_BLOCK_INTERACTION_STATS);
        }
        for (String section : generalList) {
            if (string.contains(section)) {
                return !GameruleHelper.getBool(BooleanGamerules.GENERAL_STATS);
            }
        }
        return !GameruleHelper.getBool(BooleanGamerules.GENERAL_BLOCK_INTERACTION_STATS);
    }

    @Inject(method = "awardStat", at = @At("HEAD"), cancellable = true)
    private void cancelAwardingStat(Stat<?> stat, int i, CallbackInfo ci) {
        if (shouldCancelStat(stat)) {
            ci.cancel();
        }
    }
}
