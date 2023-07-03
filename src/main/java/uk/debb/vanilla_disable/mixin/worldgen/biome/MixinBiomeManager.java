package uk.debb.vanilla_disable.mixin.worldgen.biome;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

import java.util.Objects;

@Mixin(BiomeManager.class)
public abstract class MixinBiomeManager {
    @ModifyReturnValue(method = "getBiome", at = @At("RETURN"))
    private Holder<Biome> getBiome(Holder<Biome> original) {
        if (WorldgenDataHandler.toml == null) return original;
        Registry<Biome> biomeRegistry = WorldgenDataHandler.biomeRegistry;
        String rule = WorldgenDataHandler.cleanup(Objects.requireNonNull(biomeRegistry.getKey(original.value())));
        if (!WorldgenDataHandler.get("biomes", rule)) {
            return WorldgenDataHandler.getDefaultBiome(original);
        }
        return original;
    }
}
