package sound;

import java.io.IOException;


public class SoundPlay{
    private static SoundPlay instance;
    private SoundBgm sb;

    private SoundPlay(){}
    public static SoundPlay getInstance(){
        if(instance == null) instance = new SoundPlay();
        return instance;
    }

    public void play(SoundType type){
        switch (type){
            case hit:
                
            case shoot:

            case enemyKill:

            case menuClick:

            case mainGameBgm:


        }
    }

    //SoundPlay.getInstance().Play("파일명.wav"); 로 음악 재생
    //음악은 res폴더안에
    public void playBgm(String filename){
        if(sb == null){
            sb = new SoundBgm(filename);
            sb.play();
        }
    }

    public void stopBgm(){
        sb.stop();   
    }


    public void playEffect(String filename) throws IOException {
        SoundEffect se = new SoundEffect(filename);
        se.play();
        se.stop();
    }

}