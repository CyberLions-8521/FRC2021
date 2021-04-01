// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake;

//Command used to control the intake via button on controller
public class IntakeCommand extends CommandBase {
  /** Creates a new intake. */

  private final intake m_intake;

  public IntakeCommand(intake intake){

    m_intake = intake;
    //declares subsystem dependencies
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_intake.armStop();
    m_intake.succOff();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    m_intake.periodic();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {

  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
