import java.util.LinkedList;
import java.util.Random;

/* 
 * 敵オブジェクト。画面右端から出現し、時間経過で左に流れていく。
 * キャラクタは’?’で表現する
 * 出現高さはランダム。
 * 左端の画面外にでたら消去
 */
public class Enemy {
	
	private Random random = new Random();
	private char s;// 敵の表示
	private int x,y; // 敵のx座標とy座標
	
	public Enemy(int width, int height) {
		this.s = '?';      // 敵のキャラクタ
		this.x = width -1 ; //　画面右端
		this.y = random.nextInt(height); // 高さはランダム
	}
	/* 敵の更新 */
	public void update() {
		x -=1;
	}
	/* 敵を画面に反映 */
	public void paint(ConsoleView view) {
		view.put(s, x, y);
	}
	/* 敵が画面外に出たら削除 */
	public boolean isOutScrean(int width, int height) {
		return x<0 || width <= x || y<0 || height <= y;
	}
	/* 弾に当たったら敵を削除 */
	public boolean isHit(LinkedList<Bullet> bullets) {
		for(Bullet b:bullets)
			if((x==b.getX()&&y==b.getY())||(x-1==b.getX()&&y==b.getY()))
				return true;
		return false;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void set(int x,int y) {
		this.x=x;
		this.y=y;
	}
}
