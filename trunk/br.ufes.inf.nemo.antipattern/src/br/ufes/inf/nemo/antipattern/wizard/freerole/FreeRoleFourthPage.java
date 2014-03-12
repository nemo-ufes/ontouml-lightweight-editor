package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

public class FreeRoleFourthPage extends FreeRolePage {
	
	private int index = -1;
	private Label lblToCharacterizeThe;
	private Text relatorNameText;
	private Label lblTheNameOf;
	private Label lblMultiplicityOnEnd;
	private Combo multCombo;
	private Label lblMultiplicityOnRelator;
	private Combo combo;
	private Label lblTypesToWhich;
	
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
		
		Composite composite = new Composite(container, SWT.BORDER);
		composite.setBounds(10, 54, 554, 218);
		
		lblTheNameOf = new Label(composite, SWT.NONE);
		lblTheNameOf.setBounds(10, 13, 189, 15);
		lblTheNameOf.setText("The name of the new Relator:");
		
		relatorNameText = new Text(composite, SWT.BORDER);
		relatorNameText.setBounds(210, 10, 203, 21);
		
		lblMultiplicityOnEnd = new Label(composite, SWT.NONE);
		lblMultiplicityOnEnd.setBounds(10, 40, 189, 15);
		lblMultiplicityOnEnd.setText("Multiplicity on "+freeRole.getFreeRoles().get(index).getName()+" End:");
		
		lblMultiplicityOnRelator = new Label(composite, SWT.NONE);
		lblMultiplicityOnRelator.setBounds(10, 73, 189, 15);
		lblMultiplicityOnRelator.setText("Multiplicity on Relator End: ");
		
		combo = new Combo(composite, SWT.NONE);
		combo.setBounds(210, 70, 48, 23);
		combo.setItems(new String[] {"0..1", "1", "1..*", "0..*"});
		combo.select(2);
		
		multCombo = new Combo(composite, SWT.NONE);
		multCombo.setBounds(210, 37, 48, 23);
		multCombo.setItems(new String[] {"0..1", "1", "1..*", "0..*"});
		multCombo.select(2);
		
		lblTypesToWhich = new Label(composite, SWT.WRAP);
		lblTypesToWhich.setBounds(10, 107, 534, 15);
		lblTypesToWhich.setText("Types to which "+freeRole.getFreeRoles().get(index).getName()+" is dependent on: (you can use existing types or create new ones) ");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		//Action =============================
//		FreeRoleAction newAction = new FreeRoleAction(freeRole);
//		newAction.setCreateOCLDerivation(freeRole.getFreeRoles().get(index),styledText.getText()); 
//		geFreeRoleWizard().replaceAction(0,newAction);	
		//======================================
		
		if(index<freeRole.getFreeRoles().size()-1){
			return ((FreeRoleWizard)getWizard()).getFirstPage(index+1);
		}else{
			return ((FreeRoleWizard)getWizard()).getFinishing();
		}		
	}
}
	