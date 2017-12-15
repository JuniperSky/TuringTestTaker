package generalPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.tokenize.WhitespaceTokenizer;
import utilityPackage.*;

public class ResponseAssembler {
	//The categorized input words we got from the phrase processor:
	private List<List<String>> inputWords;
	//The categorized sentences we got from the phrase processor:
	private List<List<String>> inputSentences;
	//The sentence maker (which can be a statement or question maker; we'll decide later):
	private SentenceMaker sentenceMaker;
	//The title of the document we'll be getting our word associations from:
	private String wordAssociations;
	//The variable where we'll store the words for the response:
	private List<String> responseWords;
	
	//The file itself:
	private File textFile;
	//The network of word associations gained from the file:
	private List<WordNetwork> greaterNetwork;
	
	//A tokenizer, for separating out the lines from the file:
	private WhitespaceTokenizer tokenizer;
	
	public ResponseAssembler(String fileName) {
		//This just initializes the file...
		this.wordAssociations = fileName;
		this.textFile = new File(wordAssociations);
	}
	
	public void addInputWords(List<List<String>> relevantWords) {
		//Get the relevant words from the phrase processor...
		this.inputWords = relevantWords;
	}
	
	public void addInputSentences(List<List<String>> sentenceTypes) {
		//Get the relevant sentences from the phrase processor...
		this.inputSentences = sentenceTypes;
	}
	
	public void newFile(String name) {
		//In case we want to change what file we're using, at some point.
		this.wordAssociations = name;
	}
	
	public void determineSentenceType() {
		//The "1st" index of the list of lists of sentences holds the
		//questions. If the user asked no questions, we'll ask one ourselves.
		if (inputSentences.get(1).isEmpty()) {
			sentenceMaker = new QuestionMaker();
		//Otherwise, we'll make a statement in response to a question.
		} else {
			sentenceMaker = new StatementMaker();
		}
	}
	
	public void formGreaterNetwork() throws IOException {
		//This method takes all the information in the text file and puts it into a more
		//convenient list of lists of weighted words (where each list is also associated
		//with a central "keyword" to define its category).
		this.greaterNetwork = new ArrayList<WordNetwork>();
		//Make the file reader...
		FileReader tempFileReader = new FileReader(textFile);
		BufferedReader bufferedReader = new BufferedReader(tempFileReader);
		//For every line we read...
		for (String currentLine = bufferedReader.readLine(); 
				!currentLine.isEmpty(); 
				currentLine = bufferedReader.readLine()) {
			//If we find the special *** line, it indicates a new set of words to examine.
			if(currentLine.equals("***")) {
				//Get the main word associated with the new category.
				String newWord = bufferedReader.readLine();
				//Create a new sub-network to represent a category of words.
				WordNetwork newNetwork = new WordNetwork(newWord);
				//And add it to the whole.
				greaterNetwork.add(newNetwork);
			} else {
				//This part is for when we're within a word category, and adding the associated words.
				//First, we divide the line into the word itself, the weight it gets, and the part of
				//speech it's labeled as.
				String[] splitLine =  tokenizer.tokenize(currentLine);
				//We assemble a new instance of the weighted word class from the tokens...
				WeightedWord newWeightedWord = new WeightedWord(splitLine[0], 
						Integer.parseInt(splitLine[1]),	splitLine[2]);
				//We check the length because we want to insert this into our most recent category.
				int length = greaterNetwork.size();
				//And now the actual insertion.
				greaterNetwork.get(length - 1).getAssociations().add(newWeightedWord);
			}
		}
		bufferedReader.close();
	}
	
