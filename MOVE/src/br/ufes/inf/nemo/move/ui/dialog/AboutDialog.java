package br.ufes.inf.nemo.move.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.ufes.inf.nemo.move.ui.TheFrame;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -251319551154959770L;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1; 
	private JLabel lblTiagoPrinceSales;
	private JLabel lblVersion;
	private JLabel lblNewLabel_2;
	private JButton btnOk;
	
	/**
	 * Launch the Dialog.
	 */
	public static void open(TheFrame frame) 
	{
		try {
			
			AboutDialog dialog = new AboutDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(frame);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutDialog() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutDialog.class.getResource("/resources/br/ufes/inf/nemo/move/window.png")));
		setTitle("About MOVE");
		setBounds(100, 100, 450, 283);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setPreferredSize(new Dimension(100, 190));
		
		lblNewLabel = new JLabel("OntoUML Model Validation Environment - MOVE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblNewLabel_1 = new JLabel("A suite of tools for validating ontology conceptual models.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblTiagoPrinceSales = new JLabel("Tiago Prince Sales and John Guerson");
		lblTiagoPrinceSales.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTiagoPrinceSales.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblVersion = new JLabel("Version 0.1");
		lblVersion.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel_2 = new JLabel("December 12 2012 , Brazil");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(lblVersion, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(lblTiagoPrinceSales, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(27)
					.addComponent(lblVersion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTiagoPrinceSales)
					.addGap(31)
					.addComponent(lblNewLabel_2)
					.addGap(33))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(318, Short.MAX_VALUE)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnOk)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
