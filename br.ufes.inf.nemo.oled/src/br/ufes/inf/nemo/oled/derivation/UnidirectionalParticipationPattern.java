package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.oled.DiagramManager;

public class UnidirectionalParticipationPattern extends JDialog {

	private static final long serialVersionUID = -4945696637902507744L;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField lbl_base;
	private JTextField lbl_target;
	private JTextField lbl_relator;
	private JTextField lbl_derived;
	@SuppressWarnings("rawtypes")
	JComboBox cmb_base = new JComboBox();
	@SuppressWarnings("rawtypes")
	JComboBox cmb_target = new JComboBox();
	DiagramManager dman ;
	Point2D.Double location = new Point2D.Double();

	/**
	 * Launch the application.
	 */
	

	

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UnidirectionalParticipationPattern(DiagramManager dm) {
		
		dman=dm;
		setIconImage(Toolkit.getDefaultToolkit().getImage(UnidirectionalParticipationPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		setTitle("Derivation By Participation: Unidirectional");
		setBounds(100, 100, 381, 493);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel = new JLabel("Base Type:");
		}
		
		lbl_base = new JTextField();
		lbl_base.setText("Base");
		lbl_base.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Target Type:");
		
		lbl_target = new JTextField();
		lbl_target.setText("Target");
		lbl_target.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Relator:");
		
		lbl_relator = new JTextField();
		lbl_relator.setText("Relator");
		lbl_relator.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Derived by Participation:");
		
		lbl_derived = new JTextField();
		lbl_derived.setText("Derived");
		lbl_derived.setColumns(10);
		
		
		cmb_base.setModel(new DefaultComboBoxModel(new String[] {"Kind", "SubKind", "Role", "Phase", "Category", "RoleMixin", "Mixin"}));
		
		
		cmb_target.setModel(new DefaultComboBoxModel(new String[] {"Kind", "SubKind", "Role", "Phase", "Category", "RoleMixin", "Mixin"}));
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(UnidirectionalParticipationPattern.class.getResource("/resources/figures/derivationbyparticipation_uni.PNG")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lbl_target, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(cmb_target, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lbl_base, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(cmb_base, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lbl_relator)
							.addComponent(lbl_derived))
						.addComponent(lblNewLabel_5))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_base, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_base, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_target, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_target, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addGap(5)
					.addComponent(lbl_relator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl_derived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_5)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DerivedTypesOperations.createParticipationUni(dman, lbl_base.getText(), lbl_target.getText(),lbl_relator.getText(),lbl_derived.getText(), cmb_base.getSelectedItem().toString(), cmb_target.getSelectedItem().toString(),location);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
		}
	}
	public void setPosition(Point2D.Double location) {
		this.location = location;
	}
}
