package edu.vassar.cs;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class WordNetworkTest {

  @Test
  public void testWordNetwork() {

    String s = "TestA";
    String t = "topic";
    double d = 1.0;
    String pos = "N";

    WeightedWord ww = new WeightedWord(t,d,pos);
    WordNetwork wnt = new WordNetwork(s);

    String n = wnt.getKeyWord();
    List<WeightedWord> a = wnt.getAssociations();

    assertSame(s, n);
    assertNotSame(a, ww);
  }

  @Test
  public void testContains() {
    String s = "Weather";
    WordNetwork wnt = new WordNetwork(s);
    Boolean f = wnt.contains(s);

    assertFalse(f);
  }

  @Test
  public void testFindWord() {
    String s = "Computer";
    WordNetwork wnt = new WordNetwork(s);
    WeightedWord ww = wnt.findWord(s);
    assertNull(ww);
  }
}
