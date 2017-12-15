package edu.vassar.cs;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.List;

import org.junit.Test;

public class PhraseSeparatorTest {

	@Test
	public void testSeparateSentences() {
		PhraseSeparator ps = new PhraseSeparator();
		String s = "It was the best of times. It was the worst of times. Who says that?";
		ps.addPhrase(s);
		String[] ss = ps.separateSentences();
		
		String[] ex = new String[3];
		ex[0] = "It was the best of times.";
		ex[1] = "It was the worst of times.";
		ex[2] = "Who says that?";
		
		assertNotSame(ex, ss);
	}

	@Test
	public void testSeparateWords() {
		PhraseSeparator ps = new PhraseSeparator();
		String s = "The wolf jumped over the log.";
		ps.addPhrase(s);
		String[] sw = ps.separateWords();
		
		String[] ex = new String[6];
		ex[0] = "This";
		ex[1] = "new";
		ex[2] = "microwave";
		ex[3] = "is";
		ex[4] = "really";
		ex[5] = "red";
		
		assertNotSame(ex, sw);
	}

}
