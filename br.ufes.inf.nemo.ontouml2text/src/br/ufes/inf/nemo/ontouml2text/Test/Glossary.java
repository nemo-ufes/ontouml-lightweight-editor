package br.ufes.inf.nemo.ontouml2text.Test;

import java.util.*;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.*;

public class Glossary {	
	
	public void TextExecute(OntoUMLParser parser) {		
		System.out.println("======= TEST =======");

		// Criando Description Space
		DescriptionSpace space = new DescriptionSpace();		
		space.SetParser(parser);
		
/*		DescriptionCategory auxS = new Relator("Transporte Rodoviário de Cargas");
		space.getCategories().add(auxS);
		
		// Funcoes da categoria descrita
		DescriptionCategory auxT1 = new Relator("Execução de Serviço");	
		DescriptionFunction func = new Generalization("",auxS,auxT1,1,1,1,1);
		auxS.getFunctions().add(func);
		PatternCategory pc1 = new PatternCategory(auxT1.getLabel(),
				func.getTargetMinMultiplicity(), func.getTargetMaxMultiplicity());
		
		DescriptionCategory auxT2 = new Role("Motorista em Transporte de Cargas");	
		func = new Mediation("",auxS,auxT2,1,-1,1,-1);
		auxS.getFunctions().add(func);
		PatternCategory pc2 = new PatternCategory(auxT2.getLabel(),
				func.getTargetMinMultiplicity(), func.getTargetMaxMultiplicity());
		
		DescriptionCategory auxT3 = new Role("Veículo em Transporte de Cargas");	
		func = new Mediation("",auxS,auxT3,1,-1,1,1);
		auxS.getFunctions().add(func);
		PatternCategory pc3 = new PatternCategory(auxT3.getLabel(),
				func.getTargetMinMultiplicity(), func.getTargetMaxMultiplicity());
		
		DescriptionCategory auxT4 = new Role("Trecho percorrido por Transporte Rodoviário de Cargas");	
		func = new Mediation("",auxS,auxT3,1,-1,1,-1);
		auxS.getFunctions().add(func);
		PatternCategory pc4 = new PatternCategory(auxT4.getLabel(),
				func.getTargetMinMultiplicity(), func.getTargetMaxMultiplicity());
		
		// Listando padroes
		List<DescriptionPattern> patterns = new ArrayList<DescriptionPattern>();
		
		DescriptionPattern pattern = new HomogeneousGeneralizationPattern(auxS,pc1);
		patterns.add(pattern);
		
		pattern = new OrdinaryMediationPattern(auxS);
		((NaryPattern)pattern).getTargetCategories().add(pc2);
		((NaryPattern)pattern).getTargetCategories().add(pc3);
		((NaryPattern)pattern).getTargetCategories().add(pc4);
		
		patterns.add(pattern);	
		
		// Gerando descricao	
		PortugueseDictionary dictionary = new PortugueseDictionary();
		PortugueseLanguageAdaptor adaptor = new PortugueseLanguageAdaptor(dictionary);
		
		System.out.println(adaptor.generateCategoryDescription(patterns));
		space.ParserTest();*/
	}

}
