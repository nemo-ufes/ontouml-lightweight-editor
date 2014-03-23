package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import java.util.ArrayList;

import RefOntoUML.Category;
import RefOntoUML.Mixin;
import RefOntoUML.RoleMixin;
import RefOntoUML.impl.CollectiveImpl;
import RefOntoUML.impl.KindImpl;
import RefOntoUML.impl.PhaseImpl;
import RefOntoUML.impl.QuantityImpl;
import RefOntoUML.impl.RoleImpl;
import RefOntoUML.impl.SubKindImpl;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class MixIdenWizard extends AntipatternWizard {

	public MixIdenFirstPage firstPage;
	public MixIdenSecondPage secondPage;
	protected String mixinName, mixinRigidity, mixinStereotype;
	
	public MixIdenWizard(MixIdenOccurrence mixIden) {
		super(mixIden,MixIdenAntipattern.getAntipatternInfo().name);
		
		mixinName = mixIden.getMixin().getName();
		
		if(mixIden.getMixin() instanceof Mixin){
			mixinRigidity = "semi-rigid";
			mixinStereotype = "Mixin";
		}
		if(mixIden.getMixin() instanceof RoleMixin){ 
			mixinRigidity = "anti-rigid";
			mixinStereotype = "RoleMixin";
		}
		if(mixIden.getMixin() instanceof Category){
			mixinRigidity = "rigid";
			mixinStereotype = "Category";
		}
	}

	@Override
	public void addPages() 
	{
		firstPage = new MixIdenFirstPage(getAp());
		secondPage = new MixIdenSecondPage(getAp());
		
		finishing = new FinishingPage();
		options = new MixIdenRefactoringPage(getAp());
		
		presentation = new PresentationPage(
			DepPhaseAntipattern.getAntipatternInfo().name,
			DepPhaseAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstPage,
			options
		);
			
		addPage(presentation);	
		addPage(firstPage);
		addPage(secondPage);
		addPage(options);
		addPage(finishing);
	}
	
	public MixIdenOccurrence getAp() {
		return (MixIdenOccurrence)ap;
	}
	
	public MixIdenFirstPage getFirstPage()
	{
		return firstPage;
	}
	
	public MixIdenSecondPage getSecondPage()
	{
		return secondPage;
	}

	@Override
	public boolean performFinish() {
		runAllActions();
		return true;
	}
	public ArrayList<Class<?>> allowedStereotypes(){
		ArrayList<Class<?>> allowedStereotypes = new ArrayList<Class<?>>();
		
		if(getAp().getMixin() instanceof RoleMixin || getAp().getMixin() instanceof Mixin){
			allowedStereotypes.add(RoleImpl.class);
			allowedStereotypes.add(PhaseImpl.class);
		}
		
		if(getAp().getMixin() instanceof Category || getAp().getMixin() instanceof Mixin){
			allowedStereotypes.add(KindImpl.class);
			allowedStereotypes.add(CollectiveImpl.class);
			allowedStereotypes.add(QuantityImpl.class);
			allowedStereotypes.add(SubKindImpl.class);
		}
		
		return allowedStereotypes;
	}
	
	public ArrayList<Class<?>> identityProviderStereotypes(){
		ArrayList<Class<?>> identityProviderStereotypes = new ArrayList<Class<?>>();

		identityProviderStereotypes.add(KindImpl.class);
		identityProviderStereotypes.add(CollectiveImpl.class);
		identityProviderStereotypes.add(QuantityImpl.class);
		
		return identityProviderStereotypes;
	}
}
