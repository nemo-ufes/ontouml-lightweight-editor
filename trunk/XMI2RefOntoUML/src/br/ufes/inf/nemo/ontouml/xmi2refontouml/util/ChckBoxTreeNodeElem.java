package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.*;

public class ChckBoxTreeNodeElem {
	
	private Element element;
	private String name;
	
	public ChckBoxTreeNodeElem (Element element) {
		this.element = element;
		try {
			this.name = ((NamedElement)element).getName();
		} catch (ClassCastException ccex) {
			System.out.println(element.toString());
			System.out.println("Something wrong is not right.");
		}
	}
	
	public ChckBoxTreeNodeElem (String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		if (name == null || name.equals("")) {
			if (element != null) {
				return "<" + element.getClass().toString().replace("class " +
						"RefOntoUML.impl.", "").replace("Impl", "") + ">";
			} else
				return "<Name Unavailable>";
		} else
			return name;
	}
	
	public Element getElement() {
		return element;
	}
	
	public String getInfo() {
		String info = this.toString() + "\n\n";
		info += "Type: ";
		
		if (element != null) {
		
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
			
		} else {
			info += "Diagram";
		}
		
		return info;
	}
}
