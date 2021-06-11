import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Model {

	private static final int WIDTH  = 80;
	private static final int HEIGHT = 24;
	private static final int FLOOR = 3; // 床の数

	private Random random = new Random();
	private ConsoleView view;
	private ConsoleController controller;
	private Pleyer player;
	private LinkedList<Enemy> enemy;
	private LinkedList<LinkedList<Map>> maps;
	private LinkedList<Hool> hool;
	private LinkedList<Bullet> bullets;

	
	public Model() {
		this.view = new ConsoleView(this,WIDTH,HEIGHT,FLOOR);
		this.controller = new ConsoleController(this);
		this.player = new Pleyer();
		this.enemy = new LinkedList<Enemy>();
		this.maps = new LinkedList<LinkedList<Map>>();
		this.bullets = new LinkedList<Bullet>();
		this.hool = new LinkedList<Hool>();
		for(int i=0;i<FLOOR;i++)
			maps.add(new LinkedList<Map>());
		player.paint(view);
	}
	
	public void prosess(String event) {
		/* 時間経過処理 */
		if(event.equals("TIME_ELAPSED")) {
			/* 床のスクロール処理 */
			//scrollMap();
			
			/**/
			scrollHool();
			
			/* 自機の更新 */
			player.update(view,WIDTH,HEIGHT);
			
			/*弾のスクロール処理*/
			scrollBullets();
			
			/*敵と弾の当たり判定処理*/
			enmeyIsHitBullet();
			
			/* 敵のスクロール処理 */
			scrollEnemy();

			// 場外にでてゲームオーバーの処理
			if(player.isOutScrean(WIDTH, HEIGHT)) { 
				System.out.println("OUT!!");
				controller.stop();
				return ;
			}
			
			// 敵に当たったらゲームオーバーの処理
			if(player.isHit(enemy)) {
				System.out.println("HIT!!");
				controller.stop();
				return ;
			}
		}
		/* キー入力処理 */
		else {
			if(event.equals("w")) player.upPlayer(); // 上に移動
			else if(event.equals("s")) player.downPlayer(); // 下に移動
			else if(event.equals("j")) player.jumpPlayer(); // ジャンプ
			else if(event.equals("b")) {
				bullets.add(new Bullet(player.getX(),player.getY()));
			}
			enemy.add(makeEnemy()); // キー入力で敵がPOP
		}
		view.update();
	}
	


	private void enmeyIsHitBullet() {
		if(bullets.isEmpty())return ;
		if(enemy.isEmpty())return ;
		LinkedList<Enemy> new_es = new LinkedList<Enemy>();
		LinkedList<Bullet> new_bs = new LinkedList<Bullet>();
		for(Enemy e:enemy) {
			if(!e.isHit(bullets))
				new_es.add(e);
		}
		for(Bullet b:bullets) {
			if(!b.isHit(enemy)) {
				new_bs.add(b);
			}
		}
		enemy = new_es;
		bullets = new_bs;
		
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
	
	public LinkedList<Hool> getHool() {
		// TODO 自動生成されたメソッド・スタブ
		return hool;
	}
	
	public LinkedList<Bullet> getBullets() {
		return bullets;
	}
	public void scrollMap() {
		// LinkedList<Map> すべての高さのMapを左へ一つスクロール
		for(LinkedList<Map> ms:maps) {
			for(Map m:ms)	
				m.update();
			LinkedList<Map> new_map = new LinkedList<Map>();
			for(Map m:ms)
				if(!m.isOutScrean(WIDTH,HEIGHT))
					new_map.add(m);
			ms = new_map;
		}
		/* すべての高さのMapの右端に床を追加 */
		for(int i=0;i<maps.size();i++) {
			maps.get(i).add(makeMap(WIDTH,HEIGHT/FLOOR * (i+1)));// FLOER数で分割する
		}
	}

	private void scrollHool() {
		for(Hool h:hool) {
			h.update();
		}
		LinkedList<Hool> new_hs = new LinkedList<Hool>();
		for(Hool h:hool) {
			if(!h.isOutScrean(WIDTH, HEIGHT))
				new_hs.add(h);
		}
		hool = new_hs;
		
		double n=random.nextDouble();
		if(n<0.25) {
			int i=random.nextInt(3)+1;
			hool.add(new Hool(3,WIDTH,HEIGHT/FLOOR *(i)));
			
		}
		
	}


	private void scrollBullets() {
		for(Bullet b:bullets) {
			b.update();
		}
		LinkedList<Bullet> new_bs = new LinkedList<Bullet>();
		for(Bullet b:bullets)
			if(!b.isOutScrean(WIDTH, HEIGHT))
				new_bs.add(b);
		bullets = new_bs;
	}
	
	public void scrollEnemy() {
		for(Enemy e:enemy)
			e.update();
		LinkedList<Enemy> new_enemy = new LinkedList<Enemy>();
		for(Enemy e:enemy)
			if(!e.isOutScrean(WIDTH,HEIGHT))
				new_enemy.add(e);
		enemy = new_enemy;
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
