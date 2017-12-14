package Project;
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
	
	//Add the input phrase to this class's phrase
	public void addPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	public String displayPhrase() {
		return phrase;
	}
	
	//Run the newly added phrase through the sentenceCategorizer method in the SentenceCategorizer class
	//and return what type of sentence it is
	public List<List<String>> getSentenceType() {
		sentenceCategorizer.addPhrase(phrase);
		return sentenceCategorizer.categorize();
	}
 
	//Run the newly added phrase through the wordCategorizer method in the WordCategorizer class
	//and categorize the words in the phrase based on types of speech
	public List<List<String>> getRelevantWords() {
		wordCategorizer.addPhrase(phrase);
		return wordCategorizer.categorize();
	}
 
	//Using all the collected information, return the appropriate response
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
