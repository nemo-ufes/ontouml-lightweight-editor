package br.ufes.inf.nemo.pattern.impl.partition;

import java.util.Arrays;

import RefOntoUML.Classifier;
import RefOntoUML.RoleMixin;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_PARTITION_ROLEMIXIN extends AbstractPattern{
	private Classifier c;
	public FOP_PARTITION_ROLEMIXIN(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/RoleMixin_Partition_FOP.png", "RoleMixin Partition");
		this.c = c;
	}

	public FOP_PARTITION_ROLEMIXIN(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/RoleMixin_Partition_FOP.png", "RoleMixin Partition");
	}
	
	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{RoleMixin.class})));

		dym.addTableLine("general", "General", new String[] {"RoleMixin"});

		if(c != null){
			dym.addTableRigidLine("specific", UtilAssistant.getStringRepresentationClass(c), new String[] {"RoleMixin"});
		}else{
			dym.addTableLine("specific", "Specific 1", new String[] {"RoleMixin"});
		}
		dym.addTableLine("specific", "Specific 2", new String[] {"RoleMixin"});

		dym.setInitialItemCount(3);

		dym.setAddLineButtonAction("specific", "Specific N", new String[] {"RoleMixin"});

		isPartitionPattern(Arrays.asList(new Class[]{RoleMixin.class}), Arrays.asList(new Class[]{RoleMixin.class}));

		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		getPartitionFix();
		return fix;
	}

}
