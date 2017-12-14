package Project;

public class TypedInput{
	private PhraseProcessor processor;
    private String currentInput;		//The input text
    private String response = "";			//The response text

    public TypedInput() {
    	processor = new PhraseProcessor();
    }
    
    public void processInput(String phrase) {
		//The text input from the user is obtained from the TuringTestGUI class
		currentInput = phrase;
		
        
		this.processor.addPhrase(currentInput);
		
		this.processor.displayPhrase();
		
		
		//this.processor.getSentenceType();
		//this.processor.getRelevantWords();
		//this.processor.getResponse();
	}
	
	//public void sendInput() {
		//The text input is sent to the PhraseProcessor class
    	//this.processor.addPhrase(currentInput);
    	
    //}
    
    public String returnInput() {
    	return response;
    }

}
