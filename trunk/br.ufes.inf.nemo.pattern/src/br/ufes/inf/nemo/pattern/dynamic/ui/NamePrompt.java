package br.ufes.inf.nemo.pattern.dynamic.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.pattern.util.UtilPattern;

public class NamePrompt extends Dialog {

	private static Shell shell;

	private static NamePrompt init(){
		Display display = Display.getDefault();	    	
		shell = display.getActiveShell();	
		if(shell == null){
			shell = new Shell(display);
		}

		UtilPattern.centralizeShell(display, shell);

		NamePrompt window = new NamePrompt(shell);

		return window;
	}

	protected NamePrompt(Shell parentShell) {
		super(parentShell);
		setDefaultImage(SWTResourceManager.getImage(DynamicWindow.class,"/resources/icons/x16/sitemap.png"));
	}


	@Override
	public void create() {
		super.create();
		setShellStyle(SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		UtilPattern.bringToFront(shell);
		getShell().setText("Set Class Name");	    
	}

	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		return null;
	}
	
	public static NamePrompt createDialog()	{			
		NamePrompt window = init();
		window.create();

		return window;
	}

	private String className = new String();
	public String getClassName() {
		return className;
	}
	
	private Composite container;
	
	@Override
	protected Control createDialogArea(Composite parent){
		container = (Composite) super.createDialogArea(parent);
		
		container.setLayout(new GridLayout(2, true));

		Label label = new Label(container, SWT.NULL);
		label.setText("Please enter the class Name:");

		final Text text = new Text(container, SWT.SINGLE | SWT.BORDER);

		final Button buttonOK = new Button(container, SWT.PUSH);
		buttonOK.setText("Ok");
		buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		text.addListener(SWT.Modify, new Listener() {
			public void handleEvent(Event event) {
				try {
					className = text.getText();
					buttonOK.setEnabled(true);
				} catch (Exception e) {
					buttonOK.setEnabled(false);
				}
			}
		});

		buttonOK.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				shell.dispose();
			}
		});

		shell.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event event) {
				if(event.detail == SWT.TRAVERSE_ESCAPE)
					event.doit = false;
			}
		});

		text.setText("");
		container.pack();
		
		return container;
	}

}
