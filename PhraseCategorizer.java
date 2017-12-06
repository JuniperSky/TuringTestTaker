package generalPackage;

import java.util.List;

public abstract class PhraseCategorizer {
	protected String phrase;
	protected List<String> list;
	
	public void addPhrase(String phrase) {
		this.phrase = phrase;
	}
	public abstract void getList();
	public abstract List<String> categorize();
}
