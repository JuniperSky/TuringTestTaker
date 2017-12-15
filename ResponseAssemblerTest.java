package edu.vassar.cs;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResponseAssemblerTest {

	@Test
	public void testFillUpWordNetwork() {
		
		
		String filename = "file";
		ResponseAssembler ra = new ResponseAssembler(filename);
		String s = "test";
		WordNetwork wn = new WordNetwork(s);
		
		ra.fillUpWordNetwork(wn);
		
		
		
	}

}
