package edu.vassar.cs;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SentenceCategorizerTest {

  @Test(expected = NullPointerException.class)
  public void testCategorize(){
    SentenceCategorizer sc = new SentenceCategorizer();
    List<List<String>> lls = sc.categorize();
    assertNull(lls);
  }
}
