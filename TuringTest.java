import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TuringTest extends JFrame implements ActionListener{
  
  //Constants for window size
  private static int WIDTH = 300;
  private static final int HEIGHT = 300;
  
  //Constants for text entry boxes
  private static final int BOX_Y = 80;
  private static final int BOX_SIZE = 50;
  
  //private instance variables to be instantiated in the constructor
  private JTextArea textInput; //The chat history + computer response
  private Container holder; //Will hold getUserInput, userInput, and enterButton
  private JTextField getUserInput;  //The blank box where user types in text
  private JLabel status;  //Text that instructs user to type in JTextField
  private static JButton enterButton;  //User must click this button to submit text (not necessary, user just presses ENTER) 
  private JButton clearButton, quitButton;  //Miscellaneous buttons
  private static String title;  //Name of input file
  
  public static void main(String[] args){
    //JFrame is the window that will hold everything
    JFrame window = new JFrame("TuringTest");
    TuringTestGUI tGUI = new TuringTestGUI();
    
    //JFrame Settings
    window.setContentPane(content);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLocation(120,70);
    window.setSize(300,350);
    window.setVisible(true);
    title = JOptionPane.showInputDialog("Please enter a file "+
                                        "to read from and then\n"+
                                        "press \"Process Input\" button: ");
    enterButton.setEnabled(true);
  }
  
  public void TuringTestGUI(){
    setBackground(Color.DARK_GRAY);
    
    //Input Area Settings
    textInput = new JTextArea();
    textInput.setBackground(Color.WHITE);
    textInput.setLineWrap(true);
    textInput.setWrapStyleWord(true);
    
    //JPanel Grid Layout
    JPanel south = new JPanel();
    south.setBackground(Color.DARK_GRAY);
    south.setLayout(new GridLayout(3,1,2,2)); //(#Rows, #Columns, HGap, VGap)
    
    //Add holder to the Grid Layout
    holder = this.getContentPane();
    holder.setLayout(null);
    holder.setBackground(Color.white);
    south.add(holder);
    
    //Add the JTextField to holder
    getUserInput = new JTextField("");
    getUserInput.setBounds(50, BOX_Y, BOX_SIZE, BOX_SIZE); //(x,y,width,height)
    getUserInput.setEditable(true);
    getUserInput.addActionListener(this);
    holder.add(getUserInput);
    
    //Add the JLabel right under the JTextField
    status = new JLabel();
    status.setBounds();
    holder.add(status);
    
    //Add enterButton to holder
    enterButton = new JButton("Enter Input");
    enterButton.addActionListener(this);
    enterButton.setEnabled(false);
    holder.add(enterButton);
    
    //Add clearButton to Grid Layout
    clearButton = new JButton("Quit");
    clearButton.setBounds(109,200,75,30);
    clearButton.addActionListener(this);
    south.add(clearButton);
    
    //Add quitButton to Grid Layout
    quitButton = new JButton("Quit");
    quitButton.setBounds(109,200,75,30);
    quitButton.addActionListener(this);
    south.add(quitButton);
    
    //BorderLayout
    setLayout(new BorderLayout(2,2));
    setBorder(BorderFactory,createLineBorder(Color.DARK_GRAY));
    
    //JScrollPane provides scroll bars for the JTextArea
    JScrollPane scroller = new JScrollPane(textInput);
    add(scroller, BorderLayout.CENTER);
    add(south, BorderLayout.SOUTH);
  }
  
  public void actionPerformed(ActionEvent e){
    if(e.getSource().equals(getUserInput)){  //What should happen when user types in text?
      processInfo();
    }
    else if(e.getSource().equals(enterButton)){
      addText();
    }
    else if(e.getSource().equals(clearButton)){
      clearScreen();
    }
    else if(e.getSource().equals(quitButton)){
      System.exit(0);
    }
  }
  
  
  
}