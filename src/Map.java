/*
 * ゲーム上のMapを管理するクラス。画面右端から床をせり出す。
 * 床に接地しているときはプレイヤーの自由落下を止める。
 * 床は３種類の高さに出し、適度に穴を開ける。
 * 穴の実装は、確率で判定し、その後どの列かを確率にし、長さも確率にする。
 */

/*まずは床を三種類の高さに出現させよう。穴は、ランダムで*/
public class Map {
	
	private final static char FLOOR = '_'; // 床を表す文字
	private final static int dx = -1;// スクロール速度
	private char m; // Mapの床の表示
	private int x,y;// 出現位置と出現高さ
	
	public Map(int width, int height) {
		this.m = FLOOR;
		this.x = width-1; // 画面右端
		this.y = height-1; //　画面一番下
	}
	public void update() {
		x += dx;
	}
	public void paint(ConsoleView view) {
		view.put(m, x, y);
	}
	public boolean isOutScrean(int width, int height) {
		// TODO 自動生成されたメソッド・スタブ
		return x<0 || width <= x || y<0 || height <= y;
	}
}
