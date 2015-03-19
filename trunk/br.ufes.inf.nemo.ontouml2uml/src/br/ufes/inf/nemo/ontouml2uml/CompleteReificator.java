package br.ufes.inf.nemo.ontouml2uml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Type;

public class CompleteReificator extends SimplifiedReificator {
		
	public int relationship_counter=0;
	public int attritbute_counter=0;
	
	public CompleteReificator(org.eclipse.uml2.uml.Package umlRoot, org.eclipse.uml2.uml.UMLFactory ufactory, HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap)
	{			
		super(umlRoot,ufactory,umap);		
	}
		
	@Override
	public void run()
	{
		super.run();
		
		outln("Executing complete reification...");
		
		/** reify all relationships */
		reifyAssociations(associations);
		
		/** reify all attributes */
		reifyAttributes(attributes);
							
		textualConstraints += generateMultiplicityOCLConstraints(associations);
		
		outln("Complete reification executed successfully");
	}
	
	public String generateMultiplicityOCLConstraints(List<Association> associations)
	{
		String result = new String();
		result += "--====================================="+"\n";
		result += "--Multiplicity"+"\n";
		result += "--======================================"+"\n\n";	
		result += "context World"+"\n\n";
		for(Association assoc: associations)
		{			
			if((getKey(assoc) instanceof RefOntoUML.Derivation)) continue;
			
			int lowerSrc = assoc.getMemberEnds().get(0).getLower();
			int upperSrc = assoc.getMemberEnds().get(0).getUpper();
			int lowerTgt = assoc.getMemberEnds().get(1).getLower();
			int upperTgt = assoc.getMemberEnds().get(1).getUpper();
			Type src = assoc.getMemberEnds().get(0).getType();
			Type tgt = assoc.getMemberEnds().get(1).getType();
			String lowerSrcName = src.getName().toLowerCase().trim();
			String lowerTgtName = tgt.getName().toLowerCase().trim();
			String lowerAssocName = (assoc.getName() ==null) ? "unnamed" : assoc.getName().toLowerCase().trim();			
			
			//from src to tgt 
			result += "inv "+lowerAssocName+"_from_"+lowerSrcName+"_to_"+lowerTgtName+": "+"\n";
			result += "	   self.individual->select(i | i.oclIsKindOf(_'"+src.getName()+"'))->forAll(m | "+"\n";
			result += "    let targets : Set(_'"+tgt.getName()+"') = m._'"+lowerAssocName+"'->select(r | r.world = self)"+"\n";
			result += "    in targets->size() >= "+lowerTgt;			
			if(upperTgt >0) result += " and targets->size() <= "+upperTgt;
			result += ")\n\n";	
						
			//from tgt to src 
			result += "inv "+lowerAssocName+"_from_"+lowerTgtName+"_to_"+lowerSrcName+": "+"\n";
			result += "	   self.individual->select(i | i.oclIsKindOf(_'"+tgt.getName()+"'))->forAll(m | "+"\n";
			result += "    let sources : Set(_'"+src.getName()+"') = m._'"+lowerAssocName+"'->select(r | r.world = self)"+"\n";
			result += "    in sources->size() >= "+lowerSrc;			
			if(upperTgt >0) result += " and sources->size() <= "+upperSrc;
			result += ")\n\n";	
		}
		
		return result;
	}
	
	public void reifyAttributes(List<org.eclipse.uml2.uml.Property> attributes)
	{
		for(org.eclipse.uml2.uml.Property attr: attributes)
		{
			if(attr.getAssociation()==null)
			{
				ArrayList<org.eclipse.uml2.uml.Element> createdElems =  new ArrayList<org.eclipse.uml2.uml.Element>();
								
				//Reify it into a class				
				org.eclipse.uml2.uml.Class reifiedAttr = reifyAttribute(attr);				
				createdElems.add(reifiedAttr);
				
				//Create "exists" relationship between the reified Attribute and World
				createExistsRelationship(umlWorld, reifiedAttr);
				
				//Create binary relationship between the Owner of the attribute and the Reified Attribute (i.e. a class)
				org.eclipse.uml2.uml.Association binaryRelationship = reifyOwnerAttributeEndPoint(attr, reifiedAttr);			
				createdElems.add(binaryRelationship);				
					
				//Create an attribute in the class which is the reified attribute
				org.eclipse.uml2.uml.Property attribute = cloneAttributeAtTheReifiedAttribute(attr, reifiedAttr);				
				createdElems.add(attribute);
				
				tmap.put(getKey(attr), createdElems);
				
				//if (key!=null) umap.remove(key);
				//EcoreUtil.delete(attr);
			}
		}
	}
	
