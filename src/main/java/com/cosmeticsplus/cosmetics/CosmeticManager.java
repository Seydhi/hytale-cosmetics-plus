package com.cosmeticsplus.cosmetics;

import com.cosmeticsplus.config.ConfigManager;
import com.cosmeticsplus.utils.Logger;
import java.util.ArrayList;
import java.util.List;

public class CosmeticManager {

    private final List<Cosmetic> cosmetics;
    private final ConfigManager config;

    public CosmeticManager() {
        this.cosmetics = new ArrayList<>();
        this.config = new ConfigManager();
    }

    public void loadCosmetics() {
        Logger.info("Loading cosmetics...");
        
        if (config.getBoolean("cosmetics.hats.enabled", true)) {
            loadHats();
        }
        
        if (config.getBoolean("cosmetics.wings.enabled", true)) {
            loadWings();
        }
        
        if (config.getBoolean("cosmetics.pets.enabled", true)) {
            loadPets();
        }
        
        if (config.getBoolean("cosmetics.particles.enabled", true)) {
            loadParticles();
        }
        
        Logger.success("Loaded " + cosmetics.size() + " cosmetics");
    }

    private void loadHats() {
        Logger.info("Loading hat cosmetics...");
        
        cosmetics.add(new Cosmetic(
            "hat_crown",
            "Golden Crown",
            "A majestic golden crown for royalty",
            CosmeticType.HAT,
            500,
            true,
            "cosmeticsplus.hat.crown"
        ));

        cosmetics.add(new Cosmetic(
            "hat_tophat",
            "Top Hat",
            "A fancy top hat for the sophisticated",
            CosmeticType.HAT,
            300,
            true,
            "cosmeticsplus.hat.tophat"
        ));

        cosmetics.add(new Cosmetic(
            "hat_cap",
            "Baseball Cap",
            "A stylish baseball cap",
            CosmeticType.HAT,
            200,
            true,
            "cosmeticsplus.hat.cap"
        ));

        cosmetics.add(new Cosmetic(
            "hat_wizard",
            "Wizard Hat",
            "A magical wizard hat with stars",
            CosmeticType.HAT,
            600,
            true,
            "cosmeticsplus.hat.wizard"
        ));

        cosmetics.add(new Cosmetic(
            "hat_viking",
            "Viking Helmet",
            "A fierce viking warrior helmet",
            CosmeticType.HAT,
            450,
            true,
            "cosmeticsplus.hat.viking"
        ));

        cosmetics.add(new Cosmetic(
            "hat_sombrero",
            "Sombrero",
            "A festive Mexican sombrero",
            CosmeticType.HAT,
            350,
            true,
            "cosmeticsplus.hat.sombrero"
        ));

        cosmetics.add(new Cosmetic(
            "hat_miner",
            "Miner Helmet",
            "A helmet with a built-in flashlight",
            CosmeticType.HAT,
            250,
            true,
            "cosmeticsplus.hat.miner"
        ));

        cosmetics.add(new Cosmetic(
            "hat_party",
            "Party Hat",
            "A colorful cone-shaped party hat",
            CosmeticType.HAT,
            150,
            true,
            "cosmeticsplus.hat.party"
        ));

        cosmetics.add(new Cosmetic(
            "hat_bunny",
            "Bunny Ears",
            "Cute fluffy bunny ears",
            CosmeticType.HAT,
            400,
            true,
            "cosmeticsplus.hat.bunny"
        ));

        cosmetics.add(new Cosmetic(
            "hat_cat",
            "Cat Ears",
            "Adorable cat ear headband",
            CosmeticType.HAT,
            350,
            true,
            "cosmeticsplus.hat.cat"
        ));
    }

    private void loadWings() {
        Logger.info("Loading wing cosmetics...");
        
        cosmetics.add(new Cosmetic(
            "wings_angel",
            "Angel Wings",
            "Pure white angelic wings",
            CosmeticType.WINGS,
            800,
            true,
            "cosmeticsplus.wings.angel"
        ));

        cosmetics.add(new Cosmetic(
            "wings_demon",
            "Demon Wings",
            "Dark demon wings",
            CosmeticType.WINGS,
            800,
            true,
            "cosmeticsplus.wings.demon"
        ));

        cosmetics.add(new Cosmetic(
            "wings_butterfly",
            "Butterfly Wings",
            "Colorful butterfly wings",
            CosmeticType.WINGS,
            600,
            true,
            "cosmeticsplus.wings.butterfly"
        ));

        cosmetics.add(new Cosmetic(
            "wings_dragon",
            "Dragon Wings",
            "Powerful dragon wings",
            CosmeticType.WINGS,
            1000,
            true,
            "cosmeticsplus.wings.dragon"
        ));

        cosmetics.add(new Cosmetic(
            "wings_fairy",
            "Fairy Wings",
            "Magical glowing fairy wings",
            CosmeticType.WINGS,
            500,
            true,
            "cosmeticsplus.wings.fairy"
        ));

        cosmetics.add(new Cosmetic(
            "wings_bat",
            "Bat Wings",
            "Spooky bat wings",
            CosmeticType.WINGS,
            400,
            true,
            "cosmeticsplus.wings.bat"
        ));

        cosmetics.add(new Cosmetic(
            "wings_techno",
            "Cyber Wings",
            "Futuristic mechanical wings",
            CosmeticType.WINGS,
            1200,
            true,
            "cosmeticsplus.wings.techno"
        ));

        cosmetics.add(new Cosmetic(
            "wings_ice",
            "Ice Wings",
            "Beautiful crystalline ice wings",
            CosmeticType.WINGS,
            900,
            true,
            "cosmeticsplus.wings.ice"
        ));

        cosmetics.add(new Cosmetic(
            "wings_fire",
            "Fire Wings",
            "Flaming phoenix wings",
            CosmeticType.WINGS,
            1100,
            true,
            "cosmeticsplus.wings.fire"
        ));

        cosmetics.add(new Cosmetic(
            "wings_feather",
            "Feather Wings",
            "Elegant feathered wings",
            CosmeticType.WINGS,
            700,
            true,
            "cosmeticsplus.wings.feather"
        ));
    }

