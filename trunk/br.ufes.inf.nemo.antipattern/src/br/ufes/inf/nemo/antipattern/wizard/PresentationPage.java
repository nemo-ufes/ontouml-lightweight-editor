package br.ufes.inf.nemo.antipattern.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class PresentationPage extends WizardPage {
	
	public String title;
	public String apType;
	public String elements;
	public String genericDescription;
	
	//GUI
	public Label lblModelElements;
	public Button btnGoOptions;
	public Button btnGoWizard;
	public StyledText combinationText;
	
	// Next page
	public WizardPage stepByStep;
	public WizardPage optionPage;
	private StyledText descriptionText;
	
	/**
	 * Create the wizard.
	 */
	public PresentationPage(String title, String apType, String elements, String genericDescription, WizardPage stepByStep, WizardPage optionPage) 
	{
		super(title);
		
		if (this.apType==null) this.apType = "";
		else this.apType = apType;
		
		this.elements = elements;
		this.stepByStep = stepByStep;
		this.optionPage = optionPage;
		this.genericDescription = genericDescription;
		
		setTitle(title);
		setDescription("This Wizard will help you decide if the occurrence of the "+apType+" antipattern characterizes an error.");		
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		lblModelElements = new Label(container, SWT.NONE);		
		lblModelElements.setText("The combination of the following elements characterize an occurence of the "+apType+" antipattern:");
		
		Label lblWouldYouLike = new Label(container, SWT.NONE);
		lblWouldYouLike.setText("Would you like to:");
		
		btnGoWizard = new Button(container, SWT.RADIO);
		btnGoWizard.setText("use the step-by-step wizard?");
		
		
		btnGoOptions = new Button(container, SWT.RADIO);
		btnGoOptions.setText("go directly to the refactoring options?");
		btnGoOptions.setSelection(true);
		
		combinationText = new StyledText(container, SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);
		combinationText.setText(elements);
		
		descriptionText = new StyledText(container, SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);
		descriptionText.setText(genericDescription);
		
		Label lblAntipatternDescripton = new Label(container, SWT.NONE);
		lblAntipatternDescripton.setText("Antipattern Descripton:");
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(11)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblModelElements, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(combinationText, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
					.add(11))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblAntipatternDescripton, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(descriptionText, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblWouldYouLike, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(btnGoWizard, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(btnGoOptions, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(13)
					.add(lblModelElements)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(combinationText, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblAntipatternDescripton)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(descriptionText, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblWouldYouLike)
					.add(8)
					.add(btnGoWizard)
					.add(8)
					.add(btnGoOptions)
					.add(11))
		);
		container.setLayout(gl_container);
	}	
	
	@Override
	public IWizardPage getNextPage() 
	{
		if(btnGoOptions.getSelection()) return optionPage;
		else if(btnGoWizard.getSelection()) return stepByStep;
		else return super.getNextPage();
	}
}
