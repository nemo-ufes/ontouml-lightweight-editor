package br.ufes.inf.nemo.pattern.impl.generalization;

import java.util.Arrays;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_GENERALIZATION_MIXIN extends AbstractPattern{
	private Classifier c;
	public FOP_GENERALIZATION_MIXIN(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/Mixin_Generalization_FOP.png", "Mixin Generalization");
		this.c = c;
	}

	public FOP_GENERALIZATION_MIXIN(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/Mixin_Generalization_FOP.png", "Mixin Generalization");
	}
	
	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, SubKind.class, Phase.class, Role.class, Category.class, RoleMixin.class, Mixin.class})));

		if(c != null){
			dym.addTableRigidLine("general", UtilAssistant.getStringRepresentationClass(c), new String[] {"Mixin"});
		}else{
			dym.addTableLine("general", "General", new String[] {"Mixin"});
		}
		
		dym.addTableLine("specific", "Specific 1", new String[] {"Kind", "Collective", "Quantity", "Subkind", "Phase", "Role", "Category", "RoleMixin", "Mixin"});
		dym.addTableLine("specific", "Specific 2", new String[] {"Kind", "Collective", "Quantity", "Subkind", "Phase", "Role", "Category", "RoleMixin", "Mixin"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Kind", "Collective", "Quantity", "Subkind", "Phase", "Role", "Category", "RoleMixin", "Mixin"});

		isPartitionPattern(Arrays.asList(new Class[]{Mixin.class}), Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, SubKind.class, Phase.class, Role.class, Category.class, RoleMixin.class, Mixin.class}));

		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getGeneralizationFix();
		return fix;
	}

}
