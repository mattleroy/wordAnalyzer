package wordAnalyzer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import wordAnalyzer.WordAnalyzerGui;

public class main {
	
	/**
	 * This method takes a URL as a string input, connects to the webpage, and scrapes all text within paragraph tags from the HTML code.
	 * It then splits the text into individual words, and stores them in an ArrayList of strings.
	 * @param url a string representing the URL of the webpage to be scraped
	 * @return an ArrayList of strings containing all words found within the paragraphs of the webpage
	 */
	
	public static ArrayList<String> htmlScraper(String url){	// Input string of URL
		
		ArrayList<String> paragraphs = new ArrayList<String>();	// ArrayList to store large section of html
		
		try {
			final Document soup = Jsoup.connect(url).get(); 	// Getting the html of the page
			
			for(Element line : soup.getElementsByTag("p")) {	// Naming var "line" and printing everything with the tag of p in our "soup"
				String paragraphText = line.text();				// Converting Jsoup "Element" type, to Java "String" type with text() function.
				paragraphs.add(paragraphText);					// Adding the 18 paragraphs to our ArrayList "paragraphs"
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		ArrayList<String> arrOfStr = new ArrayList<String>();	// This ArrayList is used to store arrays of strings
		
		for (int i = 0; i < paragraphs.size(); i++) {
			String para = paragraphs.get(i);					// Assigning a paragraph to the para variable
			String[] word = para.split(" ");					// Creating a string array called "word", using " " delimiter to split
			arrOfStr.addAll(Arrays.asList(word));				// Adding arrays of words to our wordList
		}
		return arrOfStr;
	}

	/**
	 * This method strips off non-alphabetic characters and converts all alphabetic characters to lowercase.
	 * It takes an ArrayList of strings as input and returns an ArrayList of strings with only alphabetic characters in lowercase.
	 * @param wordArray the input ArrayList of strings
	 * @return an ArrayList of strings with only alphabetic characters in lowercase
	 */
	
	public static ArrayList<String> wordStripper(ArrayList<String> wordArray){	// Strip off non-alpha chars
		
		ArrayList<String> wordList = new ArrayList<String>();
		for (String word: wordArray) {
			word = word.toLowerCase();						// Set to lower-case
			word = word.replaceAll("[^A-Za-z'-]+", "");		// Removes all characters like commas, exclamation points, etc
			if (!word.isEmpty()) { 							// Only add non-empty strings to the list
				wordList.add(word);
			}
		}
		return wordList;
	}
	
	/**
	 * This method counts the frequency of each unique word in an input ArrayList and returns a Map with the word as the key
	 * and its frequency as the value.
	 * @param wordList the input ArrayList of strings with only alphabetic characters in lowercase
	 * @return a Map with the word as the key and its frequency as the value
	 */
	
	public static Map<String, Integer> wordCounter(ArrayList<String> wordList) { // Input ArrayList

		Map<String,Integer> wordMap = new HashMap<>();
		
		for (String word : wordList) {
			String trimmedWord = word.trim().toLowerCase();		// Trims words of whitespace, lowercases as well (redundant)
			if (!trimmedWord.isEmpty()) {						// Checks for empty strings
				wordMap.merge(trimmedWord, 1, Integer::sum);	// Adds trimmedWord to map. Initializes to 1 if it doesn't exist in the map
			}													// Otherwise grabs existing word and increments by 1 with Integer::sum
		}
		
		return wordMap;
	}
	
	/**
	* Returns a Map of the top 20 most frequently occurring words in the input Map, sorted in descending order by frequency.
	* The input Map must have String keys and Integer values, representing words and their respective frequencies.
	* @param wordMap a Map with String keys and Integer values, representing words and their respective frequencies
	* @return a Map of the top 20 most frequently occurring words in the input Map, sorted in descending order by frequency
	*/
	
	public static Map<String, Integer> topTwentyWords(Map<String, Integer> wordMap) {
	    
		// Where the top 20 words will be stored
		Map<String, Integer> topTwenty = new HashMap<>();
	    
		// Reference code: (Map.Entry<String, Integer> entry : myMap.entrySet()) This is a for loop where the type is
		// Map.Entry<...> which names the var "entry" and loops through myMap.entrySet()
		
	    PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(	// New PriorityQueue
	            Map.Entry.<String, Integer>comparingByValue().reversed());		// Reversing k-v pairs

	    queue.addAll(wordMap.entrySet());
	    for (int i = 0; i < 20 && !queue.isEmpty(); i++) {
	        Map.Entry<String, Integer> entry = queue.poll();	// Poll to retrieve entry with highest value (defined above with comparingByValue().reversed()
	        topTwenty.put(entry.getKey(), entry.getValue());	// Putting that value inside our topTwenty HashMap
	    }
	    System.out.println(topTwenty);	// Visualization
	    return topTwenty;
	}
	
	/**
	* Main method that starts the Word Analyzer GUI.
	* @param args command line arguments (not used)
	*/
	
	public static void main(String[] args) {
		
		new WordAnalyzerGui();
		
	}

}