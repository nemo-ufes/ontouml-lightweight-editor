package br.ufes.inf.nemo.validator.meronymic.forbidden;

import java.util.ArrayList;

import javax.swing.JDialog;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.validator.meronymic.forbidden.ui.ForbiddenComponentOfDialog;
import br.ufes.inf.nemo.validator.meronymic.ui.FixDialog;

public class ForbiddenComponentOf extends ForbiddenMeronymic<componentOf> {
	
	enum Pattern {PATTERN_4, PATTERN_5, COMPOSITION};
	enum Action {REMOVE, REVERSE, FLATTEN};
	
	Pattern pattern;
	
	ArrayList<Classifier> nodes;
	
	public ForbiddenComponentOf(componentOf m, OntoUMLParser parser) {
		super(m, parser);
		nodes = new ArrayList<Classifier>();
		action = null;
	}

	public void setNodes(ArrayList<Classifier> nodes){
		this.nodes.clear();
		this.nodes.addAll(nodes);
	}
	
	public void classifyPath(){
		boolean childIndirection = false;
		boolean parentIndirection = false;
		
		for (int i = 0; i < path.size(); i++) {
			Property p = path.get(i);
			Classifier c = nodes.get(i+1);
			
			if(c.allParents().contains(p.getType()))
				childIndirection = true;
			else if (c.allChildren().contains(p.getType()))
				parentIndirection = true;
			
			if(parentIndirection && childIndirection)
				break;
		}
		
		if(parentIndirection && !childIndirection)
			pattern = Pattern.PATTERN_4;
		else if (!parentIndirection && childIndirection)
			pattern = Pattern.PATTERN_5;
		else if (parentIndirection && childIndirection)
			pattern = Pattern.COMPOSITION;
		else
			pattern = null;
	}
	
	@Override
	public String getDescription() {
		String result;
		
		if (pattern==Pattern.PATTERN_4)
			result = "Pattern 4";
		else if (pattern==Pattern.PATTERN_5)
			result = "Pattern 5";
		else if (pattern==Pattern.COMPOSITION)
			result =  "Patterns 4 and 5";
		else
			result = "null";
		
		int i = 0;
		for (Classifier node : nodes) {
			if(i!=0)
				result += " -> ";
			result += OntoUMLNameHelper.getTypeAndName(node, true, true);
			i++;
		}
		
		return result;
	}
	
	@Override
	public Fix fix() {
		if(isRemove())
			remove();
		else if(isReverse())
			reverse();
		else if(isFlatten())
			flatten();
		
		return fix;
	}

	private void flatten() {
		
		for (int i = 0; i < path.size(); i++) {
			Property partEnd = path.get(i);
			Type nextWhole;
			
			if(i==path.size()-1)
				nextWhole = OntoUMLParser.getPartEnd(meronymic).getType();
			else
				nextWhole = path.get(i+1).getOpposite().getType();
			
			if(!partEnd.getType().equals(nextWhole)){
				partEnd.setType(nextWhole);
				fix.includeModified(partEnd);
			}
		}
		
		return;
	}

	@Override
	public FixDialog<?> createDialog(JDialog parent) {
		ForbiddenComponentOfDialog dialog = new ForbiddenComponentOfDialog(parent, this);
		dialog.setSize(600, 610);
		return dialog;
	}

	public void setRemove(Meronymic relation) {
		action = Action.REMOVE;
		this.relationToRemove = relation;
	}

	public void setReverse(Meronymic relation) {
		action = Action.REVERSE;
		this.relationToReverse = relation;
	}

	public void setFlatten() {
		action = Action.FLATTEN;
	}

	public boolean isRemove() {
		return action==Action.REMOVE;
	}
	
	public boolean isReverse() {
		return action==Action.REVERSE;
	}
	
	public boolean isFlatten() {
		return action==Action.FLATTEN;
	}

	
	
}
