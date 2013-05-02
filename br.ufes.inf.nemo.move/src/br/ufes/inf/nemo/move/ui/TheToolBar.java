package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import br.ufes.inf.nemo.move.ui.dialog.AntiPatternListDialog;
import br.ufes.inf.nemo.move.ui.dialog.AutoCompletionDialog;
import br.ufes.inf.nemo.move.ui.dialog.OptionsDialog;

/**
 * @author John Guerson
 */

public class TheToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;

	private TheFrame frame;		
	private JButton btnSearchForAntipatterns;	
	private JButton btnShowHideConsole;	
	private JButton btnShowOrHideAntiPattern;
	private JButton btnShowOrHideOCL;
	private JButton btnAlloyAnalyzer;
	private JButton btnCompleteSelect;
	private JButton btnShowInstances;
	
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
		createShowHideConsole();		
        createShowHideOCLView();
        createAutoSelectionButton();
        createShowHideAntiPatternView();       
        createAntiPatternButton();		
        createAlloyAnalyzerButton();
	}		
		
	public void createAutoSelectionButton()
	{
		btnCompleteSelect = new JButton();
		btnCompleteSelect.setText("Auto Completion");
		btnCompleteSelect.setIcon(new ImageIcon(TheToolBar.class.getResource("/resources/icon/completion-36x36.png")));
		btnCompleteSelect.setToolTipText("Automatically select elements of the model");
		btnCompleteSelect.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCompleteSelect.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCompleteSelect.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				if(frame.getManager().getOntoUMLModel().getOntoUMLParser()==null)
       			{	       			
       				frame.showInformationMessageDialog("Auto Completion","First you need to load your Model"); 
       				
       			}else{
       				
       				AutoCompletionDialog.open(frame);
       			}
			}
		});				
		add(btnCompleteSelect);
	}
	
	public void createAlloyAnalyzerButton()
	{
		btnAlloyAnalyzer = new JButton();
		btnAlloyAnalyzer.setToolTipText("Transforming models and rules to Alloy");
		btnAlloyAnalyzer.setIcon(new ImageIcon(TheToolBar.class.getResource("/resources/icon/alloy-36x36.png")));
		btnAlloyAnalyzer.setText("Alloy Analyzer");		
		btnAlloyAnalyzer.setEnabled(true);
		btnAlloyAnalyzer.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAlloyAnalyzer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAlloyAnalyzer.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			if(frame.getManager().getOntoUMLModel().getOntoUMLParser()==null)
       			{	       			
       				frame.showInformationMessageDialog("Launch Analyzer","First you need to load your Model"); 
       				
       			}else{
       				
       				frame.getManager().ParseOCL(false);
       				
       				frame.getManager().getOntoUMLOptionModel().getOptions().openAnalyzer=true;
       				
       				OptionsDialog.open(frame.getManager().getOntoUMLOptionModel(),frame.getManager().getOCLOptionModel(),frame);       				
       			}
       		}
       	});
		add(btnAlloyAnalyzer);
	}
	
	public void createShowInstances()
	{
		btnShowInstances = new JButton();
		btnShowInstances.setIcon(new ImageIcon(TheToolBar.class.getResource("/resources/icon/atom-36x36.png")));
		btnShowInstances.setText("Show Instances");	
		btnShowInstances.setToolTipText("");
		btnShowInstances.setEnabled(false);
		btnShowInstances.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnShowInstances.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShowInstances.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			if(frame.getManager().getOntoUMLModel().getOntoUMLParser()==null)
       			{	       			
       				frame.showInformationMessageDialog("Show Instances","First you need to load your Model"); 
    				       				
       			}else{ 
       				
       				frame.getManager().ParseOCL(false);
       				
       				frame.getManager().getOntoUMLOptionModel().getOptions().openAnalyzer=false;
       				
       				OptionsDialog.open(frame.getManager().getOntoUMLOptionModel(),frame.getManager().getOCLOptionModel(),frame);       				
       			}
       		}
       	});
		add(btnShowInstances);
	}
		
	public void createShowHideConsole ()
	{
		btnShowHideConsole = new JButton();
		btnShowHideConsole.setIcon(new ImageIcon(TheToolBar.class.getResource("/resources/icon/display-36x36.png")));
		btnShowHideConsole.setText("Console");	
		btnShowHideConsole.setToolTipText("Show/Hide Console View");
		btnShowHideConsole.setEnabled(true);
		btnShowHideConsole.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnShowHideConsole.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShowHideConsole.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.ShowOrHideConsole();
       		}
       	});
		add(btnShowHideConsole);
	}
	
	public void createShowHideAntiPatternView ()
	{
		btnShowOrHideAntiPattern = new JButton();
		btnShowOrHideAntiPattern.setIcon(new ImageIcon(TheToolBar.class.getResource("/resources/icon/panel-36X36.png")));
		btnShowOrHideAntiPattern.setText("AntiPattern Manager");
		btnShowOrHideAntiPattern.setToolTipText("Show/Hide AntiPattern Manager View");
		btnShowOrHideAntiPattern.setEnabled(true);
		btnShowOrHideAntiPattern.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnShowOrHideAntiPattern.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShowOrHideAntiPattern.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.ShowOrHideAntiPatternView();
       		}
       	});
		add(btnShowOrHideAntiPattern);
	}
	
	public void createShowHideOCLView ()
	{
		btnShowOrHideOCL = new JButton();
		btnShowOrHideOCL.setIcon(new ImageIcon(TheToolBar.class.getResource("/resources/icon/edit-36x36.png")));
		btnShowOrHideOCL.setText("OCL Editor");
		btnShowOrHideOCL.setToolTipText("Show/Hide OCL Editor View");
		btnShowOrHideOCL.setEnabled(true);
		btnShowOrHideOCL.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnShowOrHideOCL.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShowOrHideOCL.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.ShowOrHideOCLView();
       		}
       	});
		add(btnShowOrHideOCL);
	}
	
	public void createAntiPatternButton()
	{
		btnSearchForAntipatterns = new JButton();		
		btnSearchForAntipatterns.setText("Detect AntiPatterns");
		btnSearchForAntipatterns.setIcon(new ImageIcon(TheToolBar.class.getResource("/resources/icon/antipattern-36x36.png")));
		btnSearchForAntipatterns.setToolTipText("Search for model antipatterns");
		btnSearchForAntipatterns.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSearchForAntipatterns.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearchForAntipatterns.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				if (frame.getManager().getOntoUMLModel().getOntoUMLParser()==null) 
				{ 
					frame.showInformationMessageDialog("Detect AntiPatterns","First you need to load your Model"); 
					return; 
				}
				
				try {
					
					AntiPatternListDialog.open(frame);
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(frame,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
					e.printStackTrace();
				}					
			}
		});
		add(btnSearchForAntipatterns);		
	}	
}
