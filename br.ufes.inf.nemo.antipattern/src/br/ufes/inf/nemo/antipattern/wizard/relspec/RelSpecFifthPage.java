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

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecFifthPage extends RelSpecPage {

	public Button btnGeneral;
	public Button btnSpecific;

	/**
	 * Create the wizard.
	 */
	public RelSpecFifthPage(RelSpecOccurrence relSpec) 
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
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("Select the association you would like to delete:");
		styledText.setEditable(false);
		styledText.setJustify(true);
		styledText.setAlwaysShowScrollBars(false);
		  
		btnGeneral = new Button(container, SWT.RADIO);
		btnGeneral.setText("The CHILD association");
		setAsEnablingNextPageButton(btnGeneral);
		
		btnSpecific = new Button(container, SWT.RADIO);
		btnSpecific.setText("The PARENT association");
		setAsEnablingNextPageButton(btnSpecific);
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		Composite composite = new AssociationComposite(container, SWT.NONE, occurrence);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(composite, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(btnSpecific, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(9))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(btnGeneral, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(9))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.add(13)
					.add(btnSpecific)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnGeneral)
					.add(40)
					.add(label)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(composite, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		RelSpecAction newAction = new RelSpecAction(occurrence);
		if(btnGeneral.getSelection()) {
			//ACTION			
			newAction.setDeleteGeneral();
			getAntipatternWizard().replaceAction(0,newAction);
		}
		if(btnSpecific.getSelection()) {
			//ACTION			
			newAction.setDeleteSpecific();
			getAntipatternWizard().replaceAction(0,newAction);
		}
		
		return getAntipatternWizard().getFinishing();
	}
}
