package br.ufes.inf.nemo.pattern.impl.generalization;

import java.util.Arrays;
import java.util.Locale.Category;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_GENERALIZATION_CATEGORY extends AbstractPattern{

	public FOP_GENERALIZATION_CATEGORY(OntoUMLParser parser, double x, double y) {
		super(parser, x, y,"/resource/Category_Generalization_FOP.png", "Category Generalization");
	}

	Classifier c;
	public FOP_GENERALIZATION_CATEGORY(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y,"/resource/Category_Generalization_FOP.png", "Category Generalization");
		this.c = c;
	}
	
	@Override
	public void runPattern(){
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, SubKind.class, Category.class})));

		if(c != null){
			dym.addTableRigidLine("general", UtilAssistant.getStringRepresentationClass(c), new String[] {"Category"});
		}else{
			dym.addTableLine("general", "General", new String[] {"Category"});	
		}

		dym.addTableLine("specific", "Specific 1", new String[] {"Category","Kind","Collective", "Quantity", "Subkind"});
		dym.addTableLine("specific", "Specific 2", new String[] {"Category","Kind","Collective", "Quantity", "Subkind"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Category","Kind","Collective", "Quantity", "Subkind"});

		isPartitionPattern(Arrays.asList(new Class[]{Category.class}), Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, SubKind.class, Category.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getGeneralizationFix();
		return fix;
	}
}
