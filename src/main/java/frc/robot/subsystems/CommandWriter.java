package frc.robot.subsystems;
import java.io.FileWriter;
import java.io.IOException;
// import frc.robot.subsystems.Drivebase;
// import the robot
/**
 * This class is used to write throttle/speed values to a csv file.
 */
public class CommandWriter
{
    private String mPath;
    FileWriter fout;
    boolean success;
    // Default constructor
    public CommandWriter()
    {
      try
      {
        mPath = "/home/lvuser/autonomousmacros/test.txt";
        fout = new FileWriter(mPath);
        success = true;
      }
      catch (IOException e)
      {
        success = false;
      }
    }

    public boolean isReady()
    {
      return success;
    }

    public void writeDouble(double value) throws IOException
    {
      if (isReady())
      {
        fout.append(""+ value + "\n");
      }
    }

    public void stopRecording()
    {
      try
      {
      fout.flush();
      fout.close();
      } catch (IOException e)
      {

      }
    }

    // // Closes the file
    // public void closeFile()
    // {
    //   fout.close();
    // }





}