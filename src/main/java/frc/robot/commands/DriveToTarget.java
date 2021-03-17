// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
  public void initialize() {
    m_chooser.setDefaultoption("Default auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", M_chooser);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    Update_Limelight_Tracking();
    double steer = m_Controller.getX(Hand.kRight);
    double drive =-m_Controller.gety(Hand.kLeft);

    boolean auto = m_Controller.getAButton();

    steer *= 0.70;
    drive *= 0.70

    if (auto){
      if (m_LimelightHasValidTarget){
        m_Drive.arcadeDrive(M_LimelightDriveCommand,m_LimelightSteerCommand);

      }
      else{
        m_Drive.arcadeDrive(0.0,0.0);
    
      }
    }
    else{
      m_Drive.arcadeDrive(,steer);
    }
    
  }

  public void Update_LimeLight_Tracking(){
    //Numbers must be tuned pepesad

    //look on doc to see what they control
    final double STEER_K = 0.03;
    final double DRIVE_K = 0.26;
    final double DESIRED_TARGET_AREA = 13.0;
    final double MAX_DRIVE = 0.7;

    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    
    if (tv < 1.0){
      m_LimelightHasValidTarget = false;
      m_LimelightDriveCommand = 0.0;
      m_LimelightSteerCommand = 0.0;
      return;
    }

    m_LimelightHasValidTarget = true;

    //Start proportional steering
    double steer_cmd = tx * STEER_K;
    m_LimelightSteerCommand = steer_cmd;

    //drive until reach desired target
    double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

    //MAX OUTPUT ON ACCERATION SPEED TOWARDS GOAL

    if (drive_cmd > MAX_DRIVE){
      drive_CMD = MAX_DRIVE;
    }
    m_LightlightDriveCommand = drive_cmd;

  }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    break;
  }

  // Returns true when the command should end.
  //Finished assuming
  @Override
  public boolean isFinished(){
    return false;
  }
}
