package general;

//Contains the Main Method
public class TuringTestMain {

  private static GuiBuilder guiBuilder;
  private static TuringTestGui gui;

  /**
 * @param args This is the main method.
 */
  public static void main(String[] args) {
    guiBuilder = new GuiBuilder();
    gui = guiBuilder.getGui();

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
