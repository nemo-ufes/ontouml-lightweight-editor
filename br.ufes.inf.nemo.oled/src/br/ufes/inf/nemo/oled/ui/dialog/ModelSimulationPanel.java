package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * @author John Guerson
 */

public class ModelSimulationPanel extends JPanel {

	private static final long serialVersionUID = 4762609414668205905L;
		
	@SuppressWarnings("unused")
	private OntoUML2AlloyOptions refOptions;
		
	private AppFrame frame;
	private JCheckBox cbxRelator;
	private JCheckBox cbxWeak ;	
	private JCheckBox cbxIdentity ;
	private JCheckBox cbxAntirigidity;
	private JButton btnEnableall;
	private JButton btnDisableall;
	private JButton lblSateRelator;
	private JButton lblStateWeak;
	private JButton lblStateIdentity;
	private JButton lblStateAntirigidity;
	private JLabel lblChooseWhichAxioms;

	private JButton btndefault;
	
	/**
	 * Creates a View from OntoUML Options Model and the main frame application.
	 * 
	 * @param optModel
	 * @param frame
	 */
	public ModelSimulationPanel(OntoUML2AlloyOptions refOptions, AppFrame frame)
	{
		this();
		
		this.refOptions = refOptions;
		this.frame = frame;
	
		setOntoUMLOptionsPane(refOptions,frame);
	}

	/**
	 * Set OntoUML Options Pane from OntoUML2Alloy Options.
	 */
	public void setOntoUMLOptionsPane (OntoUML2AlloyOptions refOptions, AppFrame frame)
	{
		this.frame = frame;
		this.cbxAntirigidity.setSelected(refOptions.antiRigidity);
				
		this.cbxIdentity.setSelected(refOptions.identityPrinciple);
		if (refOptions.identityPrincipleInvalid) {
			lblStateIdentity.setIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error.png")));
			lblStateIdentity.setRolloverIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error-rollover.png")));
			lblStateIdentity.setToolTipText("<html>"+refOptions.identityPrincipleInvalidMsg.replace("\n", "<br>")+"</html>");
		}else{
			lblStateIdentity.setIcon(null);
		}
		
		this.cbxRelator.setSelected(refOptions.relatorConstraint);
		if (refOptions.relatorConstraintInvalid) {
			lblSateRelator.setIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error.png")));
			lblSateRelator.setRolloverIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error-rollover.png")));
			lblSateRelator.setToolTipText("<html>"+refOptions.relatorConstraintInvalidMsg.replace("\n", "<br>")+"</html>");
		}else{
			lblSateRelator.setIcon(null);
		}
		
		this.cbxWeak.setSelected(refOptions.weakSupplementation);
		if (refOptions.weakSupplementationInvalid) {
			lblStateWeak.setIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error.png")));
			lblStateWeak.setRolloverIcon(new ImageIcon(ModelSimulationPanel.class.getResource("/resources/icons/x16/error-rollover.png")));
			lblStateWeak.setToolTipText("<html>"+refOptions.weakSupplementationInvalidMsg.replace("\n", "<br>")+"</html>");
		}else{
			lblStateWeak.setIcon(null);
		}
		
		invalidate();	
	}

