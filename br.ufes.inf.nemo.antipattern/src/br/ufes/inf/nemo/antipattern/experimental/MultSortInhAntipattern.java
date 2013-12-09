package br.ufes.inf.nemo.antipattern.experimental;

import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class MultSortInhAntipattern extends Antipattern<MultSortInhOccurrence> {

	public MultSortInhAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	private static final String oclQuery = 
			"SortalClass.allInstances()" +
			"->select( x | " +
			"	x.oclAsType(Classifier).parents()" +
			"	->select( parent : Classifier | " +
			"		parent.oclIsKindOf(SortalClass)" +
			"	)->size()>1" +
			")";

	@Override
	public ArrayList<MultSortInhOccurrence> identify() {
		// TODO Auto-generated method stub
		return null;
	}

}
