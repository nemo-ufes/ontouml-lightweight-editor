package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleFifthPage extends FreeRolePage {
	
	private int index = -1;
	private Label lblIsDirectlyOr;
	private FreeRoleDependenceTable dependenceTable;
	
	public FreeRoleFifthPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
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
		
		lblIsDirectlyOr = new Label(container, SWT.WRAP);
		lblIsDirectlyOr.setBounds(10, 10, 554, 94);
		String text = ""+freeRole.getDefinedRole().getName()+" is directly or indirectly connected to the following relators: ";
		int i=0;
		for(RefOntoUML.Property p: freeRole.getDefiningRelatorEnds()){
			if (i==freeRole.getDefiningRelatorEnds().size()) text += p.getType().getName();
			else if (i==freeRole.getDefiningRelatorEnds().size()) text += p.getType().getName()+", ";
		}
		text += "\r\n\r\n"+
		"Please select below the dependencies which are used to define "+freeRole.getFreeRoles().get(index).getName()+". For each, select if it is necessary to specialize the existing relator.";
		lblIsDirectlyOr.setText(text);
		
		dependenceTable = new FreeRoleDependenceTable(container, SWT.NONE,freeRole);
		dependenceTable.getTable().setBounds(10, 110, 554, 126);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		int i=0;
		for(Boolean use: dependenceTable.getUse())
		{
			Boolean specialize = dependenceTable.getSpecialize().get(i);
			String relatorEndMultip = dependenceTable.getMultRelatorEnd().get(i);
			String roleEndMultip = dependenceTable.getMultRoleEnd().get(i);
						
			if(use && !specialize) {
				//Action =============================
				FreeRoleAction newAction = new FreeRoleAction(freeRole);
				newAction.setCreateMediation((Relator)freeRole.getDefiningRelatorEnds().get(i).getType(),freeRole.getFreeRoles().get(index),relatorEndMultip,roleEndMultip); 
				getFreeRoleWizard().addAction(index,newAction);	
				//======================================				
			}
			if(use && specialize){
				
				String relatorName = dependenceTable.getRelatorNames().get(i);
				//Action =============================
				FreeRoleAction newAction = new FreeRoleAction(freeRole);
				newAction.setCreateSubRelatorWithMediation((Relator)freeRole.getDefiningRelatorEnds().get(i).getType(),freeRole.getFreeRoles().get(index),relatorName,relatorEndMultip,roleEndMultip); 
				getFreeRoleWizard().addAction(index,newAction);	
				//======================================
			}
			i++;
		}
				
		if(index<freeRole.getFreeRoles().size()-1){
			return ((FreeRoleWizard)getWizard()).getFirstPage(index+1);
		}else{
			return ((FreeRoleWizard)getWizard()).getFinishing();
		}			
	}
}