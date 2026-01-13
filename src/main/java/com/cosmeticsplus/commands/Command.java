package com.cosmeticsplus.commands;

import com.cosmeticsplus.utils.Logger;

public abstract class Command {

    protected final String name;
    protected final String description;
    protected final String permission;

    public Command(String name, String description, String permission) {
        this.name = name;
        this.description = description;
        this.permission = permission;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public void register() {
        Logger.info("Registered command: /" + name);
    }
}

interface CommandSender {
    String getName();
    void sendMessage(String message);
    boolean hasPermission(String permission);
}
