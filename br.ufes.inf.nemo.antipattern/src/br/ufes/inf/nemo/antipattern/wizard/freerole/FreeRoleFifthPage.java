package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.Relator;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleFifthPage extends FreeRolePage {
	
	private Label lblIsDirectlyOr;
	private FreeRoleDependenceTable dependenceTable;
	
	public FreeRoleFifthPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
	{
		super(freeRole);		
		this.index = freeRoleIndex;
		setDescription(	"Defined Role: " +freeRole.getDependentType().getName()+
						"\nCurrent Free Role: "+freeRole.getFreeRoles().get(index).getName());
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		String currentFreeRole = OntoUMLNameHelper.getTypeAndName(occurrence.getFreeRoles().get(index), true, true);
		
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		
		lblIsDirectlyOr = new Label(container, SWT.WRAP);
		lblIsDirectlyOr.setText("Please select below the dependencies which are used to define "+currentFreeRole+". " +
								"\r\n\r\n" +
								"On the first column you will find the relator's name. Check the column \"Use?\" if you want to create a dependency to the current relator. " +
								"Check the column \"Specialize\" if you wish to create a subtype of the current relator. Use the \"New Relator's Name\" column to specify the name of the new relator subtype (only use it if you checked \"Specialize\") column. " +
								"The columns \"Mult. Role End\" and \"Mult. Relator End\" to specify the multiplicities to be created.");
		
		dependenceTable = new FreeRoleDependenceTable(container, SWT.BORDER, occurrence);
		Table table = dependenceTable.getTable();
//		Table table = new Table(container, SWT.BORDER);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setText("(For each line checked on \"Use\", a new mediation connecting the current Free Role and the respective relator.)");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblNewLabel, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
					.addContainerGap())
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, table, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, lblIsDirectlyOr, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblIsDirectlyOr, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(table, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblNewLabel))
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		int i=0;
		for(Boolean use : dependenceTable.getUse())
		{
			Boolean specialize = dependenceTable.getSpecialize().get(i);
			
			if(use && !specialize) {
				String relatorEndMultip = dependenceTable.getMultRelatorEnd().get(i);
				String roleEndMultip = dependenceTable.getMultRoleEnd().get(i);
				Classifier relator = (Relator) dependenceTable.getTable().getItem(i).getData("relator");
				//Action =============================
				FreeRoleAction newAction = new FreeRoleAction(occurrence);
				newAction.setCreateMediation(relator,occurrence.getFreeRoles().get(index),relatorEndMultip,roleEndMultip); 
				getAntipatternWizard().addAction(index,newAction);	
				//======================================				
			}
			if(use && specialize){
				String relatorEndMultip = dependenceTable.getMultRelatorEnd().get(i);
				String roleEndMultip = dependenceTable.getMultRoleEnd().get(i);
				Classifier relator = (Relator) dependenceTable.getTable().getItem(i).getData("relator");
				String relatorName = dependenceTable.getRelatorNames().get(i);
				//Action =============================
				FreeRoleAction newAction = new FreeRoleAction(occurrence);
				newAction.setCreateSubRelatorWithMediation(relator,occurrence.getFreeRoles().get(index),relatorName,relatorEndMultip,roleEndMultip); 
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