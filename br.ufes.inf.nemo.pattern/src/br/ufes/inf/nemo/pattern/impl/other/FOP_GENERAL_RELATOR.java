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

public class FOP_GENERAL_RELATOR extends AbstractPattern {

	private Classifier c = null;
	public FOP_GENERAL_RELATOR(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/General_Relator_FOP.png", "General Relator Pattern");
	}

	public FOP_GENERAL_RELATOR(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/General_Relator_FOP.png", "General Relator Pattern");
		this.c = c;
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, Relator.class, Role.class, Phase.class})));

		if(c != null && c instanceof Role){
			dym.addTableLine("general", "General 1", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Role"});

			dym.addTableLine("general", "General 2", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
			dym.addTableLine("specific", "Specific 2", new String[] {"Role"});
		}else{
			dym.addTableLine("general", "General 1", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
			dym.addTableLine("specific", "Specific 1", new String[] {"Role"});

			dym.addTableLine("general", "General 2", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
			dym.addTableLine("specific", "Specific 2", new String[] {"Role"});
		}

		if(c != null && c instanceof Relator){
			dym.addTableRigidLine("relator", UtilAssistant.getStringRepresentationClass(c), new String[] {"Relator"});
		}else{
			dym.addTableLine("relator", "Relator", new String[] {"Relator"});
		}

		dm.open();		
	}

	@Override
	public Fix getSpecificFix() {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		ArrayList<Object[]> generals = dym.getRowsOf("general");
		ArrayList<Object[]> specifics = dym.getRowsOf("specific");
		ArrayList<Object[]> relators = dym.getRowsOf("relator");

		if(generals == null || specifics == null || relators == null)
			return null;
		
		Classifier general1, general2, specific1, specific2, relator;

		if(c != null){
			general1 	= getClassifier(generals.get(0), x-(verticalDistance/2), y+70);
			general2 	= getClassifier(generals.get(1), x+(verticalDistance/2), y+70);
			specific1 	= getClassifier(specifics.get(0),x-(verticalDistance/2), y+(horizontalDistance/2)+70);
			specific2 	= getClassifier(specifics.get(1),x+(verticalDistance/2), y+(horizontalDistance/2)+70);
			relator 		= getClassifier(relators.get(0), x, y);
		}else{
			general1 	= getClassifier(generals.get(0), x-(verticalDistance/2), y);
			general2 	= getClassifier(generals.get(1), x+(verticalDistance/2), y);
			specific1 	= getClassifier(specifics.get(0),x-(verticalDistance/2), y+(horizontalDistance/2));
			specific2 	= getClassifier(specifics.get(1),x+(verticalDistance/2), y+(horizontalDistance/2));
			relator 		= getClassifier(relators.get(0), x, y);
		}

		Association leftMediation = null;
		Association rightMediation = null;
		Association material = null;
		Association derivation = null;

		if(general1 != null && specific1 != null){
			fix.addAll(outcomeFixer.createGeneralization(specific1,general1));	
		}

		if(general2 != null && specific2 != null){
			fix.addAll(outcomeFixer.createGeneralization(specific2,general2));	
		}

		if(specific1 != null && specific2 != null){
			material = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MATERIAL, "", specific1, specific2).getAdded().get(0);
			fix.includeAdded(material);
		}

		if(relator != null){
			if(specific1 != null){
				leftMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, specific1).getAdded().get(0);
				fix.includeAdded(leftMediation);
			}

			if(specific2 != null){
				rightMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, specific2).getAdded().get(0);
				fix.includeAdded(rightMediation);
			}

			if(material != null){
				derivation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.DERIVATION, "", relator, material).getAdded().get(0);
				fix.includeAdded(derivation);
			}
		}
		return fix;
	}

}
