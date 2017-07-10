package net.johnbrooks.fjg;

import java.io.*;
import java.net.URL;

/**
 * Created by ieatl on 7/10/2017.
 */
public class Updater
{
    private static float version = 1.0f;
    private static boolean updateAvailable = false;
    public static boolean isUpdateAvailable() { return updateAvailable; }

    public static boolean checkForUpdates()
    {
        try
        {
            saveFileFromUrlWithJavaIO("version.dat", "http://johnbrooks.net/towerdefense/version.dat");
        } catch (IOException e)
        {
            System.out.println("Could not download version checker.");
            e.printStackTrace();
            return false;
        }

        File file = new File("version.dat");
        if (!file.exists())
        {
            return false;
        }
        else
            System.out.println("Checking game version...");

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.startsWith("version="))
                {
                    line = line.replace("version=", "");
                    float version = Float.parseFloat(line);
                    if (Updater.version < version)
                    {
                        System.out.println("Update is available!");
                        updateAvailable = true;
                        reader.close();
                        file.delete();
                        return true;
                    }
                    else
                    {
                        System.out.println("Everything is up-to-date!");
                        file.delete();
                        reader.close();
                        return false;
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            file.delete();
            e.printStackTrace();
        }
        catch (IOException e)
        {
            file.delete();
            e.printStackTrace();
        }
        catch (NumberFormatException e)
        {
            file.delete();
            e.printStackTrace();
        }

        file.delete();
        return false;
    }

    // Using Java IO
    private static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl) throws IOException
    {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try
        {
            in = new BufferedInputStream(new URL(fileUrl).openStream());
            fout = new FileOutputStream(fileName);

            byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);

            }
        } finally {
            if (in != null)
                in.close();
            if (fout != null)
                fout.close();
        }
    }
}
