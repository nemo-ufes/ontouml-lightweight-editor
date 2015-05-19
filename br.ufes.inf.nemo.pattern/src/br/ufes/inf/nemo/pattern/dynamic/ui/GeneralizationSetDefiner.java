package br.ufes.inf.nemo.pattern.dynamic.ui;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import br.ufes.inf.nemo.pattern.util.UtilPattern;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class GeneralizationSetDefiner extends Dialog{
	private Composite container;
	private Text txGS;
	
	public GeneralizationSetDefiner(Shell parentShell) {
		super(parentShell);		
	}

	public static GeneralizationSetDefiner createDialog(){			
		Display display = Display.getDefault();	    	
		Shell shell = display.getActiveShell();	
		if(shell == null){
			shell = new Shell(display);
		}

		GeneralizationSetDefiner resultDIalog = new GeneralizationSetDefiner(shell);

		UtilPattern.centralizeShell(display, shell);
		resultDIalog.create();

		return resultDIalog;
	}
	
	@Override
	protected Button createButton(org.eclipse.swt.widgets.Composite parent,	int id, String label, boolean defaultButton) {
		if (id == IDialogConstants.CANCEL_ID) return null;
		return super.createButton(parent, id, label, defaultButton);
	}
	@Override
	public void create() {
		super.create();	    	  
		setShellStyle(SWT.TITLE);
		bringToFront(getShell());
		getShell().setText("Generalization Set naming");	      
	}

	public void bringToFront(final Shell shell) {
		shell.getDisplay().asyncExec(new Runnable() {
			public void run() {
				shell.forceActive();
			}
		});
	}
	
	@Override
	protected boolean canHandleShellCloseEvent() {
		return false;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent){
		container = (Composite) super.createDialogArea(parent);	
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(container, SWT.BORDER);
		
		Label lblGiveAName = new Label(composite, SWT.NONE);
		lblGiveAName.setBounds(10, 10, 407, 14);
		lblGiveAName.setText("Give a name for this new Generalization Set");
		
		txGS = new Text(composite, SWT.BORDER);
		txGS.setBounds(10, 30, 147, 19);
		
		return container;
	}
}
