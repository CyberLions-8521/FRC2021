// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Limelight;
import frc.robot.Constants.DriveConstants;

public class LimelightSeek extends CommandBase {
  /** Creates a new LimelightSeek. */
  Limelight m_cam;
  Drivebase m_db;
  boolean targetFound;
  double steeringAdjust;

  public LimelightSeek(Drivebase db, Limelight cam)
  {
    m_cam = cam;
    m_db = db;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(db);
    addRequirements(cam);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    steeringAdjust = 0.0;
    targetFound = m_cam.getIsTargetFound();
    double tx = m_cam.getTx();
    if (!targetFound)
    {
      steeringAdjust = 0.5;
    }
    else
    {
      steeringAdjust = Math.min(DriveConstants.STEER_K * tx, DriveConstants.MAX_OUTPUT);
    }
    SmartDashboard.putNumber("Steering Adjust", steeringAdjust);
    m_db.turnInPlace(-steeringAdjust);

    double area = m_cam.getTa();
    if (steeringAdjust < 0.25)
    {
      double speed = 0.0;
      if (area < 1.0)
      {
        speed = -DriveConstants.DRIVE_SLOW;
      }
      else
      {
        speed = 0.0;
      }
      m_db.moveForward(speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // steeringadjust is never 0, find the point where it actually stops moving
    // return steeringAdjust < 0.14;
    return false;
  }
}
