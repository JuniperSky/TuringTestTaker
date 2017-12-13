package Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUIBuilder {
    private TuringTestGUI gui;
    private static String logHistory = "Hello!";

    public GUIBuilder() {
    	gui = new TuringTestGUI();
    }
    
    public TuringTestGUI getGUI() {
        return gui;
    }

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

    public void buildChatLog() {
    	//I'm referring to this as "Chat Log 1" at the moment because
    	//we may end up adding a second chat log for the second human.
    	String textInScreen = getLogHistory();
    	gui.chatLog1 = new JTextArea(textInScreen, 10, 15);
    	gui.chatLog1.setBackground(Color.WHITE);
    	gui.chatLog1.setLineWrap(true);
    	gui.chatLog1.setWrapStyleWord(true);
    	gui.north.add(gui.chatLog1);
    }

    public void buildStatus() {
    	gui.status = new JLabel("Test JLabel");
    	gui.status.setBackground(Color.WHITE);
        gui.status.setForeground(Color.BLUE);
        gui.status.setOpaque(true);
        gui.north.add(gui.status);
    }

    public void buildSouth() {
    	//JPanel Grid Layout
    	gui.south = new JPanel();
    	gui.south.setBackground(Color.DARK_GRAY);
    	gui.south.setLayout(new GridLayout(3,1,2,2)); //(#Rows, #Columns, HGap, VGap)
    	gui.add(gui.south, BorderLayout.SOUTH);
    	gui.south.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    }

    public void buildEnterButton() {
    	//Add enterButton to holder
    	TuringTestGUI.enterButton = new JButton("Enter Input");
    	TuringTestGUI.enterButton.setActionCommand("updateLog");
    	TuringTestGUI.enterButton.addActionListener(gui);
    	TuringTestGUI.enterButton.setEnabled(true);  //Why is this needed? Shouldn't it always be enabled?
    	gui.south.add(TuringTestGUI.enterButton);
    }

    public void buildClearButton() {
    	//Add clearButton to Grid Layout
    	gui.clearButton = new JButton("Clear");
    	gui.clearButton.addActionListener(gui);
    	gui.south.add(gui.clearButton);
    }

    public void buildQuitButton() {
    	//Add quitButton to Grid Layout
    	gui.quitButton = new JButton("Quit");
    	gui.quitButton.addActionListener(gui);
    	gui.south.add(gui.quitButton);
    }

    public void buildScroller() {
    	//JScrollPane provides scroll bars for the JTextArea
    	JScrollPane scroller = new JScrollPane(gui.chatLog1);
    	gui.add(scroller, BorderLayout.NORTH);
    }

    public void buildUserInput() {
    	gui.getUserInput = new JTextField(23);
    	gui.getUserInput.setEditable(true);
    	gui.getUserInput.addActionListener(gui);
    	gui.north.add(gui.getUserInput);
    }
    
    public static String getLogHistory() {
        return logHistory;
     }
}
