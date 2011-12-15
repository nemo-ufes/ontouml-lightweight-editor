package br.inf.ufes.nemo.transformation.ontouml2alloy.v2;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.DependencyRelationship;
import RefOntoUML.DirectedBinaryAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Meronymic;
import RefOntoUML.Model;
import RefOntoUML.Property;
import RefOntoUML.SubKind;
import RefOntoUML.impl.AntiRigidSortalClassImpl;
import RefOntoUML.impl.CategoryImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.DependencyRelationshipImpl;
import RefOntoUML.impl.DerivationImpl;
import RefOntoUML.impl.DirectedBinaryAssociationImpl;
import RefOntoUML.impl.FormalAssociationImpl;
import RefOntoUML.impl.GeneralizationImpl;
import RefOntoUML.impl.GeneralizationSetImpl;
import RefOntoUML.impl.MeronymicImpl;
import RefOntoUML.impl.MixinClassImpl;
import RefOntoUML.impl.MomentClassImpl;
import RefOntoUML.impl.NonRigidMixinClassImpl;
import RefOntoUML.impl.PrimitiveTypeImpl;
import RefOntoUML.impl.RelationshipImpl;
import RefOntoUML.impl.RigidSortalClassImpl;
import RefOntoUML.impl.RoleImpl;
import RefOntoUML.impl.SubKindImpl;
import RefOntoUML.impl.SubstanceSortalImpl;
import br.inf.ufes.nemo.transformation.ontouml2alloy.AlloyProcessor;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base.AlloyDatatypeRelationship;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base.AlloyDependencyRelationship;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base.AlloyDirectedBinaryRelationship;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base.AlloyGeneralizationSet;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base.AlloyMeronymic;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base.AlloyProperty;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base.AlloyRelationship;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base.AlloySigDatatypeRelationship;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyAntiRigidSortalClass;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyCategory;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyClass;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyClassifier;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyDatatype;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyNonRigidClass;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyNonRigidMixinClass;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyPhase;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyRole;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloySigClass;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloySigClassifier;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloySimpleDatatype;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyStructuredDatatype;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloySubkind;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyUltimateIdentityProvider;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.util.invalidModelException;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.util.invalidSubKindException;

//TODO: documentar!
/*
* Erros detectados no editor
* Impedir generalizations de generalizar generalizations
* Generalization sets devem ter uma mesma superclasse para todos os generalizations 
* Category pode herdar de tipos não rigidos
* Tipos não rigidos podem herdar de category
* Um role pode herdar de um substance sortal e de um relator ao mesmo tempo (deveria poder só de um ultimate id provider ou subkind por vez, diretamente ou indiretamente)
* Generalization sets permitem ter mais de um general (não tenho certeza, mas acho que não deviam ter)
* Modes podem ter mais de uma characterization
* 
* TODOLIST:
* Generalizações para datatypes!!!
*  - Pode herança múltipla? (se puder, vou ter que fazer uma signature artificial que seja supertipo de toda a hierarquia e as outras 'in' ela)
*  - Pode um structured herdar de um simple? (
* Mecanismo de subtipo para classes em geral! (tipo, modes)
* 
* isExtensional dos collectives (como fazer? tem que haver restrições nas relações memberOf dele... tem que descobrir quais relações ele pertence)
* qualidades das relações todo parte diferentes (transitividade, etc)
* verificar se as restrições de cardinalidade para uma relação generalizada é impressa com o nome da relação general
* Formal relations: Para sigclasses pode ser relação binária... Para stateclasses ternária
* Implementar as gs das sigclasses como um fact separado, para otimizar. (por enquanto tá como sigfact de State)
*/


