package br.ufes.inf.nemo.antipattern.wizard.relspec;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecRefactoringPage extends RefactoringPage {
	
	protected RelSpecOccurrence relSpec;
	
	private Button btnSubsets;
	private Button btnRedefine;
	private Button btnDisjoint;
	private Button btnNothing;
	private Button btnSpecializeRedefine;
	private Button btnDelete;
	private CCombo comboDelete;
	private CCombo comboSpecializeRedefine;
	private Composite composite;
	private Label label;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecRefactoringPage(RelSpecOccurrence relSpec) 
	{
		super();	
		this.relSpec = relSpec;
				
		setTitle(RelSpecAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public RelSpecWizard getRelSpecWizard(){
		return (RelSpecWizard)getWizard();
	}
	
	class RadioButtonListener {
		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		Label lblChooseTheAppropriate = new Label(container, SWT.NONE);
		lblChooseTheAppropriate.setText("Choose the appropriate refactoring option:");
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (isPageComplete()==false) setPageComplete(true);
	      }
	    };
		    
	    //TODO
	    setPageComplete(true);
	    
		btnSubsets = new Button(container, SWT.RADIO);
		btnSubsets.setText("Child association SUBSETS Parent association");
		btnSubsets.addSelectionListener(listener);
		//TODO
		btnSubsets.setSelection(true);
		
		btnRedefine = new Button(container, SWT.RADIO);
		btnRedefine.setText("Child association REDEFINES Parent association");
		btnRedefine.addSelectionListener(listener);
//		btnRedefine.setSelection(true);
		
		btnDisjoint = new Button(container, SWT.RADIO);
		btnDisjoint.setText("Associations are disjoint");
		btnDisjoint.addSelectionListener(listener);
		
		btnNothing = new Button(container, SWT.RADIO);
		btnNothing.setText("Do nothing");
		btnNothing.addSelectionListener(listener);
		
		btnSpecializeRedefine = new Button(container, SWT.RADIO);
		btnSpecializeRedefine.setText("Specialize ends and redefine");
		btnSpecializeRedefine.addSelectionListener(listener);
		
		btnDelete = new Button(container, SWT.RADIO);
		btnDelete.setText("Delete one of the associations");
		btnDelete.addSelectionListener(listener);
		
		comboSpecializeRedefine = new CCombo(container, SWT.BORDER);
		comboSpecializeRedefine.setItems(new String[] {	relSpec.getParser().getStringRepresentation(relSpec.getSpecificSource())+" ("+relSpec.getParser().getStringRepresentation(relSpec.getSpecific())+" source end)", 
										relSpec.getParser().getStringRepresentation(relSpec.getSpecificTarget())+" ("+relSpec.getParser().getStringRepresentation(relSpec.getSpecific())+" target end)",
										"Both ends of "+relSpec.getParser().getStringRepresentation(relSpec.getSpecific()), 
										relSpec.getParser().getStringRepresentation(relSpec.getGeneralSource())+" ("+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+" source end)", 
										relSpec.getParser().getStringRepresentation(relSpec.getGeneralTarget())+" ("+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+" target end)",
										"Both ends of "+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())});
					
		comboDelete = new CCombo(container, SWT.BORDER);
		comboDelete.setItems(new String[] {	relSpec.getParser().getStringRepresentation(relSpec.getGeneral()), 
											relSpec.getParser().getStringRepresentation(relSpec.getSpecific())});
		
		composite = new AssociationComposite(container, SWT.NONE,relSpec);
		
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(gl_container.createSequentialGroup()
									.add(gl_container.createParallelGroup(GroupLayout.LEADING)
										.add(btnNothing, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
										.add(btnDisjoint, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
										.add(btnRedefine, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
										.add(btnSubsets, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
									.add(233))
								.add(gl_container.createSequentialGroup()
									.add(gl_container.createParallelGroup(GroupLayout.TRAILING, false)
										.add(GroupLayout.LEADING, btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.add(GroupLayout.LEADING, btnSpecializeRedefine, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
									.addPreferredGap(LayoutStyle.RELATED)
									.add(gl_container.createParallelGroup(GroupLayout.LEADING)
										.add(comboSpecializeRedefine, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
										.add(comboDelete, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))))
							.addContainerGap())
						.add(gl_container.createSequentialGroup()
							.add(lblChooseTheAppropriate, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
							.add(10))))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(composite, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblChooseTheAppropriate)
					.add(13)
					.add(btnSubsets)
					.add(9)
					.add(btnRedefine)
					.add(9)
					.add(btnDisjoint)
					.add(9)
					.add(btnNothing)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(btnDelete)
						.add(comboDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(btnSpecializeRedefine)
						.add(comboSpecializeRedefine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED, 29, Short.MAX_VALUE)
					.add(label)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(composite, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
		);
		container.setLayout(gl_container);
		
		if (!(relSpec.isVariation4() || relSpec.isVariation5())){
			comboSpecializeRedefine.setEnabled(false);
			btnSpecializeRedefine.setEnabled(false);
		}
			
	}

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}

	@Override
	public IWizardPage getNextPage() {
		getRelSpecWizard().removeAllActions(0);
		RelSpecAction newAction = new RelSpecAction(relSpec);
		if (btnSubsets.getSelection()){
			// Action =====================
			newAction.setSubset();
			getRelSpecWizard().replaceAction(0,newAction);
		}
		if (btnRedefine.getSelection()){
			// Action =====================
			newAction.setRedefine();			
			getRelSpecWizard().replaceAction(0,newAction);
		}
		if (btnDisjoint.getSelection()){
			// Action =====================
			newAction.setDisjoint();
			getRelSpecWizard().replaceAction(0,newAction);
		}
		
		if (btnSpecializeRedefine!=null && btnSpecializeRedefine.getSelection()){
			ClassStereotype source = ClassStereotype.ROLE;
			ClassStereotype target = ClassStereotype.ROLE;
			
			if(comboSpecializeRedefine.getSelectionIndex()==0) {
				// Action =====================
				newAction.setSpec_Specific_Source_Redefine(source);		
			}
			if(comboSpecializeRedefine.getSelectionIndex()==1) {
				// Action =====================
				newAction.setSpec_Specific_Target_Redefine(target);
			}
			if(comboSpecializeRedefine.getSelectionIndex()==2) {
				// Action =====================
				newAction.setSpec_Specific_Both_Redefine(source,target);
			}
			if(comboSpecializeRedefine.getSelectionIndex()==3) {
				// Action =====================
				newAction.setSpec_General_Source_Redefine(source);	
			}
			if(comboSpecializeRedefine.getSelectionIndex()==4) {
				// Action =====================
				newAction.setSpec_General_Target_Redefine(target);
			}
			if(comboSpecializeRedefine.getSelectionIndex()==5) {
				// Action =====================
				newAction.setSpec_General_Both_Redefine(source,target);			
			}			
			getRelSpecWizard().replaceAction(0,newAction);
		}
		
		if (btnDelete!=null && btnDelete.getSelection()){
			if(comboDelete.getSelectionIndex()==0){
				//ACTION
				newAction.setDeleteGeneral();
			}
			if(comboDelete.getSelectionIndex()==1){
				//ACTION
				newAction.setDeleteSpecific();
			}
			getRelSpecWizard().replaceAction(0,newAction);
		}				
		if(btnNothing.getSelection()){
			getRelSpecWizard().removeAllActions();
		}
	
		return 	getRelSpecWizard().getFinishing();				
	}
}
