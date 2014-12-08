package br.ufes.inf.nemo.pattern.ui.manager;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.dynamic.ui.DynamicWindowForDomainPattern;
import br.ufes.inf.nemo.pattern.dynamic.ui.ReuseCheckListener;

public class DynamicManagerWindowForDomainPattern {

	private DynamicWindowForDomainPattern window; 
	private HashMap<String, String[]> hashTree;

	public DynamicManagerWindowForDomainPattern(DynamicWindowForDomainPattern dynwin) {
		window = dynwin;
		addTableHeaders();
	}

	public void addHashTree(HashMap<String, String[]> hash){
		hashTree = hash;
	}

	public TableItem addTableLine(String dataValue, String className, String[] stereotypes){
		Table table = window.getTable();

		TableItem tableItem = new TableItem(table, SWT.NONE);
		ArrayList<TableEditor> editors = new ArrayList<>();

		//add dataValue to dataValue to future using
		window.addDataField(dataValue);

		//Text
		TableEditor editor = new TableEditor (table);
		Text text = new Text (table, SWT.NONE);
		text.setText(className);
		editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(text, tableItem, 0);
		tableItem.setData("text_"+dataValue, text);
		editors.add(editor);

		//Combo
		CCombo combo = new CCombo(table, SWT.NONE);
		combo.setItems(stereotypes);
		combo.select(0);
		combo.setEditable(false);
		combo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
		editor = new TableEditor (table);
		editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(combo, tableItem, 1);
		tableItem.setData("stereotype_"+dataValue, combo);
		editors.add(editor);

		//Check
		editor = new TableEditor (table);
		Button btnReuse = new Button (table, SWT.CHECK);
		btnReuse.pack ();
		editor.minimumWidth = btnReuse.getSize ().x;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor (btnReuse, tableItem, 2);
		tableItem.setData("reuse_"+dataValue, btnReuse);
		editors.add(editor);


		window.addTableEditor(tableItem, editors);
		window.addUsedStereotypes(stereotypes);

		btnReuse.addSelectionListener(new ReuseCheckListener(hashTree, null, text, combo));

		return tableItem;
	}

	private void addTableHeaders() {
		Table table = window.getTable();

		//Defining columns
		TableColumn	column = new TableColumn(table, SWT.CENTER);
		column.setWidth(220);
		column.setText("Class Name");

		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(110);
		column.setText("Stereotype");

		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(133);
		column.setText("Pick up from model?");

	}

	public ArrayList<Object[]> getRowsOf(String field){
		return  window.getHashTable().get(field);
	}

	public Fix getFix(){
		return null;
	}
}
