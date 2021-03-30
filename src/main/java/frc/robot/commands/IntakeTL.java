package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSuccTL;

//This class is used to control Intake On and Off via XBOX Controller

public class IntakeTL extends CommandBase 
{
    private final IntakeSuccTL m_IntakeZero;
    //fix above
    

    //Command is called when initially scheduled
    @Override
    public void initialize() {}

    @Override
    public void execute()
    {
        //Command here will execute turning on motor on or off 
        m_IntakeZero.IntakeWithController(RobotContainer.m_controller); // change this up to a button on and off not drive

    }


    /*
    *Dont Touch anything below here
    ----------------------------------------------------------------------------------------
    */

    //Command downbelow ends the execution if Button B is pressed again

    //Called if command is interrupted or ends
    @Override
    public void end(boolean interrupted) {}

    //Command ends = Command = true
    @Override
    public boolean isFinished()
    {
        return false;
    }
    
}
