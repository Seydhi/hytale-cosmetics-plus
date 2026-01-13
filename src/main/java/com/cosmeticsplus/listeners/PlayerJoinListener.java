package com.cosmeticsplus.listeners;

import com.cosmeticsplus.storage.PlayerDataStorage;
import com.cosmeticsplus.utils.Logger;
import java.util.UUID;

public class PlayerJoinListener extends Listener {

    public PlayerJoinListener() {
        super("PlayerJoinListener");
    }

    public void onPlayerJoin(UUID playerUuid, String playerName) {
        Logger.info("Player joined: " + playerName);
        
        PlayerDataStorage storage = (PlayerDataStorage) com.cosmeticsplus.CosmeticsPlusPlugin.getInstance().getStorage();
        if (storage != null) {
            storage.loadPlayerData(playerUuid);
            Logger.debug("Loaded cosmetic data for " + playerName);
        }
    }

    @Override
    public void register() {
        Logger.info("Registered listener: " + getName());
    }
}
