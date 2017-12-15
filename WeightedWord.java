package utilityPackage;

public class WeightedWord {
	
	private String word;
	private String partOfSpeech;
	private int weight;
	
	public WeightedWord(String word, int weight, String partOfSpeech) {
		this.word = word;
		this.weight = weight;
		this.partOfSpeech = partOfSpeech;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public String getPartOfSpeech() {
		return this.partOfSpeech;
	}
	
	public void changeWeight(int newWeight) {
		this.weight = newWeight;
	}
}
