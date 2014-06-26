package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class RigidTableComposite extends Composite{

	public GSRigOccurrence gsrig;
	private RigidTable tableCreator;
	private Label lblNewStereotype;
	public Combo stereoCombo;
	
	public RigidTable getRigidTable() { return tableCreator; }
	
	public RigidTableComposite(Composite parent, int args, GSRigOccurrence gsrig) {
		super(parent,args);
		this.gsrig = gsrig;
		
		
		tableCreator = new RigidTable(this, SWT.BORDER | SWT.V_SCROLL ,gsrig);
		Table table = tableCreator.getTable();
//		Table table = new Table(this, SWT.BORDER);
		
		Composite composite2 = new Composite(this, SWT.BORDER);
			
		lblNewStereotype = new Label(composite2, SWT.NONE);
		lblNewStereotype.setText("New parent stereotype:");
		
		stereoCombo = new Combo(composite2, SWT.READ_ONLY);
		stereoCombo.setItems(new String[] {"Role", "Phase", "RoleMixin"});
		stereoCombo.select(0);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(composite2, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
				.add(table, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(composite2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(table, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
		);
		GroupLayout gl_composite2 = new GroupLayout(composite2);
		gl_composite2.setHorizontalGroup(
			gl_composite2.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite2.createSequentialGroup()
					.add(10)
					.add(lblNewStereotype, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(stereoCombo, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
					.add(10))
		);
		gl_composite2.setVerticalGroup(
			gl_composite2.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite2.createSequentialGroup()
					.add(10)
					.add(lblNewStereotype))
				.add(gl_composite2.createSequentialGroup()
					.add(7)
					.add(stereoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		composite2.setLayout(gl_composite2);
		setLayout(groupLayout);
		
//		Composite composite = new Composite(this, SWT.NONE);
//		composite.setBounds(248, 21, 362, 95);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  if(stereoCombo.getText().compareToIgnoreCase("Role")==0 || stereoCombo.getText().compareToIgnoreCase("Phase")==0){
	    		  	limitChoiceToRoleAndPhase();
	    	  	  	setVisible(true);
	    	  }else{
	    		  	limitChoiceToRoleMixin();
	    		  	setVisible(true);
	    	  }
	      }
	    };
	    stereoCombo.addSelectionListener(listener);    
	    
	}

	public void enable(boolean value)
	{
		tableCreator.enable(value);
	}
	public void limitChoiceToRoleMixin() {
		tableCreator.limitChoiceToRoleMixin();		
	}
	
	public void limitChoiceToRoleAndPhase() {
		tableCreator.limitChoiceToRoleAndPhase();		
	}
}
