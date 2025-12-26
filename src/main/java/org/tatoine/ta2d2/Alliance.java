package org.tatoine.ta2d2;

/**
 * Represents the alliance color in FTC matches.
 * Used to determine which alliance (RED or BLUE) the robot is on.
 */
public enum Alliance {
    RED,
    BLUE;

    /**
     * Returns the opposite alliance.
     * @return The opposing alliance color
     */
    public Alliance getOpposite() {
        return this == RED ? BLUE : RED;
    }

    /**
     * Checks if this alliance is RED.
     * @return true if RED, false otherwise
     */
    public boolean isRed() {
        return this == RED;
    }

    /**
     * Checks if this alliance is BLUE.
     * @return true if BLUE, false otherwise
     */
    public boolean isBlue() {
        return this == BLUE;
    }
}
