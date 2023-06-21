package uk.debb.vanilla_disable.worldgen.data;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
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
import uk.debb.vanilla_disable.gamerules.migration.util.GameruleMigrationDataHandler;

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
    private static File PATH;

    /**
     * Removes the "minecraft:" prefix from a string.
     *
     * @param o The object to clean up.
     * @return The cleaned up string.
     */
    public static String cleanup(Object o) {
        return o.toString().replace("minecraft:", "");
    }

    /**
     * Writes the data to the file.
     *
     * @param structures     The structures to write.
     * @param placedFeatures The placed features to write.
     * @param biomeLevels    The biome levels to write.
     */
    private static void write(Map<String, Object> structures, Map<String, Object> placedFeatures, Map<String, Object> biomeLevels) {
        Map<String, Object> data = new HashMap<>();
        data.put("structures", structures);
        data.put("placed_features", placedFeatures);
        data.put("biomes", biomeLevels);

        TomlWriter writer = new TomlWriter.Builder()
                .indentValuesBy(2)
                .build();
        String tomlString = writer.write(data).replace("[", "\n[").substring(1);

        try {
            Files.write(PATH.toPath(), tomlString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes and sets up all the data.
     */
    @SuppressWarnings("unchecked")
    public static void init() {
        structureRegistry = server.registryAccess().registryOrThrow(Registries.STRUCTURE);
        placedFeatureRegistry = server.registryAccess().registryOrThrow(Registries.PLACED_FEATURE);
        biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);

        PATH = new File(server.getWorldPath(LevelResource.ROOT) + "/vanilla_disable_worldgen.toml");
        boolean first = !PATH.exists();

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

        write(structures, placedFeatures, biomes);

        toml = new Toml().read(PATH);

        if (first) {
            GameruleMigrationDataHandler.updateToml();
        }
    }

    /**
     * Gets a boolean from the toml file.
     *
     * @param table The table to get the boolean from.
     * @param key   The key to get the boolean from.
     * @return The boolean.
     */
    public static boolean get(String table, String key) {
        try {
            return toml.getTable(table).getBoolean(key);
        } catch (NullPointerException e) {
            return true;
        }
    }

    /**
     * Checks if a biome is in a dimension.
     *
     * @param level The dimension.
     * @param biome The biome.
     * @return Whether the biome is in the dimension.
     */
    private static boolean hasBiome(ResourceKey<Level> level, Holder<Biome> biome) {
        return Objects.requireNonNull(server.getLevel(level)).getChunkSource().getGenerator().getBiomeSource().possibleBiomes.get().contains(biome);
    }

    /**
     * Gets the default biome for a dimension, from a biome in that dimension.
     *
     * @param biome The biome.
     * @return The default biome.
     */
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

    /**
     * Updates the values in the toml file based on gamerule migration.
     *
     * @param structureMap     The structures to update.
     * @param placedFeatureMap The placed features to update.
     * @param biomeMap         The biomes to update.
     */
    @SuppressWarnings("unchecked")
    public static void updateVals(Object2ObjectMap<String, Boolean> structureMap, Object2ObjectMap<String, Boolean> placedFeatureMap, Object2ObjectMap<String, Boolean> biomeMap) {
        Map<String, Object> data = toml.toMap();

        Map<String, Object> structures = new TreeMap<>((Map<String, Object>) data.getOrDefault("structures", new HashMap<>()));
        Map<String, Object> placedFeatures = new TreeMap<>((Map<String, Object>) data.getOrDefault("placed_features", new HashMap<>()));
        Map<String, Object> biomes = new TreeMap<>((Map<String, Object>) data.getOrDefault("biomes", new HashMap<>()));

        structures.putAll(structureMap);
        placedFeatures.putAll(placedFeatureMap);
        biomes.putAll(biomeMap);

        write(structures, placedFeatures, biomes);

        toml = new Toml().read(PATH);
    }
}
