package sound;

import engine.Core;
import engine.FileManager;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.net.URLDecoder;
import java.util.logging.Logger;

public class SoundEffect {


    protected Logger logger;
    public SoundEffect(String filename){
        this.logger = Core.getLogger();
        try{
            String jarPath = FileManager.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath();
            jarPath = URLDecoder.decode(jarPath, "UTF-8");

            String soundPath = new File(jarPath).getParent();
            soundPath += File.separator;
            soundPath += "fri-space-invaders/" + filename;

            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(soundPath));
            AudioFormat format = stream.getFormat();
            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(),
                        format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2,
                        format.getFrameRate(), true); // big endian
                stream = AudioSystem.getAudioInputStream(format, stream);
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){}

    public void stop(){}
}
