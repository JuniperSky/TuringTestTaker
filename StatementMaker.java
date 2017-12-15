package generalPackage;

public class StatementMaker extends SentenceMaker {
	public String assembleSentence() {
		//Suppose we couldn't find one of the words the user used back in the response assembler.
		//That's what this condition is for.
		if (this.nullWordCheck(relevantWords)) {
			return new String("I'm sorry, I still don't understand.");
		} else {
			//The noun is always stored in the 0th part of the list, the verb is always in the 1st
			//part, the adjective is always in the 2nd part, and the determiner is always in the 3rd.
			String noun = relevantWords.get(0);
			String verb = relevantWords.get(1);
			String adjective = relevantWords.get(2);
			String determiner = relevantWords.get(3);
			//Now form a generic sentence using the proper words.
			String newSentence = 
					new String("I " + verb + " " + determiner + " " + adjective + " " + noun + ".");
			return newSentence;
		}
	}
}
