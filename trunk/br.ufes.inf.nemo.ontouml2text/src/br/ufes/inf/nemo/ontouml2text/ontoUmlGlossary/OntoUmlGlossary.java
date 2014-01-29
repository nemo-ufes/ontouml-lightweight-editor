package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary;

import java.awt.EventQueue;
import java.util.HashSet;
import java.util.Set;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.*;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Relator;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Role;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Generalization;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Mediation;
import br.ufes.inf.nemo.ontouml2text.descriptionSpaceGenerator.DescriptionSpaceGenerator;
import br.ufes.inf.nemo.ontouml2text.glossaryExporter.*;
import br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui.GlossaryGeneratorUI;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.*;

public class OntoUmlGlossary {

	public static void main(String[] args) {
		
		// Creating Description Space
		DescriptionSpace space = new DescriptionSpace();
		
		DescriptionCategory auxS = new Relator("Transporte Rodoviário de Cargas");
		
		space.addCategory(auxS);
		
		// Described Category Functions
		DescriptionCategory auxT1 = new Relator("Execução de Serviço");	
		DescriptionFunction func = new Generalization("",auxS,auxT1,1,1,1,1);
		auxS.getFunctions().add(func);
		auxT1.getFunctions().add(func);
		
		space.addCategory(auxT1);
		
		DescriptionCategory auxT2 = new Role("Motorista em Transporte de Cargas");	
		func = new Mediation("",auxS,auxT2,1,-1,1,-1);
		auxS.getFunctions().add(func);
		auxT2.getFunctions().add(func);
		
		space.addCategory(auxT2);
		
		DescriptionCategory auxT3 = new Role("Veículo em Transporte de Cargas");	
		func = new Mediation("",auxS,auxT3,1,-1,1,1);
		auxS.getFunctions().add(func);
		auxT3.getFunctions().add(func);
		
		space.addCategory(auxT3);
		
		DescriptionCategory auxT4 = new Role("Trecho percorrido por Transporte Rodoviário de Cargas");	
		func = new Mediation("",auxS,auxT4,1,-1,1,-1);
		auxS.getFunctions().add(func);
		auxT4.getFunctions().add(func);
		
		space.addCategory(auxT4);
		
		// Generating glossary
		StringGenerator glossaryGenerator = new StringGenerator(space, 
				new HtmlGlossaryExporter("Glossary","C:/Users/Dio.Dio/Desktop","Glossário ANTT"), 
				new PortugueseLanguageAdaptor(new PortugueseDictionary()));
		
		glossaryGenerator.generateGlossary();
	}


	public void xmiToText(OntoUMLParser parser) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlossaryGeneratorUI settings = new GlossaryGeneratorUI();
					settings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		//if(settings.doGlossaryGeneration()){
			DescriptionSpace space = new DescriptionSpace();		
			
			// Hash containing the labels of the categories already covered
			Set<String> hashCategories = new HashSet<String>();
			
			DescriptionSpaceGenerator generator = new DescriptionSpaceGenerator(space);
			generator.PopulateDescriptionSpace(parser, hashCategories);
			
			// Processing description space
			StringGenerator glossaryGenerator = new StringGenerator(space, 
					new HtmlGlossaryExporter("Glossary","C:/Users/Dio.Dio/Desktop","Glossário ANTT"), 
					new PortugueseLanguageAdaptor(new PortugueseDictionary()));
			
			glossaryGenerator.generateGlossary();
		//}
	}

}
