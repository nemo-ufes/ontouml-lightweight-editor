package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class GSRigSubTypeComposite extends Composite{

	public GSRigOccurrence gsrig;
	private Button btnAllAntirigid;
	private Button btnAllRigid;
	private GSRigSubTypeTable subtypeTable;
	
	public GSRigSubTypeComposite(Composite parent, int args, GSRigOccurrence gsrig) {
		super(parent,args);
		this.gsrig = gsrig;
				
		subtypeTable = new GSRigSubTypeTable(this, SWT.V_SCROLL ,gsrig);
		subtypeTable.getTable().setBounds(10, 10, 524, 150);
		
		SelectionAdapter allRigidListener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  subtypeTable.populatesRigid();
	      }
	    };
		   
	    SelectionAdapter antiRigidListener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  subtypeTable.populatesAntiRigid();
	      }
	    };
	    
		btnAllRigid = new Button(this, SWT.NONE);
		btnAllRigid.setBounds(540, 10, 80, 25);
		btnAllRigid.setText("All rigid");
		btnAllRigid.addSelectionListener(allRigidListener);
				    
		btnAllAntirigid = new Button(this, SWT.NONE);
		btnAllAntirigid.setBounds(540, 41, 80, 25);
		btnAllAntirigid.setText("All anti-rigid");
		btnAllAntirigid.addSelectionListener(antiRigidListener);
		
//		Composite composite = new Composite(this, SWT.NONE);
//		composite.setBounds(10, 10, 524, 150);
	}
}
