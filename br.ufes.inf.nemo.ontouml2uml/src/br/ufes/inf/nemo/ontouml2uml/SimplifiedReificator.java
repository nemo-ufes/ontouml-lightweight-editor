package br.ufes.inf.nemo.ontouml2uml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

public class SimplifiedReificator extends Reificator {
	
	/** From pure UML */
	public org.eclipse.uml2.uml.Package umlRoot;	
	public org.eclipse.uml2.uml.UMLFactory ufactory;
	public HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap;
		
	/** Temporal Inclusion */	
	public HashMap<RefOntoUML.Element, ArrayList<org.eclipse.uml2.uml.Element>> tmap = new HashMap<RefOntoUML.Element, ArrayList<org.eclipse.uml2.uml.Element>>();
	public String tlog = new String();	
	
	/** Constructor */
	public SimplifiedReificator(org.eclipse.uml2.uml.Package umlRoot, org.eclipse.uml2.uml.UMLFactory ufactory, HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap)
	{			
		this.umlRoot=umlRoot;
		this.ufactory = ufactory;
		this.umap = umap;		
	}
	
	public HashMap<RefOntoUML.Element, ArrayList<org.eclipse.uml2.uml.Element>> getMap() { return tmap; }
	
	public String getTemporalLog() { return tlog; }
	
	public void outln(String text) { tlog += text+"\n"; }
	
	public void out(String text) { tlog += text; }
	
	public RefOntoUML.Element getKey(org.eclipse.uml2.uml.Element value) 
    {
        for (Entry<RefOntoUML.Element,org.eclipse.uml2.uml.Element> entry : umap.entrySet()) 
        {
            if (value.equals(entry.getValue())){ return entry.getKey(); }
        }
        return null;
    }
	
	public void run()
	{
		outln("Executing temporal reification...");

		/** Get top level classes of the model */
		ArrayList<Classifier> topLevelClasses = new ArrayList<Classifier>();
		getAllTopLevelClasses(umlRoot,topLevelClasses);
		
		/** Create "Individual" top level class */
		org.eclipse.uml2.uml.Class umlIndividual = createIndividualSuperType(topLevelClasses);
		
		/** Get all attributes of the model */
		ArrayList<org.eclipse.uml2.uml.Property> attributes = new ArrayList<org.eclipse.uml2.uml.Property>();
		getAllAttributes(umlRoot,attributes);
		
		/** Create the world accessibility relationship */
		org.eclipse.uml2.uml.Class umlWorld = umlRoot.createOwnedClass("World", false);
		createWorldAccessibilityRelation(umlWorld);
		
		/** Create the path structure related to worlds */
		org.eclipse.uml2.uml.Class umlPath = createPathStructure(umlWorld, umlRoot);				
		
		/** Create all worlds operations */
		createWorldOperations(umlWorld,umlPath,umlIndividual);		
		
		/** Create all path operations */
		createPathOperations(umlWorld,umlPath);
		
		/** Get all classes of the model */
		ArrayList<Classifier> classes = new ArrayList<Classifier>();
		getAllClasses(umlRoot,classes);
		
		/** Create "allInstances(w)" temporal operation to all the classes given as parameter*/
		createAllInstancesTemporalOperation(umlWorld, umlPath, classes);

		ArrayList<Classifier> classesList = new ArrayList<Classifier>();
		classesList.add(umlIndividual);
		
		/** Create "existsIn(w)" temporal operation to all the classes given as parameter*/
		createExistsInTemporalOperation(umlWorld, classesList);			
		
		/** Create four Individual temporal operations (oclIsCreated, oclIsDeleted, oclBecomes and oclCeasesToBe) for the classes given as parameter */
		createIndividualsOperations(umlWorld,classesList);
		
		/** Create two operations for (i) navigation at all worlds and (ii) at a particular world e.g. navigation(w) and navigation() */
		createTemporalNavigationsOperations(umlWorld, attributes);
					
		outln("Temporal reification executed succesfully.");
	}
	
