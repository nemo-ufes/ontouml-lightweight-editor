package br.ufes.inf.nemo.pattern.dynamic.ui;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;
import br.ufes.inf.nemo.pattern.util.UtilPattern;


/**
 * @author Victor Amorim
 */
public class ModelCompleter extends Dialog{
	protected ModelCompleter(Shell parentShell) {
		super(parentShell);
		setDefaultImage(SWTResourceManager.getImage(DynamicWindow.class,"/resources/icons/x16/sitemap.png"));
	}

	private ArrayList<Button> buttons = new ArrayList<>();

	private static Shell shell;
	
	private static ModelCompleter init(){
		Display display = Display.getDefault();	    	
		shell = display.getActiveShell();	
		if(shell == null){
			shell = new Shell(display);
		}
		
		UtilPattern.centralizeShell(display, shell);
		
		ModelCompleter window = new ModelCompleter(shell);
		
		return window;
	}
	

	@Override
	protected boolean canHandleShellCloseEvent() {
		return false;
	}
	
	public static ModelCompleter createDialog(boolean bool)	{			
		ModelCompleter window = init();
		window.showCompleteMessage(bool);
		window.create();
		
		return window;
	}
	
	public static ModelCompleter createDialog()	{			
		ModelCompleter window = init();
		window.create();
		
		return window;
	}

	@Override
	public int open() {
		//to show a friendly message
		if(showCompleteMessage && table.getItemCount() == 0){
			return showMessageBox();
		}
		return super.open();
	}
	
	public int showMessageBox(){
		MessageBox dialog = new MessageBox(getShell(), SWT.ICON_INFORMATION | SWT.OK );
		dialog.setText("Model Completer");
		dialog.setMessage("There is nothing to do, your model appear to be complete.");
		return dialog.open(); 
	}
	
	public void addTableListener(){
		TableListener paintListener = new TableListener(buttons);

		table.addListener(SWT.MeasureItem, paintListener);
		table.addListener(SWT.PaintItem, paintListener);
		table.addListener(SWT.EraseItem, paintListener);
	}

	@Override
	public void create() {
		super.create();
		setShellStyle(SWT.TITLE);
		UtilPattern.bringToFront(shell);
		getShell().setText("Model Completer");	    
	}

	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		return null;
	}

	private Table table;
	public Table getTable() {
		return table;
	}

	/**
	 * 
	 * Create contents of the window.
	 *
	 */
	private Label label;
	private Composite container;
	@Override
	protected Control createDialogArea(Composite parent){
		container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);

		this.table = new Table(container, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		table.setBounds(9, 44, 789, 228);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		addTableHeaders();

		label = new Label(container, SWT.NONE);
		label.setBounds(10, 10, 420, 14);
		label.setText("The model appears to be incomplete. The actions below must be applied.");

		Button btnClose = new Button(container, SWT.NONE);
		btnClose.setBounds(298, 278, 123, 28);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				wasClosed = true;
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
		column.setWidth(330);
		column.setText("Action");

		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(181);
		column.setText("Structural Patterns");
	}

	private Fix fix = null;

	private boolean showCompleteMessage = true;
	public Fix getFix(){
		return fix;
	}

	private ArrayList<String> performedActions = new ArrayList<>();
	public ArrayList<String> getPerformedActions() {
		return performedActions;
	}
	public void addLine(String name, String stereotype, String action, final AbstractPattern fop){
		TableItem tableItem = new TableItem(table, SWT.NONE);

		//Name
		tableItem.setText(0, name);

		//Stereotype
		tableItem.setText(1, stereotype);

		//Stereotype
		tableItem.setText(2, action);

		//Button
		Button btn = new Button (table, SWT.NONE);
		btn.setText(fop.getTitle());
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				close();
				fop.runPattern();
				fix = fop.getFix();
				if(fix != null)
					performedActions.addAll(fop.getActionsPerformed());
			}
		});
		btn.pack ();

		TableEditor editor = new TableEditor (table);
		editor.minimumWidth = btn.getSize().x;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor (btn, tableItem, 3);
	}
	
	//Solution found: http://www.java2s.com/Tutorial/Java/0280__SWT/MultilineTablecell.htm
	class TableListener implements Listener{
		private ArrayList<Button> buttons;
		public TableListener(ArrayList<Button> buttons) {
			this.buttons = buttons;
		}

		@Override
		public void handleEvent(Event event) {
			for(Button button: buttons){
				button.pack();
			}

			switch (event.type) {
			case SWT.MeasureItem: {
				TableItem item = (TableItem) event.item;
				String text = item.getText(event.index);
				Point size = event.gc.textExtent(text);
				event.width = size.x;
				event.height = Math.max(event.height, size.y);
				break;
			}
			case SWT.PaintItem: {
				TableItem item = (TableItem) event.item;
				String text = item.getText(event.index);
				Point size = event.gc.textExtent(text);
				int offset2 = event.index == 0 ? Math.max(0, (event.height - size.y) / 2) : 0;
				event.gc.drawText(text, event.x, event.y + offset2, true);
				break;
			}
			case SWT.EraseItem: {
				event.detail &= ~SWT.FOREGROUND;
				break;
			}
			}	
		}

	}

	public void showCompleteMessage(boolean showCompleteMessage) {
		this.showCompleteMessage = showCompleteMessage;
	}

	public void redraw() {
		table.removeAll();
		table.setRedraw(true);
	}
	
	public boolean isEmpty(){
		return table.getItemCount() == 0;
	}
	
	private boolean wasClosed = false;

	public boolean wasClosed() {
		return wasClosed;
	}
}