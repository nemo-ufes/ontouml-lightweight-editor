package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import br.ufes.inf.nemo.move.ui.dialog.AntiPatternListDialog;
import br.ufes.inf.nemo.move.util.ui.ToolbarButton;

/**
 * @author John Guerson
 */

public class TheToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;

	private TheFrame frame;		
	private ToolbarButton btnSyntaticVerification;	
	private ToolbarButton btnSearchForAntipatterns;	
	private ToolbarButton btnExecute;	
	private ToolbarButton btnOutput;
	private ToolbarButton btnInfo;
	
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
		
		createInfoButton();
		
		createOutputButton();		
	}		

	/** Verify */
	public void createSyntaticButton ()
	{
		btnSyntaticVerification = new ToolbarButton("Verify","/resources/br/ufes/inf/nemo/move/check-36x36.png");						
		btnSyntaticVerification.setEnabled(false);
		btnSyntaticVerification.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			// TO DO !
       		}
       	});
		add(btnSyntaticVerification);
	}
	
	/** Execute */
	public void createExecuteButton ()	
	{
		btnExecute = new ToolbarButton("Validate","/resources/br/ufes/inf/nemo/move/play-36x36.png");		
		btnExecute.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getOptionView().setVisible(true);					
       		}
       	});
		add(btnExecute);
	}
	
	/** AntiPattern */
	public void createAntiPatternButton()
	{
		btnSearchForAntipatterns = new ToolbarButton("AntiPatterns","/resources/br/ufes/inf/nemo/move/search-36x36.png");		
		btnSearchForAntipatterns.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
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

	/** Information */
	public void createInfoButton()
	{
		btnInfo = new ToolbarButton("Information","/resources/br/ufes/inf/nemo/move/info-36x36.png");				
		btnInfo.setEnabled(false);
		btnInfo.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			// TO DO!
       		}
       	});
		add(btnInfo);	
	}
	
	/** File Output Location */
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
	
}
