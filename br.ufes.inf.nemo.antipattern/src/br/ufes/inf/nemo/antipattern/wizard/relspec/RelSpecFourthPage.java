package br.ufes.inf.nemo.antipattern.wizard.relspec;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecFourthPage extends RelSpecPage {

	//GUI
	public Button btnSpecificSource;
	public Button btnSpecificBoth;
	public Button btnSpecificTarget;
	public Button btnGeneralSource;
	public Button btnGeneralBoth;
	public Button btnGeneralTarget;
	private Composite composite;
	private Label label;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecFourthPage(RelSpecOccurrence relSpec) 
	{
		super(relSpec);
		setDescription("Associations: "+OntoUMLNameHelper.getTypeAndName(relSpec.getGeneral(), true, true)+" and "+OntoUMLNameHelper.getTypeAndName(relSpec.getSpecific(), true, true));
			
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		
		setPageComplete(false);
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("For which ends would like to create subtypes?");
		styledText.setJustify(true);
		styledText.setAlwaysShowScrollBars(false);
			    
		btnSpecificSource = new Button(container, SWT.RADIO);
		btnSpecificSource.setText(OntoUMLNameHelper.getTypeAndName(occurrence.getSpecificSource(), true, true)+" (source end of the CHILD association)");
		btnSpecificSource.setSelection(true);
		setAsEnablingNextPageButton(btnSpecificSource);
		
		btnSpecificTarget = new Button(container, SWT.RADIO);
		btnSpecificTarget.setText(OntoUMLNameHelper.getTypeAndName(occurrence.getSpecificTarget(), true, true)+" (target end of the CHILD association)");
		setAsEnablingNextPageButton(btnSpecificTarget);
		
		btnSpecificBoth = new Button(container, SWT.RADIO);
		btnSpecificBoth.setText(OntoUMLNameHelper.getTypeAndName(occurrence.getSpecificSource(), true, true)+" and "+OntoUMLNameHelper.getTypeAndName(occurrence.getSpecificTarget(), true, true)+" (both ends of the CHILD association)");
		setAsEnablingNextPageButton(btnSpecificBoth);
		
		btnGeneralSource = new Button(container, SWT.RADIO);
		btnGeneralSource.setText(OntoUMLNameHelper.getTypeAndName(occurrence.getGeneralSource(), true, true)+" (source end of the PARENT association)");
		setAsEnablingNextPageButton(btnGeneralSource);
		
		btnGeneralTarget = new Button(container, SWT.RADIO);
		btnGeneralTarget.setText(OntoUMLNameHelper.getTypeAndName(occurrence.getGeneralTarget(), true, true)+" (target end of the PARENT association)");
		setAsEnablingNextPageButton(btnGeneralTarget);
		
		btnGeneralBoth = new Button(container, SWT.RADIO);
		btnGeneralBoth.setText(OntoUMLNameHelper.getTypeAndName(occurrence.getGeneralSource(), true, true)+" and "+OntoUMLNameHelper.getTypeAndName(occurrence.getGeneralTarget(), true, true)+" (both ends of the PARENT association)");
		setAsEnablingNextPageButton(btnGeneralBoth);
		
		composite = new AssociationComposite(container, SWT.NONE, occurrence);
		
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(btnGeneralSource, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
						.add(btnGeneralTarget, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
						.add(btnGeneralBoth, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
						.add(btnSpecificSource, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
						.add(btnSpecificTarget, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
						.add(btnSpecificBoth, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
					.add(9))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(composite, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnGeneralSource)
					.add(6)
					.add(btnGeneralTarget)
					.add(6)
					.add(btnGeneralBoth)
					.add(6)
					.add(btnSpecificSource)
					.add(6)
					.add(btnSpecificTarget)
					.add(6)
					.add(btnSpecificBoth)
					.add(36)
					.add(label, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(composite, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.add(0, 0, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
			
	}
	
	@Override
	public IWizardPage getNextPage() {

		ClassStereotype source = ClassStereotype.ROLE;
		ClassStereotype target = ClassStereotype.ROLE;
		
		RelSpecAction newAction = new RelSpecAction(occurrence);
		if(btnGeneralSource.getSelection()) {
			// Action =====================	
			newAction.setSpec_General_Source_Redefine(source);
			getAntipatternWizard().replaceAction(0,newAction);
		}
		if(btnGeneralTarget.getSelection()) {
			// Action =====================
			newAction.setSpec_General_Target_Redefine(target);
			getAntipatternWizard().replaceAction(0,newAction);
		}
		if(btnGeneralBoth.getSelection()) {
			// Action =====================
			newAction.setSpec_General_Both_Redefine(source,target);
			getAntipatternWizard().replaceAction(0,newAction);
		}
		if(btnSpecificSource.getSelection()) {			
			// Action =====================
			newAction.setSpec_Specific_Source_Redefine(source);
			getAntipatternWizard().replaceAction(0,newAction);
		}
		if(btnSpecificTarget.getSelection()) {
			// Action =====================
			newAction.setSpec_Specific_Target_Redefine(target);
			getAntipatternWizard().replaceAction(0,newAction);
		}
		if(btnSpecificBoth.getSelection()) {
			// Action =====================
			newAction.setSpec_Specific_Both_Redefine(source,target);
			getAntipatternWizard().replaceAction(0,newAction);
		}
				
		return getAntipatternWizard().getFinishing();
		
	}
}
