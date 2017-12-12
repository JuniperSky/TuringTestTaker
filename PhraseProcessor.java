package generalPackage;

import java.util.List;

public class PhraseProcessor {
	private String phrase;
	private String dictionary;
	protected SentenceCategorizer sentenceCategorizer;
	protected WordCategorizer wordCategorizer;
	protected ResponseAssembler assembler;
	
	public PhraseProcessor() {
		this.sentenceCategorizer = new SentenceCategorizer();
		this.wordCategorizer = new WordCategorizer();
		this.assembler = new ResponseAssembler();
	}
	
	public void addPhrase(String phrase) {
		this.phrase = phrase;
	}
	public List<List<String>> getSentenceType() {
		sentenceCategorizer.addPhrase(phrase);
		return sentenceCategorizer.categorize();
	}
	
	public List<List<String>> getRelevantWords() {
		wordCategorizer.addPhrase(phrase);
		return wordCategorizer.categorize();
	}
	
	public String getResponse() {
		List<List<String>> relevantWords = getRelevantWords();
		List<List<String>> sentenceTypes = getSentenceType();
		assembler.addInputWords(relevantWords);
		assembler.addInputSentences(sentenceTypes);
		assembler.getRelevantWords();
		assembler.determineSentenceType();
		return assembler.assembleSentence();
	}
}
