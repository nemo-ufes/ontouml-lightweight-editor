package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;


public class NewRelator extends WizardPageAssistant {

	/**
	 * Create the wizard.
	 */
	public NewRelator() {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
	}

	private Table table;
	private Text relatorName;
	private int currentItemSelection;
	private Button btDeleteMediation;
	
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);

		container.setLayout(null);

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
			public void widgetSelected(SelectionEvent arg0) {
				//TODO Aparecer uma janela para selecionar alguma classe do modelo
				TableItem item = new TableItem (table, SWT.NONE);
				item.setText (0,relatorName.getText());
				item.setText (1,"0..*");
				item.setText (2,"Mediation");
				item.setText (3,"0..*");
				item.setText (4,"Classe");
//				item.addListener(SWT.SELECTED, new Listener() {
//					public void handleEvent(Event event) {
//						currentItemSelection = table.getSelectionIndex();
//						btDeleteMediation.setEnabled(true);
//					}
//				});
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

		btDeleteMediation = new Button(container, SWT.NONE);
		btDeleteMediation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				table.remove(currentItemSelection);
				btDeleteMediation.setEnabled(false);
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
		// TODO Auto-generated method stub
		return false;
	}
}
