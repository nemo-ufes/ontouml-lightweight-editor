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

	private HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashGraph;

	public ModellingAssistant(RefOntoUML.Package root) {
//		String path = "Patterns.asta";
//		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);	
//		if(is == null) {
//			//Runtime (eclipse)
//			hashGraph = SWTAstahParser.doParser("src/"+path);
//		}else{
//			//Running in .jar
//			hashGraph = SWTAstahParser.doParser(is);	
//		}
//
//		Iterator<GraphAssistant> graphs = hashGraph.values().iterator();
//		while(graphs.hasNext()){
//			GraphAssistant graph = graphs.next();
//			graph.getManagerPattern().setRefOntoUML(root);
//		}

	}

	public Fix runPattern(Classifier elem){
		StereotypeOntoUMLEnum stereotype = getStereotypeFromClassifier(elem);

		if(stereotype != null){
			GraphAssistant graph = hashGraph.get(stereotype);
			graph.updateNodeList();
			graph.getManagerPattern().setClassifierOrigem(elem);

			Fix fix = null;

			Display display = Display.getDefault();	    	
			Shell shell = display.getActiveShell();
			WizardDialog wizardDialog = new WizardDialog(shell,new WizardAssitant(graph));

			if (wizardDialog.open() == Window.OK) {
				fix = graph.getManagerPattern().getFix();
			}

			return fix;
		}
		
		return null;
	}

	public static StereotypeOntoUMLEnum getStereotypeFromClassifier(Classifier elem){
		if(elem instanceof Kind){
			return StereotypeOntoUMLEnum.KIND;
		}else if(elem instanceof Relator){
			return StereotypeOntoUMLEnum.RELATOR;
		}
		return null;
	}
}
