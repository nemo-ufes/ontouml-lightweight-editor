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
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;

public class PrincipleOfIdentity extends AbstractPattern{
	private Classifier c;
	public PrincipleOfIdentity(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser,x,y, "/resource/PrincipleOfIdentity_"+UtilAssistant.getStringRepresentationStereotype(c)+".png", "Principle of Identity Pattern");
		this.c = c;
	}

	@Override
	public void runPattern() { 
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, Role.class, Phase.class})));

		dym.setInitialItemCount(3);
		
		dym.addTableLine("general", "Sortal", new String[] {"Kind","Collective", "Quantity"});
		
		if(c instanceof SubKind){
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Subkind"});
			dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Subkind"});
		}		

		if(c instanceof Phase){
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Phase"});
			dym.addTableLine("specific", "Phase counter part", new String[] {"Phase"});
		}

		if(c instanceof Role){
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Role"});
			dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Role"});
		}

		reuseGeneralizationSet(Arrays.asList(new Class[]{Mixin.class}), Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, Role.class, Phase.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		try{
			Package root = parser.getModel();
			outcomeFixer = new OutcomeFixer(root);
			fix = new Fix();
			Fix _fix = null;

			//General
			ArrayList<Object[]> generals = dym.getRowsOf("general");
			Classifier general = getClassifier(generals.get(0), x, y);

			//Specifics
			ArrayList<Generalization> generalizationList = new ArrayList<>();
			ArrayList<Object[]> specifics = dym.getRowsOf("specific");
			
			if(generals == null || specifics == null)
				return null;
			
			int i = 0;
			Classifier specific;
			for(Object[] row : specifics){
				specific = getClassifier(row, x+(i*verticalDistance)/3, y+horizontalDistance);

				if(specific != null){
					if(general != null){
						_fix = outcomeFixer.createGeneralization(specific, general);
						Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
						generalizationList.add(generalization);
						fix.addAll(_fix);
					}
					i++;
				}
			}

			if(general != null && specifics.size() == 2){//not_activate = false
				fix.addAll(createGeneralizationSet(generalizationList, true, true, dym.getGeneralizationSetName()));
			}
		}catch(Exception e){
			//Do nothing, totally safe ;-)
		}
		return fix;
	}

}
