package edu.vassar.cs;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import static org.junit.Assert.*;

public class TuringTestInteractionTest {

  @Rule
  public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

  @Test
  public void doesTuringTestWork() {
    String[] args = {""};
    TuringTestMain.main(args);

    assertEquals(systemOutRule.getLog(), "");

  }
}