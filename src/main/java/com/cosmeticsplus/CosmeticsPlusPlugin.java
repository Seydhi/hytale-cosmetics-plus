package com.cosmeticsplus;

import com.cosmeticsplus.config.ConfigManager;
import com.cosmeticsplus.cosmetics.CosmeticManager;
import com.cosmeticsplus.commands.CosmeticCommand;
import com.cosmeticsplus.commands.UnlockCommand;
import com.cosmeticsplus.listeners.PlayerJoinListener;
import com.cosmeticsplus.storage.PlayerDataStorage;
import com.cosmeticsplus.utils.Logger;
import java.util.HashMap;
import java.util.Map;

public class CosmeticsPlusPlugin {

    private static CosmeticsPlusPlugin instance;
    private ConfigManager configManager;
    private CosmeticManager cosmeticManager;
    private PlayerDataStorage storage;
    private Map<String, Object> services;

    public CosmeticsPlusPlugin() {
        instance = this;
        Logger.info("====================================");
        Logger.info("  CosmeticsPlus Plugin v1.0.0");
        Logger.info("  Loading cosmetic system...");
        Logger.info("====================================");
    }

    public void onEnable() {
        Logger.info("Enabling CosmeticsPlus...");

        try {
            initializeServices();
            loadConfiguration();
            initializeStorage();
            initializeCosmetics();
            registerCommands();
            registerListeners();
            
            Logger.info("CosmeticsPlus enabled successfully!");
            Logger.info("Loaded " + cosmeticManager.getCosmeticCount() + " cosmetics");
        } catch (Exception e) {
            Logger.error("Failed to enable CosmeticsPlus: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void onDisable() {
        Logger.info("Disabling CosmeticsPlus...");

        try {
            if (storage != null) {
                storage.saveAll();
            }
            
            Logger.info("CosmeticsPlus disabled successfully!");
        } catch (Exception e) {
            Logger.error("Error during disable: " + e.getMessage());
        }
    }

    private void initializeServices() {
        services = new HashMap<>();
    }

    private void loadConfiguration() {
        configManager = new ConfigManager();
        configManager.loadConfig();
        Logger.info("Configuration loaded");
    }

    private void initializeStorage() {
        storage = new PlayerDataStorage();
        storage.initialize();
        Logger.info("Player storage initialized");
    }

    private void initializeCosmetics() {
        cosmeticManager = new CosmeticManager();
        cosmeticManager.loadCosmetics();
        services.put("cosmeticManager", cosmeticManager);
        Logger.info("Cosmetic system initialized");
    }

    private void registerCommands() {
        new CosmeticCommand().register();
        new UnlockCommand().register();
        Logger.info("Commands registered");
    }

    private void registerListeners() {
        new PlayerJoinListener().register();
        Logger.info("Event listeners registered");
    }

    public static CosmeticsPlusPlugin getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public CosmeticManager getCosmeticManager() {
        return cosmeticManager;
    }

    public PlayerDataStorage getStorage() {
        return storage;
    }

    public Object getService(String serviceName) {
        return services.get(serviceName);
    }

    public void registerService(String name, Object service) {
        services.put(name, service);
        Logger.info("Service registered: " + name);
    }
}
