package br.ufes.inf.nemo.ontouml2uml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

public class TemporalStructureGenerator {

	// from pure UML
	public org.eclipse.uml2.uml.Package umlRoot;	
	public org.eclipse.uml2.uml.UMLFactory ufactory;
	public HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap;
	
	// temporal inclusion	
	public HashMap<RefOntoUML.Element, ArrayList<org.eclipse.uml2.uml.Element>> tmap = new HashMap<RefOntoUML.Element, ArrayList<org.eclipse.uml2.uml.Element>>();
	public String tlog = new String();	
	@Deprecated
	public int assoc_counter=0;
	@Deprecated
	public int attr_counter=0;
		
	public HashMap<RefOntoUML.Element, ArrayList<org.eclipse.uml2.uml.Element>> getMap()
	{
		return tmap;
	}
	
	public TemporalStructureGenerator(org.eclipse.uml2.uml.Package umlRoot, org.eclipse.uml2.uml.UMLFactory ufactory, HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap)
	{			
		this.umlRoot=umlRoot;
		this.ufactory = ufactory;
		this.umap = umap;		
	}
	
	public String getTemporalLog()
	{
		return tlog;
	}
	
	public void outln(String text)
	{
		tlog += text+"\n";
	}
	
	public void out(String text)
	{
		tlog += text;
	}
	
	public void run()
	{
		outln("Generating temporal structure...");

		ArrayList<Classifier> topLevels = new ArrayList<Classifier>();
		getTopLevelTypes(umlRoot,topLevels);
		org.eclipse.uml2.uml.Class umlIndividual = createIndividualSuperType(topLevels);
		
		ArrayList<Classifier> classes = new ArrayList<Classifier>();
		getAllClasses(umlRoot,classes);

		ArrayList<org.eclipse.uml2.uml.Property> attributes = new ArrayList<org.eclipse.uml2.uml.Property>();
		getAttributes(umlRoot,attributes);
		
		// Judged conceptually unnecessary
		// org.eclipse.uml2.uml.Class umlWorld = createWorldHierarchy(umlRoot);
		
		org.eclipse.uml2.uml.Class umlWorld = umlRoot.createOwnedClass("World", false);
		createWorldAccessibilityRelation(umlWorld);
		
		org.eclipse.uml2.uml.Class umlTimePath = createPathStructure(umlWorld, umlRoot);				
		
		createWorldOperations(umlWorld,umlTimePath,umlIndividual);		
		
		createPathOperations(umlWorld,umlTimePath);
		
		ArrayList<Classifier> newTopLevels = new ArrayList<Classifier>();
		//newTopLevels.addAll(topLevels);
		newTopLevels.add(umlIndividual);
		createTopLevelAllInstancesOperation(umlWorld, classes);	
		createTopLevelExistenceOperations(umlWorld, newTopLevels);			
		createOclIsNewOperation(umlWorld,newTopLevels);
		
		createTemporalAttributeAccessOperations(umlWorld, attributes);

		// Unnecessary since end-points in UML are owned attributes
		// ArrayList<Association> assocList = new ArrayList<Association>();
		// getAssociations(umlRoot,assocList);
		// createTemporalNavigationOperations(umlWorld, assocList);
		
		// Unnecessary to create the existence association between World and the types (classes,associations and attributes) existent in that World
		// We found a workaround for this i.e. we used UML/OCL pre-defined operations
		//createExistenceRelations(umlWorld,topLevels);
		// reifyAssociations(umlWorld, topLevels);
		// reifyAttributes(umlWorld,attributes);
					
		outln("Temporal generation finished.");
	}
	
	public org.eclipse.uml2.uml.Class createIndividualSuperType(ArrayList<Classifier> toplevels)
	{
		org.eclipse.uml2.uml.Class umlIndividual = umlRoot.createOwnedClass("Individual", false);
		
		org.eclipse.uml2.uml.GeneralizationSet genSet = ufactory.createGeneralizationSet();
		genSet.setIsDisjoint(true);
		genSet.setIsCovering(true);
		umlRoot.getPackagedElements().add(genSet);
		
		ArrayList<Generalization> gens = new ArrayList<Generalization>();
		for(Classifier c: toplevels)
		{
			org.eclipse.uml2.uml.Generalization gen = ufactory.createGeneralization();
			gen.setGeneral(umlIndividual);
			gen.setSpecific(c);
			gens.add(gen);
			gen.getGeneralizationSets().add(genSet);	
			
			outln(gen);
		}
		
		outln(genSet);
		
		genSet.getGeneralizations().addAll(gens);
		return umlIndividual;
		
	}
	
