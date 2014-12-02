package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Derivation;
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
	
	public void setAsAntiRigidCombo(){
		OntoUMLElement[] array = convertSetToArray(parser.getAntiRigidClasses());
		setModel(new DefaultComboBoxModel<OntoUMLElement>(array));
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

	public void setObjects(Set<EObject> items) {
		setModel(new DefaultComboBoxModel<OntoUMLElement>(convertSetToArray(items)));	
	}
	
	public void setAsAssociationCombo(){
		Set<? extends EObject> associations = parser.getAllInstances(Association.class);
		Iterator<? extends EObject> iterator = associations.iterator();
		
		while(iterator.hasNext()){
			EObject a = iterator.next();
			if(a instanceof Derivation)
				iterator.remove();
		}
		
		OntoUMLElement[] array = convertSetToArray(associations);
		setModel(new DefaultComboBoxModel<OntoUMLElement>(array));
	}
	
	

}
