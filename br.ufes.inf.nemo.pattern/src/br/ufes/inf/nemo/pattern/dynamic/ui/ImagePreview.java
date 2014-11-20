package br.ufes.inf.nemo.pattern.dynamic.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class ImagePreview{
	
	private static Shell shell = null;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void open(Image img){
		Display display = Display.getCurrent();
		shell = new Shell(display, SWT.CLOSE & (~SWT.RESIZE));
		shell.setSize(269, 336);
		shell.setText("Image Preview");
		shell.setLayout(new GridLayout(1, false));
		shell.setImage(SWTResourceManager.getImage(DynamicWindow.class,"/resources/icons/x16/chart_organisation.png"));

		shell.addListener(SWT.Traverse, new Listener(){
			@Override
			public void handleEvent(Event event) {
				switch (event.detail) {
				case SWT.TRAVERSE_ESCAPE:
					shell.close();
					event.detail = SWT.TRAVERSE_NONE;
					event.doit = false;
					break;
				}
			}
		});
		
		Label label = new Label(shell, SWT.BORDER);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1));
		label.setAlignment(SWT.CENTER);

		label.setImage(img);
		label.pack ();
		
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		btnNewButton.setText("Close");
		shell.pack ();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) 
				display.sleep (); 
		} 
	}
}