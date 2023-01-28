package wordAnalyzer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class main {
	
	static HashMap<String, Integer> wordCounter(ArrayList<String> wordList){
		
		HashMap<String, Integer> frequency = new HashMap<String, Integer>();	// HashMap to store k,v of words, and frequency of words
		
		for (String word : wordList) {			// Looping through every word in given array
			word.toLowerCase();					// Set to lower case
			Integer count = frequency.get(word);          
			frequency.put(word, (count==null) ? 1 : count+1);

			}
		System.out.println(frequency);
		return frequency;
	}
	
	// for(int i = 0; i<array.length; i++) prints the length of the array
	// for(String x : array) looping through everything in the list

	public static void main(String[] args) {
		final String url = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
		ArrayList<String> section = new ArrayList<String>();
		
		try {
			final Document soup = Jsoup.connect(url).get(); 	// Getting the html of the page
			
			for(Element line : soup.getElementsByTag("p")) {	// Naming var "line" and printing everything with the tag of p in our soup
				String sLine = line.text();						// Converting Jsoup "Element" type, to Java "String" type with text() function.
				section.add(sLine);								// Adding the 18 paragraphs to our ArrayList "section"	
			}
			
			ArrayList<String> arrOfStr = new ArrayList<String>();	// This ArrayList is used to store arrays of strings
			
			for (int i = 0; i < section.size(); i++) {
				String para = section.get(i);				// Assigning a paragraph to the para variable
				String[] word = para.split(" ");			// Creating a string array called "word", using " " delimiter to split
				arrOfStr.addAll(Arrays.asList(word));		// Adding arrays of words to our wordList
			}
			
			ArrayList<String> wordList = new ArrayList<String>();
			
			for (int i = 0; i < arrOfStr.size(); i++) {			// Now begin looping through the ArrayList of arrays.
				for(String word : arrOfStr) {					// Looping through each individual word
					word = word.replaceAll("[^A-Za-z]+", "");	// Removes all characters like commas, exclamation points, etc
					wordList.add(word);							// Making one giant list instead of 18 individual ones with wordList
				}
			}
			
			//System.out.println(wordList);
			wordCounter(wordList);
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		
		
	}

}
