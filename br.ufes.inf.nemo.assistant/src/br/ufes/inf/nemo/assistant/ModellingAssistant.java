package br.ufes.inf.nemo.assistant;

import java.util.HashMap;

import br.ufes.inf.nemo.assistant.astah2graph.AstahParser;
import br.ufes.inf.nemo.assistant.graph.GraphAssistant;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;

public class ModellingAssistant {

	public ModellingAssistant() {
		HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashTree = AstahParser.doParser("./Patterns.asta");
	}
}
