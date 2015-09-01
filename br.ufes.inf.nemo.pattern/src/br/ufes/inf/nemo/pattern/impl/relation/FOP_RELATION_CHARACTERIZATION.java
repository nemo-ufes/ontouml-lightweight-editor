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

public class FOP_RELATION_CHARACTERIZATION extends AbstractPattern {

	private Classifier c = null;
	public FOP_RELATION_CHARACTERIZATION(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/Characterization_FOP.png", "Characterization Relation Pattern");
	}

	public FOP_RELATION_CHARACTERIZATION(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/Characterization_FOP.png", "Characterization Relation Pattern");
		this.c = c;
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, Role.class, Phase.class, Relator.class, Mixin.class, RoleMixin.class, Category.class, Mode.class})));

		if(c instanceof Mode){
			dym.addTableRigidLine("mode", UtilAssistant.getStringRepresentationClass(c), new String[] {"Mode"});
		}else{
			dym.addTableLine("mode", "Mode", new String[] {"Mode"});
		}

		dym.addTableLine("universal", "Characterized", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role", "RoleMixin", "Mixin", "Category", "Relator", "Mode" });

		dm.open();		
	}

	@Override
	public Fix getSpecificFix() {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		ArrayList<Object[]> modes = dym.getRowsOf("mode");
		ArrayList<Object[]> universals = dym.getRowsOf("universal");
		
		if(modes == null || universals == null)
			return null;

		Classifier mode 	= getClassifier(modes.get(0), x, y);
		Classifier universal= getClassifier(universals.get(0), x+verticalDistance, y);

		Association characterization = null;

		if(mode != null && universal != null){
			characterization = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.CHARACTERIZATION, "", mode, universal).getAdded().get(0);
			fix.includeAdded(characterization);
		}

		return fix;
	}

}
