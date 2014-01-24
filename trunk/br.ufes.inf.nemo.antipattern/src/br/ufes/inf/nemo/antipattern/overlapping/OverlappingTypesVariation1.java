package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.MixinClass;
import RefOntoUML.Mode;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.SortalClass;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;

//partEnds with the same type
public class OverlappingTypesVariation1 extends OverlappingTypesVariation {
	
	//common type of all properties
	Classifier commonType;
	
	public OverlappingTypesVariation1 (OverlappingOccurrence occurrence, ArrayList<Property> overlappingProperties) throws Exception{
		super(occurrence,overlappingProperties);
		
		commonType = null;
		
		for (Property p : overlappingProperties) {
			if(commonType==null){
				commonType=(Classifier) p.getType();
			}
			else{ 
				if(!commonType.equals(p.getType())){
					throw new Exception("VAR1: all part types must be the same!");
				}
			}
		}
		
		super.validVariation = true;
	}
	
	@Override
	public String toString(){
		String result =	"VAR1" +
						"\nCommon Type: "+occurrence.getParser().getStringRepresentation(commonType)+
						"\nProperties: ";
		
		for (Property p : overlappingProperties) {
			result+="\n\t"+occurrence.getParser().getStringRepresentation(p);
		}
		
		return result;
	}

	@Override
	public boolean makeEndsDisjoint(ArrayList<Property> partEnds) {
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
				
			this.occurrence.getFix().addAll(occurrence.getFixer().createSubTypeAsInvolvingLink(p.getType(), stereotype, p.getAssociation()));
			
			subtypes.add((Classifier) p.getType());
		}
		
		this.occurrence.getFix().addAll(occurrence.getFixer().createGeneralizationSet(commonType, subtypes));
		
		return true;
		
	}
	
}