	public void fillUpWordNetwork(WordNetwork wordNetwork) {
		//This method fills up a word category with any words the user used that it doesn't have.
		//Iterate over all the user's words...
		for (int j = 0; j < 4; j++) {
			List<String> currentCategory = inputWords.get(j);
			for (String examinedWord: currentCategory) {
				//Check to see if we have them or not...
				if (!wordNetwork.contains(examinedWord)) {
					//Determine what part of speech we should give it (based on what part of the list it was
					//in; nouns are index 0, verbs are in 1, adjectives are in 2, and determiners are in 3)
					String partOfSpeech = null;
					switch(j) {
					case 0: partOfSpeech = "N";
					break;
					case 1: partOfSpeech = "V";
					break;
					case 2: partOfSpeech = "J";
					break;
					case 3: partOfSpeech = "D";
					break;
					}
					//Then, create the new weighted words for us to insert.
					int length = wordNetwork.getAssociations().size();
					int newWeight = 1/(length + 1);
					WeightedWord newWeightedWord = 
							new WeightedWord(examinedWord, newWeight, partOfSpeech);
					wordNetwork.getAssociations().add(newWeightedWord);
					//Now, update the weights, so that their sum isn't higher than one.
					for (WeightedWord toBeUpdated: wordNetwork.getAssociations()) {
						toBeUpdated.changeWeight(newWeight);
					}
				}
			}
		}
	}
	
	public void updateGreaterNetwork() {
		//This method updates the network of word categories we created from the text file, so that we can
		//then use it to update the text file.
		//We look at all the words the user used...
		for (List<String> currentList: inputWords) {
			for (String currentWord: currentList) {
				//We throw in a boolean to tell us if we've found a word's corresponding category yet...
				boolean foundWord = false;
				//Then we look through the category networks we already have.
				for (WordNetwork currentNetwork: greaterNetwork) {
					if (currentNetwork.getKeyWord().equals(currentWord)) {
						//If we find it, we update the boolean, then call a method that will update the
						//category with any words it doesn't have.
						foundWord = true;
						fillUpWordNetwork(currentNetwork);
					}
				}
				//If we haven't found the word's category, we'll need to make one.
				if (!foundWord) {
					WordNetwork newWordNetwork = new WordNetwork(currentWord);
					//Then add in all the other words the user used, to show how they're associated.
					fillUpWordNetwork(newWordNetwork);
					greaterNetwork.add(newWordNetwork);
				}
			}
		}
	}
	
