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

public class RelSpecFirstPage extends RelSpecPage {

	//GUI
	public Button btnRequired;
	public Button btnForbidden;
	public Button btnPossible;
	private Composite group;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecFirstPage(RelSpecOccurrence relSpec) 
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
		styledText.setText(	"Consider an instance 'x' of "+OntoUMLNameHelper.getTypeAndName(occurrence.getAlignedSpecificSource(), true, true)+
							" that is related to an instance 'y' of " +
							OntoUMLNameHelper.getTypeAndName(occurrence.getAlignedSpecificTarget(),true,true)+
							", through the CHILD association. What can be said about the same 'x' being connected to 'y' through the PARENT association?"
		);
		styledText.setEditable(false);
		styledText.setJustify(true);
		
		btnRequired = new Button(container, SWT.RADIO);
		btnRequired.setText("Required");		
		setAsEnablingNextPageButton(btnRequired);
		
		btnForbidden = new Button(container, SWT.RADIO);
		btnForbidden.setText("Forbbiden");
		setAsEnablingNextPageButton(btnForbidden);
		
		btnPossible = new Button(container, SWT.RADIO);
		btnPossible.setText("Possible, but not required");
		setAsEnablingNextPageButton(btnPossible);
		
		group = new AssociationComposite(container, SWT.NONE,occurrence);
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(btnPossible, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(btnForbidden, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(btnRequired, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(group, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnRequired)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnForbidden)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnPossible)
					.add(40)
					.add(label, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(group, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnPossible.getSelection()) {
			
			getAntipatternWizard().removeAllActions();
			
			return getAntipatternWizard().getFinishing();
			
		}else if(btnForbidden.getSelection()){			
						
			RelSpecAction newAction = new RelSpecAction(occurrence);
			newAction.setDisjoint();
			getAntipatternWizard().replaceAction(0,newAction);
			
			return getAntipatternWizard().getFinishing();
		}
		else if(btnRequired.getSelection()){
			
			return getAntipatternWizard().getSecondPage();
		}
		return super.getNextPage();
	}
}
