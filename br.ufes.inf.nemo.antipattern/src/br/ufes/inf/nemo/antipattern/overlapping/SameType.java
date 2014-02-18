package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.MixinClass;
import RefOntoUML.Mode;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.SortalClass;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;

//partEnds with the same type
public class SameType extends OverlappingGroup {
	
	//common type of all properties
	Classifier commonType;
	
	public SameType (ArrayList<Property> overlappingProperties) throws Exception{
		super(overlappingProperties);
		
		commonType = null;
		
		for (Property p : overlappingProperties) {
			if(commonType==null){
				commonType=(Classifier) p.getType();
			}
			else{ 
				if(!commonType.equals(p.getType())){
					throw new Exception("OVER_GROUP_1: all part types must be the same!");
				}
			}
		}
		
		super.validGroup = true;
	}
	
	@Override
	public String toString(){
		String result =	"Overllaping Group: Same Types" +
						"\nCommon Type: "+commonType.getName()+
						"\nProperties: ";
		
		for (Property p : overlappingProperties) {
			result+="\n\t("+p.getName()+") "+p.getType().getName();
		}
		
		return result;
	}

	@Override
	public boolean makeEndsDisjoint(AntipatternOccurrence occurrence, ArrayList<Property> partEnds) {
		if(!this.overlappingProperties.containsAll(partEnds))
			return false;
		
		ArrayList<Classifier> subtypes = new ArrayList<Classifier>();
		
		for (Property p : partEnds) {
			
			ClassStereotype stereotype;
			
			if(p.getType() instanceof SortalClass)
				stereotype = ClassStereotype.ROLE;
			else if(p.getType() instanceof MixinClass)
				stereotype = ClassStereotype.ROLEMIXIN;
			else if(p.getType() instanceof Mode)
				stereotype = ClassStereotype.MODE;
			else if(p.getType() instanceof Relator)
				stereotype = ClassStereotype.RELATOR;
			else if(p.getType() instanceof DataType)
				stereotype = ClassStereotype.DATATYPE;
			else
				return false;
				
			occurrence.getFix().addAll(occurrence.getFixer().createSubTypeAsInvolvingLink(p.getType(), stereotype, p.getAssociation()));
			
			subtypes.add((Classifier) p.getType());
		}
		
		occurrence.getFix().addAll(occurrence.getFixer().createGeneralizationSet(commonType, subtypes));
		return true;
		
	}
	
}
