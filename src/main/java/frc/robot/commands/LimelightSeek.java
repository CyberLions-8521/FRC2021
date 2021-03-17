// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Limelight;
import frc.robot.Constants.DriveConstants;

public class LimelightSeek extends CommandBase {
  /** Creates a new LimelightSeek. */
  Limelight m_cam;
  Drivebase m_db;
  boolean targetFound;

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
    double steeringAdjust = 0.0;
    targetFound = m_cam.getIsTargetFound();
    double tx = m_cam.getTx();
    if (!targetFound)
    {
      steeringAdjust = 0.3;
    }
    else
    {
      steeringAdjust = DriveConstants.kP*tx;
    }
    m_db.turnInPlace(steeringAdjust);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return targetFound;
  }
}
