package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public List<String> readFileTXT(String nameFileTXT) throws IOException{
		List<String> lines = new ArrayList<String>();
		FileReader archive = new FileReader(nameFileTXT);
		BufferedReader reader = new BufferedReader(archive);
		while(reader.ready()) {
			String line = reader.readLine();
			lines.add(line);	
		}
		
		return lines;
	}

}
