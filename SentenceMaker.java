package generalPackage;

import java.util.List;

public abstract class SentenceMaker {
	List<String> relevantWords;
	
	public void getRelevantWords(List<String> words) {
		this.relevantWords = words;
	}
	
	public abstract String assembleSentence();
}
