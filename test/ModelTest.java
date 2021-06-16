import static org.junit.Assert.*;

import org.junit.Test;


public class ModelTest {
	private static final int WIDTH  = 80;
	private static final int HEIGHT = 24;
	Model model = new Model();
	private final Model.GameMode Tital = model.getTital();
	private final Model.GameMode Game = model.getGame();
	private final Model.GameMode Result = model.getResult();

	
	
	@Test
	public void Model定義後のGameModeはTitalである() {
		Model model = new Model();
		assertEquals(Tital,model.getGameMode());
	}
	
	@Test
	public void titalでsが押されてたらGameModeがGameに変わる() {
		assertEquals(Tital,model.getGameMode());
		model.prosess("s");
		assertEquals(Game,model.getGameMode());
	}

	@Test
	public void プレイヤーと敵が当たったらGameModeがResultに変わる() {
		model.setGameModeGame();
		Enemy e = new Enemy(WIDTH,HEIGHT);
		int x = 20,y = 10;
		e.set(x, y);
		model.getEnemys().add(e);
		assertEquals(false,model.getEnemys().isEmpty());
		
		model.getPlayer().set(y);
		assertEquals(e.getX(),model.getPlayer().getX());
		assertEquals(e.getY(),model.getPlayer().getY());
		assertEquals(true,model.getPlayer().isHit(model.getEnemys()));
		model.prosess("TIME_ELAPSED");
		assertEquals(true,model.getPlayer().isHit(model.getEnemys()));
		assertEquals(e.getX(),model.getPlayer().getX());
		assertEquals(e.getY(),model.getPlayer().getY());
		assertEquals(Result,model.getGameMode());
	}
	
	@Test
	public void 画面外にプレーヤーが出たらGameModeがResultに変わる() {
		model.setGameModeGame();
		assertEquals(Game,model.getGameMode());
		model.getPlayer().set(-1);
		assertEquals(true,model.getPlayer().isOutScrean(WIDTH, HEIGHT));
		model.prosess("TIME_ELAPSED");
		assertEquals(Result,model.getGameMode());
		
		model.setGameModeGame();
		assertEquals(Game,model.getGameMode());
		model.getPlayer().set(0);
		assertEquals(false,model.getPlayer().isOutScrean(WIDTH, HEIGHT));
		model.prosess("TIME_ELAPSED");
		assertEquals(Game,model.getGameMode());
		
		model.setGameModeGame();
		assertEquals(Game,model.getGameMode());
		model.getPlayer().set(HEIGHT);
		assertEquals(true,model.getPlayer().isOutScrean(WIDTH, HEIGHT));
		model.prosess("TIME_ELAPSED");
		assertEquals(Result,model.getGameMode());
	
		model.setGameModeGame();
		assertEquals(Game,model.getGameMode());
		model.getPlayer().set(HEIGHT-1);
		assertEquals(false,model.getPlayer().isOutScrean(WIDTH, HEIGHT));
		model.prosess("TIME_ELAPSED");
		assertEquals(Game,model.getGameMode());
	}

	@Test
	public void 敵と弾が当たったら敵と弾が消える() {
		model.setGameModeGame();
		Enemy e = new Enemy(WIDTH,HEIGHT);
		int ex=10,ey=10;
		int bx=10,by = 10;
		e.set(ex,ey);
		model.getEnemys().add(e);
		assertEquals(false,model.getEnemys().isEmpty());
		Bullet b = new Bullet(bx,by);
		model.getBullets().add(b);
		assertEquals(false,model.getBullets().isEmpty());
		assertEquals(true,e.isHit(model.getBullets()));
		assertEquals(true,b.isHit(model.getEnemys()));
		model.enmeyIsHitBullet();
		assertEquals(true,model.getEnemys().isEmpty());
		assertEquals(true,model.getBullets().isEmpty());
	
		ex=10;ey=10;
		bx=9;by=10;
		e.set(ex,ey);
		model.getEnemys().add(e);
		assertEquals(false,model.getEnemys().isEmpty());
		b = new Bullet(bx,by);
		model.getBullets().add(b);
		assertEquals(false,model.getBullets().isEmpty());
		model.enmeyIsHitBullet();
		assertEquals(true,model.getEnemys().isEmpty());
		assertEquals(true,model.getBullets().isEmpty());

	}
	@Test
	public void 敵に弾が当たったらBulletHitCountが一つ増える() {
		Model model = new Model();
		int ex=10, ey=10;
		int bx=9,by=10;
		int bHC = model.getBulletHitCounts();
		Enemy e = new Enemy(WIDTH,HEIGHT);
		e.set(ex,ey);
		model.getEnemys().add(e);
		assertEquals(false,model.getEnemys().isEmpty());
		Bullet b = new Bullet(bx,by);
		model.getBullets().add(b);
		assertEquals(false,model.getBullets().isEmpty());
		model.enmeyIsHitBullet();
		assertEquals(true,model.getEnemys().isEmpty());
		assertEquals(true,model.getBullets().isEmpty());
		assertEquals(bHC+1,model.getBulletHitCounts());
	}
	
	
	@Test
	public void resultでrが押されてたらGameModeがGameに変わる() {
		Model model = new Model();
		model.setGameModeResult();
		assertEquals(Result,model.getGameMode());
		model.prosess("r");
		assertEquals(Game,model.getGameMode());
	}


	
	

}
