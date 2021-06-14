
public class ConsoleView {
	
	private char screan[][];
	private Model model;
	private int width;
	private int height;
	private int floor;

	public ConsoleView(Model model, int width, int height,int floor) {
		this.model = model;
		this.width = width;
		this.height = height;
		this.screan = new char[width][height];
		this.floor = floor;
		clear();
	}
	
	public void clear() {
		for(int y=0; y<height;y++) {
			for(int x=0;x<width;x++) {
				screan[x][y] = ' ';
			}
		}
	}
	
	public void put(char c,int x,int y) {
		if( x<0 || x>=width || y<0 || y>=height) return ;
		screan[x][y]=c;
	}
	
	public void drawString(String s,int x,int y) {
		for(int i=0;i<s.length();i++)
			put(s.charAt(i),x+i,y);
	}
	
	public void paint() {
		for(int y=0; y< height; y++) {
			for(int x=0; x<width ; x++) {
				System.out.print(screan[x][y]);
			}
			System.out.println(y);
		}
	}
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

	public void setTital() {
		clear();
		drawString("D run",10,10);
		drawString("start: sキー",20,15);
		drawString("up: w",40,10);
		drawString("down: s",40,11);
		drawString("bullet: b",40,12);
		drawString("jump: p",40,13);
		paint();
		
	}

	public void setGameOver() {
		clear();
		drawString("Game Over",10,10);
		drawString("Restart: r",20,15);
		drawString("Finish: Ctrl-C",20,18);
		paint();
	}
	
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
		paint();
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

	public char getChar(int x, int y) {
		// TODO 自動生成されたメソッド・スタブ
		return screan[x][y];
	}

	
}
