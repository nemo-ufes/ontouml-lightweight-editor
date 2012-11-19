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

import br.ufes.inf.nemo.move.antipattern.AntiPatternListController;
import br.ufes.inf.nemo.move.antipattern.AntiPatternListModel;
import br.ufes.inf.nemo.move.antipattern.AntiPatternListView;
import br.ufes.inf.nemo.move.ocl.OCLController;
import br.ufes.inf.nemo.move.ocl.OCLModel;
import br.ufes.inf.nemo.move.ocl.OCLView;
import br.ufes.inf.nemo.move.ontouml.OntoUMLController;
import br.ufes.inf.nemo.move.ontouml.OntoUMLModel;
import br.ufes.inf.nemo.move.ontouml.OntoUMLView;
import br.ufes.inf.nemo.move.option.OptionController;
import br.ufes.inf.nemo.move.option.OptionModel;
import br.ufes.inf.nemo.move.option.OptionView;
import br.ufes.inf.nemo.move.output.OutputController;
import br.ufes.inf.nemo.move.output.OutputModel;
import br.ufes.inf.nemo.move.output.OutputView;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * @author John Guerson
 */

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
		
	private TheToolBar toolBar;	
	private TheMenuBar menuBar;	
	private TheStatusBar statusbar;	
	private TheConsole console;
	
	private JSplitPane mainSplitPane;
	private JSplitPane innerSplitPane;	
	private JSplitPane centerSplitPane;
	
	private OntoUMLModel ontoumlmodel;
	private OntoUMLView ontoumlview;	
	@SuppressWarnings("unused")
	private OntoUMLController ontoumlcontroller;
		
	private OCLModel oclmodel;
	private OCLView oclview;	
	@SuppressWarnings("unused")
	private OCLController oclcontroller;
	
	private AntiPatternListModel antipatternmodel;
	private AntiPatternListView antipatternview;
	@SuppressWarnings("unused")
	private AntiPatternListController antipatterncontroller;
	
	private OptionModel optmodel;
	private OptionView optview;
	@SuppressWarnings("unused")
	private OptionController optcontroller;
	
	private OutputModel outputmodel;
	private OutputView outputview;
	@SuppressWarnings("unused")
	private OutputController outputcontroller;
	
	public TheConsole getConsole() { return console; }
	public TheToolBar getToolBar() { return toolBar; }
	public TheMenuBar getTheMenuBar() { return menuBar; }
	public TheStatusBar getTheStatusBar() { return statusbar; }	
	public AntiPatternListModel getAntiPatternListModel() { return antipatternmodel; }
	public AntiPatternListView getAntiPatternListView() { return antipatternview; }	
	public OntoUMLModel getOntoUMLModel() { return ontoumlmodel; }
	public OntoUMLView getOntoUMLView() { return ontoumlview; }	
	public OCLModel getOCLModel() { return oclmodel; }
	public OCLView getOCLView() { return oclview; }
	public OutputView getOutputView() { return outputview; }
	public OutputModel getOutputModel() { return outputmodel; }	
	public OptionView getOptionView() { return optview;	}
	public OptionModel getOptionModel() { return optmodel; }	
	
	/**
	 * Create the frame.
	 */
	public TheFrame (RefOntoUML.Package model, String oclConstraints, String alsPath) throws IOException
	{
		this();		
		
		ontoumlmodel.setOntoUML(model);
		ontoumlview.setPath(ontoumlmodel.getOntoUMLPath(),ontoumlmodel.getOntoUMLModelInstance());
    	ontoumlview.setModelTree(ontoumlmodel.getOntoUMLModelInstance());    	
    	ontoumlview.validate();
    	ontoumlview.repaint();    
    	
    	OutputModel outputmodel = new OutputModel(alsPath,alsPath.replace(".als",".uml"));
    	
    	ontoumlview.getTheFrame().setOutputModel(outputmodel);
    			
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}	

	/**
	 * Create the frame.
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
					
		oclmodel = new OCLModel();
		oclview = new OCLView(oclmodel,this);
		oclcontroller = new OCLController(oclview,oclmodel);
		
		antipatternmodel = new AntiPatternListModel();
		antipatternview = new AntiPatternListView(antipatternmodel,this);		
		
		innerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,oclview,antipatternview);
		innerSplitPane.setOneTouchExpandable(true);		

		ontoumlmodel = new OntoUMLModel();
		ontoumlview = new OntoUMLView(ontoumlmodel,this);
		ontoumlcontroller = new OntoUMLController(ontoumlview,ontoumlmodel);
		
		optmodel = new OptionModel();
		optview = new OptionView(optmodel,this);
		optcontroller = new OptionController(optview,optmodel);
		
		outputmodel = new OutputModel();
		outputview = new OutputView(outputmodel,this);
		outputcontroller = new OutputController(outputview,outputmodel);
		
		centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,ontoumlview,innerSplitPane);
		centerSplitPane.setOneTouchExpandable(true);		
		
		console = new TheConsole();		
		mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,centerSplitPane,console);
		mainSplitPane.setOneTouchExpandable(true);		
		getContentPane().add(BorderLayout.CENTER,mainSplitPane);
		
		statusbar = new TheStatusBar();
		getContentPane().add(BorderLayout.SOUTH,statusbar);
				
		pack();
		
		mainSplitPane.setDividerLocation(1.0);
		centerSplitPane.setDividerLocation(0.50);
		innerSplitPane.setDividerLocation(1.0);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
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
	public void ShowAntiPatternView() { innerSplitPane.setDividerLocation(0.50); }
	public void HideAntiPatternView() { innerSplitPane.setDividerLocation(1.00); }
	
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
	public void ShowConsole() { mainSplitPane.setDividerLocation(0.50); }
	public void HideConsole() { mainSplitPane.setDividerLocation(1.00); }
	
	/** 
	 * Validate...
	 */
	public void Validate () 
	{
		try {
		
			if (getOntoUMLModel().getOntoUMLModelInstance()==null) 
				return;
		
			Options opt = getOptionModel().getOptions();				
			String alsPath = getOutputModel().getAlloyPath();		
			RefOntoUML.Package refmodel = getOntoUMLModel().getOntoUMLModelInstance();				
			OntoUML2Alloy.Transformation(refmodel, alsPath, opt);
			
			if (opt.openAnalyzer)
			{
				AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", OutputModel.alsOutDirectory);
			
				String[] argsAnalyzer = { "","" };
				argsAnalyzer[0] = alsPath;
				getOutputModel();
				argsAnalyzer[1] = OutputModel.alsOutDirectory + "standart_theme.thm"	;	
				SimpleGUICustom.main(argsAnalyzer);
			}
			
			/*
			// run verifier...
			OntoUMLVerifier verifier = new OntoUMLVerifier(refmodel);
			verifier.initialize();
			
			// no substance sortal warning...
			if (!verifier.haveSubstanceSortal && opt.identityPrinciple) 
			{
				opt.identityPrinciple=false;
				enforcepanel.cbxIdentityPrinciple.setSelected(false);
				JOptionPane.showMessageDialog(this, "No Substance Sortals in the model.\n\nThe Identity Principle Axiom should not be enforced."
						+"\nThis option was unchecked by default.\n ", "Warning",JOptionPane.WARNING_MESSAGE);
			}*/
			
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}	
	}
	
	public void setOutputView(OutputView outputview) 
	{
		this.outputview = outputview;
	}
	
	public void setOutputModel(OutputModel outputmodel)
	{
		this.outputmodel = outputmodel;
		this.outputview.setOutputModel(outputmodel);
	}
	
	public void setAntiPatternListModel(AntiPatternListModel antipatternListModel) 
	{ 
		this.antipatternmodel = antipatternListModel; 
		this.antipatternview.setAntiPatternListModel(antipatternListModel);		
	}
	
	
	
}
