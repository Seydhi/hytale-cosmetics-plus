package com.cosmeticsplus.utils;

public class Logger {

    private static final String PREFIX = "[CosmeticsPlus] ";

    public static void info(String message) {
        System.out.println(PREFIX + message);
    }

    public static void warn(String message) {
        System.out.println(PREFIX + "[WARN] " + message);
    }

    public static void error(String message) {
        System.err.println(PREFIX + "[ERROR] " + message);
    }

    public static void debug(String message) {
        System.out.println(PREFIX + "[DEBUG] " + message);
    }

    public static void success(String message) {
        System.out.println(PREFIX + "[SUCCESS] " + message);
    }
}
