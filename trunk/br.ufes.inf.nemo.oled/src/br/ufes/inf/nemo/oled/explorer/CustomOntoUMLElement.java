package br.ufes.inf.nemo.oled.explorer;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.NamedElement;

/** Custom OntoUML element used for listing ref-onto elements */
public class CustomOntoUMLElement extends OntoUMLElement {

	public CustomOntoUMLElement(EObject refElement, String uniqueName) { super(refElement, uniqueName); }		
	
	@Override
	public String toString() {
		if (this.element instanceof GeneralizationSet) {
			return type + " "+((NamedElement)element).getName();
		}		
		else if (this.element instanceof Generalization)
		{
			String general = new String();
			String specific = new String();
			if (((Generalization)element).getGeneral()==null) general = "null";
			else general = ((Generalization)element).getGeneral().getName();
			if (((Generalization)element).getSpecific()==null) specific = "null";
			else specific = ((Generalization)element).getSpecific().getName();
			return type + " "+specific+"->"+general;
		}		
		else return super.toString();		
	}
}
