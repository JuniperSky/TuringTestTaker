package general;

import java.util.ArrayList;
import java.util.List;

public class SentenceCategorizer extends PhraseCategorizer {

  public SentenceCategorizer() {
    //The constructor just initializes a phrase separator.
    this.separator = new PhraseSeparator();
  }


  /**
 * @see general.PhraseCategorizer#categorize()
 */
  public List<List<String>> categorize() {
    //Create a new list, of lists, of strings, to hold the sentences and their categories.
    List<List<String>> categories = new ArrayList<List<String>>();
    //Creates and adds in two lists, one for statements, and one for categories.
    List<String> statements = new ArrayList<String>();
    categories.add(statements);
    List<String> questions = new ArrayList<String>();
    categories.add(questions);
    separator.addPhrase(phrase);
    String[] separatedSentences = separator.separateSentences();
    for (String sentence: separatedSentences) {
      int length = sentence.length();
      char punctuation = sentence.charAt(length - 1);
      if (punctuation == '?') {
        categories.get(1).add(sentence);
      } else {
        categories.get(0).add(sentence);
      }
    }
    return categories;
  }
}
