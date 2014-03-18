package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class RigidTableComposite extends Composite{

	public GSRigOccurrence gsrig;
	private RigidTable rigidTable;
	
	public RigidTable getRigidTable() { return rigidTable; }
	
	public RigidTableComposite(Composite parent, int args, GSRigOccurrence gsrig) {
		super(parent,args);
		this.gsrig = gsrig;
				
		rigidTable = new RigidTable(this, SWT.V_SCROLL ,gsrig);
		rigidTable.getTable().setBounds(10, 10, 620, 80);
						
//		Composite composite = new Composite(this, SWT.NONE);
//		composite.setBounds(10, 10, 620, 80);
	}

	public void limitChoiceToRoleMixin() {
		rigidTable.limitChoiceToRoleMixin();		
	}
	
	public void limitChoiceToRoleAndPhase() {
		rigidTable.limitChoiceToRoleAndPhase();		
	}
}
