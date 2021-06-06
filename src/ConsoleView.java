import java.util.LinkedList;

public class ConsoleView {
	
	private char screan[][];
	private Model model;
	private int width;
	private int height;

	public ConsoleView(Model model, int width, int height) {
		this.model = model;
		this.width = width;
		this.height = height;
		this.screan = new char[width][height];
		clear();
	}
	
	public void clear() {
		for(int y=0; y<height;y++) {
			for(int x=0;x<width;x++) {
				screan[x][y] = '-';
			}
		}
	}
	
	public void put(char c,int x,int y) {
		if( x<0 || x>=width || y<0 || y>=height) return ;
		screan[x][y]=c;
	}
	
	public void paint() {
		for(int y=0; y< height; y++) {
			for(int x=0; x<width ; x++) {
				System.out.print(screan[x][y]);
			}
			System.out.println(y);
		}
	}

	public void update() {
		clear();
		for(LinkedList<Map> ms:model.getMaps())
			for(Map m:ms)
				m.paint(this);
		for(Enemy e:model.getEnemys())
			e.paint(this);
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
			view.paint();
			Thread.sleep(100);
		}
	}

	public char getChar(int x, int y) {
		// TODO 自動生成されたメソッド・スタブ
		return screan[x][y];
	}
	
}
