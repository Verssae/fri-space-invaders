package screen;


import engine.*;
import entity.Ship;
import sound.SoundType;

import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.Queue;
import java.util.LinkedList;


public class MapScreen extends Screen {

    /** Milliseconds until the screen accepts user input. */
    private static final int SELECTION_TIME = 200;
    private static final Logger logger = Core.getLogger();

    private Cooldown selectionCooldown;

    /**
     * Constructor, establishes the properties of the screen.
     *
     * @param width  Screen width.
     * @param height Screen height.
     * @param fps    Frames per second, frame rate at which the game is run.
     */
    private ChapterState chapterState;

    public MapScreen(final ChapterState chapterState,final int width, final int height, final int fps) {
        super(width, height, fps);

        this.chapterState = chapterState;
        this.returnCode = 1;
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
    }

    public final int run() {
        super.run();

        return this.returnCode;
    }
    protected final void update() {
        super.update();
        draw();
        if (this.inputDelay.checkFinished()){
            if (inputManager.isKeyDown(KeyEvent.VK_UP)
                    || inputManager.isKeyDown(KeyEvent.VK_W)) {
                sound.SoundPlay.getInstance().play(SoundType.menuSelect);
                chapterState.curPosMove(3);
                this.inputDelay.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
                    || inputManager.isKeyDown(KeyEvent.VK_S)) {
                sound.SoundPlay.getInstance().play(SoundType.menuSelect);
                chapterState.curPosMove(2);
                this.inputDelay.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_RIGHT) // 뽑기 버튼으로 가기
                    || inputManager.isKeyDown(KeyEvent.VK_D)) {
                sound.SoundPlay.getInstance().play(SoundType.menuSelect);
                chapterState.curPosMove(0);
                this.inputDelay.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_LEFT) // 메뉴 선택으로 되돌아가기
                    || inputManager.isKeyDown(KeyEvent.VK_A)) {
                sound.SoundPlay.getInstance().play(SoundType.menuSelect);
                chapterState.curPosMove(1);
                this.inputDelay.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_SPACE)){
                sound.SoundPlay.getInstance().play(SoundType.menuClick);
                chapterState.curStageClear();
                this.inputDelay.reset();
            }
        }
    }

    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawLives(this, chapterState.getLivesRemaining());
        drawManager.drawScore(this, chapterState.getScore());
        drawManager.drawMap(this, chapterState);

        drawManager.completeDrawing(this);
    }
}
