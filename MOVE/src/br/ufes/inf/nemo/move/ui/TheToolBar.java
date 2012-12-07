package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.ui.dialog.AntiPatternListDialog;
import br.ufes.inf.nemo.move.ui.dialog.CompleteSelectionDialog;
import br.ufes.inf.nemo.move.ui.dialog.OptionsDialog;
import br.ufes.inf.nemo.move.util.ToolbarButton;

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
	public ToolbarButton btnCompleteSelect;
	
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
		
        JSeparator toolBarSeparator1 = new Separator();  
        toolBarSeparator1.setVisible(false);
        toolBarSeparator1.setOrientation( SwingConstants.VERTICAL );  
        add( toolBarSeparator1 );        
       
        createShowHideOCLView();
        
        JSeparator toolBarSeparator3 = new Separator();  
        toolBarSeparator3.setVisible(false);
        toolBarSeparator3.setOrientation( SwingConstants.VERTICAL );  
        add( toolBarSeparator3 );  
        
        createShowHideAntiPatternView();
        
        JSeparator toolBarSeparator0= new Separator();  
        toolBarSeparator0.setVisible(false);
        toolBarSeparator0.setOrientation( SwingConstants.VERTICAL );  
        add( toolBarSeparator0 );
        
        createAutoSelectionButton();
        
        JSeparator toolBarSeparator2= new Separator();  
        toolBarSeparator2.setVisible(false);
        toolBarSeparator2.setOrientation( SwingConstants.VERTICAL );  
        add( toolBarSeparator2 );
        
        createAntiPatternButton();		
		
        JSeparator toolBarSeparator4 = new Separator();
        toolBarSeparator4.setVisible(false);
        toolBarSeparator4.setOrientation( SwingConstants.VERTICAL );  
        add( toolBarSeparator4 );
        
        createAlloyAnalyzerButton();			
	}		
		
	public void createAutoSelectionButton()
	{
		btnCompleteSelect = new ToolbarButton("Complete Selection","/resources/br/ufes/inf/nemo/move/autoselection-36x36.png");
		btnCompleteSelect.setToolTipText("Complete Model Selection");
		
		btnCompleteSelect.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				CompleteSelectionDialog.open(frame);
			}
		});				
		add(btnCompleteSelect);
	}
	
	public void createAlloyAnalyzerButton()
	{
		btnAlloyAnalyzer = new ToolbarButton("Launch Analyzer","/resources/br/ufes/inf/nemo/move/alloy-36x36.png");
		btnAlloyAnalyzer.setToolTipText("Run Validation With Analyzer");
		btnAlloyAnalyzer.setEnabled(true);
		btnAlloyAnalyzer.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			if(frame.getManager().getOntoUMLModel().getOntoUMLParser()==null)
       			{	       			
       				frame.getManager().OpenAlloyModelWithAnalyzer(frame.getManager().getAlloyModel().getAlloyPath(),AlloyModel.alsOutDirectory);
       			}else{ 
       				frame.getManager().ParseOCL(false);       				
       				OptionsDialog.open(frame.getManager().getOntoUMLOptionModel(),frame.getManager().getOCLOptionModel(),frame);       				
       			}
       		}
       	});
		add(btnAlloyAnalyzer);
	}
		
	public void createShowHideConsole ()
	{
		btnShowHideConsole = new ToolbarButton("Console","/resources/br/ufes/inf/nemo/move/display-36x36.png");
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
		btnShowOrHideAntiPattern = new ToolbarButton("AntiPatterns","/resources/br/ufes/inf/nemo/move/panel-36x36.png");
		btnShowOrHideAntiPattern.setToolTipText("Show/Hide AntiPatterns");
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
		btnShowOrHideOCL = new ToolbarButton("Constraints","/resources/br/ufes/inf/nemo/move/edit-36x36.png");
		btnShowOrHideOCL.setToolTipText("Show/Hide OCL");
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
		btnSearchForAntipatterns = new ToolbarButton("Search AntiPatterns","/resources/br/ufes/inf/nemo/move/search-36x36.png");		
		btnSearchForAntipatterns.setToolTipText("Search for AntiPatterns");
		btnSearchForAntipatterns.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				if (frame.getManager().getOntoUMLModel().getOntoUMLParser()==null) { frame.getConsole().write("First you need to load the Model"); return; }
				
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
