package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public class RepRelSecondPage extends RepRelPage {

	//GUI
	public ScrolledComposite sc ;
	public Composite composite;
	
	public RepRelSecondPage(RepRelOccurrence rr) {
		super(rr);
	}

	public void setRigids(ArrayList<Type> rigids)
	{		
		for(Type type: rigids)
		{			
//			Spinner spinner = new Spinner(composite, SWT.BORDER);
//			spinner.setBounds(51, 62, 47, 22);
//			
//			StyledText styledText = new StyledText(composite, SWT.WRAP);
//			styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
//			styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
//			styledText.setText("A maximum of N (N must be lower than <a> and <b>, otherwise the model is already correct) instances of "+repRel.getRelator().getName()+
//				"can mediate the very same instances of "+type.getName()+" at the same time");
//			styledText.setBounds(10, 10, 430, 46);
		}
		
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));	
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		setPageComplete(true);
		
		setControl(container);	
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 554, 15);
		lblNewLabel.setText("Does "+repRel.getRelator().getName()+" is intended with current or historical semantic?");
		
		Button btnCurrent = new Button(container, SWT.RADIO);
		btnCurrent.setBounds(10, 31, 90, 16);
		btnCurrent.setText("Current");
		
		Button btnHistorical = new Button(container, SWT.RADIO);
		btnHistorical.setBounds(106, 31, 90, 16);
		btnHistorical.setText("Historical");
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setBounds(10, 64, 554, 15);
		lblNewLabel_1.setText("Which types may be combined a limited number of times? How many times?");
		
		sc = new ScrolledComposite(container, SWT.V_SCROLL | SWT.BORDER);
		sc.setBounds(10, 138, 554, 134);
		
		composite = new Composite(sc, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
	}

	@Override
	public IWizardPage getNextPage() 
	{		
		return super.getNextPage();
	}
}
