package br.ufes.inf.nemo.ontouml2tuml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.PackageableElement;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UMLOption;

public class OntoUML2TUML {

	// from pure conversion to UML
	public static OntoUMLParser refparser;
	public static org.eclipse.uml2.uml.Package umlRoot;
	public static HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap;
	public static String ulog;	
	public static org.eclipse.uml2.uml.UMLFactory ufactory;
	
	// including temporal reification
	public static String tlog = new String();
	public static HashMap<RefOntoUML.Association, ArrayList<org.eclipse.uml2.uml.Classifier>> assocMap = new HashMap<RefOntoUML.Association, ArrayList<org.eclipse.uml2.uml.Classifier> >();
	public static HashMap<RefOntoUML.Property, ArrayList<org.eclipse.uml2.uml.Element>> attrMap = new HashMap<RefOntoUML.Property, ArrayList<org.eclipse.uml2.uml.Element> >();
	public static int assoc_counter=0;
	public static int attr_counter=0;

	public static void main(String args[])
	{
		String refpath = "src/br/ufes/inf/nemo/ontouml2tuml/tests/models/Project.refontouml";
		
		try {
			
			initialize(refpath);
			
			ArrayList<Classifier> topLevels = new ArrayList<Classifier>();
			getTopLevelTypes(umlRoot,topLevels);
			
			org.eclipse.uml2.uml.Class umlWorld = createWorldHierarchy(umlRoot);
			
			createWorldAccessibilityRelation(umlWorld);
			
			createExistenceRelations(umlWorld,topLevels);
			
			reifyAssociations(umlWorld, topLevels);
			
			ArrayList<org.eclipse.uml2.uml.Property> attributes = new ArrayList<org.eclipse.uml2.uml.Property>();
			getAttributes(umlRoot,attributes);
			reifyAttributes(umlWorld,attributes);
			
			System.out.println(tlog);
			
			OntoUML2TUMLUtil.saveUML(refpath.replace(".refontouml", ".uml" ), umlRoot);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void outln(String text)
	{
		tlog += text+"\n";
	}
	
	public static void out(String text)
	{
		tlog += text;
	}
	
	public static void initialize(String refontoumlPath) throws IOException
	{
		refparser = new OntoUMLParser(refontoumlPath);
		Resource umlResource = OntoUML2UML.convertToUML(refparser, refontoumlPath.replace(".refontouml", ".uml" ), new OntoUML2UMLOption(true,true));
		umap = OntoUML2UML.getMap();
		ulog = OntoUML2UML.getLog();
		umlRoot = (org.eclipse.uml2.uml.Package)umlResource.getContents().get(0);		
		ufactory = org.eclipse.uml2.uml.UMLFactory.eINSTANCE;
	}
	
	public static void getAttributes (org.eclipse.uml2.uml.Package umlRoot, ArrayList<org.eclipse.uml2.uml.Property> properties)
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
	
	public static void reifyAttributes(org.eclipse.uml2.uml.Class umlWorld, ArrayList<org.eclipse.uml2.uml.Property> properties)
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
				
				RefOntoUML.Element key = getKey(top);
				attrMap.put((RefOntoUML.Property)key, tempList);
			}
		}
	}
	
	public static void createExistenceRelation(org.eclipse.uml2.uml.Class umlWorld, org.eclipse.uml2.uml.Type class_)
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
	
	public static void reifyAssociations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> topLevels)
	{
		for(Classifier top: topLevels)
		{
			if(top instanceof org.eclipse.uml2.uml.Association)
			{
				ArrayList<Classifier> tempList =  new ArrayList<Classifier>();
				
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
								
				RefOntoUML.Element key = getKey(top);
				assocMap.put((RefOntoUML.Association)key, tempList);

				//if (key!=null) umap.remove(key);
				//EcoreUtil.delete(top);
			}
		}		
	}
	
	public static org.eclipse.uml2.uml.Association createSideRelation(org.eclipse.uml2.uml.Property property, org.eclipse.uml2.uml.Class reifiedAssoc)
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
	
    public static RefOntoUML.Element getKey(org.eclipse.uml2.uml.Element value) 
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
    
	public static void createExistenceRelations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> topLevels)
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

	public static void outln(org.eclipse.uml2.uml.GeneralizationSet genSet)
	{
        out("UML:GeneralizationSet :: ");		
        for  (org.eclipse.uml2.uml.Generalization g : genSet.getGeneralizations()) {        	 
        	out(g.getSpecific().getName()+"->"+g.getGeneral().getName()+"  "); 
        }	
        out("isCovering="+genSet.isCovering()+", isDisjoint="+genSet.isDisjoint()+"\n");
	}
	
	public static void outln(org.eclipse.uml2.uml.Association association)
	{		
		org.eclipse.uml2.uml.Property p1 = association.getMemberEnds().get(0);
		org.eclipse.uml2.uml.Property p2 = association.getMemberEnds().get(1);
		
		outln("UML:Association :: name="+association.getName()+", visibility="+association.getVisibility().getName()+", isAbstract="+association.isAbstract()+", isDerived="+association.isDerived());
        outln(p1);
        outln(p2);
	}

	public static void outln(org.eclipse.uml2.uml.Property property)
	{
		outln("UML:Property :: "+"name="+property.getName()+", isDerived="+property.isDerived()+", lower="+property.getLower()+", upper="+property.getUpper()+
	    ", type="+property.getType().getName()+", aggregationkind="+property.getAggregation().getName()+", visibility="+property.getVisibility().getName()+            
	    ", isLeaf="+property.isLeaf()+", isStatic="+property.isStatic()+", isReadOnly="+property.isReadOnly());
	}
	
	public static void getTopLevelTypes(org.eclipse.uml2.uml.Package umlRoot, ArrayList<Classifier> result)
	{		
		for(PackageableElement pe: umlRoot.getPackagedElements())
		{
			if (pe instanceof org.eclipse.uml2.uml.Type){
				org.eclipse.uml2.uml.Classifier type = (org.eclipse.uml2.uml.Classifier)pe;
				if (type.getGeneralizations().size()==0) result.add(type);
			}
			if (pe instanceof org.eclipse.uml2.uml.Package){
				getTopLevelTypes((org.eclipse.uml2.uml.Package)pe,result);
			}
		}	
	}
			
	public static org.eclipse.uml2.uml.Class createWorldHierarchy(org.eclipse.uml2.uml.Package umlRoot)
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
	
	public static void createWorldAccessibilityRelation (org.eclipse.uml2.uml.Class umlWorld)
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
}
