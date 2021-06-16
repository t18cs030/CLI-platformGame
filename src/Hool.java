
public class Hool {
	private String s; // 穴のキャラクタ
	private int x,y;  // 穴の先頭のx座標とy座標
	
	public Hool (int x, int y) {
		this.x=x-1;
		this.y=y-1;
		this.s = "       ";
	}
	/* 穴の位置を更新 */
	public void update() {
		x-=1;
	}
	/* 穴を画面に反映 */
	public void paint(ConsoleView view) {
		view.drawString(s, x, y);
	}
	/* 完全に画面外に出たら削除 */
	public boolean isOutScrean(int width,int height) {
		return x+s.length()<0 ||width <= x || y<0 || height <= y;
	}
	
}
