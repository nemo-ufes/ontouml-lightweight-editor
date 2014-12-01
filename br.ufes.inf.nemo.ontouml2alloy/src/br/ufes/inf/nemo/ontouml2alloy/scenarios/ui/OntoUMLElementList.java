package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.parser.OntoUMLParser;

public class OntoUMLElementList extends JList<OntoUMLElement>{

	private static final long serialVersionUID = 7355470398462222638L;

	private OntoUMLParser parser;
	private Class<? extends EObject> metaClass;

	private ArrayList<EObject> allElements;
	
	public OntoUMLElementList(OntoUMLParser parser){
		super();
		this.parser = parser;
		setModel(new DefaultListModel<OntoUMLElement>());
		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		setEnabled(true);
	}
	
	public OntoUMLElementList(OntoUMLParser parser, Class<? extends EObject> metaClass){
		this(parser);
		
		setMetaClass(metaClass);
		addInstancesToModel();
	}

	private void setMetaClass(Class<? extends EObject> metaClass) {
		this.metaClass = metaClass;
	}
	
	public void addInstancesToModel(){
		allElements = new ArrayList<EObject>(parser.getAllInstances(metaClass));
		
		for (EObject obj : allElements) {
			getDefaulListModel().addElement(new OntoUMLElement(obj));
		}
	}
	
	public DefaultListModel<OntoUMLElement> getDefaulListModel(){
		return (DefaultListModel<OntoUMLElement>) getModel();
	}
	
	public ArrayList<EObject> getSelectedArray(){
		ArrayList<EObject> array = new ArrayList<EObject>();
		
		for (OntoUMLElement e : getSelectedValuesList()) {
			array.add(e.element);
		}
		
		return array;
	}
	
	public void setSelectedArray(ArrayList<EObject> array){
		
		int[] indices = new int[array.size()];
		int i = 0;
		
		for (EObject e : array) {
			indices[i]=allElements.indexOf(e);
			i++;
		}
		
		setSelectedIndices(indices);	
	}
	
	
	
	

}
