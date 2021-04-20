import java.io.*;
import java.util.*;

public class Flag {
	String arg;
	Scanner file;
	FileWriter writer ;
	
	public void getInput(SearchEngine word, String[] args) throws FileNotFoundException {
		 for (int i = 0; i < args.length; i++) {
	        	arg = args[i].toLowerCase(); 		//ensures that the flag name is not case sensitive
	            if (arg.startsWith("-stoplist")) {
	                System.out.println("Stoplist = " + args[i + 1]);
	                try {
						file = new Scanner(new File(args[i + 1]));
						word.createStopList(file);
					} 
	                catch (FileNotFoundException e) {
						System.out.println("Stoplist File not Found");
						
						e.printStackTrace();
					}
	            }
	           
				if(arg.equals("-corpusdir")) {
					if (i < args.length) {
						
						String p =  args[i + 1];
						File dir = new File (p);
						System.out.println("directory obtain");
						try {
							word.getFilesFromDir(dir);
							
							System.out.println("finish loading inverted index");
						} catch (IOException e) {
							System.out.println("directory not  File not Found");
							e.printStackTrace();
						}
						
						
					}				
				}
				
	            if(arg.equals("-invertedindex")) {
	            	if (i < args.length) {
	            		System.out.println("InvertedIndex = " + args[i + 1]);
	            		try {
						   writer = new FileWriter(args[i+ 1]);
						   word.printInvertIndex(writer);
						   
						   writer.close();
						   System.out.println("finish printing inverted index");
						   
						} catch (IOException e) {
							System.out.println(" inverted index file not found");
							e.printStackTrace();
						}
	            	}
	            }
				if(arg.equals("-query")) {
					if (i < args.length) {	
						
						try 
						{
							file = new Scanner(new BufferedReader( new FileReader(args[i+ 1])));
							word.loadQuery(file);
							System.out.println("finish loading query");
						}
						catch(FileNotFoundException e) {
							System.out.println("query file not found");
							e.printStackTrace();
						}
					}	
				
				}
				
				if(arg.equals("-pathofdir")) {
					
					if (i < args.length) {	
						try {
							
							writer = new FileWriter(args[i + 1]);
							System.out.println("searching");
							word.printSearchResult(writer);
							System.out.println("finish writing search result");
							writer.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
		 }
	}

}
