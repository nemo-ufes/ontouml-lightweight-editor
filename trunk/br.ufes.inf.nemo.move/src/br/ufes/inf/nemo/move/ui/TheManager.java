package br.ufes.inf.nemo.move.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlverificator.ModelDiagnostician;
import br.ufes.inf.nemo.common.ontoumlverificator.SyntacticVerificator;
import br.ufes.inf.nemo.common.resource.ResourceUtil;
import br.ufes.inf.nemo.move.mvc.controller.AntiPatternListController;
import br.ufes.inf.nemo.move.mvc.controller.OCLController;
import br.ufes.inf.nemo.move.mvc.controller.OntoUMLController;
import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.mvc.model.AntiPatternListModel;
import br.ufes.inf.nemo.move.mvc.model.OCLModel;
import br.ufes.inf.nemo.move.mvc.model.OCLOptionsModel;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLModel;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLOptionsModel;
import br.ufes.inf.nemo.move.mvc.model.UMLModel;
import br.ufes.inf.nemo.move.mvc.view.AntiPatternListView;
import br.ufes.inf.nemo.move.mvc.view.OCLView;
import br.ufes.inf.nemo.move.mvc.view.OntoUMLView;
import br.ufes.inf.nemo.move.tree.ontouml.OntoUMLCheckBoxTree;
import br.ufes.inf.nemo.move.ui.dialog.AntiPatternListDialog;
import br.ufes.inf.nemo.ocl2alloy.OCLOptions;
import br.ufes.inf.nemo.ontouml2alloy.Onto2AlloyOptions;

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
    	
    	doModelDiagnostic();
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
		new OCLController(oclview,oclmodel);
		new AntiPatternListController(antipatternmodel, antipatternview);
	}
	
	public void createOntoUMLController()
	{
		new OntoUMLController(ontoumlview,ontoumlmodel);
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
	
	public void doModelDiagnostic()
	{
		if (ontoumlmodel.getOntoUMLParser()==null) 
		{
			frame.showInformationMessageDialog("Diagnostic", "First you need to open a model!");
			return;
		}		
		
		// do auto selection completion.
		doAutoSelectionCompletion(OntoUMLParser.NO_HIERARCHY);
		
		// Warnings showed
    	ModelDiagnostician verificator = new ModelDiagnostician();
		frame.getWarnings().setData(
			verificator.getWarningsMatrixFormat(ontoumlmodel.getOntoUMLParser()),
			verificator.getWarnings()
		);
		if (verificator.getWarnings()>0)
		{
			frame.getWarnings().selectRow(0);
			frame.focusOnWarnings();			
		}
		
		// Errors showed	    	
		frame.getErrors().setData(
			verificator.getErrorsMatrixFormat(ontoumlmodel.getOntoUMLParser()),
			verificator.getErrors()
		);
		if (verificator.getErrors()>0) 
		{  
			frame.getErrors().selectRow(0); frame.focusOnErrors(); 
		}		
		
		if (verificator.getErrors()>0) frame.showErrorMessageDialog("Errors", "Hey ! Your model has some Errors ...\nPlease, Fix them before continue.\n\n");		
		else if (verificator.getWarnings()>0) frame.showWarningMessageDialog("Warnings", "Hey ! Your model has some Warnings ...\nPlease, Be Aware of them before continue.\n\n");
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
		List<EObject> selected = ontoumlview.getModelTree().getCheckedElements();
		
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
		
		ontoumlview.getModelTree().checkElements(selected, true);		
			
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
	public void doParseOCL(boolean showSuccesfullyMessage)
	{
		if (ontoumlmodel.getOntoUMLParser()==null) 
		{
			frame.showInformationMessageDialog("Parse OCL", "First you need to open the Model.");
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
			String msg =  "Constraints are syntactically correct.\n";
			if(showSuccesfullyMessage) frame.showSuccessfulMessageDialog("Parsing OCL",msg);
						
    	//}catch(SemanticException e2){
    	//	frame.showErrorMessageDialog("OCL Semantic Error",  "OCL Parser : "+e2.getLocalizedMessage());    		
		//	e2.printStackTrace();	
			
    	}catch(ParserException e1){
    		frame.showErrorMessageDialog("OCL Parser Error", "OCL Parser : "+e1.getLocalizedMessage());    			
			e1.printStackTrace();    	
			
		}catch(IOException e3){
			frame.showErrorMessageDialog("IO Error", e3.getLocalizedMessage());						
			e3.printStackTrace();
			
		}catch(Exception e4){
			frame.showErrorMessageDialog("Unexpected Error", e4.getLocalizedMessage());			
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
	
	public void doOpenOntoUML ()
	{
		try{
	     	
      	String path = FileChoosersUtil.getOntoUMLPathLocation(frame,ontoumlmodel.getOntoUMLPath());
    				
      	if (path==null) return;
      	
    	ontoumlmodel.setOntoUML(path);
    					
    	ontoumlview.setPath(ontoumlmodel.getOntoUMLPath(),ontoumlmodel.getOntoUMLModelInstance());
    	ontoumlview.setModelTree(ontoumlmodel.getOntoUMLModelInstance(),ontoumlmodel.getOntoUMLParser());	    		
    	ontoumlview.validate();
    	ontoumlview.repaint();
    	
    	ontoumlview.getTheFrame().getManager().getAntiPatternListView().Clear();	    	
    	umlmodel.setUMLModel(path.replace(".refontouml",".uml"));	    	
    	alloymodel.setAlloyModel(path.replace(".refontouml",".als"));
    		    		    					
    	} catch (IOException exception) {
    		
    		String msg = "An error ocurred while loading the model.\n"+exception.getMessage();
    		ontoumlview.getTheFrame().showErrorMessageDialog("Open OntoUML",msg);
    		exception.printStackTrace();
    	}	    	
    	
    	ontoumlview.getTheFrame().getManager().doModelDiagnostic();	
    	
    	// Guarantee accordance between the OntoUML model and Simulation options...
    	ontoumlOptModel.setOptions(new Onto2AlloyOptions());
    	
    	if (!ontoumlmodel.getOntoUMLParser().getElementsWithIdentityMissing().isEmpty())
    	ontoumlOptModel.getOptions().identityPrinciple = false;    		    	
    	
    	if (!ontoumlmodel.getOntoUMLParser().getRelatorsWithInvalidAxiom().isEmpty()) 
        ontoumlOptModel.getOptions().relatorAxiom = false;
    	
    	if (!ontoumlmodel.getOntoUMLParser().getWholesWithInvalidWeakSupplementation().isEmpty()) 
        ontoumlOptModel.getOptions().weakSupplementationAxiom = false;    	
    	
	}
	
	public void doShowOrHideAliases ()
	{
		if(ontoumlview.getModelTree()==null)
			{	       			
    		ontoumlview.getTheFrame().showInformationMessageDialog("Show Aliases","First you need to load your Model");
    		return;			       				
			}	    	
    	
    	((OntoUMLCheckBoxTree.OntoUMLTreeCellRenderer)ontoumlview.getModelTree().getCellRenderer()).showOrHideUniqueName();
    	
    	ontoumlview.getModelTree().updateUI();
    }
	
	public void doSaveOntoUML ()
	{
		if(ontoumlview.getModelTree()==null)
		{	       			
    		ontoumlview.getTheFrame().showInformationMessageDialog("Save","First you need to load your Model");
    		return;			       				
		}
    	ontoumlview.getTheFrame().getManager().doAutoSelectionCompletion(OntoUMLParser.NO_HIERARCHY);
    	
    	String path = FileChoosersUtil.saveOntoUMLPathLocation(frame,ontoumlmodel.getOntoUMLPath());
    	
    	if(path!=null)ResourceUtil.saveReferenceOntoUML(path, ontoumlview.getTheFrame().getManager().getOntoUMLModel().getOntoUMLModelInstance());		
	}
	
	public void doOpenOCL () 
	{
    	try{
     	
      	String path = FileChoosersUtil.openOCLPathLocation(frame,oclmodel.getOCLPath());
    		
      	if (path==null) return;
      	
      	oclmodel.setConstraints(path,"PATH");
			
      	oclview.setPath(oclmodel.getOCLPath(),oclmodel.getOCLString());	      			
      	oclview.setConstraints(oclmodel.getOCLString());
      	
      	oclview.validate();
      	oclview.repaint();
    	
    	} catch (IOException exception) {				
    		String msg = "An error ocurred while loading the model.\n"+exception.getMessage();
    		oclview.getTheFrame().showErrorMessageDialog("Open OCL",msg);
    		exception.printStackTrace();
    	}		
	}
	
	public void doNewOCL () 
	{
		boolean option = JOptionPane.showConfirmDialog(
    			oclview.getTheFrame(),
    			"Do you really want to create a new OCL Document?\n",
    			"New OCL Document",
    			JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    	
    	if (option)
    	{
    		oclmodel.clearModel();
    	
    		oclview.setPath(oclmodel.getOCLPath(), oclmodel.getOCLString());
    		oclview.setConstraints(oclmodel.getOCLString());
    	}
	}
	
	public void doSaveOCL ()
	{
		if (oclview.getPath()==null || oclview.getPath().isEmpty())
    	{
    		try{
    			
    			String path = FileChoosersUtil.saveOCLPathLocation(frame,oclmodel.getOCLPath());	    		
    			if (path==null) return;		
	      		    					      	
    			oclmodel.setConstraints(oclview.getConstraints(),"CONTENT");
    			oclmodel.setOCLPath(path);
    			
    			FileUtil.copyStringToFile(oclview.getConstraints(), path);
    			
    			oclview.setPath(path,oclview.getConstraints());	    			
    	    	
    		}catch(IOException exception){
    			
    			String msg = "An error ocurred while saving the model.\n"+exception.getMessage();
    			oclview.getTheFrame().showErrorMessageDialog("IO",msg);		       			
    			exception.printStackTrace();
    		}
	      			      	
    	}else{
    		try{
    			
    			oclmodel.setConstraints(oclview.getConstraints(),"CONTENT");
    			oclmodel.setOCLPath(oclview.getPath());
    			
    			FileUtil.copyStringToFile(oclview.getConstraints(), oclview.getPath());
    			
    		}catch(IOException exception){
    			
    			String msg = "An error ocurred while saving the model.\n"+exception.getMessage();
    			oclview.getTheFrame().showErrorMessageDialog("IO",msg);	       			
    			exception.printStackTrace();
    		}		      		
    	}
	}
	
	public void doSearchAntiPatterns ()
	{
    	if (frame.getManager().getOntoUMLModel().getOntoUMLParser()==null) 
    	{ 
    		frame.showInformationMessageDialog("Search AntiPatterns"," You need to open a Model!"); 
    		return; 
    	}    	
    	try {    		
    		AntiPatternListDialog.open(frame);    		
    	}catch(Exception x){
    		JOptionPane.showMessageDialog(frame,x.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
    		x.printStackTrace();
    	}
	}
	
	public void doSimulation()
	{
		frame.getManager().doOntoUMLToAlloy();
    	
    	frame.getManager().doOCLToAlloy();
    	
    	if (ontoumlOptModel.getOptions().openAnalyzer)
    		frame.getManager().doOpeningAlloy(true,-1);
    	else
    		frame.getManager().doOpeningAlloy(false,0);		
	}
	
}
