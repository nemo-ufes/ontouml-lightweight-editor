package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleIndependenceComposite extends Composite{

	private int index =-1;
	private Text relatorNameText;
	private Label lblTheNameOf;
	private Label lblMultiplicityOnEnd;
	private Combo roleMultip;
	private Label lblMultiplicityOnRelator;
	private Combo relatorMultip;
	private Label lblTypesToWhich;
	private Button btnAdd;
	private FreeRoleIndependenceTable independenceTable;
	
	public FreeRoleIndependenceTable getIndependenceTable()
	{
		return independenceTable;
	}
	
	public Text getRelatorNameText()
	{
		return relatorNameText;
	}
	
	public Combo getRelatorMultipCombo()
	{
		return relatorMultip;
	}
	
	public Combo getRoleMultipCombo()
	{
		return roleMultip;
	}
	
	public FreeRoleIndependenceComposite(Composite parent, int args, FreeRoleOccurrence freeRole, int freeRoleIndex)
	{
		super(parent,args);
		this.index = freeRoleIndex;
		
		lblTheNameOf = new Label(this, SWT.NONE);
		lblTheNameOf.setBounds(10, 13, 189, 15);
		lblTheNameOf.setText("The name of the new Relator:");
		
		relatorNameText = new Text(this, SWT.BORDER);
		relatorNameText.setBounds(210, 10, 330, 21);
		relatorNameText.setText("");
		
		lblMultiplicityOnEnd = new Label(this, SWT.NONE);
		lblMultiplicityOnEnd.setBounds(10, 40, 189, 15);
		lblMultiplicityOnEnd.setText("Multiplicity on "+freeRole.getFreeRoles().get(index).getName()+" End:");
		
		lblMultiplicityOnRelator = new Label(this, SWT.NONE);
		lblMultiplicityOnRelator.setBounds(292, 40, 189, 15);
		lblMultiplicityOnRelator.setText("Multiplicity on Relator End: ");
		
		relatorMultip = new Combo(this, SWT.NONE);
		relatorMultip.setBounds(492, 37, 48, 23);
		relatorMultip.setItems(new String[] {"0..1", "1", "1..*", "0..*"});
		relatorMultip.select(2);
		
		roleMultip = new Combo(this, SWT.NONE);
		roleMultip.setBounds(210, 37, 48, 23);
		roleMultip.setItems(new String[] {"0..1", "1", "1..*", "0..*"});
		roleMultip.select(2);
		
		lblTypesToWhich = new Label(this, SWT.WRAP);
		lblTypesToWhich.setBounds(10, 81, 490, 15);
		lblTypesToWhich.setText("Types to which "+freeRole.getFreeRoles().get(index).getName()+" is dependent on:");
		
	//	Composite composite_1 = new Composite(composite,SWT.NONE);
	//	composite_1.setBounds(10, 111, 530, 73);
		independenceTable = new FreeRoleIndependenceTable(this, SWT.BORDER, freeRole);
		independenceTable.getTable().setBounds(10, 111, 530, 73);
		
		SelectionAdapter newLineListneer = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  independenceTable.addLine();	
	      }
	    };
		    
		btnAdd = new Button(this, SWT.NONE);
		btnAdd.setBounds(506, 76, 34, 25);
		btnAdd.setText("Add");
		btnAdd.addSelectionListener(newLineListneer);
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 66, 530, 8);
		
		Label lblyouCanUse = new Label(this, SWT.NONE);
		lblyouCanUse.setBounds(10, 189, 530, 15);
		lblyouCanUse.setText("You can use existing types or create new ones...");	
	}
}
