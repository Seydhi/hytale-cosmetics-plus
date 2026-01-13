package com.cosmeticsplus.config;

import com.cosmeticsplus.utils.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private Properties config;
    private File configFile;

    public ConfigManager() {
        this.config = new Properties();
    }

    public void loadConfig() {
        try {
            String configPath = System.getProperty("user.dir") + File.separator + "plugins" + File.separator + "CosmeticsPlus";
            File configDir = new File(configPath);
            
            if (!configDir.exists()) {
                configDir.mkdirs();
                Logger.info("Created config directory: " + configPath);
            }
            
            configFile = new File(configDir, "config.properties");
            
            if (configFile.exists()) {
                try (FileInputStream fis = new FileInputStream(configFile)) {
                    config.load(fis);
                    Logger.info("Loaded configuration from: " + configFile.getAbsolutePath());
                }
            } else {
                createDefaultConfig();
            }
        } catch (IOException e) {
            Logger.error("Failed to load config: " + e.getMessage());
            createDefaultConfig();
        }
    }

    private void createDefaultConfig() {
        Logger.info("Creating default configuration...");
        
        config.setProperty("plugin.enabled", "true");
        config.setProperty("cosmetics.hats.enabled", "true");
        config.setProperty("cosmetics.wings.enabled", "true");
        config.setProperty("cosmetics.pets.enabled", "true");
        config.setProperty("cosmetics.particles.enabled", "true");
        config.setProperty("storage.type", "json");
        config.setProperty("storage.path", "plugins/CosmeticsPlus/playerdata");
        config.setProperty("messages.prefix", "&6[&eCosmetics&6] ");
        config.setProperty("messages.no_permission", "&cYou don't have permission!");
        config.setProperty("messages.cosmetic_unlocked", "&aYou unlocked the %cosmetic% cosmetic!");
        config.setProperty("messages.cosmetic_equipped", "&aYou equipped the %cosmetic% cosmetic!");
        config.setProperty("messages.cosmetic_unequipped", "&aYou unequipped the cosmetic!");
        config.setProperty("messages.invalid_cosmetic", "&cInvalid cosmetic: %cosmetic%");
        config.setProperty("messages.already_unlocked", "&cYou already have this cosmetic!");
        config.setProperty("messages.locked_cosmetic", "&cThis cosmetic is locked!");
        
        saveConfig();
    }

    public void saveConfig() {
        if (configFile == null) {
            Logger.warn("Config file not initialized, skipping save");
            return;
        }
        
        try (FileOutputStream fos = new FileOutputStream(configFile)) {
            config.store(fos, "CosmeticsPlus Configuration");
            Logger.info("Saved configuration to: " + configFile.getAbsolutePath());
        } catch (IOException e) {
            Logger.error("Failed to save config: " + e.getMessage());
        }
    }

    public String getString(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }

    public String getString(String key) {
        return config.getProperty(key);
    }

    public int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(config.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(config.getProperty(key, String.valueOf(defaultValue)));
    }

    public void set(String key, String value) {
        config.setProperty(key, value);
    }

    public Properties getConfig() {
        return config;
    }
}
