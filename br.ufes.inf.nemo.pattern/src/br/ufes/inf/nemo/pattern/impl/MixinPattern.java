package br.ufes.inf.nemo.pattern.impl;

import java.util.ArrayList;
import java.util.Arrays;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Role;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;

public class MixinPattern extends AbstractPattern{

	private Classifier c;

	public MixinPattern(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/MixinPattern.png", "Mixin Pattern");
	}

	public MixinPattern(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/MixinPattern.png", "Mixin Pattern");
		this.c = c;
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, Mixin.class, Role.class, Phase.class})));

		if(c instanceof Mixin){
			dym.addTableRigidLine("mixin", UtilAssistant.getStringRepresentationClass(c), new String[] {"Mixin"});
		}else{
			dym.addTableLine("mixin", "Mixin", new String[] {"Mixin"});
		}

		dym.addTableLine("sortal", "Sortal", new String[] {"Kind","Collective", "Quantity"});

		dym.addTableLine("antirigidsortal", "Anti Rigid Sortal", new String[] {"Role","Phase"});

		reuseGeneralizationSet(Arrays.asList(new Class[]{Mixin.class}), Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, Role.class, Phase.class}));
		
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();
		Fix _fix = new Fix();

		ArrayList<Generalization> generalizationList = new ArrayList<>();

		ArrayList<Object[]> mixins = dym.getRowsOf("mixin");
		ArrayList<Object[]> sortals = dym.getRowsOf("sortal");
		ArrayList<Object[]> antirigids = dym.getRowsOf("antirigidsortal");
		
		if(mixins == null || sortals == null || antirigids == null)
			return null;

		Classifier mixin 	= getClassifier(mixins.get(0), x, y);
		Classifier sortal 	= getClassifier(sortals.get(0), x, y+horizontalDistance);
		Classifier antirigid 	= getClassifier(antirigids.get(0),x+(1*verticalDistance)/3, y+horizontalDistance);


		if(mixin != null){
			if(sortal != null){
				_fix = outcomeFixer.createGeneralization(sortal, mixin);
				Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);
			}

			if(antirigid != null){
				_fix = outcomeFixer.createGeneralization(antirigid, mixin);
				Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);
			}
			if(sortal != null && antirigid != null){
				fix.addAll(_fix);
				fix.addAll(createGeneralizationSet(generalizationList, true, true, dym.getGeneralizationSetName()));
			}
		}

		return fix;
	}

}
