# TA2D2

An FTC (FIRST Tech Challenge) utility library by Team #12201 TATOINE.

## Features

TA2D2 provides essential utilities for FTC robot development:

- **Alliance** - Alliance color management (RED/BLUE)
- **Gamepads** - Gamepad input handling with deadzone and curve functions
- **MathUtils** - Common mathematical operations for robotics
- **Units** - Unit conversion utilities (distance, angles, speed)
- **Debug** - Debugging and logging helpers
- **LUT** - Look-Up Table for efficient value interpolation
- **PID** - PID controller for closed-loop control

## Installation

### Using as a Library

1. Download the latest JAR from releases
2. Add to your FTC project's `libs` folder
3. Add dependency in your `build.gradle`:

```gradle
dependencies {
    implementation files('libs/TA2D2-1.0.0.jar')
}
```

### Building from Source

```bash
./gradlew build
```

The compiled JAR will be in `build/libs/`.

## Usage Examples

### Alliance

```java
import org.tatoine.ta2d2.Alliance;

Alliance alliance = Alliance.RED;
if (alliance.isRed()) {
    // Do red alliance specific logic
}
Alliance opposite = alliance.getOpposite(); // Returns BLUE
```

### Gamepads

```java
import org.tatoine.ta2d2.Gamepads;

// Apply deadzone to joystick input
double leftY = Gamepads.applyDeadzone(gamepad1.left_stick_y);

// Apply curve for finer control
double power = Gamepads.squareInput(leftY);
```

### MathUtils

```java
import org.tatoine.ta2d2.MathUtils;

// Clamp value
double speed = MathUtils.clamp(power, -0.5, 0.5);

// Normalize angle
double heading = MathUtils.normalizeAngle(imu.getAngle());

// Calculate distance
double dist = MathUtils.distance(x1, y1, x2, y2);
```

### Units

```java
import org.tatoine.ta2d2.Units;

// Convert inches to meters
double meters = Units.inchesToMeters(24.0);

// Convert degrees to radians
double radians = Units.degreesToRadians(90.0);

// Convert RPM to radians per second
double angularVelocity = Units.rpmToRadiansPerSecond(300);
```

### Debug

```java
import org.tatoine.ta2d2.Debug;

// Simple logging
Debug.log("Robot initialized");

// Formatted logging
Debug.log("Position: (%.2f, %.2f)", x, y);

// Timing code execution
Debug.startTimer("loop");
// ... your code ...
Debug.logTimer("loop"); // Logs elapsed time
```

### LUT (Look-Up Table)

```java
import org.tatoine.ta2d2.LUT;

// Create a lookup table for motor characterization
double[] voltages = {0.0, 0.5, 1.0, 1.5, 2.0};
double[] speeds = {0.0, 10.0, 25.0, 45.0, 70.0};
LUT motorLUT = new LUT(voltages, speeds);

// Get interpolated speed for voltage
double speed = motorLUT.get(1.25); // Returns ~35.0
```

### PID

```java
import org.tatoine.ta2d2.PID;

// Create PID controller
PID headingController = new PID(0.1, 0.01, 0.05);
headingController.setSetpoint(90.0); // Target 90 degrees
headingController.setOutputLimits(-1.0, 1.0);

// In your control loop
double currentHeading = imu.getAngle();
double turnPower = headingController.calculate(currentHeading);
robot.turn(turnPower);

// Check if at target
if (headingController.atSetpoint(currentHeading, 2.0)) {
    // Within 2 degrees of target
}
```

## API Documentation

### Alliance

- `getOpposite()` - Returns the opposite alliance
- `isRed()` - Returns true if RED alliance
- `isBlue()` - Returns true if BLUE alliance

### Gamepads

- `applyDeadzone(value, deadzone)` - Applies deadzone to input
- `squareInput(value)` - Applies square curve
- `cubeInput(value)` - Applies cubic curve
- `clamp(value)` - Clamps value to [-1, 1]

### MathUtils

- `clamp(value, min, max)` - Clamps value to range
- `lerp(start, end, t)` - Linear interpolation
- `normalizeAngle(angle)` - Normalizes angle to [-180, 180)
- `angleDifference(from, to)` - Shortest angular distance
- `map(value, inMin, inMax, outMin, outMax)` - Maps value between ranges
- `distance(x1, y1, x2, y2)` - Euclidean distance

### Units

- Distance: `inchesToMeters`, `metersToInches`, `feetToMeters`, etc.
- Angle: `degreesToRadians`, `radiansToDegrees`
- Speed: `rpmToRadiansPerSecond`, `radiansPerSecondToRpm`

### Debug

- `log(message)` - Logs message if debug enabled
- `warn(message)` - Logs warning
- `error(message)` - Logs error
- `startTimer(name)` - Starts named timer
- `stopTimer(name)` - Stops timer and returns elapsed ms
- `logTimer(name)` - Logs timer result

### LUT

- `get(input)` - Gets interpolated output value
- `getMinInput()` - Returns minimum input value
- `getMaxInput()` - Returns maximum input value

### PID

- `setSetpoint(setpoint)` - Sets target value
- `setPID(kP, kI, kD)` - Sets PID gains
- `calculate(measurement)` - Calculates control output
- `reset()` - Resets controller state
- `setIntegralLimits(min, max)` - Prevents integral windup
- `setOutputLimits(min, max)` - Limits output range
- `atSetpoint(measurement, tolerance)` - Checks if at target

## License

See LICENSE file for details.

## Contributing

Contributions welcome! Please submit pull requests or open issues.

## Team

Created by TATOINE FTC Team #12201