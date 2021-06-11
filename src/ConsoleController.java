import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.Timer;

public class ConsoleController implements ActionListener {
	
	private final static int DELAY = 400;
	private Model model;
	private Timer timer;

	public ConsoleController(Model model){
		// TODO 自動生成されたコンストラクター・スタブ
		this.model = model;
		timer = new Timer(DELAY,this);
		
	}

	public void run() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		// キー入力の処理
		timer.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while((line = reader.readLine()) != null)
			//System.out.println(line);
			model.prosess(line);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		// 時間経過の処理
		//System.out.println("TIME_ELAPSED");
		model.prosess("TIME_ELAPSED");
	}
	
	public void stop() { timer.stop(); }
	
	// デバック用
	public ConsoleController() {
		timer = new Timer(DELAY,this);
	}
	public static void main(String[] args) throws IOException {
		ConsoleController controller = new ConsoleController();
		controller.run();
	}
}
