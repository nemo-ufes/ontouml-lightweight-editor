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

public class RelSpecSecondPage extends RelSpecPage {

	//GUI
	public Button btnYes;
	public Button btnNo;
	private AssociationComposite group;
	private Label label;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecSecondPage(RelSpecOccurrence relSpec) 
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
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		String question =
			"By your previous answer, we conclude that there is a missing restriction in your model. The CHILD association either subsets or redefines the PARENT association. "+
			"To figure out which restriction should be added, answer the following question: "+
			"\n\n" +
			"Must an instance 'x' of "+OntoUMLNameHelper.getTypeAndName(occurrence.getAlignedSpecificSource(), true, true)+" (or an instance 'y' of "+OntoUMLNameHelper.getTypeAndName(occurrence.getAlignedSpecificTarget(),true,true)+
			") be connected to exactly the same instances by CHILD and the PARENT associations?"; 
		  
		styledText.setText(question);
		styledText.setEditable(false);
		styledText.setJustify(true);
		styledText.setAlwaysShowScrollBars(false);
			    
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes (Redefinition)");
		setAsEnablingNextPageButton(btnYes);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No (Subsetting)");
		setAsEnablingNextPageButton(btnNo);
		
		group = new AssociationComposite(container, SWT.NONE,occurrence);
		
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(10)
							.add(styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(btnYes)))
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(btnNo, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(400, Short.MAX_VALUE))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(group, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnYes)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnNo)
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
		
		if(btnNo.getSelection()) {
			// Action =====================
			RelSpecAction newAction = new RelSpecAction(occurrence);
			newAction.setSubset();
			
			getAntipatternWizard().replaceAction(0,newAction);
						
			return getAntipatternWizard().getFinishing(); 
		}
			
		else if(btnYes.getSelection()){			
			
			//CONDITION
			if(occurrence.isVariation4() || occurrence.isVariation5())
				return getAntipatternWizard().getThirdPage();
			else{
				// Action =====================
				RelSpecAction newAction = new RelSpecAction(occurrence);
				newAction.setRedefine();
				getAntipatternWizard().replaceAction(0,newAction);
				
				return getAntipatternWizard().getFinishing();
			}
		}
		return super.getNextPage();
	}
}
