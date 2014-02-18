package br.ufes.inf.nemo.antipattern.wizard.binover;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;

public class DisjointnessPage extends BinOverPage {

	Button btnYes, btnNo;
	
	public DisjointnessPage(BinOverOccurrence binOver) {
		super("DisjointnessPage", binOver);
		
		setTitle(BinOverAntipattern.getAntipatternInfo().getName()+" - Disjointness");
		
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		setDescription("Binary Relation: "+getRelationName()+
				   "\nCurrent Stereotype: "+getBinOverWizard().getCurrentStereotypeName(this));	
		
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(null);
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setBounds(10, 10, 554, 115);
		styledText.setText(	"Before we analyze any binary property, we have to be sure that the ends of relation <"+getRelationName()+"> " +
							"are indeed overlapping. To achieve that, answer the following question:" +
							"\r\n\r\n" +
							"Is it possible for the same individual to act as <"+getSourceEndName()+"> " +
							"and <"+getTargetEndName()+"> at the same time? " +
							"Notice that the question is not only if the individual can instantiate the association ends' types, but act as the roles defined by them.");
		styledText.setJustify(true);
		styledText.setBackground(container.getBackground());
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 131, 93, 16);
		btnYes.setText("Yes (Overlapping Ends)");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 152, 93, 16);
		btnNo.setText("No (Disjoint Ends)");
		
		setPageComplete(false);
		
		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				if((btnYes.getSelection() || btnNo.getSelection() ) && !isPageComplete())
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		};
		
		btnYes.addSelectionListener(listener);
		btnNo.addSelectionListener(listener);
	}
	
	@Override
	public IWizardPage getNextPage(){
		
		getBinOverWizard().removeAllActions(0);
		
		if(btnNo.getSelection()){
			BinOverAction action = new BinOverAction(binOver);
			action.setDisjointness();
			getBinOverWizard().addAction(0, action);
			return getBinOverWizard().getFinishing();
		}
		
		return getBinOverWizard().getReflexivityPage();
		
	}

}
