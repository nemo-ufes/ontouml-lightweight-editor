package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import java.io.Serializable;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class NewPhase extends WizardPageAssistant  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Table table;

	/**
	 * Create the wizard.
	 */
	public NewPhase() {
		super("Create new phase");
		setTitle("Creating new Phases and its rules");
	}

	private int contPhases = 0;
	private int currentItemSelection;
	private Button btDeletePhase;
	private Label className;

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(null);

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(2, 5, 96, 15);
		lblNewLabel.setText("Creating Phases  ");

		Button btAddNewRow = new Button(container, SWT.NONE);
		btAddNewRow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TableItem item = new TableItem (table, SWT.NONE);
				item.setText (0,"name phase");
				item.setText (1,"existential rule");

				contPhases++;
				enableFinish(false);
			}
		});
		btAddNewRow.setBounds(440, 256, 104, 25);
		btAddNewRow.setText("Add new row");

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLocation(0, 35);
		table.setSize(574, 215);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		table.setLayoutData(data);

		TableColumn column = new TableColumn (table, SWT.NONE);
		column.setText ("Phase Name");
		column.setWidth(574/2);
		column = new TableColumn (table, SWT.NONE);
		column.setText ("Rules");
		column.setWidth((574/2)-5);

		TableItem item = new TableItem (table, SWT.NONE);
		item.setText (0,"name phase");
		item.setText (1,"existential rule");

		/* Select Row Listener */
		final TableEditor editor = new TableEditor (table);

		className = new Label(container, SWT.NONE);
		className.setBounds(408, 5, 46, 15);
		className.setText("<currentClass>");
		className.setVisible(false);

		btDeletePhase = new Button(container, SWT.NONE);
		btDeletePhase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				contPhases--;
				enableFinish(false);
				table.remove(currentItemSelection);
				btDeletePhase.setEnabled(false);
			}
		});
		btDeletePhase.setBounds(342, 256, 91, 25);
		btDeletePhase.setText("Delete Phase");
		btDeletePhase.setEnabled(false);

		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;

		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				currentItemSelection = table.getSelectionIndex();
				btDeletePhase.setEnabled(true);
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
	public boolean next() {
		return true;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if(visible){
			className.setText(getCurrentClass());
			enableFinish(false);
		}
	}

	@Override
	public boolean canFlipToNextPage() {
		if(isEndPage)
			return false;

		//At least two Phases needs to be created
		if(contPhases >= 1){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String s;
		s = "Page: "+getName()+"{";
		s += "\nclass: "+getClassName();
		ArrayList<String[]> list = getPhases();
		for(String[] row : list){
			s += "\nPhase: "+row[0]+" - Rule: "+row[1];
		}
		s += "\n}";
		return s;
	}

	/* get operations */

	public String getClassName(){
		return className.getText();
	}

	public ArrayList<String[]> getPhases(){
		ArrayList<String[]> list = new ArrayList<>();
		String[] row;
		for(TableItem ti : table.getItems()){
			row = new String[table.getColumnCount()];
			for (int i = 0; i < table.getColumnCount(); i++) {
				row[i] = ti.getText(i);
			}
			list.add(row);
		}
		setClassList(list);
		return list;
	}

	@Override
	public void init() {
		setTitle("Creating new Phases and its rules");
		contPhases = 0;
	}
}
