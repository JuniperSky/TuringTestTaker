package generalPackage;

import java.util.List;

public class PhraseProcessor {
	private String phrase;
	private String dictionary;
	protected SentenceCategorizer sentenceCategorizer;
	protected WordCategorizer wordCategorizer;
	protected ResponseAssembler assembler;
	
	public void getPhrase(String phrase) {
		this.phrase = phrase;
	}
	public List<String> getSentenceType() {
		
		return sentenceCategorizer.categorize();
	}
}
