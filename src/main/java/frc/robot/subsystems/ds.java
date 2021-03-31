package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//Double Solenoid will be executed in IntakeTL along with IntakeSucc


public class ds extends SubsystemBase
{
    enum solenoideModeX
    {
        kForward, kOff, kReverse
    }

    boolean kForward = true;
    boolean kOff = false;

    public Object DoubleSolenoid()
    {
        // 0123 right
        // 0145 left
        DoubleSolenoid m_ds = new DoubleSolenoid(0123, 0145); // Channels 
        m_ds.set(kForward);
    }

    public void toggle()
    {
        if (m_controller.getBButtonPressed())
        {
            switch (solenoideModeX)
            {
                case kForward:
                m_ds.toggle(kForward);
                break;
                case kOff:
                m_ds.toggle(kOff);
            }
            
        }
    }

    public static void set(double int kForward)
    {
        m_ds.set(0.5);
        //Motor Double solenoid forward
    }
    public void set(double int kReverse)
    {
        m_ds.set(0);
        //Double solenoide turned off
    }

    public void set(double int kOff)
    {
        
    }
    //ZZZ idko adsada
    
}
