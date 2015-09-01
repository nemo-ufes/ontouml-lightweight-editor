package br.ufes.inf.nemo.pattern.impl.partition;

import java.util.Arrays;

import RefOntoUML.Relator;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_PARTITION_RELATOR extends AbstractPattern{

	public FOP_PARTITION_RELATOR(OntoUMLParser parser, double x, double y) {
		super(parser, x, y,"/resource/Relator_Partition_FOP.png", "Relator Partition");
	}

	@Override
	public void runPattern(){
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Relator.class})));

		dym.addTableLine("general", "General", new String[] {"Relator"});

		dym.addTableLine("specific", "Specific 1", new String[] {"Relator"});
		dym.addTableLine("specific", "Specific 2", new String[] {"Relator"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Relator"});

		isPartitionPattern(Arrays.asList(new Class[]{Relator.class}), Arrays.asList(new Class[]{Relator.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getPartitionFix();
		return fix;
	}
}
