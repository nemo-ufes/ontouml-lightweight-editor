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
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_RELATION_COLLECTIVE_MEMBEROF extends AbstractPattern {

	private Classifier c = null;
	public FOP_RELATION_COLLECTIVE_MEMBEROF(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/COLLECTIVE_MEMBER_OF_FOP.png", "Collective with MemeberOf Relation Pattern");
	}

	public FOP_RELATION_COLLECTIVE_MEMBEROF(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/COLLECTIVE_MEMBER_OF_FOP.png", "Collective with MemeberOf Relation Pattern");
		this.c = c;
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, Role.class, Phase.class, Relator.class, Mixin.class, RoleMixin.class, Category.class, Mode.class})));

		if(c instanceof Collective){
			dym.addTableRigidLine("collective", UtilAssistant.getStringRepresentationClass(c), new String[] {"Collective"});
		}else{
			dym.addTableLine("collective", "Collective", new String[] {"Collective"});
		}

		dym.addTableLine("universal", "Member", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role", "RoleMixin", "Mixin", "Category", "Relator", "Mode" });

		dm.open();		
	}

	@Override
	public Fix getSpecificFix() {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		ArrayList<Object[]> collectives = dym.getRowsOf("collective");
		ArrayList<Object[]> universals = dym.getRowsOf("universal");
		
		if(collectives == null || universals == null)
			return null;

		Classifier collective = getClassifier(collectives.get(0), x, y);
		Classifier universal  = getClassifier(universals.get(0), x+verticalDistance, y);

		Association memberOf = null;

		if(collective != null && universal != null){
			memberOf = (Association) outcomeFixer.createAssociationBetween(RelationStereotype.MEMBEROF, "", collective, universal).getAdded().get(0);
			
			fix.addAll(outcomeFixer.changePropertyMultiplicity(memberOf.getMemberEnd().get(0), "2..*"));
			fix.addAll(outcomeFixer.changePropertyMultiplicity(memberOf.getMemberEnd().get(1), "0..*"));
			
			fix.includeAdded(memberOf);
		}

		return fix;
	}

}
