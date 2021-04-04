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
import frc.robot.commands.SpookyAuto;
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

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
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
  private final String rotate = "rotate";
  private final String seek = "seek";

  private final SpookyAuto mTest = new SpookyAuto(m_drivebase);
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
    // new JoystickButton(m_controller, XBOX.B).whenPressed(new SequentialCommandGroup(
    //   new ToggleIntakeArms(m_intake),
    //   new WaitCommand(5),
    //   new ToggleIntakeArms(m_intake)
    //   // new ToggleIntakeMotor(m_intake)
    // ));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // https://github.com/Cyberheart6009/FRC-2020-Robot/blob/master/src/main/java/frc/robot/RobotContainer.java
  public Command getAutonomousCommand() {
    
    // return new SequentialCommandGroup(
    //   new MoveForwardNSeconds(m_drivebase, m_intake).withTimeout(5),
    //   new RotateCommand(m_drivebase, 90.0)
    // );
    MoveForwardNSeconds t = new MoveForwardNSeconds(m_drivebase, m_intake, 0.4);
    // positive angle = clockwise, negative = ccw
    // return new RotateCommand(m_drivebase, -90.0);
    // Path A Red
    // return new SequentialCommandGroup(
    //   new ToggleIntakeArms(m_intake),
    //   new WaitCommand(1),
    //   new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(2.8),
    //   new WaitCommand(1),
    //   new RotateCommand(m_drivebase, -85.0),
    //   new WaitCommand(1),
    //   new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(1.8),
    //   new WaitCommand(1),
    //   new RotateCommand(m_drivebase, 65.0),
    //   new WaitCommand(1),
    //   new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(3.4)
    // );

    // Path B
    // return new SequentialCommandGroup(
    //   new ToggleIntakeArms(m_intake),
    //   new WaitCommand(1),
    //   new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(0.7),
    //   new WaitCommand(1),
    //   new RotateCommand(m_drivebase, 23.0),
    //   new WaitCommand(1),
    //   new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(2.8),
    //   new WaitCommand(1),
    //   new RotateCommand(m_drivebase, -84.0),
    //   new WaitCommand(1),
    //   new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(1.8),
    //   new WaitCommand(1),
    //   new RotateCommand(m_drivebase, 68.0),
    //   new WaitCommand(1),
    //   new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(3.8)
    // );
    
    return new SequentialCommandGroup(
      new WaitCommand(1),
      new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(0.9),
      new WaitCommand(1),
      new RotateCommand(m_drivebase, -48.3),
      new WaitCommand(1),
      new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(1.35),
      new WaitCommand(1),
      new RotateCommand(m_drivebase, 54.8),
      new WaitCommand(1),
      new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(2.9),
      new WaitCommand(1),
      new RotateCommand(m_drivebase, 40.0),
      new WaitCommand(1),
      new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(1.25),
      new WaitCommand(1),
      new RotateCommand(m_drivebase, -45.0),
      new WaitCommand(1),
      new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(0.8),
      new WaitCommand(1),
      new RotateCommand(m_drivebase, -90.0),
      new WaitCommand(1),
      new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(1.7),
      new WaitCommand(1),
      new RotateCommand(m_drivebase, -120.0),
      new WaitCommand(1),
      new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(2.1),
      new WaitCommand(1),
      new RotateCommand(m_drivebase, 42.0),
      new WaitCommand(1),
      new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(2.9),
      new WaitCommand(1),
      new RotateCommand(m_drivebase, 45.0),
      new WaitCommand(1),
      new MoveForwardNSeconds(m_drivebase, m_intake, 0.4).withTimeout(2.0)



    );
    // return new ToggleIntakeMotor(m_intake);
    
    // return 
  }
}
