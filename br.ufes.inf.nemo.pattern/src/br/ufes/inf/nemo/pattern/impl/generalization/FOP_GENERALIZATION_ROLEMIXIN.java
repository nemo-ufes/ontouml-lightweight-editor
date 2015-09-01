package br.ufes.inf.nemo.pattern.impl.generalization;

import java.util.Arrays;

import RefOntoUML.Classifier;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_GENERALIZATION_ROLEMIXIN extends AbstractPattern{
	private Classifier c;
	public FOP_GENERALIZATION_ROLEMIXIN(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/RoleMixin_Generalization_FOP.png", "RoleMixin Generalization");
		this.c = c;
	}

	public FOP_GENERALIZATION_ROLEMIXIN(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/RoleMixin_Generalization_FOP.png", "RoleMixin Generalization");
	}
	
	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{RoleMixin.class, Role.class})));

		dym.addTableLine("general", "General", new String[] {"RoleMixin"});

		if(c != null){
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"RoleMixin"});
		}else{
			dym.addTableLine("specific", "Specific 1", new String[] {"RoleMixin", "Role"});
		}
		dym.addTableLine("specific", "Specific 2", new String[] {"RoleMixin", "Role"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"RoleMixin", "Role"});

		isPartitionPattern(Arrays.asList(new Class[]{RoleMixin.class}), Arrays.asList(new Class[]{RoleMixin.class,Role.class}));

		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getGeneralizationFix();
		return fix;
	}

}
