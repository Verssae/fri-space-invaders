package engine;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Implements an object that stores the persistent game state.
 * 
 * @author <a href="mailto:rhdqor213@gmail.com">Junho Lee</a>
 * 
 */
public final class PermanentState {

	public enum State {
		coin,
		shipShape,
		shipColor,
		BGM,
		bulletSFX
	}
	LinkedHashMap<State, Integer> p_state;

	/** Application logger. */
	private static Logger logger;

	private static PermanentState ps;

	private final FileManager fileManager = FileManager.getInstance();

	private PermanentState() {
		try {
			this.p_state = fileManager.loadP_state();
		} catch (IOException e) {
			logger.warning("Couldn't load p_state!");
		}
	}

	public static PermanentState getInstance(){
		if (ps == null)
			ps = new PermanentState();
		return ps;
	}
	public void saveP_state() throws IOException {
		fileManager.saveP_state(p_state);
	}
	public int getP_state(State key) {
		return p_state.get(key);
	}

	public void gainP_state(State key, int value) {
		p_state.replace(key, getP_state(key) + value);
		try {
			saveP_state();
		} catch (IOException e) {
			logger.info(e.toString());
		}
	}

	public void setP_state(State key, int value){
		p_state.replace(key, value);
		try {
			saveP_state();
		} catch (IOException e) {
			logger.info(e.toString());
		}
	}
}
