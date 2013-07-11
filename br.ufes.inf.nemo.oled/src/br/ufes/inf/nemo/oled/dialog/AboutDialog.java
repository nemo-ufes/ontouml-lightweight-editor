package br.ufes.inf.nemo.oled.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.oled.ui.AppFrame;


public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -251319551154959770L;
	private JLabel labelDescription; 
	private JButton btnOk;
	private JPanel SouthPanel;
	private JPanel CenterPanel;
	private JLabel labeTitle;
	private JLabel labelAuthorTiago;
	private JLabel lblOntology;
	private JLabel lblForMoreInformation;
	private JPanel NorthPanel;
	private JLabel label;
	
	/**
	 * Launch the Dialog.
	 */
	public static void open(AppFrame frame) 
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
	public AboutDialog(AppFrame frame) 
	{
		super(frame);
		
		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
		setTitle("About");
		setBounds(100, 100, 421, 516);
		
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
		
		labeTitle = new JLabel("OntoUML Lightweight Editor (OLED)");		
		labeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelAuthorTiago = new JLabel("Antognoni Albuquerque, John Guerson and Tiago Prince Sales");
		labelAuthorTiago.setForeground(Color.BLACK);
		labelAuthorTiago.setHorizontalAlignment(SwingConstants.CENTER);
		
		labelDescription = new JLabel("An OntoUML editor with a simple, lightweight and integrated set of features.");
		labelDescription.setHorizontalAlignment(SwingConstants.CENTER);
		labelDescription.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblOntology = new JLabel("Ontology & Conceptual Modeling Research Group (NEMO)");
		lblOntology.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblForMoreInformation = new JLabel("nemo.inf.ufes.br");
		lblForMoreInformation.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblAuthors = new JLabel("Author(s)");
		lblAuthors.setHorizontalAlignment(SwingConstants.CENTER);
				
		GroupLayout gl_CenterPanel = new GroupLayout(CenterPanel);
		gl_CenterPanel.setHorizontalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(labelDescription, GroupLayout.PREFERRED_SIZE, 385, Short.MAX_VALUE)
						.addComponent(labeTitle, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
						.addComponent(lblOntology, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
						.addComponent(labelAuthorTiago, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
						.addComponent(lblAuthors, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
						.addComponent(lblForMoreInformation, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_CenterPanel.setVerticalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(labeTitle)
					.addGap(4)
					.addComponent(labelDescription)
					.addGap(34)
					.addComponent(lblAuthors)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelAuthorTiago)
					.addGap(18)
					.addComponent(lblForMoreInformation)
					.addGap(24)
					.addComponent(lblOntology, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		CenterPanel.setLayout(gl_CenterPanel);
		
		NorthPanel = new JPanel();
		NorthPanel.setPreferredSize(new Dimension(100, 220));
		getContentPane().add(NorthPanel, BorderLayout.NORTH);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(AboutDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/nemo.png")));
		NorthPanel.add(label);
	}
}
