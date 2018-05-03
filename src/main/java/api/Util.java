package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public List<String> readFileTXT(String path) throws IOException{
		List<String> lines = new ArrayList<String>();
		File file = new File(path);
		
		if(!file.exists()) {
			throw new IOException("Arquivo não existe.");
		}
		
		FileReader fileReader = new FileReader(path);
		BufferedReader bufferReader = new BufferedReader(fileReader);
		while(bufferReader.ready()) {
			String line = bufferReader.readLine();
			lines.add(line);	
		}
		
		fileReader.close();
		bufferReader.close();
		
		return lines;
	}
	
	public String[] split(String line, String parameter) {
		String[] result = line.split(parameter);
		return result;
	}

}
