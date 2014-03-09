package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.ufes.inf.nemo.ocl.ocl2alloy.OCL2AlloyOption;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;
import br.ufes.inf.nemo.tocl.tocl2alloy.TOCL2AlloyOption;

/**
 * @author John Guerson
 */

public class SimulationDialog extends JDialog {
	
	private static final long serialVersionUID = 7877781445149017806L;
	
	private ConstraintSimulationPanel constraintSimulationPanel;
	private ModelSimulationPanel modelSimulationPanel;	
	private AppFrame frame;
	private JButton btnOk;	
	private JButton btnCancel;	
	private JPanel btnpanel;	
	
	/**
	 * Launch the Dialog.
	 */
	public static void open(OntoUML2AlloyOptions refOptions, OCL2AlloyOption oclOptions, AppFrame frame)
	{
		try {
			
			SimulationDialog dialog = new SimulationDialog(refOptions,oclOptions,frame);
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
	public SimulationDialog(OntoUML2AlloyOptions refOptions, OCL2AlloyOption oclOptions, AppFrame frame)
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
	public void setOptionDialog (OntoUML2AlloyOptions refOptions, OCL2AlloyOption oclOptions)
	{
		modelSimulationPanel.setOntoUMLOptionsPane(refOptions,frame);				
		constraintSimulationPanel.setOCLOptionPane(oclOptions,frame);

		invalidate();
	}
	
	/**
	 * Constructor
	 */
	public SimulationDialog(AppFrame frame) 
	{
		super(frame);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SimulationDialog.class.getResource("/resources/icons/x16/cog.png")));
		setTitle("Configuring Simulation");
		setSize(new Dimension(876, 363));
		
		btnOk = new JButton("OK");	
		btnOk.setPreferredSize(new Dimension(70, 25));
		btnOk.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			OkActionPerformed(event);
       		}
       	});
		
		btnCancel = new JButton("Cancel");
		btnCancel.setPreferredSize(new Dimension(70, 25));
		btnCancel.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
       	});
		
		btnpanel = new JPanel();
		btnpanel.setBorder(BorderFactory.createTitledBorder(""));
		btnpanel.setPreferredSize(new Dimension(100, 40));
		btnpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnpanel.add(btnOk);
		btnpanel.add(btnCancel);
				
		modelSimulationPanel = new ModelSimulationPanel();		
		constraintSimulationPanel = new ConstraintSimulationPanel();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(modelSimulationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(constraintSimulationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnpanel, GroupLayout.PREFERRED_SIZE, 822, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(modelSimulationPanel, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
						.addComponent(constraintSimulationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(45))
		);
		getContentPane().setLayout(groupLayout);		
	}
			
	public void OkActionPerformed(ActionEvent event)
	{
		OntoUML2AlloyOptions ontoumlOptions = new OntoUML2AlloyOptions();
		ontoumlOptions.antiRigidity = modelSimulationPanel.isSelectedAntirigidity(); 
		ontoumlOptions.identityPrinciple = modelSimulationPanel.isSelectedIdentityPrinciple();
		ontoumlOptions.weakSupplementation = modelSimulationPanel.isSelectedWeakSupplementation();
		ontoumlOptions.relatorConstraint = modelSimulationPanel.isSelectedRelatorConstraint();			    	
		
		ProjectBrowser.setOntoUMLOptionsFor(frame.getDiagramManager().getCurrentProject(),ontoumlOptions);
		
		OCLDocument oclmodel = ProjectBrowser.getOCLModelFor(frame.getDiagramManager().getCurrentProject());
		TOCL2AlloyOption oclOptions = new TOCL2AlloyOption(oclmodel.getOCLParser());		
		oclOptions.setTransformationType(constraintSimulationPanel.getTransformationsTypesListSelected());
    	oclOptions.setCommandScope(constraintSimulationPanel.getScopesListSelected());    			
    	oclOptions.setBiwidth(constraintSimulationPanel.getBitWidthListSelected());
		oclOptions.setConstraintList(constraintSimulationPanel.getConstraintListSelected());
    	
    	ProjectBrowser.setOCLOptionsFor(frame.getDiagramManager().getCurrentProject(), oclOptions);
		  
//    	dispose();
    
    	frame.getDiagramManager().generatesAlloy();		  		
	}
	
	public AppFrame getFrame()
	{
		return frame;
	}
}
