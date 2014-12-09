// Christine Perinchery
// 28 November 2014
// Latin 375/798

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class main {
	static ArrayList<BaseWord> list;
		
	public static void main(String[] args) {
		// Initialize variables
		BufferedReader inFile = null;
		BufferedWriter outFile = null;
		list = new ArrayList<BaseWord>();
		
		// Take in the file
		try {
			inFile = new BufferedReader(new FileReader("Latin.txt"));
		} catch (FileNotFoundException e) {
			//May want some sort of Usage display?
			e.printStackTrace();
		}
		//Read in the file
		try {
			String currentLine = inFile.readLine();
			outFile = new BufferedWriter(new FileWriter(new File("progamOutput.txt"))); 
			while(currentLine != null){
				String section = currentLine.split(" ")[0];
				// Take out all the unnecessary characters
				currentLine = currentLine.replaceAll("\\?", "");
				currentLine = currentLine.replaceAll("\\.", "");
				currentLine = currentLine.replaceAll(",", "");
				currentLine = currentLine.replaceAll("<", "");
				currentLine = currentLine.replaceAll(">", "");
				currentLine = currentLine.replaceAll("\\(", "");
				currentLine = currentLine.replaceAll("\\)", "");
				currentLine = currentLine.replaceAll(":", "");
				currentLine = currentLine.replaceAll(";", "");
				currentLine = currentLine.replaceAll("\\]", "");
				currentLine = currentLine.replaceAll("\\[", "");
				currentLine = currentLine.replaceAll("\\!", "");
				
				String[] line = currentLine.split(" ");
				for(int i=1; i< line.length; i++){
//					if(line[i].length()>4){
					if(line[i].length()>3){
						if(!wordIsInBaseWordList(line[i], section)){
							list.add(new BaseWord(line[i], section));
						}
					}
				}
				currentLine = inFile.readLine();				
			}
			
			for(int i= 0; i < list.size(); i++){
				outFile.write(list.get(i).printDiffConj());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			//May want some sort of Usage displayed?
		}finally{
			try{
				inFile.close();
				outFile.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	// Checks to see if the word has been found before
	public static boolean wordIsInBaseWordList(String w, String s){
		String wordMagicked = BaseWord.wordMagic(w);
		for(int i = 0; i< list.size(); i++){
			String baseWord = list.get(i).compareWord;
			int l = baseWord.length();
			if(!wordMagicked.equals("")){
				try{
					if(Math.abs(l-wordMagicked.length())<2){
						if(wordMagicked.startsWith(baseWord)||baseWord.startsWith(wordMagicked)){
							list.get(i).addToList(w, s);
							return true;
						}
					}
	//				if(baseWord.equalsIgnoreCase(w.substring(0, l-1))){
	//					list.get(i).addToList(w, n);
	//					return true;
	//				}
				}catch(Exception e){
					
				}
	//			if(w.contains(baseWord)){
	//				list.get(i).addToList(w, n);
	//				return true;
	//			}
			}
		}
	return false;
	}

}
