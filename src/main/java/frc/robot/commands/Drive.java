// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivebase;
import frc.robot.RobotContainer;

// This class is the command used to control the robot via controller
public class Drive extends CommandBase {
  /** Creates a new Drive. */
  private final Drivebase m_db;
  public Drive(Drivebase db) {
    m_db = db;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(db);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    m_db.driveWithController(RobotContainer.m_controller);
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