	public void createTopLevelExistenceOperations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> topLevels)
	{
		org.eclipse.uml2.uml.PrimitiveType pt = getPrimitiveBooleanType();
		
		EList<String> parameters = new BasicEList<String>();
		parameters.add("w");
		
		EList<org.eclipse.uml2.uml.Type> typeParameters = new BasicEList<org.eclipse.uml2.uml.Type>();
		typeParameters.add(umlWorld);
		
		for(Classifier c: topLevels)
		{
			if (c instanceof org.eclipse.uml2.uml.Class)
			{
				org.eclipse.uml2.uml.Class class_ = ((org.eclipse.uml2.uml.Class)c);				
				org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation("existsIn", parameters, typeParameters, pt);
				outln(op);								
			}			
		}
	}
	
	public void createOclIsNewOperation(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> topLevels)
	{
		org.eclipse.uml2.uml.PrimitiveType pt = getPrimitiveBooleanType();
		
		EList<String> parameters = new BasicEList<String>();
		parameters.add("w");
		
		EList<org.eclipse.uml2.uml.Type> typeParameters = new BasicEList<org.eclipse.uml2.uml.Type>();
		typeParameters.add(umlWorld);
		
		for(Classifier c: topLevels)
		{
			if (c instanceof org.eclipse.uml2.uml.Class)
			{
				org.eclipse.uml2.uml.Class class_ = ((org.eclipse.uml2.uml.Class)c);				
				org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation("oclIsNew", parameters, typeParameters, pt);
				outln(op);
			}			
		}
	}
	
	public void createTopLevelAllInstancesOperation(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> classes)
	{		
		EList<String> parameters = new BasicEList<String>();
		parameters.add("w");
		
		EList<org.eclipse.uml2.uml.Type> typeParameters = new BasicEList<org.eclipse.uml2.uml.Type>();
		typeParameters.add(umlWorld);
		
		for(Classifier c: classes)
		{
			if (c instanceof org.eclipse.uml2.uml.Class)
			{
				org.eclipse.uml2.uml.Class class_ = ((org.eclipse.uml2.uml.Class)c);				
				org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation("allInstances", parameters, typeParameters, class_);
				op.setLower(0);
				op.setUpper(-1);
				op.setIsStatic(true);
				outln(op);				
			}
		}
	}
	
	public void outln(org.eclipse.uml2.uml.Operation op)
	{
		outln("UML:Operation :: "+"name="+op.getName()+", type="+op.getType().getName()+", lower="+op.getLower()+", upper="+op.getUpper()+"");		
	}
	
	public void createPathOperations(org.eclipse.uml2.uml.Class umlWorld, org.eclipse.uml2.uml.Class umlTimePath)
	{
		org.eclipse.uml2.uml.Operation worldsOp = umlTimePath.createOwnedOperation("worlds", null, null, umlWorld);
		worldsOp.setLower(1);
		worldsOp.setUpper(-1);
		
		outln(worldsOp);
		
		org.eclipse.uml2.uml.Operation pathsOp = umlWorld.createOwnedOperation("paths", null, null, umlTimePath);
		pathsOp.setLower(1);
		pathsOp.setUpper(-1);
		
		outln(pathsOp);
	}
	
	public void createWorldOperations(org.eclipse.uml2.uml.Class umlWorld, org.eclipse.uml2.uml.Class umlTimePath, org.eclipse.uml2.uml.Class umlIndividual)
	{	
		org.eclipse.uml2.uml.Operation previousOp = umlWorld.createOwnedOperation("previous", null, null, umlWorld);
		previousOp.setLower(0);
		previousOp.setUpper(1);
		
		outln(previousOp);

		org.eclipse.uml2.uml.Operation allPreviousOp = umlWorld.createOwnedOperation("allPrevious", null, null, umlWorld);
		allPreviousOp.setLower(0);
		allPreviousOp.setUpper(-1);
		
		outln(allPreviousOp);
		
		EList<String> parameters = new BasicEList<String>();
		parameters.add("w");		
		EList<org.eclipse.uml2.uml.Type> typeParameters = new BasicEList<org.eclipse.uml2.uml.Type>();
		typeParameters.add(umlWorld);
		
		org.eclipse.uml2.uml.Operation untilpreviousOp = umlWorld.createOwnedOperation("allPrevious", parameters, typeParameters, umlWorld);
		untilpreviousOp.setLower(0);
		untilpreviousOp.setUpper(-1);		
		outln(untilpreviousOp);
				
		org.eclipse.uml2.uml.Operation nextOp = umlWorld.createOwnedOperation("next", null, null, umlWorld);
		nextOp.setLower(0);
		nextOp.setUpper(-1);
		
		outln(nextOp);
		
		org.eclipse.uml2.uml.Operation directNextOp = umlWorld.createOwnedOperation("allNext", null, null, umlWorld);
		directNextOp.setLower(0);
		directNextOp.setUpper(-1);
		
		outln(directNextOp);
		
		org.eclipse.uml2.uml.Operation untilnextOp = umlWorld.createOwnedOperation("allNext", parameters, typeParameters, umlWorld);
		untilnextOp.setLower(0);
		untilnextOp.setUpper(-1);		
		outln(untilnextOp);
						
		EList<String> paramList = new BasicEList<String>();
		paramList.add("p");		
		EList<org.eclipse.uml2.uml.Type> typeParamList = new BasicEList<org.eclipse.uml2.uml.Type>();
		typeParamList.add(umlTimePath);
		
		org.eclipse.uml2.uml.Operation untilnextOpPath = umlWorld.createOwnedOperation("allNext", paramList, typeParamList, umlTimePath);
		untilnextOpPath.setLower(0);
		untilnextOpPath.setUpper(-1);
		
		outln(untilnextOpPath);
		
		org.eclipse.uml2.uml.PrimitiveType pt = getPrimitiveBooleanType();
		
		org.eclipse.uml2.uml.Operation hasPreviousOp = umlWorld.createOwnedOperation("hasPrevious", null, null, pt);
		hasPreviousOp.setLower(1);
		hasPreviousOp.setUpper(1);
		
		outln(hasPreviousOp);
		
		org.eclipse.uml2.uml.Operation hasNextOp = umlWorld.createOwnedOperation("hasNext", null, null, pt);
		hasNextOp.setLower(1);
		hasNextOp.setUpper(1);
		
		org.eclipse.uml2.uml.Operation isTerminalOp = umlWorld.createOwnedOperation("isTerminal", null, null, pt);
		isTerminalOp.setLower(1);
		isTerminalOp.setUpper(1);
		
		outln(isTerminalOp);
		
		org.eclipse.uml2.uml.Operation isOriginOp = umlWorld.createOwnedOperation("isOrigin", null, null, pt);
		isOriginOp.setLower(1);
		isOriginOp.setUpper(1);
		
		outln(isOriginOp);
		
		org.eclipse.uml2.uml.Operation allIndividualsOp = umlWorld.createOwnedOperation("allIndividuals", null, null, umlIndividual);
		allIndividualsOp.setLower(0);
		allIndividualsOp.setUpper(-1);
		
		outln(allIndividualsOp);
	}
	
	public org.eclipse.uml2.uml.PrimitiveType getPrimitiveBooleanType()
	{
		ArrayList<PrimitiveType> ptList = new ArrayList<PrimitiveType>();
		getPrimitiveBoolean(umlRoot, ptList);
		org.eclipse.uml2.uml.PrimitiveType pt=null;
		if(ptList.size()==0){
			pt = ufactory.createPrimitiveType();
			pt.setName("Boolean");
			umlRoot.getPackagedElements().add(pt);
		}else{
			pt = ptList.get(0);
		}	
		return pt;
	}
	
	private void getPrimitiveBoolean(org.eclipse.uml2.uml.Package umlRoot, ArrayList<org.eclipse.uml2.uml.PrimitiveType> ptList)
	{
		for(PackageableElement pe: umlRoot.getPackagedElements())
		{
			if (pe instanceof org.eclipse.uml2.uml.PrimitiveType){
				org.eclipse.uml2.uml.PrimitiveType type = (org.eclipse.uml2.uml.PrimitiveType)pe;
				if (type.getName().compareToIgnoreCase("boolean")==0) ptList.add(type);
			}			
			if (pe instanceof org.eclipse.uml2.uml.Package){
				getPrimitiveBoolean((org.eclipse.uml2.uml.Package)pe,ptList);
			}
		}			
	}
	
	public void getAttributes (org.eclipse.uml2.uml.Package umlRoot, ArrayList<org.eclipse.uml2.uml.Property> properties)
	{
		for(PackageableElement pe: umlRoot.getPackagedElements())
		{
			if (pe instanceof org.eclipse.uml2.uml.Class){
				org.eclipse.uml2.uml.Class type = (org.eclipse.uml2.uml.Class)pe;
				properties.addAll(type.getOwnedAttributes());
			}
			if (pe instanceof org.eclipse.uml2.uml.DataType){
				org.eclipse.uml2.uml.DataType type = (org.eclipse.uml2.uml.DataType)pe;
				properties.addAll(type.getOwnedAttributes());
			}
			if (pe instanceof org.eclipse.uml2.uml.Package){
				getAttributes((org.eclipse.uml2.uml.Package)pe,properties);
			}
		}	
	}
	
	public void createTemporalAttributeAccessOperations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Property> attrList)
	{
		for(Property attr: attrList)
		{
			EList<String> parameters = new BasicEList<String>();
			parameters.add("w");
			
			EList<org.eclipse.uml2.uml.Type> typeParameters = new BasicEList<org.eclipse.uml2.uml.Type>();
			typeParameters.add(umlWorld);
			
			Type owner = (Type)attr.eContainer();
			Type type = attr.getType();
			
			if(attr.getName()!=null && !attr.getName().isEmpty() && owner != null)
			{
				if(owner instanceof org.eclipse.uml2.uml.Class){
					org.eclipse.uml2.uml.Class class_ = (org.eclipse.uml2.uml.Class)owner;
					
					org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation(attr.getName(), parameters, typeParameters, type);
					op.setUpper(attr.getUpper());
					op.setLower(attr.getLower());
					outln(op);
					
//					we decide that the world parameter must always be specified
//					org.eclipse.uml2.uml.Operation op2 = class_.createOwnedOperation(attr.getName(), null, null, type);
//					if(attr.isReadOnly()){ 
//						op2.setLower(attr.getLower()); 
//						op2.setUpper(attr.getUpper()); 
//					}else{						
//						op2.setLower(attr.getLower());
//						op2.setUpper(-1);
//					}					
//					outln(op2);
					
					ArrayList<org.eclipse.uml2.uml.Element> list = new ArrayList<org.eclipse.uml2.uml.Element>();
					list.add(op);
//					list.add(op2);
					tmap.put(getKey(attr), list);
				}
				if(owner instanceof org.eclipse.uml2.uml.DataType){
					org.eclipse.uml2.uml.DataType class_ = (org.eclipse.uml2.uml.DataType)owner;
					
					org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation(attr.getName(), parameters, typeParameters, type);
					op.setUpper(attr.getUpper());
					op.setLower(attr.getLower());
					outln(op);
					
//					we decide that the world parameter must always be specified
//					org.eclipse.uml2.uml.Operation op2 = class_.createOwnedOperation(attr.getName(), null, null, type);
//					if(attr.isReadOnly()){ 
//						op2.setLower(attr.getLower()); 
//						op2.setUpper(attr.getUpper()); 
//					}else{						
//						op2.setLower(attr.getLower());
//						op2.setUpper(-1);
//					}
//					outln(op2);

					ArrayList<org.eclipse.uml2.uml.Element> list = new ArrayList<org.eclipse.uml2.uml.Element>();
					list.add(op);
//					list.add(op2);
					tmap.put(getKey(attr), list);
				}
			}
		}
	}

	public RefOntoUML.Element getKey(org.eclipse.uml2.uml.Element value) 
    {
        for (Entry<RefOntoUML.Element,org.eclipse.uml2.uml.Element> entry : umap.entrySet()) 
        {
            if (value.equals(entry.getValue())) 
            {
                return entry.getKey();
            }
        }
        return null;
    }

	public void outln(org.eclipse.uml2.uml.GeneralizationSet genSet)
	{
        out("UML:GeneralizationSet :: ");		
        for  (org.eclipse.uml2.uml.Generalization g : genSet.getGeneralizations()) {        	 
        	out(g.getSpecific().getName()+"->"+g.getGeneral().getName()+"  "); 
        }	
        out("isCovering="+genSet.isCovering()+", isDisjoint="+genSet.isDisjoint()+"\n");
	}
	
	public void outln(org.eclipse.uml2.uml.Generalization genCurrent)
	{
		outln("UML:Generalization :: "+genCurrent.getSpecific().getName()+"->"+genCurrent.getGeneral().getName());
	}
	
	public void outln(org.eclipse.uml2.uml.Association association)
	{		
		org.eclipse.uml2.uml.Property p1 = association.getMemberEnds().get(0);
		org.eclipse.uml2.uml.Property p2 = association.getMemberEnds().get(1);
		
		outln("UML:Association :: name="+association.getName()+", visibility="+association.getVisibility().getName()+", isAbstract="+association.isAbstract()+", isDerived="+association.isDerived());
        outln(p1);
        outln(p2);
	}

	public void outln(org.eclipse.uml2.uml.Property property)
	{
		outln("UML:Property :: "+"name="+property.getName()+", isDerived="+property.isDerived()+", lower="+property.getLower()+", upper="+property.getUpper()+
	    ", type="+property.getType().getName()+", aggregationkind="+property.getAggregation().getName()+", visibility="+property.getVisibility().getName()+            
	    ", isLeaf="+property.isLeaf()+", isStatic="+property.isStatic()+", isReadOnly="+property.isReadOnly());
	}
	
	public void getTopLevelTypes(org.eclipse.uml2.uml.Package umlRoot, ArrayList<Classifier> result)
	{		
		for(PackageableElement pe: umlRoot.getPackagedElements())
		{
			if (pe instanceof org.eclipse.uml2.uml.Type){
				org.eclipse.uml2.uml.Classifier type = (org.eclipse.uml2.uml.Classifier)pe;
				if (type.getGeneralizations().size()==0) {
					if(type instanceof org.eclipse.uml2.uml.Class) result.add(type);				
				}
			}
			if (pe instanceof org.eclipse.uml2.uml.Package){
				getTopLevelTypes((org.eclipse.uml2.uml.Package)pe,result);
			}
		}	
	}
	
	public void getAllClasses(org.eclipse.uml2.uml.Package umlRoot, ArrayList<Classifier> result)
	{		
		for(PackageableElement pe: umlRoot.getPackagedElements())
		{
			if (pe instanceof org.eclipse.uml2.uml.Class || pe instanceof org.eclipse.uml2.uml.DataType){
				org.eclipse.uml2.uml.Classifier type = (org.eclipse.uml2.uml.Classifier)pe;
				result.add(type);
			}
			if (pe instanceof org.eclipse.uml2.uml.Package){
				getTopLevelTypes((org.eclipse.uml2.uml.Package)pe,result);
			}
		}	
	}
	
	public org.eclipse.uml2.uml.Class createPathStructure(org.eclipse.uml2.uml.Class umlWorld, org.eclipse.uml2.uml.Package umlRoot)
	{
		org.eclipse.uml2.uml.Class umlTimePath = umlRoot.createOwnedClass("Path", false);
		
		boolean end1IsNavigable = true;
		String end1Name = "worlds";
		org.eclipse.uml2.uml.AggregationKind agg1 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		int end1Lower = 1;
		int end1Upper = -1;				
		boolean end2IsNavigable = true;
		org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		String end2Name = "paths";
		int end2Lower = 1;
		int end2Upper = -1;				
		org.eclipse.uml2.uml.Association existenceRelation = umlTimePath.createAssociation(
			end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, (org.eclipse.uml2.uml.Type)umlWorld,
			end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
		);
		umlRoot.getPackagedElements().add(existenceRelation);
		
		outln(existenceRelation);
		
		return umlTimePath;
	}
	
	public void createWorldAccessibilityRelation (org.eclipse.uml2.uml.Class umlWorld)
	{
		boolean end1IsNavigable = true;
		String end1Name = "previous";
		org.eclipse.uml2.uml.AggregationKind agg1 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		int end1Lower = 0;
		int end1Upper = 1;
		
		boolean end2IsNavigable = true;
		org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		String end2Name = "next";
		int end2Lower = 0;
		int end2Upper = -1;
		
		org.eclipse.uml2.uml.Association worldAccessibility = umlWorld.createAssociation(
			end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, (org.eclipse.uml2.uml.Type)umlWorld,
			end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
		);
		
		org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)umlWorld.getOwner();
		umlRoot.getPackagedElements().add(worldAccessibility);
		
		outln(worldAccessibility);
	}
	
	// ***************************************************
	// Deprecated methods 
	// ***************************************************

	@Deprecated
	public org.eclipse.uml2.uml.Class createWorldHierarchy(org.eclipse.uml2.uml.Package umlRoot)
	{
		org.eclipse.uml2.uml.Class umlWorld = umlRoot.createOwnedClass("World", false);
		org.eclipse.uml2.uml.Class umlCurrentWorld = umlRoot.createOwnedClass("Current World", false);
		org.eclipse.uml2.uml.Class umlPastWorld = umlRoot.createOwnedClass("Past World", false);
		org.eclipse.uml2.uml.Class umlCounterfactualWorld = umlRoot.createOwnedClass("Counterfactual World", false);
		org.eclipse.uml2.uml.Class umlFutureWorld = umlRoot.createOwnedClass("FutureWorld", false);
		
		org.eclipse.uml2.uml.Generalization genCurrent = ufactory.createGeneralization();
		genCurrent.setGeneral(umlWorld);
		genCurrent.setSpecific(umlCurrentWorld);
		
		org.eclipse.uml2.uml.Generalization genPast = ufactory.createGeneralization();
		genPast.setGeneral(umlWorld);
		genPast.setSpecific(umlPastWorld);
		
		org.eclipse.uml2.uml.Generalization genFuture = ufactory.createGeneralization();
		genFuture.setGeneral(umlWorld);
		genFuture.setSpecific(umlFutureWorld);	
		
		org.eclipse.uml2.uml.Generalization genCounterfactual = ufactory.createGeneralization();
		genCounterfactual.setGeneral(umlWorld);
		genCounterfactual.setSpecific(umlCounterfactualWorld);
		
		org.eclipse.uml2.uml.GeneralizationSet genSet = ufactory.createGeneralizationSet();
		genSet.getGeneralizations().add(genCurrent);
		genSet.getGeneralizations().add(genPast);
		genSet.getGeneralizations().add(genFuture);
		genSet.getGeneralizations().add(genCounterfactual);
		genSet.setIsDisjoint(true);
		genSet.setIsCovering(true);
		umlRoot.getPackagedElements().add(genSet);
		
		outln("UML:Class :: name="+umlWorld.getName()+", visibility="+umlWorld.getVisibility().getName()+", isAbstract="+umlWorld.isAbstract());
		outln("UML:Class :: name="+umlCurrentWorld.getName()+", visibility="+umlCurrentWorld.getVisibility().getName()+", isAbstract="+umlCurrentWorld.isAbstract());
		outln("UML:Class :: name="+umlPastWorld.getName()+", visibility="+umlPastWorld.getVisibility().getName()+", isAbstract="+umlPastWorld.isAbstract());
		outln("UML:Class :: name="+umlFutureWorld.getName()+", visibility="+umlFutureWorld.getVisibility().getName()+", isAbstract="+umlFutureWorld.isAbstract());
		outln("UML:Class :: name="+umlCounterfactualWorld.getName()+", visibility="+umlCounterfactualWorld.getVisibility().getName()+", isAbstract="+umlCounterfactualWorld.isAbstract());
        outln("UML:Generalization :: "+genCurrent.getSpecific().getName()+"->"+genCurrent.getGeneral().getName());
        outln("UML:Generalization :: "+genPast.getSpecific().getName()+"->"+genPast.getGeneral().getName());
        outln("UML:Generalization :: "+genFuture.getSpecific().getName()+"->"+genFuture.getGeneral().getName());
        outln("UML:Generalization :: "+genCounterfactual.getSpecific().getName()+"->"+genCounterfactual.getGeneral().getName());
        outln(genSet);
        
		return umlWorld;
	}
	
	@SuppressWarnings("unused")
	@Deprecated
	private void getAssociations(org.eclipse.uml2.uml.Package umlRoot, ArrayList<Association> result)
	{
		for(PackageableElement pe: umlRoot.getPackagedElements())
		{
			if (pe instanceof org.eclipse.uml2.uml.Association){
				org.eclipse.uml2.uml.Association assoc = (org.eclipse.uml2.uml.Association)pe;
				result.add(assoc);
			}			
			if (pe instanceof org.eclipse.uml2.uml.Package){
				getAssociations((org.eclipse.uml2.uml.Package)pe,result);
			}
		}			
	}
	
	@Deprecated
	public void reifyAttributes(org.eclipse.uml2.uml.Class umlWorld, ArrayList<org.eclipse.uml2.uml.Property> properties)
	{
		for(org.eclipse.uml2.uml.Property top: properties)
		{
			if(top.getAssociation()==null)
			{
				ArrayList<org.eclipse.uml2.uml.Element> tempList =  new ArrayList<org.eclipse.uml2.uml.Element>();
								
				//reify it into a class
				org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)top.eContainer().eContainer();
				org.eclipse.uml2.uml.Class reifiedAttr = null;
				if (top.getName()==null || top.getName().isEmpty()) { top.setName("Attribute"+attr_counter); }
				reifiedAttr = umlRoot.createOwnedClass(top.getName(), false);				
				attr_counter++; 
				
				tempList.add(reifiedAttr);
				
				outln("UML:Class :: name="+reifiedAttr.getName()+", visibility="+reifiedAttr.getVisibility().getName()+", isAbstract="+reifiedAttr.isAbstract());
				
				//imitate first ternary link i.e., existence.
				createExistenceRelation(umlWorld,reifiedAttr);
				
				//imitate second ternary link i.e., property representing the attribute 
				boolean end1IsNavigable = true;
				String end1Name = "attr"+attr_counter;
				org.eclipse.uml2.uml.AggregationKind agg1 = top.getAggregation();
				int end1Lower = top.getLower();
				int end1Upper = top.getUpper();				
				boolean end2IsNavigable = true;
				org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
				String end2Name = ((org.eclipse.uml2.uml.NamedElement)top.eContainer()).getName();
				if (end2Name==null) end2Name = "";
				int end2Lower = 1;
				int end2Upper = 1;
				org.eclipse.uml2.uml.Association sourceRelation = ((org.eclipse.uml2.uml.Type)top.eContainer()).createAssociation(
					end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, reifiedAttr,
					end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
				);
				umlRoot.getPackagedElements().add(sourceRelation);		
				
				tempList.add(sourceRelation);				
				
				outln(sourceRelation);

				//imitate third ternary link i.e., an attribute
				org.eclipse.uml2.uml.Property attribute = ufactory.createProperty();
				attribute.setName(top.getName());
				attribute.setType(top.getType());
				org.eclipse.uml2.uml.LiteralInteger lowerValue = ufactory.createLiteralInteger();
				org.eclipse.uml2.uml.LiteralUnlimitedNatural upperValue = ufactory.createLiteralUnlimitedNatural();
				lowerValue.setValue(top.getLower());
				upperValue.setValue(top.getUpper());         
				attribute.setUpperValue(upperValue);
				attribute.setLowerValue(lowerValue);
				attribute.setAggregation(top.getAggregation());
				reifiedAttr.getOwnedAttributes().add(attribute);
				
				tempList.add(attribute);
				
				outln(attribute);
				
				tmap.put(getKey(top), tempList);
			}
		}
	}
	
	@Deprecated
	public void createExistenceRelation(org.eclipse.uml2.uml.Class umlWorld, org.eclipse.uml2.uml.Type class_)
	{
		boolean end1IsNavigable = true;
		String end1Name = "worlds";
		org.eclipse.uml2.uml.AggregationKind agg1 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		int end1Lower = 1;
		int end1Upper = -1;				
		boolean end2IsNavigable = true;
		org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		String end2Name = "exists";
		int end2Lower = 0;
		int end2Upper = -1;				
		org.eclipse.uml2.uml.Association existenceRelation = class_.createAssociation(
			end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, (org.eclipse.uml2.uml.Type)umlWorld,
			end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
		);
		umlRoot.getPackagedElements().add(existenceRelation);
		
		outln(existenceRelation);
	}
	
	@Deprecated
	public void createTemporalNavigationOperations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Association> assocList)
	{
		for(Association assoc: assocList)		
		{			
			EList<String> parameters = new BasicEList<String>();
			parameters.add("w");
			
			EList<org.eclipse.uml2.uml.Type> typeParameters = new BasicEList<org.eclipse.uml2.uml.Type>();
			typeParameters.add(umlWorld);
			
			Type src = assoc.getMemberEnds().get(0).getType();
			Type tgt = assoc.getMemberEnds().get(1).getType();
			
			// tgtType::srcRoleName(w: World) : Set(srcType)
			if(assoc.getMemberEnds().get(0).getName()!=null && !assoc.getMemberEnds().get(0).getName().isEmpty() && tgt != null)
			{
				if(tgt instanceof org.eclipse.uml2.uml.Class){
					org.eclipse.uml2.uml.Class class_ = (org.eclipse.uml2.uml.Class)tgt;
					org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation(assoc.getMemberEnds().get(0).getName(), parameters, typeParameters, src);
					op.setUpper(assoc.getMemberEnds().get(0).getUpper());
					op.setLower(assoc.getMemberEnds().get(0).getLower());
					outln(op);
				}
				else if(tgt instanceof org.eclipse.uml2.uml.DataType){
					org.eclipse.uml2.uml.DataType class_ = (org.eclipse.uml2.uml.DataType)tgt;
					org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation(assoc.getMemberEnds().get(0).getName(), parameters, typeParameters, src);
					op.setUpper(assoc.getMemberEnds().get(0).getUpper());
					op.setLower(assoc.getMemberEnds().get(0).getLower());
					outln(op);					
				}
			}			
			
			// srcType::tgtRoleName(w: World) : Set(tgtType)
			if(assoc.getMemberEnds().get(1).getName()!=null && !assoc.getMemberEnds().get(1).getName().isEmpty() && src != null)
			{
				if(src instanceof org.eclipse.uml2.uml.Class){
					org.eclipse.uml2.uml.Class class_ = (org.eclipse.uml2.uml.Class)src;
					org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation(assoc.getMemberEnds().get(1).getName(), parameters, typeParameters, tgt);
					op.setUpper(assoc.getMemberEnds().get(1).getUpper());
					op.setLower(assoc.getMemberEnds().get(1).getLower());
					outln(op);
				}
				else if(src instanceof org.eclipse.uml2.uml.DataType){
					org.eclipse.uml2.uml.DataType class_ = (org.eclipse.uml2.uml.DataType)src;
					org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation(assoc.getMemberEnds().get(1).getName(), parameters, typeParameters, tgt);
					op.setUpper(assoc.getMemberEnds().get(1).getUpper());
					op.setLower(assoc.getMemberEnds().get(1).getLower());
					outln(op);
				}
			}			
		}
	}
	
	@Deprecated
	public void reifyAssociations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> topLevels)
	{
		for(Classifier top: topLevels)
		{
			if(top instanceof org.eclipse.uml2.uml.Association)
			{
				ArrayList<org.eclipse.uml2.uml.Element> tempList =  new ArrayList<org.eclipse.uml2.uml.Element>();
				
				org.eclipse.uml2.uml.Association assoc = (org.eclipse.uml2.uml.Association)top;
				
				//reify it into a class
				org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)top.eContainer();
				org.eclipse.uml2.uml.Class reifiedAssoc = null;
				if (assoc.getName()==null || assoc.getName().isEmpty()) { assoc.setName("Association"+assoc_counter); }
				reifiedAssoc = umlRoot.createOwnedClass(assoc.getName(), false);				
				assoc_counter++; 
				
				tempList.add(reifiedAssoc);
				
				outln("UML:Class :: name="+reifiedAssoc.getName()+", visibility="+reifiedAssoc.getVisibility().getName()+", isAbstract="+reifiedAssoc.isAbstract());
				
				//imitate first ternary link i.e., existence.
				createExistenceRelation(umlWorld, reifiedAssoc);
				
				//imitate second ternary link i.e., association's source 
				org.eclipse.uml2.uml.Association sourceRelation = createSideRelation(assoc.getMemberEnds().get(0), reifiedAssoc);				
				
				tempList.add(sourceRelation);				
								
				//imitate third ternary linki.e., association's target
				org.eclipse.uml2.uml.Association targetRelation = createSideRelation(assoc.getMemberEnds().get(1), reifiedAssoc);				
				
				tempList.add(targetRelation);
								
				tmap.put(getKey(top), tempList);

				//if (key!=null) umap.remove(key);
				//EcoreUtil.delete(top);
			}
		}		
	}
	
	@Deprecated
	public org.eclipse.uml2.uml.Association createSideRelation(org.eclipse.uml2.uml.Property property, org.eclipse.uml2.uml.Class reifiedAssoc)
	{
		boolean end1IsNavigable = true;
		String end1Name = "assoc"+assoc_counter;				
		org.eclipse.uml2.uml.AggregationKind agg1 = property.getAggregation();
		int end1Lower = property.getLower();
		int end1Upper = property.getUpper();				
		boolean end2IsNavigable = true;
		org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		String end2Name = property.getName();
		if (end2Name==null) end2Name = "";
		int end2Lower = 1;
		int end2Upper = 1;

		org.eclipse.uml2.uml.Association reifiedSideRelation = property.getType().createAssociation(
			end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, reifiedAssoc,
			end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
		);
		umlRoot.getPackagedElements().add(reifiedSideRelation);
		
		outln(reifiedSideRelation);
		
		return reifiedSideRelation;
	}
	
	@Deprecated
	public void createExistenceRelations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> topLevels)
	{
		for(Classifier top: topLevels)
		{
			if(top instanceof org.eclipse.uml2.uml.Class || (top instanceof org.eclipse.uml2.uml.DataType && 
			 !(top instanceof org.eclipse.uml2.uml.PrimitiveType) && !(top instanceof org.eclipse.uml2.uml.Enumeration)))
			{
				createExistenceRelation(umlWorld, (org.eclipse.uml2.uml.Type)top);
			}
		}
	}

}
