package entityTest;

import engine.GameSettings;
import entity.EnemyShipFormation;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class EnemyShipFormationTest {
    private GameSettings gameSettings;
    EnemyShipFormation data = new EnemyShipFormation(this.gameSettings);

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void random_speed() {
        int[] nums = new int[2];

        for (int i = 0; i < 2; i++)
            nums[i] = data.random_speed();

        assertSame(nums[0], nums[1], "두 수가 서로 랜덤하여 다른지 확인.");
    }
}