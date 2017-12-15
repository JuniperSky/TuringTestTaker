package generalPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.tokenize.WhitespaceTokenizer;
import utilityPackage.*;

public class ResponseAssembler {
	private List<List<String>> inputWords;
	private List<List<String>> inputSentences;
	private SentenceMaker sentenceMaker;
	private String wordAssociations;
	private List<String> responseWords;
	
	private File textFile;
	private List<WordNetwork> greaterNetwork;
	
	private WhitespaceTokenizer tokenizer;
	
	public ResponseAssembler(String fileName) {
		this.wordAssociations = fileName;
		this.textFile = new File(wordAssociations);
	}
	
	public void addInputWords(List<List<String>> relevantWords) {
		this.inputWords = relevantWords;
	}
	
	public void addInputSentences(List<List<String>> sentenceTypes) {
		this.inputSentences = sentenceTypes;
	}
	
	public void newFile(String name) {
		this.wordAssociations = name;
	}
	
	public void determineSentenceType() {
		if (inputSentences.get(1).isEmpty()) {
			sentenceMaker = new QuestionMaker();
		} else {
			sentenceMaker = new StatementMaker();
		}
	}
	
	public void formGreaterNetwork() throws IOException {
		this.greaterNetwork = new ArrayList<WordNetwork>();
		FileReader tempFileReader = new FileReader(textFile);
		BufferedReader bufferedReader = new BufferedReader(tempFileReader);
		for (String currentLine = bufferedReader.readLine(); 
				!currentLine.isEmpty(); 
				currentLine = bufferedReader.readLine()) {
			if(currentLine.equals("***")) {
				String newWord = bufferedReader.readLine();
				WordNetwork newNetwork = new WordNetwork(newWord);
				greaterNetwork.add(newNetwork);
			} else {
				String[] splitLine =  tokenizer.tokenize(currentLine);
				WeightedWord newWeightedWord = new WeightedWord(splitLine[0], 
						Integer.parseInt(splitLine[1]),	splitLine[2]);
				int length = greaterNetwork.size();
				greaterNetwork.get(length - 1).getAssociations().add(newWeightedWord);
			}
		}
		bufferedReader.close();
	}
	
	public void updateGreaterNetwork() {
		for (List<String> currentList: inputWords) {
			for (String currentWord: currentList) {
				for (WordNetwork currentNetwork: greaterNetwork) {
					if (currentNetwork.getKeyWord().equals(currentWord)) {
						for (int j = 0; j < 4; j++) {
							List<String> currentCategory = inputWords.get(j);
							for (String examinedWord: currentCategory) {
								if (!currentNetwork.contains(examinedWord)) {
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
									int length = currentNetwork.getAssociations().size();
									int newWeight = 1/(length + 1);
									WeightedWord newWeightedWord = 
											new WeightedWord(examinedWord, newWeight, partOfSpeech);
									currentNetwork.getAssociations().add(newWeightedWord);
									for (WeightedWord toBeUpdated: currentNetwork.getAssociations()) {
										toBeUpdated.changeWeight(newWeight);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void getRelevantWords() throws IOException {
		formGreaterNetwork();
		WordNetwork potentialNouns = new WordNetwork("Noun");
		WordNetwork potentialVerbs = new WordNetwork("Verb");
		WordNetwork potentialAdjectives = new WordNetwork("Adjectives");
		WordNetwork potentialDeterminers = new WordNetwork("Determiners");
		for (List<String> currentList: inputWords) {
			for (String currentWord: currentList) {
				for (WordNetwork currentNetwork: greaterNetwork) {
					if (currentNetwork.getKeyWord().equals(currentWord)) {
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
		responseWords.add(noun);
		responseWords.add(verb);
		responseWords.add(adjective);
		responseWords.add(determiner);
		updateGreaterNetwork();
		updateFile();
	}
	
	public void updateFile() throws IOException {
		FileWriter writer = new FileWriter(textFile);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		PrintWriter printWriter = new PrintWriter(bufferedWriter);
		printWriter.print("");
		for (WordNetwork currentNetwork: greaterNetwork) {
			printWriter.println("***");
			printWriter.println(currentNetwork.getKeyWord());
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
		sentenceMaker.getRelevantWords(responseWords);
		return sentenceMaker.assembleSentence();
	}
}
