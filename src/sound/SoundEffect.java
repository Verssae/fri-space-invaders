package sound;

import engine.Core;

import java.util.logging.Logger;

public class SoundEffect {

    protected Logger logger;
    public SoundEffect(String filename){
        this.logger = Core.getLogger();
        try{

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){}

    public void stop(){}
}
