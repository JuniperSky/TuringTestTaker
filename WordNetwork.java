package utility;

import java.util.ArrayList;
import java.util.List;

public class WordNetwork {
  private String keyWord;
  private List<WeightedWord> associationList;

  public String getKeyWord() {
    return this.keyWord;
  }

  public List<WeightedWord> getAssociations() {
    return this.associationList;
  }

  public WordNetwork(String word) {
    this.keyWord = word;
    this.associationList = new ArrayList<WeightedWord>();
  }

  /**
   * Does the association list have the given word?
 * @param word Does it have it or not!?
 * @return Return the veracity of this.
 */
  public boolean contains(String word) {
    boolean hasWord = false;
    for (WeightedWord currentWord: associationList) {
      if (currentWord.getWord().equals(word)) {
        hasWord = true;
      }
    }
    return hasWord;
  }

  /** This finds a given word within a network.
 * @param word Find a weighted word with this as the base word.
 * @return Return the specific base word.
 */
  public WeightedWord findWord(String word) {
    for (WeightedWord currentWord: associationList) {
      if (currentWord.getWord().equals(word)) {
        return currentWord;
      }
    }
    return null;
  }
}
