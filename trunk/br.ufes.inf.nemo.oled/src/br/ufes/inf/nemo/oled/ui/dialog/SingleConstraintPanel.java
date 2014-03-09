package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.eclipse.ocl.uml.UMLEnvironment;
import org.eclipse.uml2.uml.Constraint;

/**
 * @author John Guerson
 */

public class SingleConstraintPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JComboBox<?> comboTransformationType;	
	public JTextField txtConstraintName;	
	public JCheckBox checkEnforce;
	public JSpinner spinCommandScope;
	public JLabel lblScope;
	public Constraint constraint;	
	public JLabel lblBitwidht;
	public JPanel panel_1;
	public JSpinner bitSpinner;
	
	/**
	 * Constructor.
	 * 
	 * @param ct
	 * @param ctType
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SingleConstraintPanel(Constraint ct, String ctType, UMLEnvironment env)
	{
		this();
		
		this.constraint = ct;		
		if(ctType=="invariant") ctType = "inv";
		else if (ctType=="derivation") ctType = "derive";
		else if (ctType=="temporal") ctType = "temp";
		
		if (ctType=="inv" && ct.getName()==null) txtConstraintName.setText(ctType+" <empty-name>");
		else if (ctType=="inv") txtConstraintName.setText(ctType+" "+ct.getName());
		
		if (ctType=="temp" && ct.getName()==null) txtConstraintName.setText(ctType+" <empty-name>");
		else if (ctType=="temp") txtConstraintName.setText(ctType+" "+ct.getName());
		
		org.eclipse.ocl.util.ToStringVisitor visitor = org.eclipse.ocl.util.ToStringVisitor.getInstance(env);
		String text = visitor.visitConstraint(ct);
		
		if (ctType=="derive") {			
			txtConstraintName.setText(ctType+" "+text.substring(8,text.indexOf("derive")).trim());
		}
				
		txtConstraintName.setToolTipText("<html>"+text.replace("\n","<br>").replace("null", "")+"</html>");
	}
		
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SingleConstraintPanel() 
	{
//		setBorder(BorderFactory.createTitledBorder(""));		
		setPreferredSize(new Dimension(420, 77));
		
		checkEnforce = new JCheckBox("");
		checkEnforce.setBorder(BorderFactory.createTitledBorder(""));
		checkEnforce.setHorizontalAlignment(SwingConstants.CENTER);
		checkEnforce.setSelected(true);
		
		JPanel panel = new JPanel();
//		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		panel_1 = new JPanel();
//		panel_1.setBorder(BorderFactory.createTitledBorder(""));
		
		GroupLayout gl_panel = new GroupLayout(this);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(checkEnforce, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(checkEnforce, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		txtConstraintName = new JTextField();
		txtConstraintName.setSize(new Dimension(150, 20));
		txtConstraintName.setPreferredSize(new Dimension(150, 20));
		txtConstraintName.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtConstraintName.setHorizontalAlignment(SwingConstants.LEFT);
		txtConstraintName.setEditable(false);		
		txtConstraintName.setColumns(10);
		
		comboTransformationType = new JComboBox();		
		comboTransformationType.setPreferredSize(new Dimension(100, 20));
		comboTransformationType.setModel(new DefaultComboBoxModel(new String[] {"FACT ", "SIMULATE", "CHECK"}));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboTransformationType, Alignment.LEADING, 0, 173, Short.MAX_VALUE)
						.addComponent(txtConstraintName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
					.addGap(11))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(txtConstraintName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboTransformationType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		lblScope = new JLabel("Default scope:");
		lblScope.setPreferredSize(new Dimension(90, 20));
		panel.add(lblScope);
		
		spinCommandScope = new JSpinner();
		spinCommandScope.setPreferredSize(new Dimension(50, 20));
		panel.add(spinCommandScope);
		spinCommandScope.setModel(new SpinnerNumberModel(10, 0, 32, 1));
		
		lblBitwidht = new JLabel("Bitwidth:");
		lblBitwidht.setPreferredSize(new Dimension(90, 20));
		panel.add(lblBitwidht);
		
		bitSpinner = new JSpinner();
		bitSpinner.setPreferredSize(new Dimension(50, 20));
		panel.add(bitSpinner);
		bitSpinner.setModel(new SpinnerNumberModel(7, 0, 32, 1));
		setLayout(gl_panel);		

		spinCommandScope.setEnabled(false);
		bitSpinner.setEnabled(false);
		lblBitwidht.setEnabled(false);
		lblScope.setEnabled(false);
		
		comboTransformationType.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboTransformationType.getSelectedIndex()==0){
					spinCommandScope.setEnabled(false);
					bitSpinner.setEnabled(false);
					lblBitwidht.setEnabled(false);
					lblScope.setEnabled(false);
				}else{
					spinCommandScope.setEnabled(true);
					bitSpinner.setEnabled(true);
					lblBitwidht.setEnabled(true);
					lblScope.setEnabled(true);
				}					
			}
		});
	}
}
