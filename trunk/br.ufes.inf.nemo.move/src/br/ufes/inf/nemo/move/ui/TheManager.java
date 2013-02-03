package br.ufes.inf.nemo.move.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.SemanticException;

import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlverificator.ProblemVerificator;
import br.ufes.inf.nemo.common.ontoumlverificator.SyntacticVerificator;
import br.ufes.inf.nemo.move.mvc.controller.OCLController;
import br.ufes.inf.nemo.move.mvc.controller.OntoUMLController;
import br.ufes.inf.nemo.move.mvc.controller.antipattern.list.AntiPatternListController;
import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.mvc.model.OCLModel;
import br.ufes.inf.nemo.move.mvc.model.OCLOptionsModel;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLModel;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLOptionsModel;
import br.ufes.inf.nemo.move.mvc.model.UMLModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.list.AntiPatternListModel;
import br.ufes.inf.nemo.move.mvc.view.OCLView;
import br.ufes.inf.nemo.move.mvc.view.OntoUMLView;
import br.ufes.inf.nemo.move.mvc.view.antipattern.list.AntiPatternListView;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLCheckBoxTree;
import br.ufes.inf.nemo.ocl2alloy.options.OCLOptions;

/**
 * This class represents a Manager to Models, View and Controllers of the main frame application.
 * 
 * @author John Guerson
 *
 */
public class TheManager {

	private TheFrame frame;
	
	private OntoUMLView ontoumlview;			
	private OCLView oclview;		
	private AntiPatternListView antipatternview;
	
	private OntoUMLModel ontoumlmodel;
	private UMLModel umlmodel;
	private OCLModel oclmodel;
	private AntiPatternListModel antipatternmodel;
	private OntoUMLOptionsModel ontoumlOptModel;
	private OCLOptionsModel oclOptModel;
	private AlloyModel alloymodel;
			
	/**
	 * Set Manager from OntoUML Package, String COnstraints and Alloy Path.
	 * 
	 * @param frame
	 * @param model
	 * @param oclConstraints
	 * @param alsPath
	 * @throws IOException
	 */
	public void setManager (TheFrame frame, RefOntoUML.Package model, String oclConstraints, String alsPath) throws IOException
	{
		ontoumlmodel.setOntoUML(model);
		
		alloymodel.setAlloyModel(alsPath);
		umlmodel.setUMLModel(alsPath.replace(".als",".uml"));    	
    	
		ontoumlview.setPath(ontoumlmodel.getOntoUMLPath(),ontoumlmodel.getOntoUMLModelInstance());
    	ontoumlview.setModelTree(ontoumlmodel.getOntoUMLModelInstance(),ontoumlmodel.getOntoUMLParser());    	
    	ontoumlview.validate();
    	ontoumlview.repaint();    	
	}	
	
	/**
	 * Creates a Manager to Models, Views and Controllers of the main frame application.
	 * 
	 * @param frame
	 */
	public TheManager(TheFrame frame)
	{
		this.frame = frame;
		
		createModels();
		createViews();
		createControllers();
	}	
	
	/**
	 * Create Model layer - MVC Design Pattern
	 */
	public void createModels()
	{
		ontoumlmodel = new OntoUMLModel();
		umlmodel = new UMLModel();
		oclmodel = new OCLModel();
		antipatternmodel = new AntiPatternListModel();
		ontoumlOptModel = new OntoUMLOptionsModel();
		oclOptModel = new OCLOptionsModel();
		alloymodel = new AlloyModel();
	}
	
	/**
	 * Create View Layer - MVC Design Pattern
	 */
	public void createViews()
	{
		ontoumlview = new OntoUMLView(ontoumlmodel,frame);
		oclview = new OCLView(oclmodel,frame);
		antipatternview = new AntiPatternListView(antipatternmodel,frame);
	}
	
	/**
	 * Create Controllers Layer - MVC Design Pattern
	 */
	public void createControllers()
	{
		new OntoUMLController(ontoumlview,ontoumlmodel);		
		new OCLController(oclview,oclmodel);
		new AntiPatternListController(antipatternmodel, antipatternview);
	}
	
	/**
	 * Set AntiPattern List Model.
	 * 
	 * @param antipatternListModel
	 */
	public void setAntiPatternListModel(AntiPatternListModel antipatternListModel) 
	{ 
		this.antipatternmodel = antipatternListModel; 
		this.antipatternview.setAntiPatternListModel(antipatternListModel);		
	}	
	
