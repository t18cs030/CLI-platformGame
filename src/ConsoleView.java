import java.util.ArrayList;


public class ConsoleView {
	
	private char screan[][];
	private Model model;
	private int width;
	private int height;
	private int floor; // 床の枚数を保存

	public ConsoleView(Model model, int width, int height,int floor) {
		this.model = model;
		this.width = width;
		this.height = height;
		this.screan = new char[width][height];
		this.floor = floor;
		clear();
	}
	
	/* 画面をクリアする */
	public void clear() {
		for(int y=0; y<height;y++) {
			for(int x=0;x<width;x++) {
				screan[x][y] = ' ';
			}
		}
	}
	
	/* x,y に文字を代入する */
	public void put(char c,int x,int y) {
		if( x<0 || x>=width || y<0 || y>=height) return ;
		screan[x][y]=c;
	}
	
	/* x,y から文章を画面に表示する */
	public void drawString(String s,int x,int y) {
		for(int i=0;i<s.length();i++)
			put(s.charAt(i),x+i,y);
	}
	
	/* (x,y)のキャラクタを返す */
	public char getChar(int x, int y) {
		return screan[x][y];
	}
	
	/* 画面に床を置く */
	public void putMap() {
		int i=0;
		for(int y=0;y<height;y++) {
			if(y==height/floor *(i+1)-1) {
				for(int x=0;x<width;x++)
					put('_',x,y);
				i++;
			}
		}
	}
	
	/* 敵と弾の当たった回数を表示 */
	public void showBulletHitCounts(int bulletHitCounts) {
		// TODO 自動生成されたメソッド・スタブ
		drawString("hit : ",60,0);
		drawString(String.valueOf(bulletHitCounts),68,0);
	}
	
	/* ランキングの表示 */
	private void drawRanking(ArrayList<Integer> rank) {
		// TODO 自動生成されたメソッド・スタブ
		drawString("high score:",30,12);
		for(int i=0;i<rank.size();i++) {
			drawString(String.valueOf(rank.get(i)),45,12+i);
		}
		
	}

	/* タイトル画面の表示 */
	public void setTital() {
		clear();
		drawString("D run",30,5);
		drawString("start: s",20,12);
		drawString("-操作方法-",38,10);
		drawString("up: w",40,11);
		drawString("down: s",40,12);
		drawString("jump: j",40,13);
		drawString("bullet: b",40,14);
		paint();
		
	}
	
	/* ゲーム画面の表示 */
	public void update() {
		clear();
		putMap();
		for(Hool h:model.getHool())
			h.paint(this);
		for(Enemy e:model.getEnemys())
			e.paint(this);
		for(Bullet b:model.getBullets())
			b.paint(this);
		model.getPlayer().paint(this);
		showBulletHitCounts(model.getBulletHitCounts());
		paint();
	}
	
	/* リザルト画面の表示 */
	public void setGameOver(ArrayList<Integer> rank) {
		clear();
		drawString("Game Over",32,5);
		drawString("youre score:", 30,10);
		drawString(String.valueOf(model.getBulletHitCounts()),45,10);
		drawString("Restart: r",32,19);
		drawString("Finish: Ctrl-C",32,20);
		drawRanking(rank);
		paint();
	}
	
	/* コンソール画面に表示 */
	public void paint() {
		for(int y=0; y< height; y++) {
			for(int x=0; x<width ; x++) {
				System.out.print(screan[x][y]);
			}
			System.out.println(y);
		}
	}
	

	
	// デバック用
	public ConsoleView() {
		this.width=80;
		this.height=24;
		this.screan=new char[width][height];
	}
	
	public static void main(String[] args) throws InterruptedException {
		ConsoleView view = new ConsoleView();
		for(int x=0;x<10;x++) {
			view.clear();
			view.put('*',x,5);
			view.drawString("   ", x, 10);
			view.paint();
			Thread.sleep(100);
		}
	}



	
}
