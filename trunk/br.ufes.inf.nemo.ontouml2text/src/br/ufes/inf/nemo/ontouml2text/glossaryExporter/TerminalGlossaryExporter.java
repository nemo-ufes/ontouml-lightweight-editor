package br.ufes.inf.nemo.ontouml2text.glossaryExporter;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public class TerminalGlossaryExporter extends GlossaryExporter {
	
	public TerminalGlossaryExporter(){

	}
	
	public void initilizeExportFile(){
		
	}
	
	public void saveDescription(DescriptionCategory category, String description){
		System.out.println("==========================================================================");
		System.out.println("CATEGORIA: "+category.getLabel());
		System.out.println("DESCRIÇÃO: "+description);
	}
	
	public void finalizeExportFile(){
		
	}

}
