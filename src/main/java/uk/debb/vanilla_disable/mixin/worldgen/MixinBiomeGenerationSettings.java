package uk.debb.vanilla_disable.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(BiomeGenerationSettings.class)
public abstract class MixinBiomeGenerationSettings implements Maps {
    @ModifyReturnValue(method = "hasFeature", at = @At("RETURN"))
    private boolean cancelHavingFeature(boolean original, PlacedFeature feature) {
        Gamerules gameRule = biomeGenerationSettingsStringMap.get(String.format("%s", feature));
        if (gameRule != null && !gameRule.getBool()) {
            return false;
        }
        return original;
    }
}