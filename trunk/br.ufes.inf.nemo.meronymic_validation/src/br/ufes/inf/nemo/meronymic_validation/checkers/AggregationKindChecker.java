package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;

import RefOntoUML.AggregationKind;
import RefOntoUML.Meronymic;
import RefOntoUML.componentOf;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class AggregationKindChecker {
	OntoUMLParser parser;
	
	ArrayList<Meronymic> meronymicWithError;
	
	public AggregationKindChecker(OntoUMLParser parser) {
		this.parser = parser;
		meronymicWithError = null;
	}
	
	public void check(){
		
		if(meronymicWithError==null)
			meronymicWithError = new ArrayList<Meronymic>();
		else
			meronymicWithError.clear();
		
		for (componentOf compOf : parser.getAllInstances(componentOf.class)) {
			if(!isAggregationKindSet(compOf))
				meronymicWithError.add(compOf);
		}
	}
		
	public boolean isAggregationKindSet(Meronymic m){
		//both ends are NONE
		if(m.getMemberEnd().get(0).getAggregation()==AggregationKind.NONE && m.getMemberEnd().get(1).getAggregation()==AggregationKind.NONE)
			return false;
		
		//both ends are SET
		if(m.getMemberEnd().get(0).getAggregation()!=AggregationKind.NONE && m.getMemberEnd().get(1).getAggregation()!=AggregationKind.NONE)
			return false;
		
		return true;
	}

	public ArrayList<Meronymic> getMeronymicWithError() {
		if(meronymicWithError==null)
			check();
		
		return meronymicWithError;
	}
	
	
}
