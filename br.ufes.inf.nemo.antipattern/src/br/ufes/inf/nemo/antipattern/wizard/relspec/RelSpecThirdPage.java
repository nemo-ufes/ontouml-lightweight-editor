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

public class RelSpecThirdPage extends RelSpecPage {
	
	//GUI
	public Button btnSpecialize;
	public Button btnDelete;
	public Button btnKeep;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecThirdPage(RelSpecOccurrence relSpec) 
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
		
		StyledText styledText = new StyledText(container, SWT.WRAP | SWT.V_SCROLL);
		styledText.setAlwaysShowScrollBars(false);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText(
			"When two distinct associations connected the very same types, if said that one redefines the other, " +
			"they turn out to be replicas, increasing the model’s complexity, without adding new information." +
			"\n\n" +
			"If you would like to keep both associations and it is true that one redefines the other, at least one of end types should be specialized into a new class. " +
			"Would you like to:");
		styledText.setEditable(false);
		styledText.setJustify(true);
				    
		btnSpecialize = new Button(container, SWT.RADIO);
		btnSpecialize.setText("Specialize ends and include the redefinition constraint");
		setAsEnablingNextPageButton(btnSpecialize);
		
		btnKeep = new Button(container, SWT.RADIO);
		btnKeep.setText("Keep the model as it is and include the redefinition constraint");
		setAsEnablingNextPageButton(btnKeep);
		
		btnDelete = new Button(container, SWT.RADIO);
		btnDelete.setText("Delete one of the associations (redefinition constraint will not be included)");
		setAsEnablingNextPageButton(btnDelete);
		
		Composite composite = new AssociationComposite(container, SWT.NONE,occurrence);
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, btnSpecialize, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, gl_container.createSequentialGroup()
							.add(btnKeep, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
							.add(4))
						.add(GroupLayout.LEADING, gl_container.createSequentialGroup()
							.add(btnDelete, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
							.add(7)))
					.add(9))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(composite, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnSpecialize)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnKeep)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnDelete)
					.add(40)
					.add(label, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(composite, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					)
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnKeep.getSelection()) {
			// Action =====================
			RelSpecAction newAction = new RelSpecAction(occurrence);
			newAction.setRedefine();
			getAntipatternWizard().replaceAction(0,newAction);
						
			return getAntipatternWizard().getFinishing();
		}	
		else if(btnSpecialize.getSelection()){			
			return getAntipatternWizard().getFourthPage();			
		}
		else if (btnDelete.getSelection()){
			return getAntipatternWizard().getFifthPage();
		}
		return super.getNextPage();
	}
}
