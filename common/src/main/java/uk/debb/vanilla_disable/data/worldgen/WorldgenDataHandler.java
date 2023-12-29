package uk.debb.vanilla_disable.data.worldgen;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
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
import org.apache.commons.io.FileUtils;
import uk.debb.vanilla_disable.Constants;
import uk.debb.vanilla_disable.config.global.VanillaDisableConfig;
import uk.debb.vanilla_disable.data.gamerule.GameruleMigrationDataHandler;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Properties;

public class WorldgenDataHandler {
    public static MinecraftServer server;
    public static Registry<Biome> biomeRegistry;
    public static Registry<Structure> structureRegistry;
    public static Registry<PlacedFeature> placedFeatureRegistry;
    public static Object2BooleanMap<String> biomeMap = new Object2BooleanOpenHashMap<>();
    public static Object2BooleanMap<String> structureMap = new Object2BooleanOpenHashMap<>();
    public static Object2BooleanMap<String> placedFeatureMap = new Object2BooleanOpenHashMap<>();
    public static File directory;
    public static File tomlFile;
    public static File propertiesFile;
    public static Properties properties = new Properties();
    public static boolean shouldMigrate = true;
    public static boolean updated = false;

    /**
     * Removes the "minecraft:" prefix from a string.
     *
     * @param o The object to clean up.
     * @return The cleaned up string.
     */
    public static String cleanup(Object o) {
        String[] output = o.toString().split(":");
        return output[output.length - 1];
    }

    /**
     * Create basic constants, and decide what to do with the config.
     */
    public static void init() {
        biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);
        structureRegistry = server.registryAccess().registryOrThrow(Registries.STRUCTURE);
        placedFeatureRegistry = server.registryAccess().registryOrThrow(Registries.PLACED_FEATURE);

        directory = server.getWorldPath(LevelResource.ROOT).toFile();
        tomlFile = new File(directory, "vanilla_disable_worldgen.toml");
        propertiesFile = new File(directory, "vanilla_disable_worldgen.properties");

        if (!propertiesFile.exists()) {
            if (tomlFile.exists()) {
                convertConfig();
            } else {
                createConfig(true);
                if (VanillaDisableConfig.autoMigration && shouldMigrate) {
                    GameruleMigrationDataHandler.updateProperties();
                }
            }
        } else {
            updateConfig();
        }
        loadConfig();

        biomeMap.clear();
        structureMap.clear();
        placedFeatureMap.clear();
    }

    /**
     * Migrate the config from the old .toml format to the new .properties format.
     */
    private static void convertConfig() {
        if (!tomlFile.renameTo(propertiesFile)) {
            Constants.LOG.error("Unable to convert legacy .toml worldgen config to .properties format.");
            return;
        }

        try {
            String[] lines = FileUtils.readFileToString(propertiesFile, Charset.defaultCharset()).split("\n");
            Object2ObjectMap<String, StringBuilder> sections = new Object2ObjectOpenHashMap<>();

            String current = "";
            for (String line : lines) {
                if (line.startsWith("[")) {
                    current = line.substring(1, line.length() - 1);
                    sections.put(current, new StringBuilder());
                } else {
                    sections.get(current).append(line.replace("  ", current + ".")).append("\n");
                }
            }

            try (FileWriter writer = new FileWriter(propertiesFile)) {
                sections.values().forEach(content -> {
                    try { writer.write(content.toString()); }
                    catch (IOException ex) { throw new UncheckedIOException(ex); }
                });
            } catch (IOException e) {
                throw new IOException(e);
            }
        } catch (IOException e) {
            Constants.LOG.error("Unable to convert legacy .toml worldgen config to .properties format.");
        }
    }

    /**
     * Create the config file with default values (and values configured in the creation screen GUI).
     */
    private static void createConfig(boolean write) {
        biomeRegistry.keySet().forEach(biome -> {
            if (!biome.toString().contains("minecraft:")) { return; }
            String clean = cleanup(biome);
            if ("plains the_nether the_end the_void".contains(clean)) { return; }
            properties.put("biomes." + clean, biomeMap.getOrDefault(clean, true));
        });

        structureRegistry.keySet().forEach(structure -> {
            if (!structure.toString().contains("minecraft:")) { return; }
            String clean = cleanup(structure);
            properties.put("structures." + clean, structureMap.getOrDefault(clean, true));
        });

        placedFeatureRegistry.keySet().forEach(placedFeature -> {
            if (!placedFeature.toString().contains("minecraft:")) { return; }
            String clean = cleanup(placedFeature);
            properties.put("placed_features." + clean, placedFeatureMap.getOrDefault(clean, true));
        });
        ImmutableList.of("obsidian_platform", "end_spike_cage").forEach(placedFeature ->
                properties.put("placed_features." + placedFeature, placedFeatureMap.getOrDefault(placedFeature, true)));

        if (write) {
            try {
                if (!propertiesFile.createNewFile()) {
                    throw new UncheckedIOException(new IOException());
                }
            } catch (IOException e) {
                Constants.LOG.error("Unable to generate new worldgen config.");
            }
            write();
        }
    }

    /**
     * Updates the config file.
     */
    private static void updateConfig() {
        Properties loadedProperties = new Properties();
        try (FileInputStream in = new FileInputStream(propertiesFile)) {
            loadedProperties.load(in);
        } catch (IOException e) {
            Constants.LOG.error("Unable to load worldgen config.", e);
        }

        loadedProperties.forEach((key, value) -> loadedProperties.put(key, Boolean.parseBoolean(value.toString())));
        createConfig(false);
        properties.putAll(loadedProperties);

        if (!Objects.equals(properties.keySet(), loadedProperties.keySet())) {
            write();
            if (properties.values().stream().anyMatch(value -> !(boolean)value)) {
                updated = true;
            }
        }
    }

    /**
     * Load the config file.
     */
    private static void loadConfig() {
        try (FileInputStream in = new FileInputStream(propertiesFile)) {
            properties.load(in);
        } catch (IOException e) {
            Constants.LOG.error("Unable to load worldgen config.", e);
        }
    }

    /**
     * Write the properties to the config file.
     */
    public static void write() {
        try (FileWriter writer = new FileWriter(propertiesFile)) {
            properties.keySet().stream().sorted().forEach(key -> {
                boolean value = (boolean) properties.get(key);
                try {
                    writer.write(key + "=" + value + "\n");
                } catch (IOException e) {
                    Constants.LOG.error("Unable to write to worldgen config.");
                }
            });
        } catch (IOException e) {
            Constants.LOG.error("Unable to write to worldgen config.");
        }
    }

    /**
     * Get a value from the config file.
     *
     * @param table The table to get the value from.
     * @param key   The key to get the value of.
     * @return      The value of the key.
     */
    public static boolean get(String table, String key) {
        return Boolean.parseBoolean(properties.getProperty(table + "." + key));
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
        biomeMap.forEach((key, value) -> properties.put("biomes." + key, value.toString()));
        structureMap.forEach((key, value) -> properties.put("structures." + key, value.toString()));
        placedFeatureMap.forEach((key, value) -> properties.put("placed_features." + key, value.toString()));
        write();
    }
}
