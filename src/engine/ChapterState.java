package engine;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Implements an object that stores the state of the game between levels.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class ChapterState {
	/** Current map size.*/
	private int map_size;
	/** Current chapter. */
	private int chapter;
	/** Current score. */
	private int score;
	/** Current coin. */
	private int coin;
	/** Lives currently remaining. */
	private int livesRemaining;
	/** Bullets shot until now. */
	private int bulletsShot;
	/** Ships destroyed until now. */
	private int shipsDestroyed;

	public enum Stage_Type {
		NONE,
		ENEMY,
		STORE,
		REPAIR,
		CLEAR,
		BOSS
	}

	public int map_type[][];
	public int is_adj[][];
	public int map_moveable[][][];
	private int dx[] = {1,-1,0,0};
	private int dy[] = {0,0,1,-1};
	private int cur_x = 0; // <= initialization to start x
	private int cur_y = 0; // <= initialization to start y

	private static final Logger logger = Core.getLogger();


	/**
	 * Constructor.
	 *
	 * @param chapter
	 *            Current chapter.
	 * @param score
	 *            Current score.
	 * @param livesRemaining
	 *            Lives currently remaining.
	 * @param bulletsShot
	 *            Bullets shot until now.
	 * @param shipsDestroyed
	 *            Ships destroyed until now.
	 */
	public ChapterState(final int map_size,
						final int chapter,
						final int score,
						final int coin,
                        final int livesRemaining,
						final int bulletsShot,
                        final int shipsDestroyed) {
		this.map_size = map_size;
		this.chapter = chapter;
		this.score = score;
		this.coin = coin;
		this.livesRemaining = livesRemaining;
		this.bulletsShot = bulletsShot;
		this.shipsDestroyed = shipsDestroyed;

		initialize_map(); // 맵 생성 후 저장
	}

	private void initialize_map(){
		map_type = new int[map_size][map_size];
		is_adj = new int[map_size][map_size];
		map_moveable = new int[map_size][map_size][4];

		Random rd = new Random();
		do {
			for (int i = 0; i < map_size; i++){
				for (int j = 0; j < map_size; j++){
					map_type[i][j] = rd.nextInt(Stage_Type.values().length - 2); // Exclude type { CLEAR, BOSS }
				}
			}
			for (int i = 0; i < map_size; i++){
				for (int j = 0; j < map_size; j++){
					for (int k = 0; k < 4; k++){
						int cx = j + dx[k], cy = i + dy[k];
						if(cx < 0 || cx >= map_size || cy < 0 || cy >= map_size || map_type[i][j] == 0 || map_type[cy][cx] == 0)
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
		int visit[][] = new int[map_size][map_size];
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
		for (int i = 0; i < map_size; i++)
			for (int j = 0; j < map_size; j++)
				if (visit[i][j] == 0 && map_type[i][j] > 0)
					return 1;
		return 0;
	}

	/**
	 * @return the level
	 */
	public final int getChapter() {
		return chapter;
	}

	/**
	 * @return the score
	 */
	public final int getScore() {
		return score;
	}

	/**
	 * @return the coin
	 */
	public final int getCoin() {
		return coin;
	}

	/**
	 * @return the livesRemaining
	 */
	public final int getLivesRemaining() {
		return livesRemaining;
	}

	/**
	 * @return the bulletsShot
	 */
	public final int getBulletsShot() {
		return bulletsShot;
	}

	/**
	 * @return the shipsDestroyed
	 */
	public final int getShipsDestroyed() {
		return shipsDestroyed;
	}

	public void curStageClear(){
		for (int i = 0; i < 4; i++)
			if (map_moveable[cur_y][cur_x][i] == 1)
				is_adj[cur_y + dy[i]][cur_x + dx[i]] = 1;
		map_type[cur_y][cur_x] = Stage_Type.CLEAR.ordinal();
	}

	public void curPosMove(int dir){
		if (map_moveable[cur_y][cur_x][dir] == 1 && is_adj[cur_y+dy[dir]][cur_x+dx[dir]] == 1){
			cur_x += dx[dir];
			cur_y += dy[dir];
		}
	}

	public boolean isCur(int y, int x){
		return x == cur_x && y == cur_y;
	}
}
