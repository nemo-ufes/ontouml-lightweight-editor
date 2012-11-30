package br.ufes.inf.nemo.common.ontoumlparser;

import org.eclipse.emf.ecore.EObject;

/**
 * This class represents a Parsing Element. 
 * OntoUMLParser uses this to keep useful information about an OntoUML Element. 
 * 
 * @author John
 *
 */
public class ParsingElement {
	
	/** OntoUML Element. */
	private EObject element;
	
	/** Is Selected (for transformation purposes). */
	private Boolean selected;
	
	/** Alias Name. */
	private String alias;
	
	/**
	 * Constructor.
	 * 
	 * @param element
	 * @param selected
	 * @param alias
	 */
	public ParsingElement (EObject element, Boolean selected, String alias)
	{
		this.element = element;
		this.selected = selected;
		this.alias = alias;
	}

	/** Get OntoUML Element. */
	public EObject getElement() { return element; }

	/** Set OntoUML Element. */
	public void setElement(EObject element) { this.element = element; }

	/** Verifies if the OntoUML Element Is Selected. */
	public Boolean getSelected() { return selected; }

	/** Set if the OntoUML Element is Selected or Not. */
	public void setSelected(Boolean selected) { this.selected = selected; }

	/** Get Alias Name. */
	public String getAlias() { return alias; }

	/** Set Alias Name. */
	public void setAlias(String alias) { this.alias = alias; }
}
