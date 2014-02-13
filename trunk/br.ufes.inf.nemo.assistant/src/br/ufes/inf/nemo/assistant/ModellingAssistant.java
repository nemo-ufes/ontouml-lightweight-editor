package br.ufes.inf.nemo.assistant;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import RefOntoUML.Classifier;
import RefOntoUML.Kind;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.assistant.astah2graph.SWTAstahParser;
import br.ufes.inf.nemo.assistant.graph.GraphAssistant;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.assistant.wizard.WizardAssitant;
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
			hashGraph = SWTAstahParser.doParser("src/"+path);
		}else{
			//Running in .jar
			hashGraph = SWTAstahParser.doParser(is);	
		}

		Iterator<GraphAssistant> graphs = hashGraph.values().iterator();
		while(graphs.hasNext()){
			GraphAssistant graph = graphs.next();
			graph.getManagerPattern().setOntoPaser(ontoParser);
		}

	}

	public Fix runPattern(Classifier elem){
		GraphAssistant graph = hashGraph.get(getStereotypeFromClassifier(elem));
//		GraphAssistant graph = hashGraph.get(StereotypeOntoUMLEnum.RELATOR);
		graph.updateNodeList();
		graph.getManagerPattern().setClassifierOrigem(elem);
		
		System.out.println(graph.toString());
		
		Fix fix = null;
		
		Display display = Display.getDefault();	    	
		Shell shell = display.getActiveShell();
		WizardDialog wizardDialog = new WizardDialog(shell,new WizardAssitant(graph));
		
		if (wizardDialog.open() == Window.OK) {
			fix = graph.getManagerPattern().getFix();
			System.out.println("added: "+fix.getAddedString());
		}
		
		return fix;
	}

	private StereotypeOntoUMLEnum getStereotypeFromClassifier(Classifier elem){
		if(elem instanceof Kind){
			return StereotypeOntoUMLEnum.KIND;
		}else if(elem instanceof Relator){
			return StereotypeOntoUMLEnum.RELATOR;
		}
		return StereotypeOntoUMLEnum.RELATOR;
	}

}
