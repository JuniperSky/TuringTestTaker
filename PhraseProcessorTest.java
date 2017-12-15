package edu.vassar.cs;

import static org.junit.Assert.*;

import org.junit.Test;

public class PhraseProcessorTest {

	@Test
	public void testAddPhrase() {
		PhraseProcessor pp = new PhraseProcessor();
		PhraseProcessor pp2 = new PhraseProcessor();
		String s = "Test";
		String t = "Quiz";
		
		pp.addPhrase(s);
		String u = pp.getPhrase();
		
		pp2.addPhrase(t);
		String v = pp2.getPhrase();
		assertNotSame(u, v);
	}
}
