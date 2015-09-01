package br.ufes.inf.nemo.pattern.impl.partition;

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

public class FOP_PARTITION_PHASE extends AbstractPattern{

	public FOP_PARTITION_PHASE(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/Phase_Partition_FOP.png", "Phase Partition");
	}

	private Classifier c;
	public FOP_PARTITION_PHASE(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/Phase_Partition_FOP.png", "Phase Partition");
		this.c = c;
	}
	
	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, SubKind.class, Phase.class, Role.class})));
		
		dym.addTableLine("general", "Sortal", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role"});
		
		if(c instanceof Phase){
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Phase"});
		}else{
			dym.addTableLine("specific", "Specific 1", new String[] {"Phase"});	
		}
		
		dym.addTableLine("specific", "Specific 2", new String[] {"Phase"});
		
		dym.setInitialItemCount(3);
		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Phase"});
		
		isPartitionPattern(Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, SubKind.class, Phase.class, Role.class}), Arrays.asList(new Class[]{Phase.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getPartitionFix();
		return fix;
	}

}
