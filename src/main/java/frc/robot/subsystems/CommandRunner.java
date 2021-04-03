// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import frc.robot.subsystems.Drivebase;

/** This class reads in some file and creates trajectories or something idk */
public class CommandRunner
{
    Scanner scanner;
    long startTime;
    boolean onTime = true;
    double nextDouble;

    public CommandRunner() throws FileNotFoundException
    {
        scanner = new Scanner(new File("/home/lvuser/test.txt"));
        scanner.useDelimiter(",|\\n");
        startTime = System.currentTimeMillis();
    }

    
    public void play(Drivebase db)
    {
        if ((scanner != null) && (scanner.hasNextDouble()))
        {
            double dt;

            if (onTime)
            {
                nextDouble = scanner.nextDouble();
            }

            dt = nextDouble - (System.currentTimeMillis()-startTime);

            if (dt <= 0)
            {
                db.getLeftMotor().set(scanner.nextDouble());
                db.getRightMotor().set(scanner.nextDouble());
                onTime = true;
            }
            else
            {
                onTime = false;
            }
        }
        else
        {
            stop(db);
            if (scanner != null)
            {
                scanner.close();
                scanner = null;
            }

        }
    }

    public void stop(Drivebase db)
    {
        db.getLeftMotor().stopMotor();
        db.getRightMotor().stopMotor();

        if (scanner != null)
        {
            scanner.close();
        }
    }
}
