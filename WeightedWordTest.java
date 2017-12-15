package edu.vassar.cs;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeightedWordTest {

	@Test
	public void testGetWeight() {
		String a = "Apple";
		Double d1 = 0.99;
		String c = "N";
		Boolean b = false;
		
		WeightedWord ww = new WeightedWord(a, d1, c);
		Double d2 = ww.getWeight();
		
		int i = Double.compare(d1, d2);
		if(i == 0) {
			b = true;
		}
		assertTrue(b);
	}

	@Test
	public void testGetPartOfSpeech() {
		String a = "Apple";
		Double d = 0.99;
		String c = "N";
		
		WeightedWord ww = new WeightedWord(a, d, c);
		String s = ww.getPartOfSpeech();
		
		assertSame(s, c);
	}

	@Test
	public void testChangeWeight() {
		String a = "Apple";
		Double d1 = 0.99;
		String c = "N";
		Double d2 = 0.5;
		Boolean b = true;
		
		WeightedWord ww = new WeightedWord(a, d1, c);
		
		ww.changeWeight(d2);
		Double d3 = ww.getWeight();
		
		if(d1 != d3) {
			b = false;
		}
		
		assertFalse(b);
	}
}
