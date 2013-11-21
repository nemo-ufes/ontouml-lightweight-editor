package br.ufes.inf.nemo.antipattern.relrig;

import java.util.ArrayList;

import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.InstantiationPattern;
import br.ufes.inf.nemo.antipattern.InstantiationPatternParameter;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class CreationalRelator extends InstantiationPattern{

	RelRigAntipattern rwrt;
	
	public CreationalRelator(RelRigAntipattern rwrt) {
		
		this.antipattern = rwrt;
		this.rwrt = rwrt;
		this.worldNumber = 2;
		
	}
		
	public String description(ArrayList<InstantiationPatternParameter> parameter) throws Exception{
		String descr = "Throughout the lifecycle of individuals of the type(s)";
		int i =0;
		
		for (InstantiationPatternParameter ipp : parameter) {
			
			if (ipp.getElement() instanceof Property) {
				Property p = (Property) ipp.getElement();
				
				if(i>=1&&i<parameter.size()-1)
					descr += ",";
				if(i==parameter.size()-1)
					descr += " and";
				
				descr += " "+p.getType().getName();
				
				i++;
			}
			else throw new Exception("Incompatible parameter type for generating description of ChangingRelator Instantiation Pattern");
		}
		descr += 	", the instances of the relator "+rwrt.getRelator().getName()+" which mediates them ARE THE SAME."+
					" This means that the event which created "+rwrt.getRelator().getName()+" also created the other types and stablished the connection between them.";

		return descr;
		
	}	
	
	@Override
	public String predicate(ArrayList<InstantiationPatternParameter> parameter, OntoUMLParser parser) throws Exception {
		
		String name, predicate_rules, predicate;
		
		name = "creationalRelator"+parser.getAlias(rwrt.getRelator());
		
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
				
				predicate_rules += " some w1."+parser.getAlias(t)+" & w2."+parser.getAlias(t);
			}
		}
		
		for (InstantiationPatternParameter ipp : parameter) {
			
			if (ipp.getElement() instanceof Property){
				Property p = (Property) ipp.getElement();
				
				predicate_rules += "\n\tall disj w1,w2:World | all b:Object | (b in w1."+parser.getAlias(p.getType())+" and b in w2."+parser.getAlias(p.getType())+")";
				predicate_rules += " implies b."+parser.getAlias(parser.getRelatorEnd((Mediation)p.getAssociation()))+"[w1]=b."+parser.getAlias(parser.getRelatorEnd((Mediation)p.getAssociation()))+"[w2]";
			}
		}
		
		predicate = AlloyConstructor.AlloyParagraph(name, predicate_rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", Integer.toString(worldNumber), AlloyConstructor.PRED)+"\n";
		
		return predicate;
		
	}

}