	public org.eclipse.uml2.uml.Class reifyAttribute(org.eclipse.uml2.uml.Property attr)
	{
		org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)attr.eContainer().eContainer();
		org.eclipse.uml2.uml.Class reifiedAttr = null;
		if (attr.getName()==null || attr.getName().isEmpty()) { attr.setName("attribute"+attritbute_counter); }
		reifiedAttr = umlRoot.createOwnedClass(attr.getName(), false);				
		attritbute_counter++; 
		return reifiedAttr;
	}
	
	public org.eclipse.uml2.uml.Property cloneAttributeAtTheReifiedAttribute(org.eclipse.uml2.uml.Property attr, org.eclipse.uml2.uml.Class reifiedAttr)
	{
		org.eclipse.uml2.uml.Property attribute = ufactory.createProperty();
		attribute.setName(attr.getName());
		attribute.setType(attr.getType());
		org.eclipse.uml2.uml.LiteralInteger lowerValue = ufactory.createLiteralInteger();
		org.eclipse.uml2.uml.LiteralUnlimitedNatural upperValue = ufactory.createLiteralUnlimitedNatural();
		lowerValue.setValue(attr.getLower());
		upperValue.setValue(attr.getUpper());         
		attribute.setUpperValue(upperValue);
		attribute.setLowerValue(lowerValue);
		attribute.setAggregation(attr.getAggregation());
		reifiedAttr.getOwnedAttributes().add(attribute);				
		outln(attribute);
		return attribute;
	}
	
	public org.eclipse.uml2.uml.Association reifyOwnerAttributeEndPoint(org.eclipse.uml2.uml.Property attr, org.eclipse.uml2.uml.Class reifiedAttr)
	{
		boolean end1IsNavigable = true;
		String end1Name = new String();
		if(reifiedAttr.getName()!=null && reifiedAttr.getName().isEmpty()) end1Name = reifiedAttr.getName().toLowerCase().trim(); 
		else end1Name = "attribute"+attritbute_counter;		
		org.eclipse.uml2.uml.AggregationKind agg1 = attr.getAggregation();
		int end1Lower = 0;
		int end1Upper = -1;				
		
		boolean end2IsNavigable = true;
		org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		String end2Name = ((org.eclipse.uml2.uml.NamedElement)attr.eContainer()).getName();
		if (end2Name==null) end2Name = "";
		int end2Lower = 1;
		int end2Upper = 1;
		
		org.eclipse.uml2.uml.Association reifiedEndPoint = ((org.eclipse.uml2.uml.Type)attr.eContainer()).createAssociation(
			end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, reifiedAttr,
			end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
		);
		
		umlRoot.getPackagedElements().add(reifiedEndPoint);		
		outln(reifiedEndPoint);		
		
		return reifiedEndPoint;
	}
	
	public void reifyAssociations(List<Association> associations)
	{
		for(Association assoc: associations)
		{
			List<org.eclipse.uml2.uml.Element> createdElems =  new ArrayList<org.eclipse.uml2.uml.Element>();
						
			//Reify Association
			org.eclipse.uml2.uml.Class reifiedAssoc = reifyAssociation(assoc);						
			createdElems.add(reifiedAssoc);
			
			//Create "exists" relationship between the reified Association and World
			createExistsRelationship(umlWorld, reifiedAssoc);
			
			//Create binary relationship between the Reified Association (i.e. a class) and its source type 
			org.eclipse.uml2.uml.Association sourceBinaryRelation = reifyAssociationEndPoint(assoc.getMemberEnds().get(0), reifiedAssoc);			
			createdElems.add(sourceBinaryRelation);				
							
			//Create binary relationship between the Reified Association (i.e. a class) and its target type 
			org.eclipse.uml2.uml.Association targetBinaryRelation = reifyAssociationEndPoint(assoc.getMemberEnds().get(1), reifiedAssoc);			
			createdElems.add(targetBinaryRelation);
							
			tmap.put(getKey(assoc), createdElems);

			//if (key!=null) umap.remove(key);
			//EcoreUtil.delete(assoc);			
		}		
	}
	
	public org.eclipse.uml2.uml.Class reifyAssociation(Association assoc)
	{
		org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)assoc.eContainer();
		org.eclipse.uml2.uml.Class reifiedAssoc = null;
		if (assoc.getName()==null || assoc.getName().isEmpty()) { assoc.setName("relationship"+relationship_counter); }
		reifiedAssoc = umlRoot.createOwnedClass(assoc.getName(), false);
		relationship_counter++;					
		outln("UML:Class :: name="+reifiedAssoc.getName()+", visibility="+reifiedAssoc.getVisibility().getName()+", isAbstract="+reifiedAssoc.isAbstract());
		return reifiedAssoc;
	}
	
	public org.eclipse.uml2.uml.Association reifyAssociationEndPoint(org.eclipse.uml2.uml.Property property, org.eclipse.uml2.uml.Class reifiedAssoc)
	{
		boolean end1IsNavigable = true;
		String end1Name = new String();
		if(reifiedAssoc.getName()!=null && !reifiedAssoc.getName().isEmpty()) end1Name = reifiedAssoc.getName().toLowerCase().trim(); 
		else end1Name = "relationship"+relationship_counter;
		org.eclipse.uml2.uml.AggregationKind agg1 = property.getAggregation();
		int end1Lower = 0;
		int end1Upper = -1;
		
		boolean end2IsNavigable = true;
		org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		String end2Name = property.getType().getName().toLowerCase().trim();
		if (end2Name==null) end2Name = "";
		int end2Lower = 1;
		int end2Upper = 1;

		org.eclipse.uml2.uml.Association reifiedEndPoint = property.getType().createAssociation(
			end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, reifiedAssoc,
			end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
		);
		
		umlRoot.getPackagedElements().add(reifiedEndPoint);		
		outln(reifiedEndPoint);
		
		return reifiedEndPoint;
	}
}
