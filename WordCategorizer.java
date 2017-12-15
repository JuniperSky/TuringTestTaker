package general;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class WordCategorizer extends PhraseCategorizer {

  POSTaggerME tagger;

  /**
 * This construct the categorizer, including its tagger.
 */
  public WordCategorizer() {
    this.separator = new PhraseSeparator();
    try {
      FileInputStream taggerReference = 
          new FileInputStream("C:\\OpenNLP_models\\en-pos-maxent.bin");
      POSModel model = new POSModel(taggerReference);
      this.tagger = new POSTaggerME(model);
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
 * @see general.PhraseCategorizer#categorize()
 */
  public List<List<String>> categorize() {
    List<List<String>> categories = new ArrayList<List<String>>();
    List<String> nouns = new ArrayList<String>();
    categories.add(nouns);
    List<String> verbs = new ArrayList<String>();
    categories.add(verbs);
    List<String> adjectives = new ArrayList<String>();
    categories.add(adjectives);
    List<String> determiners = new ArrayList<String>();
    categories.add(determiners);
    List<String> prepositions = new ArrayList<String>();
    categories.add(prepositions);
    separator.addPhrase(phrase);
    String[] separatedWords = separator.separateWords();
    String[] taggedWords = tagger.tag(separatedWords);
    for (int i = 0; i < taggedWords.length; i++) {
      String currentWord = taggedWords[i];
      char indicator = currentWord.charAt(currentWord.length() - 2);
      String insertedWord = separatedWords[i];
      if (indicator == 'N') {
        categories.get(0).add(insertedWord);
      } else if ((indicator == 'V') || (indicator == 'B')) {
        categories.get(1).add(insertedWord);
      } else if (indicator == 'J') {
        categories.get(2).add(insertedWord);
      } else if (indicator == 'D') {
        categories.get(3).add(insertedWord);
      } else if (indicator == 'I') {
        categories.get(4).add(insertedWord);
      }
    }
    return categories;
  }
}