	/** Get AntiPattern Model */
	public AntiPatternListModel getAntiPatternListModel() { 
		return antipatternmodel; 
	}
	/** Get OntoUML Model */
	public OntoUMLModel getOntoUMLModel() { 
		return ontoumlmodel; 
	}
	/** Get UML Model */
	public UMLModel getUMLModel() { 
		return umlmodel; 
	}
	/** Get OCL Model */
	public OCLModel getOCLModel() { 
		return oclmodel; 
	}
	/** Get Alloy Model */
	public AlloyModel getAlloyModel() { 
		return alloymodel; 
	}
	/** Get Option Model */
	public OntoUMLOptionsModel getOntoUMLOptionModel() { 
		return ontoumlOptModel; 
	}	
	/** Get Option Model */
	public OCLOptionsModel getOCLOptionModel() { 
		return oclOptModel; 
	}		
	/** Get AntiPattern View */
	public AntiPatternListView getAntiPatternListView() { 
		return antipatternview; 
	}	
	/** Get OntoUML View */
	public OntoUMLView getOntoUMLView() { 
		return ontoumlview; 
	}
	/** Get OCL View */
	public OCLView getOCLView() { 
		return oclview; 
	}
	
	public String doModelDiagnostic()
	{
		String log = new String();		
		if (ontoumlmodel.getOntoUMLParser()==null) 
		{
			frame.showInformationMessageDialog("Verify Model", "First you need to load your Model !");
			return "";
		}		
		
		// do auto selection completion.
		doAutoSelectionCompletion(OntoUMLParser.NO_HIERARCHY);
		
		// diagnose model
		ProblemVerificator verificator = new ProblemVerificator();
		log = verificator.getDiagnostic(ontoumlmodel.getOntoUMLParser());
						
		return log;
	}
	
	/**
	 * Model Verification.
	 * 
	 * @return
	 */
	public String doModelVerification()
	{
		String log = new String();		
		if (ontoumlmodel.getOntoUMLParser()==null) 
		{
			frame.showInformationMessageDialog("Verify Model", "First you need to load your Model !");
			return "";
		}		
		
		// do auto selection completion.
		doAutoSelectionCompletion(OntoUMLParser.NO_HIERARCHY);
		
		// verify model
		log = SyntacticVerificator.verify(ontoumlmodel.getOntoUMLModelInstance());
		
		return log;
	}
	
	/**
	 * Do Auto Selection Completion.
	 * 
	 * @param option
	 * @return
	 */
	public String doAutoSelectionCompletion(int option)
	{		
		if (ontoumlmodel.getOntoUMLParser()==null || ontoumlview.getModelTree()==null) 
		{
			frame.showInformationMessageDialog("Verify Model", "First you need to load your Model !");
			return "";
		}	
		
		// get elements from the tree
		List<EObject> selected = OntoUMLCheckBoxTree.getCheckedElements(ontoumlview.getModelTree());
		
		// get added elements from the auto selection completion
		ontoumlmodel.getOntoUMLParser().selectThisElements((ArrayList<EObject>)selected,true);		
		List<EObject> added = ontoumlmodel.getOntoUMLParser().autoSelectDependencies(option,false);
		
		// show wich elements were added to selection
		String msg = new String();
		if (added.size()>0) msg = "The following elements were include in selection:\n\n";
		for(EObject o: added)
		{
			msg += ""+ontoumlmodel.getOntoUMLParser().getStringRepresentation(o)+".\n";
		}
		if (added.size()==0) msg += "No elements to include in selection.";		
		
		// update tree adding the elements...
		selected.removeAll(added);
		selected.addAll(added);
		OntoUMLCheckBoxTree.checkElements(selected, true, ontoumlview.getModelTree());		
    	ontoumlview.getModelTree().updateUI();	
    	
    	// create a new model package from selected elements in the model.
    	ontoumlmodel.setOntoUMLPackage(ontoumlmodel.getOntoUMLParser().createPackageFromSelections(new Copier()));
		
    	return msg;
	}

	/**
	 * Do OntoUML Model Transformation into Alloy.
	 */
	public void doOntoUMLToAlloy()
	{
		if (ontoumlmodel.getOntoUMLParser()==null) 
		{
			frame.showInformationMessageDialog("OntoUML to Alloy", "First you need to load your Model !");
			return ;
		}
		
		// do auto selection completion.
		doAutoSelectionCompletion(OntoUMLParser.NO_HIERARCHY); 
		
		// warning: no substance sortal.
		if (ontoumlmodel.getOntoUMLParser().getAllInstances(SubstanceSortal.class).isEmpty() && ontoumlOptModel.getOptions().identityPrinciple) 
		{
			ontoumlOptModel.getOptions().identityPrinciple=false;
			String msg = "No Substance Sortals in the model.\nThe Identity Principle Axiom should not be enforced. This option was unchecked.\n ";
			frame.showWarningMessageDialog("No Substance Sortal",msg);
		}
		
		try {			
			
			// here the model is transformed into alloy according to the options...
			alloymodel.setAlloyModel(ontoumlmodel,ontoumlOptModel);			
			
		} catch (Exception e) {
			frame.showErrorMessageDialog("OntoUML to Alloy",e.getLocalizedMessage());					
			e.printStackTrace();
		}
	}

