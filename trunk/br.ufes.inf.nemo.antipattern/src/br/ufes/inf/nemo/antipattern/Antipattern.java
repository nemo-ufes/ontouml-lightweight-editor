package br.ufes.inf.nemo.antipattern;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class Antipattern {
	
	/*sets the elements to be transformed to alloy on the provided parser*/
	public abstract void setSelected(OntoUMLParser parser);
}
