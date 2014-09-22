package br.ufes.inf.nemo.validator.meronymic.checkers.ui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import RefOntoUML.Generalization;
import RefOntoUML.parser.OntoUMLNameHelper;

public class GeneralizationListModel extends AbstractListModel<String>{
	
	private static final long serialVersionUID = 1184366409859153347L;
	
	ArrayList<Generalization> gList = new ArrayList<Generalization>();
	
	public GeneralizationListModel() {

	}
	
	public GeneralizationListModel(ArrayList<Generalization> gList){
		this.gList.addAll(gList);
	}

	@Override
	public String getElementAt(int index) {
		return OntoUMLNameHelper.getCompleteName(gList.get(index));
	}

	@Override
	public int getSize() {
		return gList.size();
	}

	public void addAt(int index, Generalization g) {
		gList.add(index, g);
		fireIntervalAdded(this, index, index);
	}
	
	public void add(Generalization g) {
		gList.add(g);
		fireIntervalAdded(this, gList.size()-1, gList.size()-1);
	}
	
	public Generalization getId(int index) {
		return gList.get(index);
	}
	
	public Generalization removeAt(int index){
		Generalization g = gList.remove(index);
		fireIntervalRemoved(this, gList.size(), gList.size());
		return g;
	}
	
	public ArrayList<Generalization> getAll(){
		return gList;
	}
}
