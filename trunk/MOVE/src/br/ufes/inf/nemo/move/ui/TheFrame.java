package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.SemanticException;

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
 * @author John Guerson
 */

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
		
	private TheToolBar toolBar;	
	private TheMenuBar menuBar;	
	private TheConsole console;	
	private JSplitPane mainSplitPane;
	private JSplitPane innerSplitPane;	
	private JSplitPane centerSplitPane;	
	
	private OntoUMLModel ontoumlmodel;
	private UMLModel umlmodel;
	private OCLModel oclmodel;
	private AntiPatternListModel antipatternmodel;
	private OntoUMLOptionsModel ontoumlOptModel;
	private OCLOptionsModel oclOptModel;
	private AlloyModel alloymodel;
	
	private OntoUMLView ontoumlview;			
	private OCLView oclview;		
	private AntiPatternListView antipatternview;
		
	/**
	 * Constructor.
	 * 
	 * @param model
	 * @param oclConstraints
	 * @param alsPath
	 * @throws IOException
	 */
	public TheFrame (RefOntoUML.Package model, String oclConstraints, String alsPath) throws IOException
	{
		this();		
		
		ontoumlmodel.setOntoUML(model);

    	umlmodel.setUMLModel(alsPath.replace(".als",".uml"));
    	
    	alloymodel.setAlloyModel(alsPath);
    	
		ontoumlview.setPath(ontoumlmodel.getOntoUMLPath(),ontoumlmodel.getOntoUMLModelInstance());
    	ontoumlview.setModelTree(ontoumlmodel.getOntoUMLModelInstance(),ontoumlmodel.getOntoUMLParser());    	
    	ontoumlview.validate();
    	ontoumlview.repaint();
    	
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
	}	
	
	/**
	 * Constructor.
	 */
	public TheFrame() 
	{
		super();				
		
		setExtendedState(MAXIMIZED_BOTH);		
		getContentPane().setBackground(new Color(230, 230, 250));
		getContentPane().setLayout(new BorderLayout(0, 0));		
		menuBar = new TheMenuBar(this);
		setJMenuBar(menuBar);		
		toolBar = new TheToolBar(this);				
		JPanel headpanel = new JPanel();
		headpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		headpanel.setLayout(new BorderLayout(0, 0));
		headpanel.add(BorderLayout.NORTH,toolBar);				
		getContentPane().add(BorderLayout.NORTH,headpanel);		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TheFrame.class.getResource("/resources/br/ufes/inf/nemo/move/window.png")));
		setTitle("OntoUML Model Validation Environment - MOVE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		createModels();
		createViews();
		createControllers();
		
		innerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,oclview,antipatternview);
		innerSplitPane.setOneTouchExpandable(true);		
		centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,ontoumlview,innerSplitPane);
		centerSplitPane.setOneTouchExpandable(true);		
		console = new TheConsole();		
		mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,centerSplitPane,console);
		mainSplitPane.setOneTouchExpandable(true);		
		getContentPane().add(BorderLayout.CENTER,mainSplitPane);				
		pack();		
		
		mainSplitPane.setDividerLocation(1.0);
		centerSplitPane.setDividerLocation(0.50);
		innerSplitPane.setDividerLocation(1.0);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		ontoumlview = new OntoUMLView(ontoumlmodel,this);
		oclview = new OCLView(oclmodel,this);
		antipatternview = new AntiPatternListView(antipatternmodel,this);
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
	
	/** Get Console Panel. */
	public TheConsole getConsole() { 
		return console; 
	}
	/** Get Tool Bar */
	public TheToolBar getToolBar() { 
		return toolBar; 
	}
	/** Get Menu Bar */
	public TheMenuBar getTheMenuBar() { 
		return menuBar; 
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
		
	/**
	 * Show Or Hide AntiPAttern View...
	 */
	public void ShowOrHideAntiPatternView()
	{
		int location = innerSplitPane.getDividerLocation();
		int maxLocation = innerSplitPane.getMaximumDividerLocation();
		if(location < maxLocation)
	    {
			innerSplitPane.setDividerLocation(1.0);	
	    }
	    else
	    {
	      	innerSplitPane.setDividerLocation(0.50);
	    }
	}
	
	/** Show AntiPattern View */
	public void ShowAntiPatternView() { 
		innerSplitPane.setDividerLocation(0.50); 
	}
	/** Hide AntiPattern View */
	public void HideAntiPatternView() { 
		innerSplitPane.setDividerLocation(1.00); 
	}
	
	/**
	 * Show Or Hide Console...
	 */
	public void ShowOrHideConsole()
	{
		int location = mainSplitPane.getDividerLocation();
		int maxLocation = mainSplitPane.getMaximumDividerLocation();
		if(location < maxLocation)
	    {
			mainSplitPane.setDividerLocation(1.0);	
	    }
	    else
	    {
	      	mainSplitPane.setDividerLocation(0.60);
	    }
	}	
	
	/** Show Console */
	public void ShowConsole() { 
		mainSplitPane.setDividerLocation(0.70); 
	}
	/** Hide Console */
	public void HideConsole() { 
		mainSplitPane.setDividerLocation(1.00); 
	}

	/**
	 * Select elements of the Parser from Check Box Model Tree for Transformations purposes.
	 */
	public String UpdateSelection()
	{		
		List<EObject> selected = OntoUMLCheckBoxTree.getCheckedElements(ontoumlview.getModelTree());   			
		
		ontoumlmodel.getOntoUMLParser().selectThisElements((ArrayList<EObject>)selected,true);		
		List<EObject> added = ontoumlmodel.getOntoUMLParser().autoSelectDependencies(ontoumlview.hierarchyOption(),false);   			
				
		String msg = new String();
		for(EObject o: added)
		{
			msg += ""+ontoumlmodel.getOntoUMLParser().getStringRepresentation(o)+" added.\n";
		}
		msg += "\nSelection Completed!";
		
		selected.addAll(added);
		OntoUMLCheckBoxTree.checkElements(selected, true,ontoumlview.getModelTree());
		
    	ontoumlview.getModelTree().updateUI();
		
    	console.write("This Elements are selected: \n\n"+ontoumlmodel.getOntoUMLParser().getStringRepresentations());
		return msg;
	}
	
	/**
	 * Transforms OntoUML Model into Alloy according to the options.
	 */
	public void TransformsOntoUMLIntoAlloy()
	{
		try {
			
			if (ontoumlmodel.getOntoUMLModelInstance()==null) return;	
			
			UpdateSelection();
   			
			alloymodel.setAlloyModel(ontoumlmodel,ontoumlOptModel);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error - Transforming OntoUML into Alloy",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}
	}
	
	/**
	 * Transforms OntoUML Model into UML.
	 */
	public void TransformsOntoUMLIntoUML()
	{
		try{
			
			umlmodel.setUMLModel(ontoumlmodel.getOntoUMLPath().replace(".refontouml",".uml"),ontoumlmodel);
			
			console.write(umlmodel.getDetails());
			
		}catch (Exception e) {			
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error - Transforming OntoUML into UML",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}
	}
		
	/**
	 * Parse OCL.
	 */
	public void ParseOCL(boolean showSuccesfullyMessage)
	{
		try {			
			
			oclmodel.setParser(oclview.parseConstraints());
			
			oclOptModel.setOCLOptions(new OCLOptions(oclmodel.getOCLParser()));
			
			console.write(oclmodel.getOCLParser().getDetails());
			
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
	
	/**
	 * Transforms OCL Model into Alloy.
	 */
	public void TransformsOCLIntoAlloy()
	{
		try {
			
			if (ontoumlmodel.getOntoUMLModelInstance()==null) return;
			if (oclmodel.getOCLParser()==null) return;
			
			//console.write(oclmodel.getOCLParser().getDetails());
			
			String logMessage = alloymodel.addConstraints(ontoumlmodel, oclmodel,oclOptModel);
			
			if (!logMessage.isEmpty() && logMessage!=null)
			{
				JOptionPane.showMessageDialog(this,logMessage,"Warning",JOptionPane.WARNING_MESSAGE);					
			}
			
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error - Transforming OCL into Alloy",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}
	}
		
	/** 
	 * Open Alloy Model with Analyzer.
	 */
	public void OpenAlloyModelWithAnalyzer () 
	{		
		if (alloymodel.getAlloyPath().isEmpty() || alloymodel.getAlloyPath()==null)
		{
			try {
				
				// open standalone...
				AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", AlloyModel.alsOutDirectory);				
				String[] argsAnalyzer = { "","" };
				argsAnalyzer[0] = alloymodel.getAlloyPath();				
				SimpleGUI.main(argsAnalyzer);
				
			} catch (Exception e) {			
				JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error - Launching Analyzer",JOptionPane.ERROR_MESSAGE);					
				e.printStackTrace();
			}
			
		}else{
			try {
				
				// open custom...		
				if (ontoumlOptModel.getOptions().openAnalyzer)
				{
					AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", AlloyModel.alsOutDirectory);					
					String[] argsAnalyzer = { "","" };
					argsAnalyzer[0] = alloymodel.getAlloyPath();
					argsAnalyzer[1] = AlloyModel.alsOutDirectory + "standart_theme.thm"	;					
					SimpleGUICustom.main(argsAnalyzer);
				}
				
			} catch (Exception e) {			
				JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error - Opening Alloy With Analyzer",JOptionPane.ERROR_MESSAGE);					
				e.printStackTrace();
			}	
		}
	}		
		
	
	
	/* ============================================================================= */
	/* ============================================================================= */
	/* ============================================================================= */
	/* ============================================================================= */
	
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
