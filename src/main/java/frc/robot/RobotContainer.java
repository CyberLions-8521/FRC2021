// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.LimelightSeek;
import frc.robot.commands.RotateCommand;
// import frc.robot.commands.Rotate90;
import frc.robot.Constants.XBOX;
import frc.robot.commands.Drive;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Limelight;
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

  // Commands
  private final Drive m_driveSystem = new Drive(m_drivebase);
  private final LimelightSeek m_seek = new LimelightSeek(m_drivebase, m_limelight);
  private final RotateCommand m_turn = new RotateCommand(m_drivebase);
  
  // Controller
  public static final XboxController m_controller = new XboxController(Constants.IO.kXBOX);


  // Button Bindings
  // JoystickButton ButtonB;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drivebase.setDefaultCommand(m_driveSystem);
    // Configure the button bindings
    // ButtonB = new JoystickButton(m_controller, XBOX.B);
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
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return m_seek;
    return m_turn;
  }
}
