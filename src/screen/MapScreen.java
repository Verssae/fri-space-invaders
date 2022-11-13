package screen;


import engine.Cooldown;
import engine.Core;
import engine.GameSettings;
import engine.GameState;
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
    private static final int Map_Size = 4; // N * N

    private static final Logger logger = Core.getLogger();

    public enum Stage_Type {
        NONE,
        ENEMY,
        STORE,
        REPAIR,
        CLEAR,
        BOSS
    }
    private Cooldown selectionCooldown;

    /**
     * Constructor, establishes the properties of the screen.
     *
     * @param width  Screen width.
     * @param height Screen height.
     * @param fps    Frames per second, frame rate at which the game is run.
     */
    private GameSettings gameSettings;

    private int score;
    private int bulletsShot;
    private int shipsDestroyed;

    public static int lives;
    private int level;
    private Ship ship;
    private int map_type[][] = new int[Map_Size][Map_Size];
    private int is_adj[][] = new int[Map_Size][Map_Size];
    private int map_moveable[][][] = new int[Map_Size][Map_Size][4];
    private int dx[] = {1,-1,0,0};
    private int dy[] = {0,0,1,-1};
    private int cur_x = 0; // <= initialization to start x
    private int cur_y = 0; // <= initialization to start y

    public MapScreen(final int width, final int height, final int fps) {
        super(width, height, fps);

        this.returnCode = 1;
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
    }

    public final void initialize() {
        super.initialize();

        Random rd = new Random();
        do {
            for (int i = 0; i < Map_Size; i++){
                for (int j = 0; j < Map_Size; j++){
                    map_type[i][j] = rd.nextInt(Stage_Type.values().length - 2); // Exclude type { CLEAR, BOSS }
                }
            }
            for (int i = 0; i < Map_Size; i++){
                for (int j = 0; j < Map_Size; j++){
                    for (int k = 0; k < 4; k++){
                        int cx = j + dx[k], cy = i + dy[k];
                        if(cx < 0 || cx >= Map_Size || cy < 0 || cy >= Map_Size || map_type[i][j] == 0 || map_type[cy][cx] == 0)
                            map_moveable[i][j][k] = 0;
                        else
                            map_moveable[i][j][k] = 1;
                    }
                }
            }
        } while(bfs(cur_x, cur_y) == 1);
        is_adj[cur_y][cur_x] = 1;
        logger.info("map initialized");
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
                if (map_moveable[cur_y][cur_x][3] == 1 && is_adj[cur_y - 1][cur_x] == 1)
                    cur_y -= 1;
                this.inputDelay.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
                    || inputManager.isKeyDown(KeyEvent.VK_S)) {
                sound.SoundPlay.getInstance().play(SoundType.menuSelect);
                if (map_moveable[cur_y][cur_x][2] == 1 && is_adj[cur_y + 1][cur_x] == 1)
                    cur_y += 1;
                this.inputDelay.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_RIGHT) // 뽑기 버튼으로 가기
                    || inputManager.isKeyDown(KeyEvent.VK_D)) {
                sound.SoundPlay.getInstance().play(SoundType.menuSelect);
                if (map_moveable[cur_y][cur_x][0] == 1 && is_adj[cur_y][cur_x + 1] == 1)
                    cur_x += 1;
                this.inputDelay.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_LEFT) // 메뉴 선택으로 되돌아가기
                    || inputManager.isKeyDown(KeyEvent.VK_A)) {
                sound.SoundPlay.getInstance().play(SoundType.menuSelect);
                if (map_moveable[cur_y][cur_x][1] == 1 && is_adj[cur_y][cur_x - 1] == 1)
                    cur_x -= 1;
                this.inputDelay.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_SPACE)){
                sound.SoundPlay.getInstance().play(SoundType.menuClick);
                //if (map_type[cur_y][cur_x] == Stage_Type.BOSS.ordinal())
                    //isRunning = false;
                for (int i = 0; i < 4; i++)
                    if (map_moveable[cur_y][cur_x][i] == 1)
                        is_adj[cur_y + dy[i]][cur_x + dx[i]] = 1;
                map_type[cur_y][cur_x] = Stage_Type.CLEAR.ordinal();
                this.inputDelay.reset();
            }
        }
    }

    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawMap(this, map_type, is_adj, map_moveable, cur_x, cur_y);

        drawManager.completeDrawing(this);
    }

    private int bfs(int x, int y){
        class Pair{
            Integer x;
            Integer y;

            public Pair(Integer x, Integer y) {
                this.x = x;
                this.y = y;
            }
        }
        Queue<Pair> q = new LinkedList<>();
        int visit[][] = new int[Map_Size][Map_Size];
        if (map_type[y][x] > 0) {
            q.add(new Pair(x, y));
            visit[y][x] = 1;
        }
        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i < 4; i++){
                if (map_moveable[p.y][p.x][i] == 1 && visit[p.y + dy[i]][p.x + dx[i]] == 0){
                    q.add(new Pair(p.x + dx[i], p.y + dy[i]));
                    visit[p.y + dy[i]][p.x + dx[i]] = 1;
                }
            }
        }
        for (int i = 0; i < Map_Size; i++)
            for (int j = 0; j < Map_Size; j++)
                if (visit[i][j] == 0 && map_type[i][j] > 0)
                    return 1;
        return 0;
    }
}
