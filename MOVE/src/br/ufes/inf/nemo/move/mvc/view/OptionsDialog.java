package br.ufes.inf.nemo.move.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

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
import br.ufes.inf.nemo.move.ui.TheFrame;

/**
 * @author John Guerson
 */

public class OptionsDialog extends JDialog {
	
	private static final long serialVersionUID = 7877781445149017806L;
	
	@SuppressWarnings("unused")
	private OntoUMLOptionsModel ontoumlOptModel;
	@SuppressWarnings("unused")
	private OCLOptionsModel oclOptModel;
	
	private TheFrame frame;
	private JButton btnOk;	
	private JButton btnCancel;	
	private JTabbedPane ontoumlTabbedPane;
	private JPanel ontoumlpanel;
	private JPanel oclpanel;
	private JTabbedPane oclTabbedPane;
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
		OntoUMLOptionsView ontoumlOptView = new OntoUMLOptionsView(ontoumlOptModel,frame);		
		ontoumlpanel.add(ontoumlOptView);
		
		OCLOptionsView oclOptView = new OCLOptionsView(oclOptModel,frame);
		oclpanel.add(oclOptView);
	}

	/**
	 * Constructor
	 */
	public OptionsDialog() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptionsDialog.class.getResource("/resources/br/ufes/inf/nemo/move/options.png")));
		setPreferredSize(new Dimension(323,300));
		setTitle("Options");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		ontoumlpanel = new JPanel();		
		ontoumlTabbedPane = new JTabbedPane();		
		ontoumlTabbedPane.setBackground(UIManager.getColor("Panel.background"));		
		ontoumlTabbedPane.add(ontoumlpanel);	
		ontoumlTabbedPane.setTitleAt(ontoumlTabbedPane.indexOfComponent(ontoumlpanel),"OntoUML Conceptual Model");				
		getContentPane().add(ontoumlTabbedPane, BorderLayout.WEST);

		oclpanel = new JPanel();		
		oclTabbedPane = new JTabbedPane();		
		oclTabbedPane.setBackground(UIManager.getColor("Panel.background"));		
		oclTabbedPane.add(oclpanel);	
		oclTabbedPane.setTitleAt(oclTabbedPane.indexOfComponent(oclpanel),"OCL Domain Constraints");				
		getContentPane().add(oclTabbedPane, BorderLayout.CENTER);
		
				
		btnOk = new JButton("OK");		
		btnCancel = new JButton("Cancel");
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
			
	public TheFrame getTheFrame()
	{
		return frame;
	}
}
