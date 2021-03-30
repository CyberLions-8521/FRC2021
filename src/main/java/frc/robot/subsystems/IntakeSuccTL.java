package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import frc.robot.RobotContainer;
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
import frc.robot.Constants.DriveMode;
*/






//Check if ready (Done) - Thien


public class IntakeSuccTL extends SubsystemBase 
{
   

    enum intakeModeX
    {
        IntakeOn, IntakeOff
    }

    intakeModeX m_IntakeX = intakeModeX.IntakeOff;

    //Intake Motor
    /**************************************************************/
    CANSparkMax kIntake = new CANSparkMax(Constants.CAN.kIntake, MotorType.kBrushed);
    /**************************************************************/


    public void kMotorOn()
    {
        m_IntakeX = intakeModeX.IntakeOn;
        kIntake.set(0.5);
    }

    public void kMotorOff()
    {
        m_IntakeX = intakeModeX.IntakeOff;
        kIntake.set(0);
        //help on set motor?
    }



    @Override
    public void periodic() 
    {
        // //Method called when Program Runs or executed
        // if (RobotContainer.m_controller.getBButtonPressed())
        // {
        //     switch (m_IntakeX)
        //     {
                
        //         case IntakeOn:
        //         kMotorOn();
    
                
        //         case IntakeOff:
        //         kMotorOff();
        //     }
        // }
    }
    public void IntakeWithController(XboxController controller)
    {
        //Press B to turn on or off intake.
        if (RobotContainer.m_controller.getBButtonPressed()) // I think X button was already taken.
        {
            switch (m_IntakeX)
            {
                case IntakeOn:
                kMotorOn();
                case IntakeOff:
                kMotorOff();

            }
            
        }
        
    }




    





}
