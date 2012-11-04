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

import br.ufes.inf.nemo.move.panel.antipattern.AntiPatternPanel;
import br.ufes.inf.nemo.move.panel.ocl.OCLPanel;
import br.ufes.inf.nemo.move.panel.ontouml.OntoUMLPanel;

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static String dirPath;
	
	private TheToolBar toolBar;	
	private TheMenuBar menuBar;	
	private TheModelsPanel modelspanel;
	private TheConsolePanel consolepanel;	
	private JSplitPane mainSplitPane;	
	private OntoUMLPanel ontoumlpanel;	
	private OCLPanel oclpanel;	
	private AntiPatternPanel antipatternpanel;
	
	
	public TheMenuBar getTheMenuBar()
	{
		return menuBar;
	}
	
	public TheModelsPanel getTheModelsPanel()
	{
		return modelspanel;
	}
	
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
	      	mainSplitPane.setDividerLocation(0.75);
	    }
	}
	
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
		dirPath = alsPath.substring(0, alsPath.lastIndexOf(File.separator)+1);		
	}	
		
	/**
	 * Create the frame.
	 */
	public TheFrame() 
	{
		getContentPane().setBackground(new Color(230, 230, 250));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		menuBar = new TheMenuBar(this);
		setJMenuBar(menuBar);
		
		toolBar = new TheToolBar(this);
		modelspanel = new TheModelsPanel();
		
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
	
		ontoumlpanel = new OntoUMLPanel();
		oclpanel = new OCLPanel();
		antipatternpanel = new AntiPatternPanel();
		
		JSplitPane innerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,oclpanel,antipatternpanel);
		innerSplitPane.setOneTouchExpandable(true);
		innerSplitPane.setDividerLocation(1.0);
		
		JSplitPane centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,ontoumlpanel,innerSplitPane);
		centerSplitPane.setOneTouchExpandable(true);
		centerSplitPane.setDividerLocation(0.75);
		
		consolepanel = new TheConsolePanel();		
		mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,centerSplitPane,consolepanel);
		mainSplitPane.setOneTouchExpandable(true);
		mainSplitPane.setDividerLocation(1.0);
		getContentPane().add(BorderLayout.CENTER,mainSplitPane);
	}
}