	/**
	 * Do OntoUML Model Transformation into UML.
	 */
	public void doOntoUMLToUML()
	{
		if (ontoumlmodel.getOntoUMLParser()==null) 
		{
			frame.showInformationMessageDialog("OntoUML to UML", "First you need to load your Model !");
			return ;
		}
		
		// do auto selection completion.
		doAutoSelectionCompletion(OntoUMLParser.NO_HIERARCHY); 
			
		// UML model path.
		String umlPath = ontoumlmodel.getOntoUMLPath().replace(".refontouml",".uml");
		
		try{
			
			// here the model is transformed into UML...
			umlmodel.setUMLModel(umlPath,ontoumlmodel.getOntoUMLParser());			
									
		}catch (Exception e) {			
			frame.showErrorMessageDialog("OntoUML to UML",e.getLocalizedMessage());					
			e.printStackTrace();
		}
	}
		
	/**
	 * Parse Constraints from the View according the model tree.
	 * 
	 * @param showSuccesfullyMessage
	 */
	public void ParseOCL(boolean showSuccesfullyMessage)
	{
		if (ontoumlmodel.getOntoUMLParser()==null) 
		{
			frame.showInformationMessageDialog("Parse OCL", "First you need to load your Model !");
			return ;
		}
		
		// do auto selection completion.
		doAutoSelectionCompletion(OntoUMLParser.NO_HIERARCHY); 
		
		try {
		
			// set parser from the editor view.
			oclmodel.setParser(oclview.parseConstraints());			
						
			// set options from the parser.
			oclOptModel.setOCLOptions(new OCLOptions(oclmodel.getOCLParser()));

			// show Message
			String msg =  "Your Constraints are Syntactically Correct !\n";
			if(showSuccesfullyMessage) frame.showSuccessfulMessageDialog("Parsing Constraints",msg);
						
    	}catch(SemanticException e2){
    		frame.showErrorMessageDialog("OCL Semantic Error",  "OCL Parser : "+e2.getMessage());    		
			e2.printStackTrace();	
			
    	}catch(ParserException e1){
    		frame.showErrorMessageDialog("OCL Parser Error", "OCL Parser : "+e1.getMessage());    			
			e1.printStackTrace();    	
			
		}catch(IOException e3){
			frame.showErrorMessageDialog("IO Error", e3.getMessage());						
			e3.printStackTrace();
			
		}catch(Exception e4){
			frame.showErrorMessageDialog("Unexpected Error", e4.getMessage());			
			e4.printStackTrace();
		}
		
	}

	/**
	 * Do OCL Constraints Transformation into Alloy.
	 */
	public void doOCLToAlloy()
	{
		if (ontoumlmodel.getOntoUMLParser()==null) 
		{
			frame.showInformationMessageDialog("OCL to Alloy", "First you need to load your Model !");
			return ;
		}
		if (oclmodel.getOCLParser()==null) 
		{
			frame.showInformationMessageDialog("OCL to Alloy", "First you need to parse the Constraints !");
			return ;
		}
		
		try {						
						
			// here the constraints are transformed into Alloy...
			String logMessage = alloymodel.addConstraints(ontoumlmodel, oclmodel,oclOptModel);			
			
			// show warnings 
			if (!logMessage.isEmpty() && logMessage!=null)
			{
				frame.showWarningMessageDialog("OCL to Alloy",logMessage);					
			}
			
		} catch (Exception e) {			
			frame.showErrorMessageDialog("OCL to Alloy",e.getLocalizedMessage());					
			e.printStackTrace();
		}
	}

	/**
	 * Do Opening Alloy Analyzer
	 * 
	 * @param alsPath
	 * @param alsOutDirectory
	 */
	public void doOpeningAlloy (boolean showAnalyzer, int cmdIndexToExecute) 
	{		
		if (alloymodel.getAlloyPath().isEmpty() || alloymodel.getAlloyPath()==null) return;
		
		try {

			frame.getAlloyAnalyzer().setTheme(alloymodel.getDirectory() + "standart_theme.thm");
			
			frame.getAlloyAnalyzer().setVisible(showAnalyzer);
			
			frame.getAlloyAnalyzer().doOpenFile(alloymodel.getAlloyPath());
    	    
			if (cmdIndexToExecute>=0)frame.getAlloyAnalyzer().doRun(cmdIndexToExecute);
			
		} catch (Exception e) {			
			frame.showErrorMessageDialog("Open Alloy Analyzer",e.getLocalizedMessage());					
			e.printStackTrace();
		}	
		
	}		
}
