package br.ufes.inf.nemo.assistant;

import java.io.InputStream;
import java.util.HashMap;

import br.ufes.inf.nemo.assistant.astah2graph.AstahParser;
import br.ufes.inf.nemo.assistant.graph.GraphAssistant;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ModellingAssistant {
	private HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashGraph;
	
	public ModellingAssistant(OntoUMLParser ontoParser) {
		
		String path = "Patterns.asta";
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);	
		if(is == null) {
			//Runtime (eclipse)
			hashGraph = AstahParser.doParser("src/"+path);
		}else{
			//Running in .jar
			hashGraph = AstahParser.doParser(is);	
		}		
	}
	
	public void startPattern(StereotypeOntoUMLEnum stereotype){
		hashGraph.get(stereotype).getStart().run();
	}
}
