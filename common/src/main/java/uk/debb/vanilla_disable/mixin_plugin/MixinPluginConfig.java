package uk.debb.vanilla_disable.mixin_plugin;

import uk.debb.vanilla_disable.Constants;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

public class MixinPluginConfig {
    public final Properties properties;
    private final File configDirectory;
    private final File configFile;

    public MixinPluginConfig() {
        this.configDirectory = new File(Paths.get(".").toAbsolutePath().toString(), "config");
        this.configFile = new File(this.configDirectory, "vanilla-disable-mixin.properties");
        this.properties = new Properties();

        if (!configFile.exists()) {
            createConfigFile();
        } else {
            loadConfigFile();
        }
    }

    private void createConfigFile() {
        if (!this.configDirectory.exists() && !this.configDirectory.mkdirs()) {
            Constants.LOG.error("Failed to create config directory for VanillaDisable.");
            return;
        }
        try (FileWriter writer = new FileWriter(this.configFile)) {
            writer.write("# Mixin config file for debugging purposes only. Please read the wiki for more information.\n");
            writer.write("# https://github.com/DragonEggBedrockBreaking/VanillaDisable/wiki/Mixin-Configuration-File\n");
        } catch (IOException e) {
            Constants.LOG.error("Failed to create mixin config file for VanillaDisable.");
        }
    }

    private void loadConfigFile() {
        try (InputStream input = new FileInputStream(configFile)) {
            properties.load(input);
        } catch (IOException e) {
            Constants.LOG.error("Failed to load mixin config file for VanillaDisable.");
        }
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("mixin.util")) {
                String[] parts = key.split("\\.");
                if (parts.length > 2) {
                    String partAfterUtil = parts[2];
                    properties.setProperty("mixin." + partAfterUtil, "false");
                    properties.setProperty("mixin.util." + partAfterUtil, "false");
                }
            }
        }
    }

    public boolean isMixinConfigured(String mixinName) {
        return properties.containsKey(mixinName);
    }

    public boolean isMixinEnabled(String mixinName) {
        return !properties.getProperty(mixinName, "true").equals("false");
    }
}