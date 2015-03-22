package br.ufes.inf.nemo.ontouml2uml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
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
		reifyAssociations(associations,false);
		
		/** reify all attributes */
		reifyAttributes(attributes,false);
							
		textualConstraints += generateConstraintsForAssocationsMultiplicity();
		textualConstraints += generateConstraintsForAttributesMultiplicity();
		textualConstraints += generateConstraintsForAssociationsExistenceCycles();
		textualConstraints += generateConstraintsForAttributesExistenceCycles();
		textualConstraints += generateConstraintsForImmutabiliity();
		textualConstraints += generateConstraintsSetCollectionType();
		textualConstraints += generateNavigationsImplForAssociations();
		textualConstraints += generateNavigationsImplForAttributes();
		
		outln("Complete reification executed successfully");
	}
	
	public String generateConstraintsForImmutabiliity()
	{
		String result = new String();
		result += "--====================================="+"\n";
		result += "--Immutability (ReadOnly): Relationships"+"\n";
		result += "--======================================"+"\n\n";		
		for(Association assoc: associations)
		{			
			if((getKey(assoc) instanceof RefOntoUML.Derivation)) continue;
								    
			Property srcEnd = assoc.getMemberEnds().get(0);
			Property tgtEnd = assoc.getMemberEnds().get(1);
			Type src = srcEnd.getType();
			Type tgt = tgtEnd.getType();
			String lowerSrcName = (srcEnd.getName()==null || srcEnd.getName().isEmpty()) ? src.getName().toLowerCase().trim() : srcEnd.getName();
			String lowerTgtName = (tgtEnd.getName()==null || tgtEnd.getName().isEmpty()) ? tgt.getName().toLowerCase().trim() : tgtEnd.getName();
			String lowerAssocName = (assoc.getName() ==null) ? "unnamed" : assoc.getName().toLowerCase().trim().replaceAll(" ", "");
			
			String lowerSrcNameX = deAccent(lowerSrcName.replaceAll(" ", "_"));
			String lowerTgtNameX = deAccent(lowerTgtName.replaceAll(" ", "_"));
			
			if(assoc.getMemberEnds().get(1).isReadOnly()){
				result += "context World"+"\n";
				result += "inv immutable_"+lowerTgtNameX+": "+"\n";
				result += "    self.individual->select(i | i.oclIsKindOf(_'"+src.getName()+"'))->forAll(m |"+"\n"; 
				result += "      self->asSet()->closure(next)->asSet()->forAll(n |"+ "\n"; 
				result += "         m.oclAsType(_'"+src.getName()+"')._'"+lowerAssocName+"'->select(r | r.world = n)._'"+lowerTgtName+"' = "+"\n"; 
				result += "         m.oclAsType(_'"+src.getName()+"')._'"+lowerAssocName+"'->select(r | r.world = self)._'"+lowerTgtName+"'"+"\n";
			    result += "      )\n";
			    result += "    )\n\n";
			}
			
			if(assoc.getMemberEnds().get(0).isReadOnly()){
				result += "context World"+"\n";
				result += "inv immutable_"+lowerSrcNameX+": "+"\n";
				result += "    self.individual->select(i | i.oclIsKindOf(_'"+tgt.getName()+"'))->forAll(m |"+"\n"; 
				result += "      self->asSet()->closure(next)->asSet()->forAll(n |"+ "\n"; 
				result += "         m.oclAsType(_'"+tgt.getName()+"')._'"+lowerAssocName+"'->select(r | r.world = n)._'"+lowerSrcName+"' = "+"\n"; 
				result += "         m.oclAsType(_'"+tgt.getName()+"')._'"+lowerAssocName+"'->select(r | r.world = self)._'"+lowerSrcName+"'"+"\n";
			    result += "      )\n";
			    result += "    )\n\n";
			}
		}
		
		return result;
	}
	    
	public String generateConstraintsSetCollectionType()
	{
		String result = new String();
		result += "--====================================="+"\n";
		result += "--Set Collection Type: Relationships "+"\n";
		result += "--======================================"+"\n\n";		
		for(Association assoc: associations)
		{			
			if((getKey(assoc) instanceof RefOntoUML.MaterialAssociation)) continue;
			if((getKey(assoc) instanceof RefOntoUML.Derivation)) continue;
			
			result += "context World"+"\n";
								    
			Property srcEnd = assoc.getMemberEnds().get(0);
			Property tgtEnd = assoc.getMemberEnds().get(1);
			Type src = srcEnd.getType();
			Type tgt = tgtEnd.getType();
			String lowerSrcName = (srcEnd.getName()==null || srcEnd.getName().isEmpty()) ? src.getName().toLowerCase().trim() : srcEnd.getName();
			String lowerTgtName = (tgtEnd.getName()==null || tgtEnd.getName().isEmpty()) ? tgt.getName().toLowerCase().trim() : tgtEnd.getName();
			String lowerAssocName = (assoc.getName() ==null) ? "unnamed" : assoc.getName().toLowerCase().trim().replaceAll(" ", "");

			String lowerSrcNameX = deAccent(lowerSrcName.replaceAll(" ", "_"));
			String lowerTgtNameX = deAccent(lowerTgtName.replaceAll(" ", "_"));
			String lowerAssocNameX = deAccent(lowerAssocName.replaceAll(" ", "_").replaceAll("/", ""));
			
			result += "inv no_duplicted_"+lowerAssocNameX+"_between_"+lowerSrcNameX+"_and_"+lowerTgtNameX+": "+"\n";
			result += "    not self._'"+lowerAssocName+"'->exists(m1, m2 |"+"\n";     
			result += "    m1 <> m2 and m1._'"+lowerSrcName+"' = m2._'"+lowerSrcName+"' and m1._'"+lowerTgtName+"' = m2._'"+lowerTgtName+"')"+"\n\n";     			
		}
		
		return result;
	}   

	public String generateConstraintsForAssocationsMultiplicity()
	{
		String result = new String();
		result += "--====================================="+"\n";
		result += "--Multiplicity: Relationships"+"\n";
		result += "--======================================"+"\n\n";	
		
		for(Association assoc: associations)
		{			
			if((getKey(assoc) instanceof RefOntoUML.Derivation)) continue;
			
			int lowerSrc = assoc.getMemberEnds().get(0).getLower();
			int upperSrc = assoc.getMemberEnds().get(0).getUpper();
			int lowerTgt = assoc.getMemberEnds().get(1).getLower();
			int upperTgt = assoc.getMemberEnds().get(1).getUpper();			
			Property srcEnd = assoc.getMemberEnds().get(0);
			Property tgtEnd = assoc.getMemberEnds().get(1);
			Type src = srcEnd.getType();
			Type tgt = tgtEnd.getType();
			String lowerSrcName = (srcEnd.getName()==null || srcEnd.getName().isEmpty()) ? src.getName().toLowerCase().trim() : srcEnd.getName();
			String lowerTgtName = (tgtEnd.getName()==null || tgtEnd.getName().isEmpty()) ? tgt.getName().toLowerCase().trim() : tgtEnd.getName();			
			String lowerAssocName = (assoc.getName() ==null) ? "unnamed" : assoc.getName().toLowerCase().trim().replaceAll(" ", "");			
			
			String lowerSrcNameX = deAccent(lowerSrcName.replaceAll(" ", "_"));
			String lowerTgtNameX = deAccent(lowerTgtName.replaceAll(" ", "_"));
			String lowerAssocNameX = deAccent(lowerAssocName.replaceAll(" ", "_").replaceAll("/", ""));
			
			//from src to tgt
			result += "context World"+"\n";
			result += "inv "+lowerAssocNameX+"_from_"+lowerSrcNameX+"_to_"+lowerTgtNameX+": "+"\n";
			result += "    self.individual->select(i | i.oclIsKindOf(_'"+src.getName()+"'))->forAll(m | "+"\n";
			result += "    let list : Set(_'"+tgt.getName()+"') = m._'"+lowerAssocName+"'->select(r | r.world = self)"+"\n";
			result += "    in list->size() >= "+lowerTgt;			
			if(upperTgt >0) result += " and list->size() <= "+upperTgt;
			result += ")\n\n";	
						
			//from tgt to src 
			result += "context World"+"\n";
			result += "inv "+lowerAssocNameX+"_from_"+lowerTgtNameX+"_to_"+lowerSrcNameX+": "+"\n";
			result += "    self.individual->select(i | i.oclIsKindOf(_'"+tgt.getName()+"'))->forAll(m | "+"\n";
			result += "    let list : Set(_'"+src.getName()+"') = m._'"+lowerAssocName+"'->select(r | r.world = self)"+"\n";
			result += "    in list->size() >= "+lowerSrc;			
			if(upperTgt >0) result += " and list->size() <= "+upperSrc;
			result += ")\n\n";	
		}
		
		return result;
	}
	
	public String generateNavigationsImplForAssociations()
	{
		String result = new String();
		result += "--====================================="+"\n";
		result += "--Navigations: Relationships"+"\n";
		result += "--======================================"+"\n\n";	
		
		for(Association assoc: associations)
		{			
			if((getKey(assoc) instanceof RefOntoUML.Derivation)) continue;
			
			Property srcEnd = assoc.getMemberEnds().get(0);
			Property tgtEnd = assoc.getMemberEnds().get(1);
			Type src = srcEnd.getType();
			Type tgt = tgtEnd.getType();
			String lowerSrcName = (srcEnd.getName()==null || srcEnd.getName().isEmpty()) ? src.getName().toLowerCase().trim() : srcEnd.getName();
			String lowerTgtName = (tgtEnd.getName()==null || tgtEnd.getName().isEmpty()) ? tgt.getName().toLowerCase().trim() : tgtEnd.getName();
			String lowerAssocName = (assoc.getName() ==null) ? "unnamed" : assoc.getName().toLowerCase().trim().replaceAll(" ", "");			
			
			//from src to tgt 
			result += "context _'"+src.getName()+"'::_'"+lowerTgtName+"'(w: World) : Set(_'"+tgt.getName()+"')\n";			
			result += "body: self._'"+lowerAssocName+"'->select(m | m.world = w)->collect(_'"+lowerTgtName+"')->asSet()\n\n";		
			
			result += "context _'"+src.getName()+"'::_'"+lowerTgtName+"'() : Set(_'"+tgt.getName()+"') \n";
			result += "body: self._'"+lowerAssocName+"'->collect(_'"+lowerTgtName+"')->asSet()\n\n";
			
			//from tgt to src 
			result += "context _'"+tgt.getName()+"'::_'"+lowerSrcName+"'(w: World) : Set(_'"+src.getName()+"') \n";			
			result += "body: self._'"+lowerAssocName+"'->select(m | m.world = w)->collect(_'"+lowerSrcName+"')->asSet()\n\n";		
			
			result += "context _'"+tgt.getName()+"'::_'"+lowerSrcName+"'() : Set(_'"+src.getName()+"') \n";
			result += "body: self._'"+lowerAssocName+"'->collect(_'"+lowerSrcName+"')->asSet()\n\n";	
		}
		
		return result;
	}

	
	public String generateConstraintsForAssociationsExistenceCycles()
	{
		String result = new String();
		result += "--====================================="+"\n";
		result += "--Existence Cycles: Relationships"+"\n";
		result += "--======================================"+"\n\n";		
		for(Association assoc: associations)
		{			
			if((getKey(assoc) instanceof RefOntoUML.Derivation)) continue;
			
			result += "context _'"+assoc.getName().replaceAll(" ", "")+"'\n";
			
			Property srcEnd = assoc.getMemberEnds().get(0);
			Property tgtEnd = assoc.getMemberEnds().get(1);
			Type src = srcEnd.getType();
			Type tgt = tgtEnd.getType();
			String lowerSrcName = (srcEnd.getName()==null || srcEnd.getName().isEmpty()) ? src.getName().toLowerCase().trim() : srcEnd.getName();
			String lowerTgtName = (tgtEnd.getName()==null || tgtEnd.getName().isEmpty()) ? tgt.getName().toLowerCase().trim() : tgtEnd.getName();
			
			//tgt 
			result += "inv target_cycle: "+"\n";
			result += "	   self.world.individual->select(i | i.oclIsKindOf(_'"+tgt.getName()+"'))->includes(self._'"+lowerTgtName+"')"+"\n";
			
			//src
			result += "inv source_cycle: "+"\n";
			result += "	   self.world.individual->select(i | i.oclIsKindOf(_'"+src.getName()+"'))->includes(self._'"+lowerSrcName+"')"+"\n\n";
		}
		
		return result;
	}
	
	public String generateConstraintsForAttributesMultiplicity()
	{
		String result = new String();
		result += "--====================================="+"\n";
		result += "--Multiplicity: Attributes"+"\n";
		result += "--======================================"+"\n\n";	
		
		int i=0;
		for(Property p: attributes)
		{			
			if(p.getAssociation()!=null) continue;
			
			int lowerTgt = p.getLower();
			int upperTgt = p.getUpper();
			Type src = (Type)p.eContainer();
			Type tgt = p.getType();
			String lowerSrcName = src.getName().toLowerCase().trim();
			String lowerTgtName = tgt.getName().toLowerCase().trim();
			String lowerAttrName = "attribute"+i;
	
			String lowerSrcNameX = deAccent(lowerSrcName.replaceAll(" ", "_"));
			String lowerTgtNameX = deAccent(lowerTgtName.replaceAll(" ", "_"));
						
			//from src to tgt 
			result += "context World"+"\n";
			result += "inv "+lowerAttrName+"_from_"+lowerSrcNameX+"_to_"+lowerTgtNameX+": "+"\n";
			result += "    self.individual->select(i | i.oclIsKindOf(_'"+src.getName()+"'))->forAll(m | "+"\n";
			result += "    let list : Set(_'"+tgt.getName()+"') = m._'"+lowerAttrName+"'->select(r | r.world = self)"+"\n";
			result += "    in list->size() >= "+lowerTgt;			
			if(upperTgt >0) result += " and list->size() <= "+upperTgt;
			result += ")\n\n";
			
			i++;
		}
		
		return result;
	}
	
	public String generateNavigationsImplForAttributes()
	{
		String result = new String();
		result += "--====================================="+"\n";
		result += "--Navigations: Attributes"+"\n";
		result += "--======================================"+"\n\n";	
		
		int i=0;
		for(Property p: attributes)
		{			
			if(p.getAssociation()!=null) continue;
			

			Type src = (Type)p.eContainer();
			Type tgt = p.getType();
			String lowerTgtName = p.getName().toLowerCase().trim();
			String lowerAttrName = "attribute"+i;
			
			//from src to tgt 
			result += "context _'"+src.getName()+"'::_'"+lowerTgtName+"'(w: World) : Set(_'"+tgt.getName()+"') \n";			
			result += "body: self._'"+lowerAttrName+"'->select(m | m.world = w)->collect(_'"+lowerTgtName+"')->asSet()\n\n";		
						
			result += "context _'"+src.getName()+"'::_'"+lowerTgtName+"'() : Set(_'"+tgt.getName()+"') \n";
			result += "body: self._'"+lowerAttrName+"'->collect(_'"+lowerTgtName+"')->asSet()\n\n";
			
			i++;
		}
		
		return result;
	}
	
	public String generateConstraintsForAttributesExistenceCycles()
	{
		String result = new String();
		result += "--====================================="+"\n";
		result += "--Existence Cycles: Attributes"+"\n";
		result += "--======================================"+"\n\n";
		
		int i=0;
		for(Property attr: attributes)
		{			
			if(attr.getAssociation()!=null) continue;
			
			result += "context attribute"+i+"\n";
						
			Type src = (Type)attr.eContainer();			
			String lowerSrcName = src.getName().toLowerCase().trim();
			
			//src
			result += "inv source_cycle: "+"\n";
			result += "	   self.world.individual->select(i | i.oclIsKindOf(_'"+src.getName()+"'))->includes(self._'"+lowerSrcName+"')"+"\n\n";		
			i++;
		}		
		return result;
	}
	
	public void reifyAttributes(List<org.eclipse.uml2.uml.Property> attributes, boolean eraseFormerAttribute)
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
				
				attritbute_counter++;
				
				tmap.put(getKey(attr), createdElems);
				
				if(eraseFormerAttribute){
					if (getKey(attr)!=null) umap.remove(getKey(attr));
					EcoreUtil.delete(attr);
				}
			}
		}
	}
	
	public org.eclipse.uml2.uml.Class reifyAttribute(org.eclipse.uml2.uml.Property attr)
	{
		org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)attr.eContainer().eContainer();
		org.eclipse.uml2.uml.Class reifiedAttr = null;
		if (attr.getName()==null || attr.getName().isEmpty()) { attr.setName("attribute"+attritbute_counter); }
		reifiedAttr = umlRoot.createOwnedClass("attribute"+attritbute_counter, false);		 
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
		if(reifiedAttr.getName()!=null && reifiedAttr.getName().isEmpty()) end1Name = reifiedAttr.getName().toLowerCase().trim().replaceAll(" ", ""); 
		else end1Name = "attribute"+attritbute_counter;		
		org.eclipse.uml2.uml.AggregationKind agg1 = attr.getAggregation();
		int end1Lower = 0;
		int end1Upper = -1;				
		
		boolean end2IsNavigable = true;
		org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		String end2Name = ((org.eclipse.uml2.uml.NamedElement)attr.eContainer()).getName().toLowerCase().trim();
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
	
	public void reifyAssociations(List<Association> associations, boolean eraseFormerAssociation)
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

			if(eraseFormerAssociation){
				if (getKey(assoc)!=null) umap.remove(getKey(assoc));
				EcoreUtil.delete(assoc);
			}
		}		
	}
	
	public org.eclipse.uml2.uml.Class reifyAssociation(Association assoc)
	{
		org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)assoc.eContainer();
		org.eclipse.uml2.uml.Class reifiedAssoc = null;
		if (assoc.getName()==null || assoc.getName().isEmpty()) { assoc.setName("relationship"+relationship_counter); }
		reifiedAssoc = umlRoot.createOwnedClass(assoc.getName().trim().replaceAll(" ", ""), false);
		relationship_counter++;					
		outln("UML:Class :: name="+reifiedAssoc.getName()+", visibility="+reifiedAssoc.getVisibility().getName()+", isAbstract="+reifiedAssoc.isAbstract());
		return reifiedAssoc;
	}
	
	public org.eclipse.uml2.uml.Association reifyAssociationEndPoint(org.eclipse.uml2.uml.Property property, org.eclipse.uml2.uml.Class reifiedAssoc)
	{
		boolean end1IsNavigable = true;
		String end1Name = new String();
		if(reifiedAssoc.getName()!=null && !reifiedAssoc.getName().isEmpty()) end1Name = reifiedAssoc.getName().toLowerCase().trim().replaceAll(" ", ""); 
		else end1Name = "relationship"+relationship_counter;
		org.eclipse.uml2.uml.AggregationKind agg1 = property.getAggregation();
		int end1Lower = 0;
		int end1Upper = -1;
		
		boolean end2IsNavigable = true;
		org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		String end2Name = (property.getName()==null || property.getName().isEmpty()) ? property.getType().getName().toLowerCase().trim() : property.getName();
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
