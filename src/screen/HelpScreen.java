package screen;

import java.awt.event.KeyEvent;

import java.io.IOException;
import java.util.List;

import engine.Core;
import engine.Score;

/**
 * Implements the high scores screen, it shows player records.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class HelpScreen extends Screen {

	/** List of past high scores. */
	static int page_num = 1;

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
	public HelpScreen(final int width, final int height, final int fps) {
		super(width, height, fps);

		this.returnCode = 1;

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
		if (inputManager.isKeyDown(KeyEvent.VK_SPACE)
				&& this.inputDelay.checkFinished())
			this.isRunning = false;
		else if(inputManager.isKeyDown(KeyEvent.VK_1)&& this.inputDelay.checkFinished()) {
			page_num = 1;
			
		}
		else if(inputManager.isKeyDown(KeyEvent.VK_2)&& this.inputDelay.checkFinished()) {
			page_num = 2;
		}
		
				
	}

	/**
	 * Draws the elements associated with the screen.
	 */
	private void draw() {
		drawManager.initDrawing(this);

		drawManager.drawHelpMenu(this, page_num);

		drawManager.completeDrawing(this);
	}
}