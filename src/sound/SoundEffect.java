package sound;

import engine.Core;
import engine.FileManager;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.logging.Logger;

public class SoundEffect {
    private static int volume = 70;
    private SourceDataLine line;

    public File effectFileLoader;
    private AudioInputStream stream;

    protected Logger logger;

    public enum SoundType{
        enemyKill,
        hit
    }

    public  SoundEffect(SoundType type){
        switch (type){
            case enemyKill:
                this.LoadEffect("EnemyKill_001.wav");
                break;
            case hit:
                this.LoadEffect("Hit_001.wav");
                break;
        }
    }
    public void LoadEffect(String filename){
        this.logger = Core.getLogger();

        try{
            String jarPath = FileManager.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath();
            jarPath = URLDecoder.decode(jarPath, "UTF-8");

            String soundPath = new File(jarPath).getParent();
            soundPath += File.separator;
            soundPath += "fri-space-invaders/" + filename;


            effectFileLoader = new File(soundPath);

            stream = AudioSystem.getAudioInputStream(effectFileLoader);
            AudioFormat effectFormat = stream.getFormat();
            if (effectFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                effectFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, effectFormat.getSampleRate(),
                        effectFormat.getSampleSizeInBits() * 2, effectFormat.getChannels(), effectFormat.getFrameSize() * 2,
                        effectFormat.getFrameRate(), true); // big endian
                stream = AudioSystem.getAudioInputStream(effectFormat, stream);
            }

            SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, stream.getFormat(),
                    ((int) stream.getFrameLength() * effectFormat.getFrameSize()));
            line = (SourceDataLine) AudioSystem.getLine(info);

            //set volume
            if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl volumeControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(20.0f * (float) Math.log10(volume / 100.0));

            }

            line.open(stream.getFormat());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play() throws IOException {
        line.start();
        int numRead;
        byte[] buf = new byte[line.getBufferSize()];
        while ((numRead = stream.read(buf, 0, buf.length)) >= 0) {
            int offset = 0;
            while (offset < numRead) {
                offset += line.write(buf, offset, numRead - offset);
            }
        }
    }

    public void stop(){
        line.drain();
        line.stop();
    }

    /**
     * Setting volume
     * @param value
     */
    public void setVolume(int value){volume = value;}

    /**
     * getting volume
     * @return volume
     */
    public int getVolume(){return volume;}
}
