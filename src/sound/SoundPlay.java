package sound;

public class SoundPlay{
    private static SoundPlay instance;

    private SoundPlay(){}
    //SoundPlay.getInstance().Play("파일명.wav"); 로 음악 재생
    //음악은 res폴더안에
    public void Play(String filename){
        SoundBgm s = new SoundBgm(filename);
        s.play();
    }

    public static SoundPlay getInstance(){
        if(instance == null) instance = new SoundPlay();
        return instance;
    }
}