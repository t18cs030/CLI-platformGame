import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadFile {
	
	private static final int SHOW_RANK = 5;
	private File file; 
	private Path Pfile;
	private List<String> textL ;
	private List<Integer> textInt;

	public ReadFile(String s) throws IOException {
		this.file = new File("ranking.txt");
		this.Pfile = Paths.get("ranking.txt");
		this.textL = Files.readAllLines(Pfile);
		this.textInt = new ArrayList<Integer>();
		for(String str:textL)
			textInt.add(Integer.parseInt(str));
		
	}
	public void updateRanking(int score)throws IOException {
		textInt.add(score);
		Collections.sort(textInt,Collections.reverseOrder());

		FileWriter filewriter = new FileWriter(file);
		for(int i=0;i<SHOW_RANK;i++)
			filewriter.write(String.valueOf(textInt.get(i))+"\n");
		filewriter.close();
		
	}
	public List<Integer> getRanking(){
		List<Integer> rank = new ArrayList<Integer>();
		for(int i=0;i<SHOW_RANK;i++)
			rank.add(textInt.get(i));
		return rank;
	}
	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		
		File file = new File("ranking.txt");

		Path Pfile = Paths.get("ranking.txt");
		List<String> textL = Files.readAllLines(Pfile);
		ArrayList<Integer> textInt = new ArrayList<Integer>();
		for(String s:textL)
			textInt.add(Integer.parseInt(s));
		
		// write file
		// File file = new File("ranking.txt");
		FileWriter filewriter = new FileWriter(file);
		int n = 22;
		textInt.add(n);
		Collections.sort(textInt,Collections.reverseOrder());
		for(int num :textInt)
			System.out.println(num);
		textL.toString();
		for(int i=0;i<5;i++)
			filewriter.write(String.valueOf(textInt.get(i))+"\n");
		filewriter.close();
	}

}
