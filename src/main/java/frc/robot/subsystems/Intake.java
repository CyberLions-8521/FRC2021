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

  DoubleSolenoid m_leftDS = new DoubleSolenoid(4, 5);
  DoubleSolenoid m_rightDS = new DoubleSolenoid(0, 1);

  boolean m_isExtended;
  boolean m_isOn;

  public Intake()
  {
    retractArms();
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

  public void extendArms()
  {
    m_leftDS.set(Value.kForward);
    m_rightDS.set(Value.kForward);
    m_isExtended = true;
  }

  public void retractArms()
  {
    m_leftDS.set(Value.kReverse);
    m_rightDS.set(Value.kReverse);
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
