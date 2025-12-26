package org.tatoine.ta2d2;

import java.util.Arrays;

/**
 * Look-Up Table (LUT) class for FTC robotics.
 * Provides efficient value interpolation using pre-computed tables.
 * Useful for motor characterization, sensor calibration, and non-linear mappings.
 */
public class LUT {

    private final double[] inputValues;
    private final double[] outputValues;

    /**
     * Creates a new Look-Up Table.
     * Input and output arrays must be the same length and have at least 2 elements.
     * Input values must be in ascending order.
     * 
     * @param inputValues The input values (must be sorted in ascending order)
     * @param outputValues The corresponding output values
     * @throws IllegalArgumentException if arrays are invalid
     */
    public LUT(double[] inputValues, double[] outputValues) {
        if (inputValues == null || outputValues == null) {
            throw new IllegalArgumentException("Input and output arrays cannot be null");
        }
        if (inputValues.length != outputValues.length) {
            throw new IllegalArgumentException("Input and output arrays must have the same length");
        }
        if (inputValues.length < 2) {
            throw new IllegalArgumentException("Arrays must have at least 2 elements");
        }
        
        // Verify inputs are sorted
        for (int i = 1; i < inputValues.length; i++) {
            if (inputValues[i] <= inputValues[i - 1]) {
                throw new IllegalArgumentException("Input values must be in strictly ascending order");
            }
        }

        this.inputValues = Arrays.copyOf(inputValues, inputValues.length);
        this.outputValues = Arrays.copyOf(outputValues, outputValues.length);
    }

    /**
     * Gets the interpolated output value for the given input.
     * Uses linear interpolation between points.
     * Values outside the table range are clamped to the nearest endpoint.
     * 
     * @param input The input value
     * @return The interpolated output value
     */
    public double get(double input) {
        // Clamp to table range
        if (input <= inputValues[0]) {
            return outputValues[0];
        }
        if (input >= inputValues[inputValues.length - 1]) {
            return outputValues[outputValues.length - 1];
        }

        // Binary search for the right interval
        int index = binarySearch(input);
        
        // Linear interpolation
        double x0 = inputValues[index];
        double x1 = inputValues[index + 1];
        double y0 = outputValues[index];
        double y1 = outputValues[index + 1];
        
        double t = (input - x0) / (x1 - x0);
        return y0 + t * (y1 - y0);
    }

    /**
     * Finds the index of the largest input value less than or equal to the target.
     * 
     * @param target The target value
     * @return The index
     */
    private int binarySearch(double target) {
        int low = 0;
        int high = inputValues.length - 1;
        
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (inputValues[mid] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        
        return low;
    }

    /**
     * Gets the minimum input value in the table.
     * @return The minimum input value
     */
    public double getMinInput() {
        return inputValues[0];
    }

    /**
     * Gets the maximum input value in the table.
     * @return The maximum input value
     */
    public double getMaxInput() {
        return inputValues[inputValues.length - 1];
    }

    /**
     * Gets the size of the look-up table.
     * @return The number of entries
     */
    public int size() {
        return inputValues.length;
    }
}
