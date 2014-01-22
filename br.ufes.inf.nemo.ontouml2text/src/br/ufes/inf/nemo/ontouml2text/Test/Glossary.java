package br.ufes.inf.nemo.ontouml2text.Test;

import java.util.*;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.*;

public class Glossary {	
	
	public void TextExecute(OntoUMLParser parser) {		
		System.out.println("\n\n======= TEST =======\n\n");

		// Criando Description Space
		DescriptionSpace space = new DescriptionSpace();		
		space.SetParser(parser);
		
		Popular(space, parser);
		
		// Cria classes a partir do RefOntoUML
		//space.PopulateLists();
		
/*
	DescriptionCategory auxS = new Relator("Transporte Rodovi�rio de Cargas");
		space.getCategories().add(auxS);
		
		// Funcoes da categoria descrita
		DescriptionCategory auxT1 = new Relator("Execu��o de Servi�o");	
		DescriptionFunction func = new Generalization("",auxS,auxT1,1,1,1,1);
		auxS.getFunctions().add(func);
		PatternCategory pc1 = new PatternCategory(auxT1.getLabel(),
				func.getTargetMinMultiplicity(), func.getTargetMaxMultiplicity());
		
		DescriptionCategory auxT2 = new Role("Motorista em Transporte de Cargas");	
		func = new Mediation("",auxS,auxT2,1,-1,1,-1);
		auxS.getFunctions().add(func);
		PatternCategory pc2 = new PatternCategory(auxT2.getLabel(),
				func.getTargetMinMultiplicity(), func.getTargetMaxMultiplicity());
		
		DescriptionCategory auxT3 = new Role("Ve�culo em Transporte de Cargas");	
		func = new Mediation("",auxS,auxT3,1,-1,1,1);
		auxS.getFunctions().add(func);
		PatternCategory pc3 = new PatternCategory(auxT3.getLabel(),
				func.getTargetMinMultiplicity(), func.getTargetMaxMultiplicity());
		
		DescriptionCategory auxT4 = new Role("Trecho percorrido por Transporte Rodovi�rio de Cargas");	
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

		public void Popular(DescriptionSpace space, OntoUMLParser parser){
			
			Set <Classifier> classfSet = parser.getAllInstances(Classifier.class);	
			Set<Generalization> gen = parser.getAllInstances(Generalization.class);
			
			int sourceLower,sourceUpper,targetLower,targetUpper;
			DescriptionCategory source;
			DescriptionCategory target;
			
			for (Classifier classf : classfSet){
				
				if(classf instanceof RefOntoUML.Association){
					source = FindCategory(((Association) classf).getEndType().get(0));
					target = FindCategory(((Association) classf).getEndType().get(1));
					
					//Find Multiplicity
					sourceLower = FindLowerMultiplicity(((Association) classf).getMemberEnd().get(0));
					sourceUpper = FindUpperMultiplicity(((Association) classf).getMemberEnd().get(0));
					
					targetLower = FindLowerMultiplicity(((Association) classf).getMemberEnd().get(1));
					targetUpper = FindUpperMultiplicity(((Association) classf).getMemberEnd().get(1));
					
					if(classf instanceof RefOntoUML.Characterization){	
						// verificar se o 
					/*	if(!ExisteLista(space.getCategories(),((RefOntoUML.Association) classf).getEndType().get(0).getName())){//se o source nao existir na lista, cria
							source = FindCategory(((Association) classf).getEndType().get(0));
							DescriptionFunction mat = new Characterization(classf.getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
							space.getFunctions().add(mat);
							source.getFunctions().add(e)
							}*/
					}
					if(classf instanceof RefOntoUML.componentOf){
						DescriptionFunction mat = new ComponentOf(classf.getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, false, false, false);
						space.getFunctions().add(mat);
					}
					if(classf instanceof RefOntoUML.FormalAssociation){
						DescriptionFunction mat = new Formal(classf.getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
						space.getFunctions().add(mat);
					}
					if(classf instanceof RefOntoUML.MaterialAssociation){
						DescriptionFunction mat = new Material(classf.getName(), source,target, sourceLower, sourceUpper,targetLower, targetUpper);
						space.getFunctions().add(mat);
					}
					if(classf instanceof RefOntoUML.Mediation){			
						DescriptionFunction mat = new Mediation(classf.getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper);
						space.getFunctions().add(mat);
					}
					if(classf instanceof RefOntoUML.memberOf){
						DescriptionFunction mat = new MemberOf(classf.getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
						space.getFunctions().add(mat);
					}
					if(classf instanceof RefOntoUML.subCollectionOf){
						DescriptionFunction mat = new SubcollectiveOf(classf.getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
						space.getFunctions().add(mat);
					}
					if(classf instanceof RefOntoUML.subQuantityOf){
						DescriptionFunction mat = new SubquantityOf(classf.getName(), source, target, sourceLower, sourceUpper,targetLower,targetUpper, false, false, false);
						space.getFunctions().add(mat);
					}
					//Categoria abstrata
					//	if(classf instanceof RefOntoUML.partof){}
						
				} else {/*
				
				if(classf instanceof RefOntoUML.Category){
					DescriptionCategory mat = new Category(classf.getName());
					categories.add(mat);
				}
				
				if(classf instanceof RefOntoUML.Collective){
					DescriptionCategory mat = new Collective(classf.getName());
					categories.add(mat);
				}
				if(classf instanceof RefOntoUML.Kind){
					DescriptionCategory mat = new Kind(classf.getName());
					categories.add(mat);
				}
				if(classf instanceof RefOntoUML.Mixin){
					DescriptionCategory mat = new Mixin(classf.getName());
					categories.add(mat);
				}
				if(classf instanceof RefOntoUML.Mode){
					DescriptionCategory mat = new Mode(classf.getName());
					categories.add(mat);
				}			
				if(classf instanceof RefOntoUML.Quantity){
					DescriptionCategory mat = new Quantity(classf.getName());
					categories.add(mat);
				}
				if(classf instanceof RefOntoUML.Relator){
					DescriptionCategory mat = new Relator(classf.getName());
					categories.add(mat);
				}
				if(classf instanceof RefOntoUML.Role){
					DescriptionCategory mat = new Role(classf.getName());
					categories.add(mat);
				}
				if(classf instanceof RefOntoUML.RoleMixin){
					DescriptionCategory mat = new RoleMixin(classf.getName());
					categories.add(mat);
				}*/
				}
				
			}
		}
		
		public int FindLowerMultiplicity(Property p){
			return p.getLower();

		}
		
		public int FindUpperMultiplicity(Property p){
			return p.getUpper();
		}
		
		public DescriptionCategory FindCategory(Type type){
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
			if(type instanceof RefOntoUML.Mixin){
				DescriptionCategory mat = new Mixin(type.getName());
				return mat;
			}
			if(type instanceof RefOntoUML.Mode){
				DescriptionCategory mat = new Mode(type.getName());
				return mat;
			}
		//	if(obj instanceof RefOntoUML.partof){}
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