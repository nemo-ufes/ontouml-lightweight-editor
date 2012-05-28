package refontouml2alloy.bts;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import refontouml2alloy.bts.classifiers.AlloyClassifier;
import refontouml2alloy.bts.classifiers.atoms.AlloyAntiRigidSortalClass;
import refontouml2alloy.bts.classifiers.atoms.AlloyCategory;
import refontouml2alloy.bts.classifiers.atoms.AlloyClass;
import refontouml2alloy.bts.classifiers.atoms.AlloyDatatype;
import refontouml2alloy.bts.classifiers.atoms.AlloyIsAnAtom;
import refontouml2alloy.bts.classifiers.atoms.AlloyNonRigidClass;
import refontouml2alloy.bts.classifiers.atoms.AlloyNonRigidMixinClass;
import refontouml2alloy.bts.classifiers.atoms.AlloyPhase;
import refontouml2alloy.bts.classifiers.atoms.AlloyRole;
import refontouml2alloy.bts.classifiers.atoms.AlloySigClass;
import refontouml2alloy.bts.classifiers.atoms.AlloySigClassifier;
import refontouml2alloy.bts.classifiers.atoms.AlloyStructuredDatatype;
import refontouml2alloy.bts.classifiers.atoms.AlloySubkind;
import refontouml2alloy.bts.classifiers.atoms.AlloyUltimateIdentityProvider;
import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyDatatypeRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyGeneralizationNonRigid;
import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyMeronymic;
import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloyDependencyRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigDatatypeRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigMeronymic;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs.AlloySigRelationshipToSig;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.DependencyRelationship;
import RefOntoUML.DirectedBinaryAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Model;
import RefOntoUML.NonRigidMixinClass;
import RefOntoUML.Property;
import RefOntoUML.impl.AntiRigidSortalClassImpl;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.CategoryImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.DependencyRelationshipImpl;
import RefOntoUML.impl.DerivationImpl;
import RefOntoUML.impl.DirectedBinaryAssociationImpl;
import RefOntoUML.impl.FormalAssociationImpl;
import RefOntoUML.impl.GeneralizationSetImpl;
import RefOntoUML.impl.MaterialAssociationImpl;
import RefOntoUML.impl.MeronymicImpl;
import RefOntoUML.impl.MixinClassImpl;
import RefOntoUML.impl.MomentClassImpl;
import RefOntoUML.impl.RoleImpl;
import RefOntoUML.impl.SubKindImpl;
import RefOntoUML.impl.SubstanceSortalImpl;


/*
Nota sobre DataTypes e DatatypeRelationship
Segundo Roberto, usar Datatypes em um modelo ontológico é inapropriado. 
Instâncias de um modelo ontológico deveriam ser entidades mais abstratas, e não dados com um enconding específico.
Portanto, ele escolheu abolir SimpleDataType e StructuredDataType do RefOntoUML, como era implementado pelo alessander
e está usando somente a classe DataType. Em UML tradicional existem duas maneiras de representar attributos, é uma questão de gosto.
Pode-se usar ownedAttribute ou uma Association com navigableEnd em direção ao DataType e colocando um nome nesse end. 
E é assim que está representado no RefOntoUML e consequentemente nessa trasnformação.
*/


//TODO: 
//	Entender melhor writeCoExistenceConstraints e Extraconstraints e definir melhor o que vai no state, o que vai no sig.
//

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
 * Sig rel pra datatype (simple) não funcionou. Devo ter esquecido de adicionar as rels no datatype...
 * 
 * Sig rels pra categories: Se a relação não é especializada, joga ela pras subclasses com o nome + um sufixo e declara a relação como uma função. Tomar cuidado pra se houverem especializações, incluir elas na função e não precisa gerar a relação especializada...
 * Dá pra fazer o mesmo com mixins, dividir a relação entre as sigrels e as staterels e uni-las na função.. exists<:sigrel + staterel
 * Obs: resolvi não implementar isso pelo trabalho que ia dar, melhor implementar tudo como sigrel logo
 * 
 * Cuidar pra Inseparable e Essential com Categories
 * 
 * Datatype associations com os dois lados readonly deve ser analisado com mais calma, acho que não tá bem implementado pra esse caso
 * Ver sigmeronymic pro outro lado sendo nrigido
 * Ver datatype association pro lado n rigido e pro lado rigido
 * 
 * Generalization entre rigid sortals já é solucionado na declaração da assinatura. Entre cat e rigid sortal tb
 * gen entre rigid sortal e anti rigid sortal precisa declarar o subset explicitamente
 * gen com nr mixin precisa declarar a igualdade dos conjuntos
 * 
 * 
 * Non rigid classes deveriam se declarar como membros da intercessão das superclasses. ?
 * Generalizations para n rigid classes e relações
 * Cardinalidades para n rigid classes
 * 
 * Falta implementar Associations!!!!
 * Idéia para tratar dependência existencial:
 * Pra sigclasses a gente declara a dependência em signature facts pra usar a relação na própria assinatura
 * Pras outras classes, no State signature. 
 * (Essential e immutable part), (inseparable e immutable whole) são basicamente a mesma coisa
 * 
 *   
 *    rationale das Relações read-only:
 *    	-Pra datatype relationship é fácil, no caso de ser uma sigclass (frequente pra structured datatype)... Se não for, usa o normal
 *      -outras relações, só sobrou todo-parte, que de novo, só funciona pra sigclasses
 *      Na relação todo-parte, se faz referência ao átomo, e na sig faz "all s: State | this in s.exists imply rel in s.(..)" completar com a classe
 *      Posso fazer Essential e Inseparable como especializações de meronymic, e quando for lidar com as diferenças de componentof, memberof, subcollectionof, subquantityof, delegar a maneira diferente de tratar pra outra classe...
 *      
 *      Obs: Não podemos lidar com herança múltipla pra datatypes (a não ser que seja adicionado uma superclasse artificial comum à todas)
 *      
 */



