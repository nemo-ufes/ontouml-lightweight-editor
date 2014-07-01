package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

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
		String text = ""+occurrence.getDefinedRole().getName()+" is directly or indirectly connected to the following relators: ";
		int i=0;
		for(RefOntoUML.Property p: occurrence.getDefiningRelatorEnds()){
			if (i==occurrence.getDefiningRelatorEnds().size()) text += p.getType().getName();
			else if (i==occurrence.getDefiningRelatorEnds().size()) text += p.getType().getName()+", ";
		}
		text += "\r\n\r\n"+
		"Please select below the dependencies which are used to define "+occurrence.getFreeRoles().get(index).getName()+". For each, select if it is necessary to specialize the existing relator.";
		lblIsDirectlyOr.setText(text);
		
		dependenceTable = new FreeRoleDependenceTable(container, SWT.BORDER, occurrence);
		Table table = dependenceTable.getTable();
//		Table table = new Table(container, SWT.BORDER);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(table, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
							.add(10))
						.add(gl_container.createSequentialGroup()
							.add(lblIsDirectlyOr, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
							.add(10))))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblIsDirectlyOr, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(table, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
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