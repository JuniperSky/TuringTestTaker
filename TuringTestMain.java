package Project;
import javax.swing.*;
import java.awt.*;
import java.io.*;

//Contains the Main Method
public class TuringTestMain {
    
    private static GUIBuilder guiBuilder;
    private static TuringTestGUI gui;
    
    public static void main(String[] args) {
        guiBuilder = new GUIBuilder();
        gui = guiBuilder.getGUI();
        
        guiBuilder.buildNorth();
        guiBuilder.buildSouth();
        guiBuilder.buildChatLog();
        guiBuilder.buildUserInput();
        guiBuilder.buildStatus();
        guiBuilder.buildEnterButton();
        guiBuilder.buildClearButton();
        guiBuilder.buildQuitButton();
        guiBuilder.buildScroller();
        
        guiBuilder.buildWindow();
    }
    
}
