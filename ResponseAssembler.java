package generalPackage;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.util.List;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class ResponseAssembler {
	private List<List<String>> inputWords;
	private List<List<String>> inputSentences;
	private SentenceMaker sentenceMaker;
	private String wordAssociations;
	private List<String> responseWords;
	
	private RandomAccessFile randomTextFile;
	private RandomAccessFile randomTextFileStorage;
	private FileChannel sourceChannel;
	private FileChannel targetChannel;
	
	private File textFile;
	private File storageTextFile;
	
	private WhitespaceTokenizer tokenizer;
	
	public ResponseAssembler(String fileName) {
		this.wordAssociations = fileName;
		try {
			this.textFile = new File(wordAssociations);
			this.storageTextFile = new File(wordAssociations + "~");
			this.randomTextFile = new RandomAccessFile(textFile, "rw");
			this.randomTextFileStorage = new RandomAccessFile(storageTextFile, "rw");
			this.sourceChannel = randomTextFile.getChannel();
			this.targetChannel = randomTextFileStorage.getChannel();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
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
	public void getRelevantWords() throws IOException {
		List<String> potentialNouns;
		List<String> potentialVerbs;
		List<String> potentialAdjectives;
		List<String> potentialDeterminers;
		for (int i = 0; i < 4; i++) {
			List<String> currentList = inputWords.get(i);
			for(String currentWord: currentList) {
				FileReader tempFileReader = new FileReader(textFile);
				BufferedReader bufferedReader = new BufferedReader(tempFileReader);
				for (String currentLine = bufferedReader.readLine(); !currentLine.isEmpty(); currentLine = bufferedReader.readLine()) {
					if (currentLine.equals("***")) {
						currentLine = bufferedReader.readLine();
						if (currentLine.equals(currentWord)) {
							
						}
					}
				}
				bufferedReader.close();
			}
		}
		
		
	}
	
	public void updateFile() {
		
	}
	
	public String assembleSentence() {
		sentenceMaker.getRelevantWords(responseWords);
		return sentenceMaker.assembleSentence();
	}
}
