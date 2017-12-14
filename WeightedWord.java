package utilityPackage;

public class WeightedWord {
	
	private String word;
	private int weight;
	
	public WeightedWord(String word, int weight) {
		this.word = word;
		this.weight = weight;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public void changeWeight(int newWeight) {
		this.weight = newWeight;
	}
}
