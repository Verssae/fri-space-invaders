package sound;

import engine.Core;
import engine.FileManager;

import javax.sound.sampled.*;
import java.io.File;
import java.net.URLDecoder;
import java.util.logging.Logger;

public class SoundEffect {
    private static int volume;
    private Clip effectClip;
    public File effectFileLoader;
    private AudioInputStream effectInputStream;

    protected Logger logger;

    public SoundEffect(String filename){
        this.logger = Core.getLogger();

        try{
            String soundPath = "res/sound/" + filename;

            effectFileLoader = new File(soundPath);

            effectInputStream = AudioSystem.getAudioInputStream(effectFileLoader);
            effectClip = AudioSystem.getClip();
            //set volume
            if (effectClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl volumeControl = (FloatControl) effectClip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(20.0f * (float) Math.log10(volume / 100.0));
            }
            effectClip.open(effectInputStream);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play() {
        effectClip.start();
    }

    public void stop(){
        effectClip.stop();
    }

    /**
     * Setting volume
     * @param value
     */
    public void setVolume(int value){
        volume = value;
        if (effectClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl volumeControl = (FloatControl) effectClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(20.0f * (float) Math.log10(volume / 100.0));
        }
    }

}
