package br.ufes.inf.nemo.assistant;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import RefOntoUML.Classifier;
import RefOntoUML.Package;
import br.ufes.inf.nemo.assistant.astah2graph.SWTAstahParser;
import br.ufes.inf.nemo.assistant.graph.GraphAssistant;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.assistant.wizard.WizardAssitant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

public class ModellingAssistant {

	private HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashGraph;

	/**
	 * Instantiating the class and runner the SWTAstahParser or by .jar execution
	 * or by eclipse running.
	 * */
	public ModellingAssistant(RefOntoUML.Package root) {
		String path = "Patterns_NEMO.asta";
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
			graph.getManagerPattern().setRefOntoUML(root);
		}
		p = root;
	}

	Package p;

	/**
	 * Run the pattern for the elem.
	 * Start and show the wizard
	 * */
	public Fix runPattern(Classifier elem){
		StereotypeOntoUMLEnum stereotype = UtilAssistant.getStereotypeFromClassifier(elem);
		try{
			if(stereotype != null){
				GraphAssistant graph = hashGraph.get(stereotype);
				graph.updateNodeList();
				graph.getManagerPattern().setClassSource(elem);

				Fix fix = null;

				Display display = Display.getDefault();	    	
				Shell shell = display.getActiveShell();
				WizardDialog wizardDialog = new WizardDialog(shell,new WizardAssitant(graph));

				if (wizardDialog.open() == Window.OK) {
					fix = graph.getManagerPattern().getFix();
				}
				System.out.println("REFonto: {");
				UtilAssistant.printRefOntoUML(p);
				System.out.println("}");
				return fix;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("REFonto: {");
		UtilAssistant.printRefOntoUML(p);
		System.out.println("}");
		return null;
	}
}
