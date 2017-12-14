package Project;
import java.util.ArrayList;
import java.util.List;

public class SentenceCategorizer extends PhraseCategorizer {
 
	public SentenceCategorizer() {
		this.separator = new PhraseSeparator();
	}
 
	public List<List<String>> categorize() {
		//categories is a list of lists of String
		List<List<String>> categories = new ArrayList<List<String>>();
		
		//statements is a list of Strings
		List<String> statements = new ArrayList<String>();
		//Add String "Statements" to the list of Strings 
		statements.add("Statements");
		//Add the list of Strings to the list of lists of Strings
		categories.add(statements);
		
		//questions is a list of Strings
		List<String> questions = new ArrayList<String>();
		//Add String "Questions" to the list of Strings
		questions.add("Questions");
		//Add the list of Strings to the list of lists of Strings
		categories.add(questions);
		
		//Determine if the input text is a statement or a question using the
		//PhraseSeparator class
		separator.addPhrase(phrase);
		String[] separatedSentences = separator.separateSentences();
		for(String sentence: separatedSentences) {
			int length = sentence.length();
			char punctuation = sentence.charAt(length - 1);
			if (punctuation == '?') {
				categories.get(1).add(sentence);
			} else {
				categories.get(0).add(sentence);
			}
		}
		return categories;
	}
 
 
}