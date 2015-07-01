package br.ufes.inf.nemo.pattern.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.Quantity;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

public class RigidWeakSupplementation extends AbstractPattern {
	public RigidWeakSupplementation(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/RigidWeakSupplementation.png", "Rigid Weak Supplementation");
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class})));
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

		dym.addHashTree(hashTree);

		dym.addTableLine("type", "Type", new String[] {"Kind","Collective", "Quantity", "Subkind"});
		dym.addTableLine("complex", "Complex Type", new String[] {"Subkind"});
		dym.addTableLine("atomic", "Atomic Type", new String[] {"Subkind"});

		reuseGeneralizationSet(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class}), Arrays.asList(new Class[]{SubKind.class}));

		dm.open();		
	}

	@Override
	public Fix getSpecificFix() {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();
		Fix _fix = new Fix();
		
		ArrayList<Generalization> generalizationList = new ArrayList<>();
		
		ArrayList<Object[]> types = dym.getRowsOf("type");
		ArrayList<Object[]> complexes = dym.getRowsOf("complex");
		ArrayList<Object[]> atomics = dym.getRowsOf("atomic");
		
		if(types == null || complexes == null || atomics == null)
			return null;

		Classifier type 	= getClassifier(types.get(0), x, y);
		Classifier complex 	= getClassifier(complexes.get(0), x-horizontalDistance/2, y+horizontalDistance*0.6);
		Classifier atomic 	= getClassifier(atomics.get(0),x+(1*verticalDistance)/4, y+horizontalDistance*0.6);

		Association componentOf = null;
		
		
		if(type != null){
			
			if(complex != null){
				_fix.addAll(outcomeFixer.createGeneralization(complex, type));
				Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);		
				fix.addAll(_fix);
			}
			if(atomic != null){
				_fix = outcomeFixer.createGeneralization(atomic, type);
				Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);
				fix.addAll(_fix);
			}
			if(generalizationList.size() == 2){
				fix.addAll(createGeneralizationSet(generalizationList, true, true, dym.getGeneralizationSetName()));
			}
		}

		if(complex != null && type != null){
			componentOf = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.COMPONENTOF, "", complex, type).getAdded().get(0);
			
			((Meronymic)(componentOf)).setIsShareable(true);
			
			outcomeFixer.changePropertyMultiplicity(componentOf.getMemberEnd().get(0), "0..1");//complex
			outcomeFixer.changePropertyMultiplicity(componentOf.getMemberEnd().get(1), "2..*");//type
			
			fix.includeAdded(componentOf);
		}

		return fix;
	}

}
