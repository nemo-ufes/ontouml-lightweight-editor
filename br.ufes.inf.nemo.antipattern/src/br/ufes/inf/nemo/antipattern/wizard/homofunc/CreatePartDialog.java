package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class CreatePartDialog extends Dialog {

	protected Object result;
	protected Shell shlCreateNewPart;
	private Text partNameField;
	private Text componentOfNameField;
	private Combo stereoCombo;
	private Button btnIsShareable;
	private Button btnIsEssential;
	private Button btnIsInseparable;
	private Button btnIsImmutablePart;
	private Button btnIsImmutableWhole;
	private Composite container;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public CreatePartDialog(Shell parentShell) 
	{
		super(parentShell);		
	}
	
	@Override
	public void create() {
	    super.create();
	    setShellStyle(SWT.TITLE);
	    bringToFront(getShell());
	    getShell().setText("Create new part");	    
	}

	public void bringToFront(final Shell shell) {
	    shell.getDisplay().asyncExec(new Runnable() {
	        public void run() {
	            shell.forceActive();
	        }
	    });
	}
	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) 
	{
		container = (Composite) super.createDialogArea(parent);		
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		createPartControl();		
		
		return container;
	}
	
	public String getPartName()
	{
		return partNameField.getText();
	}
	
	public void createPartControl() 
	{		
		container.setLayout(null);
		Label label = new Label(container, SWT.NONE);
		label.setText("Part stereotype:");
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(10, 13, 122, 21);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("Part name:");
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(10, 40, 122, 21);
		
		Label lblComponentofName = new Label(container, SWT.NONE);
		lblComponentofName.setText("ComponentOf name:");
		lblComponentofName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblComponentofName.setBounds(10, 67, 122, 21);
		
		stereoCombo = new Combo(container, SWT.NONE);
		stereoCombo.setBounds(138, 10, 256, 23);
		
		partNameField = new Text(container, SWT.BORDER);
		partNameField.setBounds(138, 37, 256, 21);
		
		componentOfNameField = new Text(container, SWT.BORDER);
		componentOfNameField.setBounds(138, 64, 256, 21);
		
		btnIsShareable = new Button(container, SWT.CHECK);
		btnIsShareable.setText("isShareable");
		btnIsShareable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnIsShareable.setBounds(250, 144, 144, 26);
		
		btnIsEssential = new Button(container, SWT.CHECK);
		btnIsEssential.setText("isEssential");
		btnIsEssential.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnIsEssential.setBounds(138, 94, 108, 16);
		
		btnIsInseparable = new Button(container, SWT.CHECK);
		btnIsInseparable.setText("isInseparable");
		btnIsInseparable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnIsInseparable.setBounds(250, 94, 144, 16);
		
		btnIsImmutablePart = new Button(container, SWT.CHECK);
		btnIsImmutablePart.setText("isImmutablePart");
		btnIsImmutablePart.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnIsImmutablePart.setBounds(138, 122, 108, 16);
		
		btnIsImmutableWhole = new Button(container, SWT.CHECK);		
		btnIsImmutableWhole.setText("isImmutableWhole");
		btnIsImmutableWhole.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnIsImmutableWhole.setBounds(250, 122, 144, 16);

	}
	
	@Override
	protected boolean isResizable() {	
		return true;
	}
	
	public static void openDialog()
	{			
		Display display = Display.getDefault();	    	
		Shell shell = display.getActiveShell();			
		CreatePartDialog resultDIalog = new CreatePartDialog(shell);					
		resultDIalog.create();
		resultDIalog.open();		
	}
	
	@Override
	protected void okPressed() {	
		super.okPressed();
		
	}
	
	@Override
	protected void cancelPressed() {	
		super.cancelPressed();
	}
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) 
	{
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() 
	{
		return new Point(427, 269);
	}	
}
