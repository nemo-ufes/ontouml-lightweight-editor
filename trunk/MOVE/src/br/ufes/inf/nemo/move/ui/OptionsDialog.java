package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

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
	
	private OntoUMLOptionsModel ontoumlOptModel;
	private OCLOptionsModel oclOptModel;	
	private OntoUMLOptionsView ontoumlOptView;
	private OCLOptionsView oclOptView;
	
	private TheFrame frame;
	private JButton btnOk;	
	private JButton btnCancel;	
	private JTabbedPane tabbedPane;
	private JPanel ontoumlpanel;
	private JPanel oclpanel;
	private JPanel btnpanel;
	
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
	public void setOptionDialog (OntoUMLOptionsModel ontoumlOptModel,OCLOptionsModel oclOptModel)
	{
		ontoumlOptView = new OntoUMLOptionsView(ontoumlOptModel,frame);		
		ontoumlpanel.add(ontoumlOptView);
		
		oclOptView = new OCLOptionsView(oclOptModel,frame);
		oclpanel.add(oclOptView);
	}

	/**
	 * Constructor
	 */
	public OptionsDialog() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptionsDialog.class.getResource("/resources/br/ufes/inf/nemo/move/options.png")));
		setTitle("Options");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		ontoumlpanel = new JPanel();
		oclpanel = new JPanel();
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(330,300));
		tabbedPane.setBackground(UIManager.getColor("Panel.background"));		
		tabbedPane.add(ontoumlpanel);
		tabbedPane.add(oclpanel);
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(ontoumlpanel),"OntoUML");
		tabbedPane.setTitleAt(tabbedPane.indexOfComponent(oclpanel),"OCL");	
		getContentPane().add(tabbedPane, BorderLayout.WEST);
				
		setSize(new Dimension(345, 300));
			
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
		btnpanel.setPreferredSize(new Dimension(100, 50));
		GroupLayout gl_btnpanel = new GroupLayout(btnpanel);
		gl_btnpanel.setHorizontalGroup(
			gl_btnpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnpanel.createSequentialGroup()
					.addGap(79)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(56, Short.MAX_VALUE))
		);
		gl_btnpanel.setVerticalGroup(
			gl_btnpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnpanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_btnpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		btnpanel.setLayout(gl_btnpanel);		
		getContentPane().add(btnpanel, BorderLayout.SOUTH);
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
		
    	if(frame.getOCLModel().getOCLParser()!=null)
    	{
    		OCLOptions oclOptions = new OCLOptions(frame.getOCLModel().getOCLParser());    	
    		oclOptModel.setOCLOptions(oclOptions);
    	}
    	
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
