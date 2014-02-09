package br.ufes.inf.nemo.ontouml2text.glossaryExporter;

import java.io.PrintWriter;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public abstract class GlossaryExporter {
		
	public abstract void initilizeExportFile();
	
	public abstract void saveDescription(DescriptionCategory category, String description, PrintWriter htmlLetter);
	
	public abstract void finalizeExportFile();
	
	public abstract PrintWriter findLetter(String letter);
	
}
