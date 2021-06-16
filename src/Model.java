import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Model {

	enum GameMode{ Tital,Game,Result};
	private static final int WIDTH  = 80;
	private static final int HEIGHT = 24;
	private static final int FLOOR = 3; // 床の数
	private static final double HOOL_PROBABILITY = 0.25; // 時間経過で穴が生成される確率
	private static final double ENEMY_PROBABILITY = 0.8; // 時間経過で敵が生成される確率

	private Random random = new Random();
	private ConsoleView view;
	private ConsoleController controller;
	private Pleyer player;
	private LinkedList<Enemy> enemy;
	private LinkedList<Hool> hool;
	private LinkedList<Bullet> bullets;
	private GameMode gameMode;
	private int bulletHitCounts;
	//private ReadDj ranking;

	
	public Model() {
		this.gameMode=GameMode.Tital;
		this.view = new ConsoleView(this,WIDTH,HEIGHT,FLOOR);
		this.controller = new ConsoleController(this);
		this.player = new Pleyer();
		this.enemy = new LinkedList<Enemy>();
		this.bullets = new LinkedList<Bullet>();
		this.hool = new LinkedList<Hool>();
		this.bulletHitCounts=0;
//		try {
//			this.ranking = new ReadDj("ranking.txt");
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
	}
	
	public void prosess(String event) {
		
		switch(gameMode){
			case Tital:
				controller.stop();
				view.setTital();
				if(event.equals("s")) {
					controller.start();
					gameMode=GameMode.Game;
				}
				break;
				
			case Result:
				controller.stop();
				view.setGameOver();
				//view.setGameOver(ranking.getRanking());
				if(event.equals("r")) {
					gameMode=GameMode.Game;
					resetGame();
					controller.start();
				}
				break;
				
			case Game:
				controller.start();
				/* 時間経過処理 */
				if(event.equals("TIME_ELAPSED")) {

					// 場外にでてゲームオーバーの処理
					if(player.isOutScrean(WIDTH, HEIGHT)) { 
						gameMode = GameMode.Result;
//						try {
//							   ranking.updateRanking(bulletHitCounts);
//							} catch (IOException e) {
//						    	e.printStackTrace();
//						    } 
						return;
					}
					
					// 敵に当たったらゲームオーバーの処理
					if(player.isHit(enemy)) {
						gameMode = GameMode.Result;
//						try {
//							   ranking.updateRanking(bulletHitCounts);
//							} catch (IOException e) {
//						    	e.printStackTrace();
//						    } 
						return;
					}
					
					/*敵と弾の当たり判定処理*/
					enmeyIsHitBullet();

					/* 床のスクロール処理 */
					scrollHool();
					
					/* 自機の更新 */
					player.update(view,WIDTH,HEIGHT);
					
					/*弾のスクロール処理*/
					scrollBullets();
					
					/* 敵のスクロール処理 */
					scrollEnemy();
					
					/* 確率で穴を追加 */
					hoolAddProbability();
					
					/* 確率で敵を追加 */
					enemyAddProbability();
					
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
				
				break;				

		}
	}

	private void resetGame() {
		// TODO 自動生成されたメソッド・スタブ
		player.set();
		enemy.clear();
		bullets.clear();
		hool.clear();
		bulletHitCounts=0;
		
	}

	private void hoolAddProbability() {
		// TODO 自動生成されたメソッド・スタブ
		double n = random.nextDouble();
		if(n<HOOL_PROBABILITY) {
			int i=random.nextInt(3)+1;
			hool.add(new Hool(WIDTH,HEIGHT/FLOOR *(i)));
		}
		
	}

	private void enemyAddProbability() {
		// TODO 自動生成されたメソッド・スタブ
		double n = random.nextDouble();
		if(n<ENEMY_PROBABILITY) {
			enemy.add(makeEnemy());
		}
		
	}

	public void enmeyIsHitBullet() {
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
			}else {
				bulletHitCounts++;
			}
		}
		enemy = new_es;
		bullets = new_bs;
		
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
	
	public Enemy makeEnemy() {
		return new Enemy(WIDTH,HEIGHT);
	}
	
	public Pleyer getPlayer() {
		return player;
	}
	
	public LinkedList<Enemy> getEnemys() {
		return enemy;
	}
	
	public LinkedList<Hool> getHool() {
		// TODO 自動生成されたメソッド・スタブ
		return hool;
	}
	
	public LinkedList<Bullet> getBullets() {
		return bullets;
	}
	public int getBulletHitCounts() {
		return bulletHitCounts;
	}
	public GameMode getGameMode() {
		return gameMode;
	}
	public void setGameModeGame() {
		gameMode = GameMode.Game;
	}
	public void setGameModeResult() {
		gameMode = GameMode.Result;
	}
	public GameMode getTital() {
		return GameMode.Tital;
	}
	public GameMode getGame() {
		return GameMode.Game;
	}
	public GameMode getResult() {
		return GameMode.Result;
	}


	private void run() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		controller.run();
	}

	public static void main(String[] args) throws IOException {
		Model model = new Model();
		model.run();
	}

}