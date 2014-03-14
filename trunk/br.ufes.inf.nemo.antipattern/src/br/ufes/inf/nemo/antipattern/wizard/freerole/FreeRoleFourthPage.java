package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleFourthPage extends FreeRolePage {
	
	private int index = -1;
	private Label lblToCharacterizeThe;
	private FreeRoleIndependenceComposite composite; 
	
	public FreeRoleFourthPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
	{
		super(freeRole);		
		this.index = freeRoleIndex;
		setDescription("Role "+freeRole.getFreeRoles().get(index).getName());
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
		lblToCharacterizeThe.setText("To characterize the new independent relational dependency of "+freeRole.getFreeRoles().get(index).getName()+", we will need some additional information:");
			
//		Composite composite = new Composite(container, SWT.BORDER);
//		composite.setBounds(10, 54, 554, 218);
		
		composite = new FreeRoleIndependenceComposite(container,SWT.BORDER,freeRole,index);
		composite.setBounds(10, 54, 554, 218);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		//Action =============================				
		FreeRoleAction newAction = new FreeRoleAction(freeRole);
		newAction.setCreateNewRelatorWithMediation(freeRole.getFreeRoles().get(index), composite.getRelatorNameText().getText(), composite.getRelatorMultipCombo().getText(), composite.getRoleMultipCombo().getText()); 
		getFreeRoleWizard().replaceAction(index,newAction);	
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
				newAction = new FreeRoleAction(freeRole);
				newAction.setCreateDependentObjects(freeRole.getFreeRoles().get(index), typeName, stereo, relatorMultiplicity, mediatedMultiplicity,createMaterial); 
				getFreeRoleWizard().addAction(index,newAction);	
				//======================================
			}
		}
		
		if(index<freeRole.getFreeRoles().size()-1){
			return ((FreeRoleWizard)getWizard()).getFirstPage(index+1);
		}else{
			return ((FreeRoleWizard)getWizard()).getFinishing();
		}		
	}
}
	