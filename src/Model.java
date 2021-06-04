import java.io.IOException;
import java.util.LinkedList;

public class Model {

	private static final int WIDTH  = 80;
	private static final int HEIGHT = 24;

	private ConsoleView view;
	private ConsoleController controller;
	private Pleyer player;
	private LinkedList<Enemy> enemy;
	private LinkedList<Map> map;
	
	public Model() {
		this.view = new ConsoleView(this,WIDTH,HEIGHT);
		this.controller = new ConsoleController(this);
		this.player = new Pleyer();
		this.enemy = new LinkedList<Enemy>();
		this.map = new LinkedList<Map>();
		player.paint(view);
	}
	
	public void prosess(String event) {
		/* 時間経過処理 */
		if(event.equals("TIME_ELAPSED")) {
			// 床がないとき自由落下する
			/* 床スクロール処理 */
			for(Map m:map)
				m.update();
			LinkedList<Map> new_map = new LinkedList<Map>();
			for(Map m:map)
				if(!m.isOutScrean(WIDTH,HEIGHT))
					new_map.add(m);
			map = new_map;
			/* 右端に床を追加 */
			map.add(makeMap());
			/* 自機の更新 */
			player.update();
			/* 敵のスクロール処理 */
			for(Enemy e:enemy)
				e.update();
			LinkedList<Enemy> new_enemy = new LinkedList<Enemy>();
			for(Enemy e:enemy)
				if(!e.isOutScrean(WIDTH,HEIGHT))
					new_enemy.add(e);
			enemy = new_enemy;

			// 場外にでてゲームオーバーの処理
			if(player.isOutOfScrean(WIDTH, HEIGHT)) { 
				System.out.println("OUT!!");
				return ;
			}
		}
		/* キー入力処理 */
		else {
			if(event.equals("w")) player.upPlayer(); // 上に移動
			else if(event.equals("s")) player.downPlayer(); // 下に移動
			else if(event.equals("j")) player.jumpPlayer(); // ジャンプ
			enemy.add(makeEnemy()); // キー入力で敵がPOP
		}
		view.update();
	}
	
	private void run() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		controller.run();
	}
	
	public Pleyer getPlayer() {
		return player;
	}
	
	public LinkedList<Enemy> getEnemys() {
		return enemy;
	}
	
	public LinkedList<Map> getMaps() {
		return map;
	}
	
	public Map makeMap() {
		return new Map(WIDTH,HEIGHT);
	}
	public Enemy makeEnemy() {
		return new Enemy(WIDTH,HEIGHT);
	}
	
	public static void main(String[] args) throws IOException {
		Model model = new Model();
		model.run();
	}

}
