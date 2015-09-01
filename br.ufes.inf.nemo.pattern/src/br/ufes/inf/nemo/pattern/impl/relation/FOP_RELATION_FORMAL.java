package br.ufes.inf.nemo.pattern.impl.relation;

import java.util.ArrayList;
import java.util.Arrays;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
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

public class FOP_RELATION_FORMAL extends AbstractPattern {

	public FOP_RELATION_FORMAL(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/Formal_FOP.png", "Formal Relation Pattern");
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, Role.class, Phase.class, Relator.class, Mixin.class, RoleMixin.class, Category.class, Mode.class})));

		dym.addTableLine("member", "Member A", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role", "RoleMixin", "Mixin", "Category", "Relator", "Mode" });
		dym.addTableLine("member", "Member B", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role", "RoleMixin", "Mixin", "Category", "Relator", "Mode" });

		dm.open();		
	}

	@Override
	public Fix getSpecificFix() {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		ArrayList<Object[]> members = dym.getRowsOf("member");
		
		if(members == null)
			return null;

		Classifier memberA = getClassifier(members.get(0), x, y);
		Classifier memberB = getClassifier(members.get(1), x+verticalDistance, y);

		Association formal = null;

		if(memberA != null && memberB != null){
			formal = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.FORMAL, "", memberA, memberB).getAdded().get(0);
			fix.includeAdded(formal);
		}

		return fix;
	}

}
