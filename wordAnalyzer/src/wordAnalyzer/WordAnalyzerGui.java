package wordAnalyzer;
import javax.swing.*;
import java.awt.event.*;
import java.util.Map;

import wordAnalyzer.main;

public abstract class WordAnalyzerGui implements ActionListener{

	private main mainClass;
	
	public WordAnalyzerGui(){
		
		// Instance of main class
		mainClass = new main();
		
		// Create GUI components
		JFrame frame = new JFrame("Word Analyzer");
		JLabel label = new JLabel();
		
		// Create button with ActionListener
		JButton button = new JButton("Analyze");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Oi mate we got here");
			}
		});
		
		((JComponent) frame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		frame.getContentPane().add(button, "North");
		frame.getContentPane().add(label, "Center");
		
		frame.pack();
		frame.setSize(800,800);
		frame.setVisible(true);
		
	}
	
	/*
	 * 
	 * 			public void actionPerformed(ActionEvent e) {
				final String url = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
				
				// Run main func
				Map<String, Integer> result = mainClass.topTwentyWords(mainClass.wordCounter(mainClass.wordStripper(mainClass.htmlScraper(url))));
				//topTwentyWords(wordCounter(wordStripper(htmlScraper(url))));
				
				// Create string representation of the map
				String mapString = "Word Frequencies:\n";
				for (Map.Entry<String, Integer> entry : result.entrySet()) {
				    String word = entry.getKey();
				    int frequency = entry.getValue();
				    mapString += String.format("%s : %d%n", word, frequency);
				}
				
				label.setText(mapString);
			}
	 */
	
	
	//public static void main(String[] args) {
		
		// Create the new frame and set its properties
		//JFrame frame = new JFrame("Gui for WordAnalyzer");
		//frame.setSize(400,400);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create the components
		//JLabel label = new JLabel("Hello GUI");
		//JButton button = new JButton("Click Me");
		
		// Add the components to the frame
		//frame.setLayout(new FlowLayout());
		//frame.add(label);
		//frame.add(button);
		
		// Make visible
		//frame.setVisible(true);

	//}

	
}