/*
* Falta implementar Associations!!!!
* Idéia para tratar dependência existencial:
* Pra sigclasses a gente declara a dependência em signature facts pra usar a relação na própria assinatura
* Pras outras classes, no State signature. 
* (Essential e immutable part), (inseparable e immutable whole) são basicamente a mesma coisa
* 
*    Pensando bem, é melhor só fazer isso pras relações characterization e mediation... Senão fica estranho
* usar as relações, sem saber se está na state signature ou na class signature.
*    Ok, eu poderia usar a mesma lógica de botar a relação na sig na hora de usar, mas deixaria o código mnto complexo.
*    rationale das Relações read-only:
*    	-Pra datatype relationship é fácil, no caso de ser uma sigclass (frequente pra structured datatype)... Se não for, usa o normal
*      -outras relações, só sobrou todo-parte, que de novo, só funciona pra sigclasses
*      Na relação todo-parte, se faz referência ao átomo, e na sig faz "all s: State | this in s.exists imply rel in s.(..)" completar com a classe
*      Posso fazer Essential e Inseparable como especializações de meronymic, e quando for lidar com as diferenças de componentof, memberof, subcollectionof, subquantityof, delegar a maneira diferente de tratar pra outra classe...
*      
*      Obs: Não podemos lidar com herança múltipla pra datatypes (a não ser que seja adicionado uma superclasse artificial comum à todas)
*      
*/

public class Processor implements AlloyProcessor {

	// TODO Rename variables
	// Mapeamentos
	// OntoUML.DirectedBinaryRelationship -> RefOntoUML.DirectedBinaryAssociation
	
	// Here we split the model elements into separate sets, to deal with them individually
	Model allObjects;
	HashSet<AlloySigClass> sigClasses = new HashSet<AlloySigClass>();
	HashSet<AlloyNonRigidClass> nonRigidClasses = new HashSet<AlloyNonRigidClass>();
	HashSet<AlloyCategory> categories = new HashSet<AlloyCategory>();
	HashSet<AlloyRelationship> relationships = new HashSet<AlloyRelationship>();
	HashSet<Generalization> generalizations = new HashSet<Generalization>();
	HashSet<AlloyGeneralizationSet> gsets = new HashSet<AlloyGeneralizationSet>();
	HashSet<AlloyDatatype> datatypes = new HashSet<AlloyDatatype>();
	
