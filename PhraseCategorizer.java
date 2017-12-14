package Project;
import java.util.List;

public abstract class PhraseCategorizer {
	protected String phrase;
	protected PhraseSeparator separator;
 
	public void addPhrase(String phrase) {
		this.phrase = phrase;
	}
 
	public abstract List<List<String>> categorize();
}