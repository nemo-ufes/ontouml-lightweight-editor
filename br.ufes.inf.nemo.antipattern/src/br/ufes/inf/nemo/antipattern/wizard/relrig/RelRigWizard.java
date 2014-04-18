package br.ufes.inf.nemo.antipattern.wizard.relrig;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelRigWizard extends AntipatternWizard {

	protected ArrayList<RelRigFirstPage> firstpageList = new ArrayList<RelRigFirstPage>();
	protected ArrayList<RelRigSecondPage> secondpageList= new ArrayList<RelRigSecondPage>();
	protected ArrayList<RelRigThirdPage> thirdpageList= new ArrayList<RelRigThirdPage>();
	protected ArrayList<RelRigFourthPage> fourthpageList= new ArrayList<RelRigFourthPage>();
		
	public RelRigWizard(RelRigOccurrence ap) {
		super(ap,RelRigAntipattern.getAntipatternInfo().name);		
	}
	
	@Override
	public void addPages() 
	{		
		for(int i=0;i<getAp().getRigidMediatedProperties().size();i++){
			
			RelRigFirstPage firstpage = new RelRigFirstPage(getAp(),i);
			firstpageList.add(firstpage);
			
			RelRigSecondPage secondpage = new RelRigSecondPage(getAp(),i);
			secondpageList.add(secondpage);
			
			RelRigThirdPage thirdpage = new RelRigThirdPage(getAp(),i);
			thirdpageList.add(thirdpage);
			
			RelRigFourthPage fourthpage = new RelRigFourthPage(getAp(),i);
			fourthpageList.add(fourthpage);
		}		
		
		finishing = new FinishingPage();
		options = new RelRigRefactoringPage(getAp());
		
		presentation = new PresentationPage(
			RelRigAntipattern.getAntipatternInfo().name,
			RelRigAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			RelRigAntipattern.getAntipatternInfo().description,
			firstpageList.get(0),
			options
		);
		
		addPage(presentation);		
		for(int i=0; i<firstpageList.size();i++) { 
			addPage(firstpageList.get(i));
			addPage(secondpageList.get(i));
			addPage(thirdpageList.get(i));
			addPage(fourthpageList.get(i));
		}		
		addPage(options);
		addPage(finishing);
	}

	public WizardPage getSecondPage(int rigid){
		for(RelRigSecondPage page: secondpageList){
			if (page.rigid == rigid) return page;
		}
		return null;
	}
	
	public RelRigOccurrence getAp() {
		return (RelRigOccurrence)ap;
	}

	public WizardPage getFirstPage(int rigid){
		for(RelRigFirstPage page: firstpageList){
			if (page.rigid == rigid) return page;
		}
		return null;
	}
	
	public WizardPage getThirdPage(int rigid){
		for(RelRigThirdPage page: thirdpageList){
			if (page.rigid == rigid) return page;
		}
		return null;
	}
	
	public WizardPage getFourthPage(int rigid){
		for(RelRigFourthPage page: fourthpageList){
			if (page.rigid == rigid) return page;
		}
		return null;
	}
	
}