	public void getRelevantWords() throws IOException {
		//This method determines all the words we'll be using in the output sentence.
		//First, we build the programmer-friendly network.
		formGreaterNetwork();
		//Next, we initialize some word networks (lists of weighted words, essentially) to hold the words
		//we might potentially use.
		WordNetwork potentialNouns = new WordNetwork("Noun");
		WordNetwork potentialVerbs = new WordNetwork("Verb");
		WordNetwork potentialAdjectives = new WordNetwork("Adjectives");
		WordNetwork potentialDeterminers = new WordNetwork("Determiners");
		//We iterate over the whole list of the user's words, so that we can add the potential words 
		//(and their weights) corresponding each one.
		for (List<String> currentList: inputWords) {
			for (String currentWord: currentList) {
				//And we check to see if we have any of them in the networks.
				for (WordNetwork currentNetwork: greaterNetwork) {
					if (currentNetwork.getKeyWord().equals(currentWord)) {
						//For each of the words in the network, we check if its a noun, verb, adjective, or
						//determiner, and choose one of the "potential" lists accordingly. If the list
						//already has the word, we add to its weight with the new weight. And if it didn't
						//have the word, we add the word in. That, essentially, is the rest of these operations.
						for(WeightedWord word: currentNetwork.getAssociations()) {
							switch(word.getPartOfSpeech()) {
							case "N":
								if (potentialNouns.contains(word.getWord())) {
									WeightedWord examinedWord = potentialNouns.findWord(word.getWord());
									int newWeight = examinedWord.getWeight() + word.getWeight();
									potentialNouns.findWord(word.getWord()).changeWeight(newWeight);
								} else {
									potentialNouns.getAssociations().add(word);
								}
								break;
							case "V":
								if (potentialVerbs.contains(word.getWord())) {
									WeightedWord examinedWord = potentialVerbs.findWord(word.getWord());
									int newWeight = examinedWord.getWeight() + word.getWeight();
									potentialVerbs.findWord(word.getWord()).changeWeight(newWeight);
								} else {
									potentialVerbs.getAssociations().add(word);
								}
								break;
							case "J":
								if (potentialAdjectives.contains(word.getWord())) {
									WeightedWord examinedWord = potentialAdjectives.findWord(word.getWord());
									int newWeight = examinedWord.getWeight() + word.getWeight();
									potentialAdjectives.findWord(word.getWord()).changeWeight(newWeight);
								} else {
									potentialAdjectives.getAssociations().add(word);
								}
								break;
							case "D":
								if (potentialDeterminers.contains(word.getWord())) {
									WeightedWord examinedWord = potentialDeterminers.findWord(word.getWord());
									int newWeight = examinedWord.getWeight() + word.getWeight();
									potentialDeterminers.findWord(word.getWord()).changeWeight(newWeight);
								} else {
									potentialDeterminers.getAssociations().add(word);
								}
								break;
							}
						}
					}
				}
			}
		}
		//So, now that we have all the potential words and their weights sorted out, we go through each of
		//the "potential" lists we created and find which word has the highest accumulated weight. If
		//multiple words have the same weight, we just use the one that appears first.
		this.responseWords = new ArrayList<String>();
		int topWeight = 0;
		String noun = null;
		for(WeightedWord currentWord: potentialNouns.getAssociations()) {
			if (currentWord.getWeight() > topWeight) {
				topWeight = currentWord.getWeight();
				noun = currentWord.getWord();
			}
		}
		topWeight = 0;
		String verb = null;
		for(WeightedWord currentWord: potentialVerbs.getAssociations()) {
			if (currentWord.getWeight() > topWeight) {
				topWeight = currentWord.getWeight();
				verb = currentWord.getWord();
			}
		}
		topWeight = 0;
		String adjective = null;
		for(WeightedWord currentWord: potentialAdjectives.getAssociations()) {
			if (currentWord.getWeight() > topWeight) {
				topWeight = currentWord.getWeight();
				adjective = currentWord.getWord();
			}
		}
		topWeight = 0;
		String determiner = null;
		for(WeightedWord currentWord: potentialDeterminers.getAssociations()) {
			if (currentWord.getWeight() > topWeight) {
				topWeight = currentWord.getWeight();
				determiner = currentWord.getWord();
			}
		}
		//Once we've determined the final words, we store them in our response words variable.
		//Again, the noun is first, then the verb, then the adjective, then the determiner.
		responseWords.add(noun);
		responseWords.add(verb);
		responseWords.add(adjective);
		responseWords.add(determiner);
		//Finally, we update the network and the file.
		updateGreaterNetwork();
		updateFile();
	}
	
	public void updateFile() throws IOException {
		//This method creates a new version of the file, after the relevant words have been determined,
		//that accounts for words the user used.
		//Create a file writer...
		FileWriter writer = new FileWriter(textFile);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		PrintWriter printWriter = new PrintWriter(bufferedWriter);
		printWriter.print("");
		for (WordNetwork currentNetwork: greaterNetwork) {
			//Write in the special line indicating a new word category...
			printWriter.println("***");
			//Write in the word itself...
			printWriter.println(currentNetwork.getKeyWord());
			//And write in all the words associated with it, along with their weights and what part of
			//speech they are.
			for (WeightedWord currentWord: currentNetwork.getAssociations()) {
				String newLine = currentWord.getWord() + " " + 
						currentWord.getWeight() + " " + currentWord.getPartOfSpeech();
				printWriter.println(newLine);
			}
		}
		printWriter.close();
		bufferedWriter.close();
	}
	
	public String assembleSentence() {
		//Now that we have all the relevant words, we just ask the sentence maker to arrange them for us.
		sentenceMaker.getRelevantWords(responseWords);
		return sentenceMaker.assembleSentence();
	}
}
