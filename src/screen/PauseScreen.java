package screen;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


import engine.*;

public class PauseScreen extends Screen {
    /** Milliseconds between changes in user selection. */
    private static final int SELECTION_TIME = 30;
    /** Maximum number of high scores. */
    private static final int MAX_HIGH_SCORE_NUM = 7;

    /** Current score. */
    private int score;
    /** Player lives left. */
    private int livesRemaining;
    /** Total bullets shot by the player. */
    private int bulletsShot;
    /** Total ships destroyed by the player. */
    private int shipsDestroyed;
    /** List of past high scores. */
    private List<Score> highScores;
    /** Checks if current score is a new high score. */
    private boolean isNewRecord;
    /** Time between changes in user selection. */
    private Cooldown selectionCooldown;


    private static int coin = PermanentState.getInstance().getCoin();

    /**
     * Constructor, establishes the properties of the screen.
     *
     * @param width
     *            Screen width.
     * @param height
     *            Screen height.
     * @param fps
     *            Frames per second, frame rate at which the game is run.
     * @param gameState
     *            Current game state.
     */
    public PauseScreen(final int width, final int height, final int fps,
                       final GameState gameState) {

        super(width, height, fps);

        this.score = gameState.getScore();
        this.livesRemaining = gameState.getLivesRemaining();
        this.bulletsShot = gameState.getBulletsShot();
        this.shipsDestroyed = gameState.getShipsDestroyed();
        this.isNewRecord = false;

        if (this.score > 500)
            this.coin = this.score - 500;
        else
            this.coin = 0;
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();

        try {
            this.highScores = Core.getFileManager().loadHighScores();
            if (highScores.size() < MAX_HIGH_SCORE_NUM
                    || highScores.get(highScores.size() - 1).getScore()
                    < this.score)
                this.isNewRecord = true;

        } catch (IOException e) {
            logger.warning("Couldn't load high scores!");
        }
    }

    /**
     * Constructor, establishes the properties of the screen.
     *
     * @param width  Screen width.
     * @param height Screen height.
     * @param fps    Frames per second, frame rate at which the game is run.
     */

    /**
     * @param width
     * @param height
     * @param fps
     * @param gameState
     */

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
        if (this.inputDelay.checkFinished()) {
            if (inputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
                // Return to main menu.
                this.returnCode = 1;
                this.isRunning = false;
            } else if (inputManager.isKeyDown(KeyEvent.VK_SPACE)) {
                // Play again.
                this.returnCode = 2;
                this.isRunning = false;
            }
        }

    }

    /**
     * Saves the score as a high score.
     */
    private void saveScore() {
        Collections.sort(highScores);
        if (highScores.size() > MAX_HIGH_SCORE_NUM)
            highScores.remove(highScores.size() - 1);

        try {
            Core.getFileManager().saveHighScores(highScores);
        } catch (IOException e) {
            logger.warning("Couldn't load high scores!");
        }
    }

    /**
     * Draws the elements associated with the screen.
     */
    private void draw() {
        drawManager.initDrawing(this);

        drawManager.PauseGame(this, this.inputDelay.checkFinished(),
                this.isNewRecord);
        drawManager.drawPause(this, this.score, this.livesRemaining,
                this.shipsDestroyed, this.coin, this.isNewRecord);

        drawManager.completeDrawing(this);
    }
}