// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** This class reads in some file and creates trajectories or something idk */
public class CommandRunner
{
    BufferedReader fin;
    boolean success;
    String line;
    ArrayList<Double> throttles;
    ArrayList<Double> turns;
    public CommandRunner(String path)
    {
        try
        {
            fin = new BufferedReader(new FileReader(path));
            success = true;
            line = fin.readLine();
            while (line != null)
            {
                SmartDashboard.putString("READING IN", line);
                throttles.add(Double.parseDouble(line));
                line = fin.readLine();
                if (line == null) break;
                turns.add(Double.parseDouble(line));
                line = fin.readLine();
                if (line == null) break;
            }
        }
        catch (FileNotFoundException e)
        {
            success = false;
        }
        catch (IOException e)
        {

        }
        
    }

    public boolean isReady()
    {
        return success;
    }

    public ArrayList<Double> getThrottles()
    {
        return throttles;
    }

    public ArrayList<Double> getTurns()
    {
        return turns;
    }
}
