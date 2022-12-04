package screen;

import engine.GameSettings;
import engine.GameState;
import engine.InputManager;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameScreenTest {
    private GameSettings gameSettings;
    private GameState gameState;
    private Screen screen;
    protected InputManager inputManager;
    GameScreen gameScreen = new GameScreen(gameState, gameSettings,
            false, screen.width, screen.height, screen.fps);

    @Test
    void update() {
        while (true)
            if (inputManager.isKeyDown(KeyEvent.VK_C))
                assertEquals(3, gameState.getBulletsShot());
    }
}