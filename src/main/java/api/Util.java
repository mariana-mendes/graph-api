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
			throw new IOException("Arquivo n�o existe.");
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

	/**
	 * Popula um ArrayList com outros ArrayLists vazios, deixando um array no
	 * formato correto para montar uma lista de adjacencia
	 * 
	 * @param maxVertex
	 *            - número de indices preenchidos por arrayLists
	 * @return ArrayLists de arrayLists
	 */
	public ArrayList<ArrayList<Integer>> fillArrayList(int maxVertex) {
		ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= maxVertex; i++) {
			ArrayList<Integer> empty = new ArrayList<Integer>();
			array.add(empty);
		}
		return array;
	}
}
