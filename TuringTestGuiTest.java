package edu.vassar.cs;
import edu.vassar.cs.TuringTestGui;

import static org.junit.Assert.*;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TuringTestGuiTest{
	
	@Test
	public void testGetWidth() {
		TuringTestGui gui1 = new TuringTestGui();
	    int n = 500;
	    gui1.setWidth(n);
	    int a = gui1.getWidth();
	    
	    TuringTestGui gui2 = new TuringTestGui();
	    int m = 500;
	    gui2.setWidth(m);
	    int b = gui2.getWidth();
	    
	    assertEquals(a, b);
	}

	@Test
	public void testGetHeight() {
		TuringTestGui gui1 = new TuringTestGui();
	    TuringTestGui gui2 = new TuringTestGui();
		int n = 700;
		int m = 900;
		
	    gui1.setHeight(n);
	    gui2.setHeight(m);
	    
	    int a = gui1.getHeight();
	    int b = gui2.getHeight();
	    
	    assertNotSame(a, b);
	}

}
