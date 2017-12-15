package edu.vassar.cs;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TypedInputTest {

  @Test(expected = NullPointerException.class)
  public void testProcessInput() throws IOException {
    TypedInput ti = new TypedInput();
    String s = "Vassar is a college.";
    ti.processInput(s);

    PhraseProcessor pp = new PhraseProcessor();
    String a = pp.getResponse();
    String b = ti.returnInput();

    assertSame(a,b);
  }
}
