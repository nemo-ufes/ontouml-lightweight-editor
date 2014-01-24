package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.*;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Category;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Collective;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Kind;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Mixin;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Mode;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Quantity;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Relator;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Role;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.RoleMixin;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Subkind;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Characterization;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.ComponentOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Formal;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Generalization;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Material;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Mediation;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.MemberOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.SubcollectiveOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.SubquantityOf;
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

		// Criando Description Space
		DescriptionSpace space = new DescriptionSpace();		
		
		// Hash que contém os labels das categorias.
		Set<String> hashCategories = new HashSet<String>();
		
		PopulateDescriptionSpace(space, parser, hashCategories);
	
	/*
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
		
		glossaryGenerator.generateGlossary();*/
}

	public void PopulateDescriptionSpace(DescriptionSpace space, OntoUMLParser parser, Set<String> hashCategories){
		
		Set <RefOntoUML.Class> classfSet = parser.getAllInstances(RefOntoUML.Class.class);	
		
		for (RefOntoUML.Class classf : classfSet){
			
			DescriptionCategory mat;
			
			if(space.findCategory(classf.getName()) == null){			//verificar se a classe já foi criada
				mat = CreateCategoryClass(classf);
				space.addCategory(mat);
			}else											//se ja for criada, retorne a propria
				mat = space.findCategory(classf.getName());
			
			PopulateRelationships(parser.getRelationships(classf),space,mat,parser,hashCategories);
		}
		System.out.println("Tamanho da categories no DescriptionSpace:  " + space.getCategories().size());
}


public DescriptionCategory CreateCategoryClass(Class classf) {
	
	if(classf instanceof RefOntoUML.Category){
		DescriptionCategory mat = new Category(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Collective){
		DescriptionCategory mat = new Collective(classf.getName());
		return mat;
	}	
	if(classf instanceof RefOntoUML.Kind){
		DescriptionCategory mat = new Kind(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.SubKind){
		DescriptionCategory mat = new Subkind(classf.getName());
		return mat;	
	}
	if(classf instanceof RefOntoUML.Mixin){
		DescriptionCategory mat = new Mixin(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Mode){
		DescriptionCategory mat = new Mode(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Quantity){
		DescriptionCategory mat = new Quantity(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Relator){
		DescriptionCategory mat = new Relator(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Role){
		DescriptionCategory mat = new Role(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.RoleMixin){
		DescriptionCategory mat = new RoleMixin(classf.getName());
		return mat;
	}
	
	return null;
}


// FALTA implementar a parte das Generalizations 
private void PopulateRelationships(ArrayList<Relationship> eList, DescriptionSpace space, DescriptionCategory source, OntoUMLParser parser, Set<String> hashCategories) {
	
	int classNumberTarget;
	DescriptionCategory target;
	
	for(Relationship r : eList){

		if(r instanceof RefOntoUML.Association){			
			
			classNumberTarget = ChooseTarget(((RefOntoUML.Association) r).getEndType().get(0).getName(),((RefOntoUML.Association) r).getEndType().get(1).getName(),source.getLabel());

			if(space.findCategory(((Association) r).getEndType().get(classNumberTarget).getName()) == null){ // se a classe nao foi criada
				target = CreateCategory(((Association) r).getEndType().get(classNumberTarget));
				createRelationship(r,target,source);
				space.addCategory(target);
				break;//vai para a proxima iteracao do for
			}

			else{ //se a classe ja foi criada
				if(hashCategories.contains(((RefOntoUML.Association) r).getEndType().get(1).getName())) //verifica se ta na hash,se ja foi percorrido
					break;	//sai do for e vai pra proxima relaçao
				else{
					//acha a classe target e cria a relaçao
					DescriptionCategory targetCreated = space.findCategory(((RefOntoUML.Association) r).getEndType().get(1).getName());
					createRelationship(r, targetCreated, source);
				} 
			}
		}
		
		if(r instanceof RefOntoUML.Generalization){}
	}
	hashCategories.add(source.getLabel());
}


						
public int ChooseTarget(String name, String name2, String label) {
	if(label.equals(name))
		return 1;
	if(label.equals(name2))
		return 0;
	return -1;	
}


private void createRelationship(Relationship r, DescriptionCategory target,DescriptionCategory source) {
	
	int sourceLower,sourceUpper,targetLower,targetUpper;
	
		//Find multiplicity source and target
		sourceLower = FindLowerMultiplicity(((Association) r).getMemberEnd().get(0));
		sourceUpper = FindUpperMultiplicity(((Association) r).getMemberEnd().get(0));
		
		targetLower = FindLowerMultiplicity(((Association) r).getMemberEnd().get(1));
		targetUpper = FindUpperMultiplicity(((Association) r).getMemberEnd().get(1));
		
		
		if(r instanceof RefOntoUML.Characterization){	
			DescriptionFunction mat = new Characterization(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
			source.getFunctions().add(mat);
			}
		if(r instanceof RefOntoUML.componentOf){
			DescriptionFunction mat = new ComponentOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, false, false, false);
			source.getFunctions().add(mat);
		}
		if(r instanceof RefOntoUML.FormalAssociation){
			DescriptionFunction mat = new Formal(((Association)r).getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
			source.getFunctions().add(mat);
		}
		if(r instanceof RefOntoUML.MaterialAssociation){
			DescriptionFunction mat = new Material(((Association)r).getName(), source,target, sourceLower, sourceUpper,targetLower, targetUpper);
			source.getFunctions().add(mat);
		}
		if(r instanceof RefOntoUML.Mediation){			
			DescriptionFunction mat = new Mediation(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper);
			source.getFunctions().add(mat);
		}
		if(r instanceof RefOntoUML.memberOf){
			DescriptionFunction mat = new MemberOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
			source.getFunctions().add(mat);
		}
		if(r instanceof RefOntoUML.subCollectionOf){
			DescriptionFunction mat = new SubcollectiveOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
			source.getFunctions().add(mat);
		}
		if(r instanceof RefOntoUML.subQuantityOf){
			DescriptionFunction mat = new SubquantityOf(((Association)r).getName(), source, target, sourceLower, sourceUpper,targetLower,targetUpper, false, false, false);
			source.getFunctions().add(mat);
		}
	}


public int FindLowerMultiplicity(Property p){
			return p.getLower();
		}
		
public int FindUpperMultiplicity(Property p){
			return p.getUpper();
		}
		
public DescriptionCategory CreateCategory(Type type){
	
	if(type instanceof RefOntoUML.Category){
		DescriptionCategory mat = new Category(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Collective){
		DescriptionCategory mat = new Collective(type.getName());
		return mat;
	}	
	if(type instanceof RefOntoUML.Kind){
		DescriptionCategory mat = new Kind(type.getName());
		return mat;
	}		
	if(type instanceof RefOntoUML.SubKind){
		DescriptionCategory mat = new Subkind(type.getName());
		return mat;	
	}
	if(type instanceof RefOntoUML.Mixin){
		DescriptionCategory mat = new Mixin(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Mode){
		DescriptionCategory mat = new Mode(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Quantity){
		DescriptionCategory mat = new Quantity(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Relator){
		DescriptionCategory mat = new Relator(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Role){
		DescriptionCategory mat = new Role(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.RoleMixin){
		DescriptionCategory mat = new RoleMixin(type.getName());
		return mat;
	}
	return null;
}		
}
