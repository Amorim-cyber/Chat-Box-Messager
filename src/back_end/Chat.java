package back_end;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Chat {
	
	private String txt;
	private String path;
	private boolean invalid;
	
	public Chat(String m, String d, String y, boolean isRecord) {
		path = System.getProperty("user.dir")+ "/chatDates/"+m+"-"+d+"-"+y+".txt";
		File file = new File(path);
		if(!file.exists()) {
			try {
				if(isRecord)
					invalid=true;
				else {
					file.createNewFile();
					txt = read(path);
					invalid=false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	private String read(String path) {
		String txt = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			while (br.ready()) {
				txt += br.readLine() + "\n";
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return txt;
	}
	
	private void write(String path,String txt) {

		try {
			FileWriter fw = new FileWriter(path); 
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(txt);
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	public void save(String txt) {
		write(path,txt);
	}

	public String getTxt() {
		txt = read(path);
		return txt;
	}

	public boolean isInvalid() {
		return invalid;
	}

	
}
