package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import java.util.List;

public interface LanguageAdaptor {
	
	public String generateCategoryDescription(List<DescriptionPattern> patterns);
	
}
