package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.plugins.bmp.BMPImageWriteParam;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;

import br.ufes.inf.nemo.oled.DiagramManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

public class UnidirectionalParticipationPattern extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField lbl_base;
	private JTextField lbl_target;
	private JTextField lbl_relator;
	private JTextField lbl_derived;
	JComboBox cmb_base = new JComboBox();
	JComboBox cmb_target = new JComboBox();
	DiagramManager dman ;
	Point2D.Double location = new Point2D.Double();

	/**
	 * Launch the application.
	 */
	

	

	/**
	 * Create the dialog.
	 */
	public UnidirectionalParticipationPattern(DiagramManager dm) {
		
		dman=dm;
		setIconImage(Toolkit.getDefaultToolkit().getImage(UnidirectionalParticipationPattern.class.getResource("/resources/icons/x16/sitemap.png")));
		setTitle("Derivation By Participation Unidirectional");
		setBounds(100, 100, 411, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel = new JLabel("Base Type");
		}
		
		lbl_base = new JTextField();
		lbl_base.setText("Base");
		lbl_base.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Target Type");
		
		lbl_target = new JTextField();
		lbl_target.setText("Target");
		lbl_target.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Relator");
		
		lbl_relator = new JTextField();
		lbl_relator.setText("Relator");
		lbl_relator.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Derived by Participation");
		
		lbl_derived = new JTextField();
		lbl_derived.setText("Derived");
		lbl_derived.setColumns(10);
		
		
		cmb_base.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Subkind", "Role", "Phase", "Category", "RoleMixin", "Mixin"}));
		
		
		cmb_target.setModel(new DefaultComboBoxModel(new String[] {"Kind", "Subkind", "Role", "Phase", "Category", "RoleMixin", "Mixin"}));
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(UnidirectionalParticipationPattern.class.getResource("/resources/figures/derivationbyparticipation_uni.PNG")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(34)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lbl_base, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(cmb_base, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lbl_relator, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)
								.addComponent(lbl_derived, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lbl_target, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cmb_target, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel_5)))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_base, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_base, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(lblNewLabel_1)
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_target, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_target, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(lblNewLabel_2)
					.addGap(5)
					.addComponent(lbl_relator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblNewLabel_3)
					.addGap(6)
					.addComponent(lbl_derived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_5)
					.addContainerGap(20, Short.MAX_VALUE))
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
		}
	}
	public void setPosition(Point2D.Double location) {
		this.location = location;
	}
}
