package wordAnalyzer;
import javax.swing.*;
import java.awt.event.*;
import java.util.Map;

public class WordAnalyzerGui implements ActionListener{

	private String url = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
	
	public WordAnalyzerGui(){
		
		// Create GUI components
		JFrame frame = new JFrame("Word Analyzer");
		JPanel panel = new JPanel();

		// Layout
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,460);
		frame.add(panel);
		panel.setLayout(null);
		
		// JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10,65,260,400);
		panel.add(scrollPane);
		
		// JList
		JList<String> list = new JList<String>();
		scrollPane.setViewportView(list);
		
		// JButton
		JButton button = new JButton("Analyze");
		button.setBounds(90,10,100,40);
		panel.add(button);
		
		// JButton ActionListener
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    // Call the necessary methods to get the result map
			    Map<String, Integer> result = main.topTwentyWords(main.wordCounter(main.wordStripper(main.htmlScraper(url))));

			    // Create a DefaultListModel object and add the keys and values from the result map to it
			    DefaultListModel<String> listModel = new DefaultListModel<>();
			    for (Map.Entry<String, Integer> entry : result.entrySet()) {
			        listModel.addElement(entry.getKey() + ": " + entry.getValue());
			    }

			    // Set the listModel as the model for your JList object
			    list.setModel(listModel);

			    // Add the list object to the scrollPane
			    scrollPane.setViewportView(list);
			}
		});
		frame.setVisible(true);

	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

