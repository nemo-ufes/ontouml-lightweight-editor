package br.ufes.inf.nemo.pattern.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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

public class CharacterizationPattern extends AbstractPattern {

	private Classifier c = null;
	public CharacterizationPattern(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/Characterization.png", "Characterization Pattern");
	}

	public CharacterizationPattern(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/Characterization.png", "Characterization Pattern");
		this.c = c;
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
		
		set = parser.getAllInstances(Mixin.class);
		if(!set.isEmpty())
			hashTree.put("Mixin", UtilAssistant.getStringRepresentationClass(set));
		
		set = parser.getAllInstances(RoleMixin.class);
		if(!set.isEmpty())
			hashTree.put("RoleMixin", UtilAssistant.getStringRepresentationClass(set));
		
		set = parser.getAllInstances(Category.class);
		if(!set.isEmpty())
			hashTree.put("Category", UtilAssistant.getStringRepresentationClass(set));
		
		set = parser.getAllInstances(Mode.class);
		if(!set.isEmpty())
			hashTree.put("Mode", UtilAssistant.getStringRepresentationClass(set));

		dym.addHashTree(hashTree);

		if(c instanceof Mode){
			dym.addTableRigidLine("mode", UtilAssistant.getStringRepresentationClass(c), new String[] {"Mode"});
		}else{
			dym.addTableLine("mode", "Mode", new String[] {"Mode"});
		}

		dym.addTableLine("universal", "Universal", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role", "RoleMixin", "Mixin", "Category", "Relator"});

		dm.open();		
	}

	@Override
	public Fix getSpecificFix() {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		ArrayList<Object[]> modes = dym.getRowsOf("mode");
		ArrayList<Object[]> universals = dym.getRowsOf("universal");

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
