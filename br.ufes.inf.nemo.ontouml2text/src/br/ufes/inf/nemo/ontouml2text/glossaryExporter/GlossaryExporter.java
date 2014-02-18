package br.ufes.inf.nemo.ontouml2text.glossaryExporter;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public abstract class GlossaryExporter {
		
	public abstract void initilizeExportFile();
	
	public abstract void saveDescription(DescriptionCategory category, String description);
	
	public abstract void finalizeExportFile();
	
}
