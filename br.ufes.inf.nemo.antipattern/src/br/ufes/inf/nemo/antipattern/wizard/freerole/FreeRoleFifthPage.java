package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleFifthPage extends FreeRolePage {
	
	private Label lblIsDirectlyOr;
	private FreeRoleDependenceTable dependenceTable;
	
	public FreeRoleFifthPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
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
		
		lblIsDirectlyOr = new Label(container, SWT.WRAP);
		lblIsDirectlyOr.setBounds(10, 10, 554, 94);
		String text = ""+occurrence.getDefinedRole().getName()+" is directly or indirectly connected to the following relators: ";
		int i=0;
		for(RefOntoUML.Property p: occurrence.getDefiningRelatorEnds()){
			if (i==occurrence.getDefiningRelatorEnds().size()) text += p.getType().getName();
			else if (i==occurrence.getDefiningRelatorEnds().size()) text += p.getType().getName()+", ";
		}
		text += "\r\n\r\n"+
		"Please select below the dependencies which are used to define "+occurrence.getFreeRoles().get(index).getName()+". For each, select if it is necessary to specialize the existing relator.";
		lblIsDirectlyOr.setText(text);
		
		dependenceTable = new FreeRoleDependenceTable(container, SWT.NONE,occurrence);
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
				FreeRoleAction newAction = new FreeRoleAction(occurrence);
				newAction.setCreateMediation((Relator)occurrence.getDefiningRelatorEnds().get(i).getType(),occurrence.getFreeRoles().get(index),relatorEndMultip,roleEndMultip); 
				getAntipatternWizard().addAction(index,newAction);	
				//======================================				
			}
			if(use && specialize){
				
				String relatorName = dependenceTable.getRelatorNames().get(i);
				//Action =============================
				FreeRoleAction newAction = new FreeRoleAction(occurrence);
				newAction.setCreateSubRelatorWithMediation((Relator)occurrence.getDefiningRelatorEnds().get(i).getType(),occurrence.getFreeRoles().get(index),relatorName,relatorEndMultip,roleEndMultip); 
				getAntipatternWizard().addAction(index,newAction);	
				//======================================
			}
			i++;
		}
				
		if(index<occurrence.getFreeRoles().size()-1){
			return getAntipatternWizard().getFirstPage(index+1);
		}else{
			return getAntipatternWizard().getFinishing();
		}			
	}
}