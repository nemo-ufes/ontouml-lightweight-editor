package br.ufes.inf.nemo.pattern.impl;

import java.util.HashMap;
import java.util.Set;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

public class RolePartition extends AbstractPattern{

	public RolePartition(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/RolePartition.png", "Role Partition");
	}

	@Override
	public void runPattern() {
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
		
		set = parser.getAllInstances(Phase.class);
		if(!set.isEmpty())
			hashTree.put("Phase", UtilAssistant.getStringRepresentationClass(set));

		set = parser.getAllInstances(Role.class);
		if(!set.isEmpty())
			hashTree.put("Role", UtilAssistant.getStringRepresentationClass(set));


		dym.addHashTree(hashTree);

		dym.addTableLine("general", "General", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});

		dym.addTableLine("specific", "Specific 1", new String[] {"Role"});
		dym.addTableLine("specific", "Specific 2", new String[] {"Role"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Role"});

		dm.open();
		
	}

	@Override
	public Fix getFix(){
		getPartitionFix();
		return fix;
	}

}
