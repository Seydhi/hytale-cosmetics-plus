package com.cosmeticsplus.commands;

import com.cosmeticsplus.CosmeticsPlusPlugin;
import com.cosmeticsplus.cosmetics.Cosmetic;
import com.cosmeticsplus.cosmetics.CosmeticType;
import com.cosmeticsplus.cosmetics.CosmeticManager;
import com.cosmeticsplus.storage.PlayerDataStorage;
import java.util.UUID;

public class CosmeticCommand extends Command {

    public CosmeticCommand() {
        super("cosmetic", "Manage your cosmetics", "cosmeticsplus.cosmetic");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sendHelp(sender);
            return;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "list":
                handleList(sender, args);
                break;
            case "equip":
                handleEquip(sender, args);
                break;
            case "unequip":
                handleUnequip(sender, args);
                break;
            case "info":
                handleInfo(sender, args);
                break;
            case "menu":
                handleMenu(sender);
                break;
            default:
                sendHelp(sender);
                break;
        }
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage("&6====== &eCosmeticsPlus Help &6======");
        sender.sendMessage("&e/cosmetic list [type] &7- List all cosmetics");
        sender.sendMessage("&e/cosmetic equip <id> &7- Equip a cosmetic");
        sender.sendMessage("&e/cosmetic unequip [type] &7- Unequip a cosmetic");
        sender.sendMessage("&e/cosmetic info <id> &7- Get cosmetic info");
        sender.sendMessage("&e/cosmetic menu &7- Open cosmetic menu");
        sender.sendMessage("&e/unlock <id> &7- Unlock a cosmetic");
        sender.sendMessage("&6=================================");
    }

    private void handleList(CommandSender sender, String[] args) {
        CosmeticManager manager = CosmeticsPlusPlugin.getInstance().getCosmeticManager();

        if (args.length >= 2) {
            String typeStr = args[1].toUpperCase();
            try {
                CosmeticType type = CosmeticType.valueOf(typeStr);
                showCosmeticsByType(sender, manager, type);
            } catch (IllegalArgumentException e) {
                sender.sendMessage("&cInvalid cosmetic type: " + typeStr);
                sender.sendMessage("&7Valid types: HAT, WINGS, PET, PARTICLE");
            }
        } else {
            sender.sendMessage("&6====== &eAvailable Cosmetics &6======");
            
            for (CosmeticType type : CosmeticType.values()) {
                int count = manager.getCosmeticsByType(type).size();
                sender.sendMessage("&e" + type.getDisplayName() + "s: &7" + count);
            }
            
            sender.sendMessage("&eTotal: &7" + manager.getEnabledCosmeticCount() + " cosmetics");
            sender.sendMessage("&6=================================");
        }
    }

    private void showCosmeticsByType(CommandSender sender, CosmeticManager manager, CosmeticType type) {
        sender.sendMessage("&6====== &e" + type.getDisplayName() + " Cosmetics &6======");
        
        for (Cosmetic cosmetic : manager.getCosmeticsByType(type)) {
            String status = hasCosmetic(sender, cosmetic.getId()) ? "&a[Owned]" : "&c[Locked]";
            sender.sendMessage(status + " &e" + cosmetic.getName() + " &7- " + cosmetic.getPrice() + " coins");
            sender.sendMessage("   &7ID: " + cosmetic.getId());
        }
        
        sender.sendMessage("&6=================================");
    }

    private void handleEquip(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("&cUsage: /cosmetic equip <cosmetic_id>");
            return;
        }

        String cosmeticId = args[1];
        CosmeticManager manager = CosmeticsPlusPlugin.getInstance().getCosmeticManager();
        Cosmetic cosmetic = manager.getCosmetic(cosmeticId);

        if (cosmetic == null) {
            sender.sendMessage("&cInvalid cosmetic: " + cosmeticId);
            return;
        }

        if (!cosmetic.isEnabled()) {
            sender.sendMessage("&cThis cosmetic is currently disabled!");
            return;
        }

        if (!hasCosmetic(sender, cosmeticId)) {
            sender.sendMessage("&cYou don't own this cosmetic!");
            sender.sendMessage("&eUse /unlock " + cosmeticId + " to unlock it");
            return;
        }

        PlayerDataStorage storage = CosmeticsPlusPlugin.getInstance().getStorage();
        String uuid = getUuid(sender);
        
        storage.equipCosmetic(getUUIDFromSender(sender), cosmeticId, cosmetic.getType().getConfigKey());
        
        sender.sendMessage("&aYou equipped the " + cosmetic.getName() + "!");
    }

    private void handleUnequip(CommandSender sender, String[] args) {
        String typeStr = args.length >= 2 ? args[1].toUpperCase() : "";
        
        if (typeStr.isEmpty()) {
            sender.sendMessage("&cUsage: /cosmetic unequip <type>");
            sender.sendMessage("&7Types: HAT, WINGS, PET, PARTICLE");
            return;
        }

        try {
            CosmeticType type = CosmeticType.valueOf(typeStr);
            PlayerDataStorage storage = CosmeticsPlusPlugin.getInstance().getStorage();
            storage.unequipCosmetic(getUUIDFromSender(sender), type.getConfigKey());
            
            sender.sendMessage("&aYou unequipped your " + type.getDisplayName().toLowerCase() + "!");
        } catch (IllegalArgumentException e) {
            sender.sendMessage("&cInvalid cosmetic type: " + typeStr);
        }
    }

    private void handleInfo(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("&cUsage: /cosmetic info <cosmetic_id>");
            return;
        }

        String cosmeticId = args[1];
        CosmeticManager manager = CosmeticsPlusPlugin.getInstance().getCosmeticManager();
        Cosmetic cosmetic = manager.getCosmetic(cosmeticId);

        if (cosmetic == null) {
            sender.sendMessage("&cInvalid cosmetic: " + cosmeticId);
            return;
        }

        sender.sendMessage("&6====== &e" + cosmetic.getName() + " &6======");
        sender.sendMessage("&7ID: " + cosmetic.getId());
        sender.sendMessage("&7Type: " + cosmetic.getType().getDisplayName());
        sender.sendMessage("&7Price: " + cosmetic.getPrice() + " coins");
        sender.sendMessage("&7Description: " + cosmetic.getDescription());
        sender.sendMessage("&7Owned: " + (hasCosmetic(sender, cosmeticId) ? "&aYes" : "&cNo"));
        sender.sendMessage("&6=================================");
    }

    private void handleMenu(CommandSender sender) {
        sender.sendMessage("&eOpening cosmetic menu...");
        sender.sendMessage("&7(GUI coming soon! Use commands for now.)");
    }

    private boolean hasCosmetic(CommandSender sender, String cosmeticId) {
        PlayerDataStorage storage = CosmeticsPlusPlugin.getInstance().getStorage();
        return storage.hasCosmetic(getUUIDFromSender(sender), cosmeticId);
    }

    private String getUuid(CommandSender sender) {
        return sender.getName();
    }

    private UUID getUUIDFromSender(CommandSender sender) {
        return UUID.nameUUIDFromBytes(sender.getName().getBytes());
    }
}
