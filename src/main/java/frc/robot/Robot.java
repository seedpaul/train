// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import frc.robot.Subsystems.LED;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Subsystems.LED;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {

  private final WPI_VictorSPX leftFront = new WPI_VictorSPX(10);
  private final WPI_VictorSPX leftRear = new WPI_VictorSPX(9);
  private final WPI_VictorSPX rightFront = new WPI_VictorSPX(11);
  private final WPI_VictorSPX rightRear = new WPI_VictorSPX(12);

  private final MotorControllerGroup leftMCG = new MotorControllerGroup(leftFront, leftRear);
  private final MotorControllerGroup rightMCG = new MotorControllerGroup(rightFront, rightRear);

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftMCG, rightMCG);
  private final Joystick sidewinder = new Joystick(0);

  private final VictorSP headVictor = new VictorSP(0);
  private final LED led = new LED();

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    rightMCG.setInverted(true);
    led.start();
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(sidewinder.getRawAxis(1),sidewinder.getRawAxis(0));
    
    double speed =  sidewinder.getRawAxis(2);

    if(sidewinder.getTriggerPressed()){
      headVictor.set(speed);
    }
    if(sidewinder.getTriggerReleased()){
      headVictor.set(0.0);
    }
  }
}
