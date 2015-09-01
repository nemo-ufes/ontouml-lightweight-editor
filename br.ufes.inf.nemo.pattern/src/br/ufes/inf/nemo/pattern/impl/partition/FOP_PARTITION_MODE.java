package br.ufes.inf.nemo.pattern.impl.partition;

import java.util.Arrays;

import RefOntoUML.Mode;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_PARTITION_MODE extends AbstractPattern{

	public FOP_PARTITION_MODE(OntoUMLParser parser, double x, double y) {
		super(parser, x, y,"/resource/Mode_Partition_FOP.png", "Mode Partition");
	}

	@Override
	public void runPattern(){
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Mode.class})));

		dym.addTableLine("general", "General", new String[] {"Mode"});

		dym.addTableLine("specific", "Specific 1", new String[] {"Mode"});
		dym.addTableLine("specific", "Specific 2", new String[] {"Mode"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Mode"});

		isPartitionPattern(Arrays.asList(new Class[]{Mode.class}), Arrays.asList(new Class[]{Mode.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getPartitionFix();
		return fix;
	}
}
