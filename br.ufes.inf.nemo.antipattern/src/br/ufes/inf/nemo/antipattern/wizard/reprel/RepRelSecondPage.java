package br.ufes.inf.nemo.antipattern.wizard.reprel;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public class RepRelSecondPage extends RepRelPage {
	
	public StyledText styledText;
	public Button unlimitedCopiesButton;
	public Button noCopiesButton;
	private Button limitedCopiesButton;
	
	public RepRelSecondPage(RepRelOccurrence rr) {
		super(rr);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
			    
		setPageComplete(false);
			
		styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setText(	"Your previous answer reassures that, in fact, the mediated types can simultaneously be connected to multiple instances of the relator." +
							"Now we focus on a single possible snapshot of the ontology." +
							"\r\n\r\n" +
							"Is it possible for two distinct instances of "+OntoUMLNameHelper.getTypeAndName(occurrence.getRelator(), true, true)+
							" to mediate the exact same instances of the mediated types at the very same time");
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setJustify(true);
		styledText.setAlwaysShowScrollBars(false);
		
		unlimitedCopiesButton = new Button(container, SWT.RADIO);
		unlimitedCopiesButton.setText("Yes, there is no limit to the number of \"copies\"");
		setAsEnablingNextPageButton(unlimitedCopiesButton);
		
		limitedCopiesButton = new Button(container, SWT.RADIO);
		limitedCopiesButton.setText("Yes, but there is a limit of repetitions");
		setAsEnablingNextPageButton(limitedCopiesButton);
		
		noCopiesButton = new Button(container, SWT.RADIO);
		noCopiesButton.setText("No, some combinations of mediated types are limited");
		setAsEnablingNextPageButton(noCopiesButton);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(unlimitedCopiesButton, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(limitedCopiesButton, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(noCopiesButton, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(unlimitedCopiesButton)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(limitedCopiesButton)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(noCopiesButton)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
	}

	@Override
	public IWizardPage getNextPage() 
	{		
		if(unlimitedCopiesButton.getSelection()) 
			return ((RepRelWizard)getWizard()).getFinishing();
		else 
			if(noCopiesButton.getSelection() || limitedCopiesButton.getSelection()) 
				return ((RepRelWizard)getWizard()).getThirdPage();	

		return super.getNextPage();
	}
}
