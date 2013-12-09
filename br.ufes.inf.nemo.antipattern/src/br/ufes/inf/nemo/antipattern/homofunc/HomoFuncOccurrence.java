package br.ufes.inf.nemo.antipattern.homofunc;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Mode;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.componentOf;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class HomoFuncOccurrence extends AntipatternOccurrence {
	
	private Classifier whole;
	private Property partEnd;
	
	public HomoFuncOccurrence(Association compOf, OntoUMLParser parser) throws Exception {
		super(parser);
		
		if (compOf==null)
			throw new NullPointerException("HomoFunc: null inputs!");
		if (compOf instanceof componentOf){
			
			//discovers which end represents the part and the whole			
			if(compOf.getMemberEnd().get(1).getAggregation()==AggregationKind.NONE){
				this.whole = (Classifier) compOf.getMemberEnd().get(0).getType();
				this.partEnd = compOf.getMemberEnd().get(1);
			} else{
				this.whole = (Classifier) compOf.getMemberEnd().get(1).getType();
				this.partEnd = compOf.getMemberEnd().get(0);
			}
			
		}else
			throw new Exception("HomoFunc: provided relation must be an instance of componentOf.");
		
		if(whole==null || partEnd==null)
			throw new NullPointerException("HomoFunc: null inputs!");
		
		if(whole instanceof Collective || whole instanceof Quantity || whole instanceof Relator || whole instanceof Mode)
			throw new Exception("HomoFunc: whole type not acceptable. Whole must be a functional complex");
		
		if (!(partEnd.getAssociation() instanceof componentOf))
			throw new Exception("HomoFunc: partEnd must refer to componentOf relation.");
		
		if (!partEnd.getOpposite().getType().equals(whole))
			throw new Exception("HomoFunc: partEnd must refer to componentOf relation that is connected to the provided whole");
		
		
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(this.whole);
		selection.add(this.partEnd.getAssociation());
		selection.add(this.partEnd.getType());
				
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		String result = 
				"Functional Complex: "+super.parser.getStringRepresentation(this.whole) + "\n" +
				"Part: "+super.parser.getStringRepresentation(partEnd);
		return result;
	}

}
