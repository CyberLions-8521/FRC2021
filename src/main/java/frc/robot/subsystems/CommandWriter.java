package frc.robot.subsystems;
// import java.io.FileWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
// import frc.robot.subsystems.Drivebase;
// import the robot
/**
 * This class is used to write throttle/speed values to a csv file.
 */
public class CommandWriter
{
    File file;
    FileOutputStream fout;
    private String mPath;
    // FileWriter fout;

    boolean success;
    // Default constructor
    public CommandWriter()
    {
      try
      {
        mPath = "/home/lvuser/test.txt";
        file = new File(mPath);
        file.createNewFile();
        fout = new FileOutputStream(file, false);
        // String content = "cow\n";
        // fout.write(content.getBytes());
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
        String content = ""+ value + "\n";
        fout.write(content.getBytes());
        // fout.flush();
        // fout.close();
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