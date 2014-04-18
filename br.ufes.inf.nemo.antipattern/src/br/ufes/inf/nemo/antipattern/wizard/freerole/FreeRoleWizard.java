package br.ufes.inf.nemo.antipattern.wizard.freerole;

import java.util.ArrayList;

import RefOntoUML.Role;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleAntipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class FreeRoleWizard extends AntipatternWizard {

	public ArrayList<FreeRoleFirstPage> firstPageList = new ArrayList<FreeRoleFirstPage>();
	public ArrayList<FreeRoleSecondPage> secondPageList = new ArrayList<FreeRoleSecondPage>();
	public ArrayList<FreeRoleThirdPage> thirdPageList = new ArrayList<FreeRoleThirdPage>();
	public ArrayList<FreeRoleFourthPage> fourthPageList = new ArrayList<FreeRoleFourthPage>();
	public ArrayList<FreeRoleFifthPage> fifthPageList = new ArrayList<FreeRoleFifthPage>();
	
	public FreeRoleWizard(FreeRoleOccurrence ap) {
		super(ap,FreeRoleAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		for(Role role: ((FreeRoleOccurrence)ap).getFreeRoles()){
			firstPageList.add(new FreeRoleFirstPage((FreeRoleOccurrence)ap,((FreeRoleOccurrence)ap).getFreeRoles().indexOf(role)));
			secondPageList.add(new FreeRoleSecondPage((FreeRoleOccurrence)ap,((FreeRoleOccurrence)ap).getFreeRoles().indexOf(role)));
			thirdPageList.add(new FreeRoleThirdPage((FreeRoleOccurrence)ap,((FreeRoleOccurrence)ap).getFreeRoles().indexOf(role)));
			fourthPageList.add(new FreeRoleFourthPage((FreeRoleOccurrence)ap,((FreeRoleOccurrence)ap).getFreeRoles().indexOf(role)));
			fifthPageList.add(new FreeRoleFifthPage((FreeRoleOccurrence)ap,((FreeRoleOccurrence)ap).getFreeRoles().indexOf(role)));
		}
				
		finishing = new FinishingPage();
		options = new FreeRoleRefactoringPage(getAp());
		
		presentation = new PresentationPage(
				FreeRoleAntipattern.getAntipatternInfo().name,
				FreeRoleAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			FreeRoleAntipattern.getAntipatternInfo().description,
			firstPageList.get(0),
			options
		);
		
		addPage(presentation);
		for(int i=0; i<firstPageList.size();i++) { 
			addPage(firstPageList.get(i));
			addPage(secondPageList.get(i));
			addPage(thirdPageList.get(i));
			addPage(fourthPageList.get(i));
			addPage(fifthPageList.get(i));
		}				
		addPage(options);
		addPage(finishing);
	}
	
	public FreeRoleOccurrence getAp() {
		return (FreeRoleOccurrence)ap;
	}
	
	public FreeRoleFirstPage getFirstPage(int index)
	{
		return firstPageList.get(index);
	}
	public FreeRoleSecondPage getSecondPage(int index)
	{
		return secondPageList.get(index);
	}
	public FreeRoleThirdPage getThirdPage(int index)
	{
		return thirdPageList.get(index);
	}
	public FreeRoleFourthPage getFourthPage(int index)
	{
		return fourthPageList.get(index);
	}
	public FreeRolePage getFifthPage(int index)
	{
		return fifthPageList.get(index);
	}
	
}