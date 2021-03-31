// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  CANSparkMax m_IntakeMotor = new CANSparkMax(Constants.CAN.kIntake, MotorType.kBrushed);
  // Not sure if these are the right numbers-- they probably aren't
  DoubleSolenoid m_ds = new DoubleSolenoid(1, 2);

  boolean m_isExtended;
  boolean m_isOn;

  public Intake()
  {
    retractArm();
    stopSucc();
  }

  public boolean isExtended()
  {
    return m_isExtended;
  }

  public boolean isOn()
  {
    return m_isOn;
  }

  public void extendArm()
  {
    m_ds.set(Value.kForward);
    m_isExtended = true;
  }

  public void retractArm()
  {
    m_ds.set(Value.kReverse);
    m_isExtended = false;
  }

  public void succ()
  {
    m_isOn = true;
    // Testing with a low speed first
    m_IntakeMotor.set(0.10);
  }

  public void stopSucc()
  {
    m_isOn = false;
    m_IntakeMotor.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
