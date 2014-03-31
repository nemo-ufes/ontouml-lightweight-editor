package br.ufes.inf.nemo.antipattern.decint;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;

public class GeneralizationSetReplica{
	private String name;
	private boolean isCovering, isDisjoint, isDeleted;
	ArrayList<Generalization> selectedGeneralizations;
	ArrayList<Generalization> allowedGeneralizations;
	private GeneralizationSet original;
	private Classifier supertype;
	
	public GeneralizationSetReplica(GeneralizationSet original){
		this.original = original;
		setOriginalValues();
		setAllowedGeneralizations();
	}

	private void setAllowedGeneralizations(){
		supertype = original.getGeneralization().get(0).getGeneral();
		allowedGeneralizations = new ArrayList<Generalization>();
		for (Classifier child : supertype.children()) {
			for (Generalization g : child.getGeneralization()) {
				if(g.getGeneral().equals(supertype))
					allowedGeneralizations.add(g);
			}
		}
	}
	
	public void setOriginalValues() {
		isCovering = original.isIsCovering();
		isDisjoint = original.isIsDisjoint();
		isDeleted = false;
		name = original.getName();
		selectedGeneralizations = new ArrayList<Generalization>(original.getGeneralization());
	}

	public boolean isCovering() {
		return isCovering;
	}

	public void setCovering(boolean isCovering) {
		this.isCovering = isCovering;
	}

	public boolean isDisjoint() {
		return isDisjoint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisjoint(boolean isDisjoint) {
		this.isDisjoint = isDisjoint;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ArrayList<Generalization> getSelectedGeneralizations() {
		return selectedGeneralizations;
	}

	public void setSelectedGeneralizations(ArrayList<Generalization> generalizations) {
		this.selectedGeneralizations = generalizations;
	}

	public GeneralizationSet getOriginal() {
		return original;
	}

	public void setOriginal(GeneralizationSet original) {
		this.original = original;
	}

	public ArrayList<Generalization> getAllowedGeneralizations() {
		return allowedGeneralizations;
	}

	public Classifier getSupertype() {
		return supertype;
	}
	
	public void persistChanges(){
		original.setIsCovering(isCovering);
		original.setIsDisjoint(isDisjoint);
		original.setName(name);
		original.getGeneralization().clear();
		
		for (Generalization selectedGen : getSelectedGeneralizations())
			original.getGeneralization().add(selectedGen);
	}
	
	@Override
	public String toString() {
		String result = new String();
		result += this.getName() + ": "+this.getSupertype().getName()+" { ";
		
		ArrayList<Generalization> genlist = this.getSelectedGeneralizations();		    
		int i=1;
		for(Generalization gen: genlist)
		{
			if(i < genlist.size()) result += gen.getSpecific().getName()+", ";
			else result += gen.getSpecific().getName(); 
			i++;
		}
		result+=" } ";
		return result;
	}
	
	
}