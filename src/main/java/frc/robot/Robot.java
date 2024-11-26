package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Subsystems.LED;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {

  private final WPI_VictorSPX leftFront = new WPI_VictorSPX(10);
  private final WPI_VictorSPX leftRear = new WPI_VictorSPX(9);
  private final WPI_VictorSPX rightFront = new WPI_VictorSPX(11);
  private final WPI_VictorSPX rightRear = new WPI_VictorSPX(12);

  private DifferentialDrive driveTrain;

    // Xbox controller
    private final XboxController controller = new XboxController(0); // 0 for first USB port

    private final LED led = new LED();

  @Override
  public void robotInit() {
        leftFront.configFactoryDefault();
        leftRear.configFactoryDefault();
        rightFront.configFactoryDefault();
        rightRear.configFactoryDefault();

        leftFront.setNeutralMode(NeutralMode.Brake);
        leftRear.setNeutralMode(NeutralMode.Brake);
        rightFront.setNeutralMode(NeutralMode.Brake);
        rightRear.setNeutralMode(NeutralMode.Brake);

        // Set s to brushed mode (use `ControlMode.PercentOutput` for percent control)
        leftFront.set(ControlMode.PercentOutput, 0);
        leftRear.set(ControlMode.PercentOutput, 0);
        rightFront.set(ControlMode.PercentOutput, 0);
        rightRear.set(ControlMode.PercentOutput, 0);

        // Link left and right s for DifferentialDrive
        leftRear.follow(leftFront);
        rightRear.follow(rightFront);

        // Initialize DifferentialDrive
        driveTrain = new DifferentialDrive(leftFront, rightFront);

        led.start();
    }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    driveTrain.arcadeDrive(controller.getRawAxis(1),controller.getRawAxis(0));
  }
  @Override
  public void disabledInit() {
      // Stop the motors when disabled
      leftFront.set(0);
      rightFront.set(0);
  }
}
