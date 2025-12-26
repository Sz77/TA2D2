package org.tatoine.ta2d2;

/**
 * Utility class for gamepad input handling in FTC.
 * Provides helper methods for working with gamepad inputs,
 * such as deadzone application and button state tracking.
 */
public class Gamepads {
    
    /**
     * Default deadzone value for joystick inputs.
     */
    public static final double DEFAULT_DEADZONE = 0.1;

    /**
     * Applies a deadzone to a joystick value.
     * Values within the deadzone are returned as 0.
     * 
     * @param value The raw joystick value (-1.0 to 1.0)
     * @param deadzone The deadzone threshold (0.0 to 1.0)
     * @return The processed value with deadzone applied
     */
    public static double applyDeadzone(double value, double deadzone) {
        if (Math.abs(value) < deadzone) {
            return 0.0;
        }
        // Scale the value to maintain smooth control outside deadzone
        double sign = Math.signum(value);
        return sign * (Math.abs(value) - deadzone) / (1.0 - deadzone);
    }

    /**
     * Applies the default deadzone to a joystick value.
     * 
     * @param value The raw joystick value (-1.0 to 1.0)
     * @return The processed value with default deadzone applied
     */
    public static double applyDeadzone(double value) {
        return applyDeadzone(value, DEFAULT_DEADZONE);
    }

    /**
     * Applies a square curve to joystick input for finer control at low speeds.
     * Preserves the sign of the input.
     * 
     * @param value The joystick value (-1.0 to 1.0)
     * @return The squared value with sign preserved
     */
    public static double squareInput(double value) {
        return Math.signum(value) * value * value;
    }

    /**
     * Applies a cubic curve to joystick input for even finer control at low speeds.
     * 
     * @param value The joystick value (-1.0 to 1.0)
     * @return The cubed value
     */
    public static double cubeInput(double value) {
        return value * value * value;
    }

    /**
     * Clamps a value between -1.0 and 1.0.
     * 
     * @param value The value to clamp
     * @return The clamped value
     */
    public static double clamp(double value) {
        return Math.max(-1.0, Math.min(1.0, value));
    }
}
