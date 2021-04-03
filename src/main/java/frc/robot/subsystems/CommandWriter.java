package frc.robot.subsystems;

import java.io.IOException;
import java.io.FileWriter;


/**
 * This class is used to write throttle/speed values to a csv file.
 */
public class CommandWriter
{
    FileWriter fout;
    long startTime;
    private String mPath = "/home/lvuser/test.txt";

    boolean success;
    // Default constructor
    public CommandWriter() throws IOException
    {
      startTime = System.currentTimeMillis();
      fout = new FileWriter(mPath);
      success = true;
    }

    public boolean isReady()
    {
      return success;
    }
    
    public void record(Drivebase db) throws IOException
    {
      if (fout != null)
      {
        fout.append("" + (System.currentTimeMillis()-startTime));
        fout.append("," + db.getLeftMotor().get());
        fout.append("," + db.getRightMotor().get());
      }
    }

    public void stopRecording() throws IOException
    {
      if (fout != null)
      {
        fout.flush();
        fout.close();
      }
    }





}