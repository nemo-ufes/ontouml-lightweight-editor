package br.ufes.inf.nemo.pattern.dynamic.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.pattern.ui.manager.DynamicManagerWindow;

public class PartitionGeneralizationSetCheckListener implements SelectionListener{

	private HashMap<String, ArrayList<String>> hashGS;
	private ArrayList<TableEditor> generalEditors;
	private DynamicReuseGeneralizationSetTree window = new DynamicReuseGeneralizationSetTree();
	private String firstName;
	private Text textField;
	private Set<GeneralizationSet> generalizationSets;
	private ArrayList<ArrayList<TableEditor>> editors;
	private DynamicManagerWindow dmw;
	
	public PartitionGeneralizationSetCheckListener(DynamicManagerWindow dmWin, HashMap<String, ArrayList<String>> hash, ArrayList<ArrayList<TableEditor>> eds, Text t, Set<GeneralizationSet> gs) {
		hashGS = hash;
		editors = eds;
		generalEditors = eds.get(0);
		firstName = ((Text)generalEditors.get(1).getEditor()).getText();
		textField = t;
		generalizationSets = gs;
		dmw = dmWin;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent evt) {
		widgetSelected(evt);

	}

	@Override
	public void widgetSelected(SelectionEvent evt) {
		Button btn = (Button) evt.getSource();
		if(btn.getSelection()){
			window.open(hashGS);
			if(window.getStereotype() != null){
				generalEditors.get(0).getEditor().setEnabled(false);
				
				generalEditors.get(1).getEditor().setEnabled(false);
				String className = window.getGS().substring(window.getGS().indexOf(" / ")+3, window.getGS().indexOf(" {"));
				((Text)generalEditors.get(1).getEditor()).setText(className);
				
				generalEditors.get(2).getEditor().setEnabled(false);
				CCombo combo = ((CCombo)generalEditors.get(2).getEditor());
				String[] items = Arrays.copyOf(combo.getItems(), combo.getItemCount()+1);
				items[combo.getItemCount()] = window.getStereotype();
				combo.setItems(items);
				combo.select(combo.getItemCount()-1);
				combo.setEnabled(false);
				
				generalEditors.get(3).getEditor().setEnabled(false);
				((Button)generalEditors.get(3).getEditor()).setSelection(true);
				
				textField.setEnabled(false);
				String partitionName = window.getGS().substring(0, window.getGS().indexOf(" / "));
				textField.setText(partitionName);
				
				String specificsStereotypes = UtilAssistant.getStereotypeFromSpecificsInPartition(generalizationSets, partitionName); 
				for(ArrayList<TableEditor> line : editors){
					if(!line.equals(generalEditors)){
						line.get(2).getEditor().setEnabled(false);
						combo = ((CCombo)line.get(2).getEditor());
						items = Arrays.copyOf(combo.getItems(), combo.getItemCount()+1);
						items[combo.getItemCount()] = specificsStereotypes;
						combo.setItems(items);
						combo.select(combo.getItemCount()-1);
						combo.setEnabled(false);
					}
				}
				
				dmw.setGSReuse(true);
			}else{
				btn.setSelection(false);
			}
		}else{
			generalEditors.get(0).getEditor().setEnabled(true);
			generalEditors.get(1).getEditor().setEnabled(true);
			((Text)generalEditors.get(1).getEditor()).setText(firstName);
			
			generalEditors.get(2).getEditor().setEnabled(true);
			CCombo combo = ((CCombo)generalEditors.get(2).getEditor());
			combo.setEnabled(true);
			combo.remove(combo.getItemCount()-1);
			combo.select(0);
			
			generalEditors.get(3).getEditor().setEnabled(true);
			((Button)generalEditors.get(3).getEditor()).setSelection(false);
			
			textField.setEnabled(true);
			textField.setText("partition"+UtilAssistant.getCont());
			
			for(ArrayList<TableEditor> line : editors){
				if(!line.equals(generalEditors)){
					combo = ((CCombo)line.get(2).getEditor());
					combo.setEnabled(true);
					combo.remove(combo.getItemCount()-1);
					combo.select(0);
				}
			}
			dmw.setGSReuse(false);
		}
		
	}
}
