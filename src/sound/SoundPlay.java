package sound;

import java.io.IOException;

public class SoundPlay{
    private String filename;
    private static SoundPlay instance;

    private SoundPlay(){}
    //SoundPlay.getInstance().Play("파일명.wav"); 로 음악 재생
    //음악은 res폴더안에
    public void playBgm(String filename){
        SoundBgm sb = new SoundBgm(filename);
        sb.play();
    }

    public static SoundPlay getInstance(){
        if(instance == null) instance = new SoundPlay();
        return instance;
    }

    public void playEffect(String filename) throws IOException {
        SoundEffect se = new SoundEffect(filename);
        se.play();
        se.stop();
    }
}