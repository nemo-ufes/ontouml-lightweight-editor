package br.ufes.inf.nemo.ontouml2text.stringGenerator;

public class PortugueseDictionary implements Dictionary{

	public PortugueseDictionary() {

	}
	
	public boolean isMale(String label){
		String completeLabel[] = label.split(" ");
		// completeLabel[0] represents the first word
		
		if(completeLabel[0].endsWith("dade")) return false;
		if(completeLabel[0].endsWith("ista")) return true;
		if(completeLabel[0].endsWith("ção")) return false;
		if(completeLabel[0].endsWith("são")) return false;
		if(completeLabel[0].endsWith("dão")) return false;
		if(completeLabel[0].endsWith("ez")) return false;
		if(completeLabel[0].endsWith("tude")) return false;
		if(completeLabel[0].endsWith("or")) return true;
		if(completeLabel[0].endsWith("nte")) return true;
		if(completeLabel[0].endsWith("il")) return true;
		if(completeLabel[0].endsWith("agem")) return false;
		if(completeLabel[0].endsWith("al")) return true;
		if(completeLabel[0].endsWith("ame")) return true;
		if(completeLabel[0].endsWith("edo")) return true;
		if(completeLabel[0].endsWith("ume")) return true;
		if(completeLabel[0].endsWith("ine")) return false;
		if(completeLabel[0].endsWith("ite")) return false;
		if(completeLabel[0].endsWith("ol")) return true;
		if(completeLabel[0].endsWith("a")) return false;
		
		return true;
	}
	
	public String getPlural(String label){
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
		if(word.endsWith("ão")) 
			return word.substring(0, word.length() - 2) + "ões";
		else if(word.endsWith("al"))
			return word.substring(0, word.length() - 1) + "is";
		else if(word.endsWith("el"))
			return word.substring(0, word.length() - 2) + "éis";
		else if(word.endsWith("em"))
			return word.substring(0, word.length() - 2) + "ens";
		else if(word.endsWith("r"))
			return word + "es";
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
		return "o";
	}

	@Override
	public String getFemaleArticle() {
		return "a";
	}

	@Override
	public String getAditionSeparator() {
		return "e";
	}

	@Override
	public String getAlternativeSeparator() {
		return "ou";
	}	
	
	@Override
	public String getFemaleIndefiniteArticle() {
		return "uma";
	}
	
	@Override
	public String getMaleIndefiniteArticle() {
		return "um";
	}

}
