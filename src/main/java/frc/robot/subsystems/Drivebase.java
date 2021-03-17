// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveMode;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;

public class Drivebase extends SubsystemBase {
  /** Creates a new Drivebase. */
  // Descriptions
  String driveMode = "Drive Mode";


  // Motors
  CANSparkMax m_leftMaster = new CANSparkMax(Constants.CAN.kLeftMaster, MotorType.kBrushed);
  CANSparkMax m_rightMaster = new CANSparkMax(Constants.CAN.kRightMaster, MotorType.kBrushed);
  CANSparkMax m_leftSlave = new CANSparkMax(Constants.CAN.kLeftSlave, MotorType.kBrushed);
  CANSparkMax m_rightSlave = new CANSparkMax(Constants.CAN.kRightSlave, MotorType.kBrushed);

  // Differential drive class
  DifferentialDrive m_drive = new DifferentialDrive(m_leftMaster, m_rightMaster);

  // Gyro
  AHRS m_gyro = new AHRS(SPI.Port.kMXP);

  // Drive Mode
  public static DriveMode m_mode;

  public Drivebase()
  {
    // Default mode is tank drive
    m_mode = DriveMode.TANK;

    m_leftSlave.follow(m_leftMaster);
    m_rightSlave.follow(m_rightMaster);

    // Invert the motors
    m_leftMaster.setInverted(false);
    m_rightMaster.setInverted(false);    

    // If we want to set max output
    // drive.setMaxOutput(0.9);
  }

  @Override
  public void periodic()
  {
    // This method will be called once per scheduler run
    double tHeading = getHeading().getDegrees();


    SmartDashboard.putNumber("Heading", tHeading);
  }

  public Rotation2d getHeading()
  {
    return Rotation2d.fromDegrees(-m_gyro.getAngle());
  }


  
  public void driveWithController(XboxController controller)
  {

    if (RobotContainer.m_controller.getYButtonPressed())
    {
      if (m_mode == DriveMode.TANK)
      {
        m_mode = DriveMode.ARCADE;
        driveMode = "Arcade Drive";
      }
      else if (m_mode == DriveMode.ARCADE)
      {
        m_mode = DriveMode.TANK;
        driveMode = "Tank Drive";
      }
    }

    switch (m_mode)
    {
      case TANK:
        // left speed, right speed, squared inputs
        double leftSpeed = controller.getRawAxis(Constants.XBOX.LEFT_STICK_Y);
        double rightSpeed = controller.getRawAxis(Constants.XBOX.RIGHT_STICK_Y);
        SmartDashboard.putNumber("Left Speed", leftSpeed);
        SmartDashboard.putNumber("Right Speed", rightSpeed);
        m_drive.tankDrive(leftSpeed, rightSpeed, true);
        break;
      case ARCADE:
        double speed = controller.getRawAxis(Constants.XBOX.LEFT_STICK_Y);
        double turnRate = controller.getRawAxis(Constants.XBOX.RIGHT_STICK_X);
        m_drive.arcadeDrive(speed, turnRate, true);
        SmartDashboard.putNumber("Speed", speed);
        SmartDashboard.putNumber("Turn Rate", turnRate);
        break;
    }

    // Display values to smart dashboard
    SmartDashboard.putString("Drive Mode", driveMode);
  }
}
