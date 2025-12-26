package org.tatoine.ta2d2;

import java.util.HashMap;
import java.util.Map;

/**
 * Debug utility class for FTC development.
 * Provides logging and debugging helpers for robot code.
 */
public class Debug {

    private static boolean enabled = true;
    private static final Map<String, Long> timers = new HashMap<>();

    /**
     * Enables debug output.
     */
    public static void enable() {
        enabled = true;
    }

    /**
     * Disables debug output.
     */
    public static void disable() {
        enabled = false;
    }

    /**
     * Checks if debug is enabled.
     * @return true if enabled, false otherwise
     */
    public static boolean isEnabled() {
        return enabled;
    }

    /**
     * Logs a message if debug is enabled.
     * @param message The message to log
     */
    public static void log(String message) {
        if (enabled) {
            System.out.println("[DEBUG] " + message);
        }
    }

    /**
     * Logs a formatted message if debug is enabled.
     * @param format The format string
     * @param args The arguments
     */
    public static void log(String format, Object... args) {
        if (enabled) {
            System.out.println("[DEBUG] " + String.format(format, args));
        }
    }

    /**
     * Logs a warning message.
     * @param message The warning message
     */
    public static void warn(String message) {
        if (enabled) {
            System.out.println("[WARN] " + message);
        }
    }

    /**
     * Logs an error message.
     * @param message The error message
     */
    public static void error(String message) {
        System.err.println("[ERROR] " + message);
    }

    /**
     * Logs an error message with an exception.
     * @param message The error message
     * @param e The exception
     */
    public static void error(String message, Throwable e) {
        System.err.println("[ERROR] " + message);
        if (e != null) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a named timer.
     * @param name The timer name
     */
    public static void startTimer(String name) {
        timers.put(name, System.nanoTime());
    }

    /**
     * Stops a named timer and returns the elapsed time in milliseconds.
     * @param name The timer name
     * @return The elapsed time in milliseconds, or -1 if timer not found
     */
    public static double stopTimer(String name) {
        Long startTime = timers.remove(name);
        if (startTime == null) {
            warn("Timer '" + name + "' was not started");
            return -1.0;
        }
        long elapsed = System.nanoTime() - startTime;
        return elapsed / 1_000_000.0; // Convert to milliseconds
    }

    /**
     * Logs the elapsed time for a named timer.
     * @param name The timer name
     */
    public static void logTimer(String name) {
        double elapsed = stopTimer(name);
        if (elapsed >= 0) {
            log("%s took %.2f ms", name, elapsed);
        }
    }

    /**
     * Asserts a condition and logs an error if false.
     * @param condition The condition to check
     * @param message The error message if condition is false
     */
    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            error("Assertion failed: " + message);
        }
    }
}
