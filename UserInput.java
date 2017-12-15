package general;

public abstract class UserInput {
  private PhraseProcessor processor;
  private String currentInput;

  public UserInput() {
    this.processor = new PhraseProcessor();
  }

  public abstract void takeInput(String input);

  public void sendInput() {
    this.processor.addPhrase(currentInput);
  }
}
