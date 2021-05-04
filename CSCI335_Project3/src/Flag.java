/*
 * Trisha Espejo
 * Phase 3
 * Due: 5/11/21
 */
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
						   word.createInvertedIndex();
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
						i = i + 1;
						String action = "";
						if (i != args.length)
							action = args[i].toLowerCase();
						
						if(action.equals("text")) {
							i = i + 1;
							try {
								
								writer = new FileWriter(args[i]);
								//System.out.println("searching");
								word.printSearchResult(writer);
								System.out.println("finish writing search result");
								writer.close();
							} catch (IOException e) {
								System.out.println("result file not found");
								e.printStackTrace();
							}
							
						}
						else if (action.equals("gui")) {
							word.displayGUI();
						}
						else if (action.equals("both")) {
							word.displayGUI();
							i = i + 1;
							
							if (i != args.length) {
								action = args[i].toLowerCase();
								try {
									
									writer = new FileWriter(action);
									System.out.println("searching");
									word.printSearchResult(writer);
									System.out.println("finish writing search result");
									writer.close();
								} catch (IOException e) {
									System.out.println("result file not found");
									e.printStackTrace();
								}
							}
							else {
								try {
									
									writer = new FileWriter("Result.txt");
									System.out.println("searching");
									word.printSearchResult(writer);
									System.out.println("finish writing search result");
									writer.close();
								} catch (IOException e) {
									System.out.println("result file not found");
									e.printStackTrace();
								}
							}
						}
						else {
							try {
								
								writer = new FileWriter("Result.txt");
								System.out.println("searching");
								word.printSearchResult(writer);
								System.out.println("finish writing search result");
								writer.close();
							} catch (IOException e) {
								System.out.println("result file not found");
								e.printStackTrace();
							}
						}
					}
				}
				
				if(arg.equals("-isstem")) {
					if (i < args.length) {	
						String isStem = args[i + 1].toLowerCase();
						if (isStem.equals("yes")) {
							System.out.println( "Stemming is enabled");
							word.isStem = true;
						}
						else if (isStem.equals("no")) {
							System.out.println( "Stemming disabled");
							word.isStem = false;
						}
						else 
							System.out.println( "Stemming is disabled");
					}
				}
				

				
				
		 }
	}

}