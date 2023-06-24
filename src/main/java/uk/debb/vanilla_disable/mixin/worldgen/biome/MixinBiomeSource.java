package uk.debb.vanilla_disable.mixin.worldgen.biome;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Mixin(BiomeSource.class)
public abstract class MixinBiomeSource {
    @ModifyReturnValue(method = "possibleBiomes", at = @At("RETURN"))
    private Set<Holder<Biome>> possibleBiomes(Set<Holder<Biome>> original) {
        Set<Holder<Biome>> set = new HashSet<>(original);
        for (Holder<Biome> biomeHolder : original) {
            String rule = WorldgenDataHandler.cleanup(Objects.requireNonNull(WorldgenDataHandler.biomeRegistry.getKey(biomeHolder.value())));
            if (!WorldgenDataHandler.get("biomes", rule)) {
                set.remove(biomeHolder);
            }
        }
        return set;
    }
}
