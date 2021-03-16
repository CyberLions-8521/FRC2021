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
import com.kauailabs.navx.frc.AHRS;;

public class Drivebase extends SubsystemBase {
  /** Creates a new Drivebase. */
  // Motors
  CANSparkMax m_leftMaster = new CANSparkMax(Constants.CAN.kLeftMaster, MotorType.kBrushed);
  CANSparkMax m_rightMaster = new CANSparkMax(Constants.CAN.kRightMaster, MotorType.kBrushed);
  CANSparkMax m_leftSlave = new CANSparkMax(Constants.CAN.kLeftSlave, MotorType.kBrushed);
  CANSparkMax m_rightSlave = new CANSparkMax(Constants.CAN.kRightSlave, MotorType.kBrushed);

  // Differential drive class
  DifferentialDrive m_drive = new DifferentialDrive(m_leftMaster, m_rightMaster);

  // Gyro
  // AHRS m_gyro = new AHRS();

  public Drivebase()
  {
    m_leftSlave.follow(m_leftMaster);
    m_rightSlave.follow(m_rightMaster);

    // Invert the motors
    m_leftMaster.setInverted(false);
    m_rightMaster.setInverted(false);    

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
    m_drive.tankDrive(leftSpeed, rightSpeed, true);
    // System.out.println("Left Speed: " + leftSpeed);
    // System.out.println("Right Speed: " + rightSpeed);
  }
}
