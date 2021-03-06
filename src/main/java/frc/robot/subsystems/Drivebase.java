// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveMode;
import frc.robot.Constants.XBOX;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SlewRateLimiter;
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

  // Filter thing pew pew pew
  // trying a smaller value for the rate limit
  SlewRateLimiter filter = new SlewRateLimiter(0.1);

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
    m_mode = DriveMode.ARCADE;

    m_gyro.reset();

    m_leftSlave.follow(m_leftMaster);
    m_rightSlave.follow(m_rightMaster);

    // Invert the motors
    m_leftMaster.setInverted(false);
    m_rightMaster.setInverted(false);

    m_leftMaster.setOpenLoopRampRate(0.2);
    m_rightMaster.setOpenLoopRampRate(0.2);
    m_leftSlave.setOpenLoopRampRate(0.2);
    m_rightSlave.setOpenLoopRampRate(0.2);
    // If we want to set max output
    m_drive.setMaxOutput(0.9);
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

  public double getAngle()
  {
    return m_gyro.getAngle();
  }

  public void turnInPlace(double adjust)
  {
    // m_drive.arcadeDrive(0.0, adjust);
    m_drive.tankDrive(adjust, -adjust);
  }

  public void moveForward(double speed)
  {
    m_drive.arcadeDrive(speed, 0.0);
  }
  
  public void driveWithController(XboxController controller)
  {

    // Use the Y button to switch between ARCADE and TANK
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
        double leftSpeed = controller.getRawAxis(XBOX.LEFT_STICK_Y) * DriveConstants.MAX_OUTPUT;
        double rightSpeed = controller.getRawAxis(XBOX.RIGHT_STICK_Y) * DriveConstants.MAX_OUTPUT;
        SmartDashboard.putNumber("Left Speed", leftSpeed);
        SmartDashboard.putNumber("Right Speed", rightSpeed);
        m_drive.tankDrive(leftSpeed, rightSpeed, false);
        break;
      case ARCADE:
        // double speed = filter.calculate(controller.getRawAxis(XBOX.LEFT_STICK_Y) * DriveConstants.MAX_OUTPUT);
        // double turnRate = filter.calculate(controller.getRawAxis(XBOX.RIGHT_STICK_X) * DriveConstants.MAX_OUTPUT);
        double speed = controller.getRawAxis(XBOX.LEFT_STICK_Y) * DriveConstants.MAX_OUTPUT;
        double turnRate = controller.getRawAxis(XBOX.RIGHT_STICK_X) * DriveConstants.MAX_OUTPUT;
        // Experimental: using LR triggers to increase/reduce speed
        // Decrease speed when right trigger pressed, increase speed when right button pressed
        if (controller.getRawAxis(XBOX.RIGHT_TRIGGER) > 0) turnRate = Math.copySign(0.30, turnRate);
        else if (controller.getRawButton(XBOX.RB)) 
        {
          turnRate /= Math.min(turnRate/DriveConstants.MAX_OUTPUT, Math.copySign(DriveConstants.MAX_OUTPUT, turnRate));
        }

        if (controller.getRawAxis(XBOX.LEFT_TRIGGER) > 0) speed = Math.copySign(0.30, speed);
        else if (controller.getRawButton(XBOX.LB))
        {
          speed = Math.min(speed/DriveConstants.MAX_OUTPUT, Math.copySign(DriveConstants.MAX_OUTPUT, speed));

        }
        //

        if (controller.getRawButton(XBOX.LOGO_RIGHT))
        {
          speed = filter.calculate(speed);
          turnRate = filter.calculate(speed);
        }

        m_drive.arcadeDrive(speed, -turnRate, true);

        SmartDashboard.putNumber("Speed", speed);
        SmartDashboard.putNumber("Turn Rate", turnRate);
        SmartDashboard.putNumber("X Displacement", m_gyro.getDisplacementX());
        SmartDashboard.putNumber("Y Displacement", m_gyro.getDisplacementY());
        SmartDashboard.putNumber("Z Displacement", m_gyro.getDisplacementZ());
        break;
    }

    // Display values to smart dashboard
    SmartDashboard.putString("Arcade Drive", driveMode);
  }

  // public void rotateByAngle(double degrees, boolean isClockwise)
  // {
  //   if (isClockwise)
  //     degrees = -degrees;
    
    
  // }


  public AHRS getGyro()
  {
    return m_gyro;
  }
}
