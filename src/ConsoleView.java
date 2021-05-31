
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
			System.out.println();
		}
	}

	public void update() {
		clear();
		paint();
	}
	
}
