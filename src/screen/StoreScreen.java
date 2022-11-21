package screen;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import engine.Cooldown;
import engine.Core;
import engine.PermanentState;
import sound.SoundPlay;
import sound.SoundType;

public class StoreScreen extends Screen {

    /** Milliseconds between changes in user selection. */
    private static final int SELECTION_TIME = 200;

    private static final int COST_SHAPE = 100;
    private static final int COST_COLOR = 100;
    private static final int COST_BULLET = 100;
    private static final int COST_BGM = 100;

    /** Time between changes in user selection. */
    private Cooldown selectionCooldown;
    private PermanentState permanentState = PermanentState.getInstance();

    private SoundPlay soundPlay = SoundPlay.getInstance();

    private int menuCode = 0;
    private int focusReroll = 0;

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
    public StoreScreen (final int width, final int height, final int fps) {
        super(width, height, fps);

        this.returnCode = 1;
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
                if (focusReroll == 0)
                    previousMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
                    || inputManager.isKeyDown(KeyEvent.VK_S)) {
                if (focusReroll == 0)
                    nextMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_RIGHT) // 뽑기 버튼으로 가기
                    || inputManager.isKeyDown(KeyEvent.VK_D)) {
                if (menuCode != 4) focusReroll = 1;
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_LEFT) // 메뉴 선택으로 되돌아가기
                    || inputManager.isKeyDown(KeyEvent.VK_A)) {
                if (menuCode != 4) focusReroll = 0;
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_SPACE)){
                if (menuCode == 4){
                    this.isRunning = false;
                }
                else {
                    if (focusReroll == 0)
                        focusReroll = 1;
                    else { // reroll
                        rerollItem();
                    }
                }
                soundPlay.play(SoundType.menuClick);
                this.selectionCooldown.reset();
            }
        }
    }

    /**
     * Shifts the focus to the next menu item.
     */
    private void nextMenuItem() {
        if (menuCode == 4)
            menuCode = 0;
        else
            menuCode++;
        soundPlay.play(SoundType.menuSelect);
    }

    /**
     * Shifts the focus to the previous menu item.
     */
    private void previousMenuItem() {
        if (menuCode == 0)
            menuCode = 4;
        else
            menuCode--;
        soundPlay.play(SoundType.menuSelect);
    }

    private void rerollItem() {
        if (menuCode == 0){ // ship shape
            if (permanentState.getP_state(PermanentState.P_State.coin) >= COST_SHAPE) {
                int x = new Random().nextInt(3);
                while (permanentState.getP_state(PermanentState.P_State.shipShape) == x)
                    x = new Random().nextInt(3);

                permanentState.setP_state(PermanentState.P_State.shipShape, x);
                permanentState.gainP_state(PermanentState.P_State.coin, -COST_SHAPE);
            }
        }
        else if (menuCode == 1){ // ship color
            if (permanentState.getP_state(PermanentState.P_State.coin) >= COST_COLOR) {
                int x = new Random().nextInt(3);
                while (permanentState.getP_state(PermanentState.P_State.shipColor) == x)
                    x = new Random().nextInt(3);

                permanentState.setP_state(PermanentState.P_State.shipColor, x);
                permanentState.gainP_state(PermanentState.P_State.coin, -COST_COLOR);
            }
        }
        else if (menuCode == 2){ // bullet effect
            if (permanentState.getP_state(PermanentState.P_State.coin) >= COST_BULLET) {
                int x = new Random().nextInt(3);
                while (permanentState.getP_state(PermanentState.P_State.bulletSFX) == x)
                    x = new Random().nextInt(3);

                permanentState.setP_state(PermanentState.P_State.bulletSFX, x);
                permanentState.gainP_state(PermanentState.P_State.coin, -COST_BULLET);
            }
        }
        else { // BGM
            if (permanentState.getP_state(PermanentState.P_State.coin) >= COST_BGM) {
                int x = new Random().nextInt(3);
                while (permanentState.getP_state(PermanentState.P_State.BGM) == x)
                    x = new Random().nextInt(3);
                permanentState.setP_state(PermanentState.P_State.BGM, x);
                permanentState.gainP_state(PermanentState.P_State.coin, -COST_BGM);
            }
        }
    }

    /**
     * Draws the elements associated with the screen.
     */
    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawStoreTitle(this);
        drawManager.drawStoreMenu(this, menuCode, focusReroll);
        if (menuCode < 4)
            drawManager.drawStoreGacha(this, menuCode, focusReroll);
        drawManager.drawCoin(this, permanentState.getP_state(PermanentState.P_State.coin));

        drawManager.completeDrawing(this);
    }
}
