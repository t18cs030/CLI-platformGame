
public class Pleyer {
	
	private final static int farst_X=20;
	private final static int farst_Y=10;
	private final static double jumpPower=-1.0;
	private double dy;
	private  char c;
	private int x;
	private int y;

	public Pleyer() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.c='@';
		this.dy=0.0;
		this.x=farst_X;
		this.y=farst_Y;
	}
	
	public void upPlayer() {
		y -= 1; 
	}
	public void downPlayer() {
		y += 1;
	}
	public void jumpPlayer() {
		dy = jumpPower;
		y += dy;
	}
	public void update() {
		//dy +=0.2;
		//y+=dy;
	}
	public boolean isOutOfScrean(int width,int height) {
		return x<0 || width <= x || y < 0 || height <= y;
	}
	public void paint(ConsoleView view) {
		view.put(c,(int)(x+0.5),(int)(y+0.5));
	}

}
