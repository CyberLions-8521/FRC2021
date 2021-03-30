// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.LimelightSeek;
import frc.robot.commands.RotateCommand;
import frc.robot.commands.IntakeTL; //intake command here 
// import frc.robot.commands.Rotate90;
import frc.robot.Constants.XBOX;
import frc.robot.commands.Drive;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.IntakeSuccTL; //Intake here
import edu.wpi.first.wpilibj2.command.Command;
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
  private IntakeSuccTL m_intake = new IntakeSuccTL();

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
  private final String Xintake = "Intake";

  // Button Bindings
  // JoystickButton ButtonB;
  CommandWriter test = new CommandWriter();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drivebase.setDefaultCommand(m_driveSystem);
    // m_limelight.turnOffLED();
    // SendableChooser stuff
    m_chooser.addOption("Rotate 90 Degrees CCW", rotate);
    m_chooser.addOption("Find ball", seek);
    SmartDashboard.putData(m_chooser);
    // Configure the button bindings
    //ButtonB = new JoystickButton(m_controller, XBOX.B); 
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {
    // ButtonB.whenPressed(new Rotate90(m_drivebase));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // https://github.com/Cyberheart6009/FRC-2020-Robot/blob/master/src/main/java/frc/robot/RobotContainer.java
  public Command getAutonomousCommand() {
    String m_selected = m_chooser.getSelected();
    Command m_command;

    switch (m_selected)
    {
      case rotate:
        m_command = (new RotateCommand(m_drivebase));
        break;
      case seek:
        m_command = (new LimelightSeek(m_drivebase, m_limelight));
        break;
      default:
        m_command = (new LimelightSeek(m_drivebase, m_limelight));
        break; 
      case Xintake: 
        m_command = (new IntakeTL(m_intake));
        break;
    }

    return m_command;
  }
}
