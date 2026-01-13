package com.cosmeticsplus.commands;

import com.cosmeticsplus.CosmeticsPlusPlugin;
import com.cosmeticsplus.cosmetics.Cosmetic;
import com.cosmeticsplus.cosmetics.CosmeticManager;
import com.cosmeticsplus.storage.PlayerDataStorage;
import java.util.UUID;

public class UnlockCommand extends Command {

    public UnlockCommand() {
        super("unlock", "Unlock a cosmetic", "cosmeticsplus.unlock");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("&cUsage: /unlock <cosmetic_id>");
            return;
        }

        String cosmeticId = args[0];
        CosmeticManager manager = CosmeticsPlusPlugin.getInstance().getCosmeticManager();
        Cosmetic cosmetic = manager.getCosmetic(cosmeticId);

        if (cosmetic == null) {
            sender.sendMessage("&cInvalid cosmetic: " + cosmeticId);
            sender.sendMessage("&eUse /cosmetic list to see available cosmetics");
            return;
        }

        if (!cosmetic.isEnabled()) {
            sender.sendMessage("&cThis cosmetic is currently disabled!");
            return;
        }

        PlayerDataStorage storage = CosmeticsPlusPlugin.getInstance().getStorage();
        UUID playerUuid = getUUIDFromSender(sender);

        if (storage.hasCosmetic(playerUuid, cosmeticId)) {
            sender.sendMessage("&cYou already own this cosmetic!");
            return;
        }

        unlockCosmetic(sender, cosmetic, playerUuid);
    }

    private void unlockCosmetic(CommandSender sender, Cosmetic cosmetic, UUID playerUuid) {
        PlayerDataStorage storage = CosmeticsPlusPlugin.getInstance().getStorage();

        storage.unlockCosmetic(playerUuid, cosmetic.getId());

        sender.sendMessage("&aSuccessfully unlocked: &e" + cosmetic.getName() + "&a!");
        sender.sendMessage("&7Price: " + cosmetic.getPrice() + " coins");
        sender.sendMessage("&e/cosmetic equip " + cosmetic.getId() + " to use it");
    }

    private UUID getUUIDFromSender(CommandSender sender) {
        return UUID.nameUUIDFromBytes(sender.getName().getBytes());
    }
}
