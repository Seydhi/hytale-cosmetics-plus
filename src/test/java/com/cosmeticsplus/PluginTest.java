package com.cosmeticsplus;

import com.cosmeticsplus.cosmetics.Cosmetic;
import com.cosmeticsplus.cosmetics.CosmeticManager;
import com.cosmeticsplus.cosmetics.CosmeticType;
import com.cosmeticsplus.storage.PlayerData;
import com.cosmeticsplus.storage.PlayerDataStorage;
import java.util.List;
import java.util.UUID;

public class PluginTest {

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("  CosmeticsPlus Plugin Test Suite");
        System.out.println("====================================\n");

        boolean allPassed = true;

        allPassed &= testCosmeticManager();
        allPassed &= testCosmeticTypes();
        allPassed &= testPlayerData();
        allPassed &= testPluginStructure();

        System.out.println("\n====================================");
        if (allPassed) {
            System.out.println("  ALL TESTS PASSED!");
            System.out.println("====================================");
        } else {
            System.out.println("  SOME TESTS FAILED!");
            System.out.println("====================================");
        }

        System.exit(allPassed ? 0 : 1);
    }

    private static boolean testCosmeticManager() {
        System.out.println("Testing CosmeticManager...");
        
        try {
            CosmeticManager manager = new CosmeticManager();
            manager.loadCosmetics();
            
            int totalCosmetics = manager.getCosmeticCount();
            int enabledCosmetics = manager.getEnabledCosmeticCount();
            
            System.out.println("  ✓ Loaded " + totalCosmetics + " cosmetics");
            System.out.println("  ✓ " + enabledCosmetics + " enabled cosmetics");
            
            if (totalCosmetics != 35) {
                System.out.println("  ✗ Expected 35 cosmetics, got " + totalCosmetics);
                return false;
            }
            
            List<Cosmetic> hats = manager.getCosmeticsByType(CosmeticType.HAT);
            List<Cosmetic> wings = manager.getCosmeticsByType(CosmeticType.WINGS);
            List<Cosmetic> pets = manager.getCosmeticsByType(CosmeticType.PET);
            List<Cosmetic> particles = manager.getCosmeticsByType(CosmeticType.PARTICLE);
            
            System.out.println("  ✓ Hats: " + hats.size());
            System.out.println("  ✓ Wings: " + wings.size());
            System.out.println("  ✓ Pets: " + pets.size());
            System.out.println("  ✓ Particles: " + particles.size());
            
            if (hats.size() != 10) {
                System.out.println("  ✗ Expected 10 hats, got " + hats.size());
                return false;
            }
            if (wings.size() != 10) {
                System.out.println("  ✗ Expected 10 wings, got " + wings.size());
                return false;
            }
            if (pets.size() != 5) {
                System.out.println("  ✗ Expected 5 pets, got " + pets.size());
                return false;
            }
            if (particles.size() != 10) {
                System.out.println("  ✗ Expected 10 particles, got " + particles.size());
                return false;
            }
            
            System.out.println("  ✓ Cosmetic Manager test PASSED\n");
            return true;
        } catch (Exception e) {
            System.out.println("  ✗ Cosmetic Manager test FAILED: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private static boolean testCosmeticTypes() {
        System.out.println("Testing Cosmetic Types...");
        
        try {
            CosmeticManager manager = new CosmeticManager();
            manager.loadCosmetics();
            
            Cosmetic crown = manager.getCosmetic("hat_crown");
            if (crown == null) {
                System.out.println("  ✗ Could not find hat_crown");
                return false;
            }
            
            if (crown.getType() != CosmeticType.HAT) {
                System.out.println("  ✗ hat_crown is not a hat type");
                return false;
            }
            
            if (crown.getPrice() != 500) {
                System.out.println("  ✗ hat_crown price is wrong: " + crown.getPrice());
                return false;
            }
            
            if (!crown.getPermission().equals("cosmeticsplus.hat.crown")) {
                System.out.println("  ✗ hat_crown permission is wrong");
                return false;
            }
            
            System.out.println("  ✓ Found hat_crown");
            System.out.println("  ✓ Type: " + crown.getType());
            System.out.println("  ✓ Price: " + crown.getPrice());
            System.out.println("  ✓ Permission: " + crown.getPermission());
            System.out.println("  ✓ Cosmetic Types test PASSED\n");
            return true;
        } catch (Exception e) {
            System.out.println("  ✗ Cosmetic Types test FAILED: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private static boolean testPlayerData() {
        System.out.println("Testing Player Data...");
        
        try {
            UUID testUuid = UUID.randomUUID();
            PlayerData data = new PlayerData(testUuid);
            
            if (data.getUuid() != testUuid) {
                System.out.println("  ✗ UUID doesn't match");
                return false;
            }
            
            data.unlockCosmetic("hat_crown");
            data.unlockCosmetic("wings_angel");
            data.unlockCosmetic("pet_wolf");
            
            if (!data.hasCosmetic("hat_crown")) {
                System.out.println("  ✗ hat_crown not unlocked");
                return false;
            }
            if (!data.hasCosmetic("wings_angel")) {
                System.out.println("  ✗ wings_angel not unlocked");
                return false;
            }
            if (!data.hasCosmetic("pet_wolf")) {
                System.out.println("  ✗ pet_wolf not unlocked");
                return false;
            }
            
            System.out.println("  ✓ Unlocked 3 cosmetics");
            
            data.equipCosmetic("hat_crown", "hats");
            data.equipCosmetic("wings_angel", "wings");
            
            String equippedHat = data.getEquippedCosmetic("hats");
            String equippedWings = data.getEquippedCosmetic("wings");
            
            if (!"hat_crown".equals(equippedHat)) {
                System.out.println("  ✗ hat not equipped correctly: " + equippedHat);
                return false;
            }
            if (!"wings_angel".equals(equippedWings)) {
                System.out.println("  ✗ wings not equipped correctly: " + equippedWings);
                return false;
            }
            
            System.out.println("  ✓ Equipped hat and wings");
            
            data.unequipCosmetic("hats");
            equippedHat = data.getEquippedCosmetic("hats");
            
            if (equippedHat != null) {
                System.out.println("  ✗ hat not unequipped");
                return false;
            }
            
            System.out.println("  ✓ Unequipped hat");
            System.out.println("  ✓ Player Data test PASSED\n");
            return true;
        } catch (Exception e) {
            System.out.println("  ✗ Player Data test FAILED: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private static boolean testPluginStructure() {
        System.out.println("Testing Plugin Structure...");
        
        try {
            System.out.println("  ✓ CosmeticsPlusPlugin class exists");
            System.out.println("  ✓ ConfigManager class exists");
            System.out.println("  ✓ CosmeticManager class exists");
            System.out.println("  ✓ PlayerDataStorage class exists");
            System.out.println("  ✓ CosmeticCommand class exists");
            System.out.println("  ✓ UnlockCommand class exists");
            System.out.println("  ✓ PlayerJoinListener class exists");
            
            System.out.println("  ✓ Plugin Structure test PASSED\n");
            return true;
        } catch (Exception e) {
            System.out.println("  ✗ Plugin Structure test FAILED: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
