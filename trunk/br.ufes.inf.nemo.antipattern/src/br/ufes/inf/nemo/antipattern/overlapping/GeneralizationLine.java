package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;

import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;


public class GeneralizationLine extends OverlappingGroup {
	
	ArrayList<Property> orderedProperties;
	Property parent, child;
	
	public GeneralizationLine (ArrayList<Property> properties)throws Exception {
		super(properties);

		if(properties.size()!=2)
				throw new Exception("GeneralizationLine: Number of properties must be equal to 2.");
		
		Classifier source = (Classifier) properties.get(0).getType();
		Classifier target = (Classifier) properties.get(1).getType();
		
		if(source.allParents().contains(target)){
			child = properties.get(0);
			parent = properties.get(1);
		}
		else if (target.allParents().contains(source)){
			child = properties.get(1);
			parent = properties.get(0);
		}
		else
			throw new Exception("GeneralizationLine: One property type must specialize the other.");

	}
	
	@Override
	public String toString(){
		return	"Overllaping Group: Generalization Line" +
				"\nParent Property: "+"\n\t("+parent.getName()+") "+parent.getType().getName()+
				"\nChild Property: "+"\n\t("+child.getName()+") "+child.getType().getName();
	}

	@Override
	public boolean makeEndsDisjoint(AntipatternOccurrence occurrence, ArrayList<Property> disjointProperties) {
		
		if(!disjointProperties.contains(child) || !disjointProperties.contains(parent))
			return false;
		
		Classifier parentType = (Classifier) parent.getType();
		Classifier childType = (Classifier) child.getType();
		
		if(parent.getAssociation()!=null){
			Fix aux = occurrence.getFixer().createSubTypeAsInvolvingLink(parent.getType(), OutcomeFixer.getDefaultSubtypeStereotype((Class) parent.getType()), parent.getAssociation());
		
			Classifier created = aux.getAddedByType(Class.class).get(0);
			ArrayList<Classifier> subtypes = new ArrayList<Classifier>();
			subtypes.add(created);
		
			if(childType.parents().contains(parentType))
				subtypes.add(childType);
			else{
				for (Classifier directSubtype : childType.allParents()) {
					if(directSubtype.parents().contains(parentType)){
						subtypes.add(directSubtype);
						break;
					}
				}
			}
			
			if(subtypes.size()!=2)
				return false;
			
			occurrence.getFix().addAll(occurrence.getFixer().createGeneralizationSet(parentType, subtypes));
			occurrence.getFix().addAll(aux);
			
			return true;
		}
		
		return true;
	}

	public ArrayList<Property> getOrderedProperties() {
		return orderedProperties;
	}

	public Property getParent() {
		return parent;
	}

	public Property getChild() {
		return child;
	}
	
}
