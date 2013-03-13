package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import br.ufes.inf.nemo.move.ui.dialog.AntiPatternListDialog;
import br.ufes.inf.nemo.move.ui.dialog.AutoCompletionDialog;
import br.ufes.inf.nemo.move.ui.dialog.OptionsDialog;
import br.ufes.inf.nemo.move.ui.util.ToolbarButton;

/**
 * @author John Guerson
 */

public class TheToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;

	private TheFrame frame;		
	private ToolbarButton btnSearchForAntipatterns;	
	private ToolbarButton btnShowHideConsole;	
	private ToolbarButton btnShowOrHideAntiPattern;
	private ToolbarButton btnShowOrHideOCL;
	private ToolbarButton btnAlloyAnalyzer;
	private ToolbarButton btnCompleteSelect;
	private ToolbarButton btnShowInstances;
	//private JSeparator toolBarSeparator1;
	//private JSeparator toolBarSeparator3;
	//private JSeparator toolBarSeparator0;
	//private JSeparator toolBarSeparator2;
	//private JSeparator toolBarSeparator4;
	//private JSeparator toolBarSeparator5;
	
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
		
        //toolBarSeparator1 = new Separator();
        //toolBarSeparator1.setVisible(false);
        //toolBarSeparator1.setOrientation( SwingConstants.VERTICAL );  
        //add(toolBarSeparator1);        
       
        createShowHideOCLView();
        
        //toolBarSeparator3 = new Separator();  
        //toolBarSeparator3.setVisible(false);
        //toolBarSeparator3.setOrientation( SwingConstants.VERTICAL );  
        //add(toolBarSeparator3);  
        
        createShowHideAntiPatternView();
        
        //toolBarSeparator0= new Separator();  
        //toolBarSeparator0.setVisible(false);
        //toolBarSeparator0.setOrientation( SwingConstants.VERTICAL );  
       // add(toolBarSeparator0);
        
        createAutoSelectionButton();
        
        //toolBarSeparator2= new Separator();  
       // toolBarSeparator2.setVisible(false);
        //toolBarSeparator2.setOrientation( SwingConstants.VERTICAL );  
        //add(toolBarSeparator2);
        
        createAntiPatternButton();		
		
       // toolBarSeparator4 = new Separator();
       // toolBarSeparator4.setVisible(false);
       // toolBarSeparator4.setOrientation( SwingConstants.VERTICAL );  
       // add(toolBarSeparator4);
        
        createAlloyAnalyzerButton();	

        //toolBarSeparator5 = new Separator();
        //toolBarSeparator5.setVisible(false);
        //toolBarSeparator5.setOrientation( SwingConstants.VERTICAL );  
        //add( toolBarSeparator5 );
        
        //createShowInstances();
	}		
		
	public void createAutoSelectionButton()
	{
		btnCompleteSelect = new ToolbarButton("Auto Completion","/resources/icon/completion-36x36.png");
		btnCompleteSelect.setToolTipText("Automatically select elements of the model");
		
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
		btnAlloyAnalyzer = new ToolbarButton("Show Analyzer","/resources/icon/alloy-36x36.png");
		btnAlloyAnalyzer.setText("Validate with Analyzer");
		btnAlloyAnalyzer.setToolTipText("Transforming models and rules to Alloy");
		btnAlloyAnalyzer.setEnabled(true);
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
		btnShowInstances = new ToolbarButton("Show Instances","/resources/icon/atom-36x36.png");
		btnShowInstances.setToolTipText("");
		btnShowInstances.setEnabled(false);
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
		btnShowHideConsole = new ToolbarButton("Console","/resources/icon/display-36x36.png");
		btnShowHideConsole.setToolTipText("Show/Hide Console");
		btnShowHideConsole.setEnabled(true);
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
		btnShowOrHideAntiPattern = new ToolbarButton("AntiPatterns","/resources/icon/panel-36X36.png");
		btnShowOrHideAntiPattern.setText("AntiPattern");
		btnShowOrHideAntiPattern.setToolTipText("Show/Hide AntiPatterns Tab");
		btnShowOrHideAntiPattern.setEnabled(true);
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
		btnShowOrHideOCL = new ToolbarButton("Constraints","/resources/icon/edit-36x36.png");
		btnShowOrHideOCL.setText("Rules ");
		btnShowOrHideOCL.setToolTipText("Show/Hide OCL Tab");
		btnShowOrHideOCL.setEnabled(true);
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
		btnSearchForAntipatterns = new ToolbarButton("Detect AntiPatterns","/resources/icon/antipattern-36x36.png");		
		btnSearchForAntipatterns.setText("Detect AntiPatterns");
		btnSearchForAntipatterns.setToolTipText("Search for model antipatterns");
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
