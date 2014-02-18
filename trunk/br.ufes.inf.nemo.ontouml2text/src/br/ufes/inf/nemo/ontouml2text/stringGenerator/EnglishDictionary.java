package br.ufes.inf.nemo.ontouml2text.stringGenerator;

public class EnglishDictionary implements Dictionary {

	@Override
	public boolean isMale(String label) {
		String completeLabel[] = label.split(" ");
		// completeLabel[0] represents the first word
		
		if(completeLabel[0].endsWith("ter")) return false;
		
		return true;
	}

	@Override
	public String getPlural(String label) {
		String completeLabel[] = label.split(" ");	
		
		completeLabel[0] = processPlural(completeLabel[0]);
		
		if(completeLabel.length > 1){
			int j = 1;
			
			if(completeLabel[1].equals("de")) // the second word is a conjunction?
				j = 2; // The third word became the "second word"	
			else if(completeLabel[1].equals("em"))
				j = -1;
			
			if(j != -1)
				completeLabel[j] = processPlural(completeLabel[j]);
		}
		
		return join(" ", completeLabel);
	}
	
	private String processPlural(String word){
		if(word.endsWith("x")) 
			return word + "es";
		else if(word.endsWith("ch"))
			return word + "es";
		else if(word.endsWith("y")) 
			return word.substring(0, word.length() - 1) + "ties";
		else if(word.endsWith("s"))
			return word;
		else
			return word + "s";
	}
	
	private String join(String separator, String[] splitedText){
		int i;
		String joinedText = splitedText[0];
		
		for(i = 1; i < splitedText.length; i++)
			joinedText += separator + splitedText[i];
		
		return joinedText;
	}

	@Override
	public String getMaleArticle() {
		return "the";
	}

	@Override
	public String getFemaleArticle() {
		return "the";
	}

	@Override
	public String getAditionSeparator() {
		return "and";
	}

	@Override
	public String getAlternativeSeparator() {
		return "or";
	}

	@Override
	public String getFemaleIndefiniteArticle() {
		return "a";
	}

	@Override
	public String getMaleIndefiniteArticle() {
		return "a";
	}

}
