package engine;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Implements an object that stores the persistent game state.
 * 
 * @author <a href="mailto:rhdqor213@gmail.com">Junho Lee</a>
 * 
 */
public final class PermanentState {

	/** Money used in game. */
	private int coin = 0;
	/** Current ship shape. */
	private int shipShape = 0;
	/** Current ship color. */
	private int shipColor = 0;
	/** Current background Music. */
	private int BGM = 0;
	/** Current bullet sound effect. */
	private int bulletSFX = 0;
	/** Application logger. */
	private static Logger logger;

	private static PermanentState ps;

	private PermanentState() {
		try {
			this.coin = Core.getFileManager().loadCoins();
		} catch (IOException e) {
			logger.warning("Couldn't load coins!");
		}
	}

	public static PermanentState getInstance(){
		if (ps == null)
			ps = new PermanentState();
		return ps;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin += coin;
	}

	public int getShipShape() {
		return shipShape;
	}

	public void setShipShape(int shape) {
		this.shipShape = shape;
	}

	public int getShipColor() {
		return shipColor;
	}

	public void setShipColor(int color) { this.shipColor = color; }

	public int getBGM() {
		return BGM;
	}

	public void setBGM(int bgm) { this.BGM = bgm; }

	public int getBulletSFX() {
		return bulletSFX;
	}

	public void getBulletSFX(int sfx) { this.bulletSFX = sfx; }
}
