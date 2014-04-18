package br.ufes.inf.nemo.antipattern.wizard.depphase;

import java.util.ArrayList;

import org.eclipse.swt.widgets.List;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class DepPhaseWizard extends AntipatternWizard {

	public DepPhaseFirstPage firstPage;
	public DepPhaseSecondPage secondPage;
	
	public DepPhaseWizard(DepPhaseOccurrence ap) {
		super(ap,DepPhaseAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		firstPage = new DepPhaseFirstPage(getAp());
		secondPage = new DepPhaseSecondPage(getAp());
		
		finishing = new FinishingPage();
		options = new DepPhaseRefactoringPage(getAp());
		
		presentation = new PresentationPage(
			DepPhaseAntipattern.getAntipatternInfo().name,
			DepPhaseAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			DepPhaseAntipattern.getAntipatternInfo().description,
			firstPage,
			options
		);
			
		addPage(presentation);	
		addPage(firstPage);
		addPage(secondPage);
		addPage(options);
		addPage(finishing);
	}
	
	public DepPhaseOccurrence getAp() {
		return (DepPhaseOccurrence)ap;
	}
	
	public DepPhaseFirstPage getFirstPage()
	{
		return firstPage;
	}
	
	public DepPhaseSecondPage getSecondPage()
	{
		return secondPage;
	}
	
	protected ArrayList<Property> getPropertyFromList(List list){
		
		ArrayList<Property> result = new ArrayList<Property>();
		for (String item : list.getItems()) {
			Property p = getProperty(item);
			if(p!=null)
				result.add(p);
		}
		
		return result;	
	}
	
	protected Property getProperty(String propertyName){
		for(Property p: getAp().getRelatorEnds()){
			if(getAp().getParser().getStringRepresentation(p).compareToIgnoreCase(propertyName)==0) 
				return p;			
		}
		return null;
	}
	
	protected void addAllDependencies(List listToAdd, List listToRemove){
		if(listToAdd==null || listToRemove==null)
			return;
		
		listToAdd.removeAll();
		listToRemove.removeAll();
		
		for (Property p : getAp().getRelatorEnds())
			listToAdd.add(getAp().getParser().getStringRepresentation(p));
	}
	
	protected boolean contains(List list, String elem){
		for(String str: list.getItems()){
			if (str.equals(elem)) return true;
		}
		return false;
	}

}
