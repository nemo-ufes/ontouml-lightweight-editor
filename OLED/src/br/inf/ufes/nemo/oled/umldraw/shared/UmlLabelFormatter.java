package br.inf.ufes.nemo.oled.umldraw.shared;

import RefOntoUML.NamedElement;
import RefOntoUML.Property;
import RefOntoUML.impl.PropertyImpl;

public class UmlLabelFormatter {

	public static String getLabelTextFor(NamedElement namedElement)
	{
		if(namedElement instanceof PropertyImpl)
		{
			Property property = (Property) namedElement; 
			return property.getName() + " : " + property.getType().getName();
		}
		/*else if(namedElement instanceof GeneralizationSetImpl)
		{
			GeneralizationSet genSet = (GeneralizationSet) namedElement; 
			
			String params = " {";
			if(genSet.isIsDisjoint())
				params += "disjoint";
			
			if(genSet.isIsCovering() && genSet.isIsDisjoint())
				params += ", ";
			
			if(genSet.isIsCovering())
				params += "covering";
			
			params += "}";
			
			return genSet.getName() + (genSet.isIsCovering() || genSet.isIsDisjoint() ? params : "" );
		}*/
		
		return namedElement.getName(); 
	}

}
