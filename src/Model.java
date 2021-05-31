
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
	}
	
	public void prosess(String event) {
		if(event.equals("TIME_ELAPSED")) {
			
		}
		else {
			player.paint(view);
		}
		view.update();
	}
	
	private void run() {
		// TODO 自動生成されたメソッド・スタブ
		controller.run();
	}
	
	public static void main(String[] args) {
		Model model = new Model();
		model.run();
	}

}
