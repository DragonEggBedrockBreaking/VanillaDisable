package uk.debb.vanilla_disable.config.global;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class VanillaDisableConfig {
    public static boolean autoMigration = true;
    public static boolean worldLoadingScreen = true;
    public static String PATH = "";
    private static Properties data;

    public static void generateConfig() {
        Properties properties = new Properties();
        updateProperties(properties);

        try {
            properties.store(new FileOutputStream(PATH), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        data = properties;
    }

    public static void updateConfig() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        autoMigration = Boolean.parseBoolean(properties.getProperty("auto_migration", String.valueOf(autoMigration)));
        worldLoadingScreen = Boolean.parseBoolean(properties.getProperty("world_loading_screen", String.valueOf(worldLoadingScreen)));

        updateProperties(properties);

        data = properties;
    }

    public static void resetConfig() {
        autoMigration = Boolean.parseBoolean(data.getProperty("auto_migration"));
        worldLoadingScreen = Boolean.parseBoolean(data.getProperty("world_loading_screen"));
    }

    public static void saveConfig() {
        updateProperties(data);
    }

    private static void updateProperties(Properties data) {
        data.setProperty("auto_migration", String.valueOf(autoMigration));
        data.setProperty("world_loading_screen", String.valueOf(worldLoadingScreen));

        try {
            data.store(new FileOutputStream(PATH), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
