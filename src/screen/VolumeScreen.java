package screen;

import java.awt.*;
import java.awt.event.KeyEvent;

import java.io.IOException;
import java.util.List;

import engine.Core;

public class VolumeScreen extends Screen{
	
	public VolumeScreen(final int width, final int height, final int fps) {
		super(width, height, fps);

		this.returnCode = 1;
	}

	public final int run() {
		super.run();

		return this.returnCode;
	}

	protected final void update() {
		super.update();

		draw();
		if (inputManager.isKeyDown(KeyEvent.VK_SPACE)
				&& this.inputDelay.checkFinished())
			this.isRunning = false;
	}

	private void draw() {
		drawManager.initDrawing(this);
		
		drawManager.drawVolumeTitle(this);
		drawManager.drawVolume(this, 100);
		
		drawManager.completeDrawing(this);
	
	}
}