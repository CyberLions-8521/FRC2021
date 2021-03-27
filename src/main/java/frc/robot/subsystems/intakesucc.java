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

//Motors
CANSparkMax kIntake = new CANSparkMax(Constants.CAN.kIntake, MotorType.kBrushed);

public class Succ extends SubsystemBase {

  boolean motorOn = false;  //For intake Turns on
  boolean motorOff = true; // For intake Turns off
  /** Creates a new Succ. */
  private final DoubleSolenoid m_intakeSolenoid = new DoubleSolenoid(X);

  public Succ() {
    /**
     * Operates ball intake
     */
    //For intake Button 
    if(controller.getRawButton(XBOX.X))
    {
      X.setPower(0.7);
    }
    if(controller.getRawButton(XBOX.X))
    {
      //If presssed again 
      kIntake.setPower(0);
    }
     
    /**
     * Cease ball intake
     * 
     */
    if (RobotContrainer.m_controller.getXButtonPressed())
    {
      motorOn = !motorOff;
      break;

    }

  }

 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmarttDashboard.putNumber("Intake Speed", intakeSPD);
    if (RobotContainer.m_controller.getXButtonPressed())
    {
      switch (Succ)
      {
        case ON:
          motorOn();
          break;
        case OFF:
          motorOff();
          break;
      }
    


  }

}