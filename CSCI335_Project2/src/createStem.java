/*
 * Trisha Espejo
 * Phase 2
 * Due: 4/20/2021
 */
public class createStem {

	public String generate(String w) {
		char[] a = new char[501];
		String word = w;
	     Stemmer s = new Stemmer();
	     char c ;
		for (int i = 0; i < word.length(); i++) {
			c = word.charAt(i);
			s.add(c);
		}
		s.stem();
		word = s.toString();
		return word;
	}

}
