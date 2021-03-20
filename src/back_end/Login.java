package back_end;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Login {

	private String userName;
	private String login;
	private String password;
	private String txt = "";
	
	public Login(String user, String login, String password){
		userName = user;
		this.login = login;
		this.password = password;
	}
	
	public Login(String login, String password){
		this.login = login;
		this.password = password;
	}
	
	public boolean isValid() {
		
		boolean empty = userName.equals("") || login.equals("") || password.equals("");
		boolean blank = userName.trim().equals(" ") || login.trim().equals(" ") || password.trim().equals(" ");
		boolean lessThan6 = userName.length() < 6 || login.length() < 6 || password.length() < 6;
		if(empty || blank || lessThan6)
			return false;
		
		return true;
	}
	
	public void created(boolean condition) {
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.dir")+"/users/users.txt",condition); 
			BufferedWriter bw = new BufferedWriter(fw);
			if(condition)
				bw.write("\n"+userName+"\t"+login+"\t"+password);
			else
				bw.write(txt);
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleted() {
		created(false);
	}
	
	public boolean exist(boolean containUser) {
		String path = System.getProperty("user.dir")+ "/users/users.txt";
		String line = "";
		String[] el;
		int i=0;
		boolean exist = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			while (br.ready()) {
				line = br.readLine();
				el = line.split("\t");
				
				boolean allEqual;
				
				if(containUser)
					allEqual = userName.equals(el[0]) && login.equals(el[1]) && password.equals(el[2]);
				else {
					allEqual = login.equals(el[1]) && password.equals(el[2]);
					userName = el[0];
				}
					
				if(allEqual) {
					exist = true;
				}else {
					if(i==0)
						txt+= line;
					else
						txt+= "\n" +line;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return exist;
	}

	public String getUserName() {
		return userName;
	}
	
}
