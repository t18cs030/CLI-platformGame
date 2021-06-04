import java.util.Random;

/* 
 * 敵オブジェクト。画面右端から出現し、時間経過で左に流れていく。
 * キャラクタを’＠’以外で表現する
 * 移動操作はいらない。落下処理もいらない。
 * 出現高さはランダム。
 * 左端の画面外にでたら消去
 * 画面内の出現数の上限あり。
 * 
 */
public class Enemy {
	
	private Random random = new Random();
	private char s;// 敵の表示
	private int x,y;
	
	public Enemy(int width, int height) {
		super();
		this.s = '?';
		//x = 15;
		this.x = width -1 ; //　画面右端
		this.y = random.nextInt(height) -1; // 高さはランダム
	}
	public void update() {
		x -=1;
	}
	public void paint(ConsoleView view) {
		view.put(s, x, y);
	}
	
}
