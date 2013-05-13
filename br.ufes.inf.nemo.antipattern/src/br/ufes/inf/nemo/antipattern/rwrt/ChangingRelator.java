package br.ufes.inf.nemo.antipattern.rwrt;

import java.util.ArrayList;
import java.util.Iterator;

import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.InstantiationPattern;
import br.ufes.inf.nemo.antipattern.InstantiationPatternParameter;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ChangingRelator extends InstantiationPattern{
	RWRTAntiPattern rwrt;
	
	public ChangingRelator (RWRTAntiPattern rwrt){
		this.antipattern = rwrt;
		this.rwrt = rwrt;
		this.worldNumber = 2;
	}
	
	@Override
	public String predicate(ArrayList<InstantiationPatternParameter> parameter, OntoUMLParser parser) throws Exception {
		
		String name, predicate_rules, predicate;
		
		name = "changingRelator"+parser.getAlias(rwrt.getRelator());
		
		predicate_rules = "#World="+this.worldNumber;
		
		for (InstantiationPatternParameter ipp : parameter) {
			if (ipp.getElement() instanceof Property)
				predicate_rules += "\n\t#World."+parser.getAlias(((Property)ipp.getElement()).getType())+">="+ipp.getCardinality();
			if (ipp.getElement() instanceof Relator)
				predicate_rules += "\n\t#World."+parser.getAlias(ipp.getElement())+">="+ipp.getCardinality();
		}
		
		predicate_rules += "\n\tall w1,w2 : World |"; 
		
		boolean first = true;
		
		for (int i = 0; i < parameter.size(); i++) {
			
			InstantiationPatternParameter ipp = parameter.get(i);
			
			if (ipp.getElement() instanceof Property){
				Type t = ((Property)ipp.getElement()).getType();
				
				if (!first) predicate_rules += " and";
				else 		first = false;
				
				predicate_rules += " w1."+parser.getAlias(t)+" = w2."+parser.getAlias(t);
			}
		}
		
		for (InstantiationPatternParameter ipp : parameter) {
			
			if (ipp.getElement() instanceof Property){
				Property p = (Property) ipp.getElement();
				
				predicate_rules += "\n\tall disj w1,w2:World | all b:Object | (b in w1."+parser.getAlias(p.getType())+" and b in w2."+parser.getAlias(p.getType())+")";
				predicate_rules += " implies b."+parser.getAlias(parser.getRelatorEnd((Mediation)p.getAssociation()))+"[w1]!=b."+parser.getAlias(parser.getRelatorEnd((Mediation)p.getAssociation()))+"[w2]";
			}
		}
		
		
		
		
		predicate = AlloyConstructor.AlloyParagraph(name, predicate_rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", Integer.toString(worldNumber), AlloyConstructor.PRED)+"\n";
		
		return predicate;
		
	}

}
