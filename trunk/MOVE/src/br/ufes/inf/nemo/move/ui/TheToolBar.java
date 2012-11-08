package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import br.ufes.inf.nemo.move.panel.antipattern.AntiPatternDialog;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.ocl2alloy.OCLParser;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * @author John Guerson
 */

public class TheToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;

	private JButton btnSyntaticVerification;	
	private JButton btnSearchForAntipatterns;	
	private JButton btnExecute;	
	private JButton btnHideConsole;
	
	private TheFrame frame;
	private TheButton btnParseOcl;
		
	
	/**
	 * Create ToolBar.
	 * 
	 * @param parent
	 */
	public TheToolBar(TheFrame parent)
	{
		this();
		
		this.frame = parent;
	}
	
	/**
	 *	Create Toolbar. 
	 */
	public TheToolBar() 
	{
		super();
		setFloatable(false);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		setBackground(UIManager.getColor("ToolBar.dockingBackground"));
	
		createShowHideOutputButton();
		createExecuteButton();
		createSyntaticButton();
		createParseOCLButton();
		createAntiPatternButton();		
	}
	
	/**
	 * Create Show/Hide Output Button.
	 */
	public void createShowHideOutputButton()
	{
		btnHideConsole = new TheButton("Show/Hide Console","/resources/br/ufes/inf/nemo/move/output.png");
						
		btnHideConsole.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.ShowOrHideConsole();
       		}
       	});		
		
		add(btnHideConsole);	
	}
	
	/**
	 * Create Syntactic Verification Button.
	 */
	public void createSyntaticButton ()
	{
		btnSyntaticVerification = new TheButton("Syntactic Verification","/resources/br/ufes/inf/nemo/move/check.png");
		btnSyntaticVerification.setText("Verify OntoUML Syntax");
				
		btnSyntaticVerification.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			SyntaticButtonActionPerformed(event);
       		}
       	});
		
		add(btnSyntaticVerification);
	}
	
	public void createParseOCLButton ()
	{
		btnParseOcl = new TheButton("Parse OCL Domain Constraints","/resources/br/ufes/inf/nemo/move/check.png");
		btnParseOcl.setText("Verify OCL Syntax");
				
		btnParseOcl.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			ParseOCLDomainConstraints(event);
       		}
       	});
		
		add(btnParseOcl);		
	}
	
	/**
	 * Create Identify AntiPattern Button.
	 */
	public void createAntiPatternButton()
	{
		btnSearchForAntipatterns = new TheButton("Identify AntiPatterns","/resources/br/ufes/inf/nemo/move/search.png");
		
		btnSearchForAntipatterns.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{       			
       			AntiPatternButtonActionPerformed(event);		
       		}
       	});
		
		add(btnSearchForAntipatterns);
		
	}

	/**
	 * Create Execute Button
	 */	
	public void createExecuteButton ()	
	{
		btnExecute = new TheButton("Execute with Analyzer","/resources/br/ufes/inf/nemo/move/play.png");
		
		btnExecute.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 ExecuteActionPerformed(event);					
       		}
       	});
		
		add(btnExecute);
	}
	
	/**
	 * Searching AntiPatterns.
	 */
	public void  AntiPatternButtonActionPerformed(ActionEvent event)
	{
		try {
			
			AntiPatternDialog.open(frame);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}		
	}	
	
	/**	
	 * OntoUML Syntax Verification 
	 */
	public void SyntaticButtonActionPerformed(ActionEvent arg0)
	{
		// not implemented yet...
	}
	
	/**
	 * Parsing OCL Domain COnstraints...
	 */
	private void ParseOCLDomainConstraints(ActionEvent event)
	{		
		if (frame.getTheModelsBar().getOCLModel().isEmpty() || frame.getTheModelsBar().getOCLModel() == null) return;
		
		OCLParser oclparser = new OCLParser();	
		
		RefOntoUML.Package refmodel = frame.getTheModelsBar().getOntoUMLModel();
		String oclConstraints = frame.getTheModelsBar().getOCLModel();	
		String umlPath = frame.getTheModelsBar().getUMLPath();
		
		oclparser.parse(refmodel,oclConstraints,umlPath);	
				
		frame.getTheConsolePanel().append(oclparser.logDetails);	
		frame.ShowConsole();
	}
	
	/**	
	 * Executing Validation with Analyzer. 
	 */
	private void ExecuteActionPerformed (ActionEvent arg0)
	{		
		try {
		
		Options opt = frame.getTheMenuBar().getOptions();
		String alsPath = frame.getTheModelsBar().getAlloyPath();
		RefOntoUML.Package refmodel = frame.getTheModelsBar().getOntoUMLModel();
		String oclConstraints = frame.getTheModelsBar().getOCLModel();				
		
		if(oclConstraints == null || oclConstraints.equals("")) OntoUML2Alloy.Transformation(refmodel, alsPath, opt);
		
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
			AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", TheFrame.dirPath);
			
			String[] argsAnalyzer = { "","" };
			argsAnalyzer[0] = alsPath;
			argsAnalyzer[1] = TheFrame.dirPath + "standart_theme.thm"	;	
			SimpleGUICustom.main(argsAnalyzer);
		}		
		
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}		
	}	
}
