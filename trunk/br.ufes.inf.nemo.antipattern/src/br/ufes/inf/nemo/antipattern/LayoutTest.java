package br.ufes.inf.nemo.antipattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class LayoutTest extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public LayoutTest(Shell parent) {
		super(parent);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.BORDER | SWT.TITLE | SWT.PRIMARY_MODAL);
		shell.setSize(450, 300);
		shell.setText(getText());

	}

	public static void main(String[] args){
		Display display = Display.getDefault();	    	
		Shell shell = new Shell(display);
		LayoutTest dialog = new LayoutTest(shell);
		dialog.open();
		
		System.out.println("RODEI");
	}
}
