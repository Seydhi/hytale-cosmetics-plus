package com.cosmeticsplus.cosmetics;

public enum CosmeticType {
    HAT("Hat", "hats"),
    WINGS("Wings", "wings"),
    PET("Pet", "pets"),
    PARTICLE("Particle Effect", "particles");

    private final String displayName;
    private final String configKey;

    CosmeticType(String displayName, String configKey) {
        this.displayName = displayName;
        this.configKey = configKey;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getConfigKey() {
        return configKey;
    }
}
