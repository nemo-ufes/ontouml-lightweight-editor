package br.ufes.inf.nemo.pattern.impl.other;

import java.util.ArrayList;
import java.util.Arrays;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_GENERIC_RELATOR extends AbstractPattern {

	public FOP_GENERIC_RELATOR(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/Generic_Relator_FOP.png", "Generic Relator Pattern");
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, Relator.class, Role.class, Phase.class, Category.class, Mixin.class, RoleMixin.class})));

		dym.addTableLine("relator", "Relator", new String[] {"Relator"});

		dym.addTableLine("mediated", "Mediated 1", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role", "Category", "Mixin", "RoleMixin"});
		dym.addTableLine("mediated", "Mediated 2", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role", "Category", "Mixin", "RoleMixin"});

		dm.open();		
	}

	@Override
	public Fix getSpecificFix() {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		ArrayList<Object[]> mediateds = dym.getRowsOf("mediated");
		ArrayList<Object[]> relators = dym.getRowsOf("relator");

		if(mediateds == null || relators == null)
			return null;

		Classifier mediated1, mediated2, relator;

		mediated1 	= getClassifier(mediateds.get(0),x-(verticalDistance/2), y+(horizontalDistance/2));
		mediated2 	= getClassifier(mediateds.get(1),x+(verticalDistance/2), y+(horizontalDistance/2));
		relator 	= getClassifier(relators.get(0), x, y);

		Association leftMediation = null;
		Association rightMediation = null;
		Association material = null;
		Association derivation = null;

		if(mediated1 != null && mediated2 != null){
			material = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MATERIAL, "", mediated1, mediated2).getAdded().get(0);
			fix.includeAdded(material);
		}

		if(relator != null){
			if(mediated1 != null){
				leftMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, mediated1).getAdded().get(0);
				fix.includeAdded(leftMediation);
			}

			if(mediated2 != null){
				rightMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, mediated2).getAdded().get(0);
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