	/** Get all top level classes of the model */
	public void getAllTopLevelClasses(org.eclipse.uml2.uml.Package umlRoot, ArrayList<Classifier> result)
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
				getAllTopLevelClasses((org.eclipse.uml2.uml.Package)pe,result);
			}
		}	
	}
	
	/** Create a Class called Individual as the super-type of all top level classes of the model. */
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
	
	/** Get all classes and data-types of the model */
	public void getAllClassesAndDataTypes(org.eclipse.uml2.uml.Package umlRoot, ArrayList<Classifier> result)
	{		
		for(PackageableElement pe: umlRoot.getPackagedElements())
		{
			if (pe instanceof org.eclipse.uml2.uml.Class || pe instanceof org.eclipse.uml2.uml.DataType){
				org.eclipse.uml2.uml.Classifier type = (org.eclipse.uml2.uml.Classifier)pe;
				result.add(type);
			}
			if (pe instanceof org.eclipse.uml2.uml.Package){
				getAllTopLevelClasses((org.eclipse.uml2.uml.Package)pe,result);
			}
		}	
	}
	
	/** Get all classes of the model */
	public void getAllClasses(org.eclipse.uml2.uml.Package umlRoot, ArrayList<Classifier> result)
	{		
		for(PackageableElement pe: umlRoot.getPackagedElements())
		{
			if (pe instanceof org.eclipse.uml2.uml.Class){
				org.eclipse.uml2.uml.Classifier type = (org.eclipse.uml2.uml.Classifier)pe;
				result.add(type);
			}
			if (pe instanceof org.eclipse.uml2.uml.Package){
				getAllTopLevelClasses((org.eclipse.uml2.uml.Package)pe,result);
			}
		}	
	}
	
	/** Get all attributes of the model */
	public void getAllAttributes (org.eclipse.uml2.uml.Package umlRoot, ArrayList<org.eclipse.uml2.uml.Property> properties)
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
				getAllAttributes((org.eclipse.uml2.uml.Package)pe,properties);
			}
		}	
	}
	
	/** Create the world accessibility relationship (next/previous) */
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

	/** Create a Class called "Path" and a relationship between Paths and Worlds (worlds/paths) */
	public org.eclipse.uml2.uml.Class createPathStructure(org.eclipse.uml2.uml.Class umlWorld, org.eclipse.uml2.uml.Package umlRoot)
	{
		org.eclipse.uml2.uml.Class umlPath = umlRoot.createOwnedClass("Path", false);		
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
		org.eclipse.uml2.uml.Association existenceRelation = umlPath.createAssociation(
			end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, (org.eclipse.uml2.uml.Type)umlWorld,
			end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
		);
		umlRoot.getPackagedElements().add(existenceRelation);		
		outln(existenceRelation);		
		return umlPath;
	}
	
	/** Get the Primitive Type "Boolean" */
	private org.eclipse.uml2.uml.PrimitiveType getPrimitiveBooleanType()
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
	
	/** Create all world temporal operations */
	public void createWorldOperations(org.eclipse.uml2.uml.Class umlWorld, org.eclipse.uml2.uml.Class umlPath, org.eclipse.uml2.uml.Class umlIndividual)
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
		typeParamList.add(umlPath);		
		org.eclipse.uml2.uml.Operation untilnextOpPath = umlWorld.createOwnedOperation("allNext", paramList, typeParamList, umlPath);
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
		org.eclipse.uml2.uml.Operation allIndividualsOp = umlWorld.createOwnedOperation("allIndividuals", null, null, umlIndividual);
		allIndividualsOp.setLower(0);
		allIndividualsOp.setUpper(-1);		
		outln(allIndividualsOp);
	}
	
	/** Create all path operations */
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
	
	/** Create "allInstances(w)" Temporal Operation to all the classes given as parameter*/
	public void createAllInstancesTemporalOperation(org.eclipse.uml2.uml.Class umlWorld, org.eclipse.uml2.uml.Class umlPath, ArrayList<Classifier> classes)
	{		
		EList<String> parameters = new BasicEList<String>();
		parameters.add("w");		
		EList<org.eclipse.uml2.uml.Type> typeParameters = new BasicEList<org.eclipse.uml2.uml.Type>();
		typeParameters.add(umlWorld);		
		for(Classifier c: classes)
		{
			if (c instanceof org.eclipse.uml2.uml.Class)
			{
				if(!c.equals(umlWorld)||!c.equals(umlPath)){
					org.eclipse.uml2.uml.Class class_ = ((org.eclipse.uml2.uml.Class)c);				
					org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation("allInstances", parameters, typeParameters, class_);
					op.setLower(0);
					op.setUpper(-1);
					op.setIsStatic(true);
					outln(op);				
				}
			}
		}
	}
	
	/** Create the "existsIn(w)" temporal operation for the classes given as parameter */
	public void createExistsInTemporalOperation(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> classes)
	{
		org.eclipse.uml2.uml.PrimitiveType pt = getPrimitiveBooleanType();		
		EList<String> parameters = new BasicEList<String>();
		parameters.add("w");		
		EList<org.eclipse.uml2.uml.Type> typeParameters = new BasicEList<org.eclipse.uml2.uml.Type>();
		typeParameters.add(umlWorld);		
		for(Classifier c: classes)
		{
			if (c instanceof org.eclipse.uml2.uml.Class)
			{
				org.eclipse.uml2.uml.Class class_ = ((org.eclipse.uml2.uml.Class)c);				
				org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation("existsIn", parameters, typeParameters, pt);
				outln(op);								
			}			
		}
	}
	
	/** Create "oclIsCreated(w)", "oclIsDeleted(w)", "oclBecomes(w)" and "oclCeasesToBe(w)" temporal operations for the classes given as parameter */
	public void createIndividualsOperations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> classes)
	{
		org.eclipse.uml2.uml.PrimitiveType pt = getPrimitiveBooleanType();		
		EList<String> parameters = new BasicEList<String>();
		parameters.add("w");		
		EList<org.eclipse.uml2.uml.Type> typeParameters = new BasicEList<org.eclipse.uml2.uml.Type>();
		typeParameters.add(umlWorld);		
		for(Classifier c: classes)
		{
			if (c instanceof org.eclipse.uml2.uml.Class)
			{
				org.eclipse.uml2.uml.Class class_ = ((org.eclipse.uml2.uml.Class)c);				
				org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation("oclIsCreated", parameters, typeParameters, pt);
				outln(op);												
				org.eclipse.uml2.uml.Operation op2 = class_.createOwnedOperation("oclIsDeleted", parameters, typeParameters, pt);
				outln(op2);				
				org.eclipse.uml2.uml.Operation op3 = class_.createOwnedOperation("oclBecomes", parameters, typeParameters, pt);
				outln(op3);				
				org.eclipse.uml2.uml.Operation op4 = class_.createOwnedOperation("oclCeasesToBe", parameters, typeParameters, pt);
				outln(op4);
			}			
		}
	}	
			
	/** Create two operations for navigation at all worlds and at a particular world e.g. navigation(w) and navigation() */
	public void createTemporalNavigationsOperations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Property> attrList)
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
				if(owner instanceof org.eclipse.uml2.uml.Class)
				{
					/** Temporal Navigation at a particular World */
					org.eclipse.uml2.uml.Class class_ = (org.eclipse.uml2.uml.Class)owner;					
					org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation(attr.getName(), parameters, typeParameters, type);
					op.setUpper(attr.getUpper());
					op.setLower(attr.getLower());
					outln(op);
					
					/** Temporal Navigation at all possible Worlds */
					org.eclipse.uml2.uml.Operation op2 = class_.createOwnedOperation(attr.getName(), null, null, type);
					if(attr.isReadOnly()){ 
						op2.setLower(attr.getLower()); 
						op2.setUpper(attr.getUpper()); 
					}else{						
						op2.setLower(attr.getLower());
						op2.setUpper(-1);
					}					
					outln(op2);
					
					ArrayList<org.eclipse.uml2.uml.Element> list = new ArrayList<org.eclipse.uml2.uml.Element>();
					list.add(op);
					list.add(op2);
					
					tmap.put(getKey(attr), list);
				}
				if(owner instanceof org.eclipse.uml2.uml.DataType)
				{
					/** Temporal Navigation at a particular World */
					org.eclipse.uml2.uml.DataType class_ = (org.eclipse.uml2.uml.DataType)owner;					
					org.eclipse.uml2.uml.Operation op = class_.createOwnedOperation(attr.getName(), parameters, typeParameters, type);
					op.setUpper(attr.getUpper());
					op.setLower(attr.getLower());
					outln(op);

					/** Temporal Navigation at all possible Worlds */
					org.eclipse.uml2.uml.Operation op2 = class_.createOwnedOperation(attr.getName(), null, null, type);
					if(attr.isReadOnly()){ 
						op2.setLower(attr.getLower()); 
						op2.setUpper(attr.getUpper()); 
					}else{						
						op2.setLower(attr.getLower());
						op2.setUpper(-1);
					}
					outln(op2);

					ArrayList<org.eclipse.uml2.uml.Element> list = new ArrayList<org.eclipse.uml2.uml.Element>();
					list.add(op);
					list.add(op2);
					
					tmap.put(getKey(attr), list);
				}
			}
		}
	}

	public void outln(org.eclipse.uml2.uml.Operation op)
	{
		outln("UML:Operation :: "+"name="+op.getName()+", type="+op.getType().getName()+", lower="+op.getLower()+", upper="+op.getUpper()+"");		
	}	
	
	public void outln(org.eclipse.uml2.uml.GeneralizationSet genSet)
	{
        out("UML:GeneralizationSet :: ");		
        for  (org.eclipse.uml2.uml.Generalization g : genSet.getGeneralizations()) 
        {        	 
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
}
