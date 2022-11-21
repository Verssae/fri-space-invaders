package engine;

import java.util.LinkedHashMap;

/**
 * Implements an object that stores the state of the game between levels.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class BattleState {
	LinkedHashMap<C_State, Integer> b_state;

	/**
	 * Constructor.
	 *
	 * @param c_state
	 *            Current chapter state.
	 */

	public BattleState(final LinkedHashMap c_state) {
		b_state = c_state;
	}

	public LinkedHashMap getB_state() {
		return b_state;
	}
	public void setB_state(LinkedHashMap b_state) {
		this.b_state = b_state;
	}
	public int getB_state(C_State key) {
		return b_state.get(key);
	}

	public void gainB_state(C_State key, int value) {
		b_state.replace(key, getB_state(key) + value);
	}

	public void setB_state(C_State key, int value){
		b_state.replace(key, value);
	}
}
