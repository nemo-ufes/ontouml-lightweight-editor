package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleFourthPage extends FreeRolePage {
	
	private Label lblToCharacterizeThe;
	private FreeRoleIndependenceComposite composite; 
	
	public FreeRoleFourthPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
	{
		super(freeRole);		
		this.index = freeRoleIndex;
		setDescription(	"Defined Role: " +freeRole.getDefinedRole().getName()+
				"\nCurrent Free Role: "+freeRole.getFreeRoles().get(index).getName());
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
	
		setControl(container);
		
		lblToCharacterizeThe = new Label(container, SWT.WRAP);
		lblToCharacterizeThe.setBounds(10, 10, 554, 38);
		lblToCharacterizeThe.setText("To characterize the new independent relational dependency of "+occurrence.getFreeRoles().get(index).getName()+", we will need some additional information:");
		
		composite = new FreeRoleIndependenceComposite(container,SWT.BORDER,occurrence,index);
		composite.setBounds(10, 54, 554, 218);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		//Action =============================				
		FreeRoleAction newAction = new FreeRoleAction(occurrence);
		newAction.setCreateNewRelatorWithMediation(occurrence.getFreeRoles().get(index), composite.getRelatorNameText().getText(), composite.getRelatorMultipCombo().getText(), composite.getRoleMultipCombo().getText()); 
		getAntipatternWizard().replaceAction(index,newAction);	
		//======================================
		
		if(composite.getIndependenceTable().getTypeNames().size()>=1)
		{
			int i=1;
			for(String typeName: composite.getIndependenceTable().getTypeNames())
			{
				String stereo = composite.getIndependenceTable().getStereotypes().get(i-1);
				String relatorMultiplicity = composite.getIndependenceTable().getMultRelatorEnd().get(i-1);
				String mediatedMultiplicity = composite.getIndependenceTable().getMultMediatedEnd().get(i-1);
				Boolean createMaterial = composite.getIndependenceTable().getCreateMaterial().get(i-1);
				
				//Action =============================				
				newAction = new FreeRoleAction(occurrence);
				newAction.setCreateDependentObjects(occurrence.getFreeRoles().get(index), typeName, stereo, relatorMultiplicity, mediatedMultiplicity,createMaterial); 
				getAntipatternWizard().addAction(index,newAction);	
				//======================================
			}
		}
		
		if(index<occurrence.getFreeRoles().size()-1){
			return getAntipatternWizard().getFirstPage(index+1);
		}else{
			return getAntipatternWizard().getFinishing();
		}		
	}
}
	