	public String process(Model model) {
		
		allObjects = model;
		
		try {
			if(allObjects instanceof Model)
			{
				// TODO:For now, rules are being ignored.
				//First, we'll sort the classifiers in subsets
				Map<Classifier,AlloyClassifier> map = handleClassifiers();
				
				//Now the relations
				handleRelations(map);
				
				//We'll exclude empty generalization sets
				handleEmptyGeneralizationSets(map);
				
				//Cleanup the unhadled relations
				cleanupUnhadledRelationships();
				
				/// now we'll print the .als file
				String alloy = generateAlloy();
				
				return alloy;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void cleanupUnhadledRelationships() {
		
		Set<AlloyRelationship> to_remove = new HashSet<AlloyRelationship>();
		//adding the existentially dependent relations
		for(AlloyRelationship r: relationships)
		{
			//We have decided (to simplify) to only add as sig relationships Characterization, Mediation and datatype relationships.
			if(r instanceof AlloyDependencyRelationship)//caution: if derivations are added, this should be changed
			{
				AlloyDependencyRelationship DR = (AlloyDependencyRelationship)r;
				AlloyUltimateIdentityProvider c = (AlloyUltimateIdentityProvider)DR.getSource().getEndType();
				c.addEDRelationship((AlloyDirectedBinaryRelationship)r);
				to_remove.add(r);
			}
			else if(r instanceof AlloySigDatatypeRelationship)
			{
				AlloySigDatatypeRelationship DR = (AlloySigDatatypeRelationship)r;
				AlloySigClassifier c = (AlloySigClassifier)(DR.getHolderSigClassifier());
				c.addSigDatatypeRelationship(DR);
				to_remove.add(r);
			}
				
		}
		relationships.removeAll(to_remove);
		to_remove.clear();		
	}

	private void handleEmptyGeneralizationSets(Map<Classifier, AlloyClassifier> map) throws invalidModelException {
		for (EObject eObject : allObjects.eContents())
		{
			if(eObject instanceof GeneralizationSetImpl)
			{						
				if(((GeneralizationSet)eObject).getGeneralization().size()>0)
				{
					gsets.add(new AlloyGeneralizationSet((GeneralizationSet)eObject, (HashMap<Classifier, AlloyClassifier>) map));							
				}
			}
		}
		
	}

	private Map<Classifier,AlloyClassifier> handleClassifiers() throws invalidSubKindException
	{
		//HashMap map is used to create relations, on the second 'for' loop.
		HashMap<Classifier,AlloyClassifier> map = new HashMap<Classifier,AlloyClassifier>();

		for (EObject eObject : allObjects.eContents())
		{
			AlloyClassifier x;
			if(eObject instanceof SubstanceSortalImpl || eObject instanceof MomentClassImpl)
			{
				x = new AlloyUltimateIdentityProvider((Class)eObject);						
				sigClasses.add((AlloySigClass) x);
				map.put((Class)eObject, x);
			}
				
			else if(eObject instanceof SubKindImpl)
			{
				x = new AlloySubkind((SubKind)eObject);
				sigClasses.add((AlloySigClass) x);
				map.put((Class)eObject, x);
			}						
			else if(eObject instanceof MixinClassImpl)
			{
				if(eObject instanceof CategoryImpl)
				{
					x = new AlloyCategory((CategoryImpl)eObject,allObjects.eContents()); 
					categories.add((AlloyCategory) x);
					map.put((Class)eObject, x);
				}
				else
				{
					x = new AlloyNonRigidMixinClass((NonRigidMixinClassImpl) eObject, allObjects.eContents());
					nonRigidClasses.add((AlloyNonRigidClass) x);
					map.put((Class)eObject, x);
				}
			}
			else if(eObject instanceof AntiRigidSortalClassImpl)
			{
				if(eObject instanceof RoleImpl)x = new AlloyRole((AntiRigidSortalClass) eObject, allObjects.eContents());
				else x = new AlloyPhase((AntiRigidSortalClass) eObject, allObjects.eContents());
				nonRigidClasses.add((AlloyNonRigidClass) x);
				map.put((Class)eObject, x);
			}
			else if(eObject instanceof DataTypeImpl)
			{
				if(eObject instanceof PrimitiveTypeImpl)x = new AlloySimpleDatatype((PrimitiveTypeImpl) eObject);
				else x = new AlloyStructuredDatatype((DataType) eObject);
				datatypes.add((AlloyDatatype) x);
				map.put((Classifier)eObject,x);
			}
		}
		
		return map;
	}
	
	private void handleRelations(Map<Classifier,AlloyClassifier> map)
	{
		for (EObject eObject : allObjects.eContents())
		{
			if(eObject instanceof RelationshipImpl)
			{	
				if(eObject instanceof GeneralizationImpl)generalizations.add((Generalization)eObject);
				else if (eObject instanceof DirectedBinaryAssociationImpl)
				{
					//CLEANUP Property target = (Property) ((OntoUML.DirectedBinaryRelationship)eObject).getTarget().get(0);
					//CLEANUP Property source = (Property) ((OntoUML.DirectedBinaryRelationship)eObject).getSource().get(0);
					
					Property target = (Property) ((DirectedBinaryAssociationImpl) eObject).sourceEnd();
					Property source = (Property) ((DirectedBinaryAssociationImpl) eObject).targetEnd();
					
					if (eObject instanceof MeronymicImpl)
					{
						/*
						 * while SigMeronymics are not implemented, use only simple Meronymics
						 * TODO: implement sigmeronymics and init them here
						 
						if(((Meronymic) eObject).isIsInseparable() || ((Meronymic) eObject).isIsEssential())
						{
							relationships.add
							(
									new AlloySigMeronymic
									(
										(OntoUML.Meronymic)eObject,
										new AlloyProperty(target,map.get((Classifier)target.getEndType())),
										new AlloyProperty(source,map.get((Classifier)source.getEndType()))
									)
							);
						}
						else*/ relationships.add
						(
								new AlloyMeronymic
								(
									(Meronymic) eObject,
									//CLEANUP new AlloyProperty(target,map.get((Classifier)target.getEndType())),
									//CLEANUP new AlloyProperty(source,map.get((Classifier)source.getEndType()))
									new AlloyProperty(target,map.get((Classifier)target.getType())),
									new AlloyProperty(source,map.get((Classifier)source.getType()))
								)
						);
					}
					//Derivations are considered diagram-level relations that shouldn't appear in the instance level
					else if(eObject instanceof DependencyRelationshipImpl && !(eObject instanceof DerivationImpl))
					{
						relationships.add
						(
								new AlloyDependencyRelationship
								(
									(DependencyRelationship)eObject,
									//CLEANUPnew AlloyProperty(target,map.get((Classifier)target.getEndType())),
									//CLEANUPnew AlloyProperty(source,map.get((Classifier)source.getEndType()))
									new AlloyProperty(target,map.get((Classifier)target.getType())),
									new AlloyProperty(source,map.get((Classifier)source.getType()))
								)
						);
					}
					//CLEANUP else if(eObject instanceof DatatypeRelationship)
					
					//Nota: No RefOntoUML o DatatypeRelationship foi substituído por uma associação UML "comum", isto é, 
					//não é subtipo de nem uma das classes de relacionamento da OntoUML.
					else if(!(eObject instanceof DirectedBinaryAssociationImpl) && !(eObject instanceof FormalAssociationImpl) && !(eObject instanceof MaterialAssociation))
					{
						
						if(			(source.isIsReadOnly() && (target.getType() instanceof RigidSortalClassImpl || target.getType() instanceof MomentClassImpl || target.getType() instanceof DataTypeImpl))
								|| 	(target.isIsReadOnly() && (source.getType() instanceof RigidSortalClassImpl || source.getType() instanceof MomentClassImpl || source.getType() instanceof DataTypeImpl)))
							
						{
							relationships.add
							(
									new AlloySigDatatypeRelationship
									(
										//CLEANUP (DatatypeRelationship)eObject,
										(DirectedBinaryAssociation)eObject,
										new AlloyProperty(target,map.get((Classifier)target.getType())),
										new AlloyProperty(source,map.get((Classifier)source.getType()))
									)
							);
						}
						else									
							relationships.add
							(
									new AlloyDatatypeRelationship
									(
										//CLEANUP (DatatypeRelationship)eObject,
										(DirectedBinaryAssociation)eObject,
										new AlloyProperty(target,map.get((Classifier)target.getType())),
										new AlloyProperty(source,map.get((Classifier)source.getType()))
									)
							);
					}
				}
			}
			
			
		}
		
	}
		
	private String generateAlloy() throws IOException
	{
		StringWriter out = new StringWriter();
		
		//now we'll print the .als file.
		out.write("open util/ordering[State]\n");
		
		//Datatypes:
		for(AlloyDatatype dt: datatypes)
		{
			dt.printDeclaration(out);					
		}
		
		//the sigClasses (Rigid sortals and moments)
		for(AlloySigClass c: sigClasses)
		{
			c.printSig(out);
			c.printSigRules(out);
		}
		//the categories
		for(AlloyCategory c: categories)
		{
			c.printClassDeclaration(out);			
		}
		
		//Now we'll write the State signature, and define
		//the existence of the sigClasses and declare non-rigid classes 
		//as fields of the State signature.
		out.write("sig State\n{\n\texists: set ");
		{//brackets to make the iterator a local variable
			Iterator<AlloySigClass> sIt = sigClasses.iterator();
			for(AlloySigClass c =sIt.next();;c=sIt.next() )
			{
				out.write(c.getName());
				if(sIt.hasNext())
				{
					out.write(" + ");						
				}
				else
				{
					out.write(",\n");
					break;
				}
			}
		}
		
		//definition of non rigid classes
						
		for(int level = 1,max=1;level<=max;level++)
		{
		//we'll write the sortal classes first, level by level, then we'll write the mixin classes
			for(AlloyClass nrc : nonRigidClasses)
			{			
				if(nrc instanceof AlloyAntiRigidSortalClass)								
				{
					int nrcLevel = nrc.getLevel();
					if(nrcLevel > max)
					{
						max = nrcLevel;
					}
					else if(nrcLevel==level)
					{
						((AlloyAntiRigidSortalClass) nrc).printClassDeclaration(out);
					}
				}
								
			}
		}
					
		
		//definition of mixins
			
		for(int level = 1,max=1;level<=max;level++)
		{
			//now we'll write the mixin classes
			for(AlloyClass nrc : nonRigidClasses)
			{					
				if(nrc instanceof AlloyNonRigidMixinClass)								
				{
					int nrcLevel = nrc.getLevel();
					if(nrcLevel > max)
					{
						max = nrcLevel;
					}
					else if(nrcLevel==level)
					{
						((AlloyNonRigidMixinClass) nrc).printClassDeclaration(out);
					}
				}
								
			}
		}
		
		
		//datatypeassociations and Meronymics
		for(AlloyRelationship rel: relationships)
		{
			rel.writeDeclaration(out);
		}
		
		
		out.write("\n}\t{\n");
		
		//sigClasses' continuous existence
		out.write("\tall x: exists | x not in this.next.@exists implies x not in this.^next.@exists\n");
		
		//generalization sets
		for(AlloyGeneralizationSet gs: gsets)
		{
			//disjointness
			if(gs.isDisjoint())
			{
				out.write("\tdisj[");
				Iterator<AlloyClassifier> it = gs.getSubsumers().iterator();
				while(true)
				{
					out.write(it.next().getStateName());
					if(it.hasNext())out.write(",");
					else break;
				}
				out.write("]\n");
			}
			if(gs.isCovering())
			{
				//genereal classifier = subsumer0 + subsumer1 ...
				
				out.write("\t"+gs.getGeneral().getStateName()+" = ");
				Iterator<AlloyClassifier> it = gs.getSubsumers().iterator();
				while(true)
				{
					out.write(it.next().getStateName());
					if(it.hasNext())out.write("+");
					else break;
				}
				out.write("\n");						
			}
		
		
		}
		//Now we'll deal with relationship cardinality constraints and relationship generalizations (class generalization has been delt with while declaring the classes)
		for(AlloyRelationship rel: relationships)
		{
			//in case this relation generalizes another, we'll state that it is a subset of the general relation
			//rel.printGeneralization(out);
			rel.printExtraConstraints(out);					
			/*if(rel instanceof Meronymic)
			{
				//whole == source
				//part == target
				Meronymic me = (Meronymic)rel;
				if(!me.isIsShareable())
				{
					//black diamond
					out.write("\tall x: "+AlloyClass.getStateName(target)+" | lone "+AlloyDirectedBinaryRelationship.DBRelName(rel)+".x\n");
				}
				if(me.isIsImmutablePart())
				{
					//part is essential to the whole
					//whole is dependent of a specific part
					out.write("\tall x: "+AlloyClass.getStateName(source)+" | "+AlloyDirectedBinaryRelationship.DBRelName(rel)+"[x] = (State.@"+AlloyDirectedBinaryRelationship.DBRelName(rel)+")[x]\n");
				}
				if(me.isIsImmutableWhole())
				{
					//part is dependent of a specific whole
					// == inseparable???
					out.write("\tall x: "+AlloyClass.getStateName(target)+" | "+AlloyDirectedBinaryRelationship.DBRelName(rel)+".x = State.@"+AlloyDirectedBinaryRelationship.DBRelName(rel)+".x\n");
				}
			}*/
			
		}
		//extra constraints for the existentially dependent relationships as well
		for(AlloySigClass sc: sigClasses)
		{
			for(AlloyRelationship rel: sc.getExistentiallyDependentRelationships())
			{
				rel.printExtraConstraints(out);
				//below, we guarantee the mediated objects to belong to the mediated class 
				out.write("\t"+((AlloyDependencyRelationship)rel).getTarget().getEndType().getStateName()+ " = exists."+rel.getStateName()+"\n");//here I was using exists:>Name but alloy interprets as exists:>(Name.prop)
			}
		}
		
		//closing the State signature rule section
		out.write("\t}\n");
		
		//theme setting help
		out.write("fun visible : State->univ\n{\n\texists");
		for(AlloyDatatype x: datatypes)
		{
			out.write(" + State->"+x.getName());
		}
		out.write("\n}\n");
		
		out.write("fact all_individuals_exist_at_some_point\n{\n\t");
		{//brackets to make the iterator a local variable
			Iterator<AlloySigClass> sIt = sigClasses.iterator();
			for(AlloySigClass c =sIt.next();;c=sIt.next() )
			{
				out.write(c.getName());
				if(sIt.hasNext())
				{
					out.write(" + ");						
				}
				else
				{
					out.write(" = State.exists\n}\n");
					break;
				}
			}
		}
		
		out.write("run{} for 5\n");
		
		//
		for(AlloyNonRigidClass x: nonRigidClasses)
		{
			if(x instanceof AlloyRole)((AlloyRole)(x)).writePredicateDynamicClassification(out);
		}
		for(AlloyRelationship r: relationships)
		{
			if(r instanceof AlloyMeronymic) ((AlloyMeronymic) r).writePredicateShareability(out);
		}
		
		return out.toString();
	}
	
}
