package br.ufes.inf.nemo.assistant;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import temp.old.GraphAssistant;
import RefOntoUML.Classifier;
import RefOntoUML.Kind;
import br.ufes.inf.nemo.assistant.astah2graph.AstahParser;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ModellingAssistant {

	//getFix e limpa toda vez
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
		//For each link to another pattern
		Iterator<GraphAssistant> graphs = hashGraph.values().iterator();
		while(graphs.hasNext()){
			GraphAssistant graph = graphs.next();
			graph.getManagerPatern().setOntoPaser(ontoParser);
		}

	}

	public Fix runPattern(Classifier elem){
		GraphAssistant graph = hashGraph.get(getStereotypeFromClassifier(elem));
		graph.getStart().run();		
		return graph.getManagerPatern().getFix();
	}

	private StereotypeOntoUMLEnum getStereotypeFromClassifier(Classifier elem){
		if(elem instanceof Kind){
			return StereotypeOntoUMLEnum.KIND;
		}
		return StereotypeOntoUMLEnum.KIND;
	}

}
