package edu.vassar.cs;

import java.io.IOException;
import java.util.List;

public class PhraseProcessor {
  //The phrase processor's main purpose is to make method calls to more
  //specialized classes and transfer information between them.

  //Phrase is what the user typed in.
  private String phrase;
  protected SentenceCategorizer sentenceCategorizer;
  protected WordCategorizer wordCategorizer;
  protected ResponseAssembler assembler;
  
  
  /**
 * This is a phase processor constructor.
 */
  public PhraseProcessor() {
    //Initialize the different class variables...
    this.sentenceCategorizer = new SentenceCategorizer();
    this.wordCategorizer = new WordCategorizer();
    this.assembler = new
    ResponseAssembler("C:\\Users\\soopa\\Desktop\\CMPU203\\turing\\src\\main\\java\\edu\\vassar\\cs\\Dictionary.txt");
  }

  public void addPhrase(String phrase) {
    //This just updates the phrase, presumably with what the user typed.
    this.phrase = phrase;
  }

  /**
 * This finds out the types of sentences the user used.
 * @return This returns a list of lists representing the types of sentences
 *     in the input.
 */
  public List<List<String>> getSentenceType() {
    //Updates the sentence categorizer and gets the statement and question
    //lists from it.
    sentenceCategorizer.addPhrase(phrase);
    return sentenceCategorizer.categorize();
  }

  /** This is the get relevant words method.
 * This gets the words we need for the outputted sentence.
 * @return This returns a list of lists of strings representing the words the
 *     user typed in, and what parts of speech they are.
 */
  public List<List<String>> getRelevantWords() {
    //Updates the word categorizer and gets the lists of nouns, verbs, adjectives,
    //and determiners from it, respectively.
    wordCategorizer.addPhrase(phrase);
    return wordCategorizer.categorize();
  }

  /**
 * This gets the responding sentence.
 * @throws IOException When the text file the response assembler needs isn't there.
 */
  public String getResponse() throws IOException {
    //Gets the lists of relevant words...
    List<List<String>> relevantWords = getRelevantWords();
    //Gets the lists of statements and questions...
    List<List<String>> sentenceTypes = getSentenceType();
    //Gives the lists of lists to the assembler...
    assembler.addInputWords(relevantWords);
    assembler.addInputSentences(sentenceTypes);
    //Instructs the assembler to use its primary functions...
    assembler.getRelevantWords();
    assembler.determineSentenceType();
    //And finally gets the finished sentence, ready to be outputted.
    return assembler.assembleSentence();
  }
  public String getPhrase() {
    return phrase;
  }
}

