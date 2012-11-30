package br.ufes.inf.nemo.common.ontoumlparser;

import org.eclipse.emf.ecore.EObject;

public class ParsingElement 
{
	EObject element;
	Boolean selected;
	String alias;
	
	public ParsingElement (EObject element, Boolean selected, String alias)
	{
		this.element = element;
		this.selected = selected;
		this.alias = alias;
	}

	public EObject getElement() {
		return element;
	}

	public void setElement(EObject element) {
		this.element = element;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
