package uk.debb.vanilla_disable.mixin.worldgen.biome;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

@Mixin(BiomeManager.class)
public abstract class MixinBiomeManager {
    @ModifyReturnValue(method = "getBiome", at = @At("RETURN"))
    private Holder<Biome> vanillaDisable$getBiome(Holder<Biome> original) {
        if (WorldgenDataHandler.properties.isEmpty()) return original;
        Registry<Biome> biomeRegistry = WorldgenDataHandler.biomeRegistry;
        ResourceLocation resourceLocation = biomeRegistry.getKey(original.value());
        if (resourceLocation == null) return original;
        String rule = WorldgenDataHandler.cleanup(resourceLocation);
        if (!WorldgenDataHandler.get("biomes", rule)) {
            return WorldgenDataHandler.getDefaultBiome(original);
        }
        return original;
    }
}
