package br.ufes.inf.nemo.move.ui.dialog;

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

import br.ufes.inf.nemo.move.mvc.model.OCLOptionsModel;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLOptionsModel;
import br.ufes.inf.nemo.move.mvc.view.OCLOptionsView;
import br.ufes.inf.nemo.move.mvc.view.OntoUMLOptionsView;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ocl2alloy.options.OCLOptions;
import br.ufes.inf.nemo.ontouml2alloy.options.OntoUMLOptions;

/**
 * @author John Guerson
 */

public class OptionsDialog extends JDialog {
	
	private static final long serialVersionUID = 7877781445149017806L;
		
	private OCLOptionsModel oclOptModel;
	private OCLOptionsView oclOptView;	
	private OntoUMLOptionsModel ontoumlOptModel;
	private OntoUMLOptionsView ontoumlOptView;	
	
	private TheFrame frame;
	private JButton btnOk;	
	private JButton btnCancel;	
	private JPanel btnpanel;
	private JTabbedPane tabbedPane;
	
	/**
	 * Launch the Dialog.
	 */
	public static void open(OntoUMLOptionsModel ontoumlOptModel, OCLOptionsModel oclOptModel, TheFrame frame)
	{
		try {
			
			OptionsDialog dialog = new OptionsDialog(ontoumlOptModel,oclOptModel,frame);
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
	 * @param ontoumlOptModel
	 * @param oclOptModel
	 * @param frame
	 */
	public OptionsDialog(OntoUMLOptionsModel ontoumlOptModel, OCLOptionsModel oclOptModel, TheFrame frame)
	{
		this(frame);
		
		this.ontoumlOptModel = ontoumlOptModel;		
		this.oclOptModel = oclOptModel;
		this.frame = frame;		
					
		setOptionDialog(ontoumlOptModel,oclOptModel);
	}
	
	/**
	 * Set Options Dialog.
	 * 
	 * @param ontoumlOptModel
	 * @param oclOptModel
	 */
	public void setOptionDialog (OntoUMLOptionsModel ontoumlOptModel, OCLOptionsModel oclOptModel)
	{
		ontoumlOptView.setOntoUMLOptionsView(ontoumlOptModel);				
		oclOptView.setOCLOptionView(oclOptModel);
		
		validate();
		repaint();
	}
	
	/**
	 * Constructor
	 */
	public OptionsDialog(TheFrame frame) 
	{
		super(frame);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptionsDialog.class.getResource("/resources/icon/options.png")));
		setTitle("Options");
		setSize(new Dimension(611, 349));
		
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
		
		ontoumlOptView = new OntoUMLOptionsView();		
		ontoumlOptView.setBorder(new EmptyBorder(0, 0, 0, 0));
		oclOptView = new OCLOptionsView();
		
		tabbedPane = new JTabbedPane();		
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setBackground(UIManager.getColor("Panel.background"));
		tabbedPane.add(ontoumlOptView,"OntoUML Conceptual Model");
		tabbedPane.add(oclOptView,"OCL Domain Rules");
		
		getContentPane().setLayout(new BorderLayout(0, 0));		
		getContentPane().add(btnpanel, BorderLayout.SOUTH);		
		getContentPane().add(tabbedPane, BorderLayout.WEST);				
	}
			
	public void OkActionPerformed(ActionEvent event)
	{
		OntoUMLOptions ontoumlOptions = new OntoUMLOptions();
		ontoumlOptions.antiRigidity = ontoumlOptView.isSelectedAntirigidity(); 
		ontoumlOptions.identityPrinciple = ontoumlOptView.isSelectedIdentityPrinciple();
		ontoumlOptions.weakSupplementationConstraint = ontoumlOptView.isSelectedWeakSupplementation();
		ontoumlOptions.relatorConstraint = ontoumlOptView.isSelectedRelatorConstraint();
		ontoumlOptions.openAnalyzer = ontoumlOptView.isSelectedOpenAnalyzer();	    	
		ontoumlOptModel.setOptions(ontoumlOptions);
		
		OCLOptions oclOptions = new OCLOptions(frame.getManager().getOCLModel().getOCLParser());		
		oclOptions.setTransformationType(oclOptView.getTransformationsTypesListSelected());
    	oclOptions.setCommandScope(oclOptView.getScopesListSelected());    			
		oclOptions.setConstraintList(oclOptView.getConstraintListSelected());
    	oclOptModel.setOCLOptions(oclOptions);
		
    	dispose();
    	
    	frame.getManager().doOntoUMLToAlloy();
    	
    	frame.getManager().doOCLToAlloy();
    	
    	if (ontoumlOptions.openAnalyzer)
    		frame.getManager().doOpeningAlloy(true,-1);
    	else
    		frame.getManager().doOpeningAlloy(false,0);
	}
	
	public TheFrame getTheFrame()
	{
		return frame;
	}
}
