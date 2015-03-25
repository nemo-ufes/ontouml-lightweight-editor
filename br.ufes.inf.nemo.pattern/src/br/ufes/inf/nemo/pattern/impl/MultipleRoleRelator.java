package br.ufes.inf.nemo.pattern.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

public class MultipleRoleRelator extends AbstractPattern {


	public MultipleRoleRelator(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/MultipleRoleRelator.png", "Multiple Role Relator");
	}

	@Override
	public void runPattern() {
		HashMap<String, String[]> hashTree = new HashMap<>();
		Set<? extends Classifier> set;

		set = parser.getAllInstances(Role.class);
		if(!set.isEmpty())
			hashTree.put("Role", UtilAssistant.getStringRepresentationClass(set));

		set = parser.getAllInstances(Relator.class);
		if(!set.isEmpty())
			hashTree.put("Relator", UtilAssistant.getStringRepresentationClass(set));

		dym.addHashTree(hashTree);

		dym.addTableLine("role", "Role 1", new String[] {"Role"});
		dym.addTableLine("role", "Role 2", new String[] {"Role"});

		dym.addTableLine("relator", "Relator", new String[] {"Relator"});

		dym.setInitialItemCount(3);
		dym.setAddLineButtonAction("role", "Role N", new String[] {"Role"});
		
		dm.open();		
	}

	@Override
	public Fix getFix() {
		try{
			Package root = parser.getModel();
			outcomeFixer = new OutcomeFixer(root);
			fix = new Fix();

			ArrayList<Object[]> relators = dym.getRowsOf("relator");
			ArrayList<Object[]> roles = dym.getRowsOf("role");
			
			
			Classifier role;
			Classifier relator = getClassifier(relators.get(0), x, y);
			
			int i = 0;
			for(Object[] row : roles){
				role = getClassifier(row, x+(i*verticalDistance)/3, y+horizontalDistance);
				if(role != null){
					if(relator != null){
						Association mediation = (Association) outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, role).getAdded().get(0);
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
