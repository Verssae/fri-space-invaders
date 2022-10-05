package engine;

public class SoundPlay{
    private String filename;
    private static SoundPlay instance = new SoundPlay();
    //SoundPlay.getInstance().Play("파일명.wav"); 로 음악 재생
    //음악은 res파일안에
    public void Play(String filename){
        Sound s = new Sound(filename);
        s.play();
    }

    public static SoundPlay getInstance(){
        return instance;
    }
}