package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary;

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
import br.ufes.inf.nemo.ontouml2text.stringGenerator.*;

public class OntoUmlGlossary {

	public static void main(String[] args) {
		
		// Criando Description Space
		DescriptionSpace space = new DescriptionSpace();
		
		DescriptionCategory auxS = new Relator("Transporte Rodoviário de Cargas");
		
		space.getCategories().add(auxS);
		
		// Funcoes da categoria descrita
		DescriptionCategory auxT1 = new Relator("Execução de Serviço");	
		DescriptionFunction func = new Generalization("",auxS,auxT1,1,1,1,1);
		auxS.getFunctions().add(func);
		
		DescriptionCategory auxT2 = new Role("Motorista em Transporte de Cargas");	
		func = new Mediation("",auxS,auxT2,1,-1,1,-1);
		auxS.getFunctions().add(func);
		
		DescriptionCategory auxT3 = new Role("Veículo em Transporte de Cargas");	
		func = new Mediation("",auxS,auxT3,1,-1,1,1);
		auxS.getFunctions().add(func);
		
		DescriptionCategory auxT4 = new Role("Trecho percorrido por Transporte Rodoviário de Cargas");	
		func = new Mediation("",auxS,auxT4,1,-1,1,-1);
		auxS.getFunctions().add(func);
		
		// Gerando glossário
			StringGenerator glossaryGenerator = new StringGenerator(space, 
					new TerminalGlossaryExporter(), new PortugueseLanguageAdaptor(new PortugueseDictionary()));
			
			glossaryGenerator.generateGlossary();
}


public void TextExecute(OntoUMLParser parser) {		
	System.out.println("\n\n======= TEST =======\n\n");

		DescriptionSpace space = new DescriptionSpace();		
		
		// Hash containing the labels of the categories already covered
		Set<String> hashCategories = new HashSet<String>();
		
		DescriptionSpaceGenerator generator = new DescriptionSpaceGenerator(space);
		generator.PopulateDescriptionSpace(parser, hashCategories);
	}
}
