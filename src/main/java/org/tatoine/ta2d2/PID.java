package org.tatoine.ta2d2;

/**
 * PID (Proportional-Integral-Derivative) Controller for FTC robotics.
 * Used for closed-loop control of motors, servos, and other actuators.
 * Implements a standard PID controller with optional integral windup protection.
 */
public class PID {

    private double kP;
    private double kI;
    private double kD;
    
    private double setpoint;
    private double previousError;
    private double integral;
    private long lastTime;
    
    private double integralMin = Double.NEGATIVE_INFINITY;
    private double integralMax = Double.POSITIVE_INFINITY;
    
    private double outputMin = Double.NEGATIVE_INFINITY;
    private double outputMax = Double.POSITIVE_INFINITY;

    /**
     * Creates a new PID controller with the specified gains.
     * 
     * @param kP The proportional gain
     * @param kI The integral gain
     * @param kD The derivative gain
     */
    public PID(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.setpoint = 0.0;
        this.previousError = 0.0;
        this.integral = 0.0;
        this.lastTime = System.nanoTime();
    }

    /**
     * Sets the target setpoint.
     * 
     * @param setpoint The desired target value
     */
    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    /**
     * Gets the current setpoint.
     * 
     * @return The current setpoint
     */
    public double getSetpoint() {
        return setpoint;
    }

    /**
     * Sets the proportional gain.
     * 
     * @param kP The proportional gain
     */
    public void setP(double kP) {
        this.kP = kP;
    }

    /**
     * Sets the integral gain.
     * 
     * @param kI The integral gain
     */
    public void setI(double kI) {
        this.kI = kI;
    }

    /**
     * Sets the derivative gain.
     * 
     * @param kD The derivative gain
     */
    public void setD(double kD) {
        this.kD = kD;
    }

    /**
     * Sets the PID gains.
     * 
     * @param kP The proportional gain
     * @param kI The integral gain
     * @param kD The derivative gain
     */
    public void setPID(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    /**
     * Sets the integral limits to prevent windup.
     * 
     * @param min The minimum integral value
     * @param max The maximum integral value
     */
    public void setIntegralLimits(double min, double max) {
        this.integralMin = min;
        this.integralMax = max;
        this.integral = Math.max(min, Math.min(max, this.integral));
    }

    /**
     * Sets the output limits.
     * 
     * @param min The minimum output value
     * @param max The maximum output value
     */
    public void setOutputLimits(double min, double max) {
        this.outputMin = min;
        this.outputMax = max;
    }

    /**
     * Calculates the control output based on the current measurement.
     * 
     * @param measurement The current process variable value
     * @return The control output
     */
    public double calculate(double measurement) {
        long currentTime = System.nanoTime();
        double dt = (currentTime - lastTime) / 1_000_000_000.0; // Convert to seconds
        lastTime = currentTime;

        // Prevent division by zero on first call or if called too quickly
        if (dt <= 0.0) {
            dt = 0.001; // Default to 1ms
        }

        double error = setpoint - measurement;
        
        // Proportional term
        double p = kP * error;
        
        // Integral term with anti-windup
        integral += error * dt;
        integral = Math.max(integralMin, Math.min(integralMax, integral));
        double i = kI * integral;
        
        // Derivative term
        double derivative = (error - previousError) / dt;
        double d = kD * derivative;
        
        previousError = error;
        
        // Calculate output and apply limits
        double output = p + i + d;
        output = Math.max(outputMin, Math.min(outputMax, output));
        
        return output;
    }

    /**
     * Resets the controller state (integral and previous error).
     * Call this when starting a new control sequence or when the setpoint changes significantly.
     */
    public void reset() {
        integral = 0.0;
        previousError = 0.0;
        lastTime = System.nanoTime();
    }

    /**
     * Checks if the controller has reached the setpoint within a tolerance.
     * 
     * @param measurement The current measurement
     * @param tolerance The acceptable error tolerance
     * @return true if at setpoint, false otherwise
     */
    public boolean atSetpoint(double measurement, double tolerance) {
        return Math.abs(setpoint - measurement) <= tolerance;
    }
}
