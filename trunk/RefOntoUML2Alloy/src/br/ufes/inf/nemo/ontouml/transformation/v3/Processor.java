package br.ufes.inf.nemo.ontouml.transformation.v3;

import RefOntoUML.Model;
import RefOntoUML.impl.ModelImpl;


public class Processor implements br.inf.ufes.nemo.transformation.ontouml2alloy.AlloyProcessor {

	Model allObjects;
	//Transformer transformer;	
	//Map<PackageableElement, AlloyBaseElement> elementMap = new HashMap<PackageableElement, AlloyBaseElement>();

	public String process(Model model) {
		
		allObjects = model;		
		
		try {
			
			StringBuilder sb = new StringBuilder();
			sb.append("module model\n\n" + 
					"open world_structure[World]\n\n");
			
			//Expected Output:
			// - Cabeçalho
			// - Tipos e restrições/fatos
			// - World
			// - AdditionalFacts
			// - Checks
			
			if(allObjects instanceof ModelImpl)
			{
				
				/*List<Class<? extends Rule>> ruleTypes = new ArrayList<Class<? extends Rule>>();
		       
				ruleTypes.add(SubstanceSortal2Signature.class);
				
				transformer = new SimpleTransformerImpl(ruleTypes);
				
				for (PackageableElement element : allObjects.getPackagedElement()) {
					try {
						
						Object transformedElement = transformer.transform(element);
						if(transformedElement instanceof AlloyBaseElement)
						{
							elementMap.put(element, (AlloyBaseElement) transformedElement);
						
							System.out.println(transformedElement.toString());
						}
						
					} catch (Exception ex) {
						
					}
				}*/
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("unused")
	private String getWorldStructure() {
		
		String worldStructure = "some abstract sig TemporalWorld extends AlloyWorld{\r\n" + 
		"	next: set TemporalWorld -- Immediate next moments.\r\n" + 
		"}{\r\n" + 
		"	this not in this.^(@next) -- There are no temporal cicles.\r\n" + 
		"	lone ((@next).this) -- A world can be the immediate next momment of at maximum one world.\r\n" + 
		"}\r\n" + 
		"one sig CurrentWorld extends TemporalWorld {} {\r\n" + 
		"	next in FutureWorld\r\n" + 
		"}\r\n" + 
		"sig PastWorld extends TemporalWorld {} {\r\n" + 
		"	next in (PastWorld + CounterfactualWorld + CurrentWorld)\r\n" + 
		"	CurrentWorld in this.^@next -- All past worlds can reach the current moment.\r\n" + 
		"}\r\n" + 
		"sig FutureWorld extends TemporalWorld {} {\r\n" + 
		"	next in FutureWorld\r\n" + 
		"	this in CurrentWorld.^@next -- All future worlds can reach the current moment.\r\n" + 
		"}\r\n" + 
		"sig CounterfactualWorld extends TemporalWorld {} {\r\n" + 
		"	next in CounterfactualWorld\r\n" + 
		"	this in PastWorld.^@next -- All counterfactual worlds can reach the current moment.\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"assert non_local_accessibility {\r\n" + 
		"	no w: TemporalWorld | w in w.next\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"assert no_temporal_cicles {\r\n" + 
		"	no w: TemporalWorld | w in w.^next\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"assert no_joining_histories {\r\n" + 
		"	all w: TemporalWorld | #(next.w) <= 1\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"assert every_world_reach_the_current_world {\r\n" + 
		"	all w: TemporalWorld | CurrentWorld in w.^(next + ~next)\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"assert future_worlds_cannot_reach_the_current_world_by_the_future{\r\n" + 
		"	no w: FutureWorld | CurrentWorld in w.^next\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"check non_local_accessibility for 10\r\n" + 
		"check no_temporal_cicles for 10\r\n" + 
		"check no_joining_histories for 10\r\n" + 
		"check every_world_reach_the_current_world for 10\r\n" + 
		"check future_worlds_cannot_reach_the_current_world_by_the_future for 10";
		
		return worldStructure;
	}

	@SuppressWarnings("unused")
	private void trash()
	{

		/*
		@SuppressWarnings("unused")
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

		@SuppressWarnings("unused")
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

		@SuppressWarnings("unused")
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
		
		@SuppressWarnings("unused")
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
							else relationships.add
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
				if(rel instanceof Meronymic)
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
				}
				
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
		*/
		
	}
}
