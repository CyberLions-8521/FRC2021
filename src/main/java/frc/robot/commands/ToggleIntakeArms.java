// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ToggleIntakeArms extends CommandBase {
  /** Creates a new ToggleIntakeArms. */
  Intake m_intake;
  boolean isDone;
  public ToggleIntakeArms(Intake intake) {
    m_intake = intake;
    isDone = false;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    if (m_intake.isExtended())
    {
      m_intake.retractArm();
    }
    else
    {
      m_intake.extendArm();
    }
    isDone = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isDone;
  }
}
