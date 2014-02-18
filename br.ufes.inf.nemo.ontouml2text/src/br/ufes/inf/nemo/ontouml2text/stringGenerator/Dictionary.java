package br.ufes.inf.nemo.ontouml2text.stringGenerator;

public interface Dictionary {

	public boolean isMale(String label);
	
	public String getPlural(String label);
	
	public String getMaleArticle();
	
	public String getFemaleArticle();
	
	public String getAditionSeparator();
	
	public String getAlternativeSeparator();
	
	public String getFemaleIndefiniteArticle();
	
	public String getMaleIndefiniteArticle();
	
}
