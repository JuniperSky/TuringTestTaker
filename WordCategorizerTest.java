package edu.vassar.cs;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

public class WordCategorizerTest {

  @Test(expected = NullPointerException.class)
  public void testCategorize() {
    WordCategorizer wc = new WordCategorizer();
    List<List<String>> lls = wc.categorize();
    assertNull(lls);
  }
}
