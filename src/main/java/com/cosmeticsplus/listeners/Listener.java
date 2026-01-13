package com.cosmeticsplus.listeners;

import com.cosmeticsplus.utils.Logger;

public abstract class Listener {

    private final String name;

    public Listener(String name) {
        this.name = name;
    }

    public void register() {
        Logger.info("Registered listener: " + name);
    }

    public String getName() {
        return name;
    }
}
