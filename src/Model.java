import java.io.IOException;

public class Model {

	private static final int WIDTH  = 0;
	private static final int HEIGHT = 0;

	private ConsoleView view;
	private ConsoleController controller;
	private Pleyer pleyer;
	
	public Model() {
		this.view = new ConsoleView(this,WIDTH,HEIGHT);
		this.controller = new ConsoleController(this);
		this.pleyer = new Pleyer();
	}
	
	private void run() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		controller.run();
	}
	
	public static void main(String[] args) throws IOException {
		Model model = new Model();
		model.run();
	}

}
