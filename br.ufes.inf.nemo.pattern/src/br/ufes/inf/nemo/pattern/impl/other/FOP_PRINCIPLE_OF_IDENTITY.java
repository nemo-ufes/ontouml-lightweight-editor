package br.ufes.inf.nemo.pattern.impl.other;

import java.util.Arrays;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_PRINCIPLE_OF_IDENTITY extends AbstractPattern{
	private Classifier c;
	public FOP_PRINCIPLE_OF_IDENTITY(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser,x,y, "/resource/Principle_Identity_FOP.png", "Principle of Identity FOP");
		this.c = c;
	}

	public FOP_PRINCIPLE_OF_IDENTITY(OntoUMLParser parser, double x, double y) {
		super(parser,x,y, "/resource/Principle_Identity_FOP.png", "Principle of Identity FOP");
	}
	
	@Override
	public void runPattern() { 
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Quantity.class, Collective.class, SubKind.class, Role.class, Phase.class})));

		dym.setInitialItemCount(3);
		
		if(c instanceof SubKind){
			dym.addTableLine("general", "Sortal", new String[] {"Kind","Collective", "Quantity"});
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Subkind"});
			dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Subkind"});
		}else if(c instanceof Phase){
			dym.addTableLine("general", "Sortal", new String[] {"Kind","Collective", "Quantity", "Subkind", "Role", "Phase"});
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Phase"});
			dym.addTableLine("specific", "Phase counter part", new String[] {"Phase"});
		}else if(c instanceof Role){
			dym.addTableLine("general", "Sortal", new String[] {"Kind","Collective", "Quantity", "Subkind", "Role", "Phase"});
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Role"});
			dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Role"});
		}else{
			dym.addTableLine("general", "Sortal", new String[] {"Kind","Collective", "Quantity", "Subkind", "Role", "Phase"});
			dym.addTableLine("specific", "Specific", new String[] {"Subkind", "Phase", "Role"});
		}

		reuseGeneralizationSet(Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, Role.class, Phase.class}), Arrays.asList(new Class[]{SubKind.class, Role.class, Phase.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getPartitionFix();
		return fix;
	}

}
