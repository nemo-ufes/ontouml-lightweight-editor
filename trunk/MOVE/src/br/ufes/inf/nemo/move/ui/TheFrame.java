package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.mvc.controller.OCLController;
import br.ufes.inf.nemo.move.mvc.controller.OntoUMLController;
import br.ufes.inf.nemo.move.mvc.controller.antipattern.list.AntiPatternListController;
import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.mvc.model.OCLModel;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLModel;
import br.ufes.inf.nemo.move.mvc.model.OptionsModel;
import br.ufes.inf.nemo.move.mvc.model.UMLModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.list.AntiPatternListModel;
import br.ufes.inf.nemo.move.mvc.view.OCLView;
import br.ufes.inf.nemo.move.mvc.view.OntoUMLView;
import br.ufes.inf.nemo.move.mvc.view.antipattern.list.AntiPatternListView;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
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
	private OptionsModel optmodel;
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
		optmodel = new OptionsModel();
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
	public OptionsModel getOptionModel() { 
		return optmodel; 
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
	 * Transforms OntoUML Model into Alloy according to the options.
	 */
	public void TransformsOntoUMLIntoAlloy()
	{
		try {
			
			if (ontoumlmodel.getOntoUMLModelInstance()==null) return;	
			
			alloymodel.setAlloyModel(ontoumlmodel,optmodel);
			
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
	 * Transforms OCL Model into Alloy.
	 */
	public void TransformsOCLIntoAlloy()
	{
		try {
			
			if (ontoumlmodel.getOntoUMLModelInstance()==null) return;
			
			if (oclmodel.getOCLParser()==null) oclmodel.setParser(oclview.parseConstraints());			
			
			console.write(oclmodel.getOCLParser().getDetails());
			
			console.append(alloymodel.addConstraints(ontoumlmodel, oclmodel));
			
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
				if (optmodel.getOptions().openAnalyzer)
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
