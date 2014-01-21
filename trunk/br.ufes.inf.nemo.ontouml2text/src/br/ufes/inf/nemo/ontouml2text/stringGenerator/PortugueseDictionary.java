package br.ufes.inf.nemo.ontouml2text.stringGenerator;

public class PortugueseDictionary implements Dictionary{

	public PortugueseDictionary() {

	}
	
	public boolean isMale(String label){
		String firstWord = firstWord(label);
		
		if(firstWord.endsWith("ista")) return true;
		if(firstWord.endsWith("ção")) return false;
		if(firstWord.endsWith("são")) return false;
		if(firstWord.endsWith("dão")) return false;
		if(firstWord.endsWith("ez")) return false;
		if(firstWord.endsWith("tude")) return false;
		if(firstWord.endsWith("or")) return true;
		if(firstWord.endsWith("nte")) return true;
		if(firstWord.endsWith("il")) return true;
		if(firstWord.endsWith("agem")) return false;
		if(firstWord.endsWith("al")) return true;
		if(firstWord.endsWith("ame")) return true;
		if(firstWord.endsWith("edo")) return true;
		if(firstWord.endsWith("ume")) return true;
		if(firstWord.endsWith("ite")) return false;
		if(firstWord.endsWith("ol")) return true;
		if(firstWord.endsWith("a")) return false;
		
		return true;
	}
	
	public String getPlural(String label){
		String firstWord = firstWord(label);
		String complement = label.replaceAll(firstWord, "");
		
		if(firstWord.endsWith("ão")) return firstWord.substring(0,label.length() - 3) + "ões";
		
		return firstWord + "s" + complement;
	}
	
	private String firstWord(String label){
		return label.replaceAll(" .*", "");
	}

}
