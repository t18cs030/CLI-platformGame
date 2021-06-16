import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ReadFile {
	
	private static final int SHOW_RANK = 5; // 表示するランキングの順位
	private File file ; 						 // ランキング保存先のファイルを保存
	private ArrayList<Integer> rank; 		 // ランキングスコアを保存する配列

	public ReadFile(String s){
		this.file = new File(s);
		this.rank = new ArrayList<Integer>();
		setRanking();
	}
	
	/* 現在のランキングを読み込む */
	public void setRanking() {
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String text;
			while((text = br.readLine()) != null) {
				rank.add(Integer.valueOf(text));
			}
			reader.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	/* 引数を用いてランキングを更新 */
	public void updateRanking(int score){
		rank.add(score);
		Collections.sort(rank,Collections.reverseOrder());

		try {
			FileWriter filewriter = new FileWriter(file);
			for(int i=0;i<SHOW_RANK;i++)
				filewriter.write(String.valueOf(rank.get(i))+"\n");
			filewriter.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	/* 現在のランキングの配列を返す */
	public ArrayList<Integer> getRanking(){
		ArrayList<Integer> rank = new ArrayList<Integer>();
		for(int i=0;i<SHOW_RANK;i++)
			rank.add(rank.get(i));
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
		for(Integer n:rank) {
			System.out.println(n);
		}
		
	}

}
