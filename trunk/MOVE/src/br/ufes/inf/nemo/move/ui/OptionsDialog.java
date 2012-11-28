package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import br.ufes.inf.nemo.move.mvc.model.OCLOptionsModel;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLOptionsModel;
import br.ufes.inf.nemo.move.mvc.view.OCLOptionsView;
import br.ufes.inf.nemo.move.mvc.view.OntoUMLOptionsView;
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
		this();
		
		this.ontoumlOptModel = ontoumlOptModel;		
		this.oclOptModel = oclOptModel;
		this.frame = frame;		
		
		setLocationRelativeTo(frame);			
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
	public OptionsDialog() 
	{
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptionsDialog.class.getResource("/resources/br/ufes/inf/nemo/move/options.png")));
		setTitle("Options");
		setSize(new Dimension(800, 349));
		
		btnOk = new JButton("OK");		
		btnOk.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			OkActionPerformed(event);
       		}
       	});
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
       	});
		
		btnpanel = new JPanel();	
		btnpanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnpanel.setPreferredSize(new Dimension(100, 40));
		
		ontoumlOptView = new OntoUMLOptionsView();		
		ontoumlOptView.setBorder(null);
		oclOptView = new OCLOptionsView();
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(btnpanel, BorderLayout.SOUTH);
		btnpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnpanel.add(btnOk);
		btnpanel.add(btnCancel);
		getContentPane().add(ontoumlOptView, BorderLayout.WEST);
		getContentPane().add(oclOptView);		
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
		
		OCLOptions oclOptions = new OCLOptions(frame.getOCLModel().getOCLParser());		
		oclOptions.setTransformationType(oclOptView.getTransformationsTypesListSelected());
    	oclOptions.setCommandScope(oclOptView.getScopesListSelected());    			
		oclOptions.setConstraintList(oclOptView.getConstraintListSelected());
    	oclOptModel.setOCLOptions(oclOptions);
		
    	dispose();
    	
    	frame.TransformsOntoUMLIntoAlloy();
    	frame.TransformsOCLIntoAlloy();
    	frame.OpenAlloyModelWithAnalyzer();	 
	}
	
	public TheFrame getTheFrame()
	{
		return frame;
	}
}
