package uk.debb.vanilla_disable.config;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class VanillaDisableConfig implements ModInitializer {
    private static String PATH = "";
    private static Properties data;
    public static boolean autoMigration = true;
    public static boolean worldLoadingScreen = true;

    private static void generateConfig() {
        Properties properties = new Properties();
        properties.setProperty("auto_migration", "true");
        properties.setProperty("world_loading_screen", "true");

        try {
            properties.store(new FileOutputStream(PATH), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        data = properties;
    }

    private static void updateConfig() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        autoMigration = Boolean.parseBoolean(properties.getProperty("auto_migration", "true"));
        worldLoadingScreen = Boolean.parseBoolean(properties.getProperty("world_loading_screen", "true"));

        properties.setProperty("auto_migration", String.valueOf(autoMigration));
        properties.setProperty("world_loading_screen", String.valueOf(worldLoadingScreen));

        try {
            properties.store(new FileOutputStream(PATH), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        data = properties;
    }

    public static void resetConfig() {
        autoMigration = Boolean.parseBoolean(data.getProperty("auto_migration"));
        worldLoadingScreen = Boolean.parseBoolean(data.getProperty("world_loading_screen"));
    }

    public static void saveConfig() {
        data.setProperty("auto_migration", String.valueOf(autoMigration));
        data.setProperty("world_loading_screen", String.valueOf(worldLoadingScreen));

        try {
            data.store(new FileOutputStream(PATH), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onInitialize() {
        PATH = FabricLoader.getInstance().getConfigDir().toString() + "/vanilla_disable.properties";
        if (!new File(PATH).exists()) {
            generateConfig();
        } else {
            updateConfig();
        }
    }
}
