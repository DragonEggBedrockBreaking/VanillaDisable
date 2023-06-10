package uk.debb.vanilla_disable.gamerules.mixin.advancement;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.PlayerAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

@Mixin(PlayerAdvancements.class)
public abstract class MixinPlayerAdvancements implements Maps {
    @ModifyReturnValue(method = "getOrStartProgress", at = @At("RETURN"))
    private AdvancementProgress cancelPerformingCriterion(AdvancementProgress original, Advancement advancement) {
        if (advancement.getDisplay() != null) {
            try {
                Gamerules gameRule = playerAdvancementsStringMap.get(advancement.getDisplay().getTitle().toString().split("'")[1]);
                if (!Gamerules.ADVANCEMENTS_ENABLED.getBool() || (gameRule != null && !gameRule.getBool())) {
                    return new AdvancementProgress();
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
        return original;
    }
}
