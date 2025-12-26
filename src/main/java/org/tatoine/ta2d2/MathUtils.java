package org.tatoine.ta2d2;

/**
 * Mathematical utility functions for FTC robotics.
 * Provides common math operations used in robot control and navigation.
 */
public class MathUtils {

    /**
     * Clamps a value between a minimum and maximum.
     * 
     * @param value The value to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Clamps an integer value between a minimum and maximum.
     * 
     * @param value The value to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Linearly interpolates between two values.
     * 
     * @param start The starting value
     * @param end The ending value
     * @param t The interpolation factor (0.0 to 1.0)
     * @return The interpolated value
     */
    public static double lerp(double start, double end, double t) {
        return start + (end - start) * t;
    }

    /**
     * Normalizes an angle to the range [-180, 180) degrees.
     * 
     * @param angle The angle in degrees
     * @return The normalized angle
     */
    public static double normalizeAngle(double angle) {
        while (angle >= 180.0) {
            angle -= 360.0;
        }
        while (angle < -180.0) {
            angle += 360.0;
        }
        return angle;
    }

    /**
     * Normalizes an angle to the range [0, 360) degrees.
     * 
     * @param angle The angle in degrees
     * @return The normalized angle
     */
    public static double normalizeAngle360(double angle) {
        while (angle >= 360.0) {
            angle -= 360.0;
        }
        while (angle < 0.0) {
            angle += 360.0;
        }
        return angle;
    }

    /**
     * Calculates the shortest angular distance between two angles.
     * 
     * @param from The starting angle in degrees
     * @param to The target angle in degrees
     * @return The shortest angular distance (-180 to 180 degrees)
     */
    public static double angleDifference(double from, double to) {
        return normalizeAngle(to - from);
    }

    /**
     * Checks if a value is within a tolerance of a target.
     * 
     * @param value The value to check
     * @param target The target value
     * @param tolerance The tolerance
     * @return true if within tolerance, false otherwise
     */
    public static boolean isNear(double value, double target, double tolerance) {
        return Math.abs(value - target) <= tolerance;
    }

    /**
     * Applies a deadband to a value.
     * 
     * @param value The input value
     * @param deadband The deadband threshold
     * @return 0 if within deadband, otherwise the original value
     */
    public static double deadband(double value, double deadband) {
        return Math.abs(value) < deadband ? 0.0 : value;
    }

    /**
     * Maps a value from one range to another.
     * 
     * @param value The input value
     * @param inMin The minimum of the input range
     * @param inMax The maximum of the input range
     * @param outMin The minimum of the output range
     * @param outMax The maximum of the output range
     * @return The mapped value
     */
    public static double map(double value, double inMin, double inMax, double outMin, double outMax) {
        return (value - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }

    /**
     * Calculates the Euclidean distance between two points.
     * 
     * @param x1 The x-coordinate of the first point
     * @param y1 The y-coordinate of the first point
     * @param x2 The x-coordinate of the second point
     * @param y2 The y-coordinate of the second point
     * @return The distance between the points
     */
    public static double distance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
