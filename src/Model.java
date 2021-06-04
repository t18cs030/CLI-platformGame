import java.io.IOException;

public class Model {

	private static final int WIDTH  = 80;
	private static final int HEIGHT = 24;

	private ConsoleView view;
	private ConsoleController controller;
	private Pleyer player;
	private Enemy enemy;
	private Map map;
	
	public Model() {
		this.view = new ConsoleView(this,WIDTH,HEIGHT);
		this.controller = new ConsoleController(this);
		this.player = new Pleyer();
		this.enemy = new Enemy(WIDTH,HEIGHT);
		this.map = new Map(WIDTH,HEIGHT);
		player.paint(view);
		enemy.paint(view);
		map.paint(view);
	}
	
	public void prosess(String event) {
		/* 時間経過処理 */
		if(event.equals("TIME_ELAPSED")) {
			// 床がないとき自由落下する
			player.update(); 
			enemy.update();
			map.update();
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
	
	public Enemy getEnemy() {
		return enemy;
	}
	
	public Map getMap() {
		return map;
	}
	
	public static void main(String[] args) throws IOException {
		Model model = new Model();
		model.run();
	}

}
