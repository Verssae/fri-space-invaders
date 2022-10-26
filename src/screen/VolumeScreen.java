package screen;

import java.awt.event.KeyEvent;

import engine.Cooldown;
import engine.Core;
import sound.SoundPlay;
import sound.SoundType;

/**
 * Implements the title screen.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class VolumeScreen extends Screen {

	/** Milliseconds between changes in user selection. */
	private static final int SELECTION_TIME = 150;
	
	/** Time between changes in user selection. */
	private Cooldown selectionCooldown;
	private SoundPlay soundPlay = SoundPlay.getInstance();
	/**
	 * Constructor, establishes the properties of the screen.
	 * 
	 * @param width
	 *            Screen width.
	 * @param height
	 *            Screen height.
	 * @param fps
	 *            Frames per second, frame rate at which the game is run.
	 */
	public VolumeScreen(final int width, final int height, final int fps) {
		super(width, height, fps);

		// Defaults to play.
		this.returnCode = 100;
		this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
		this.selectionCooldown.reset();
	}

	/**
	 * Starts the action.
	 * 
	 * @return Next screen code.
	 */
	public final int run() {
		super.run();

		return this.returnCode;
	}

	/**
	 * Updates the elements on screen and checks for events.
	 */
	protected final void update() {
		super.update();

		draw();
		if (this.selectionCooldown.checkFinished()
				&& this.inputDelay.checkFinished()) {
			if (inputManager.isKeyDown(KeyEvent.VK_UP)
					|| inputManager.isKeyDown(KeyEvent.VK_W)) {
				previousMenuItem();
				this.selectionCooldown.reset();
			}
			if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
					|| inputManager.isKeyDown(KeyEvent.VK_S)) {
				nextMenuItem();
				this.selectionCooldown.reset();
			}
			if (inputManager.isKeyDown(KeyEvent.VK_RIGHT)
					|| inputManager.isKeyDown(KeyEvent.VK_D)) {
				soundPlay.play(SoundType.menuSelect);
				if(isBgm()){
					bgmVolumeControl(1);
				}
				if(isEffect()){
					effectVolumeControl(1);
				}
				this.selectionCooldown.reset();
			}
			if (inputManager.isKeyDown(KeyEvent.VK_LEFT)
					|| inputManager.isKeyDown(KeyEvent.VK_A)) {
				soundPlay.play(SoundType.menuSelect);
				if(isBgm()){
					bgmVolumeControl(-1);
				}
				if(isEffect()){
					effectVolumeControl(-1);
				}
				this.selectionCooldown.reset();
			}
			if (inputManager.isKeyDown(KeyEvent.VK_SPACE)&&this.returnCode==6){
				this.isRunning = false;
				soundPlay.play(SoundType.menuClick);
			}

		}
	}

	/**
	 * Shifts the focus to the next menu item.
	 */
	private void nextMenuItem() {
		if (this.returnCode == 101)
			this.returnCode = 6;
		else if(this.returnCode == 6)
			this.returnCode = 100;
		else
			this.returnCode++;
		soundPlay.play(SoundType.menuSelect);
	}

	/**
	 * Shifts the focus to the previous menu item.
	 */
	private void previousMenuItem() {
		if (this.returnCode == 6)
			this.returnCode = 101;
		else if (this.returnCode == 100)
			this.returnCode = 6;
		else
			this.returnCode--;
		soundPlay.play(SoundType.menuSelect);
	}

	private void bgmVolumeControl(int value){
		if((soundPlay.getBgmVolume()<100||value==-1)&&(soundPlay.getBgmVolume()>0||value==1)) {
			soundPlay.setBgmVolume(value);
		}
	}

	private void effectVolumeControl(int value) {
		if((soundPlay.getEffectVolume()<100||value==-1)&&(soundPlay.getEffectVolume()>0||value==1)) {
			soundPlay.setEffectVolume(value);
		}
	}

	private boolean isBgm(){
		return this.returnCode == 100;
	}

	private boolean isEffect(){
		return this.returnCode == 101;
	}

	/**
	 * Draws the elements associated with the screen.
	 */
	private void draw() {
			drawManager.initDrawing(this);
			
			drawManager.drawVolumeTitle(this);
			drawManager.drawVolume(this, 100, returnCode);
			
			drawManager.completeDrawing(this);
		
	}
}


