import java.io.IOException;
import java.util.LinkedList;

public class Model {

	private static final int WIDTH  = 80;
	private static final int HEIGHT = 24;
	private static final int FLOOR = 3; // 床の数


	private ConsoleView view;
	private ConsoleController controller;
	private Pleyer player;
	private LinkedList<Enemy> enemy;
	private LinkedList<LinkedList<Map>> maps;

	
	public Model() {
		this.view = new ConsoleView(this,WIDTH,HEIGHT);
		this.controller = new ConsoleController(this);
		this.player = new Pleyer();
		this.enemy = new LinkedList<Enemy>();
		this.maps = new LinkedList<LinkedList<Map>>();
		for(int i=0;i<FLOOR;i++)
			maps.add(new LinkedList<Map>());
		player.paint(view);
	}
	
	public void prosess(String event) {
		/* 時間経過処理 */
		if(event.equals("TIME_ELAPSED")) {
			/* 床のスクロール処理 */
			scrollMap(maps);
			
			/* 自機の更新 */
			player.update(view,WIDTH,HEIGHT);
			
			/* 敵のスクロール処理 */
			scrollEnemy(enemy);

			// 場外にでてゲームオーバーの処理
			if(player.isOutScrean(WIDTH, HEIGHT)) { 
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
	
	public LinkedList<LinkedList<Map>> getMaps() {
		return maps;
	}
	
	public void scrollMap(LinkedList<LinkedList<Map>> obj) {
		// LinkedList<Map> すべての高さのMapを左へ一つスクロール
		for(LinkedList<Map> ms:obj) {
			for(Map m:ms)	
				m.update();
			LinkedList<Map> new_map = new LinkedList<Map>();
			for(Map m:ms)
				if(!m.isOutScrean(WIDTH,HEIGHT))
					new_map.add(m);
			ms = new_map;
		}
		/* すべての高さのMapの右端に床を追加 */
		for(int i=0;i<obj.size();i++)
			obj.get(i).add(makeMap(WIDTH,HEIGHT/FLOOR * (i+1)));// FLOER数で分割する
	}
	
	public void scrollEnemy(LinkedList<Enemy> obj) {
		// LinkedList<Map> 
		for(Enemy e:obj)
			e.update();
		LinkedList<Enemy> new_enemy = new LinkedList<Enemy>();
		for(Enemy e:obj)
			if(!e.isOutScrean(WIDTH,HEIGHT))
				new_enemy.add(e);
		obj = new_enemy;
	}
	
	public Map makeMap(int width,int height) {
		return new Map(width,height);
	}
	public Enemy makeEnemy() {
		return new Enemy(WIDTH,HEIGHT);
	}
	
	public static void main(String[] args) throws IOException {
		Model model = new Model();
		model.run();
	}

}
