import java.io.IOException;

public class Model {

	private static final int WIDTH  = 80;
	private static final int HEIGHT = 24;

	private ConsoleView view;
	private ConsoleController controller;
	private Pleyer player;
	
	public Model() {
		this.view = new ConsoleView(this,WIDTH,HEIGHT);
		this.controller = new ConsoleController(this);
		this.player = new Pleyer();
		player.paint(view);
	}
	
	public void prosess(String event) {
		if(event.equals("TIME_ELAPSED")) {
			view.put('|', 10, 5);
		}
		else {
			player.paint(view);
		}
		view.update();
	}
	
	private void run() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		controller.run();
	}
	
	public Pleyer getPlayer() {
		return player;
	}
	
	public static void main(String[] args) throws IOException {
		Model model = new Model();
		model.run();
	}

}
