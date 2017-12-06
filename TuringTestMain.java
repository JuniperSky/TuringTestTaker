import javax.swing.*;
import java.awt.*;
import java.io.*;


public class TuringTestTaker {
    
    private static GUIBuilder guiBuilder;
    private static TuringTestGUI gui;
    
    public static void main(String[] args) {
        ProjectGUI.guiBuilder = new GUIBuilder();
        ProjectGUI.gui = guiBuilder.getGUI();
        
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
