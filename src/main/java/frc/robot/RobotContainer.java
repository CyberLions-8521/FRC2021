// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import java.sql.DriverPropertyInfo;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.LimelightSeek;
import frc.robot.commands.MoveForwardNSeconds;
import frc.robot.commands.RotateCommand;
import frc.robot.commands.ToggleIntakeArms;
import frc.robot.commands.ToggleIntakeMotor;
// import frc.robot.commands.Rotate90;
import frc.robot.Constants.XBOX;
import frc.robot.commands.CompressorControl;
import frc.robot.commands.Drive;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.CommandWriter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // PowerDistributionPanel m_pdp = new PowerDistributionPanel();
  // Subsystems
  public static Drivebase m_drivebase = new Drivebase();
  private Limelight m_limelight = new Limelight();
  private final Intake m_intake = new Intake();

  // Commands
  private final Drive m_driveSystem = new Drive(m_drivebase);
  
  // private final LimelightSeek m_seek = new LimelightSeek(m_drivebase, m_limelight);
  // private final RotateCommand m_turn = new RotateCommand(m_drivebase);
  
  // Controller
  public static final XboxController m_controller = new XboxController(Constants.IO.kXBOX);
  public static final Joystick m_aux = new Joystick(1);
  // Testing random stuff out today with SendableChooser
  // private final SendableChooser<String> m_chooser = new SendableChooser<>();
  //private final String rotate = "rotate";
  //private final String seek = "seek";

  // Button Bindings
  // JoystickButton ButtonB;
  // public static final CommandWriter recorder = new CommandWriter();

  CompressorControl ctest = new CompressorControl(m_intake);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // m_pdp.clearStickyFaults();
    m_drivebase.setDefaultCommand(m_driveSystem);
    m_intake.setDefaultCommand(ctest);
    // m_limelight.turnOffLED();
    // SendableChooser stuff
    // m_chooser.addOption("Rotate 90 Degrees CCW", rotate);
    // m_chooser.addOption("Find ball", seek);
    // SmartDashboard.putData(m_chooser);
    // Configure the button bindings
    // ButtonB = new JoystickButton(m_controller, XBOX.B);
    configureButtonBindings(); //ll
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {
    new JoystickButton(m_controller, XBOX.B).whenPressed(new ToggleIntakeArms(m_intake));
    //new JoystickButton(m_controller, XBOX.B).whenPressed(new ToggleIntakeMotor(m_intake)); Thiens Edit
    new JoystickButton(m_controller, XBOX.B).whenPressed(new SequentialCommandGroup(
      new ToggleIntakeArms(m_intake),
      new WaitCommand(5),
      new ToggleIntakeArms(m_intake)
        //new ToggleIntakeMotor(m_intake)
    ));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // https://github.com/Cyberheart6009/FRC-2020-Robot/blob/master/src/main/java/frc/robot/RobotContainer.java
  public Command getAutonomousCommand() {
    return new ToggleIntakeMotor(m_intake);
  }  

  
}
