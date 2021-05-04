/*
 * Trisha Espejo
 * Phase 3
 * Due: 5/11/21
 */
public class createStem {

	public String generate(String w) {
		char[] a = new char[501];
		String word = w;
	     Stemming s = new Stemming();
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
