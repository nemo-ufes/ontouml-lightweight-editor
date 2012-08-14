package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.*;

public class ChckBoxTreeNodeElem {
	
	private Element element;
	
	public ChckBoxTreeNodeElem (Element element) {
		this.element = element;
	}
	
	public String toString() {
		if (element instanceof NamedElement) {
			String nome = ((NamedElement)element).getName();
			if (nome == null || nome.equals("")) {
				return "<" + element.getClass().toString().replace("class " +
						"RefOntoUML.impl.", "").replace("Impl", "") + ">";
			} else
				return nome;
		} else
			return "<Name Unavailable>";
	}
	
	public Element getElement() {
		return element;
	}
	
	public String getInfo() {
		String info = this.toString() + "\n\n";
		info += "Type: ";
		
		info += element.getClass().toString().replace("class " +
				"RefOntoUML.impl.", "").replace("Impl", "") + "\n";
		
		if (element instanceof Association) {
			Association assoc = (Association)element;
			EList<Property> ownedEnds = assoc.getOwnedEnd();
			info +=	"Connections:\n";
			for (Property prop : ownedEnds) {
				info +=	"      " + prop.getType().getName() + "\n";
			}
		}
		
		return info;
	}
}
