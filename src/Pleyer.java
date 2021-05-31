
public class Pleyer {
	
	private  char c;
	private int x;
	private int y;

	public Pleyer() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.c='@';
		this.x=20;
		this.y=10;
	}
	public void paint(ConsoleView view) {
		view.put(c,x,y);
	}

}
