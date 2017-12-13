package Project;
import java.util.List;

public class ResponseAssembler {
	private List<List<String>> inputWords;
	private List<List<String>> inputSentences;
	private SentenceMaker sentenceMaker;
	private String wordAssociations;
 
	public ResponseAssembler() {
  
	}
 
	public void addInputWords(List<List<String>> relevantWords) {
		this.inputWords = relevantWords;
	}
 
	public void addInputSentences(List<List<String>> sentenceTypes) {
		this.inputSentences = sentenceTypes;
	}
 
	public void determineSentenceType() {
		if (inputSentences.get(1).isEmpty()) {
   
		}
	}
	public void getRelevantWords() {
  
	}
 
	public String assembleSentence() {
		return sentenceMaker.assembleSentence();
	}
}
