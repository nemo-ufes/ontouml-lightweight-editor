package br.ufes.inf.nemo.antipattern.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RefactoringPage extends WizardPage {

	public Label lblChooseTheAppropriate;
	public Label lblnoteThatSome;
	public Composite checkListContainer;
	public Composite container;
	
	/**
	 * Create the wizard.
	 */
	public RefactoringPage() {
		super("Refactoring Page");
		setTitle("Refactoring Page");
		setDescription("The follwing options can be used to refactor the model.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		lblChooseTheAppropriate = new Label(container, SWT.NONE);
		lblChooseTheAppropriate.setText("Choose the appropriate refactoring options:");
		
		lblnoteThatSome = new Label(container, SWT.NONE);
		lblnoteThatSome.setAlignment(SWT.RIGHT);
		lblnoteThatSome.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblnoteThatSome.setText("(Note that some options may be incompatible)");
		
		checkListContainer = new Composite(container, SWT.NONE);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, checkListContainer, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(lblnoteThatSome)
						.add(lblChooseTheAppropriate, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblChooseTheAppropriate)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(checkListContainer, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblnoteThatSome)
					.addContainerGap())
		);
		container.setLayout(gl_container);
	}
	
	public Composite getCheckListContainer(){
		return checkListContainer;
	}
	
	public void setCheckListContainer(Composite c){
		checkListContainer = c;
	}
	public void setContainer(Composite c){
		container = c;
	}
}
