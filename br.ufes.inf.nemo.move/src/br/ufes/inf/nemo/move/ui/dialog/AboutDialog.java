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
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -251319551154959770L;
	private JLabel labelDescription; 
	private JButton btnOk;
	private JPanel SouthPanel;
	private JPanel CenterPanel;
	private JLabel labeTitle;
	private JLabel labelAuthorTiago;
	private JLabel labelAuthorJohn;
	private JLabel lblOntology;
	private JLabel lblForMoreInformation;
	private JPanel NorthPanel;
	private JLabel label;
	
	/**
	 * Launch the Dialog.
	 */
	public static void open(TheFrame frame) 
	{
		try {
			
			AboutDialog dialog = new AboutDialog(frame);
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
	public AboutDialog(TheFrame frame) 
	{
		super(frame);
		
		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
		setTitle("About Model Validation Environment");
		setBounds(100, 100, 387, 516);
		
		getContentPane().setLayout(new BorderLayout());
		
		SouthPanel = new JPanel();
		SouthPanel.setPreferredSize(new Dimension(50, 40));
		getContentPane().add(SouthPanel, BorderLayout.SOUTH);
		
		btnOk = new JButton("Close");
		btnOk.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
		});
		SouthPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		SouthPanel.add(btnOk);
		
		CenterPanel = new JPanel();
		CenterPanel.setPreferredSize(new Dimension(150, 190));
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		
		labeTitle = new JLabel("Model Validation Environment 0.2");		
		labeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelAuthorTiago = new JLabel("Tiago Prince Sales");
		labelAuthorTiago.setHorizontalAlignment(SwingConstants.CENTER);
		labelAuthorJohn = new JLabel("John Guerson");
		labelAuthorJohn.setHorizontalAlignment(SwingConstants.CENTER);
		
		labelDescription = new JLabel("A suite of tools for validating ontology conceptual models.");
		labelDescription.setHorizontalAlignment(SwingConstants.CENTER);
		labelDescription.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblOntology = new JLabel("Ontology & Conceptual Modeling Research Group (NEMO)");
		lblOntology.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblForMoreInformation = new JLabel("nemo.inf.ufes.br");
		lblForMoreInformation.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblAuthors = new JLabel("Author(s):");
		lblAuthors.setHorizontalAlignment(SwingConstants.CENTER);
				
		GroupLayout gl_CenterPanel = new GroupLayout(CenterPanel);
		gl_CenterPanel.setHorizontalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_CenterPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(labelDescription, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_CenterPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(labeTitle, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)))
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAuthors, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelAuthorTiago, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelAuthorJohn, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_CenterPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblOntology, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblForMoreInformation, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_CenterPanel.setVerticalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(labeTitle)
					.addGap(4)
					.addComponent(labelDescription)
					.addGap(24)
					.addComponent(lblAuthors)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelAuthorTiago)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelAuthorJohn)
					.addGap(22)
					.addComponent(lblForMoreInformation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOntology, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		CenterPanel.setLayout(gl_CenterPanel);
		
		NorthPanel = new JPanel();
		NorthPanel.setPreferredSize(new Dimension(100, 220));
		getContentPane().add(NorthPanel, BorderLayout.NORTH);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(AboutDialog.class.getResource("/resources/icon/nemo-256x256.png")));
		NorthPanel.add(label);
	}
}
