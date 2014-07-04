package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.partover.PartOverOccurrence;
import br.ufes.inf.nemo.antipattern.relover.RelOverOccurrence;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;

public class DisjointOverlappingGroupPage extends OverlappingWizardPage{
	
	public DisjointOverlappingGroupPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);
	}
		
	@Override
	public void registerActions(){
		//register one action for each valid action in the table
		getOverlappingWizard().removeAllActions(getVariationIndex());
		for (ArrayList<Property> propertyEndList : getBuilder().getSelections()) {
			OverlappingAction action = new OverlappingAction(getOccurrence(),getVariation());
			action.setDisjoint(propertyEndList);
			getOverlappingWizard().addAction(getVariationIndex(), action);
		}
	}
	
	@Override
	public IWizardPage getNextPage(){
		
		boolean showExclusive;
		
		if(noButton.getSelection()){
			getOverlappingWizard().removeAllActions(getVariationIndex());
			showExclusive = true;
		}
		else if(yesButton.getSelection()){
			
			registerActions();
			
			if (canOpenExclusivePage())
				showExclusive = true;
			else
				showExclusive = false;
		}
		else
			return null;
		
		if (showExclusive){
			if(getOverlappingWizard().hasShownExclusiveDefinition){
				return getOverlappingWizard().getExclusivePage(getVariationIndex());
			}else{
				return getOverlappingWizard().getExclusiveDefinitionPage(getVariationIndex());
			}
		}
		else if (getOverlappingWizard().hasNextVariation(getVariationIndex())){
			return  getOverlappingWizard().getDisjointOverlappingPage(getVariationIndex()+1);
		}
		else {
			return getOverlappingWizard().getFinishing();
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

	@Override
	public String getQuestion() {
		String s = "Are there any DISJOINT types?";
		
		if(occurrence instanceof WholeOverOccurrence)
			s = "Are there any DISJOINT parts?";
		if(occurrence instanceof PartOverOccurrence)
			s = "Are there any DISJOINT wholes?";
		if(occurrence instanceof RelOverOccurrence)
			s = "Are there any DISJOINT mediated types?";
		
		return 	s+"\r\n\r\nIf you answer is \"Yes\" use the table below to specify which are DISJOINT. " +
				"If your answer is \"No\", just go directly to the next page.";
	}
	
}
