package br.ufes.inf.nemo.ontouml2uml;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.PackageableElement;

public class CompleteReificator extends SimplifiedReificator {
		
	public int assoc_counter=0;
	public int attr_counter=0;
	
	public CompleteReificator(org.eclipse.uml2.uml.Package umlRoot, org.eclipse.uml2.uml.UMLFactory ufactory, HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap)
	{			
		super(umlRoot,ufactory,umap);		
	}
		
	@Override
	public void run()
	{
		super.run();
		
		outln("Executing complete reification...");
		
		// Unnecessary since end-points in UML are owned attributes
		// ArrayList<Association> assocList = new ArrayList<Association>();
		// getAssociations(umlRoot,assocList);
		// createTemporalNavigationOperations(umlWorld, assocList);
		
		// Unnecessary to create the existence association between World and the types (classes,associations and attributes) existent in that World
		// We found a workaround for this i.e. we used UML/OCL pre-defined operations
		// reifyAssociations(umlWorld, topLevels);
		// reifyAttributes(umlWorld,attributes);
					
		outln("Complete reification executed successfully");
	}
	
//	multiplicities semantics
//	context World 
//	inv marriage_mediates_one_wife_at_a_time: 
//	    self.individual->select(i | i.oclIsKindOf(Marriage))->forAll(m |
//	    m.mediates_marriage_wife->select(r | r.world = self)->size() = 1)
//	inv wife_is_mediated_by_one_marriage_at_a_time: 
//	    self.individual->select(i| i.oclIsKindOf(Wife))->forAll(h | 
//	    h.mediates_marriage_wife->select(r | r.world = self)->size() = 1)

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
				createExistsRelationship(umlWorld,reifiedAttr);
				
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
				createExistsRelationship(umlWorld, reifiedAssoc);
				
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
}
