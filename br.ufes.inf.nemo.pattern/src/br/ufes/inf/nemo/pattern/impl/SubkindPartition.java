package br.ufes.inf.nemo.pattern.impl;

import java.util.Arrays;

import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

public class SubkindPartition extends AbstractPattern{

	public SubkindPartition(OntoUMLParser parser, double x, double y) {
		super(parser, x, y,"/resource/SubkindPartition.png", "Subkind Partition");
	}

	@Override
	public void runPattern(){
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, SubKind.class})));

		dym.addTableLine("general", "General", new String[] {"Kind","Collective", "Quantity", "Subkind"});

		dym.addTableLine("specific", "Specific 1", new String[] {"Subkind"});
		dym.addTableLine("specific", "Specific 2", new String[] {"Subkind"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Subkind"});

		isPartitionPattern(Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, SubKind.class}), Arrays.asList(new Class[]{SubKind.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getPartitionFix();
		return fix;
	}
}
