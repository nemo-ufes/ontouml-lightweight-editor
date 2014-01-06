package br.ufes.inf.nemo.antipattern.binover;

import java.util.ArrayList;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.OverlappingTypesIdentificator;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class BinOverVariation2Antipattern extends Antipattern<BinOverVariation2Occurrence> {

	public BinOverVariation2Antipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	@Override
	public ArrayList<BinOverVariation2Occurrence> identify() {
		
		Set<Association> allAssociations = parser.getAllInstances(Association.class);
		
		for (Association a : allAssociations) {
			
			Classifier source = (Classifier) a.getMemberEnd().get(0).getType();
			Classifier target = (Classifier) a.getMemberEnd().get(1).getType();
			
			if(OverlappingTypesIdentificator.isVariation2(source, target)) {
				try { 
					super.occurrence.add(new BinOverVariation2Occurrence(a, this));
				} catch (Exception e) { e.printStackTrace();}
			}
		}
		return this.getOccurrences();
	}

}
