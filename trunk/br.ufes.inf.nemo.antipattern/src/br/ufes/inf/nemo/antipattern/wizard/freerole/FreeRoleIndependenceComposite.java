package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

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
	private Table table;
	
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
		lblTheNameOf.setText("Name (New Relator):");
		
		relatorNameText = new Text(this, SWT.BORDER);
		relatorNameText.setText("");
		
		lblMultiplicityOnEnd = new Label(this, SWT.NONE);
		lblMultiplicityOnEnd.setText("Mult. (Mediated):");
		
		lblMultiplicityOnRelator = new Label(this, SWT.NONE);
		lblMultiplicityOnRelator.setText("Mult. (New Relator):");
		
		relatorMultip = new Combo(this, SWT.NONE);
		relatorMultip.setItems(new String[] {"0..1", "1", "1..*", "0..*"});
		relatorMultip.select(2);
		
		roleMultip = new Combo(this, SWT.NONE);
		roleMultip.setItems(new String[] {"0..1", "1", "1..*", "0..*"});
		roleMultip.select(2);
		
		lblTypesToWhich = new Label(this, SWT.WRAP);
		lblTypesToWhich.setText("Types to which "+freeRole.getFreeRoles().get(index).getName()+" is dependent on:");
		
		independenceTable = new FreeRoleIndependenceTable(this, SWT.BORDER, freeRole);
		table = independenceTable.getTable();
//		table = new Table(this, SWT.BORDER);
		
		SelectionAdapter newLineListneer = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  independenceTable.addLine();	
	      }
	    };
		    
		btnAdd = new Button(this, SWT.NONE);
		btnAdd.setText("Add");
		btnAdd.addSelectionListener(newLineListneer);
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		Label lblyouCanUse = new Label(this, SWT.NONE);
		lblyouCanUse.setText("You can use existing types or create new ones...");	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(label, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
							)
						.add(groupLayout.createSequentialGroup()
							.add(lblTypesToWhich, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
							.add(8)
							.add(btnAdd))
						.add(groupLayout.createSequentialGroup()
							.add(table, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
							)
						.add(groupLayout.createSequentialGroup()
							.add(lblyouCanUse, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
							)
						.add(groupLayout.createSequentialGroup()
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING, false)
								.add(lblMultiplicityOnEnd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.add(lblTheNameOf, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(relatorNameText, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
								.add(groupLayout.createSequentialGroup()
									.add(roleMultip, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
									.addPreferredGap(LayoutStyle.UNRELATED)
									.add(lblMultiplicityOnRelator, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(relatorMultip, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))))
					)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(relatorNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblTheNameOf))
					.add(9)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(lblMultiplicityOnEnd)
						.add(roleMultip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(relatorMultip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblMultiplicityOnRelator))
					.add(6)
					.add(label, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.add(7)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(lblTypesToWhich)
						.add(btnAdd))
					.add(6)
					.add(table, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.add(5)
					.add(lblyouCanUse)
					)
		);
		setLayout(groupLayout);
	}
}
