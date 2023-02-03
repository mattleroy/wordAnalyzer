package wordAnalyzer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class main {
	
	public static ArrayList<String> htmlScraper(String url){	// Input string of URL
		
		ArrayList<String> section = new ArrayList<String>();	// ArrayList to store large section of html
		
		try {
			final Document soup = Jsoup.connect(url).get(); 	// Getting the html of the page
			
			for(Element line : soup.getElementsByTag("p")) {	// Naming var "line" and printing everything with the tag of p in our "soup"
				String sLine = line.text();						// Converting Jsoup "Element" type, to Java "String" type with text() function.
				section.add(sLine);								// Adding the 18 paragraphs to our ArrayList "section"
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		ArrayList<String> arrOfStr = new ArrayList<String>();	// This ArrayList is used to store arrays of strings
		
		for (int i = 0; i < section.size(); i++) {
			String para = section.get(i);						// Assigning a paragraph to the para variable
			String[] word = para.split(" ");					// Creating a string array called "word", using " " delimiter to split
			arrOfStr.addAll(Arrays.asList(word));				// Adding arrays of words to our wordList
		}
		return arrOfStr;
	}
	
	public static ArrayList<String> wordStripper(ArrayList<String> wordArray){	// Strip off non-alpha chars
		
		ArrayList<String> wordList = new ArrayList<String>();
		for (String word: wordArray) {
			word.toLowerCase();								// Set to lower-case (doesn't actually seem to work)
			word = word.replaceAll("[^A-Za-z]+", "");		// Removes all characters like commas, exclamation points, etc
			wordList.add(word);								// Making one giant list instead of 18 individual ones with wordList
		}
		return wordList;
	}
	
	public static Map<String, Integer> wordCounter(ArrayList<String> wordList) { // Input ArrayList

		Map<String,Integer> wordMap = new HashMap<>();
		
		for (String word: wordList) {			// Looping through each word in ArrayList
			Integer count = wordMap.containsKey(word) ? wordMap.get(word) : 0;	// This sets count var to either 0 or the key value
	        wordMap.put(word, count + 1);		// Putting the word (skips duplicates) and count + 1 into HashMap
		}
		return wordMap;
	}
	
	public static Map<String, Integer> topTwentyWords(Map<String, Integer> map){
		
		Map<String, Integer> highestFreqWords = new HashMap<String, Integer>();
		
		int count = 0;												// Counter to stop while loop
		while (count != 21) {
			int max = Collections.max(map.values()); 				// Using Collections from java.util to utilize "max" function to find max int
			for (Entry<String, Integer> entry: map.entrySet()) {	// Creating var entry and looping through all entries in our map
				if(entry.getValue() == max) {						// If we find something that equals "max" move to next line
					highestFreqWords.put(entry.getKey(), max);		// Put that value in our new "top 20" map
					map.remove(entry.getKey(), max);				// Now remove that entry from original map, so we don't pull it again
					count++;										// Increment count for while loop
					break;											// Start at beginning of while loop
				}
			}
		}
		System.out.println(highestFreqWords);						// Print func to visualize data
		return highestFreqWords;
	}
	
	public static void main(String[] args) {
		
		// Url to scrape
		final String url = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";

		// Nesting all of our created functions
		topTwentyWords(wordCounter(wordStripper(htmlScraper(url))));
		
	}
}