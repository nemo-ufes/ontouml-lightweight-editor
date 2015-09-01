package br.ufes.inf.nemo.pattern.impl.partition;

import java.util.Arrays;
import java.util.Locale.Category;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_PARTITION_CATEGORY extends AbstractPattern{

	public FOP_PARTITION_CATEGORY(OntoUMLParser parser, double x, double y) {
		super(parser, x, y,"/resource/Category_Partition_FOP.png", "Category Partition");
	}

	@Override
	public void runPattern(){
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Category.class})));

		dym.addTableLine("general", "General", new String[] {"Category"});

		dym.addTableLine("specific", "Specific 1", new String[] {"Category"});
		dym.addTableLine("specific", "Specific 2", new String[] {"Category"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Category"});

		isPartitionPattern(Arrays.asList(new Class[]{Category.class}), Arrays.asList(new Class[]{Category.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getPartitionFix();
		return fix;
	}
}
