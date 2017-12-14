
package Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TuringTestGUI extends JPanel implements ActionListener{
    //Constants for window size
    private static int width = 300;
    private static int height = 350;
  
    //private instance variables to be instantiated in the constructor
    public JPanel north, south; //Containers to hold the various components
    public JTextArea chatLog1; //The box that holds the chat history + computer response
    public JTextField getUserInput;  //The blank box where user types in text
    public JLabel status;  //Text that instructs user to type in JTextField
    public static JButton enterButton;  //User must click this button to submit text (not necessary, user just presses ENTER) 
    public JButton clearButton, quitButton;  //Miscellaneous buttons
    public JFrame window; //The window that will hold everything.
    private static String inputText;    //The user's text input
    private static String logHistory = "Hello!";	//The chat history
    
    //TypedInput instance
    private TypedInput ti = new TypedInput();
    
    
    
//    private UserInput userInput;
    
    public TuringTestGUI() {
    	setBackground(Color.DARK_GRAY);
    	setLayout(new BorderLayout(2,2));
    	
    }
    
    //The four method below change the size of the window that pops up
	public int getWidth() {
		return width;
	}
	public static void setWidth(int w) {
		width = w;
	}
	public int getHeight() {
		return height;
	}
	public static void setHeight(int h) {
		height = h;
	}

    //Action Event Method
    public void actionPerformed(ActionEvent e){
    	//If the action command e = "updateLog" (enter button is pressed)
    	if(e.getActionCommand().equals("updateLog")){
    		
    		inputText = getUserInput.getText();          	//Get the text from the JTextField and
            												//store the user input as a variable.
    		System.out.println(inputText);
    		
    		//Update the logHistory and display the updated logHistory in the JTextArea.
    		logHistory = logHistory + "\n" + inputText;
    		chatLog1.setText(logHistory); 
    		
    		//Send the latest logRecord to the TypedInput class
    		//The TypedInput class will analyze the user's input
    		this.ti.processInput(inputText);
    		
    		//Then, clear JTextField.
    		getUserInput.setText("");
    		
    		//Obtain the appropriate response from the PhraseProcessor class
    		//and then add it to logRecord
    		logHistory = logHistory + "\n" + this.ti.returnInput();;  
    		chatLog1.setText(logHistory); 
    		
    		
    	}
    	else if(e.getSource().equals(clearButton)){
    		chatLog1.setText("Hello!");
    		logHistory = "Hello!";
    	}
    	else if(e.getSource().equals(quitButton)){
    		System.exit(0);
    	}
    }
    
    


//  //TRANSLATE TXT FILE METHOD
//  //This method will be called by the action listener for JTextField
//  public void processInfo(){
//    //Where the content of the file will be stored as a String
//    String line;
//    //Where the content of the file will be stored as an edited array
//    String[] token;
//    //BufferedReader reads the input file
//    BufferedReader fileIn = null;
//    //try-catch that instantiates BufferedReader to read the file
//    try{
//      fileIn = new BufferedReader(new FileReader(title));
//    }
//    catch(IOException ioe){
//      title = JOptionPane.showInputDialog("Please enter a file" +
//                                          "to read from and then \n" +
//                                          "press \"Process Input\" button:");
//    }
//    //try-catch (loop?)
//    try{
//      while((line = fileIn.readLine()) != null){
//        //Replaces non-letters with empty String and splits String into
//        //array based on whitespace between words
//        token = line.replaceAll("[^a-zA-Z' ]", "").split("\\s+");
//        
//        
//      }
//    }
//    catch(){}
//    
//  }
//  public void addText(){ //Probably delete
//  
//  }
//  public void clearScreen(){
//  
//  }
}
