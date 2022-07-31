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

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer {
    @Unique
    private static final ObjectList<String> generalList = new ObjectArrayList<>() {{
        add("jump");
        add("drop");
        add("bred");
        add("fish_caught");
        add("target_hit");
        add("trade");
    }};

    @Unique
    private boolean shouldCancelStat(Stat<?> stat) {
        if (!BooleanGamerules.STATS_ENABLED.getValue()) {
            return true;
        }
        Registry<?> registry = stat.getType().getRegistry();
        if (registry.equals(Registry.BLOCK)) {
            return !BooleanGamerules.BLOCK_STATS.getValue();
        } else if (registry.equals(Registry.ITEM)) {
            return !BooleanGamerules.ITEM_STATS.getValue();
        } else if (registry.equals(Registry.ENTITY_TYPE)) {
            return !BooleanGamerules.ENTITY_KILL_STATS.getValue();
        }
        StatFormatter formatter = stat.formatter;
        if (formatter.equals(StatFormatter.TIME)) {
            return !BooleanGamerules.TIME_STATS.getValue();
        } else if (formatter.equals(StatFormatter.DISTANCE)) {
            return !BooleanGamerules.DISTANCE_STATS.getValue();
        } else if (formatter.equals(StatFormatter.DIVIDE_BY_TEN)) {
            return !BooleanGamerules.DAMAGE_STATS.getValue();
        }
        String string = stat.getName();
        if (string.contains("interact")) {
            return !BooleanGamerules.GUI_BLOCK_INTERACTION_STATS.getValue();
        }
        for (String section : generalList) {
            if (string.contains(section)) {
                return !BooleanGamerules.GENERAL_STATS.getValue();
            }
        }
        return !BooleanGamerules.GENERAL_BLOCK_INTERACTION_STATS.getValue();
    }

    @Inject(method = "awardStat", at = @At("HEAD"), cancellable = true)
    private void cancelAwardingStat(Stat<?> stat, int i, CallbackInfo ci) {
        if (shouldCancelStat(stat)) {
            ci.cancel();
        }
    }
}
