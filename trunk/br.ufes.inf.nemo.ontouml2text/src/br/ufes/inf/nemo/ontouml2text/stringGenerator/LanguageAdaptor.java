package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import java.util.List;

import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.DescriptionPattern;

public interface LanguageAdaptor {
	
	public String generateCategoryDescription(List<DescriptionPattern> patterns);
	
}
