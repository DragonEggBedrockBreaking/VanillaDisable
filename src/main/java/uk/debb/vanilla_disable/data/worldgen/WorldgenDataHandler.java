package uk.debb.vanilla_disable.data.worldgen;

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
import uk.debb.vanilla_disable.config.global.VanillaDisableConfig;
import uk.debb.vanilla_disable.data.gamerule.GameruleMigrationDataHandler;

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
    public static Toml toml;
    public static File PATH;
    public static File DIRECTORY;
    public static boolean continueGeneration = true;
    public static boolean shouldMigrate = true;

    private record Tables(Map<String, Object> structures, Map<String, Object> placedFeatures, Map<String, Object> biomes) { }

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
    public static void init() {
        structureRegistry = server.registryAccess().registryOrThrow(Registries.STRUCTURE);
        placedFeatureRegistry = server.registryAccess().registryOrThrow(Registries.PLACED_FEATURE);
        biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);

        PATH = new File(server.getWorldPath(LevelResource.ROOT) + "/vanilla_disable_worldgen.toml");
        DIRECTORY = server.getWorldPath(LevelResource.ROOT).toFile();
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
        Tables data = getTomlTables();

        structureRegistry.keySet().forEach(structure -> {
            String structureName = cleanup(structure);
            if (!data.structures().containsKey(structureName)) {
                data.structures().put(structureName, true);
            }
        });

        placedFeatureRegistry.keySet().forEach(placedFeature -> {
            String placedFeatureName = cleanup(placedFeature);
            if (!data.placedFeatures().containsKey(placedFeatureName)) {
                data.placedFeatures().put(placedFeatureName, true);
            }
        });
        data.placedFeatures().put("obsidian_platform", true);
        data.placedFeatures().put("end_spike_cage", true);

        biomeRegistry.keySet().forEach(biome -> {
            String biomeName = cleanup(biome);
            if (!data.biomes().containsKey(biomeName)) {
                data.biomes().put(biomeName, true);
            }
        });
        data.biomes().remove("nether_wastes");
        data.biomes().remove("plains");
        data.biomes().remove("the_end");
        data.biomes().remove("the_void");

        write(data.structures(), data.placedFeatures(), data.biomes());

        toml = new Toml().read(PATH);

        if (first && shouldMigrate && VanillaDisableConfig.autoMigration) {
            GameruleMigrationDataHandler.updateToml();
        }
    }

    /**
     * Gets the tables from the toml file.
     * @return The tables.
     */
    @SuppressWarnings("unchecked")
    private static Tables getTomlTables() {
        Map<String, Object> data = toml.toMap();

        return new Tables(
                new TreeMap<>((Map<String, Object>) data.getOrDefault("structures", new HashMap<>())),
                new TreeMap<>((Map<String, Object>) data.getOrDefault("placed_features", new HashMap<>())),
                new TreeMap<>((Map<String, Object>) data.getOrDefault("biomes", new HashMap<>()))
        );
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
    public static void updateVals(Object2ObjectMap<String, Boolean> structureMap, Object2ObjectMap<String, Boolean> placedFeatureMap, Object2ObjectMap<String, Boolean> biomeMap) {
        Tables data = getTomlTables();

        data.structures().putAll(structureMap);
        data.placedFeatures().putAll(placedFeatureMap);
        data.biomes().putAll(biomeMap);

        write(data.structures(), data.placedFeatures(), data.biomes());

        toml = new Toml().read(PATH);
    }
}
