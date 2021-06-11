import java.util.LinkedList;

public class Pleyer {
	
	private static final int FARST_X = 20; // playerの自機の初期x座標 
	private static final int FARST_Y = 10; // playerの自機の初期y座標 
	private static final char FLOOR = '_';
	private final static double jumpPower=-1.0;
	private final static double G = 0.2;
	private double dy;
	private  char c;
	private int x;
	private int y;

	public Pleyer() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.c='@';
		this.dy=0.0;
		this.x=FARST_X;
		this.y=FARST_Y;
	}
	
	public void upPlayer() {
		dy = -1.25;
		y += dy; 
	}
	
	public void downPlayer() {
		dy = 1.0;
		y += 1;
	}
	
	public void jumpPlayer() {
		dy = jumpPower;
		y += dy;
	}
	
	public void update(ConsoleView view,int width,int height) {
		// 床がないときに自由落下する
		if(isOutScrean(width, height))return;
		if(!isFloor(view,height)) {
			dy +=G;
			y+=dy;
		}
		
		else {
			double n=y+dy;
			if(Math.abs(dy)<1)n=y+1;
			
			//先にdyの初期化するとSキーで跳ね上がるエラーを防げる
			dy=0.0;
			for(int i=y;i<=n&&i<height;i++) {
				if(view.getChar(x,y)==FLOOR) {
					y=i+1;
				return;
				}
			}
		}
	}
	
	
	
	public boolean isFloor(ConsoleView view,int height) {
		double n=y+dy;
		if(Math.abs(dy)<1)n=y+1;
		for(int i=y;i<=n&&i<height;i++) {
			if(view.getChar(x, i)==FLOOR) {
				System.out.println("!!!!!!FLOOR!!!!!");
				return true;
			}
		}
		return false;
	}
	
	public boolean isHit(LinkedList<Enemy> es) {
		// 自機上の文字を読み込む
		for(Enemy e:es) {
			if(x == e.getX() && y==e.getY()) {
					return true;
				}
			}
		return false;
	}
	public boolean isOutScrean(int width,int height) {
		return x<0 || width <= x || y < 0 || height <= y;
	}
	public void paint(ConsoleView view) {
		view.put(c,(int)(x+0.5),(int)(y+0.5));
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
