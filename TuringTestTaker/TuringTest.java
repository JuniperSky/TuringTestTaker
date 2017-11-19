import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TuringTest extends JPanel implements ActionListener{
  
  //Constants for window size
  private static int WIDTH = 300;
  private static final int HEIGHT = 350;
  
  //private instance variables to be instantiated in the constructor
  private JPanel north, south; //Containers to hold the various components
  private JTextArea chatLog; //The chat history + computer response
  private JTextField getUserInput;  //The blank box where user types in text
  private JLabel status;  //Text that instructs user to type in JTextField
  private static JButton enterButton;  //User must click this button to submit text (not necessary, user just presses ENTER) 
  private JButton clearButton, quitButton;  //Miscellaneous buttons
  private static String title;  //Name of input file
  
  //MAIN ARGUMENT CLASS
  public static void main(String[] args){
    //JFrame is the window that will hold everything
    JFrame window = new JFrame("TuringTest");
    TuringTest tGUI = new TuringTest();
    
    //JFrame Settings
    window.setContentPane(tGUI);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLocation(120,70);
    window.setSize(WIDTH,HEIGHT);
    window.setVisible(true);
    //title = JOptionPane.showInputDialog("Please enter a file "+
                                        //"to read from and then\n"+
                                        //"press \"Process Input\" button: ");
    enterButton.setEnabled(true);
  }
  
  //TURING TEST GUI CLASS
  public TuringTest(){
    setBackground(Color.DARK_GRAY);
    
    //Create JPanel to hold Chatlog and Textbox
    north = new JPanel();
    
    //Chat Log Area Settings
    chatLog = new JTextArea("testing", 10, 15); //int #rows, int #columns
    chatLog.setBackground(Color.WHITE);
    chatLog.setLineWrap(true);
    chatLog.setWrapStyleWord(true);
    north.add(chatLog);
    
    //Add the JTextField to the Grid
    getUserInput = new JTextField(23);
    getUserInput.setEditable(true);
    getUserInput.addActionListener(this);
    north.add(getUserInput);
    
    //Add the JLabel right under the JTextField
    status = new JLabel("Test JLabel");
    status.setBackground(Color.WHITE);
    status.setForeground(Color.BLUE);
    status.setOpaque(true);
    north.add(status);
    
    //JPanel Grid Layout
    south = new JPanel();
    south.setBackground(Color.DARK_GRAY);
    south.setLayout(new GridLayout(3,1,2,2)); //(#Rows, #Columns, HGap, VGap)
    
    //Add enterButton to holder
    enterButton = new JButton("Enter Input");
    enterButton.addActionListener(this);
    enterButton.setEnabled(false);
    south.add(enterButton);
    
    //Add clearButton to Grid Layout
    clearButton = new JButton("Clear");
    clearButton.addActionListener(this);
    south.add(clearButton);
    
    //Add quitButton to Grid Layout
    quitButton = new JButton("Quit");
    quitButton.addActionListener(this);
    south.add(quitButton);
    
    //BorderLayout
    setLayout(new BorderLayout(2,2));
    south.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    
    //JScrollPane provides scroll bars for the JTextArea
    JScrollPane scroller = new JScrollPane(chatLog);
    
    //Add all the components to the JFrame
    add(scroller, BorderLayout.NORTH);
    add(north, BorderLayout.CENTER);
    add(south, BorderLayout.SOUTH);
  }
  
  
  
  //ACTION EVENT CLASS
  public void actionPerformed(ActionEvent e){
    if(e.getSource().equals(getUserInput)){  //What should happen when user types in text?
      String s = getUserInput.getText();
      userInput.takeInput(s);  //takeInput is a method in the userInput class

    }
    else if(e.getSource().equals(enterButton)){
      //addText();
    }
    else if(e.getSource().equals(clearButton)){
      //clearScreen();
    }
    else if(e.getSource().equals(quitButton)){
      System.exit(0);
    }
  }
  
//  //TRANSLATE TXT FILE CLASS
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
//    catch(){
//    }
//    
//  }
//  public void addText(){ //Probably delete
//  
//  }
//  public void clearScreen(){
//  
//  }
}