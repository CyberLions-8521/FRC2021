// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.Drivebase;

public class RotateCommand extends CommandBase {
  /** Creates a new RotateCommand. */
  Drivebase m_db;
  double currentAngle;
  double targetAngle;
  public RotateCommand(Drivebase db) {
    m_db = db;
    addRequirements(db);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    m_db.getGyro().reset();
    currentAngle = m_db.getAngle();
    targetAngle = currentAngle + 90.0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    currentAngle = m_db.getAngle();
    if (currentAngle < targetAngle)
    {
      m_db.turnInPlace(-DriveConstants.TURN_SLOW);
    }
    else if (currentAngle > targetAngle)
    {
      m_db.turnInPlace(DriveConstants.TURN_SLOW);
    }
  

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(currentAngle-targetAngle) < 1);
  }
}
