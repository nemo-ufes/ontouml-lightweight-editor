package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;

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
		
		if(btnYes.getSelection()){
			getOverlappingWizard().removeAllActions(getVariationIndex());
			showExclusive = true;
		}
		else if(btnNo.getSelection()){
			
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
		return 	"Currently the model states that the types: "+variation.getOverlappingTypesString()+" are overlapping, i.e., " +
				"it is possible for the same individual to instantiate all of them simultaneously." +
				"\n\nIs that true? If not, use the table below to specify sub-groups of disjoint types. " +
				"If more than one restriction is required, add extra lines in the table.";
	}
	
}
