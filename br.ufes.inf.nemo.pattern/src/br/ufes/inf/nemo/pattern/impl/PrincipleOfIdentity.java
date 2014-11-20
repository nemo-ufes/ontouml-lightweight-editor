package br.ufes.inf.nemo.pattern.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
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
		
		dym.addHashTree(hashTree);
		dym.addTableLine("general", "Sortal", new String[] {"Kind","Collective", "Quantity"});
		
		if(c instanceof SubKind){
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Subkind"});
		}		

		if(c instanceof Phase){
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Phase"});
			dym.addTableLine("specific", "Phase counter part", new String[] {"Phase"});
		}

		if(c instanceof Role){
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Role"});
		}
		
		dm.open();
	}

	@Override
	public Fix getFix(){
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
				fix.addAll(outcomeFixer.createGeneralizationSet(generalizationList, true, true, "partition"+UtilAssistant.getCont()));
			}
		}catch(Exception e){
			//Do nothing, totally safe ;-)
		}
		return fix;
	}

}
