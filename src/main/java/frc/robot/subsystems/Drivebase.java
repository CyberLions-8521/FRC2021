// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivebase extends SubsystemBase {
  /** Creates a new Drivebase. */
  CANSparkMax leftMaster = new CANSparkMax(Constants.CAN.kLeftMaster, MotorType.kBrushed);
  CANSparkMax rightMaster = new CANSparkMax(Constants.CAN.kRightMaster, MotorType.kBrushed);
  CANSparkMax leftSlave = new CANSparkMax(Constants.CAN.kLeftSlave, MotorType.kBrushed);
  CANSparkMax rightSlave = new CANSparkMax(Constants.CAN.kRightSlave, MotorType.kBrushed);
  DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  public Drivebase()
  {
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    // Invert the motors
    leftMaster.setInverted(true);
    rightMaster.setInverted(false);    

    // If we want to set max output
    // drive.setMaxOutput(0.9);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveWithController(XboxController controller)
  {
    // left speed, right speed, squared inputs
    double leftSpeed = controller.getRawAxis(Constants.XBOX.LEFT_STICK_Y);
    double rightSpeed = controller.getRawAxis(Constants.XBOX.RIGHT_STICK_Y);
    drive.tankDrive(leftSpeed, rightSpeed, true);
    // System.out.println("Left Speed: " + leftSpeed);
    // System.out.println("Right Speed: " + rightSpeed);
  }
}
