package br.ufes.inf.nemo.assistant;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import RefOntoUML.Classifier;
import RefOntoUML.Package;
import br.ufes.inf.nemo.assistant.graph.GraphAssistant;
import br.ufes.inf.nemo.assistant.manager.ManagerPattern;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.assistant.wizard.WizardAssitant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

public class ModellingAssistant {

	public static void main(String[] args) {
		System.out.println("hello world!");
	}
	
	
	public static final String serializedObjectPath = "graph_assistant.ser";
	
	private HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashGraph;
	@SuppressWarnings("unused")
	private Package currentRoot;

	/**
	 * Instantiating the class and runner the SWTAstahParser or by .jar execution
	 * or by eclipse running.
	 * */
	public ModellingAssistant(RefOntoUML.Package root) {
		readBynaryFile();
		
		Iterator<GraphAssistant> graphIterator = hashGraph.values().iterator();
		while(graphIterator.hasNext()){
			GraphAssistant graph = graphIterator.next();
			if(graph.getManagerPattern() == null)
				graph.setManagerPattern(new ManagerPattern());
			graph.getManagerPattern().setRefOntoUML(root);
		}
		currentRoot = root;
	}
	
	@SuppressWarnings("unchecked")
	private void readBynaryFile(){
		try{
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(serializedObjectPath);
			
			ObjectInputStream in = null;
			
			if(is == null){
				is = this.getClass().getClassLoader().getResourceAsStream("src/"+serializedObjectPath);
				in = new ObjectInputStream(is);
			}else{
				in = new ObjectInputStream(is);
			}
			
			hashGraph = (HashMap<StereotypeOntoUMLEnum, GraphAssistant>) in.readObject();
			in.close();
			is.close();
		}catch(Exception i){
			i.printStackTrace();
			return;
		}
	}
	
	private void bringToFront(final Shell shell) {
	    shell.getDisplay().asyncExec(new Runnable() {
	        public void run() {
	            shell.forceActive();
	        }
	    });
	}
	
	private class WizardDialogAssistant extends WizardDialog {

		public WizardDialogAssistant(Shell parentShell, IWizard newWizard) 
		{
			super(parentShell, newWizard);
			setDefaultImage(new Image(Display.getDefault(),ModellingAssistant.class.getResourceAsStream("/resource/wizard36.png")));
		}
		
		@Override
		public void create() 
		{
		    super.create();
		    setShellStyle(SWT.TITLE);
		    bringToFront(getShell());
		    getShell().setText("Modeling Assistant");	    
		}		
	}
	
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

				graph.getManagerPattern().setFix(new Fix());

				Fix fix = null;

				Display display = Display.getDefault();				
				Shell shell = display.getActiveShell();				
				WizardDialogAssistant wizardDialog = new WizardDialogAssistant(shell,new WizardAssitant(graph));				
				wizardDialog.create();
				if (wizardDialog.open() == Window.OK) {
					fix = graph.getManagerPattern().getFix();
				}
				
				return fix;
			}else{
//				System.out.println("stereotype not treated yet");
			}
		}catch(Exception e){
			//e.printStackTrace();
//			System.out.println("pattern not treated yet");
		}
		return null;
	}
}
