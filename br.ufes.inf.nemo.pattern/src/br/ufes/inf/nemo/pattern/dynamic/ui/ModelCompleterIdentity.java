package br.ufes.inf.nemo.pattern.dynamic.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.PrincipleOfIdentity;
import br.ufes.inf.nemo.pattern.ui.manager.ModelCompleterManager;

/*
 * TODO LIST
 * 	- Implement reuse of classes used in the current table
 * */


/**
 * @author Victor Amorim
 */
public class ModelCompleterIdentity {
	private Shell shell;
	public Shell getShell() {
		return shell;
	}
	
	/**
	 *  @wbp.parser.entryPoint
	 * */
	public ModelCompleterIdentity() {
		init();
	}
	
	private double x;
	private double y;
	public ModelCompleterIdentity(OntoUMLParser parser, double x, double y){
		init();
		ModelCompleterManager.getCompleterIdentityWindow(parser, this);
		this.x = x;
		this.y = y;
	}
	
	private void init(){
		display = Display.getCurrent();
		createContents();
		addTableHeaders();
	}
	
	public void bringToFront(final Shell shell) {
		shell.getDisplay().asyncExec(new Runnable() {
			public void run() {
				shell.forceActive();
			}
		});
	}

	/**
	 * Open the window.
	 */
	Display display;
	public void open() {
		bringToFront(shell);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private Table table;
	public Table getTable() {
		return table;
	}

	private Label lblSelectTheSpecific;
	/**
	 * Create contents of the window.
	 *
	 */
	private void createContents() {
		shell = new Shell(SWT.CLOSE & (~SWT.RESIZE) );
		shell.setSize(400, 336);
		shell.setText("Model Identity Completer");
		shell.setImage(SWTResourceManager.getImage(ModelCompleterIdentity.class,"/resources/icons/x16/sitemap.png"));
		shell.setLayout(new FormLayout());
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

		this.table = new Table(shell, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.left = new FormAttachment(0, 9);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		Label lblSomeClassesOf = new Label(shell, SWT.NONE);
		fd_table.right = new FormAttachment(lblSomeClassesOf, 35, SWT.RIGHT);
		FormData fd_lblSomeClassesOf = new FormData();
		fd_lblSomeClassesOf.left = new FormAttachment(0, 10);
		fd_lblSomeClassesOf.top = new FormAttachment(0, 10);
		lblSomeClassesOf.setLayoutData(fd_lblSomeClassesOf);
		lblSomeClassesOf.setText("Some classes of the model are missing their principle of identity.");

		lblSelectTheSpecific = new Label(shell, SWT.NONE);
		fd_table.top = new FormAttachment(lblSelectTheSpecific, 5);
		FormData fd_lblSelectTheSpecific = new FormData();
		fd_lblSelectTheSpecific.top = new FormAttachment(lblSomeClassesOf, 1);
		fd_lblSelectTheSpecific.left = new FormAttachment(0, 11);
		lblSelectTheSpecific.setLayoutData(fd_lblSelectTheSpecific);
		lblSelectTheSpecific.setText("Select the specific class above and apply some pattern to them.");

		Button btnClose = new Button(shell, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				shell.dispose();
			}
		});
		fd_table.bottom = new FormAttachment(100, -42);
		FormData fd_btnClose = new FormData();
		fd_btnClose.top = new FormAttachment(table, 7);
		fd_btnClose.right = new FormAttachment(100, -138);
		fd_btnClose.left = new FormAttachment(0, 139);
		btnClose.setLayoutData(fd_btnClose);
		btnClose.setText("Close");
	}

	private void addTableHeaders() {
		TableColumn column = new TableColumn(table, SWT.CENTER);
		column.setWidth(150);
		column.setText("Class Name");

		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(122);
		column.setText("Stereotype");

		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(99);
		column.setText("Action");
	}
	
	private Fix fix = null;
	public Fix getFix(){
		return fix;
	}
	
	public void addLine(final OntoUMLParser parser, final Classifier c){
		TableItem tableItem = new TableItem(table, SWT.NONE);

		//Name
		tableItem.setText(0, UtilAssistant.getStringRepresentationClass(c));
		
		//Stereotype
		tableItem.setText(1, UtilAssistant.getStringRepresentationStereotype(c));

		//Button
		TableEditor editor = new TableEditor (table);
		Button btn = new Button (table, SWT.NONE);
		btn.setText("Add Identity");
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				PrincipleOfIdentity win = new PrincipleOfIdentity(parser, c,x,y);
				win.runPattern();
				fix = win.getFix();
				shell.dispose();
			}
		});
		btn.pack ();
		editor.minimumWidth = btn.getSize().x;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor (btn, tableItem, 2);
	}
}