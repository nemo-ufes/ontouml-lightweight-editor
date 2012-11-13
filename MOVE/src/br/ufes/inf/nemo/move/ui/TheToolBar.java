package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import br.ufes.inf.nemo.move.output.OutputModel;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.move.util.ui.ToolbarButton;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * @author John Guerson
 */

public class TheToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;

	private TheFrame frame;		
	private JButton btnSyntaticVerification;	
	private JButton btnSearchForAntipatterns;	
	private JButton btnExecute;	
	private JButton btnHideConsole;
	private JButton btnOutput;
	private JButton btnOptions;
	private JButton btnInfo;
	private JButton btnOntoUML;
	private ToolbarButton btnParseOcl;	
	
	/**
	 * Constructor.
	 * 
	 * @param parent
	 */
	public TheToolBar(TheFrame parent)
	{
		this();
		
		this.frame = parent;
	}
	
	/**
	 *	Constructor.
	 */
	public TheToolBar() 
	{
		super();
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));		
		setBackground(UIManager.getColor("ToolBar.dockingBackground"));
		
		createButtons();
	}
	
	/**
	 * Create ToolBar buttons.
	 */
	public void createButtons()
	{		
		createSyntaticButton();
		createExecuteButton();
		createAntiPatternButton();		
		createOptionsButton();		
		createInfoButton();
		createOutputButton();		
	}
	

	/**
	 * Output Location Button.
	 */
	public void createOntoUMLButton()
	{
		btnOntoUML = new ToolbarButton("Load Model","/resources/br/ufes/inf/nemo/move/ontouml-48x30.png");				
		btnOntoUML.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			// not implemented yet...
       		}
       	});
		add(btnOntoUML);	
	}
	
	/**
	 * Output Location Button.
	 */
	public void createInfoButton()
	{
		btnInfo = new ToolbarButton("Information","/resources/br/ufes/inf/nemo/move/info-36x36.png");				
		btnInfo.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			// not implemented yet...
       		}
       	});
		add(btnInfo);	
	}
	
	
	/**
	 * Output Location Button.
	 */
	public void createOutputButton()
	{
		btnOutput = new ToolbarButton("Output","/resources/br/ufes/inf/nemo/move/out-36x36.png");				
		btnOutput.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getOutputView().setVisible(true);
       		}
       	});
		add(btnOutput);	
	}
	
	/**
	 * Options Button.
	 */
	public void createOptionsButton()
	{
		btnOptions = new ToolbarButton("Options","/resources/br/ufes/inf/nemo/move/options-36x36.png");
								
		btnOptions.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getOptionView().setVisible(true);
       		}
       	});
		
		add(btnOptions);	
	}

	public void createSyntaticButton ()
	{
		btnSyntaticVerification = new ToolbarButton("Verify","/resources/br/ufes/inf/nemo/move/check-36x36.png");						
		btnSyntaticVerification.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			// not implemented yet...
       		}
       	});
		add(btnSyntaticVerification);
	}
			
	public void createAntiPatternButton()
	{
		btnSearchForAntipatterns = new ToolbarButton("AntiPatterns","/resources/br/ufes/inf/nemo/move/search-36x36.png");		
		btnSearchForAntipatterns.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			AntiPatternButtonActionPerformed(event);					
       		}
       	});
		add(btnSearchForAntipatterns);		
	}

	public void createExecuteButton ()	
	{
		btnExecute = new ToolbarButton("Validate","/resources/br/ufes/inf/nemo/move/play-36x36.png");		
		btnExecute.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 ExecuteActionPerformed(event);					
       		}
       	});
		add(btnExecute);
	}
		
	public void createShowHideOutputButton()
	{
		btnHideConsole = new ToolbarButton("Show/Hide Console","/resources/br/ufes/inf/nemo/move/output.png");						
		btnHideConsole.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.ShowOrHideConsole();
       		}
       	});				
		add(btnHideConsole);	
	}
	
	public void createParseOCLButton ()
	{
		btnParseOcl = new ToolbarButton("Parse OCL","/resources/br/ufes/inf/nemo/move/check.png");						
		btnParseOcl.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			// not implemented yet...
       		}
       	});		
		add(btnParseOcl);		
	}
	
	/*============================ ACTIONS =============================*/
	
	/**
	 * Searching AntiPatterns.
	 */
	public void  AntiPatternButtonActionPerformed(ActionEvent event)
	{
		try {
			
			AntiPatternListDialog.open(frame);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}		
	}	
			
	/**	
	 * Executing Validation with Analyzer. 
	 */
	private void ExecuteActionPerformed (ActionEvent arg0)
	{		
		try {				
		
		if (frame.getOntoUMLModel().getOntoUMLModelInstance()==null) return;
		
		Options opt = frame.getOptionModel().getOptions();
				
		String alsPath = frame.getOutputModel().getAlloyPath();
		
		RefOntoUML.Package refmodel = frame.getOntoUMLModel().getOntoUMLModelInstance();
				
		OntoUML2Alloy.Transformation(refmodel, alsPath, opt);
		
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
		}
		
		// dispose window...
		dispose();
		*/
		
		if (opt.openAnalyzer)
		{
			AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", OutputModel.alsOutDirectory);
			
			String[] argsAnalyzer = { "","" };
			argsAnalyzer[0] = alsPath;
			frame.getOutputModel();
			argsAnalyzer[1] = OutputModel.alsOutDirectory + "standart_theme.thm"	;	
			SimpleGUICustom.main(argsAnalyzer);
		}		
		
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}		
	}	
}
