// Import necessary WPILib and CTRE classes
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
    // Define motor controllers
    private final WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(50); // Replace with actual CAN ID
    private final WPI_TalonSRX leftRearMotor = new WPI_TalonSRX(5);  // Replace with actual CAN ID
    private final WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(6); // Replace with actual CAN ID
    private final WPI_TalonSRX rightRearMotor = new WPI_TalonSRX(7);  // Replace with actual CAN ID

    // DifferentialDrive system
    private DifferentialDrive driveTrain;

    // Xbox controller
    private final XboxController controller = new XboxController(0); // 0 for first USB port

    @Override
    public void robotInit() {
        // Configure motors to be in brushed mode
        leftFrontMotor.configFactoryDefault();
        leftRearMotor.configFactoryDefault();
        rightFrontMotor.configFactoryDefault();
        rightRearMotor.configFactoryDefault();

        leftFrontMotor.setNeutralMode(NeutralMode.Brake);
        leftRearMotor.setNeutralMode(NeutralMode.Brake);
        rightFrontMotor.setNeutralMode(NeutralMode.Brake);
        rightRearMotor.setNeutralMode(NeutralMode.Brake);

        // Set motors to brushed mode (use `ControlMode.PercentOutput` for percent control)
        leftFrontMotor.set(ControlMode.PercentOutput, 0);
        leftRearMotor.set(ControlMode.PercentOutput, 0);
        rightFrontMotor.set(ControlMode.PercentOutput, 0);
        rightRearMotor.set(ControlMode.PercentOutput, 0);

        // Link left and right motors for DifferentialDrive
        leftRearMotor.follow(leftFrontMotor);
        rightRearMotor.follow(rightFrontMotor);

        // Initialize DifferentialDrive
        driveTrain = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
    }

    @Override
    public void teleopPeriodic() {
      // Arcade drive (using the raw axis values for forward and rotation)
      double forward = controller.getRawAxis(4);  // Left stick Y-axis (invert if needed)
      double rotation = controller.getRawAxis(1);  // Right stick X-axis (invert if needed)

      driveTrain.arcadeDrive(forward, rotation);
    }

    @Override
    public void disabledInit() {
        // Stop the motors when disabled
        leftFrontMotor.set(0);
        rightFrontMotor.set(0);
    }
}
