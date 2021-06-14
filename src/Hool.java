
public class Hool {
	//private final static char HOOL = ' ';
	private String s;
	private int x,y;
	
	public Hool (int x, int y) {
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
		return x+s.length()<0 ||width <= x || y<0 || height <= y;
	}
	
}
