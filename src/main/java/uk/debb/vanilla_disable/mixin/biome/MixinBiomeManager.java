package uk.debb.vanilla_disable.mixin.biome;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.lists.Lists;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(BiomeManager.class)
public abstract class MixinBiomeManager implements Lists, Maps {
    @ModifyReturnValue(method = "getBiome", at = @At("RETURN"))
    private Holder<Biome> modifyBiome(Holder<Biome> original) {
        Gamerules gameRule = biomeSourceBiomeHolderMap.get(original);
        if (!Gamerules.BIOMES_ENABLED.getBool() || (gameRule != null && !gameRule.getBool())) {
                if (netherBiomes.contains(original)) {
                        return BuiltinRegistries.BIOME.getOrCreateHolderOrThrow(Biomes.NETHER_WASTES);
                    } else if (theEndBiomes.contains(original)) {
                        return BuiltinRegistries.BIOME.getOrCreateHolderOrThrow(Biomes.THE_END);
                    } else {
                        return BuiltinRegistries.BIOME.getOrCreateHolderOrThrow(Biomes.PLAINS);
                    }
            }
        return original;
    }
}
