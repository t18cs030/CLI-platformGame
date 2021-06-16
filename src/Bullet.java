import java.util.LinkedList;

/* 
 * 弾オブジェクト。プレイヤーから出現し、時間経過で右に流れていく
 * キャラクタは’*’で表現する
 * 出現高さはプレイヤーの高さ
 * 右端の画面外にでるか敵に当たったら消去
 */
public class Bullet {
	private char c; // 弾の表示
	private int x,y; // 弾のx座標とy座標
	
	public Bullet(int x,int y) {
		this.c = '*';
		this.x = x;
		this.y = y;
	}
	/* 弾が敵に当たったらその弾を削除 */
	public boolean isHit(LinkedList<Enemy> es) {;
		for(Enemy e:es) {
			if ((e.getX() == x && e.getY() == y)||(x+1==e.getX()&&y==e.getY()))
				return true;
		}
		return false;
	}
	/* 弾の更新 */
	public void update() {
		x += 1;
	}
	/* 弾を画面に反映*/
	public void paint(ConsoleView view) {
		view.put(c, x, y);
	}
	/* 弾が画面外出たら削除 */
	public boolean isOutScrean(int width,int height) {
		return x<0 || width <= x || y<0 || height<= y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
