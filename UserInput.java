package Project;
public abstract class UserInput {
	private PhraseProcessor processor;
    private String currentInput;

    public UserInput(PhraseProcessor processor) {
    	this.processor = processor;
    }

    public abstract takeInput();

    public void sendInput() {
    	this.processor.getPhrase(currentInput);
    }
}

