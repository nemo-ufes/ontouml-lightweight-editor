package br.ufes.inf.nemo.pattern.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

public class GenericMultipleRelator extends AbstractPattern {


	public GenericMultipleRelator(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/GenericMultipleRelator.png", "Generic Multiple Relator");
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

		set = parser.getAllInstances(Relator.class);
		if(!set.isEmpty())
			hashTree.put("Relator", UtilAssistant.getStringRepresentationClass(set));

		dym.addHashTree(hashTree);

		dym.addTableLine("sortal", "Sotal 1", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
		dym.addTableLine("sortal", "Sotal 2", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});

		dym.addTableLine("relator", "Relator", new String[] {"Relator"});

		dym.setInitialItemCount(3);
		dym.setAddLineButtonAction("sortal", "Sortal N", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
		
		dm.open();		
	}

	@Override
	public Fix getFix() {
		try{
			Package root = parser.getModel();
			outcomeFixer = new OutcomeFixer(root);
			fix = new Fix();

			ArrayList<Object[]> relators = dym.getRowsOf("relator");
			ArrayList<Object[]> sortals = dym.getRowsOf("sortal");
			
			
			Classifier sortal;
			Classifier relator = getClassifier(relators.get(0), x, y);
			
			int i = 0;
			for(Object[] row : sortals){
				sortal = getClassifier(row, x+(i*verticalDistance)/3, y+horizontalDistance);
				if(sortal != null){
					if(relator != null){
						Association mediation = (Association) outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, sortal).getAdded().get(0);
						fix.includeAdded(mediation);
					}
					i++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return fix;
	}

}
