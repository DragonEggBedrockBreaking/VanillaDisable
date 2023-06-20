package uk.debb.vanilla_disable.worldgen.data;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.LevelResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class WorldgenDataHandler {
    public static MinecraftServer server;
    public static Registry<Structure> structureRegistry;
    public static Registry<PlacedFeature> placedFeatureRegistry;
    public static Registry<Biome> biomeRegistry;
    private static Toml toml;

    public static String cleanup(Object o) {
        return o.toString().replace("minecraft:", "");
    }

    @SuppressWarnings("unchecked")
    public static void init() {
        structureRegistry = server.registryAccess().registryOrThrow(Registries.STRUCTURE);
        placedFeatureRegistry = server.registryAccess().registryOrThrow(Registries.PLACED_FEATURE);
        biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);

        File PATH = new File(server.getWorldPath(LevelResource.ROOT) + "/vanilla_disable_worldgen.toml");

        try {
            if (!PATH.exists()) {
                if (!PATH.createNewFile()) {
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        toml = new Toml().read(PATH);
        Map<String, Object> data = toml.toMap();

        Map<String, Object> structures = new TreeMap<>((Map<String, Object>) data.getOrDefault("structures", new HashMap<>()));
        Map<String, Object> placedFeatures = new TreeMap<>((Map<String, Object>) data.getOrDefault("placed_features", new HashMap<>()));
        Map<String, Object> biomes = new TreeMap<>((Map<String, Object>) data.getOrDefault("biomes", new HashMap<>()));

        structureRegistry.keySet().forEach(structure -> {
            String structureName = cleanup(structure);
            if (!structures.containsKey(structureName)) {
                structures.put(structureName, true);
            }
        });

        placedFeatureRegistry.keySet().forEach(placedFeature -> {
            String placedFeatureName = cleanup(placedFeature);
            if (!placedFeatures.containsKey(placedFeatureName)) {
                placedFeatures.put(placedFeatureName, true);
            }
        });
        placedFeatures.put("obsidian_platform", true);
        placedFeatures.put("end_spike_cage", true);

        biomeRegistry.keySet().forEach(biome -> {
            String biomeName = cleanup(biome);
            if (!biomes.containsKey(biomeName)) {
                biomes.put(biomeName, true);
            }
        });
        biomes.remove("nether_wastes");
        biomes.remove("plains");
        biomes.remove("the_end");
        biomes.remove("the_void");

        data.put("structures", structures);
        data.put("placed_features", placedFeatures);
        data.put("biomes", biomes);

        TomlWriter writer = new TomlWriter.Builder()
                .indentValuesBy(2)
                .build();
        String tomlString = writer.write(data).replace("[", "\n[").substring(1);

        try {
            Files.write(PATH.toPath(), tomlString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        toml = new Toml().read(PATH);
    }

    public static boolean get(String table, String key) {
        try {
            return toml.getTable(table).getBoolean(key);
        } catch (NullPointerException e) {
            return true;
        }
    }

    private static boolean hasBiome(ResourceKey<Level> level, Holder<Biome> biome) {
        return Objects.requireNonNull(server.getLevel(level)).getChunkSource().getGenerator().getBiomeSource().possibleBiomes.get().contains(biome);
    }

    public static Holder<Biome> getDefaultBiome(Holder<Biome> biome) {
        if (hasBiome(Level.OVERWORLD, biome)) {
            return biomeRegistry.getHolderOrThrow(Biomes.PLAINS);
        } else if (hasBiome(Level.NETHER, biome)) {
            return biomeRegistry.getHolderOrThrow(Biomes.NETHER_WASTES);
        } else if (hasBiome(Level.END, biome)) {
            return biomeRegistry.getHolderOrThrow(Biomes.THE_END);
        } else {
            return biomeRegistry.getHolderOrThrow(Biomes.THE_VOID);
        }
    }
}
