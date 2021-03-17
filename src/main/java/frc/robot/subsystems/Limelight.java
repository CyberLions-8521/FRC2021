// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Limelight extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  enum LEDStatus
  {
    ON, OFF
  }

  LEDStatus m_LEDStatus;
  String m_status;

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");

  public Limelight()
  {
    turnOnLED();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Horizontal Offset from crosshair to target (-27 degrees to 27 degrees)
    double x = tx.getDouble(0.0);
    // Vertical offset from crosshair to target (-20.5 degrees to 20.5 degrees)
    double y = ty.getDouble(0.0);
    // Target area (0% of image to 100% of image)
    double area = ta.getDouble(0.0);
    // Whether or not a valid target has appeared
    double valid = tv.getDouble(0.0);

    if (RobotContainer.m_controller.getAButtonPressed())
    {
      switch (m_LEDStatus)
      {
        case ON:
          turnOffLED();
          break;
        case OFF:
          turnOnLED();
          break;
      }
    }

    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("Valid Target", valid);
    SmartDashboard.putString("Limelight LED", m_status);
  }

  public void setEntry(String entry, int number)
  {
    table.getEntry(entry).setNumber(number);
  }

  public void turnOnLED()
  {
    m_LEDStatus = LEDStatus.ON;
    m_status = "On";
    setEntry("ledMode", 0);
  }

  public void turnOffLED()
  {
    m_LEDStatus = LEDStatus.OFF;
    m_status = "Off";
    setEntry("ledMode", 1);
  }

  // Getters
  public boolean getIsTargetFound()
  {
    return tv.getDouble(0.0) == 1.0;
  }

  public double getTx()
  {
    return tx.getDouble(0.0);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
