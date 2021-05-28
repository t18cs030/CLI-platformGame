
public class Model {

	private static final int WIDTH  = 0;
	private static final int HEIGHT = 0;
	private ConsoleView view;
	private ConsoleController controller;
	
	public Model() {
		this.view = new ConsoleView(this,WIDTH,HEIGHT);
		this.controller = new ConsoleController(this);
	}
	
	private void run() {
		// TODO 自動生成されたメソッド・スタブ
		return;
	}
	
	public static void main(String[] args) {
		Model model = new Model();
		model.run();
	}

}