    private void loadPets() {
        Logger.info("Loading pet cosmetics...");
        
        cosmetics.add(new Cosmetic(
            "pet_wolf",
            "Wolf",
            "A loyal wolf companion",
            CosmeticType.PET,
            1500,
            true,
            "cosmeticsplus.pet.wolf"
        ));

        cosmetics.add(new Cosmetic(
            "pet_cat",
            "Cat",
            "A cute kitty cat",
            CosmeticType.PET,
            1200,
            true,
            "cosmeticsplus.pet.cat"
        ));

        cosmetics.add(new Cosmetic(
            "pet_parrot",
            "Parrot",
            "A colorful tropical parrot",
            CosmeticType.PET,
            1000,
            true,
            "cosmeticsplus.pet.parrot"
        ));

        cosmetics.add(new Cosmetic(
            "pet_dragon_baby",
            "Baby Dragon",
            "A small baby dragon",
            CosmeticType.PET,
            2000,
            true,
            "cosmeticsplus.pet.dragon"
        ));

        cosmetics.add(new Cosmetic(
            "pet_bunny",
            "Bunny",
            "A fluffy bunny rabbit",
            CosmeticType.PET,
            800,
            true,
            "cosmeticsplus.pet.bunny"
        ));
    }

    private void loadParticles() {
        Logger.info("Loading particle effects...");
        
        cosmetics.add(new Cosmetic(
            "particle_heart",
            "Heart Trail",
            "Floating hearts follow you",
            CosmeticType.PARTICLE,
            400,
            true,
            "cosmeticsplus.particle.heart"
        ));

        cosmetics.add(new Cosmetic(
            "particle_star",
            "Star Trail",
            "Sparkling stars surround you",
            CosmeticType.PARTICLE,
            500,
            true,
            "cosmeticsplus.particle.star"
        ));

        cosmetics.add(new Cosmetic(
            "particle_fire",
            "Fire Aura",
            "Burning flames surround you",
            CosmeticType.PARTICLE,
            600,
            true,
            "cosmeticsplus.particle.fire"
        ));

        cosmetics.add(new Cosmetic(
            "particle_ice",
            "Frost Aura",
            "Chilly ice crystals surround you",
            CosmeticType.PARTICLE,
            600,
            true,
            "cosmeticsplus.particle.ice"
        ));

        cosmetics.add(new Cosmetic(
            "particle_magic",
            "Magic Aura",
            "Purple magical particles swirl around you",
            CosmeticType.PARTICLE,
            700,
            true,
            "cosmeticsplus.particle.magic"
        ));

        cosmetics.add(new Cosmetic(
            "particle_rainbow",
            "Rainbow Trail",
            "A beautiful rainbow follows you",
            CosmeticType.PARTICLE,
            800,
            true,
            "cosmeticsplus.particle.rainbow"
        ));

        cosmetics.add(new Cosmetic(
            "particle_smoke",
            "Smoke Trail",
            "Dark smoke follows you",
            CosmeticType.PARTICLE,
            300,
            true,
            "cosmeticsplus.particle.smoke"
        ));

        cosmetics.add(new Cosmetic(
            "particle_sparkle",
            "Sparkle Effect",
            "Tiny sparkles dance around you",
            CosmeticType.PARTICLE,
            500,
            true,
            "cosmeticsplus.particle.sparkle"
        ));

        cosmetics.add(new Cosmetic(
            "particle_bubble",
            "Bubble Trail",
            "Playful bubbles float around you",
            CosmeticType.PARTICLE,
            400,
            true,
            "cosmeticsplus.particle.bubble"
        ));

        cosmetics.add(new Cosmetic(
            "particle_note",
            "Music Notes",
            "Musical notes float around you",
            CosmeticType.PARTICLE,
            550,
            true,
            "cosmeticsplus.particle.note"
        ));
    }

    public Cosmetic getCosmetic(String id) {
        for (Cosmetic cosmetic : cosmetics) {
            if (cosmetic.getId().equalsIgnoreCase(id)) {
                return cosmetic;
            }
        }
        return null;
    }

    public List<Cosmetic> getCosmeticsByType(CosmeticType type) {
        List<Cosmetic> filtered = new ArrayList<>();
        for (Cosmetic cosmetic : cosmetics) {
            if (cosmetic.getType() == type && cosmetic.isEnabled()) {
                filtered.add(cosmetic);
            }
        }
        return filtered;
    }

    public List<Cosmetic> getAllCosmetics() {
        return new ArrayList<>(cosmetics);
    }

    public List<Cosmetic> getEnabledCosmetics() {
        List<Cosmetic> enabled = new ArrayList<>();
        for (Cosmetic cosmetic : cosmetics) {
            if (cosmetic.isEnabled()) {
                enabled.add(cosmetic);
            }
        }
        return enabled;
    }

    public int getCosmeticCount() {
        return cosmetics.size();
    }

    public int getEnabledCosmeticCount() {
        int count = 0;
        for (Cosmetic cosmetic : cosmetics) {
            if (cosmetic.isEnabled()) {
                count++;
            }
        }
        return count;
    }
}
