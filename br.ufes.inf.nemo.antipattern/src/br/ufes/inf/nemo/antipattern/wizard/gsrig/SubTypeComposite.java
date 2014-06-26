package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class SubTypeComposite extends Composite{

	public GSRigOccurrence gsrig;
	private Button btnAllAntirigid;
	private Button btnAllRigid;
	private SubTypeTable subtypeTable;
	private Table table;
	
	public SubTypeTable getSubtypeTable() { return subtypeTable; }
	
	public SubTypeComposite(Composite parent, int args, GSRigOccurrence gsrig) {
		super(parent,args);
		this.gsrig = gsrig;
				
		
		
		subtypeTable = new SubTypeTable(this, SWT.BORDER | SWT.V_SCROLL ,gsrig);
		table = subtypeTable.getTable();
//		table = new Table(this, SWT.BORDER);
		
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
		btnAllRigid.setText("All rigid");
		btnAllRigid.addSelectionListener(allRigidListener);
				    
		btnAllAntirigid = new Button(this, SWT.NONE);
		btnAllAntirigid.setText("All anti-rigid");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(table, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.TRAILING)
						.add(btnAllRigid, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.add(btnAllAntirigid, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(table, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
						.add(groupLayout.createSequentialGroup()
							.add(btnAllRigid)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(btnAllAntirigid)))
					.add(11))
		);
		setLayout(groupLayout);
		btnAllAntirigid.addSelectionListener(antiRigidListener);
		
//		Composite composite = new Composite(this, SWT.NONE);
//		composite.setBounds(10, 10, 524, 150);
	}
	
	public void enable(boolean value)
	{
		subtypeTable.getTable().setEnabled(value);
		btnAllRigid.setEnabled(value);
		btnAllAntirigid.setEnabled(value);
	}
}
