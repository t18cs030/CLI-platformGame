/*
 * ゲーム上のMapを管理するクラス。画面右端から床をせり出す。
 * 床に接地しているときはプレイヤーの自由落下を止める。
 * 床は３種類の高さに出し、適度に穴を開ける。
 * 穴の実装は、確率で判定し、その後どの列かを確率にし、長さも確率にする。
 */

/*まずは床を三種類の高さに出現させよう。穴は、ランダムで*/
public class Map {
	private char m; // Mapの床の表示
	private int x,y;
	private int dx;// スクロール速度
	
	public Map(int width, int height) {
		this.m = '_';
		this.dx = -1;
		this.x = width-1; // 画面右端
		this.y = height-1; //　画面一番下
	}
	public void update() {
		x += dx;
	}
	public void paint(ConsoleView view) {
		view.put(m, x, y);
	}
}
