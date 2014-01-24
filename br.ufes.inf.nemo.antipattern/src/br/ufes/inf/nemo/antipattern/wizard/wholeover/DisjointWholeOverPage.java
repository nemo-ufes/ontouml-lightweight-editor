package br.ufes.inf.nemo.antipattern.wizard.wholeover;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.OverlappingAction;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.OverlappingWizardPage;

public class DisjointWholeOverPage extends OverlappingWizardPage{
	
	public DisjointWholeOverPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);
		setTitle(WholeOverAntipattern.getAntipatternInfo().getName());
		setDescription("");
	}

	@Override
	public String getQuestion() {
		String question = 	"The model states that it is possible (but not required) for a single object to simultaneously act as the "+
							getVariation().getOverlappingProperties().size()+" following parts: ";
		
		for (int i = 0; i < getVariation().getOverlappingProperties().size(); i++) {
			Property partEnd = getVariation().getOverlappingProperties().get(i);
			question += "'"+partEnd.getType().getName()+"' ";
			
			if(partEnd.getName()==null || partEnd.getName().trim().isEmpty())
				question += "(unnamed)";
			else
				question += "("+partEnd.getName()+")";
			
			if (i < getVariation().getOverlappingProperties().size()-2)
				question+= ", ";
			else if (i == getVariation().getOverlappingProperties().size()-2)
				question+= " and ";
		}
		
		question += ".\n\nIs there any particular case in which that should not be allowed? If your answer is 'Yes', then use the table below to choose which part "+
					"types should be made disjoint. If your answer is 'No', just press the 'Next' button.";
		
		return question;
	}
	
	public WholeOverWizard getWholeOverWizard(){
		return (WholeOverWizard)getWizard();
	}
	
	public WholeOverOccurrence getWholeOverOccurrence(){
		return (WholeOverOccurrence)getOccurrence();
	} 
	
	public void registerAction(){
		//register one action for each valid action in the table
		getWholeOverWizard().removeAllActions(getVariationIndex());
		for (ArrayList<Property> propertyEndList : getBuilder().getSelections()) {
			OverlappingAction action = new OverlappingAction(getWholeOverOccurrence(),getVariation());
			action.setDisjoint(propertyEndList);
			getWholeOverWizard().addAction(getVariationIndex(), action);
		}
	}
	
	@Override
	public IWizardPage getNextPage(){
		
		registerAction();
		
		if (canOpenExclusivePage()){
			return getWholeOverWizard().getExclusivePage(getVariationIndex());
		}
			
		else if (getWholeOverWizard().hasNextVariation(getVariationIndex())){
			return  getWholeOverWizard().getDisjointPage(getVariationIndex()+1);
		}
		else {
			return getWholeOverWizard().getFinishing();
		}
	}
	
	private boolean canOpenExclusivePage(){
		
		for (Property partEnd : getVariation().getOverlappingProperties()) {
			boolean isPropertyContained = false;
			for (ArrayList<Property> disjointList : getBuilder().getSelections()) {
				if(disjointList.contains(partEnd)){
					isPropertyContained = true;
					break;
				}
			}
			if(!isPropertyContained)
				return true;
		}	
		return false;
	
	}
	
}
