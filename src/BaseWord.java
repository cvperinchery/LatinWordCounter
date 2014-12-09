import java.util.ArrayList;


public class BaseWord {
	String compareWord;
	int lengthWord;
	ArrayList<Word> diffConjugations;
	
	BaseWord(String w, String s){
		compareWord = wordMagic(w);
		lengthWord = compareWord.length();
		diffConjugations = new ArrayList<Word>();
		diffConjugations.add(new Word(w, s));
	}
	
	public String toString(){
		return compareWord;
	}
	
	public boolean equals(Object o){
		return ((BaseWord) o).compareWord == this.compareWord;
	}
	
	//This takes in a String and returns the base of the word 
	// any endings. This does not take out the prefix if the
	// word has one. 
	static public String wordMagic(String word){
		String wordDuplicated = word;
		if(word.endsWith("que")){
			word = word.substring(0, word.length() -3);
		}
		if(word.contains("issim")){
			word = word.substring(0, word.length() - 6);
		}
		
		if(word.endsWith("erunt") || word.contains("isse") || word.startsWith("esse")){
			if(!word.equalsIgnoreCase("esse"))
				word = word.substring(0, word.length() - 5);
		}else if(word.endsWith("orum") || word.endsWith("arum") || word.endsWith("ibus") || word.endsWith("tur") || word.endsWith("erit")){
			word = word.substring(0, word.length() - 4);
		}else{
			word = word.substring(0, word.length() -2);
		}
		//if after the trimming there is nothing left, then return the original word
		if(word.equals("")){
			return wordDuplicated;
		}
		return word;
		
	}
	
	// This adds the word to the Word List with 
	// its base word. 
	public void addToList(String w, String s){
		diffConjugations.add(new Word(w, s));
	}
	
	//Prints out and returns a String of the 
	// base word with each of its words with
	// their corresponding section
	public String printDiffConj(){
		System.out.println(compareWord);
		String out = compareWord + "\n";
		
//		if(diffConjugations.size() ==1){
//			return "";
//		}
		
		if(diffConjugations != null){
			for(int i =0; i< diffConjugations.size(); i++){
				System.out.println("\t"+diffConjugations.get(i) +"\t" + diffConjugations.get(i).section);
				out = out + "\t"+diffConjugations.get(i) +"\t" + diffConjugations.get(i).section + "\n";
			}
		}
		return out;
	}
}

	
