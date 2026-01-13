package com.cosmeticsplus.storage;

import com.cosmeticsplus.utils.Logger;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private final List<String> unlockedCosmetics;
    private final Map<String, String> equippedCosmetics;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        this.unlockedCosmetics = new ArrayList<>();
        this.equippedCosmetics = new HashMap<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<String> getUnlockedCosmetics() {
        return new ArrayList<>(unlockedCosmetics);
    }

    public boolean hasCosmetic(String cosmeticId) {
        return unlockedCosmetics.contains(cosmeticId);
    }

    public void unlockCosmetic(String cosmeticId) {
        if (!unlockedCosmetics.contains(cosmeticId)) {
            unlockedCosmetics.add(cosmeticId);
        }
    }

    public void equipCosmetic(String cosmeticId, String type) {
        if (unlockedCosmetics.contains(cosmeticId)) {
            equippedCosmetics.put(type, cosmeticId);
        }
    }

    public void unequipCosmetic(String type) {
        equippedCosmetics.remove(type);
    }

    public String getEquippedCosmetic(String type) {
        return equippedCosmetics.get(type);
    }

    public Map<String, String> getAllEquippedCosmetics() {
        return new HashMap<>(equippedCosmetics);
    }

    public void toJson(File file) throws IOException {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"uuid\": \"").append(uuid.toString()).append("\",\n");
        
        json.append("  \"unlockedCosmetics\": [");
        for (int i = 0; i < unlockedCosmetics.size(); i++) {
            json.append("\"").append(unlockedCosmetics.get(i)).append("\"");
            if (i < unlockedCosmetics.size() - 1) {
                json.append(", ");
            }
        }
        json.append("],\n");
        
        json.append("  \"equippedCosmetics\": {\n");
        int count = 0;
        for (Map.Entry<String, String> entry : equippedCosmetics.entrySet()) {
            json.append("    \"").append(entry.getKey()).append("\": \"").append(entry.getValue()).append("\"");
            if (count < equippedCosmetics.size() - 1) {
                json.append(",");
            }
            json.append("\n");
            count++;
        }
        json.append("  }\n");
        json.append("}");
        
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json.toString());
        }
    }

    public static PlayerData fromJson(File file) throws IOException {
        try (FileReader reader = new FileReader(file)) {
            StringBuilder content = new StringBuilder();
            char[] buffer = new char[1024];
            int charsRead;
            
            while ((charsRead = reader.read(buffer)) != -1) {
                content.append(buffer, 0, charsRead);
            }
            
            String json = content.toString();
            
            UUID uuid = parseUUID(json);
            PlayerData data = new PlayerData(uuid);
            
            parseUnlockedCosmetics(json, data);
            parseEquippedCosmetics(json, data);
            
            return data;
        }
    }

    private static UUID parseUUID(String json) {
        int uuidStart = json.indexOf("\"uuid\": \"") + 9;
        int uuidEnd = json.indexOf("\"", uuidStart);
        String uuidString = json.substring(uuidStart, uuidEnd);
        return UUID.fromString(uuidString);
    }

    private static void parseUnlockedCosmetics(String json, PlayerData data) {
        int arrayStart = json.indexOf("\"unlockedCosmetics\": [");
        if (arrayStart == -1) return;
        
        arrayStart = json.indexOf("[", arrayStart) + 1;
        int arrayEnd = json.indexOf("]", arrayStart);
        
        String arrayContent = json.substring(arrayStart, arrayEnd);
        String[] items = arrayContent.split(",\\s*");
        
        for (String item : items) {
            item = item.trim();
            if (item.startsWith("\"") && item.endsWith("\"")) {
                String cosmeticId = item.substring(1, item.length() - 1);
                data.unlockCosmetic(cosmeticId);
            }
        }
    }

    private static void parseEquippedCosmetics(String json, PlayerData data) {
        int objectStart = json.indexOf("\"equippedCosmetics\": {");
        if (objectStart == -1) return;
        
        objectStart = json.indexOf("{", objectStart) + 1;
        int objectEnd = json.indexOf("}", objectStart);
        
        String objectContent = json.substring(objectStart, objectEnd);
        String[] entries = objectContent.split(",\\s*");
        
        for (String entry : entries) {
            entry = entry.trim();
            String[] parts = entry.split(":\\s*");
            if (parts.length == 2) {
                String type = parts[0].trim();
                String cosmeticId = parts[1].trim();
                
                if (type.startsWith("\"") && type.endsWith("\"")) {
                    type = type.substring(1, type.length() - 1);
                }
                if (cosmeticId.startsWith("\"") && cosmeticId.endsWith("\"")) {
                    cosmeticId = cosmeticId.substring(1, cosmeticId.length() - 1);
                }
                
                data.equipCosmetic(cosmeticId, type);
            }
        }
    }
}
