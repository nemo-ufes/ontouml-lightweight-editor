package br.ufes.inf.nemo.pattern.dynamic.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
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
import br.ufes.inf.nemo.pattern.util.UtilPattern;


/**
 * @author Victor Amorim
 */
public class ModelCompleterIdentity extends Dialog{
	protected ModelCompleterIdentity(Shell parentShell, OntoUMLParser parser, double x, double y) {
		super(parentShell);
		setDefaultImage(SWTResourceManager.getImage(DynamicWindow.class,"/resources/icons/x16/sitemap.png"));
		this.x = x;
		this.y = y;
	}

	private double x;
	private double y;

	private static Shell shell;
	public static ModelCompleterIdentity createDialog(OntoUMLParser parser, double x, double y)
	{			
		Display display = Display.getDefault();	    	
		shell = display.getActiveShell();	
		if(shell == null){
			shell = new Shell(display);
		}
		UtilPattern.centralizeShell(display, shell);
		ModelCompleterIdentity resultDIalog = new ModelCompleterIdentity(shell,parser,x,y);
		resultDIalog.create();
		ModelCompleterManager.getCompleterIdentityWindow(parser, resultDIalog);
		return resultDIalog;
	}

	@Override
	public void create() {
		super.create();
		setShellStyle(SWT.TITLE);
		UtilPattern.bringToFront(shell);
		getShell().setText("Model Identity Completer");	    
	}

	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		return null;
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
	private Composite container;
	@Override
	protected Control createDialogArea(Composite parent){
		container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);
		
		this.table = new Table(container, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		table.setBounds(11, 11, 388, 34);
		table.setBounds(9, 44, 381, 228);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		addTableHeaders();
		
		Label lblSomeClassesOf = new Label(container, SWT.NONE);
		lblSomeClassesOf.setBounds(10, 10, 380, 14);
		lblSomeClassesOf.setBounds(10, 10, 345, 14);
		lblSomeClassesOf.setText("Some classes of the model are missing their principle of identity.    ");

		lblSelectTheSpecific = new Label(container, SWT.NONE);
		lblSelectTheSpecific.setBounds(11, 25, 366, 14);
		lblSelectTheSpecific.setBounds(11, 25, 344, 14);
		lblSelectTheSpecific.setText("Select the specific class above and apply some pattern to them.    ");

		Button btnClose = new Button(container, SWT.NONE);
		btnClose.setBounds(11, 94, 63, 28);
		btnClose.setBounds(139, 279, 123, 28);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				close();
			}
		});
		btnClose.setText("Close");

		return container;
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
				close();
			}
		});
		btn.pack ();
		editor.minimumWidth = btn.getSize().x;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor (btn, tableItem, 2);
	}
}