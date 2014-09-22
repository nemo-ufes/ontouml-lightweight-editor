package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;

import javax.swing.JDialog;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.validator.meronymic.checkers.ui.HierarchyCycleDialog;

public class HierarchyCycleError extends MeronymicError<ArrayList<Classifier>>{
	
	ArrayList<Generalization> gCycle, keepList, removeList, reverseList;

	public HierarchyCycleError(OntoUMLParser parser, ArrayList<Classifier> cycle, ArrayList<Generalization> generalizations) {
		super(parser,new ArrayList<Classifier>(cycle));
		gCycle = new ArrayList<Generalization>(generalizations);
		removeList = new ArrayList<Generalization>();
		reverseList = new ArrayList<Generalization>();
		keepList = new ArrayList<Generalization>();
		
		addAllToKeepList(gCycle);
	}
	
	@Override
	public String getDescription(){
		String result = "";
		
		for (Classifier c : element) {
			result += c.getName()+", ";
		}
		
		return result;
	}

	@Override
	public String getType() {
		return "Hierarchy Cycle";
	}
	
	@Override
	public JDialog createDialog(JDialog parent) {
		return new HierarchyCycleDialog(parent, this);
	}
	
	public ArrayList<Generalization> getGenCycle() {
		return gCycle;
	}
	
	public ArrayList<Generalization> getKeepList() {
		return keepList;
	}

	public ArrayList<Generalization> getRemoveList() {
		return removeList;
	}

	public ArrayList<Generalization> getReverseList() {
		return reverseList;
	}

	public void addToRemoveList(Generalization g){
		if(g==null || !gCycle.contains(g)) return;
		
		keepList.remove(g);
		reverseList.remove(g);
		
		if(!removeList.contains(g))
			removeList.add(g);
	}
	
	public void addAllToRemoveList(ArrayList<Generalization> gList){
		if(gList==null) return;
		
		for (Generalization g : gList) {
			addToRemoveList(g);
		}
	}
	
	public void addToReverseList(Generalization g){
		if(g==null || !gCycle.contains(g)) return;
		
		keepList.remove(g);
		removeList.remove(g);
		
		if(!reverseList.contains(g))
			reverseList.add(g);
	}
	
	public void addAllToReverseList(ArrayList<Generalization> gList){
		if(gList==null) return;
		
		for (Generalization g : gList) {
			addToReverseList(g);
		}
	}
	
	public void addToKeepList(Generalization g){
		if(g==null || !gCycle.contains(g)) return;
		
		removeList.remove(g);
		reverseList.remove(g);
		
		if(!keepList.contains(g))
			keepList.add(g);
	}
	
	public void addAllToKeepList(ArrayList<Generalization> gList){
		if(gList==null) return;
		
		for (Generalization g : gList) {
			addToKeepList(g);
		}
	}
	
	public void invertGeneralizations(){
		for (Generalization g : reverseList) {
			fix.addAll(fixer.invertEnds(g));
		}
	}
	
	public void removeGeneralizations(){
		for (Generalization g : removeList) {
			fix.addAll(fixer.deleteElement(g));
		}
	}

	@Override
	public Fix fix() {
		
		if(reverseList.size()>0){
			System.out.println("Hierarchy Cycle: reversing selected generalizations...");
			try {
				invertGeneralizations();
				System.out.println("Hierarchy Cycle: generalizations successfully reversed.");
			} catch (Exception e){
				System.out.println("Hierarchy Cycle: fail to reverse some generalizations.");
			}
		}
		
		if(removeList.size()>0){
			System.out.println("Hierarchy Cycle: removing selected generalizations...");
			try {
				removeGeneralizations();
				System.out.println("Hierarchy Cycle: generalizations successfully removed.");
			} catch (Exception e){
				System.out.println("Hierarchy Cycle: fail to remove some generalizations.");
			}
		}
		
		return fix;
	}
	
	@Override
	public boolean hasAction(){
		return reverseList.size()>0 || removeList.size()>0;
	}
}
