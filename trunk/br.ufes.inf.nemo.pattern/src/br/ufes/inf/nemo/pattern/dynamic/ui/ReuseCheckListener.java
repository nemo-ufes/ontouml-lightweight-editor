package br.ufes.inf.nemo.pattern.dynamic.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

public class ReuseCheckListener implements SelectionListener{
	HashMap<String,String[]> hashTree;
	DynamicTree treeView = new DynamicTree();
	Text text;
	CCombo combo;
	boolean comboEnabled = false;
	ArrayList<Text> classes;
	
	public ReuseCheckListener(HashMap<String,String[]> hashTree, ArrayList<Text> classes, Text text, CCombo combo) {
		this.hashTree = hashTree;
		this.text = text;
		this.combo = combo;
		comboEnabled = combo.isEnabled();
		this.classes = classes;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent evt) {
		widgetSelected(evt);
	}

	@Override
	public void widgetSelected(SelectionEvent evt) {
		Button btn = (Button) evt.getSource();
		if(btn.getSelection()){
			treeView.open(hashTree, classes, combo.getItems());
			if(treeView.getClassName() != null){
				text.setText(treeView.getClassName());
				text.setEnabled(false);
				String[] items = Arrays.copyOf(combo.getItems(), combo.getItemCount()+1);
				items[combo.getItemCount()] = treeView.getClassStereotype();
				combo.setItems(items);
				combo.select(combo.getItemCount()-1);
				combo.setEnabled(false);
			}else{
				btn.setSelection(false);
			}
		}else{
			text.setEnabled(true);
			if(comboEnabled)
				combo.setEnabled(true);
			combo.remove(combo.getItemCount()-1);
			combo.select(0);
		}
		
	}

	
}