	/**
	 * Creates an Empty Pane for OntoUML2Alloy Options.
	 */
	public ModelSimulationPanel() 
	{
		setBorder(BorderFactory.createTitledBorder("Model"));		
		setBackground(UIManager.getColor("Panel.background"));
		setPreferredSize(new Dimension(349, 273));
		setSize(new Dimension(186, 228));
		
		JPanel panel = new JPanel();		
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		lblChooseWhichAxioms = new JLabel("Choose which axioms do you want to enforce in simulation. ");
		
		JLabel lblTheCheckedOnes = new JLabel("The checked ones are recommented by default.");
		
		btnEnableall = new JButton("Enable All");
		btnEnableall.setPreferredSize(new Dimension(100, 25));
		
		btnEnableall.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent arg0) 
		{
			cbxAntirigidity.setSelected(true);
			cbxIdentity.setSelected(true);
			cbxRelator.setSelected(true);
			cbxWeak.setSelected(true);
		}
		});
		
		btnDisableall = new JButton("Disable All");
		btnDisableall.setPreferredSize(new Dimension(100, 25));
		
		btnDisableall.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cbxAntirigidity.setSelected(false);
				cbxIdentity.setSelected(false);
				cbxRelator.setSelected(false);
				cbxWeak.setSelected(false);
			}
		});
		
		btndefault = new JButton("(Default)");
		btndefault.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(lblSateRelator.getIcon()==null) cbxRelator.setSelected(true);
				if(lblStateWeak.getIcon()==null) cbxWeak.setSelected(true);
				if(lblStateIdentity.getIcon()==null) cbxIdentity.setSelected(true);
				cbxAntirigidity.setSelected(false);				
			}
		});		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(lblChooseWhichAxioms, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTheCheckedOnes, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btndefault)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEnableall, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDisableall, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblChooseWhichAxioms)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTheCheckedOnes, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDisableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnableall, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btndefault, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(26))
		);

		cbxRelator = new JCheckBox("Relator constraint");		
		cbxRelator.setPreferredSize(new Dimension(180, 20));
		cbxRelator.setToolTipText("<html>\r\nMark this option if in your model all relators mediate at least two distinct objects. <br>\r\nIf this is not true, leave this option unchecked. </html>\r\n\r\n");
				
		cbxWeak = new JCheckBox("Weak supplementation");
		cbxWeak.setPreferredSize(new Dimension(180, 20));
		cbxWeak.setToolTipText("<html>\r\nMark this option if in your model all wholes have two or more parts. <br>\r\nIf this is not true, leave this option unchecked. </html>");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblStateWeak = new JButton("");
		lblStateWeak.setHorizontalAlignment(SwingConstants.CENTER);		
		lblStateWeak.setPreferredSize(new Dimension(30, 20));
		lblStateWeak.setFocusable(false);
		lblStateWeak.setOpaque(false);
		lblStateWeak.setContentAreaFilled(false);
		lblStateWeak.setBorderPainted(false);
		panel.add(lblStateWeak);
		panel.add(cbxWeak);
		
		lblSateRelator = new JButton("");		
		lblSateRelator.setHorizontalAlignment(SwingConstants.CENTER);
		lblSateRelator.setPreferredSize(new Dimension(30, 20));
		lblSateRelator.setOpaque(false);
		lblSateRelator.setContentAreaFilled(false);
		lblSateRelator.setBorderPainted(false);
		lblSateRelator.setFocusable(false);
		panel.add(lblSateRelator);
		panel.add(cbxRelator);
		
		lblStateIdentity = new JButton("");		
		panel.add(lblStateIdentity);
		lblStateIdentity.setHorizontalAlignment(SwingConstants.CENTER);
		lblStateIdentity.setPreferredSize(new Dimension(30, 20));
		lblStateIdentity.setOpaque(false);
		lblStateIdentity.setFocusable(false);
		lblStateIdentity.setContentAreaFilled(false);
		lblStateIdentity.setBorderPainted(false);
		cbxIdentity = new JCheckBox("Identity principle");		
		panel.add(cbxIdentity);
		cbxIdentity.setToolTipText("<html>\r\nMark this option if you want to visualize objects that does not have identity principle.<br>\r\nIf not, leave this option unchecked. </html>");
		cbxIdentity.setPreferredSize(new Dimension(180, 20));
		
		lblStateAntirigidity = new JButton("");		
		panel.add(lblStateAntirigidity);
		
		lblStateAntirigidity.setHorizontalAlignment(SwingConstants.CENTER);
		lblStateAntirigidity.setPreferredSize(new Dimension(30, 20));
		lblStateAntirigidity.setOpaque(false);
		lblStateAntirigidity.setContentAreaFilled(false);
		lblStateAntirigidity.setBorderPainted(false);
		lblStateAntirigidity.setFocusable(false);
		cbxAntirigidity = new JCheckBox("Antirigidity visualization");		
		panel.add(cbxAntirigidity);
		cbxAntirigidity.setToolTipText("<html>\r\nMark this option if you want to enforce the visualization of anti-rigid objects.<br>\r\nIf not, leave this option unchecked. </html>\r\n");
		cbxAntirigidity.setFocusable(false);
		cbxAntirigidity.setPreferredSize(new Dimension(180, 20));
		setLayout(groupLayout);
	}
	
	public void setStateRelatorConstraint(String text) 
	{
		this.lblSateRelator.setText(text);
	}

	public void setStateWeakSupplementation(String text)
	{
		this.lblStateWeak.setText(text);
	}

	public void setStateIdentityPrinciple(String text)
	{
		this.lblStateIdentity.setText(text);
	}

	public void setStateAntirigidity(String text)
	{
		this.lblStateAntirigidity.setText(text);
	}
	
	/**
	 * Is Selected Relator Constraint Option.
	 * 
	 * @return
	 */	
	public boolean isSelectedRelatorConstraint() { return cbxRelator.isSelected(); }
	
	/**
	 * Is Selected Weak Supplementation Option.
	 * 
	 * @return
	 */
	public boolean isSelectedWeakSupplementation() { return cbxWeak.isSelected(); }
	
	/**
	 * Is Selected AntiRigidity Option.
	 * 
	 * @return
	 */
	public boolean isSelectedAntirigidity() { return cbxAntirigidity.isSelected(); }
		
	/**
	 * Is Selected Identity Principle Option.
	 * 
	 * @return
	 */
	public boolean isSelectedIdentityPrinciple() { return cbxIdentity.isSelected(); }
	
	/**
	 * Get the main frame application.
	 * 
	 * @return
	 */
	public AppFrame getFrame() { return frame; }
}
