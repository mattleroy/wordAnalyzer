package wordAnalyzer;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class tests {

	@Test
	public void testWordStripper() {

		ArrayList<String> testCase = new ArrayList<String>(); 
		testCase.add("testing");
		testCase.add("THE");
		testCase.add("word!");
		testCase.add("strIPPIng .,");
		testCase.add("Capabilities_");

		ArrayList<String> expectedOutput = new ArrayList<String>();
		expectedOutput.add("testing");
		expectedOutput.add("the");
		expectedOutput.add("word");
		expectedOutput.add("stripping");
		expectedOutput.add("capabilities");
		
		ArrayList<String> actualOutput = main.wordStripper(testCase);
		
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void testWordCounter() {
		
		main mainClass = new main();

		ArrayList<String> testAL = new ArrayList<String>();
		testAL.add("apple");
		testAL.add("apple");
		testAL.add("apple");
		testAL.add("orange");
		
		Map<String, Integer> testMap = mainClass.wordCounter(testAL);
		
		Map<String, Integer> expectedMap = new HashMap<>();
		expectedMap.put("apple", 3);
		expectedMap.put("orange", 1);
		assertEquals(expectedMap, testMap);
	}
}
