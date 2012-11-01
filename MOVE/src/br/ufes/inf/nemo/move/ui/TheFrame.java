package br.ufes.inf.nemo.move.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.panel.ConsolePanel;
import br.ufes.inf.nemo.move.panel.ConstraintPanel;
import br.ufes.inf.nemo.move.panel.TheModelTreePanel;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.ontouml.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/* toolbar */
	private TheToolBar toolBar; 
	
	/* menubar */
	private TheMenuBar menuBar;	

	/* toolbar buttons */
	private JButton btnExecute;	
	private JButton btnSearchForAntipatterns;	
	private JButton btnSyntaticVerification;

	/* content pane */
	private JPanel contentPane;
	
	/* the models panel */
	private TheModelsPanel modelspanel;
	
	/* the options panel */
	private TheOptionsPanel optionspanel;
	
	/* anti pattern options panel */
	private TheAntiPatternOptionPanel antipatternOptionsPanel;
	
	
	public JTabbedPane tabbedPane;		
	public ConsolePanel consolepanel;	
	public TheModelTreePanel treepanel;		
	public JSplitPane innerSplitPane;	
	public JSplitPane outterSplitPane;	
	public ConstraintPanel constraintpanel;	
	public static String dirPath;
		
	/**
	 * Create the frame
	 */
	public TheFrame (RefOntoUML.Package model, String oclConstraints, String alsPath) throws IOException
	{
		this();
		modelspanel.setOntoUMLModel(model);
		modelspanel.setOCLModel(oclConstraints,2);
		modelspanel.setAlloyPath(alsPath);
		dirPath = alsPath.substring(0, alsPath.lastIndexOf(File.separator)+1);
	}	
		
	/**
	 * Create the frame.
	 */
	public TheFrame() 
	{		
		toolBar = new TheToolBar();
				
		menuBar = new TheMenuBar();
										
		createExecuteButton();
		createSyntaticButton();
		createAntiPatternButton();
		
		setJMenuBar(menuBar);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TheFrame.class.getResource("/resources/br/ufes/inf/nemo/move/window.png")));
		setTitle("OntoUML Model Validation Environment - MOVE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1136, 710);
				
		createContentPane();
	}
	
	/**
	 * Create Syntatic Verification Button
	 */
	public void createSyntaticButton ()
	{
		btnSyntaticVerification = new JButton("Syntatic Verification");
		btnSyntaticVerification.setEnabled(false);
		btnSyntaticVerification.setFocusable(false);
		btnSyntaticVerification.setIcon(new ImageIcon(TheFrame.class.getResource("/resources/br/ufes/inf/nemo/move/check.png")));
		
		btnSyntaticVerification.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			// not implemented yet
       		}
       	});
		
		toolBar.add(btnSyntaticVerification);	
	}
	
	public void createAntiPatternButton()
	{
		btnSearchForAntipatterns = new JButton("Identify AntiPatterns");
		btnSearchForAntipatterns.setEnabled(false);
		btnSearchForAntipatterns.setFocusable(false);
		btnSearchForAntipatterns.setIcon(new ImageIcon(TheFrame.class.getResource("/resources/br/ufes/inf/nemo/move/search.png")));
		
		btnSearchForAntipatterns.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 AntiPatternButtonActionPerformed(event);				
       		}
       	});
		
		toolBar.add(btnSearchForAntipatterns);
	}
	
	/**
	 * Create Execute Button
	 */	
	public void createExecuteButton ()
	{
		btnExecute = new JButton("Execute with Analyzer");
		btnExecute.setFocusable(false);
		btnExecute.setIcon(new ImageIcon(TheFrame.class.getResource("/resources/br/ufes/inf/nemo/move/play.png")));
				
		btnExecute.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 ExecuteButtonActionPerformed(event);					
       		}
       	});
		
		toolBar.add(btnExecute);
	}	

	/**
	 * Searching AntiPatterns.
	 */
	public void  AntiPatternButtonActionPerformed(ActionEvent event)
	{
		try {
			
		
		if (antipatternOptionsPanel.ACisSelected())
		{
			AntiPatternIdentifier.identifyAC(modelspanel.getOntoUMLModel());
		}
		if (antipatternOptionsPanel.RSisSelected())
		{
			AntiPatternIdentifier.identifyRS(modelspanel.getOntoUMLModel());
		}
		if (antipatternOptionsPanel.RBOisSelected())
		{
			AntiPatternIdentifier.identifyRBOS(modelspanel.getOntoUMLModel());
		}
		if (antipatternOptionsPanel.RWOisSelected())
		{
			AntiPatternIdentifier.identifyRWOR(modelspanel.getOntoUMLModel());
		}
		if (antipatternOptionsPanel.IAisSelected())
		{
			AntiPatternIdentifier.identifyIA(modelspanel.getOntoUMLModel());
		}
		if (antipatternOptionsPanel.STRisSelected())
		{
			AntiPatternIdentifier.identifySTR(modelspanel.getOntoUMLModel());
		}
		
		}catch(Exception e){
			
		}
		
	}	
	
	/**	
	 * Executing Validation. 
	 */
	public void ExecuteButtonActionPerformed (ActionEvent arg0)
	{				
		try {
		
		Options opt = optionspanel.getOptions();
		String alsPath = modelspanel.getAlloyPath();
		RefOntoUML.Package refmodel = modelspanel.getOntoUMLModel();
		String oclConstraints = modelspanel.getOCLModel();				
		
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
			AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", dirPath);
			
			String[] argsAnalyzer = { "","" };
			argsAnalyzer[0] = alsPath;
			argsAnalyzer[1] = dirPath + "standart_theme.thm"	;	
			SimpleGUICustom.main(argsAnalyzer);
		}		
		
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}		
	}
	
	/**
	 * Create ContentPane
	 */
	public void createContentPane ()
	{		
		getContentPane().setBackground(UIManager.getColor("inactiveCaption"));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		optionspanel = new TheOptionsPanel();
		
		modelspanel = new TheModelsPanel();
						
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		//tabbedPane.add("Constraint",constraintpanel);
		//tabbedPane.add("AntiPattern",antipatternpanel);
		
		treepanel = new TheModelTreePanel();
		
		innerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treepanel,tabbedPane);
		innerSplitPane.setOneTouchExpandable(true);
		innerSplitPane.setDividerLocation(250);
		
		consolepanel = new ConsolePanel();
		
		outterSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,innerSplitPane,consolepanel);
		outterSplitPane.setOneTouchExpandable(true);
		outterSplitPane.setDividerLocation(350);
		
		antipatternOptionsPanel = new TheAntiPatternOptionPanel();
										
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(outterSplitPane, GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(modelspanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(optionspanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(antipatternOptionsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(modelspanel, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						.addComponent(optionspanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(antipatternOptionsPanel, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(outterSplitPane, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		getContentPane().setLayout(groupLayout);		
	}	
}
