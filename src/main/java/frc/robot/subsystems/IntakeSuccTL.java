package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveMode;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


/*
Don't Use or not using
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.XBOX;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants.DriveConstants;
*/








public class IntakeSuccTL extends SubsystemBase 
{
    String intakeMode = "Intake Mode";

    //Intake Motor
    /**************************************************************/
    CANSparkMax kIntake = new CANSparkMax(Constants.CAN.kIntake, MotorType.kBrushed);
    /**************************************************************/

    public void intakeWitchController(XboxController controller)
    {
        //Press B to turn on or off intake.
        if (RobotContainer.m_controller.getBButtonPressed()) // I think X button was already taken.
        {
            kIntake.set(0.5);
        }
        
    }




    





}
