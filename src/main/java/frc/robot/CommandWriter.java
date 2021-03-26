package frc.robot;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is used to write throttle/speed values to a csv file.
 */
class CommandWriter
{
    private String mPath;
    File file;
    FileOutputStream fout;
    boolean success;
    // Default constructor
    public CommandWriter()
    {
        mPath = "/home/lvuser/something.txt";
        try
        {
          file = new File(mPath);
          file.createNewFile();
          fout = new FileOutputStream(file, false);
          String content = "testing";
          fout.write(content.getBytes());
          fout.flush();
          fout.close();
          success = true;
        }
        catch (IOException e)
        {
          success = false;
        }
    }

    public boolean getIsReady()
    {
      return success;
    }

    // public void writeNumber(double num)
    // {
    //     // Convert to a string
    //     String output = num + "";
    //     if (success)
    //     {
    //       fout.write(output.getBytes());
    //       fout.flush();
    //     }
    // }

    // // Closes the file
    // public void closeFile()
    // {
    //   fout.close();
    // }





}