package br.ufes.inf.nemo.oled.umldraw.shared;

import RefOntoUML.Element;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.NamedElement;
import RefOntoUML.Property;
import RefOntoUML.impl.PropertyImpl;

public class UmlLabelFormatter {

	public static String getLabelTextFor(Element namedElement)
	{
		if(namedElement instanceof PropertyImpl)
		{
			Property property = (Property) namedElement; 
			return property.getName() + " : " + property.getType().getName();
		}
		if(namedElement instanceof Generalization)
		{
			String name = new String();
			int i=0;
			for(GeneralizationSet genSet: ((Generalization)namedElement).getGeneralizationSet()){
				if(i==((Generalization)namedElement).getGeneralizationSet().size()-1) name += genSet.getName();
				System.out.println(name);
				i++;
			}
			return name;
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
		
		if (((NamedElement)namedElement).getName()==null) return "<null>";
		else return ((NamedElement)namedElement).getName(); 
	}

}
