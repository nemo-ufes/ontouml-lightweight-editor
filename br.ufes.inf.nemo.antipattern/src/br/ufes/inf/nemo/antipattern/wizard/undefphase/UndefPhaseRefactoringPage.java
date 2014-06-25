package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class UndefPhaseRefactoringPage extends RefactoringPage {
	
	protected UndefPhaseOccurrence up;
	private ChangeStereoTable stereoTable;
	private CreateDataTypeComposite createDataTypeComposite;
	private ExpandBar expandBar;
	private ConstraintComposite ctComposite;
	private CreateModeComposite modeComposite;
	/**
	 * Create the wizard.
	 */
	public UndefPhaseRefactoringPage(UndefPhaseOccurrence up) 
	{
		super();	
		this.up = up;
				
		setTitle(UndefPhaseAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
	}

	public UndefPhaseWizard getUndefPhaseWizard(){
		return ( UndefPhaseWizard)getWizard();
	}	
	
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
				
		setControl(container);
		
		expandBar = new ExpandBar(container, SWT.V_SCROLL);
		
		//===========================================
		
		Composite composite1 = new Composite (expandBar, SWT.NONE);
		GridLayout layout1 = new GridLayout ();
		layout1.marginLeft = layout1.marginTop=3;
		layout1.marginRight=layout1.marginBottom=3;
		layout1.verticalSpacing = 3;
		composite1.setLayout(layout1);
		
		createDataTypeComposite = new CreateDataTypeComposite(composite1, SWT.NONE,up,null);
		GridData gd_createDataTypeComposite = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_createDataTypeComposite.widthHint = 530;
		createDataTypeComposite.setLayoutData(gd_createDataTypeComposite);
		createDataTypeComposite.setVisible(true);
				
		ExpandItem item1 = new ExpandItem (expandBar, SWT.NONE, 0);
		item1.setText("Create new attributes for general");
		item1.setHeight
		(composite1.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item1.setControl(composite1);
		
		//=============================================
		
		Composite composite2 = new Composite (expandBar, SWT.NONE);
		GridLayout layout2 = new GridLayout ();
		layout2.marginLeft = layout2.marginTop=3;
		layout2.marginRight=layout2.marginBottom=3;
		layout2.verticalSpacing = 3;
		composite2.setLayout(layout2);
		
		ctComposite = new ConstraintComposite(composite2, SWT.NONE,up);
		GridData gd_ctComposite = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_ctComposite.widthHint = 509;
		ctComposite.setLayoutData(gd_ctComposite);
		ctComposite.setVisible(true);
				
		ExpandItem item2 = new ExpandItem (expandBar, SWT.NONE, 1);
		item2.setText("Create derivation rules for each phase");
		item2.setHeight
		(composite2.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item2.setControl(composite2);
		
		//=============================================
		
		Composite composite3 = new Composite (expandBar, SWT.NONE);
		GridLayout layout3 = new GridLayout ();
		layout3.marginLeft = layout3.marginTop=3;
		layout3.marginRight=layout3.marginBottom=3;
		layout3.verticalSpacing = 3;
		composite3.setLayout(layout3);
		
		modeComposite = new CreateModeComposite(composite3, SWT.NONE,up);
		GridData gd_modeComposite = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_modeComposite.widthHint = 530;
		modeComposite.setLayoutData(gd_modeComposite);
		modeComposite.setVisible(true);
				
		ExpandItem item3 = new ExpandItem (expandBar, SWT.NONE, 2);
		item3.setText("Create new modes characterizing each phase");
		item3.setHeight
		(composite3.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item3.setControl(composite3);
		
		//=============================================
		
		Composite composite4 = new Composite (expandBar, SWT.NONE);
		GridLayout layout4 = new GridLayout ();
		layout4.marginLeft = layout4.marginTop=3;
		layout4.marginRight=layout4.marginBottom=3;
		layout4.verticalSpacing = 3;
		composite4.setLayout(layout4);
		
		stereoTable = new ChangeStereoTable(composite4, SWT.NONE,up);
		stereoTable.getTable().setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		stereoTable.getTable().setVisible(true);
				
		ExpandItem item4 = new ExpandItem (expandBar, SWT.NONE, 3);
		item4.setText("Change phases stereotypes");
		item4.setHeight
		(composite4.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item4.setControl(composite4);			
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(expandBar, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(expandBar, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		getUndefPhaseWizard().removeAllActions();
		
		if(createDataTypeComposite.getValues().keySet().size()>=1)
		{
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> types = new ArrayList<String>();
			ArrayList<String> stereotypes = new ArrayList<String>();
			ArrayList<String> cardinalities = new ArrayList<String>();
			names.addAll(createDataTypeComposite.getValues().keySet());
			types.addAll(createDataTypeComposite.getValues().values());
			stereotypes.addAll(createDataTypeComposite.getStereotypes().values());
			cardinalities.addAll(createDataTypeComposite.getCardinalities().values());
			
			//Action =============================
			UndefPhaseAction newAction = new UndefPhaseAction(up);			
			newAction.setCreateAttributes(names, types, stereotypes, cardinalities);
			getUndefPhaseWizard().replaceAction(0,newAction);	
			//======================================
		}
		
		if(ctComposite.oclText.getText()!=null && !ctComposite.oclText.getText().isEmpty() && !ctComposite.oclText.getText().trim().isEmpty())
		{
			//Action =============================
			UndefPhaseAction newAction = new UndefPhaseAction(up);
			newAction.setCreateOclDerivationRules(ctComposite.oclText.getText());
			getUndefPhaseWizard().replaceAction(1,newAction);	
			//======================================			
		}
		
		if(modeComposite.getValues().keySet().size()>=1)
		{
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> cardinalities = new ArrayList<String>();
			names.addAll(modeComposite.getValues().keySet());
			cardinalities.addAll(modeComposite.getValues().values());
			ArrayList<Classifier> phases = modeComposite.getPhases();
			
			//Action =============================
			UndefPhaseAction newAction = new UndefPhaseAction(up);			
			newAction.setCreateModes(names, cardinalities,phases);
			getUndefPhaseWizard().replaceAction(2,newAction);	
			//======================================	
		}
		
		if(stereoTable!=null && !stereoTable.isAllPhase()){
			
			//Action =============================
			UndefPhaseAction newAction = new UndefPhaseAction(up);			
			newAction.setChangeStereotypes(stereoTable.getStereotypes());
			getUndefPhaseWizard().replaceAction(3,newAction);	
			//======================================
		}			
		
		return getUndefPhaseWizard().getFinishing();		
	}
}
		
