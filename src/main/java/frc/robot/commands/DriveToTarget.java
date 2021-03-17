// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.Drivebase;
// https://docs.limelightvision.io/en/latest/cs_drive_to_goal_2019.html
import frc.robot.subsystems.Limelight;
public class DriveToTarget extends CommandBase {
  /** Creates a new DriveToTarget. */
  Limelight m_cam;
  Drivebase m_db;
  
  public DriveToTarget(Drivebase db, Limelight cam) {
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
    double area = m_cam.getTa();
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

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
