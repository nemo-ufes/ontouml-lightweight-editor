package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class GSRigFifthPage extends GSRigPage {
	
	private Button btnIntentional;
	private Button btnDerived;
	private Button btnEachRigid;
	private StyledText questionText;
	private ArrayList<String> antirigids = new ArrayList<String>();
	
	public GSRigFifthPage(GSRigOccurrence gsrig) 
	{
		super(gsrig);		
	}
	
	public void setAntirigids(ArrayList<String> antirigids)
	{
		this.antirigids = antirigids;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		questionText = new StyledText(container, SWT.FULL_SELECTION | SWT.READ_ONLY | SWT.WRAP);
		questionText.setAlwaysShowScrollBars(false);
		questionText.setText("Considering that there are rigid and anti-rigid subtypes in the generalization set, it implies that there is one or more implicit rigid subtype of "+OntoUMLNameHelper.getTypeAndName(gsrig.getParent(), true, true)+". " +
							"Would you like to create them:");
		
		btnIntentional = new Button(container, SWT.RADIO);
		btnIntentional.setText("One common intentional subtype for all anti-rigid ones");
		
		btnDerived = new Button(container, SWT.RADIO);
		btnDerived.setText("One common derived subtype for all anti-rigid ones, using negation");
		
		btnEachRigid = new Button(container, SWT.RADIO);
		btnEachRigid.setText("One rigid subtype for each anti-rigid");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(10))
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(9)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(btnIntentional, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnDerived, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnEachRigid, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnIntentional)
					.add(6)
					.add(btnDerived)
					.add(6)
					.add(btnEachRigid)
					.add(158))
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnIntentional.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			if(antirigids.size()>0)
				newAction.setCreateCommonSubtypeForAntiRigids(antirigids);
			else
				newAction.setCreateCommonSubtypeForAntiRigids();
			getGSRigWizard().replaceAction(1,newAction);
			//======================================
		}
		
		if(btnDerived.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateDerivationByNegation(); 
			getGSRigWizard().replaceAction(1,newAction);
			//======================================
			
			//Action =============================
			newAction = new GSRigAction(gsrig);
			if(antirigids.size()>0)
				newAction.setCreateCommonSubtypeForAntiRigids(antirigids);
			else
				newAction.setCreateCommonSubtypeForAntiRigids();
			getGSRigWizard().replaceAction(2,newAction);
			//======================================
		}
		
		if(btnEachRigid.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			if(antirigids.size()>0)
				newAction.setCreateRigidSubtypeForAntiRigids(antirigids);
			else
				newAction.setCreateRigidSubtypeForAntiRigids();
			getGSRigWizard().replaceAction(1,newAction);
			//======================================
		}
				
		return ((GSRigWizard)getWizard()).getFinishing();
	}
}