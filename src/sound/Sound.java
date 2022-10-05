package sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URLDecoder;
import java.util.logging.Logger;
import engine.Core;
import engine.FileManager;

public class Sound {
    public Clip c;
    public File a;
    protected Logger logger;
    public Sound(String filename){
        this.logger = Core.getLogger();
        try{
            String jarPath = FileManager.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath();
            jarPath = URLDecoder.decode(jarPath, "UTF-8");

            String soundPath = new File(jarPath).getParent();
            soundPath += File.separator;
            soundPath += "fri-space-invaders/" + filename;

            a = new File(soundPath);
            AudioInputStream b = AudioSystem.getAudioInputStream(a);
            c = AudioSystem.getClip();
            c.open(b);
            // 볼륨 설정용
//            FloatControl volumeControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
//            volumeControl.setValue(-10.0f);
//            Thread.sleep(c.getMicrosecondLength()/1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
        c.start();
    }

    public void setLoop(boolean isLoop){
        if(isLoop){
            c.loop(-1);
        } else {
            c.loop(1);
        }
    }
    public void stop(){ c.stop(); }

//    public void random(String fileNames[]){
//
//    }

}