public class OntoUML2Alloy 
{
		
	public static boolean transformToAlloyFile(Model model, List<EObject> simulationElements, String alloyFileName, String themeFileName)
	{
		boolean ans = false;
		FileWriter out = null;
		
		HashSet<AlloySigClass> sigClasses = new HashSet<AlloySigClass>();
		HashSet<AlloyNonRigidClass> nonRigidClasses = new HashSet<AlloyNonRigidClass>();
		HashSet<AlloyCategory> categories = new HashSet<AlloyCategory>();
		HashSet<AlloyRelationship> relationships = new HashSet<AlloyRelationship>();
		HashSet<AlloyGeneralizationNonRigid> nrgeneralizations = new HashSet<AlloyGeneralizationNonRigid>();
		HashSet<AlloyGeneralizationSet> gsets = new HashSet<AlloyGeneralizationSet>();
		HashSet<AlloyDatatype> datatypes = new HashSet<AlloyDatatype>();
		
		//HashMap map is used to create relations, on the second 'for' loop.
		HashMap<Classifier,AlloyClassifier> map = new HashMap<Classifier,AlloyClassifier>();
			
		try {
			
			File file = new File(alloyFileName);
			file.deleteOnExit();
			
			out = new FileWriter(file);
			
			if(model.getPackagedElement().size() > 0)
			{
			
				for (EObject eObject : model.getPackagedElement())
				{
					AlloyClassifier x;
					
					//if(simulationElements.contains(eObject))
					{
						if(eObject instanceof SubstanceSortalImpl || eObject instanceof MomentClassImpl)
						{
							x = new AlloyUltimateIdentityProvider((RefOntoUML.Class)eObject);						
							sigClasses.add((AlloySigClass) x);
							map.put((RefOntoUML.Class)eObject, x);
						}
							
						else if(eObject instanceof SubKindImpl)
						{
							x = new AlloySubkind((SubKindImpl)eObject);
							sigClasses.add((AlloySigClass) x);
							map.put((RefOntoUML.Class)eObject, x);
						}						
						else if(eObject instanceof MixinClassImpl)
						{
							if(eObject instanceof CategoryImpl)
							{
								x = new AlloyCategory((CategoryImpl)eObject, model.eContents()); 
								categories.add((AlloyCategory) x);
								map.put((RefOntoUML.Class)eObject, x);
							}
							else
							{
								x = new AlloyNonRigidMixinClass((NonRigidMixinClass) eObject, model.eContents());
								nonRigidClasses.add((AlloyNonRigidClass) x);
								map.put((RefOntoUML.Class)eObject, x);
							}
						}
						else if(eObject instanceof AntiRigidSortalClassImpl)
						{
							if(eObject instanceof RoleImpl)
								x = new AlloyRole((AntiRigidSortalClassImpl) eObject, model.eContents());
							else 
								x = new AlloyPhase((AntiRigidSortalClassImpl) eObject, model.eContents());
							
							nonRigidClasses.add((AlloyNonRigidClass) x);
							map.put((RefOntoUML.Class)eObject, x);
						}
						else if(eObject instanceof DataTypeImpl)
						{
							//if( eObject instanceof OntoUML.SimpleDatatype)x = new AlloySimpleDatatype((Datatype) eObject); 
							//else x = new AlloyStructuredDatatype((Datatype) eObject);
							
							x = new AlloyStructuredDatatype((DataTypeImpl) eObject);
							datatypes.add((AlloyDatatype) x);
							map.put((Classifier)eObject,x);
						}
					}
				}
				
				//Now the relations
				for (EObject eObject : model.getPackagedElement())
				{
					if(eObject instanceof AssociationImpl)
					{	
						if(AlloyGeneralizationNonRigid.isGeneralizationNonRigid(eObject))
							nrgeneralizations.add(new AlloyGeneralizationNonRigid((Generalization)eObject));
						
						else if (eObject instanceof DirectedBinaryAssociationImpl)
						{
							Property target = (Property) ((DirectedBinaryAssociation)eObject).targetEnd();
							Property source = (Property) ((DirectedBinaryAssociation)eObject).sourceEnd();
							
							if (eObject instanceof MeronymicImpl)
							{
								relationships.add
								(							
										AlloyMeronymic.factory
										(
											(RefOntoUML.Meronymic)eObject,
											new AlloyAtomProperty(target,(AlloyClass) map.get((Classifier)target.getType())),
											new AlloyAtomProperty(source,(AlloyClass) map.get((Classifier)source.getType()))
										)
								);
								
							}
							
							//Derivations are considered diagram-level relations that shouldn't appear in the instance level
							else if(eObject instanceof DependencyRelationshipImpl && !(eObject instanceof DerivationImpl))
							{
								relationships.add
								(
										AlloyDependencyRelationship.factory
										(
											(DependencyRelationship)eObject,
											new AlloyAtomProperty(target,(AlloyClass) map.get((Classifier)target.getType())),
											new AlloyAtomProperty(source,(AlloyClass) map.get((Classifier)source.getType()))
										)
								);
							}
						}
						
						//DatatypeRelationship : No RefOntoUML o DatatypeRelationship foi substituído por uma associação UML "comum", isto é, 
						//Não é subtipo de nem uma das classes de relacionamento da OntoUML.
						//Senão se element = DatatypeRelationship
						else if(!(eObject instanceof FormalAssociationImpl) && !(eObject instanceof MaterialAssociationImpl))
						{
							Property firstMember = ((Association)eObject).getMemberEnd().get(0);
							Property secondMember = ((Association)eObject).getMemberEnd().get(1);
							
							relationships.add
							(
									AlloyDatatypeRelationship.factory
									(
										(Association)eObject,
										new AlloyAtomProperty(firstMember,(AlloyIsAnAtom)map.get(firstMember.getType())),
										new AlloyAtomProperty(secondMember,(AlloyIsAnAtom)map.get(secondMember.getType()))
									)
							);		
							
						}
					}
				}
				
				//Now the GeneralizationSets
				for (EObject eObject : model.getPackagedElement())
				{
					if(eObject instanceof GeneralizationSetImpl)
					{
						//We'll exclude empty generalization sets						
						if(((GeneralizationSet)eObject).getGeneralization().size()>0)
						{
							gsets.add(new AlloyGeneralizationSet((GeneralizationSet)eObject, map));							
						}
					}
				}
				{			
					Set<AlloyRelationship> to_remove = new HashSet<AlloyRelationship>();
					//adding the existentially dependent relations
					for(AlloyRelationship r: relationships)
					{
						//We have decided (to simplify) to only add as sig relationships Characterization, Mediation and datatype relationships.
						if(r instanceof AlloyDependencyRelationship)//caution: if derivations are added, this should be changed
						{
							AlloyDependencyRelationship DR = (AlloyDependencyRelationship)r;
							AlloyUltimateIdentityProvider c = (AlloyUltimateIdentityProvider)DR.getSource().getEndType();
							c.addEDRelationship(DR);
							to_remove.add(r);
						}
						else if(r instanceof AlloySigDatatypeRelationship)
						{
							AlloySigDatatypeRelationship DR = (AlloySigDatatypeRelationship)r;
							AlloySigClassifier c = DR.getHolderSigClassifier();
							c.addSigDatatypeRelationship(DR);
							to_remove.add(r);
						}
						else if(r instanceof AlloySigMeronymic)
						{
							AlloySigMeronymic DR = (AlloySigMeronymic)r;
							AlloyUltimateIdentityProvider c = (AlloyUltimateIdentityProvider) DR.getHolderSigClassifier();
							c.addEDRelationship(DR);
							to_remove.add(r);
						}
							
					}
					relationships.removeAll(to_remove);
					to_remove.clear();
				}
				
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
					c.writeSigRules(out);
					for(AlloySigRelationship asr: c.getExistentiallyDependentRelationships())
					{
						if(asr instanceof AlloySigRelationshipToSig)
						{
							((AlloySigRelationshipToSig) asr).writeCardinalitiesFact(out);
						}
						
					}
				}
				//the categories are not declared, they are simply used an union
				
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
				
				//non rigid generalizations
				//ps: rigid generalizations are covered by signature declaration or function declaration
				for(AlloyGeneralizationNonRigid nrg : nrgeneralizations)
				{
					nrg.writeStateRules(out);
				}
				
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
					rel.writeStateRules(out);
				}
				//extra constraints for the existentially dependent relationships as well
				for(AlloySigClass sc: sigClasses)
				{
					for(AlloySigRelationship rel: sc.getExistentiallyDependentRelationships())
					{
						rel.writeStateRules(out);						
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
				
				out.write("//the fact below is needed to avoid generating atoms that do not exist in any state\n");
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
				
				for(AlloyNonRigidClass x: nonRigidClasses)
				{
					if(x instanceof AlloyRole)((AlloyRole)(x)).writePredicateDynamicClassification(out);
				}
				for(AlloyRelationship r: relationships)
				{
					if(r instanceof AlloyMeronymic) ((AlloyMeronymic) r).writePredicateShareability(out);
				}
				
				ans = true;
			}
			else{
				ans = true;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			ans = false;
		} finally {
			try {
				out.flush();
				out.close();
				out = null;
				
			} catch (Exception ex) { 
				ex.printStackTrace();
				ans = false;
			}	
		}
				
		return ans;
	}
}
