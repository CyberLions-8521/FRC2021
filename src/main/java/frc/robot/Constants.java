// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{

    public static class DriveConstants
    {
        // Just a coefficient to dampen how fast the robot turns
        public static final double STEER_K = 0.1;
        // Highest the robot can turn autonomously
        public static final double MAX_OUTPUT = 0.5;
        public static final double DRIVE_SLOW = 0.4;
        public static final double TURN_SLOW = 0.5;
        // Steering adjust is never zero, so we choose a number where the robot is basically centered on the target
        public static final double STEER_THRESHOLD = 3;
        // Constant for the slew rate limiter
        // Limits the rate of change of a signal (joystick input) to 0.5 units per second
        public static final double RATE_LIMIT = 0.5;
    }

    public static class VisionConstants
    {
        // Area of the ball in the camera view when the robot stops approaching it
        public static final double BALL_AREA = 0.7;
    }

    public static class CAN
    {
        public static final int kLeftMaster = 2;
        public static final int kRightMaster = 3;
        public static final int kLeftSlave = 1;
        public static final int kRightSlave = 4;
        public static final int kIntake = 5;
    }

    public static class IO 
    {
        public static final int kXBOX = 0;
        public static final int kAuxCtrl = 1;
    }

    public static class XBOX
    {
        public static final int LEFT_STICK_X = 0;
        public static final int LEFT_STICK_Y = 1;
        public static final int LEFT_TRIGGER = 2;
        public static final int RIGHT_TRIGGER = 3;
        public static final int RIGHT_STICK_X = 4;
        public static final int RIGHT_STICK_Y = 5;
        public static final int A = 1;
        public static final int B = 2;
        public static final int X = 3;
        public static final int Y = 4;
        public static final int LB = 5;
        public static final int RB = 6;
        public static final int LOGO_LEFT = 7;
        public static final int LOGO_RIGHT = 8;
        public static final int LEFT_STICK_BUTTON = 9;
        public static final int RIGHT_STICK_BUTTON = 10;
    }

    public enum DriveMode
    {
        ARCADE, TANK
    }

}
