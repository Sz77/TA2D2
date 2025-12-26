package org.tatoine.ta2d2;

/**
 * Unit conversion utilities for FTC robotics.
 * Provides conversions between different units commonly used in robotics.
 */
public class Units {

    // Distance conversions
    public static final double INCHES_PER_METER = 39.3701;
    public static final double CENTIMETERS_PER_METER = 100.0;
    public static final double MILLIMETERS_PER_METER = 1000.0;
    public static final double FEET_PER_METER = 3.28084;
    public static final double INCHES_PER_FOOT = 12.0;

    // Angular conversions
    public static final double DEGREES_PER_RADIAN = 180.0 / Math.PI;
    public static final double RADIANS_PER_DEGREE = Math.PI / 180.0;

    /**
     * Converts inches to meters.
     * @param inches The distance in inches
     * @return The distance in meters
     */
    public static double inchesToMeters(double inches) {
        return inches / INCHES_PER_METER;
    }

    /**
     * Converts meters to inches.
     * @param meters The distance in meters
     * @return The distance in inches
     */
    public static double metersToInches(double meters) {
        return meters * INCHES_PER_METER;
    }

    /**
     * Converts centimeters to meters.
     * @param centimeters The distance in centimeters
     * @return The distance in meters
     */
    public static double centimetersToMeters(double centimeters) {
        return centimeters / CENTIMETERS_PER_METER;
    }

    /**
     * Converts meters to centimeters.
     * @param meters The distance in meters
     * @return The distance in centimeters
     */
    public static double metersToCentimeters(double meters) {
        return meters * CENTIMETERS_PER_METER;
    }

    /**
     * Converts millimeters to meters.
     * @param millimeters The distance in millimeters
     * @return The distance in meters
     */
    public static double millimetersToMeters(double millimeters) {
        return millimeters / MILLIMETERS_PER_METER;
    }

    /**
     * Converts meters to millimeters.
     * @param meters The distance in meters
     * @return The distance in millimeters
     */
    public static double metersToMillimeters(double meters) {
        return meters * MILLIMETERS_PER_METER;
    }

    /**
     * Converts feet to meters.
     * @param feet The distance in feet
     * @return The distance in meters
     */
    public static double feetToMeters(double feet) {
        return feet / FEET_PER_METER;
    }

    /**
     * Converts meters to feet.
     * @param meters The distance in meters
     * @return The distance in feet
     */
    public static double metersToFeet(double meters) {
        return meters * FEET_PER_METER;
    }

    /**
     * Converts inches to feet.
     * @param inches The distance in inches
     * @return The distance in feet
     */
    public static double inchesToFeet(double inches) {
        return inches / INCHES_PER_FOOT;
    }

    /**
     * Converts feet to inches.
     * @param feet The distance in feet
     * @return The distance in inches
     */
    public static double feetToInches(double feet) {
        return feet * INCHES_PER_FOOT;
    }

    /**
     * Converts degrees to radians.
     * @param degrees The angle in degrees
     * @return The angle in radians
     */
    public static double degreesToRadians(double degrees) {
        return degrees * RADIANS_PER_DEGREE;
    }

    /**
     * Converts radians to degrees.
     * @param radians The angle in radians
     * @return The angle in degrees
     */
    public static double radiansToDegrees(double radians) {
        return radians * DEGREES_PER_RADIAN;
    }

    /**
     * Converts RPM (revolutions per minute) to radians per second.
     * @param rpm The rotational speed in RPM
     * @return The rotational speed in radians per second
     */
    public static double rpmToRadiansPerSecond(double rpm) {
        return rpm * (2.0 * Math.PI / 60.0);
    }

    /**
     * Converts radians per second to RPM.
     * @param radiansPerSecond The rotational speed in radians per second
     * @return The rotational speed in RPM
     */
    public static double radiansPerSecondToRpm(double radiansPerSecond) {
        return radiansPerSecond * (60.0 / (2.0 * Math.PI));
    }
}
