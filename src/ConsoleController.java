import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ConsoleController implements ActionListener {
	
	private final static int DELAY = 50;
	private Model model;
	private Timer timer;

	public ConsoleController(Model model){
		// TODO 自動生成されたコンストラクター・スタブ
		this.model = model;
		timer = new Timer(DELAY,this);
		
	}

	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		// キー入力の処理
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		// 時間経過の処理
		
	}
	
	// デバック用
	public ConsoleController() {
		timer = new Timer(DELAY,this);
	}
	public static void main(String[] args) {
		ConsoleController controller = new ConsoleController();
		controller.run();
	}
}
