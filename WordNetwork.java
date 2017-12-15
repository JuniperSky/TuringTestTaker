package utilityPackage;

import java.util.ArrayList;
import java.util.List;

public class WordNetwork {
	private String keyWord;
	private List<WeightedWord> associationList;
	
	public String getKeyWord() {
		return this.keyWord;
	}
	
	public List<WeightedWord> getAssociations() {
		return this.associationList;
	}
	
	public WordNetwork(String word) {
		this.keyWord = word;
		this.associationList = new ArrayList<WeightedWord>();
	}
	
	public boolean contains(String word) {
		boolean hasWord = false;
		for (WeightedWord currentWord: associationList) {
			if (currentWord.getWord().equals(word)) {
				hasWord = true;
			}
		}
		return hasWord;
	}
	
	public WeightedWord findWord(String word) {
		for (WeightedWord currentWord: associationList) {
			if (currentWord.getWord().equals(word)) {
				return currentWord;
			}
		}
		return null;
	}
}
