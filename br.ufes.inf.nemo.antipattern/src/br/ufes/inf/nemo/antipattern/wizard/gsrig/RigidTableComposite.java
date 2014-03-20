package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class RigidTableComposite extends Composite{

	public GSRigOccurrence gsrig;
	private RigidTable rigidTable;
	private Label lblNewStereotype;
	public Combo stereoCombo;
	
	public RigidTable getRigidTable() { return rigidTable; }
	
	public RigidTableComposite(Composite parent, int args, GSRigOccurrence gsrig) {
		super(parent,args);
		this.gsrig = gsrig;
//				
		rigidTable = new RigidTable(this, SWT.V_SCROLL ,gsrig);
		rigidTable.getTable().setBounds(248, 21, 362, 95);
						
		Composite composite2 = new Composite(this, SWT.NONE);
		composite2.setBounds(10, 21, 232, 95);
			
		lblNewStereotype = new Label(composite2, SWT.NONE);
		lblNewStereotype.setBounds(10, 10, 278, 15);
		lblNewStereotype.setText("New "+gsrig.getGs().getGeneralization().get(0).getGeneral().getName()+" stereotype:");
		
		stereoCombo = new Combo(composite2, SWT.READ_ONLY);
		stereoCombo.setBounds(10, 39, 155, 23);
		stereoCombo.setItems(new String[] {"Role", "Phase", "RoleMixin"});
		stereoCombo.select(0);
		
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
		rigidTable.enable(value);
	}
	public void limitChoiceToRoleMixin() {
		rigidTable.limitChoiceToRoleMixin();		
	}
	
	public void limitChoiceToRoleAndPhase() {
		rigidTable.limitChoiceToRoleAndPhase();		
	}
}
