import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ReadFile {
	
	private static final int SHOW_RANK = 5;
	private File file ;
	private ArrayList<Integer> textInt;

	public ReadFile(String s){
		this.file = new File(s);
		this.textInt = new ArrayList<Integer>();
		setRanking();
	}
	
	public void setRanking() {
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String text;
			while((text = br.readLine()) != null) {
				textInt.add(Integer.valueOf(text));
			}
			reader.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	public void updateRanking(int score){
		textInt.add(score);
		Collections.sort(textInt,Collections.reverseOrder());

		try {
			FileWriter filewriter = new FileWriter(file);
			for(int i=0;i<SHOW_RANK;i++)
				filewriter.write(String.valueOf(textInt.get(i))+"\n");
			filewriter.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	

	public ArrayList<Integer> getRanking(){
		
		ArrayList<Integer> rank = new ArrayList<Integer>();
		for(int i=0;i<SHOW_RANK;i++)
			rank.add(textInt.get(i));
		return rank;
	}
	
	// 動作確認用
	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		
		ReadFile file = new ReadFile("ranking.txt");
		ArrayList<Integer> ai = file.getRanking();
		for(Integer n:ai)
			System.out.println(n);
		System.out.println();
		file.show();
	}
	private void show() {
		// TODO 自動生成されたメソッド・スタブ
		for(Integer n:textInt) {
			System.out.println(n);
		}
		
	}

}
