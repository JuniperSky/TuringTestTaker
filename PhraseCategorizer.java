package edu.vassar.cs;

import java.util.List;

public abstract class PhraseCategorizer {
  protected String phrase;
  protected PhraseSeparator separator;

  //This just updates the phrase variable.
  public void addPhrase(String phrase) {
    this.phrase = phrase;
  }

  //Categorize depends on whether it's a sentence or word categorizer.
  /**
 * @return A list of lists of strings, representing the parts of the
 *     phrase sorted into categories.
 */
  public abstract List<List<String>> categorize();
}