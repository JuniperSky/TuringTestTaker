package edu.vassar.cs;

import static org.junit.Assert.*;

import org.junit.Test;

public class GuiBuilderTest {

	@Test
	public void testGetGui() {
		GuiBuilder g = new GuiBuilder();
		
		assertNotNull(g.getGui());
	}

}
