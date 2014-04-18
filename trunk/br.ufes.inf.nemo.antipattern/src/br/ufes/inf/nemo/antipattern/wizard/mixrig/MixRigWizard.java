package br.ufes.inf.nemo.antipattern.wizard.mixrig;

import java.util.ArrayList;

import RefOntoUML.impl.CategoryImpl;
import RefOntoUML.impl.CollectiveImpl;
import RefOntoUML.impl.KindImpl;
import RefOntoUML.impl.PhaseImpl;
import RefOntoUML.impl.QuantityImpl;
import RefOntoUML.impl.RoleImpl;
import RefOntoUML.impl.RoleMixinImpl;
import RefOntoUML.impl.SubKindImpl;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class MixRigWizard extends AntipatternWizard {
	
	public MixRigFirstPage firstPage;
	public MixRigSecondPage secondPage;
	public MixRigThirdPage thirdPage;
	
	protected String subtypesRigidity, oppositeRigidity, mixinName;
	
	public MixRigWizard(MixRigOccurrence mixRig) {
		super(mixRig,MixRigAntipattern.getAntipatternInfo().name);
		
		mixinName = mixRig.getMixin().getName();
		
		if(mixRig.rigidSubtypes())
		{
			subtypesRigidity = "rigid";
			oppositeRigidity = "anti-rigid";
		}
		else{
			subtypesRigidity = "anti-rigid";
			oppositeRigidity = "rigid";
		}
	}

	@Override
	public void addPages() 
	{
		firstPage = new MixRigFirstPage(getAp());
		secondPage = new MixRigSecondPage(getAp());
		thirdPage = new MixRigThirdPage(getAp());
		
		finishing = new FinishingPage();
		options = new MixRigRefactoringPage(getAp());
		
		presentation = new PresentationPage(
			MixRigAntipattern.getAntipatternInfo().name,
			MixRigAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			MixRigAntipattern.getAntipatternInfo().description,
			firstPage,
			options
		);
			
		addPage(presentation);	
		addPage(firstPage);
		addPage(secondPage);
		addPage(thirdPage);
		addPage(options);
		addPage(finishing);
	}
	
	public MixRigOccurrence getAp() {
		return (MixRigOccurrence)ap;
	}
	
	public MixRigFirstPage getFirstPage()
	{
		return firstPage;
	}
	
	public MixRigSecondPage getSecondPage() {
		return secondPage;
	}

	public MixRigThirdPage getThirdPage()
	{
		return thirdPage;
	}

	public ArrayList<Class<?>> allowedStereotypes(){
		ArrayList<Class<?>> allowedStereotypes = new ArrayList<Class<?>>();
		
		if(getAp().rigidSubtypes()){
			allowedStereotypes.add(RoleImpl.class);
			allowedStereotypes.add(PhaseImpl.class);
			allowedStereotypes.add(RoleMixinImpl.class);
		}
		else {
			allowedStereotypes.add(KindImpl.class);
			allowedStereotypes.add(CollectiveImpl.class);
			allowedStereotypes.add(QuantityImpl.class);
			allowedStereotypes.add(SubKindImpl.class);
			allowedStereotypes.add(CategoryImpl.class);
		}
		
		return allowedStereotypes;
	}
}
