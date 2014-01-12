package br.ufes.inf.nemo.oled.antipattern.wizard.relspec;

import org.eclipse.jface.wizard.Wizard;

import br.ufes.inf.nemo.antipattern.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.oled.antipattern.wizard.ActionWizard;
import br.ufes.inf.nemo.oled.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.oled.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecWizard extends Wizard {

	public boolean canFinish = true;
	
	protected RelSpecOccurrence ap;
	
	protected PresentationPage presentation;
	protected FinishingPage finishing;
		  
	protected RelSpecRefactoringPage options;
	
	protected RelSpecFirstPage firstPage;
	protected RelSpecSecondPage secondPage;
	protected RelSpecThirdPage thirdPage;
	protected RelSpecFourthPage fourthPage;
	protected RelSpecFifthPage fifthPage;
	
	public enum RelSpecAction {SUBSET, REDEFINE, DISJOINT, SPEC_SPECIFIC_SOURCE_REDEFINE, SPEC_SPECIFIC_TARGET_REDEFINE
		, SPEC_GENERAL_SOURCE_REDEFINE, SPEC_GENERAL_TARGET_REDEFINE, DELETE_SPECIFIC, DELETE_GENERAL, SPEC_GENERAL_BOTH_REDEFINE, SPEC_SPECIFIC_BOTH_REDEFINE }
	
	private ActionWizard<RelSpecAction> action;
	
	public ActionWizard<RelSpecAction> getAction() {
		return action;
	}

	public RelSpecWizard(RelSpecOccurrence ap) {
		this.ap = ap;
	    canFinish=false;
	    action = new ActionWizard<RelSpecAction>();
	    setNeedsProgressMonitor(true); 
		setWindowTitle(RelSpecAntipattern.getAntipatternInfo().name);
	}
	
    @Override
    public boolean canFinish() {	 
    	return canFinish;	  
    };
    
	@Override
	public void addPages() 
	{	
		options = new RelSpecRefactoringPage(ap);
		finishing = new FinishingPage();
		
		firstPage = new RelSpecFirstPage(ap);
		secondPage = new RelSpecSecondPage(ap);
		thirdPage = new RelSpecThirdPage(ap);
		fourthPage = new RelSpecFourthPage(ap);
		fifthPage = new RelSpecFifthPage(ap);
		
		presentation = new PresentationPage(
			RelSpecAntipattern.getAntipatternInfo().name,
			RelSpecAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstPage,
			options
		);
		
		addPage(presentation);		
		addPage(firstPage);
		addPage(secondPage);
		addPage(thirdPage);
		addPage(fourthPage);
		addPage(fifthPage);
		addPage(options);
		addPage(finishing);
	}
		
	public boolean isCanFinish() {
		return canFinish;
	}

	public RelSpecOccurrence getAp() {
		return ap;
	}

	public PresentationPage getPresentation() {
		return presentation;
	}

	public FinishingPage getFinishing() {
		canFinish=true;
		return finishing;
	}

	public RelSpecRefactoringPage getOptions() {
		return options;
	}

	public RelSpecFirstPage getFirstPage() {
		return firstPage;
	}

	public RelSpecSecondPage getSecondPage() {
		return secondPage;
	}

	public RelSpecThirdPage getThirdPage() {
		return thirdPage;
	}

	public RelSpecFourthPage getFourthPage() {
		return fourthPage;
	}

	public RelSpecFifthPage getFifthPage() {
		return fifthPage;
	}

	@Override
	public boolean performFinish() {
		
		if(action.getCode()==RelSpecAction.SUBSET)
			ap.generateOCL(RelSpecOccurrence.OperationType.SUBSET);
		if(action.getCode()==RelSpecAction.REDEFINE)
			ap.generateOCL(RelSpecOccurrence.OperationType.REDEFINE);
		if(action.getCode()==RelSpecAction.DISJOINT)
			ap.generateOCL(RelSpecOccurrence.OperationType.DISJOINT);
		
		if(action.getCode()==RelSpecAction.DELETE_GENERAL)
			ap.deleteGeneral();
		if(action.getCode()==RelSpecAction.DELETE_SPECIFIC)
			ap.deleteSpecific();
		
		if(action.getCode()==RelSpecAction.SPEC_GENERAL_SOURCE_REDEFINE)
			ap.createGeneralSourceSubTypeAndRedefine((ClassStereotype) action.getParameterValue("stereotype"));
		if(action.getCode()==RelSpecAction.SPEC_GENERAL_TARGET_REDEFINE)
			ap.createGeneralTargetSubTypeAndRedefine((ClassStereotype) action.getParameterValue("stereotype"));
		if(action.getCode()==RelSpecAction.SPEC_GENERAL_BOTH_REDEFINE)
			ap.createGeneralBothSubTypesAndRedefine((ClassStereotype) action.getParameterValue("sourceStereotype"),(ClassStereotype) action.getParameterValue("targetStereotype"));
		if(action.getCode()==RelSpecAction.SPEC_SPECIFIC_SOURCE_REDEFINE)
			ap.createSpecificSourceSubTypeAndRedefine((ClassStereotype) action.getParameterValue("stereotype"));
		if(action.getCode()==RelSpecAction.SPEC_SPECIFIC_SOURCE_REDEFINE)
			ap.createSpecificTargetSubTypeAndRedefine((ClassStereotype) action.getParameterValue("stereotype"));
		if(action.getCode()==RelSpecAction.SPEC_SPECIFIC_BOTH_REDEFINE)
			ap.createSpecificBothSubTypesAndRedefine((ClassStereotype) action.getParameterValue("sourceStereotype"),(ClassStereotype) action.getParameterValue("targetStereotype"));

		System.out.println("ACTION: "+action.getCode());
		System.out.println("PAR: "+action.getParameters());
		
		return true;
	}

}
