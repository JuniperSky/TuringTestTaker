package Project;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class PhraseSeparator {
	private String phrase;
	private SentenceDetectorME sentenceDetector;
	private WhitespaceTokenizer tokenizer;
 
	public PhraseSeparator() {
		try {
			FileInputStream sdReference = new FileInputStream("C:\\Users\\soopa\\Desktop\\CMPU203\\Turing_Eclipse\\bin\\resources\\en-sent.bin");
			SentenceModel model = new SentenceModel(sdReference);
			sentenceDetector = new SentenceDetectorME(model);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
 
	public void addPhrase(String input) {
		this.phrase = input;
	}
 
	public String[] separateSentences() {
		String[] separatedSentences = sentenceDetector.sentDetect(phrase);
		return separatedSentences;
	}
 
	public String[] separateWords() {
		String[] separatedWords = tokenizer.tokenize(phrase);
		return separatedWords;
	}
}