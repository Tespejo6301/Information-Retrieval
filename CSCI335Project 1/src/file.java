// the class save all information (what word is, the file that word came from, frequency of  the word in file) a word has when searching 

public class file {
	private int count = 0;
	private String word = "";
	private String file = "";
	

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
}
