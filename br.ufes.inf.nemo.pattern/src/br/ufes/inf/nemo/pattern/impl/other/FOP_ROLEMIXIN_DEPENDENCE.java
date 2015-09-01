package br.ufes.inf.nemo.pattern.impl.other;

import java.util.ArrayList;
import java.util.Arrays;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Generalization;
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
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_ROLEMIXIN_DEPENDENCE extends AbstractPattern{

	public FOP_ROLEMIXIN_DEPENDENCE(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/RoleMixin_Dependence_FOP.png", "RoleMixin Dependent Pattern");
	}

	Classifier c;
	public FOP_ROLEMIXIN_DEPENDENCE(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/RoleMixin_Dependence_FOP.png", "RoleMixin Dependent Pattern");
		this.c = c;
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, RoleMixin.class, Relator.class, Role.class, Phase.class, RoleMixin.class, Category.class, Mixin.class})));

		dym.addTableLine("relator", "Relator", new String[] {"Relator"});
		
		if(c instanceof RoleMixin){
			dym.addTableRigidLine("rolemixin", UtilAssistant.getStringRepresentationClass(c), new String[] {"RoleMixin"});	
		}else{
			dym.addTableLine("rolemixin", "Mediated 1", new String[] {"RoleMixin"});
		}
		
		dym.addTableLine("member", "Member 1", new String[] {"Role", "RoleMixin"});
		dym.addTableLine("member", "Member N", new String[] {"Role", "RoleMixin"});

		dym.addTableLine("endurant", "Mediated 2", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role", "Category", "Mixin", "RoleMixin"});
		
		reuseGeneralizationSet(Arrays.asList(new Class[]{RoleMixin.class}), Arrays.asList(new Class[]{Role.class, RoleMixin.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();
		Fix _fix = new Fix();
		ArrayList<Generalization> generalizationList = new ArrayList<>();

		ArrayList<Object[]> members = dym.getRowsOf("member");
		ArrayList<Object[]> endurants = dym.getRowsOf("endurant");
		ArrayList<Object[]> rolemixins = dym.getRowsOf("rolemixin");
		ArrayList<Object[]> relators = dym.getRowsOf("relator");

		if(endurants == null || members == null || rolemixins == null || relators == null)
			return null;
		
		Classifier member1 		= getClassifier(members.get(0),x-120,y-70);
		Classifier member2 		= getClassifier(members.get(1),x-23,y-70);
		
		Classifier endurant 	= getClassifier(endurants.get(0),x+150, y+28);

		Classifier rolemixin 	= getClassifier(rolemixins.get(0),x-71, y+28);

		Classifier relator 		= getClassifier(relators.get(0), x+40, y+170);

		Association leftMediation = null;
		Association rightMediation = null;
		Association material = null;
		Association derivation = null;

		if(rolemixin != null){
			if(member1 != null){
				_fix.addAll(outcomeFixer.createGeneralization(member1, rolemixin));
				Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);		
				fix.addAll(_fix);
			}
			if(member2 != null){
				_fix = outcomeFixer.createGeneralization(member2, rolemixin);
				Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);
				fix.addAll(_fix);
			}
			if(generalizationList.size() == 2){
				fix.addAll(createGeneralizationSet(generalizationList, true, true, dym.getGeneralizationSetName()));
			}
		}

		if(rolemixin != null && endurant != null){
			material = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MATERIAL, "", rolemixin, endurant).getAdded().get(0);
			fix.includeAdded(material);
		}

		if(relator != null){
			if(rolemixin != null){
				leftMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, rolemixin).getAdded().get(0);
				fix.includeAdded(leftMediation);
			}

			if(endurant != null){
				rightMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, endurant).getAdded().get(0);
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
