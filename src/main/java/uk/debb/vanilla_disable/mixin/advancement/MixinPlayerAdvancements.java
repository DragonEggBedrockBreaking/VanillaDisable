package uk.debb.vanilla_disable.mixin.advancement;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.PlayerAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(PlayerAdvancements.class)
public abstract class MixinPlayerAdvancements implements Maps {
    @ModifyReturnValue(method = "getOrStartProgress", at = @At("RETURN"))
    private AdvancementProgress cancelPerformingCriterion(AdvancementProgress original, Advancement advancement) {
        if (advancement.getDisplay() != null) {
            BooleanGamerules gameRule = playerAdvancementsStringMap.get(advancement.getDisplay().getTitle().toString().split("'")[1]);
            if ((!GameruleHelper.getBool(BooleanGamerules.ADVANCEMENTS_ENABLED) || (gameRule != null && !GameruleHelper.getBool(gameRule)))) {
                return new AdvancementProgress();
            }
        }
        return original;
    }
}
