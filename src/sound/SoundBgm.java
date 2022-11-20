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

public class SoundBgm {
    public Clip bgmClip;
    public File bgmFileLoader;
    private FloatControl volumeControl;
    private int volume;
    protected Logger logger;

    public SoundBgm(String filename){
        this.logger = Core.getLogger();
        try{
            String soundPath = "res/sound/" + filename;
            bgmFileLoader = new File(soundPath);
            AudioInputStream bgmInputStream = AudioSystem.getAudioInputStream(bgmFileLoader);
            bgmClip = AudioSystem.getClip();
            bgmClip.open(bgmInputStream);
            bgmClip.loop(-1);
            // 볼륨 설정용
            if(bgmClip.isControlSupported(FloatControl.Type.MASTER_GAIN)){
                volumeControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(20f * (float) Math.log10(volume / 100.0)); //백분율
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
        bgmClip.start();
    }

    public void setLoop(boolean isLoop){
        if(isLoop){
            bgmClip.loop(-1);
        } else {
            bgmClip.loop(0);
        }
    }
    public void stop(){ bgmClip.stop(); }

    public void bgmVolume(int volume){ 
        this.volume = volume;
        if(bgmClip.isControlSupported(FloatControl.Type.MASTER_GAIN)){
            volumeControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(20f * (float) Math.log10(volume / 100.0)); //백분율
        } 
    }

//    public void random(String fileNames[]){
//
//    }

}