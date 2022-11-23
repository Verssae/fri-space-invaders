package engine;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

/**
 * Implements an object that Items the persistent game state.
 *
 * @author <a href="mailto:iwtkmn0219@naver.com">Minjun Choi</a>
 *
 * */

public class ItemState {
    LinkedHashMap<I_State, Integer> i_state;
    private static Logger logger;
    private static ItemState iS;
    private final FileManager fileManager = FileManager.getInstance();

    private ItemState() {
        try {
            this.i_state = fileManager.loadI_State();
        } catch (IOException e) {
            logger.warning("Couldn't load i_state!");
        }
    }

    public static ItemState getInstance() {
        if (iS == null)
            iS = new ItemState();
        return iS;
    }

    public int getI_state(I_State key) {
        return i_state.get(key);
    }

    public void gainI_state(I_State key, int value) {
        i_state.replace(key, getI_state(key) + value);
        try {
            fileManager.saveI_State(i_state);
        } catch (IOException e) {
            logger.info(e.toString());
        }
    }

    public void setI_state(I_State key, int value) {
        i_state.replace(key, value);
        try {
            fileManager.saveI_State(i_state);
        } catch (IOException e) {
            logger.info(e.toString());
        }
    }

    /**
     * When you die, go back to First!
     * */
    public void initI_state() {
        for (int i = 0; i < I_State.values().length; i++) {
            i_state.replace(I_State.values()[i], 0);
        }
        try {
            fileManager.saveI_State(i_state);
        } catch (IOException e) {
            logger.info(e.toString());
        }
    }
}
