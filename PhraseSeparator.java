package general;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class PhraseSeparator {
  //The phrase the user typed in:
  private String phrase;
  //The sentence detector, as provided by Open NLP:
  private SentenceDetectorME sentenceDetector;
  //The tokenizer, for separating words:
  private WhitespaceTokenizer tokenizer;

  /**
 * This is the constructor for the phrase separator. It uses an external model.
 */
  public PhraseSeparator() {
    //In order to use the sentence detector, it needs to get the proper Open NLP model, from 
    //where it happens to be right now.
    try {
      FileInputStream sdReference = new FileInputStream("C:\\OpenNLP_models\\en-sent.bin");
      SentenceModel model = new SentenceModel(sdReference);
      sentenceDetector = new SentenceDetectorME(model);
      tokenizer = WhitespaceTokenizer.INSTANCE;
    //And some exception handling, in case we couldn't find the model.
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void addPhrase(String input) {
    //Updates the phrase.
    this.phrase = input;
  }

  /**
   * This separates the sentences so they can be categorized.
 * @return This returns an array of strings, separated by sentence.
 */
  public String[] separateSentences() {
    //Separates the sentences into an array, preserving the punctuation.
    String[] separatedSentences = sentenceDetector.sentDetect(phrase);
    return separatedSentences;
  }

  /**
   * This separates words so they can be categorized.
 * @return This returns an array of strings, each one a single word.
 */
  public String[] separateWords() {
    //Separates the words into an array.
    String[] separatedWords = tokenizer.tokenize(phrase);
    //But that's not all! We need to remove capital letters and punctuation!
    //That's what this loop is for.
    int length = separatedWords.length;
    for (int i = 0; i < length; i++) {
      String currentWord = separatedWords[i];
      //This is the operation that actually formats the word:
      String cleanedWord = currentWord.replaceAll("[^a-zA-Z ]", "").toLowerCase();
      separatedWords[i] = cleanedWord;
    }
    return separatedWords;
  }
}
