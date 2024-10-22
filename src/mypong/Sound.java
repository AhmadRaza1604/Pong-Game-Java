package mypong;
import java.io.File;
import javax.sound.sampled.*;

public class Sound {    
    Clip clip1;

    public void setFile(String fileName)
    {
    try
    {
        File file =new File(fileName);
        AudioInputStream sound = AudioSystem.getAudioInputStream(file);        
        clip1 = AudioSystem.getClip();
        clip1.open(sound);
        clip1.start();
    }
    catch(Exception e)
    {
        System.out.println("Something Wrong!");
    }
   
    }        
}
