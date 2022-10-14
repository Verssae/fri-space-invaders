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
    private AudioInputStream stream;

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

            stream = AudioSystem.getAudioInputStream(new File(soundPath));
            AudioFormat format = stream.getFormat();
            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(),
                        format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2,
                        format.getFrameRate(), true); // big endian
                stream = AudioSystem.getAudioInputStream(format, stream);
            }

            SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, stream.getFormat(),
                    ((int) stream.getFrameLength() * format.getFrameSize()));
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
