package edu.vassar.cs;

import java.io.IOException;

public class TypedInput {
  private PhraseProcessor processor;
  private String currentInput; //The input text
  private String response = ""; //The response text

  public TypedInput() {
    processor = new PhraseProcessor();
  }
    
  /**
 * @param phrase  The phrase the user typed in.
 * @throws IOException The text file will be used by some related classes,
 *     provided it's there.
 */
  public void processInput(String phrase) throws IOException {
    //The text input from the user is obtained from the TuringTestGUI class
    currentInput = phrase;
    this.processor.addPhrase(currentInput);
    response = this.processor.getResponse();
  }
    
  public String returnInput() {
    return response;
  }
}