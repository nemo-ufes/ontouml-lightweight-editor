package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.ocl.ocl2alloy.OCL2AlloyOptions;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.ui.OCL2AlloyOptionsPane;
import br.ufes.inf.nemo.oled.ui.OntoUML2AlloyOptionsPane;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * @author John Guerson
 */

public class SimulationOptionsDialog extends JDialog {
	
	private static final long serialVersionUID = 7877781445149017806L;
	
	private OCL2AlloyOptionsPane oclOptPane;
	private OntoUML2AlloyOptionsPane ontoumlOptPane;	
	
	private AppFrame frame;
	private JButton btnOk;	
	private JButton btnCancel;	
	private JPanel btnpanel;
	private JTabbedPane tabbedPane;
	
	/**
	 * Launch the Dialog.
	 */
	public static void open(OntoUML2AlloyOptions refOptions, OCL2AlloyOptions oclOptions, AppFrame frame)
	{
		try {
			
			SimulationOptionsDialog dialog = new SimulationOptionsDialog(refOptions,oclOptions,frame);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(frame);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor.
	 * 
	 * @param refOptions
	 * @param oclOptions
	 * @param frame
	 * @wbp.parser.constructor
	 */
	public SimulationOptionsDialog(OntoUML2AlloyOptions refOptions, OCL2AlloyOptions oclOptions, AppFrame frame)
	{
		this(frame);
		this.frame = frame;		
					
		setOptionDialog(refOptions,oclOptions);
	}
	
	/**
	 * Set Options Dialog.
	 * 
	 * @param refOptions
	 * @param oclOptions
	 */
	public void setOptionDialog (OntoUML2AlloyOptions refOptions, OCL2AlloyOptions oclOptions)
	{
		ontoumlOptPane.setOntoUMLOptionsPane(refOptions);				
		oclOptPane.setOCLOptionPane(oclOptions);
		
		validate();
		repaint();
	}
	
	/**
	 * Constructor
	 */
	public SimulationOptionsDialog(AppFrame frame) 
	{
		super(frame);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SimulationOptionsDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/options.png")));
		setTitle("Options");
		setSize(new Dimension(611, 400));
		
		btnOk = new JButton("OK");	
		btnOk.setPreferredSize(new Dimension(100, 25));
		btnOk.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			OkActionPerformed(event);
       		}
       	});
		
		btnCancel = new JButton("Cancel");
		btnCancel.setPreferredSize(new Dimension(100, 25));
		btnCancel.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
       	});
		
		btnpanel = new JPanel();
		btnpanel.setPreferredSize(new Dimension(100, 40));
		btnpanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		btnpanel.add(btnOk);
		btnpanel.add(btnCancel);
		
		ontoumlOptPane = new OntoUML2AlloyOptionsPane();		
		ontoumlOptPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		oclOptPane = new OCL2AlloyOptionsPane();
		
		tabbedPane = new JTabbedPane();		
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setBackground(UIManager.getColor("Panel.background"));
		tabbedPane.add(ontoumlOptPane,"OntoUML Model");
		tabbedPane.add(oclOptPane,"OCL Constraints");
		
		getContentPane().setLayout(new BorderLayout(0, 0));		
		getContentPane().add(btnpanel, BorderLayout.SOUTH);		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);				
	}
			
	public void OkActionPerformed(ActionEvent event)
	{
		OntoUML2AlloyOptions ontoumlOptions = new OntoUML2AlloyOptions();
		ontoumlOptions.antiRigidity = ontoumlOptPane.isSelectedAntirigidity(); 
		ontoumlOptions.identityPrinciple = ontoumlOptPane.isSelectedIdentityPrinciple();
		ontoumlOptions.weakSupplementationAxiom = ontoumlOptPane.isSelectedWeakSupplementation();
		ontoumlOptions.relatorAxiom = ontoumlOptPane.isSelectedRelatorConstraint();			    	
		
		ProjectBrowser.setOntoUMLOptionsFor(frame.getDiagramManager().getCurrentProject(),ontoumlOptions);
		
		OCLDocument oclmodel = ProjectBrowser.getOCLModelFor(frame.getDiagramManager().getCurrentProject());
		OCL2AlloyOptions oclOptions = new OCL2AlloyOptions(oclmodel.getOCLParser());		
		oclOptions.setTransformationType(oclOptPane.getTransformationsTypesListSelected());
    	oclOptions.setCommandScope(oclOptPane.getScopesListSelected());    			
		//oclOptions.setConstraintList(oclOptView.getConstraintListSelected());
    	
    	ProjectBrowser.setOCLOptionsFor(frame.getDiagramManager().getCurrentProject(), oclOptions);
		  
    	dispose();
    
    	frame.getDiagramManager().generatesAlloy();		  		
	}
	
	public AppFrame getFrame()
	{
		return frame;
	}
}
