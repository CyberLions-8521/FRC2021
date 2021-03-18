// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Limelight;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.VisionConstants;

public class LimelightSeek extends CommandBase {
  /** Creates a new LimelightSeek. */
  Limelight m_cam;
  Drivebase m_db;
  boolean m_targetFound;
  double m_steeringAdjust;

  public LimelightSeek(Drivebase db, Limelight cam)
  {
    m_cam = cam;
    m_db = db;
    m_steeringAdjust = 0.0;
    m_targetFound = m_cam.getIsTargetFound();
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
    searchForTarget();
    
    // Since m_steeringAdjust isn't ever TRULY zero, we choose a number close enough to 0
    // when the robot stops to determine if we found the ball and centered ourselves onto it
    
    // If we found the target and we're centered on it
    if (m_targetFound && m_steeringAdjust < DriveConstants.STEER_THRESHOLD)
    {
      moveToTarget();
    }



    // m_steeringAdjust = 0.0;
    // m_targetFound = m_cam.getIsTargetFound();
    // double tx = m_cam.getTx();
    // if (!m_targetFound)
    // {
    //   m_steeringAdjust = 0.5;
    // }
    // else
    // {
    //   m_steeringAdjust = Math.min(DriveConstants.STEER_K * tx, DriveConstants.MAX_OUTPUT);
    // }
    
    // m_db.turnInPlace(-m_steeringAdjust);
    
    // double area = m_cam.getTa();
    // if (m_steeringAdjust < 0.25)
    // {
    //   double speed = 0.0;
    //   if (area < 1.0)
    //   {
    //     speed = -DriveConstants.DRIVE_SLOW;
    //   }
    //   else
    //   {
    //     speed = 0.0;
    //   }
    //   m_db.moveForward(speed);
    // }
  }

  /**
   * Uses limelight output data to search for the target
   * If it doesn't detect any balls in sight, it'll spin clockwise to search for it
   * If it sees a ball, it'll turn and center itself onto it
   */
  public void searchForTarget()
  {
    // Check if the target is in the view
    m_targetFound = m_cam.getIsTargetFound();
    // Get the offset from the center of the camera and the target
    double offset = m_cam.getTx();

    // If we do not see the target, adjust the steering
    if (!m_targetFound)
    {
      m_steeringAdjust = 0.5;
    }
    // We DO see the target
    else
    {
      // Steer rate changes based on offset times steer rate
      // The highest you could steer is MAX_OUTPUT (0.7)
      m_steeringAdjust = Math.min(DriveConstants.STEER_K * offset, DriveConstants.MAX_OUTPUT);
      // Note: The offset (tx) is 0 if the robot is perfectly centered onto the ball
    }

    // Turn based on the steering adjustment rate determined by the if/else above
    m_db.turnInPlace(-m_steeringAdjust);

    // Update the value on the dashboard
    SmartDashboard.putNumber("Steering Adjust", m_steeringAdjust);

  }

  /**
   * Moves toward a target that the limelight detects by looking at the area of the ball relative to the screen
   */
  public void moveToTarget()
  {
    // Speed at which the robot moves toward the ball
    double speed = 0.0;
    // The amount of space the ball takes up in the camera view
    double area = m_cam.getTa();
    if (area < VisionConstants.BALL_AREA)
    {
      // Then move towards it slowly
      speed = -DriveConstants.DRIVE_SLOW;
    }
    else
    {
      speed = 0.0;
    }
    // Actually move
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
