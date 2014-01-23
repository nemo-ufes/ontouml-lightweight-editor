package br.ufes.inf.nemo.ontouml2text.Test;

import java.util.*;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
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
	//	space.SetParser(parser);
		
		Popular(space, parser);

		
/*
	DescriptionCategory auxS = new Relator("Transporte Rodoviário de Cargas");
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

		public void Popular(DescriptionSpace space, OntoUMLParser parser){
			
			Set <Classifier> classfSet = parser.getAllInstances(Classifier.class);	
			
			for (Classifier classf : classfSet){

				if(classf instanceof RefOntoUML.Category){
					DescriptionCategory mat = new Category(classf.getName());
					PopulateRelationships(parser.getRelationships(classf),space,mat,parser);
					space.getCategories().add(mat);
				}
				
				if(classf instanceof RefOntoUML.Collective){
					DescriptionCategory mat = new Collective(classf.getName());
					PopulateRelationships(parser.getRelationships(classf),space,mat,parser);
					space.getCategories().add(mat);
				}
				if(classf instanceof RefOntoUML.Kind){
					DescriptionCategory mat = new Kind(classf.getName());
					PopulateRelationships(parser.getRelationships(classf),space,mat,parser);
					space.getCategories().add(mat);
				}
				if(classf instanceof RefOntoUML.Mixin){
					DescriptionCategory mat = new Mixin(classf.getName());
					PopulateRelationships(parser.getRelationships(classf),space,mat,parser);
					space.getCategories().add(mat);
				}
				if(classf instanceof RefOntoUML.Mode){
					DescriptionCategory mat = new Mode(classf.getName());
					PopulateRelationships(parser.getRelationships(classf),space,mat,parser);
					space.getCategories().add(mat);
				}			
				if(classf instanceof RefOntoUML.Quantity){
					DescriptionCategory mat = new Quantity(classf.getName());
					PopulateRelationships(parser.getRelationships(classf),space,mat,parser);
					space.getCategories().add(mat);
				}
				if(classf instanceof RefOntoUML.Relator){
					DescriptionCategory mat = new Relator(classf.getName());
					PopulateRelationships(parser.getRelationships(classf),space,mat,parser);
					space.getCategories().add(mat);
				}
				if(classf instanceof RefOntoUML.Role){
					DescriptionCategory mat = new Role(classf.getName());
					PopulateRelationships(parser.getRelationships(classf),space,mat,parser);
					space.getCategories().add(mat);
				}
				if(classf instanceof RefOntoUML.RoleMixin){
					DescriptionCategory mat = new RoleMixin(classf.getName());
					PopulateRelationships(parser.getRelationships(classf),space,mat,parser);
					space.getCategories().add(mat);
				}
				}
				
		//	}
		}
		
		
		// Método que percorre a lista de relationships de uma categoria , cria instancias das functions, adiciona na lista de function do space e na lista de function da categoria.
		// FALTA implementar a parte das Generalizations 
		private void PopulateRelationships(ArrayList<Relationship> eList, DescriptionSpace space, DescriptionCategory mat2, OntoUMLParser parser) {
			
			int sourceLower,sourceUpper,targetLower,targetUpper;
			DescriptionCategory source;
			DescriptionCategory target;
			
			for(Relationship r : eList){
				
				if(r instanceof RefOntoUML.Association){
					source = FindCategory(((Association) r).getEndType().get(0));
					target = FindCategory(((Association) r).getEndType().get(1));
					
					//Find Multiplicity
					sourceLower = FindLowerMultiplicity(((Association) r).getMemberEnd().get(0));
					sourceUpper = FindUpperMultiplicity(((Association) r).getMemberEnd().get(0));
					
					targetLower = FindLowerMultiplicity(((Association) r).getMemberEnd().get(1));
					targetUpper = FindUpperMultiplicity(((Association) r).getMemberEnd().get(1));
					
					if(r instanceof RefOntoUML.Characterization){	
						DescriptionFunction mat = new Characterization(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
						space.getFunctions().add(mat);
						mat2.getFunctions().add(mat);
						}
					if(r instanceof RefOntoUML.componentOf){
						DescriptionFunction mat = new ComponentOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, false, false, false);
						space.getFunctions().add(mat);
						mat2.getFunctions().add(mat);
					}
					if(r instanceof RefOntoUML.FormalAssociation){
						DescriptionFunction mat = new Formal(((Association)r).getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
						space.getFunctions().add(mat);
						mat2.getFunctions().add(mat);
					}
					if(r instanceof RefOntoUML.MaterialAssociation){
						DescriptionFunction mat = new Material(((Association)r).getName(), source,target, sourceLower, sourceUpper,targetLower, targetUpper);
						space.getFunctions().add(mat);
						mat2.getFunctions().add(mat);
					}
					if(r instanceof RefOntoUML.Mediation){			
						DescriptionFunction mat = new Mediation(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper);
						space.getFunctions().add(mat);
						mat2.getFunctions().add(mat);
					}
					if(r instanceof RefOntoUML.memberOf){
						DescriptionFunction mat = new MemberOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
						space.getFunctions().add(mat);
						mat2.getFunctions().add(mat);
					}
					if(r instanceof RefOntoUML.subCollectionOf){
						DescriptionFunction mat = new SubcollectiveOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
						space.getFunctions().add(mat);
						mat2.getFunctions().add(mat);
					}
					if(r instanceof RefOntoUML.subQuantityOf){
						DescriptionFunction mat = new SubquantityOf(((Association)r).getName(), source, target, sourceLower, sourceUpper,targetLower,targetUpper, false, false, false);
						space.getFunctions().add(mat);
						mat2.getFunctions().add(mat);
					}
				}
				
				// IMPLEMENTAR PARA AS GENERALIZATIONS
				if(r instanceof RefOntoUML.Generalization){
					source = FindCategory(((RefOntoUML.Generalization) r).getGeneral());
					//source = FindCategory(((RefOntoUML.Generalization) r).getSource().get(0));

					System.out.println("getsource"+((RefOntoUML.Generalization) r).getSource().toString()+"/n");

					//DescriptionFunction mat = new br.ufes.inf.nemo.ontouml2text.descriptionSpace.Generalization("", source, target, 1, 1, 1, 1);
					//space.getFunctions().add(mat);
					//mat2.getFunctions().add(mat);
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