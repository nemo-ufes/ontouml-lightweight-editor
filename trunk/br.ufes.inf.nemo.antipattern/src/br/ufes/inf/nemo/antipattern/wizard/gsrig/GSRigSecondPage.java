package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class GSRigSecondPage extends GSRigPage {

	private Label lblWhichSubtypesHave;
	private Button btnRigid;
	private Button btnAntiRigid;
	private Button btnMixed;
	private List antiRigidList;
	private List rigidList;
	private List semiRigidList;
	
	public GSRigSecondPage(GSRigOccurrence gsrig) 
	{
		super(gsrig);		
	}

	
	public void setListValues(){
		if(antiRigidList!=null){
			antiRigidList.removeAll();
			for (Classifier antiRigid : gsrig.getAntiRigidSpecifics()) {
				antiRigidList.add(OntoUMLNameHelper.getTypeAndName(antiRigid, true, true));
			}	
		}
		
		if(rigidList!=null){
			rigidList.removeAll();
			for (Classifier rigid : gsrig.getRigidSpecifics()) {
				rigidList.add(OntoUMLNameHelper.getTypeAndName(rigid, true, true));
			}	
		}
		
		if(semiRigidList!=null){
			semiRigidList.removeAll();
			for (Classifier semiRigid : gsrig.getSemiRigidSpecifics()) {
				semiRigidList.add(OntoUMLNameHelper.getTypeAndName(semiRigid, true, true));
			}	
		}
		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		lblWhichSubtypesHave = new Label(container, SWT.NONE);
		lblWhichSubtypesHave.setText("Which subtypes have the same specialization criteria?");
		
		btnRigid = new Button(container, SWT.RADIO);
		btnRigid.setText("Only the rigid subtypes");
		btnRigid.addSelectionListener(canGoToNextPageAdapter);
		
		btnAntiRigid = new Button(container, SWT.RADIO);
		btnAntiRigid.setText("Only the anti-rigid subtypes");
		btnAntiRigid.addSelectionListener(canGoToNextPageAdapter);
		
		btnMixed = new Button(container, SWT.RADIO);
		btnMixed.setText("The rigid subtypes have one and the anti-rigid another");
		
		rigidList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("Rigid Subtypes:");
		
		antiRigidList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		semiRigidList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		setListValues();
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		Label lblAntirigidSubtypes = new Label(container, SWT.NONE);
		lblAntirigidSubtypes.setText("Anti-Rigid Subtypes:");
		
		Label lblSemirigidSubtypes = new Label(container, SWT.NONE);
		lblSemirigidSubtypes.setText("Semi-Rigid Subtypes:");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(10)
							.add(gl_container.createParallelGroup(GroupLayout.LEADING, false)
								.add(btnMixed, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
								.add(btnAntiRigid, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
								.add(btnRigid, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
								.add(lblWhichSubtypesHave, GroupLayout.PREFERRED_SIZE, 655, GroupLayout.PREFERRED_SIZE)))
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(rigidList, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.add(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.add(18)
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(gl_container.createSequentialGroup()
									.add(antiRigidList, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
									.add(18)
									.add(semiRigidList, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
								.add(gl_container.createSequentialGroup()
									.add(lblAntirigidSubtypes, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
									.add(18)
									.add(lblSemirigidSubtypes, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))))
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblWhichSubtypesHave)
					.add(10)
					.add(btnRigid)
					.add(6)
					.add(btnAntiRigid)
					.add(6)
					.add(btnMixed)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(label, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
						.add(lblNewLabel)
						.add(lblAntirigidSubtypes)
						.add(lblSemirigidSubtypes))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
						.add(rigidList, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
						.add(antiRigidList, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
						.add(semiRigidList, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.linkSize(new Control[] {lblWhichSubtypesHave, btnRigid, btnAntiRigid, btnMixed}, GroupLayout.HORIZONTAL);
		container.setLayout(gl_container);
		btnMixed.addSelectionListener(canGoToNextPageAdapter);
		
		setPageComplete(false);
	}
	
	private SelectionAdapter canGoToNextPageAdapter = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			if(btnMixed.getSelection() || btnAntiRigid.getSelection() || btnRigid.getSelection()){
				if(!isPageComplete())
					setPageComplete(true);
			}
			else {
				if(isPageComplete())
					setPageComplete(false);
			}
			
		}
	};
	
	
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnRigid.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForRigids(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
			return getGSRigWizard().getFinishing();
		}
		
		if(btnAntiRigid.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForAntiRigids(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
			return getGSRigWizard().getFinishing();
		}
		
		if(btnMixed.getSelection())
		{			
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForBoth(); 
			getGSRigWizard().replaceAction(0,newAction);	
			//======================================
			return getGSRigWizard().getFinishing();
		}
		
		return super.getNextPage();
	}
}
	