package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;


public class NewRelator extends WizardPageAssistant {

	/**
	 * Create the wizard.
	 */
	public NewRelator() {
		super("New Relator");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
	}

	private Table table;
	private Text relatorName;
	private int currentItemSelection;
	private Button btDeleteMediation;

	private int contMediation = 0;
	private Combo cb;

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);

		container.setLayout(null);

		setPageComplete(false);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLocation(-1, 57);
		table.setSize(574, 177);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		table.setLayoutData(data);

		TableColumn column = new TableColumn (table, SWT.NONE);
		column.setText ("Relator");
		column.setWidth(100);
		column = new TableColumn (table, SWT.NONE);
		column.setText ("Cardinality");
		column.setWidth(70);
		column = new TableColumn (table, SWT.NONE);
		column.setText ("Mediation");
		column.setWidth(157);
		column = new TableColumn (table, SWT.NONE);
		column.setText ("Cardinality");
		column.setWidth(70);
		column = new TableColumn (table, SWT.NONE);
		column.setText ("Class Destiny");
		column.setWidth(169);

		/* Select Row Listener */
		final TableEditor editor = new TableEditor (table);

		Button btNewMediation = new Button(container, SWT.NONE);
		btNewMediation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent elem) {
				if(relatorName.getText().isEmpty()){
					MessageDialog.openError(getShell(), "Error", "Give a name to Relator");
					return;
				}
				TableItem item = new TableItem (table, SWT.NONE);
				item.setText (0,relatorName.getText());
				item.setText (1,"0..*");
				item.setText (2,"Mediation");
				item.setText (3,"0..*");
				item.setText (4,"Click to set class");
				contMediation++;
				validate();
			}
		});
		btNewMediation.setBounds(441, 247, 123, 25);
		btNewMediation.setText("Add new Mediation");

		Label lblNameToRelator = new Label(container, SWT.NONE);
		lblNameToRelator.setBounds(10, 25, 93, 15);
		lblNameToRelator.setText("Name to Relator");

		relatorName = new Text(container, SWT.BORDER);
		relatorName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				for (int i = 0; i < table.getItemCount(); i++) {
					table.getItem(i).setText(0,relatorName.getText());
				}
			}
		});
		relatorName.setBounds(103, 23, 139, 21);

		Button btnAddNewClass = new Button(container, SWT.NONE);
		btnAddNewClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//TODO criar uma classe nova
			}
		});
		btnAddNewClass.setBounds(340, 247, 95, 25);
		btnAddNewClass.setText("Add new Class");
		btnAddNewClass.setEnabled(false);

		btDeleteMediation = new Button(container, SWT.NONE);
		btDeleteMediation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				table.remove(currentItemSelection);
				btDeleteMediation.setEnabled(false);
				contMediation--;
				validate();
			}
		});
		btDeleteMediation.setBounds(10, 247, 109, 25);
		btDeleteMediation.setText("Delete Mediation");
		btDeleteMediation.setEnabled(false);

		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;

		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				currentItemSelection = table.getSelectionIndex();
				btDeleteMediation.setEnabled(true);
			}
		});

		table.addListener (SWT.MouseDown, new Listener () {
			@Override
			public void handleEvent (Event event) {
				Rectangle clientArea = table.getClientArea ();
				Point pt = new Point (event.x, event.y);
				int index = table.getTopIndex ();
				while (index < table.getItemCount ()) {
					boolean visible = false;
					final TableItem item = table.getItem (index);
					for (int i=0; i<table.getColumnCount (); i++) {
						Rectangle rect = item.getBounds (i);
						if (rect.contains (pt)) {
							final int column = i;
							if(i==0){//relator name Column
								return;
							}

							if(i==4){//Class Column
								cb = new Combo(table, SWT.READ_ONLY);
								Listener textListener = new Listener () {
									@Override
									public void handleEvent (final Event e) {
										switch (e.type) {
										case SWT.FocusOut:
											item.setText(column, cb.getItem(cb.getSelectionIndex()));
											cb.dispose ();
											break;
										case SWT.Traverse:
											switch (e.detail) {
											case SWT.TRAVERSE_RETURN:
												item.setText (column, cb.getItem(cb.getSelectionIndex()));	
												//FALL THROUGH
											case SWT.TRAVERSE_ESCAPE:
												cb.dispose ();
												e.doit = false;
											}
											break;
										}
										validate();
									}
								};
								cb.addListener (SWT.FocusOut, textListener);
								cb.addListener (SWT.Traverse, textListener);
								cb.setItems(possibleClasses);
								cb.select(0);
								cb.computeSize(SWT.DEFAULT, table.getItemHeight());
								cb.setFocus ();
								editor.setEditor (cb, item, i);
								return;
							}else{
								final Text text = new Text (table, SWT.NONE);
								Listener textListener = new Listener () {
									@Override
									public void handleEvent (final Event e) {
										switch (e.type) {
										case SWT.FocusOut:
											item.setText (column, text.getText ());	
											text.dispose ();
											break;
										case SWT.Traverse:
											switch (e.detail) {
											case SWT.TRAVERSE_RETURN:
												item.setText (column, text.getText ());	
												//FALL THROUGH
											case SWT.TRAVERSE_ESCAPE:
												text.dispose ();
												e.doit = false;
											}
											break;
										}
										validate();
									}
								};
								text.addListener (SWT.FocusOut, textListener);
								text.addListener (SWT.Traverse, textListener);
								editor.setEditor (text, item, i);
								text.setText (item.getText (i));
								text.selectAll ();
								text.setFocus ();
								return;
							}
						}
						if (!visible && rect.intersects (clientArea)) {
							visible = true;
						}
					}
					if (!visible) return;
					index++;
				}
			}
		});

	}

	@Override
	public boolean canFlipToNextPage() {
		if(validate()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean next() {
		return true;
	}

	private String[] possibleClasses = {"a","b","c"};

	public void setPossbileClasses(String[] classes){
		possibleClasses = classes;
	}
	
	
	private boolean validate(){
		boolean isValid = false;
		for(TableItem ti : table.getItems()){
			//Validate cardinalities
			Pattern p = Pattern.compile("[0-9]+..[0-9]+|[0-9]+..\\*|[0-9]+");     
			Matcher card1 = p.matcher(ti.getText(1));
			Matcher card2 = p.matcher(ti.getText(3));
			if(card1.matches() && card2.matches()){
				isValid = true;
			}else{
				isValid = false;
				MessageDialog.openError(getShell(), "Error", "The mediation "+ti.getText(2)+" has some wrong cardinality.");
				break;
			}
			
			//Validate class destiny
			if(ti.getText(4).equals("Click to set class")){
				isValid = false;
				break;
			}
		}

		if(!isValid){
			if(isPageComplete())
				setPageComplete(false);
			return false;
		}
		
		//Validate quantity of mediations
		if(contMediation > 0){
			isValid = true;
		}else{
			isValid = false;
		}
		
		if(isValid){
			if(!isPageComplete())
				setPageComplete(true);
		}else{
			if(isPageComplete())
				setPageComplete(false);
		}
		
		return isValid;
	}
}