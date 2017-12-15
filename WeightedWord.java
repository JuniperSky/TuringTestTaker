package utilityPackage;

public class WeightedWord {
	
	private String word;
	private String partOfSpeech;
	private double weight;
	
	public WeightedWord(String word, double weight, String partOfSpeech) {
		this.word = word;
		this.weight = weight;
		this.partOfSpeech = partOfSpeech;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public String getPartOfSpeech() {
		return this.partOfSpeech;
	}
	
	public void changeWeight(double newWeight) {
		this.weight = newWeight;
	}
}
