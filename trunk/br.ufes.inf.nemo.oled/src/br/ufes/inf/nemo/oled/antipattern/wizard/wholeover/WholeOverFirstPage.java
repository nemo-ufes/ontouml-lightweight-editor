package br.ufes.inf.nemo.oled.antipattern.wizard.wholeover;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;
import br.ufes.inf.nemo.oled.antipattern.wizard.relspec.RelSpecWizard.RelSpecAction;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class WholeOverFirstPage extends WholeOverPage {

	public Button btnYes;
	public Button btnNo;

	public WholeOverFirstPage(WholeOverOccurrence wholeOver) 
	{
		super(wholeOver, "firstPage");
		setDescription("Whole: "+wholeOver.getParser().getStringRepresentation(wholeOver.getWhole()));
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		styledText.setText("Is it possible for an object to be an instance of <Part-1>, <Part-2>, … and <Part-n> at the same time?");

		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 16);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes");
		btnYes.setBounds(10, 32, 449, 16);
		btnYes.setSelection(true);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		btnNo.setBounds(10, 54, 449, 16);
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnYes.getSelection()) {
			getWizard().getPage("thirdPage");
		}
		if(btnNo.getSelection()) {
			getWizard().getPage("secondPage");
		}
		
		return super.getNextPage();
	}
}
