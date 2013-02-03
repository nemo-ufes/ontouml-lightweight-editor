package br.ufes.inf.nemo.antipattern.util;

import java.util.ArrayList;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mode;
import RefOntoUML.Relator;
import RefOntoUML.Type;

public class SourceTargetAssociation {
	
	public static Type getSourceAlloy (Association a){
		Type t0,t1;
		
		t0 = a.getMemberEnd().get(0).getType();
		t1 = a.getMemberEnd().get(1).getType();
		
		if (a instanceof Mediation)
			if (t0 instanceof Relator)
				return t0;
			else
				return t1;
		else if (a instanceof Characterization)
			if (t0 instanceof Mode)
				return t0;
			else
				return t1;
		else if (a instanceof Meronymic)
			if (!a.getMemberEnd().get(0).getAggregation().equals(AggregationKind.NONE))
				return t0;
			else if (!a.getMemberEnd().get(1).getAggregation().equals(AggregationKind.NONE))
				return t1;
			else
				return t0;
		else
			return t0;
		
		/*if (a1 instanceof Mediation)
		if (a1.getMemberEnd().get(0).getType() instanceof Relator)
			a1SourceName = mapper.elementsMap.get(a1.getMemberEnd().get(0).getType());
		else
			a1SourceName = mapper.elementsMap.get(a1.getMemberEnd().get(1).getType());
	else if (a1 instanceof Characterization)
		if (a1.getMemberEnd().get(0).getType() instanceof Mode)
			a1SourceName = mapper.elementsMap.get(a1.getMemberEnd().get(0).getType());
		else
			a1SourceName = mapper.elementsMap.get(a1.getMemberEnd().get(1).getType());
	else if (a1 instanceof Meronymic)
		if (!a1.getMemberEnd().get(0).getAggregation().equals(AggregationKind.NONE))
			a1SourceName = mapper.elementsMap.get(a1.getMemberEnd().get(0).getType());
		else if (!a1.getMemberEnd().get(1).getAggregation().equals(AggregationKind.NONE))
			a1SourceName = mapper.elementsMap.get(a1.getMemberEnd().get(1).getType());
		else
			a1SourceName = mapper.elementsMap.get(a1.getMemberEnd().get(0).getType());
	else
		a1SourceName = mapper.elementsMap.get(a1.getMemberEnd().get(0).getType());
	*/
	}
	
	public static Type getTargetAlloy (Association a){
		Type t0;
		t0 = getSourceAlloy(a);
		if(a.getMemberEnd().get(0).getType().equals(t0))
				return a.getMemberEnd().get(1).getType();
		return a.getMemberEnd().get(0).getType();
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<Type> getRelatedAlloy (Association a){
		ArrayList<Type> result = new ArrayList();
		
		result.add(getSourceAlloy(a));
		result.add(getTargetAlloy(a));
		return result;
	}
	
	@SuppressWarnings("unused")
	public static int getUpperSourceCardinality (Association a) {
		Type t0;		
		Type source;
		Type t1;
		
		source = SourceTargetAssociation.getSourceAlloy(a);
		t0 = a.getMemberEnd().get(0).getType();
		t1 = a.getMemberEnd().get(1).getType();
		
		if (t0.equals(source))
			return a.getMemberEnd().get(0).getUpper();
		else 
			return a.getMemberEnd().get(1).getUpper();
	}
	
	@SuppressWarnings("unused")
	public static int getUpperTargetCardinality (Association a) {
		Type t0,t1, source;
		
		source = SourceTargetAssociation.getSourceAlloy(a);
		t0 = a.getMemberEnd().get(0).getType();
		t1 = a.getMemberEnd().get(1).getType();
		
		if (t0.equals(source))
			return a.getMemberEnd().get(1).getUpper();
		else 
			return a.getMemberEnd().get(0).getUpper();
	}
}
