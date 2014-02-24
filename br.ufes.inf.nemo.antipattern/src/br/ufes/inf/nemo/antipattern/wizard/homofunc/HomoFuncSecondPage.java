package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class HomoFuncSecondPage extends HomoFuncPage {

	public Button btnYes;
	public Button btnNo;
	private Button btnCreateNewPart;
	private Button btnDeletePart;
	private List partList;
	
	public HomoFuncSecondPage(HomoFuncOccurrence homoFunc) 
	{
		super(homoFunc);
	}
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		Label lblThroughYourPrevious = new Label(container, SWT.WRAP);
		lblThroughYourPrevious.setBounds(10, 10, 554, 52);
		lblThroughYourPrevious.setText("Through your previous answer, we established that "+homoFunc.getWhole().getName()+" is indeed a functional complex. That indicates that there " +
			"are other type parts which are not captured by the model. Would you like to define new parts, which are not instances of "+homoFunc.getPartEnd().getType().getName()+"?");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 74, 484, 16);
		btnYes.setText("Yes...");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 187, 64, 16);
		btnNo.setText("No.");
		
		partList = new List(container, SWT.BORDER | SWT.V_SCROLL);
		partList.setBounds(10, 96, 385, 68);
		
		btnCreateNewPart = new Button(container, SWT.NONE);
		btnCreateNewPart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Display display = Display.getDefault();	    	
				Shell shell = display.getActiveShell();			
				CreatePartDialog resultDIalog = new CreatePartDialog(shell);					
				resultDIalog.create();
				if(Window.OK==resultDIalog.open()){
					
				}
			}
		});		
		btnCreateNewPart.setText("Create new part");
		btnCreateNewPart.setBounds(401, 95, 106, 25);
		
		btnDeletePart = new Button(container, SWT.NONE);
		btnDeletePart.setText("Delete part");
		btnDeletePart.setBounds(401, 128, 106, 25);		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnYes.getSelection()){
			
		}
		if(btnNo.getSelection()){
			
		}
		return super.getNextPage();
	}
}

