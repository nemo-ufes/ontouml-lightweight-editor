package br.ufes.inf.nemo.oled.antipattern.wizard.relrig;

import java.util.ArrayList;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;
import br.ufes.inf.nemo.oled.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.oled.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelRigWizard extends Wizard {

	protected RelRigOccurrence ap;
	
	protected PresentationPage presentation;
	protected FinishingPage finishing;
	
	protected RelRigRefactoringPage options;
	
	protected ArrayList<RelRigFirstPage> firstpageList = new ArrayList<RelRigFirstPage>();
	protected ArrayList<RelRigSecondPage> secondpageList= new ArrayList<RelRigSecondPage>();
	protected ArrayList<RelRigThirdPage> thirdpageList= new ArrayList<RelRigThirdPage>();
	protected ArrayList<RelRigFourthPage> fourthpageList= new ArrayList<RelRigFourthPage>();;
		
	public RelRigWizard(RelRigOccurrence ap) {
		this.ap = ap;
		setWindowTitle(RelRigAntipattern.getAntipatternInfo().name);		
	}
	
	@Override
	public void addPages() 
	{		
		for(int i=0;i<ap.getRigidMediatedProperties().size();i++){
			
			RelRigFirstPage firstpage = new RelRigFirstPage(ap,i);
			firstpageList.add(firstpage);
			
			RelRigSecondPage secondpage = new RelRigSecondPage(ap,i);
			secondpageList.add(secondpage);
			
			RelRigThirdPage thirdpage = new RelRigThirdPage(ap,i);
			thirdpageList.add(thirdpage);
			
			RelRigFourthPage fourthpage = new RelRigFourthPage(ap,i);
			fourthpageList.add(fourthpage);
		}		
		
		options = new RelRigRefactoringPage(ap.getRigidMediatedProperties());
		
		presentation = new PresentationPage(
			RelRigAntipattern.getAntipatternInfo().name,
			RelRigAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstpageList.get(0),
			options
		);
		finishing = new FinishingPage();
		
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

	public WizardPage getFirstPage(int rigid){
		for(RelRigFirstPage page: firstpageList){
			if (page.rigid == rigid) return page;
		}
		return null;
	}
	
	public WizardPage getFinishingPage(){
		return finishing;		
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
	
	@Override
	public boolean performFinish() {
		return false;
	}

}
