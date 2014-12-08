package br.ufes.inf.nemo.pattern.dynamic.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.pattern.util.UtilPattern;

public class ImagePreview extends Dialog{
	
	protected ImagePreview(Shell parentShell) {
		super(parentShell);
		setDefaultImage(SWTResourceManager.getImage(DynamicWindow.class,"/resources/icons/x16/chart_organisation.png"));
	}

	private static Image currentImage;
	private static Shell shell;
	public static ImagePreview createDialog(Image image)
	{			
		Display display = Display.getDefault();	    	
		shell = display.getActiveShell();	
		if(shell == null){
			shell = new Shell(display);
		}
		currentImage = image;
		UtilPattern.centralizeShell(display, shell);
		ImagePreview resultDIalog = new ImagePreview(shell);
		resultDIalog.create();
		return resultDIalog;
	}
	
	@Override
	public void create() {
	    super.create();
	    setShellStyle(SWT.TITLE);
	    UtilPattern.bringToFront(shell);
	    getShell().setText("Image Preview");	    
	}
	
	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
	    return null;
	}
	
	
	private Composite container;
	@Override
	protected Control createDialogArea(Composite parent){
		container = (Composite) super.createDialogArea(parent);
		
		Label label = new Label(container, SWT.BORDER);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1));
		label.setAlignment(SWT.CENTER);

		label.setImage(currentImage);
		label.pack ();
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				close();
			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		btnNewButton.setText("Close");
	
		return container;
	}
}