// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.LimelightSeek;
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

  // Subsystems
  private Drivebase m_drivebase = new Drivebase();
  private Limelight m_limelight = new Limelight();
  private final Intake m_intake = new Intake();

  // Commands
  private final Drive m_driveSystem = new Drive(m_drivebase);
  
  // private final LimelightSeek m_seek = new LimelightSeek(m_drivebase, m_limelight);
  // private final RotateCommand m_turn = new RotateCommand(m_drivebase);
  
  // Controller
  public static final XboxController m_controller = new XboxController(Constants.IO.kXBOX);

  // Testing random stuff out today with SendableChooser
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final String rotate = "rotate";
  private final String seek = "seek";

  private final SpookyAuto mTest = new SpookyAuto(m_drivebase);
  // Button Bindings
  // JoystickButton ButtonB;
  // public static final CommandWriter recorder = new CommandWriter();
  CommandWriter test = new CommandWriter();
  CompressorControl ctest = new CompressorControl(m_intake);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // m_drivebase.setDefaultCommand(m_driveSystem);
    m_intake.setDefaultCommand(ctest);
    // m_limelight.turnOffLED();
    // SendableChooser stuff
    m_chooser.addOption("Rotate 90 Degrees CCW", rotate);
    m_chooser.addOption("Find ball", seek);
    SmartDashboard.putData(m_chooser);
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
    // String m_selected = m_chooser.getSelected();
    // Command m_command;

    // switch (m_selected)
    // {
    //   case rotate:
    //     m_command = (new RotateCommand(m_drivebase));
    //     break;
    //   case seek:
    //     m_command = (new LimelightSeek(m_drivebase, m_limelight));
    //     break;
    //   default:
    //     m_command = (new LimelightSeek(m_drivebase, m_limelight));
    //     break; 
    // }

    return new SpookyAuto(m_drivebase);
    // return new ToggleIntakeArms(m_intake);
  }
}
