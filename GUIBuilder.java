package general;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GuiBuilder {
  private TuringTestGui gui;

  public GuiBuilder() {
    gui = new TuringTestGui();
  }
    
  public TuringTestGui getGui() {
    return gui;
  }

  /**
 * This thing builds a window.
 * Input: None
 * Output: None
 * 
 */
  public void buildWindow() {
    //The window that will hold everything.
    gui.window = new JFrame("TuringTest");
    //JFrame Settings
    gui.window.setContentPane(gui);
    gui.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.window.setLocation(120,70);
    gui.window.setSize(gui.getWidth(), gui.getHeight());
    gui.window.setVisible(true);
    //title = JOptionPane.showInputDialog("Please enter a file "+
    //"to read from and then\n"+
    //"press \"Process Input\" button: ");
  }

  public void buildNorth() {
    gui.north = new JPanel();
    gui.add(gui.north, BorderLayout.CENTER);
  }

  /**
 * This thing builds the chat log.
 */
  public void buildChatLog() {
    //I'm referring to this as "Chat Log 1" at the moment because
    //we may end up adding a second chat log for the second human.
    gui.chatLog1 = new JTextArea("Hello!",10, 15);
    gui.chatLog1.setBackground(Color.WHITE);
    gui.chatLog1.setLineWrap(true);
    gui.chatLog1.setWrapStyleWord(true);
    gui.north.add(gui.chatLog1);
  }

  /**
 * This things builds the status bar.
 */
  public void buildStatus() {
    gui.status = new JLabel("Test JLabel");
    gui.status.setBackground(Color.WHITE);
    gui.status.setForeground(Color.BLUE);
    gui.status.setOpaque(true);
    gui.north.add(gui.status);
  }

  /**
 * This thing builds the south window.
 */
  public void buildSouth() {
    //JPanel Grid Layout
    gui.south = new JPanel();
    gui.south.setBackground(Color.DARK_GRAY);
    gui.south.setLayout(new GridLayout(3,1,2,2)); //(#Rows, #Columns, HGap, VGap)
    gui.add(gui.south, BorderLayout.SOUTH);
    gui.south.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
  }

  /**
 * This thing makes an enter button.
 */
  public void buildEnterButton() {
    //Add enterButton to holder
    TuringTestGui.enterButton = new JButton("Enter Input");
    TuringTestGui.enterButton.setActionCommand("updateLog");
    TuringTestGui.enterButton.addActionListener(gui);
    TuringTestGui.enterButton.setEnabled(true);
    gui.south.add(TuringTestGui.enterButton);
  }

  /**
 * This builds and formats the clear button.
 */
  public void buildClearButton() {
    //Add clearButton to Grid Layout
    gui.clearButton = new JButton("Clear");
    gui.clearButton.addActionListener(gui);
    gui.south.add(gui.clearButton);
  }

  /**
 * This builds and formats the quit button.
 */
  public void buildQuitButton() {
    //Add quitButton to Grid Layout
    gui.quitButton = new JButton("Quit");
    gui.quitButton.addActionListener(gui);
    gui.south.add(gui.quitButton);
  }

  /**
 * This builds, formats, and adds the scroller.
 */
  public void buildScroller() {
    //JScrollPane provides scroll bars for the JTextArea
    JScrollPane scroller = new JScrollPane(gui.chatLog1);
    gui.add(scroller, BorderLayout.NORTH);
  }

  /**
 * This builds the bar where the user's input appears.
 */
  public void buildUserInput() {
    gui.getUserInput = new JTextField(23);
    gui.getUserInput.setEditable(true);
    gui.getUserInput.addActionListener(gui);
    gui.north.add(gui.getUserInput);
  }
}
