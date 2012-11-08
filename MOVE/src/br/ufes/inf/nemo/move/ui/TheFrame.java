package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.panel.antipattern.TheAntiPatternPanel;
import br.ufes.inf.nemo.move.panel.ocl.TheConstraintPanel;
import br.ufes.inf.nemo.move.panel.ontouml.TheModelPanel;

/**
 * @author John Guerson
 */

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static String dirPath;
	
	private TheToolBar toolBar;	
	private TheMenuBar menuBar;	
	private TheStatusBar statuspanel;	
	private TheConsole consolepanel;
	private TheModelBar modelspanel;
	
	private JSplitPane mainSplitPane;
	private JSplitPane innerSplitPane;
	private JSplitPane centerSplitPane;
	
	private TheModelPanel ontoumlpanel;	
	private TheConstraintPanel oclpanel;	
	private TheAntiPatternPanel antipatternpanel;
		
	public TheConsole getTheConsolePanel()
	{
		return consolepanel;
	}
	
	public TheMenuBar getTheMenuBar()
	{
		return menuBar;
	}
	
	public TheModelBar getTheModelsBar()
	{
		return modelspanel;
	}
	
	public TheModelPanel getTheModelPanel()
	{
		return ontoumlpanel;
	}
	
	public TheAntiPatternPanel getAntiPatternPanel()
	{
		return antipatternpanel;
	}
	
	public void ShowOrHideAntiPatternPanel()
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
	
	public void ShowAntiPattern() { innerSplitPane.setDividerLocation(0.50); }
	public void HideAntiPattern() { innerSplitPane.setDividerLocation(1.00); }
	
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
	 * Create the frame.
	 * 
	 * @param model
	 * @param oclConstraints
	 * @param alsPath
	 * @throws IOException
	 */
	public TheFrame (RefOntoUML.Package model, String oclConstraints, String alsPath) throws IOException
	{
		this();
		modelspanel.setOntoUMLModel(model);
		modelspanel.setOCLModel(oclConstraints,2);
		modelspanel.setAlloyPath(alsPath);
		modelspanel.setUMLPath(alsPath.replace(".als", ".uml"));
		dirPath = alsPath.substring(0, alsPath.lastIndexOf(File.separator)+1);
		
		ontoumlpanel.setModelTree(modelspanel.getOntoUMLModel());
	}	

	/**
	 * Create the frame.
	 */
	public TheFrame() 
	{
		super(); 
		
		getContentPane().setBackground(new Color(230, 230, 250));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		menuBar = new TheMenuBar(this);
		setJMenuBar(menuBar);
		
		toolBar = new TheToolBar(this);
		modelspanel = new TheModelBar();
		
		JPanel headpanel = new JPanel();
		headpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		headpanel.setLayout(new BorderLayout(0, 0));
		headpanel.add(BorderLayout.NORTH,toolBar);
		headpanel.add(BorderLayout.CENTER,modelspanel);
		
		getContentPane().add(BorderLayout.NORTH,headpanel);		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TheFrame.class.getResource("/resources/br/ufes/inf/nemo/move/window.png")));
		setTitle("OntoUML Model Validation Environment - MOVE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 930, 710);
			
		oclpanel = new TheConstraintPanel();
		antipatternpanel = new TheAntiPatternPanel(this);
		
		innerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,oclpanel,antipatternpanel);
		innerSplitPane.setOneTouchExpandable(true);		

		ontoumlpanel = new TheModelPanel();
		
		centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,ontoumlpanel,innerSplitPane);
		centerSplitPane.setOneTouchExpandable(true);		
		
		consolepanel = new TheConsole();		
		mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,centerSplitPane,consolepanel);
		mainSplitPane.setOneTouchExpandable(true);		
		getContentPane().add(BorderLayout.CENTER,mainSplitPane);
		
		statuspanel = new TheStatusBar();
		getContentPane().add(BorderLayout.SOUTH,statuspanel);
		
		pack();
		
		mainSplitPane.setDividerLocation(1.0);
		centerSplitPane.setDividerLocation(0.35);
		innerSplitPane.setDividerLocation(0.50);
	}
}
