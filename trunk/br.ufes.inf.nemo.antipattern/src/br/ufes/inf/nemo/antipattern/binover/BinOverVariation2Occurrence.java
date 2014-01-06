package br.ufes.inf.nemo.antipattern.binover;

import RefOntoUML.Association;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class BinOverVariation2Occurrence extends BinOverOccurrence {

	public BinOverVariation2Occurrence(Association a, BinOverVariation2Antipattern ap) {
		super(a,ap);
	}

	@Override
	public OntoUMLParser setSelected() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "[VAR 2]\n"+super.toString();
	}

}
