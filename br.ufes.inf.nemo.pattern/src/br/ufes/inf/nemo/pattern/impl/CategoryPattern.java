package br.ufes.inf.nemo.pattern.impl;

import java.util.Arrays;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

public class CategoryPattern extends AbstractPattern{

	private Classifier c;

	public CategoryPattern(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/CategoryPattern.png", "Category Pattern");
	}

	public CategoryPattern(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/CategoryPattern.png", "Category Pattern");
		this.c = c;
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Category.class, Kind.class, Quantity.class, Collective.class})));

		if(c instanceof RefOntoUML.Category){
			dym.addTableRigidLine("general", UtilAssistant.getStringRepresentationClass(c), new String[] {"Category"});
		}else{
			dym.addTableLine("general", "General", new String[] {"Category"});	
		}
		
		dym.addTableLine("specific", "Specific 1", new String[] {"Category", "Kind", "Quantity", "Collective"});
		dym.addTableLine("specific", "Specific 2", new String[] {"Category", "Quantity", "Collective"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Category", "Kind", "Quantity", "Collective"});

		reuseGeneralizationSet(Arrays.asList(new Class[]{Category.class}), Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, Category.class}));
		
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getPartitionFix();
		return fix;
	}

}
