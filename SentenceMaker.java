package generalPackage;

import java.util.List;

public abstract class SentenceMaker {
	List<String> relevantWords;
	
	//This method gets the relevant words from the response assembler.
	public void getRelevantWords(List<String> words) {
		this.relevantWords = words;
	}
	
	//This method checks to see if any of the words we got from the response assembler were null (which is
	//possible if they weren't in the original document).
	public boolean nullWordCheck(List<String> list) {
		for (String word: list) {
			if (word.equals(null)) {
				return true;
			}
		}
		return false;
	}
	
	//And of course, the abstract method that will actually assemble the sentence.
	public abstract String assembleSentence();
}
