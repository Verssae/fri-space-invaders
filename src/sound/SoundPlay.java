package sound;

import engine.PermanentState;
public class SoundPlay{
    private static SoundPlay instance;
    private int effectVolume = 70;
    private int bgmVolume = 50;
    private SoundBgm sb;

    private SoundPlay(){}
    public static SoundPlay getInstance(){
        if(instance == null) instance = new SoundPlay();
        return instance;
    }

    public void play(SoundType type){
        switch (type){
            case hit:
                playEffect("sound/Hit_001.wav");
                break;
            case shoot:
                playEffect("sound/Shoot_00" + PermanentState.getInstance().getBulletSFX() + ".wav");
                break;
            case enemyKill:
                playEffect("sound/EnemyKill_001.wav");
                break;
            case menuClick:
                playEffect("sound/MenuClick_001.wav");
                break;
            case menuSelect:
                playEffect("sound/MenuSelect_001.wav");
                break;
            case mainGameBgm:
                playBgm("BGM_MainGame_00" + PermanentState.getInstance().getBGM() + ".wav");
                break;
            case gameOverBGM:
                playBgmLoop("GameOver_001.wav", false);
                break;
            case roundClear:
                playEffect("sound/RoundClear_001.wav");
                break;
            case roundStart:
                playEffect("sound/RoundStart_001.wav");
                break;
            case bonusEnemyKill:
                playEffect("sound/bonusEnemyKill_001.wav");
                break;
            case roundCounting:
                playEffect("sound/RoundCounting_001.wav");
                break;

    }

    //SoundPlay.getInstance().Play("파일명.wav"); 로 음악 재생
    //음악은 res폴더안에
    private void playBgm(String filename){
        if(sb == null || !sb.bgmClip.isActive()){
            sb = new SoundBgm(filename);
            sb.bgmVolume(bgmVolume);
            sb.play();
        }
    }

    public void playBgmLoop(String filename, boolean isLoop){
        if(sb == null || !sb.bgmClip.isActive()){
            sb = new SoundBgm(filename);
            sb.setLoop(isLoop);
            sb.play();
        }
    }

    public void stopBgm(){
        if(sb.bgmClip.isActive()){
            sb.stop();
        }
    }


    private void playEffect(String filename) {
        SoundEffect se = new SoundEffect(filename);
        se.setVolume(effectVolume);
        se.play();
    }

    public void setEffectVolume(int value){
        this.effectVolume = value;
    }

    public void setBgmVolume(int value){
        this.bgmVolume = value;
    }

    public int getEffectVolume(){
        return effectVolume;
    }

    public int getBgmVolume(){
        return bgmVolume;
    }

}