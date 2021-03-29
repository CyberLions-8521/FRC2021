package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveMode;
import frc.robot.Constants.XBOX;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;









public class IntakeSuccTL extends SubsystemBase 
{
    String intakeMode = "Intake Mode";

    //Intake Motor
    /**************************************************************/
    CANSparkMax kIntake = new CANSparkMax(Constants.CAN.kIntake, MotorType.kBrushed);
    /**************************************************************/

    public static DriveMode s_intakeMode; //intake here


    public IntakeSuccTL()
    {
        s_intakeMode = DriveMode.XINTAKE0;

    
    
    }

    public void intakeMethod(XboxController controller)
    {

        //Default mode is XINTAKE0 (off)
        s_intakeMode = DriveMode.XINTAKE;
        
    }

    public void intakeWitchController(XboxController controller)
    {
        //Use the X button to switch Intake Steal or Spit
        if (RobotContainer.m_controller.getXButtonPressed())
        {
            if (s_intakeMode == DriveMode.XINTAKE0)
            {
                //XINTAKE is Taking the ball in
                s_intakeMode = DriveMode.XINTAKE;
                intakeMode = "Intake Mode STEAL";
                
            }
            else if (s_intakeMode == DriveMode.XINTAKE)
            {
                //XINTAKE0 SPits the ball out
                s_intakeMode = DriveMode.XINTAKE0;
                intakeMode = "Intake Mode SPIT";
                //it
            }
        }
        switch (s_intakeMode)
        {
            case XINTAKE0:
            //INTAKE IS ALWAYS ON Trying to steal Ball
            double i_rightSpeed = DriveConstants.MAX_OUTPUT;
            kIntake.set(0.5);
            //Make XINTAKE0 Take IN
            //SmartDashboard.putNumber("Right Speed", i_rightSpeed);
            case XINTAKE:
            //INTAKE TURNS ON MOTOR SET TO 0.5
            double i_leftSpeed = DriveConstants.MAX_OUTPUT;
            kIntake.set(0.5);
            //Make XINTAKE Sit OUT Ball
            //SmartDashboard.putNumber("Left SPeed", i_leftSpeed);
        }
    }

    





}
