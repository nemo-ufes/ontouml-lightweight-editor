package br.ufes.inf.nemo.move.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.move.ui.TheFrame;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -251319551154959770L;
	private JLabel labelDescription; 
	private JButton btnOk;
	private JPanel SouthPanel;
	private JPanel CenterPanel;
	private JLabel labeTitle;
	private JLabel labelDate;		
	private JLabel labelAuthorTiago;
	private JLabel labelAuthorJohn;
	
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
		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
		setTitle("About Model Validation Environment");
		setBounds(100, 100, 361, 255);
		
		getContentPane().setLayout(new BorderLayout());
		
		SouthPanel = new JPanel();
		SouthPanel.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(SouthPanel, BorderLayout.SOUTH);
		
		btnOk = new JButton("Close");
		btnOk.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
		});
		
		GroupLayout gl_SouthPanel = new GroupLayout(SouthPanel);
		gl_SouthPanel.setHorizontalGroup(
			gl_SouthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_SouthPanel.createSequentialGroup()
					.addContainerGap(141, Short.MAX_VALUE)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(140))
		);
		gl_SouthPanel.setVerticalGroup(
			gl_SouthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SouthPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnOk)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		SouthPanel.setLayout(gl_SouthPanel);
		
		CenterPanel = new JPanel();
		CenterPanel.setPreferredSize(new Dimension(150, 190));
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		
		labeTitle = new JLabel("Model Validation Environment 0.2");		
		labelDate = new JLabel("Build Date: 2012-12-12");		
		labelAuthorTiago = new JLabel("Author: Tiago Prince Sales");
		labelAuthorJohn = new JLabel("Author: John Guerson");
		
		labelDescription = new JLabel("A suite of tools for validating ontology conceptual models.");
		labelDescription.setHorizontalAlignment(SwingConstants.LEFT);
		labelDescription.setFont(new Font("Tahoma", Font.PLAIN, 11));
				
		GroupLayout gl_CenterPanel = new GroupLayout(CenterPanel);
		gl_CenterPanel.setHorizontalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(labelAuthorJohn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelAuthorTiago, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelDate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelDescription, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labeTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_CenterPanel.setVerticalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(labeTitle)
					.addGap(4)
					.addComponent(labelDescription)
					.addGap(18)
					.addComponent(labelDate)
					.addGap(24)
					.addComponent(labelAuthorTiago)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelAuthorJohn)
					.addGap(123))
		);
		CenterPanel.setLayout(gl_CenterPanel);
	}
}
