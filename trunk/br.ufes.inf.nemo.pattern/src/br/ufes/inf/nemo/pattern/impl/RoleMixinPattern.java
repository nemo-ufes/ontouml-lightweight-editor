package br.ufes.inf.nemo.pattern.impl;

import java.util.ArrayList;
import java.util.Arrays;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;

public class RoleMixinPattern extends AbstractPattern{

	public RoleMixinPattern(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/RoleMixin.png", "RoleMixin Pattern");
	}

	Classifier c;
	public RoleMixinPattern(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/RoleMixin.png", "RoleMixin Pattern");
		this.c = c;
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, RoleMixin.class, Role.class, Phase.class})));

		if(c instanceof RoleMixin){
			dym.addTableRigidLine("rolemixin", UtilAssistant.getStringRepresentationClass(c), new String[] {"RoleMixin"});	
		}else{
			dym.addTableLine("rolemixin", "RoleMixin", new String[] {"RoleMixin"});
		}
		
		dym.addTableLine("sortal", "Sortal 1", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
		dym.addTableLine("role", "Role 1", new String[] {"Role"});
		
		dym.addTableLine("sortal", "Sortal 2", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
		dym.addTableLine("role", "Role 2", new String[] {"Role"});

		reuseGeneralizationSet(Arrays.asList(new Class[]{RoleMixin.class}), Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, Role.class, Phase.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();
		Fix _fix = new Fix();
		ArrayList<Generalization> generalizationList = new ArrayList<>();

		ArrayList<Object[]> sortals = dym.getRowsOf("sortal");
		ArrayList<Object[]> roles = dym.getRowsOf("role");
		ArrayList<Object[]> rolemixins = dym.getRowsOf("rolemixin");

		Classifier sortal1 		= getClassifier(sortals.get(0), x-120, y-157);
		Classifier sortal2 		= getClassifier(sortals.get(1), x-23, y-157);

		Classifier role1 		= getClassifier(roles.get(0),x-120,y-70);
		Classifier role2 		= getClassifier(roles.get(1),x-23,y-70);

		Classifier rolemixin 	= getClassifier(rolemixins.get(0),x-71, y+28);

		if(sortal1 != null && role1 != null){
			fix.addAll(outcomeFixer.createGeneralization(role1,sortal1));
		}

		if(sortal2 != null && role2 != null){
			fix.addAll(outcomeFixer.createGeneralization(role2,sortal2));	
		}

		if(rolemixin != null){
			if(role1 != null){
				_fix.addAll(outcomeFixer.createGeneralization(role1, rolemixin));
				Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);		
				fix.addAll(_fix);
			}
			if(role2 != null){
				_fix = outcomeFixer.createGeneralization(role2, rolemixin);
				Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);
				fix.addAll(_fix);
			}
			if(generalizationList.size() == 2){
				fix.addAll(createGeneralizationSet(generalizationList, true, true, dym.getGeneralizationSetName()));
			}
		}

		return fix;
	}

}
