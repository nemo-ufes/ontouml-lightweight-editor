package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLCheckBoxTree;
import br.ufes.inf.nemo.move.util.ToolbarButton;

/**
 * @author John Guerson
 */

public class TheToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;

	private TheFrame frame;		
	private ToolbarButton btnSearchForAntipatterns;	
	private ToolbarButton btnShowHideConsole;
	private ToolbarButton btnAlloyAnalyzer;
	private ToolbarButton btnTreeTeste;
	
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
		createTreeTesteButton();
		
        JSeparator toolBarSeparator0 = new Separator();  
        toolBarSeparator0.setVisible(false);
        toolBarSeparator0.setOrientation( SwingConstants.VERTICAL );  
        add( toolBarSeparator0 );  
        
		createShowHideConsole();
		
        JSeparator toolBarSeparator1 = new Separator();  
        toolBarSeparator1.setVisible(false);
        toolBarSeparator1.setOrientation( SwingConstants.VERTICAL );  
        add( toolBarSeparator1 );        
       
        createAlloyAnalyzerButton();	
		
        JSeparator toolBarSeparator4 = new Separator();
        toolBarSeparator4.setVisible(false);
        toolBarSeparator4.setOrientation( SwingConstants.VERTICAL );  
        add( toolBarSeparator4 );
        
		createAntiPatternButton();			
	}		

	public void createTreeTesteButton()
	{
		btnTreeTeste = new ToolbarButton("Selection Test","/resources/br/ufes/inf/nemo/move/check-36x36.png");
		btnTreeTeste.setEnabled(true);
		btnTreeTeste.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			List<EObject> selected = OntoUMLCheckBoxTree.getCheckedElements(frame.getOntoUMLView().getModelTree());
       			
       			frame.getOntoUMLModel().getOntoUMLParser().setSelection((ArrayList<EObject>)selected);
       			
       			for (EObject eObject : frame.getOntoUMLModel().getOntoUMLParser().getSelectedElements()) {
					System.out.println(eObject);
				}
       			
       			String result = new String();
       			
       			String log = new String();
       			RefOntoUML.Package p = frame.getOntoUMLModel().getOntoUMLParser().recreatePackageFromSelectedClasses(log, false, new Copier());
       			OntoUMLParser parser2 = new OntoUMLParser(p);
       			result = "SIZE: "+parser2.getElements().size();
       			for(EObject eo: parser2.getElements())
       			{
       				result += eo.toString()+"\n";       				
       			}       			
       			
       			System.out.println("***********\nCheckBox Size: "+selected.size());
       			System.out.println("***********\nOriginal Model Size: "+frame.getOntoUMLModel().getOntoUMLParser().getElements().size());
       			System.out.println("***********\nCopy Size: "+parser2.getElements().size());
       			frame.getConsole().write(result);
       		}
       	});
		add(btnTreeTeste);
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
       			if(frame.getAlloyModel().getAlloyPath().isEmpty() || frame.getAlloyModel().getAlloyPath()==null)
       			{	       			
       				frame.OpenAlloyModelWithAnalyzer();
       			}else{       				
       				frame.ParseOCL(false);       				
       				OptionsDialog.open(frame.getOntoUMLOptionModel(),frame.getOCLOptionModel(),frame);	       			
       			}
       		}
       	});
		add(btnAlloyAnalyzer);
	}
		
	public void createShowHideConsole ()
	{
		btnShowHideConsole = new ToolbarButton("Show Console","/resources/br/ufes/inf/nemo/move/display-36x36.png");
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
			
	public void createAntiPatternButton()
	{
		btnSearchForAntipatterns = new ToolbarButton("Search AntiPatterns","/resources/br/ufes/inf/nemo/move/search-36x36.png");		
		btnSearchForAntipatterns.setToolTipText("Search for AntiPatterns");
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
}
