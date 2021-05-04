/*
 * Trisha Espejo
 * Phase 2
 * Due: 4/20/2021
 */
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class SearchEngine {
	private HashMap<Integer, String> stopWord = new HashMap<Integer, String>();
	private ArrayList<HtmlProperties> html = new ArrayList<HtmlProperties>();
	private static HashMap<String, LinkedList<file>> invertIndex;
	private File []htmlFiles;
	private HtmlProperties fileInfo;
	private ArrayList<String> query;
	public boolean isStem = false;
	
	public void createStopList(Scanner file) {
		String input = "";
		int count = 0;
		
		while(file.hasNextLine()){
			input = file.nextLine();
			this.stopWord.put(count, input);
			count++;
		}
		System.out.println("Finish reading all stopwords and storing it");
	}
	//remove stopword
	public void removeStopword(ArrayList<String> input) {
			boolean isStopword = false;
			Iterator<String> iterate = input.iterator();
			while(iterate.hasNext()) {
				String x = iterate.next();
				isStopword = stopWord.containsValue(x);
				if(isStopword) iterate.remove();		
		}
			
	}
	
	//get the file from directory
	public void getFilesFromDir(File dir) throws IOException {
		htmlFiles = dir.listFiles(new FilenameFilter() {
	        @Override
	        public boolean accept(File dir, String name) {
	            return !name.equals(".DS_Store");
	        }
	    });

		getContenthtml();
		htmlRemovejunk();
		removehtmlStopwords();
		invertIndex = new HashMap<String, LinkedList<file>>();

		
		
		
	}
	
	private void getContenthtml() throws IOException {
		
		for(int i = 0; i < htmlFiles.length; i++) {
			String sentence;
			fileInfo = new HtmlProperties();
			Scanner input = new Scanner(htmlFiles[i]);
			fileInfo.setName(htmlFiles[i].toString());
			//System.out.println( "Name: " + htmlFiles[i].toString());
			while(input.hasNextLine()) {
				sentence = input.nextLine().toLowerCase();
				if (!sentence.isEmpty()) {
				fileInfo.setContent(sentence + "\n");  	
				}
			}
				
				html.add(fileInfo);	
			}
		
	}
	private void htmlRemovejunk(){
		String content = "";

		for(int i = 0; i < html.size(); i++) {
			HtmlProperties temp = new HtmlProperties(); 
			fileInfo = new HtmlProperties();
			fileInfo = html.get(i);
			
			temp.setName(fileInfo.getName());
			content = fileInfo.getContent().toString();
			content = content.replaceAll("<head>*</head>", "");
			content = content.replaceAll("<style(.*?)</style>", "");
			content = content.replaceAll("<script(.*?)<\\/script>", "");
			content = content.replaceAll("<[^>]*>", " ");
			content = content.replaceAll("[^a-zA-Z]+", " ");
			content = content.replaceAll("lt", "");
			content = content.replaceAll("gt", "");
			temp.setContent(content);
			html.set(i, temp);
		}
		
		
		
	}
	private void removehtmlStopwords(){
		String content = "";
		for(int i = 0; i < html.size(); i++) {
			HtmlProperties x = new HtmlProperties(); 
			fileInfo = html.get(i);
			content = fileInfo.getContent().toString();
			x.setName(fileInfo.getName());
			String[] word = content.split(" ");
			ArrayList<String> temp = new ArrayList<String>(Arrays.asList(word));
			//remove stop words
			removeStopword(temp);
			StringBuilder sb = new StringBuilder();
			for (String s: temp) {
					sb.append(s + " ");
			}
			content = sb.toString();
			
			x.setContent(content);
			//add replace the old file content
			html.set(i, x);
		}
		
		

	}
	// goes over each file and create inverted index
	public void createInvertedIndex() {
		 String content;
		 String fileName = "";
		 // goes over each html file
		for(int i = 0; i < html.size(); i++) {
			fileInfo = html.get(i);
			fileName = fileInfo.getName();
			content = fileInfo.getContent().toString().trim();
			String[] words = content.trim().split(" ");
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(words));
			list.removeAll(Arrays.asList("", null));
			// get each word from the html file 
			for (int j = 0; j < list.size(); j++) {
				String word = list.get(j);
				if(isStem == true) {
					createStem stem = new createStem();
					word = stem.generate(word);
				}
				generateinvertedIndex(j, fileName, word);
			}
			
		}
		
	}
	
	

	//put the content of file in an inverted index
	private void generateinvertedIndex(int j, String f, String w) {
		LinkedList<file> a = new LinkedList<file>();
		file info = new file();
		boolean hasFile = false;
		String fileName = f;
		String word = w;
		 int count = 0;
		 
		 // if inverted index is empty add the word and file to the hash
		 if(invertIndex.isEmpty()) {
			 
			 info.setFile(fileName);
			 info.setWord(word);
			 count = 1;
			 info.setCount(count);
			 a.add(info);
			 invertIndex.put(word, a);
		 }
		 else if(invertIndex.containsKey(word)) {
			 a = invertIndex.get(word);
			 for(int i = 0; i < a.size(); i++) {
				 String txt = a.get(i).getFile();
				 // if word exist and file exist increase the frequency
				 if(txt == fileName) {
					 count = a.get(i).getCount();
					 count++;
					 info = a.get(i);
					 info.setCount(count);
					 info.setFile(fileName);
					 info.setWord(word);
					 info.setIndex(j);
					 a.set(i, info);
					 invertIndex.replace(word, a);
					 hasFile = true;
					 break; 
				 }
				 else 
					 hasFile = false;
			
			 }
			 // if word exist and new file add the new file to the inverted index
			 if(!hasFile) {
				 
				 count = 1;
				 info.setCount(count);
				 info.setFile(fileName);
				 info.setWord(word);
				 a.add(info);
				 invertIndex.replace(word, a);
			 } 
		 }
		 else {
			 // if word does not exist add it to the hash table
			 count = info.getCount();
			 count = 1;
			 info.setCount(count);
			 info.setFile(fileName);
			 info.setWord(word);
			 a.add(info);
			 invertIndex.put(word, a);
		 }
	}
	//loads the query from query.txt
	public void loadQuery(Scanner file) {
		String input = "";
		 query = new ArrayList<String>();
		while(file.hasNextLine()){
			input = file.nextLine();
			input = input.toLowerCase();
			String [] x = input.split("[^a-zA-Z]+");
			for(int i = 0; i < x.length; i++) {
				query.add(x[i]);                                                 //remove the stopwords in the query for easier search 
			}
			
		}	
	}
	
	public void printSearchResult(FileWriter output) {
		StringBuilder text = new StringBuilder();
		int frequency;
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
       try {
		output.write(text.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	// print the inverted index 
	// print the word and all its document
	public void printInvertIndex(FileWriter output) {
		System.out.println("start writing inverted index");
		LinkedList<file> value;
		String word;
		String fileName;
		file item = new file();
		try {
			output.write("Word\t\t\t\t\t" + "DocName\t\t\t");
			output.write("\n");
			for (Map.Entry<String, LinkedList<file>> entry : invertIndex.entrySet()) {
				word = entry.getKey();
				value = entry.getValue();
				output.write(word + "\t\t\t\t\t");
				for(int i = 0; i < value.size(); i++) {
					item = value.get(i);
					fileName = item.getFile();
					output.write(fileName);
					if (i < value.size() - 1) {
						output.write(" => ");
					}
				}
				output.write("\n");	
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
	
	
	