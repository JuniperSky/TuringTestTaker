package generalPackage;

import java.util.ArrayList;
import java.util.List;

public class SentenceCategorizer extends PhraseCategorizer {
	
	public SentenceCategorizer() {
		this.separator = new PhraseSeparator();
	}
	
	public List<List<String>> categorize() {
		List<List<String>> categories = new ArrayList<List<String>>();
		List<String> statements = new ArrayList<String>();
		statements.add("Statements");
		categories.add(statements);
		List<String> questions = new ArrayList<String>();
		questions.add("Questions");
		categories.add(questions);
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
