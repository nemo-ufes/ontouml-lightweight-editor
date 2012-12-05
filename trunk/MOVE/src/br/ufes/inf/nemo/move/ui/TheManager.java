package br.ufes.inf.nemo.move.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.SemanticException;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
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
import br.ufes.inf.nemo.move.ui.ocl.OCLEditorBar;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLCheckBoxTree;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.ocl2alloy.options.OCLOptions;
import edu.mit.csail.sdg.alloy4whole.SimpleGUI;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

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
    	umlmodel.setUMLModel(alsPath.replace(".als",".uml"));    	
    	alloymodel.setAlloyModel(alsPath);    	
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
	

	public String verifyModel()
	{		
		String log = new String();		
		if (ontoumlmodel.getOntoUMLParser()==null) return "First you need to load the Model. ";		
		UpdateSelection(OntoUMLParser.NO_HIERARCHY);		
		RefOntoUML.Package refmodel = ontoumlmodel.getOntoUMLParser().createPackageFromSelections(new org.eclipse.emf.ecore.util.EcoreUtil.Copier());						
		log = SyntacticVerificator.verify(refmodel);
		return log;
	}
	
	public String UpdateSelection(int option)
	{		
		List<EObject> selected = OntoUMLCheckBoxTree.getCheckedElements(ontoumlview.getModelTree());
		ontoumlmodel.getOntoUMLParser().selectThisElements((ArrayList<EObject>)selected,true);		
		List<EObject> added = ontoumlmodel.getOntoUMLParser().autoSelectDependencies(option,false);			
		String msg = new String();
		for(EObject o: added)
		{
			msg += ""+ontoumlmodel.getOntoUMLParser().getStringRepresentation(o)+" added.\n";
		}
		msg += "\nSelection Completed!";		
		selected.removeAll(added);
		selected.addAll(added);				
		OntoUMLCheckBoxTree.checkElements(selected, true, ontoumlview.getModelTree());		
    	ontoumlview.getModelTree().updateUI();		      	
		return msg;
	}


	public void TransformsOntoUMLIntoAlloy()
	{
		try {			
			
			if (ontoumlmodel.getOntoUMLModelInstance()==null) return;			
			UpdateSelection(OntoUMLParser.NO_HIERARCHY);   			
			alloymodel.setAlloyModel(ontoumlmodel,ontoumlOptModel);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,e.getLocalizedMessage(),"Error - Transforming OntoUML into Alloy",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}
	}


	public void TransformsOntoUMLIntoUML()
	{
		try{
			
			UpdateSelection(OntoUMLParser.NO_HIERARCHY); 
			umlmodel.setUMLModel(ontoumlmodel.getOntoUMLPath().replace(".refontouml",".uml"),ontoumlmodel);			
			frame.getConsole().write(umlmodel.getDetails());
			
		}catch (Exception e) {			
			JOptionPane.showMessageDialog(frame,e.getLocalizedMessage(),"Error - Transforming OntoUML into UML",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}
	}
		

	public void ParseOCL(boolean showSuccesfullyMessage)
	{
		try {			
			
			oclmodel.setParser(oclview.parseConstraints());			
			oclOptModel.setOCLOptions(new OCLOptions(oclmodel.getOCLParser()));			
			frame.getConsole().write(oclmodel.getOCLParser().getDetails());			
			if(showSuccesfullyMessage)
			{
				JOptionPane.showMessageDialog(
					oclview.getTheFrame(),"Your Constraints are Syntactically Correct !\n","Parse",JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/check-36x36.png"))
				);
			}
			
    	}catch(SemanticException e2){
			JOptionPane.showMessageDialog(
				oclview.getTheFrame(),e2.getMessage(),"Semantic",JOptionPane.ERROR_MESSAGE,
				new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/delete-36x36.png"))
			);					
			e2.printStackTrace();	
			
    	}catch(ParserException e1){
				JOptionPane.showMessageDialog(
					oclview.getTheFrame(),e1.getMessage(),"Parse",JOptionPane.ERROR_MESSAGE,
					new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/delete-36x36.png"))
				);					
			e1.printStackTrace();    	
			
		}catch(IOException e3){
			JOptionPane.showMessageDialog(
				oclview.getTheFrame(),e3.getMessage(),"IO",JOptionPane.ERROR_MESSAGE,
				new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/delete-36x36.png"))
			);					
			e3.printStackTrace();
		}
	}


	public void TransformsOCLIntoAlloy()
	{
		try {
			
			if (ontoumlmodel.getOntoUMLModelInstance()==null) return;
			if (oclmodel.getOCLParser()==null) return;			
			//console.write(oclmodel.getOCLParser().getDetails());			
			String logMessage = alloymodel.addConstraints(ontoumlmodel, oclmodel,oclOptModel);			
			if (!logMessage.isEmpty() && logMessage!=null)
			{
				JOptionPane.showMessageDialog(frame,logMessage,"Warning",JOptionPane.WARNING_MESSAGE);					
			}
			
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(frame,e.getLocalizedMessage(),"Error - Transforming OCL into Alloy",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}
	}
		

	public void OpenAlloyModelWithAnalyzer (String alsPath, String alsOutDirectory) 
	{		
		if (alsPath.isEmpty() || alsPath==null)
		{
			try {
				
				// open standalone...
				AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", alsOutDirectory);				
				String[] argsAnalyzer = { "","" };
				argsAnalyzer[0] = alsPath;
				SimpleGUI.main(argsAnalyzer);
				
			} catch (Exception e) {			
				JOptionPane.showMessageDialog(frame,e.getLocalizedMessage(),"Error - Launching Analyzer",JOptionPane.ERROR_MESSAGE);					
				e.printStackTrace();
			}
			
		}else{
			try {
				
				// open custom...		
				if (ontoumlOptModel.getOptions().openAnalyzer)
				{
					AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", alsOutDirectory);					
					String[] argsAnalyzer = { "","" };
					argsAnalyzer[0] = alsPath;
					argsAnalyzer[1] = alsOutDirectory + "standart_theme.thm"	;					
					SimpleGUICustom.main(argsAnalyzer);
				}
				
			} catch (Exception e) {			
				JOptionPane.showMessageDialog(frame,e.getLocalizedMessage(),"Error - Opening Alloy With Analyzer",JOptionPane.ERROR_MESSAGE);					
				e.printStackTrace();
			}	
		}
	}
	
	public void setAntiPatternListModel(AntiPatternListModel antipatternListModel) 
	{ 
		this.antipatternmodel = antipatternListModel; 
		this.antipatternview.setAntiPatternListModel(antipatternListModel);		
	}
			
	// run verifier...
	/*OntoUMLVerifier verifier = new OntoUMLVerifier(refmodel);
	verifier.initialize();			
	// no substance sortal warning...
	if (!verifier.haveSubstanceSortal && opt.identityPrinciple) 
	{
		opt.identityPrinciple=false;
		enforcepanel.cbxIdentityPrinciple.setSelected(false);
		JOptionPane.showMessageDialog(this, "No Substance Sortals in the model.\n\nThe Identity Principle Axiom should not be enforced."
				+"\nThis option was unchecked by default.\n ", "Warning",JOptionPane.WARNING_MESSAGE);
	}*/
}
