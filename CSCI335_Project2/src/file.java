/*
 * Trisha Espejo
 * Phase 2
 * Due: 4/20/2021
 */
// the class save all information (what word is, the file that word came from, frequency of  the word in file) a word has when searching 

import java.util.ArrayList;
public class file {
	private int count = 0;
	private String word = "";
	private String file = "";
	private ArrayList<Integer> index = new ArrayList<Integer>();
	

	// get filename
	public String getFile()
	{
		return file;
	}
	// get the word
	public String getWord()
	{
		return word;
	}
	//get the number of occurance of word in the file
	public int getCount()
	{
		return count;
	}
	public ArrayList<Integer> getIndex()
	{
		return index;
	}
	//set filename
	public void setFile(String n)
	{
		this.file = n;
		
	}
	//set the word
	public void setWord(String w)
	{
		this.word = w;
		
	}
	//set the number of occurance of word in the file
	public void setCount(int c)
	{
		this.count = c;
	}
	public void setIndex(int x)
	{
		index.add(x);	
	}
	public String print() {
		String result = "";
		StringBuilder temp = new StringBuilder();
		
		for(int i = 0; i < index.size(); i++) {
			temp.append(index.get(i));
			if(i + 1 < index.size())
				temp.append(", ");
			//System.out.println(index.get(i));
		}
		result = "[ " + file + " " + "word count = " + count + ", " + temp.toString() + " ]" ; 
		return result;
		
	}
}