import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.Timer;

public class ConsoleController implements ActionListener {
	
	private final static int DELAY = 200;
	private Model model;
	private Timer timer;

	public ConsoleController(Model model){
		this.model = model;
		timer = new Timer(DELAY,this);
		
	}

	public void start() { timer.start(); }
	public void stop() { timer.stop(); }

	// キー入力の処理
	public void run() throws IOException {
		timer.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while((line = reader.readLine()) != null)
			model.prosess(line);
	}

	// 時間経過の処理
	@Override
	public void actionPerformed(ActionEvent e) {
		model.prosess("TIME_ELAPSED");
	}
	
	
	// 動作確認用
	public ConsoleController() {
		timer = new Timer(DELAY,this);
	}
	public static void main(String[] args) throws IOException {
		ConsoleController controller = new ConsoleController();
		controller.run();
	}
}
