package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.parser.OntoUMLParser;

public class OntoUMLElementCombo extends JComboBox<OntoUMLElement>{

	private static final long serialVersionUID = 7355470398462222638L;

	private OntoUMLParser parser;
	private Class<? extends EObject> metaClass;
	
	public OntoUMLElementCombo(OntoUMLParser parser){
		super();
		this.parser = parser;
		setEditable(false);
		setEnabled(true);
	}
	
	public OntoUMLElementCombo(Class<? extends EObject> metaClass, OntoUMLParser parser){
		this(parser);
		
		setMetaClass(metaClass);
		setModel(new DefaultComboBoxModel<OntoUMLElement>(getArrayFromMetaClass()));
		setEnabled(true);
//		setEditable(true);
	}

	private void setMetaClass(Class<? extends EObject> metaClass) {
		this.metaClass = metaClass;
	}
	
	public static OntoUMLElementCombo createAntiRigidCombo(OntoUMLParser parser){
		OntoUMLElementCombo combo = new OntoUMLElementCombo(parser);
		OntoUMLElement[] array = convertSetToArray(parser.getAntiRigidClasses());
		combo.setModel(new DefaultComboBoxModel<OntoUMLElement>(array));
		
		return combo;
	} 
	
	public EObject getElement(){
		Object o = this.getSelectedItem();
		
		if(o instanceof OntoUMLElement)
			return ((OntoUMLElement)o).element;
		
		return null;
	}
	
	public void assignElement(EObject element){
		
		for (int i = 0; i < getItemCount(); i++) {
			OntoUMLElement item = getItemAt(i);
			
			if(item.getElement().equals(element)){
				setSelectedIndex(i);
				return;
			}
		}
		
		setSelectedIndex(-1);
	}
	
	private OntoUMLElement[] getArrayFromMetaClass(){
		Set<? extends EObject> set = parser.getAllInstances(metaClass);
		return convertSetToArray(set);
	}

	private static OntoUMLElement[] convertSetToArray(Set<? extends EObject> set) {
		ArrayList<OntoUMLElement> list = new ArrayList<OntoUMLElement>();
		Iterator<? extends EObject> iterator = set.iterator();
		
		while (iterator.hasNext()) {
			list.add(new OntoUMLElement(iterator.next()));
		}
		
		return list.toArray(new OntoUMLElement[1]);
	}
	
	
	
	

}
