package br.ufes.inf.nemo.pattern.impl;

import java.util.ArrayList;
import java.util.Arrays;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
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

public class RoleMixinDependentPattern extends AbstractPattern{

	public RoleMixinDependentPattern(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/RoleMixinDependent.png", "RoleMixin Dependent Pattern");
	}

	Classifier c;
	public RoleMixinDependentPattern(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/RoleMixinDependent.png", "RoleMixin Dependent Pattern");
		this.c = c;
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, RoleMixin.class, Relator.class, Role.class, Phase.class})));

		if(c instanceof RoleMixin){
			dym.addTableRigidLine("rolemixin", UtilAssistant.getStringRepresentationClass(c), new String[] {"RoleMixin"});	
		}else{
			dym.addTableLine("rolemixin", "RoleMixin", new String[] {"RoleMixin"});
		}
		
		dym.addTableLine("sortal", "Sortal 1", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
		dym.addTableLine("role", "Role 1", new String[] {"Role"});
		
		dym.addTableLine("sortal", "Sortal 2", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
		dym.addTableLine("role", "Role 2", new String[] {"Role"});
		
		dym.addTableLine("relator", "Relator", new String[] {"Relator"});
		
		dym.addTableLine("sortal", "Sortal 3", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
		dym.addTableLine("role", "Role 3", new String[] {"Role"});

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
		ArrayList<Object[]> relators = dym.getRowsOf("relator");

		if(sortals == null || roles == null || rolemixins == null || relators == null)
			return null;
		
		Classifier sortal1 		= getClassifier(sortals.get(0), x-120, y-157);
		Classifier sortal2 		= getClassifier(sortals.get(1), x-23, y-157);
		Classifier sortal3 		= getClassifier(sortals.get(2), x+150, y-70);

		Classifier role1 		= getClassifier(roles.get(0),x-120,y-70);
		Classifier role2 		= getClassifier(roles.get(1),x-23,y-70);
		Classifier role3 		= getClassifier(roles.get(2),x+150, y+28);

		Classifier rolemixin 	= getClassifier(rolemixins.get(0),x-71, y+28);

		Classifier relator 		= getClassifier(relators.get(0), x+40, y+170);

		Association leftMediation = null;
		Association rightMediation = null;
		Association material = null;
		Association derivation = null;

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

		if(sortal3 != null && role3 != null){
			fix.addAll(outcomeFixer.createGeneralization(role3,sortal3));	
		}

		if(rolemixin != null && role3 != null){
			material = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MATERIAL, "", rolemixin, role3).getAdded().get(0);
			fix.includeAdded(material);
		}

		if(relator != null){
			if(rolemixin != null){
				leftMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, rolemixin).getAdded().get(0);
				fix.includeAdded(leftMediation);
			}

			if(role3 != null){
				rightMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, role3).getAdded().get(0);
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
