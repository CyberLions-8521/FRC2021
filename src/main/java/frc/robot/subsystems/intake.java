package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotContainer;
import frc.robot.Constants;
// import frc.robot.Constants.DriveConstants;
// import frc.robot.Constants.XBOX;
// import edu.wpi.first.wpilibj.SPI;
// import edu.wpi.first.wpilibj.SlewRateLimiter;
// import edu.wpi.first.wpilibj.XboxController;

//I'm not entirely sure how the intake works yet so this may be spaghetti
//Currently operating under the assumption that the arm and succ do not work together

public class intake extends SubsystemBase {
    //intake motor
    //Operating under the assumption that the arm will be using 3 states: extend, retract, and off
    CANSparkMax m_intake = new CANSparkMax(Constants.CAN.kIntake, MotorType.kBrushed);

    // intake solenoid
    //Operating under the assumption that the intake solenoid will be using 3 states: forward, reverse, and off
    DoubleSolenoid m_intakeSolenoid = new DoubleSolenoid(1,2);

    //A variable that keeps track of/regulates the 3 states of the intake. 0 = off, 1 = forward, 2 = reverse
    int intake_state;

    //A variable that keeps track of/regulates the 3 states of the intake. 0 = off, 1 = forward, 2 = reverse
    int arm_state;
    
    public intake()
    {
        // Initializes the intake so it starts in the "off" state.
        armStop();
        succOff();
        intake_state = 0;
        arm_state = 0;

    }

    public void succAct()
    {
        //Toggle suction for Intake system
        m_intake.set(0.5);
        intake_state = 1;

    }

    public void succRelease()
    {
        //Toggle reverse for Intake system
        //Idk if we need to release anything we suck but its here just in case
        m_intake.set(-0.5);
        intake_state = 2;

    }

    public void succOff()
    {
        //Toggle off for Intake system
        m_intake.set(0.0);
        intake_state = 0;

    }

    public void armFwd()
    {
        //moves the intake arm forward
        m_intakeSolenoid.set(kForward);
        arm_state = 1;
    }

    public void armBack()
    {
        //moves the intake arm back
        m_intakeSolenoid.set(kReverse);
        arm_state = 2;
    }

    public void armStop()
    {
        //intake arm stops moving
        m_intakeSolenoid.set(kOff);
        arm_state = 0;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
            // Use the B button to switch the intake's state

        if (RobotContainer.m_controller.getBButtonPressed())
        {
            switch (intake_state)
            {
                case 0:
                    succAct();
                    break;
                case 1:
                    succRelease();
                    break;
                case 2:
                    succOff();
                    break;
        }
        //Add a button to switch the arm's state

    }
}     
}



