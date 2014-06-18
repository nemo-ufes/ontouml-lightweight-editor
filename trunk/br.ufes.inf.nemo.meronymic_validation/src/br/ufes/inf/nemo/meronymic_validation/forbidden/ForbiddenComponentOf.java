package br.ufes.inf.nemo.meronymic_validation.forbidden;

import java.util.ArrayList;

import javax.swing.JDialog;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.componentOf;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.userinterface.FixDialog;

public class ForbiddenComponentOf extends ForbiddenMeronymic<componentOf> {
	
	enum PATTERN {PATTERN_4, PATTERN_5, COMPOSITION};
	PATTERN pattern;
	
	ArrayList<Classifier> nodes;
	
	public ForbiddenComponentOf(componentOf m, OntoUMLParser parser) {
		super(m, parser);
		nodes = new ArrayList<Classifier>();
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
			pattern = PATTERN.PATTERN_4;
		else if (!parentIndirection && childIndirection)
			pattern = PATTERN.PATTERN_5;
		else if (parentIndirection && childIndirection)
			pattern = PATTERN.COMPOSITION;
		else
			pattern = null;
	}
	
	@Override
	public String getDescription() {
		String result;
		
		if (pattern==PATTERN.PATTERN_4)
			result = "Pattern 4";
		else if (pattern==PATTERN.PATTERN_5)
			result = "Pattern 5";
		else if (pattern==PATTERN.COMPOSITION)
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
		//TODO
		return null;
	}

	@Override
	public FixDialog createDialog(JDialog parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
