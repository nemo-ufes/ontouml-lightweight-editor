package br.ufes.inf.nemo.pattern.impl.other;

import java.util.ArrayList;
import java.util.Arrays;

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
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_MULTIPLE_GENERIC_RELATOR extends AbstractPattern {


	public FOP_MULTIPLE_GENERIC_RELATOR(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/Multiple_Generic_Relator_FOP.PNG", "Multiple Generic Relator");
	}

	public FOP_MULTIPLE_GENERIC_RELATOR(OntoUMLParser parser, Classifier c, double x,	double y) {
		super(parser, x, y, "/resource/Multiple_Generic_Relator_FOP.PNG", "Multiple Generic Relator");
		this.c = c;
	}
	private Classifier c = null;

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, Role.class, Phase.class, Relator.class})));

		if(c != null){
			if(c instanceof Role){
				dym.addTableLine("relator", "Relator", new String[] {"Relator"});
				dym.addTableRigidLine("sortal", UtilAssistant.getStringRepresentationClass(c), new String[] {"Role"});
				dym.addTableLine("sortal", "Mediated 2", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
			}
			if(c instanceof Relator){
				dym.addTableRigidLine("relator", UtilAssistant.getStringRepresentationClass(c), new String[] {"Relator"});
				dym.addTableLine("sortal", "Mediated 1", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
				dym.addTableLine("sortal", "Mediated 2", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
			}
		}else{
			dym.addTableLine("relator", "Relator", new String[] {"Relator"});
			dym.addTableLine("sortal", "Mediated 1", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
			dym.addTableLine("sortal", "Mediated 2", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
		}

		dym.setInitialItemCount(3);
		dym.setAddLineButtonAction("sortal", "Mediated N", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});

		dm.open();		
	}

	@Override
	public Fix getSpecificFix() {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		ArrayList<Object[]> relators = dym.getRowsOf("relator");
		ArrayList<Object[]> sortals = dym.getRowsOf("sortal");

		if(relators == null || sortals == null)
			return null;

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
		return fix;
	}

}
