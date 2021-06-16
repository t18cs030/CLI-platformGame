import java.util.LinkedList;

public class Bullet {
	private char c;
	private int x,y;
	
	public Bullet(int x,int y) {
		this.c = '*';
		this.x = x;
		this.y = y;
	}
	public boolean isHit(LinkedList<Enemy> es) {
		//LinkedList<Enemy> new_es = new LinkedList<Enemy>();
		for(Enemy e:es) {
			if ((e.getX() == x && e.getY() == y)||(x+1==e.getX()&&y==e.getY()))
				return true;
		}
		return false;
	}
	public void update() {
		x += 1;
	}
	public void paint(ConsoleView view) {
		view.put(c, x, y);
	}
	public boolean isOutScrean(int width,int height) {
		return x<0 || width <= x || y<0 || height<= y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
