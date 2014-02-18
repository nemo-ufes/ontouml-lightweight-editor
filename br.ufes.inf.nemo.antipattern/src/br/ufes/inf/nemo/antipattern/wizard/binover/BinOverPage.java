package br.ufes.inf.nemo.antipattern.wizard.binover;

import org.eclipse.jface.wizard.WizardPage;

import RefOntoUML.Association;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public abstract class BinOverPage extends WizardPage {

	protected BinOverOccurrence binOver;
	/**
	 * Create the wizard.
	 */
	public BinOverPage(String pageName, BinOverOccurrence binOver) 
	{
		super(pageName);				
		this.binOver = binOver;
		
	}
	
	public BinOverWizard getBinOverWizard(){
		return (BinOverWizard)getWizard();
	}
	
	public void updateDescription(){
		setDescription("Binary Relation: "+getRelationName()+
					"\nCurrent Stereotype: "+getBinOverWizard().getCurrentStereotypeName(this));	
	}
	
	public static String getPropertyName(Property p){
		if(p==null)
			return "Null Property";
		
		String pName = p.getName();

		if(pName==null)
			pName="unnamed";
		
		pName.replaceAll("\\s+","");
		
		if(pName.isEmpty())
			pName="unnamed";
		
		if(p.getAssociation()!=null && pName.compareTo("unnamed")==0){
			if(p.getAssociation().getMemberEnd().get(0).equals(p))
				pName = "source";
			else
				pName = "target";
		}
		
		pName = "("+pName+")";
		
		if (p.getType()==null || p.getType().getName()==null)
			return pName + " Null Type";
		
		String tName = p.getType().getName();
		
		tName.replaceAll("\\s+","");
		if(tName.isEmpty())
			tName="Unnamed";
		
		return pName+" "+tName;
	}
	
	public String getSourceEndName(){
		return getPropertyName(binOver.getAssociation().getMemberEnd().get(0));
	}
	
	public String getTargetEndName(){
		return getPropertyName(binOver.getAssociation().getMemberEnd().get(1));
	}
	
	public String getRelationName() {
		Association a = binOver.getAssociation();
		
		if (a==null)
			return "Null Association";
		
		if(a.getName()==null)
			return "Unnamed";
		
		String aName = a.getName();
		aName.replaceAll("\\s+","");
		
		if(aName.isEmpty())
			return "Unnamed";
		else
			return aName;	
	}

}
