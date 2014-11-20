package br.ufes.inf.nemo.pattern.impl;

import java.util.HashMap;
import java.util.Set;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

public class SubkindPartition extends AbstractPattern{

	public SubkindPartition(OntoUMLParser parser, double x, double y) {
		super(parser, x, y,"/resource/SubkindPartition.png", "Subkind Partition");
	}

	@Override
	public void runPattern(){
		HashMap<String, String[]> hashTree = new HashMap<>();
		Set<? extends Classifier> set;

		set = parser.getAllInstances(Kind.class);
		if(!set.isEmpty())
			hashTree.put("Kind", UtilAssistant.getStringRepresentationClass(set));

		set = parser.getAllInstances(Collective.class);
		if(!set.isEmpty())
			hashTree.put("Collective", UtilAssistant.getStringRepresentationClass(set));

		set = parser.getAllInstances(Quantity.class);
		if(!set.isEmpty())
			hashTree.put("Quantity", UtilAssistant.getStringRepresentationClass(set));

		set = parser.getAllInstances(SubKind.class);
		if(!set.isEmpty())
			hashTree.put("Subkind", UtilAssistant.getStringRepresentationClass(set));

		dym.addHashTree(hashTree);

		dym.addTableLine("general", "General", new String[] {"Kind","Collective", "Quantity", "Subkind"});

		dym.addTableLine("specific", "Specific 1", new String[] {"Subkind"});
		dym.addTableLine("specific", "Specific 2", new String[] {"Subkind"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Subkind"});

		dm.open();
	}

	@Override
	public Fix getFix(){
		getPartitionFix();
		return fix;
	}
}
