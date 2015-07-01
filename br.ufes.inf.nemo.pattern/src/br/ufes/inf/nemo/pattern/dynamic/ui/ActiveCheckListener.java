package br.ufes.inf.nemo.pattern.dynamic.ui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class ActiveCheckListener implements SelectionListener{
	Text text;
	CCombo combo;
	TableItem tableItem;
	Button reuse;
	boolean comboEnabled = false;
	ArrayList<String> status;

	public ActiveCheckListener(ArrayList<String> status, TableItem tableItem, Text text, CCombo combo, Button reuse) {
		this.text = text;
		this.combo = combo;
		comboEnabled = combo.isEnabled();
		this.tableItem = tableItem;
		this.reuse = reuse;
		this.status = status;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent evt) {
		widgetSelected(evt);
	}

	@Override
	public void widgetSelected(SelectionEvent evt) {
		Button btn = (Button) evt.getSource();
		if(btn.getSelection()){
			status.add("Deactivated <"+combo.getItem(combo.getSelectionIndex())+"> "+text.getText());
			reuse.setEnabled(false);
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			text.setEnabled(false);
			tableItem.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			if(combo.isEnabled()){
				combo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
				combo.setEnabled(false);
			}
		}else{
			reuse.setEnabled(true);
			tableItem.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			text.setEnabled(true);
			if(comboEnabled){
				combo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
				combo.setEnabled(true);
			}
		}
	}
}