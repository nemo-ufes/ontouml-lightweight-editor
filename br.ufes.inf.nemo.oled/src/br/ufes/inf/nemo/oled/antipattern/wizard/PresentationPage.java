package br.ufes.inf.nemo.oled.antipattern.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class PresentationPage extends WizardPage {
	
	public String title;
	public String apType;
	public String elements;	
	
	//GUI
	public Label lblModelElements;
	public Button btnGoOptions;
	public Button btnGoWizard;
	public StyledText styledText;
	
	/**
	 * Create the wizard.
	 */
	public PresentationPage(String title, String apType, String elements) 
	{
		super(title);
		if (this.apType==null) this.apType = "";
		else this.apType = apType;
		this.elements = elements;
		setTitle(title);
		setDescription("This Wizard will help you decide if the occurrence of the "+apType+" antipattern characterizes an error.");
		if (lblModelElements!=null){
			lblModelElements.setText("The combination of the following elements characterize an occurence of the "+this.apType+" antipattern:");
		}
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		lblModelElements = new Label(container, SWT.NONE);
		lblModelElements.setText("The combination of the following elements characterize an occurence of the "+this.apType+" antipattern:");
		
		Label lblWouldYouLike = new Label(container, SWT.NONE);
		lblWouldYouLike.setText("Would you like to:");
		
		btnGoWizard = new Button(container, SWT.RADIO);
		btnGoWizard.setText("use the step-by-step wizard?");
		btnGoWizard.setSelection(true);
		
		btnGoOptions = new Button(container, SWT.RADIO);
		btnGoOptions.setText("go directly to the refactoring options?");
				
		styledText = new StyledText(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.WRAP);
		styledText.setText(elements);
	    
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(GroupLayout.LEADING, gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(styledText, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE)
						.add(lblModelElements, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, lblWouldYouLike, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(btnGoWizard, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(btnGoOptions, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblModelElements)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblWouldYouLike)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnGoWizard)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnGoOptions)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		
		container.setLayout(gl_container);
	}	
}
