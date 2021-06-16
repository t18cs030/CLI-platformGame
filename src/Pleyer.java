import java.util.LinkedList;

public class Pleyer {
	
	private static final int FARST_X = 20; // playerの自機の初期x座標 
	private static final int FARST_Y = 10; // playerの自機の初期y座標 
	private static final char FLOOR = '_'; // 床の表示キャラクタ指定
	private final static double jumpPower=-1.0;// jump力
	private final static double G = 0.2; // 重力
	private boolean isJump; // ジャンプできるかの判定
	private double dy; // 縦方向の加速度
	private  char c; // 自機のキャラクタ
	private int x; // x座標
	private int y; // y座標

	public Pleyer() {
		this.c='@';
		this.isJump=false;
		this.dy=0.0;
		this.x=FARST_X;
		this.y=FARST_Y;
	}
	
	// プレイヤーの上移動
	public void upPlayer() {
		if(isJump) {
			dy = -1.25;
			y += dy; 
			isJump=false;
		}
	}
	
	// プレイヤーの下移動
	public void downPlayer() {
		dy = 1.0;
		y += 1;
		isJump=false;
	}
	
	// プレイヤーのジャンプ
	public void jumpPlayer() {
		if(isJump) {
			dy = jumpPower;
			y += dy;
			isJump=false;
		}
	}
	
	/* 自機の更新 */
	public void update(ConsoleView view,int width,int height) {
		// 床がないときに自由落下する
		if(isOutScrean(width, height))return;
		if(!isFloor(view,height)) {
			dy +=G;
			y+=dy;
		}
		
		else {
			isJump=true;
			double n=y+dy;
			if(Math.abs(dy)<1)n=y+1;
			
			//先にdyの初期化するとSキーで跳ね上がるエラーを防げる
			dy=0.0;
			for(int i=y;i<=n&&i<height;i++) {
				if(view.getChar(x,i)==FLOOR) {
					y=i-1;
				return;
				}
			}
		}
	}
	
	/* 床に乗っているかを判定 */
	public boolean isFloor(ConsoleView view,int height) {
		double n=y+dy;
		if(Math.abs(dy)<1)n=y+1;
		for(int i=y;i<=n&&i<height;i++) {
			if(view.getChar(x, i)==FLOOR) {
				return true;
			}
		}
		return false;
	}
	
	/* 敵と当たったか判定 */
	public boolean isHit(LinkedList<Enemy> es) {
		// 自機上の敵を読み込む
		int i=0,n=0;
		for(Enemy e:es) {
			if(dy<0) {
				i=y+(int)dy;
				n=y;
			}else {
				i=y;
				n=y+(int)dy;
			}
			for(;i<=n;i++) {
				if(x == e.getX() && i==e.getY()) {
					return true;
				}	
			}
		}
		return false;
	}
	
	/* 自機が画面外に出ていたらTrueを返す */
	public boolean isOutScrean(int width,int height) {
		return x<0 || width <= x || y < 0 || height <= y;
	}
	
	/* 自機をViewに反映させる */
	public void paint(ConsoleView view) {
		view.put(c,(int)(x+0.5),(int)(y+0.5));
	}

	/* 自機を初期値に置き直す */
	public void set() {
		x=FARST_X;
		y=FARST_Y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void set(int y) {
		this.y=y;
	}
}
