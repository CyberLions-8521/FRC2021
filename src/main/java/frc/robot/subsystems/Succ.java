// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

public class Succ extends SubsystemBase {
  /** Creates a new Succ. */
  private final DoubleSolenoid m_intakeSolenoid = new DoubleSolenoid();

  public Succ() {
    /**
     * Operates ball intake
     */
    
    /**
     * Cease ball intake
     */



  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
