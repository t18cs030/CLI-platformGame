
public class Hool {
	//private final static char HOOL = ' ';
	private String s;
	private int n;
	private int x,y;
	
	public Hool (int n, int x, int y) {
		this.n=n;
		this.x=x-1;
		this.y=y-1;
		this.s = "       ";
	}
	public void update() {
		x-=1;
	}
	public void paint(ConsoleView view) {
		view.drawString(s, x, y);
	}
	public boolean isOutScrean(int width,int height) {
		return x+n<0 ||width <= x || y<0 || height <= y;
	}
	
}
