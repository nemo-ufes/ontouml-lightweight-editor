package br.ufes.inf.nemo.pattern.impl.partition;

import java.util.Arrays;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_PARTITION_SUBKIND extends AbstractPattern{

	public FOP_PARTITION_SUBKIND(OntoUMLParser parser, double x, double y) {
		super(parser, x, y,"/resource/Subkind_Partition_FOP.png", "Subkind Partition");
	}
	
	private Classifier c;
	public FOP_PARTITION_SUBKIND(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y,"/resource/Subkind_Partition_FOP.png", "Subkind Partition");
		this.c = c;
	}

	@Override
	public void runPattern(){
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, SubKind.class})));

		dym.addTableLine("general", "General", new String[] {"Kind","Collective", "Quantity", "Subkind"});

		if(c instanceof SubKind){
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"Subkind"});
		}else{
			dym.addTableLine("specific", "Specific 1", new String[] {"Subkind"});	
		}
		
		dym.addTableLine("specific", "Specific 2", new String[] {"Subkind"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"Subkind"});

		isPartitionPattern(Arrays.asList(new Class[]{Kind.class, Collective.class, Quantity.class, SubKind.class}), Arrays.asList(new Class[]{SubKind.class}));
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getPartitionFix();
		return fix;
	}
}
