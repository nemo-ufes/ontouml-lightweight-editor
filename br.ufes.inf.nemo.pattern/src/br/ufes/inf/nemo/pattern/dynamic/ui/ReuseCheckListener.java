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
	DynamicReuseClassTree treeView = new DynamicReuseClassTree();
	Text text;
	CCombo combo;
	boolean comboEnabled = false;
	ArrayList<Text> classes;
	ArrayList<String> status;
	
	public ReuseCheckListener(ArrayList<String> status, HashMap<String,String[]> hashTree, ArrayList<Text> classes, Text text, CCombo combo) {
		this.hashTree = hashTree;
		this.text = text;
		this.combo = combo;
		comboEnabled = combo.isEnabled();
		this.classes = classes;
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
			treeView.open(hashTree, classes, combo.getItems());
			if(treeView.getClassName() != null){
				if(status != null)
					status.add("Reused class on pattern from <"+combo.getItem(combo.getSelectionIndex())+"> "+text.getText()+" to <"+treeView.getClassStereotype()+"> "+treeView.getClassName());
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