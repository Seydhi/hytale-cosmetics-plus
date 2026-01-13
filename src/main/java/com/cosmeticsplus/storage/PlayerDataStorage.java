package com.cosmeticsplus.storage;

import com.cosmeticsplus.utils.Logger;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataStorage {

    private final Map<UUID, PlayerData> playerDataMap;
    private File storageDir;

    public PlayerDataStorage() {
        this.playerDataMap = new HashMap<>();
    }

    public void initialize() {
        try {
            String storagePath = System.getProperty("user.dir") + File.separator + "plugins" + File.separator + "CosmeticsPlus" + File.separator + "playerdata";
            storageDir = new File(storagePath);
            
            if (!storageDir.exists()) {
                storageDir.mkdirs();
                Logger.info("Created player data directory: " + storagePath);
            }
            
            loadAllPlayerData();
        } catch (Exception e) {
            Logger.error("Failed to initialize storage: " + e.getMessage());
        }
    }

    private void loadAllPlayerData() {
        Logger.info("Loading player data...");
        
        File[] playerFiles = storageDir.listFiles((dir, name) -> name.endsWith(".json"));
        
        if (playerFiles != null) {
            int loaded = 0;
            for (File file : playerFiles) {
                try {
                    String fileName = file.getName();
                    String uuidString = fileName.substring(0, fileName.length() - 5);
                    UUID uuid = UUID.fromString(uuidString);
                    
                    PlayerData data = loadPlayerData(uuid);
                    if (data != null) {
                        playerDataMap.put(uuid, data);
                        loaded++;
                    }
                } catch (Exception e) {
                    Logger.warn("Failed to load player data from " + file.getName());
                }
            }
            Logger.info("Loaded " + loaded + " player data files");
        }
    }

    public PlayerData loadPlayerData(UUID uuid) {
        if (playerDataMap.containsKey(uuid)) {
            return playerDataMap.get(uuid);
        }
        
        File playerFile = new File(storageDir, uuid.toString() + ".json");
        
        if (playerFile.exists()) {
            try {
                PlayerData data = PlayerData.fromJson(playerFile);
                playerDataMap.put(uuid, data);
                return data;
            } catch (Exception e) {
                Logger.warn("Failed to load player data for " + uuid);
            }
        }
        
        PlayerData newData = new PlayerData(uuid);
        playerDataMap.put(uuid, newData);
        return newData;
    }

    public void savePlayerData(UUID uuid) {
        PlayerData data = playerDataMap.get(uuid);
        if (data != null) {
            try {
                File playerFile = new File(storageDir, uuid.toString() + ".json");
                data.toJson(playerFile);
            } catch (Exception e) {
                Logger.error("Failed to save player data for " + uuid);
            }
        }
    }

    public void saveAll() {
        Logger.info("Saving all player data...");
        int saved = 0;
        
        for (UUID uuid : playerDataMap.keySet()) {
            savePlayerData(uuid);
            saved++;
        }
        
        Logger.info("Saved " + saved + " player data files");
    }

    public PlayerData getPlayerData(UUID uuid) {
        return loadPlayerData(uuid);
    }

    public boolean hasCosmetic(UUID uuid, String cosmeticId) {
        PlayerData data = getPlayerData(uuid);
        return data.hasCosmetic(cosmeticId);
    }

    public void unlockCosmetic(UUID uuid, String cosmeticId) {
        PlayerData data = getPlayerData(uuid);
        data.unlockCosmetic(cosmeticId);
        savePlayerData(uuid);
    }

    public void equipCosmetic(UUID uuid, String cosmeticId, String type) {
        PlayerData data = getPlayerData(uuid);
        data.equipCosmetic(cosmeticId, type);
        savePlayerData(uuid);
    }

    public void unequipCosmetic(UUID uuid, String type) {
        PlayerData data = getPlayerData(uuid);
        data.unequipCosmetic(type);
        savePlayerData(uuid);
    }

    public String getEquippedCosmetic(UUID uuid, String type) {
        PlayerData data = getPlayerData(uuid);
        return data.getEquippedCosmetic(type);
    }
}
