package com.cosmeticsplus.cosmetics;

import java.util.HashMap;
import java.util.Map;

public class Cosmetic {

    private final String id;
    private final String name;
    private final String description;
    private final CosmeticType type;
    private final int price;
    private final boolean enabled;
    private final String permission;
    private final Map<String, Object> metadata;

    public Cosmetic(String id, String name, String description, CosmeticType type, int price, boolean enabled, String permission) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.enabled = enabled;
        this.permission = permission;
        this.metadata = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CosmeticType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getPermission() {
        return permission;
    }

    public void setMetadata(String key, Object value) {
        metadata.put(key, value);
    }

    public Object getMetadata(String key) {
        return metadata.get(key);
    }

    public Map<String, Object> getAllMetadata() {
        return new HashMap<>(metadata);
    }
}
