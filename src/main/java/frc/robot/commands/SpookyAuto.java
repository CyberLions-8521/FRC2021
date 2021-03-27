// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Drivebase;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CommandRunner;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class SpookyAuto extends CommandBase {
  /** Creates a new SpookyAuto. */
  Drivebase m_db;
  Scanner fin;
  boolean done;
  public SpookyAuto(Drivebase db) {
    m_db = db;
    done = false;
    // fin = new CommandRunner("/home/lvuser/test.txt");
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(db);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    try 
    {
      fin = new Scanner(new File("/home/lvuser/test.txt"));
    }
    catch (FileNotFoundException e)
    {

    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    double speed;
    double turn;
    if (fin != null && fin.hasNextDouble())
    {
      done = false;
      speed = fin.nextDouble();
      turn = fin.nextDouble();
      m_db.autoArcade(speed, turn);
    }
    else
    {
      done = true;
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
