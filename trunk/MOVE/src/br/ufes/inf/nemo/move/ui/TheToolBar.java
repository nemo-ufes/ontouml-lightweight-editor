package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * This ToolBar was created using the Windows Builder in Eclipse. 
 * 
 * @author John Guerson
 */

public class TheToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;

	private JButton btnSyntaticVerification;	
	private JButton btnSearchForAntipatterns;	
	private JButton btnExecute;	
	private JButton btnHideConsole;
	
	private TheFrame frame;
		
	
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
	 * Create Syntatic Verification Button.
	 */
	public void createSyntaticButton ()
	{
		btnSyntaticVerification = new TheButton("Syntatic Verification","/resources/br/ufes/inf/nemo/move/check.png");
				
		btnSyntaticVerification.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			// not implemented yet...
       		}
       	});
		
		add(btnSyntaticVerification);
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
	 * Executing Validation with Analyzer. 
	 */
	private void ExecuteActionPerformed (ActionEvent arg0)
	{				
		try {
		
		Options opt = frame.getTheMenuBar().getOptions();
		String alsPath = frame.getTheModelsPanel().getAlloyPath();
		RefOntoUML.Package refmodel = frame.getTheModelsPanel().getOntoUMLModel();
		String oclConstraints = frame.getTheModelsPanel().getOCLModel();				
		
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
	
	/**
	 * Searching AntiPatterns.
	 */
	public void  AntiPatternButtonActionPerformed(ActionEvent event)
	{
		try {
			
			TheAntiPatternOptionDialog.open(frame);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}
			/*
		ArrayList<ACAntiPattern> aclist = new ArrayList<ACAntiPattern>();
		ArrayList<RSAntiPattern> rslist = new ArrayList<RSAntiPattern>();
		ArrayList<RBOSAntiPattern> rboslist = new ArrayList<RBOSAntiPattern>();
		ArrayList<RWORAntiPattern> rworlist = new ArrayList<RWORAntiPattern>();
		ArrayList<IAAntiPattern> ialist = new ArrayList<IAAntiPattern>();
		ArrayList<STRAntiPattern> strlist = new ArrayList<STRAntiPattern>();
		
		if (antipatternOptionsPanel.ACisSelected()) aclist = AntiPatternIdentifier.identifyAC(modelspanel.getOntoUMLModel());		
		if (antipatternOptionsPanel.RSisSelected())	rslist = AntiPatternIdentifier.identifyRS(modelspanel.getOntoUMLModel());		
		if (antipatternOptionsPanel.RBOSisSelected()) rboslist = AntiPatternIdentifier.identifyRBOS(modelspanel.getOntoUMLModel());		
		if (antipatternOptionsPanel.RWORisSelected()) rworlist = AntiPatternIdentifier.identifyRWOR(modelspanel.getOntoUMLModel());		
		if (antipatternOptionsPanel.IAisSelected()) ialist = AntiPatternIdentifier.identifyIA(modelspanel.getOntoUMLModel());		
		if (antipatternOptionsPanel.STRisSelected()) strlist = AntiPatternIdentifier.identifySTR(modelspanel.getOntoUMLModel());
		
		String result = new String();
		if (aclist.size()>0) result += "AC AntiPattern : "+aclist.size()+" items founded.\n";
		if (rslist.size()>0) result += "RS AntiPattern : "+rslist.size()+" items founded.\n";
		if (rboslist.size()>0) result += "RBOS AntiPattern : "+rboslist.size()+" items founded.\n";
		if (rworlist.size()>0) result += "RWOR AntiPattern : "+rworlist.size()+" items founded.\n";
		if (ialist.size()>0) result += "IA AntiPattern : "+ialist.size()+" items founded.\n";
		if (strlist.size()>0) result += "STR AntiPattern : "+strlist.size()+" items founded.\n";
		
		if (!result.isEmpty()) JOptionPane.showMessageDialog(this,result,"AntiPatterns",JOptionPane.INFORMATION_MESSAGE);
		else JOptionPane.showMessageDialog(this,"No AntiPatterns Found.","AntiPatterns",JOptionPane.INFORMATION_MESSAGE);
		*/		
		
	}	
}
