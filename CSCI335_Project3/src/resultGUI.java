
/*
 * Trisha Espejo
 * Phase 3
 * Due: 5/11/21
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class resultGUI extends JFrame {
	private JTextArea content;
    private StringBuilder text;
    
    public resultGUI()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        this.text = new StringBuilder();
        this.content = new JTextArea(this.text.toString());
		this.getContentPane().add(this.content); 
        
       
    }

	public void printResult(ArrayList<String> query, HashMap<String, LinkedList<file>> invertIndex) {
		this.setTitle("Result");
		this.setSize(1000, 1500);
		JScrollPane scroll = new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.content.setLineWrap(true);
        this.content.setWrapStyleWord(true);
        this.add(scroll);
        this.setVisible(true);
        //this.getContentPane().add(this.content); 
		int frequency;
		int occurance;
		String fileName;
		String word = "";
		LinkedList<file> docs = new LinkedList<file>();
		
		for(int i = 0; i < query.size(); i++) {
			word = query.get(i);
			if(invertIndex.containsKey(word)) {
				docs = invertIndex.get(word);
				frequency = docs.size();
				text.append("word: " + "'" +  word + "'" + "\t number of docs found: " + frequency + "\n" );
				for(int j = 0; j < docs.size(); j++) {
					file doc = new file();
					doc = docs.get(j);
					text.append( doc.print() + "\n");
				}
				text.append("\n");
				
				
			}
			
		}
        this.content.setText(this.text.toString());
        //this.pack();
    
	}
	
	public void showgui()
    {
        this.pack();
        this.setVisible(true);
    }

}
