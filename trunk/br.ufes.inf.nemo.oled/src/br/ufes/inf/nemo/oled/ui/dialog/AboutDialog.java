package br.ufes.inf.nemo.oled.ui.dialog;

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
import javax.swing.border.EtchedBorder;


public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -251319551154959770L;
	private JLabel labelDescription; 
	private JButton btnOk;
	private JPanel SouthPanel;
	private JPanel CenterPanel;
	private JLabel labeTitle;
	private JLabel labelAuthor1;
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
		setBounds(100, 100, 421, 565);
		
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
		
		labelDescription = new JLabel("https://code.google.com/p/ontouml-lightweight-editor/");
		labelDescription.setForeground(Color.BLUE);
		labelDescription.setHorizontalAlignment(SwingConstants.CENTER);
		labelDescription.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblOntology = new JLabel("Ontology & Conceptual Modeling Research Group (NEMO)");
		lblOntology.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblForMoreInformation = new JLabel("nemo.inf.ufes.br");
		lblForMoreInformation.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				
		GroupLayout gl_CenterPanel = new GroupLayout(CenterPanel);
		gl_CenterPanel.setHorizontalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(labeTitle, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
						.addComponent(labelDescription, GroupLayout.PREFERRED_SIZE, 385, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
						.addComponent(lblOntology, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
						.addComponent(lblForMoreInformation, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_CenterPanel.setVerticalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(labeTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelDescription)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblForMoreInformation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOntology, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(58))
		);
		
		JLabel lblViniciusSobralVictor = new JLabel("Vinicius Sobral, Victor Amorim e Cássio Reginato");
		lblViniciusSobralVictor.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblOthers = new JLabel("Others:");
		lblOthers.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblTiagoPrinceSales = new JLabel("Tiago Prince Sales - tgoprince@gmail.com");
		lblTiagoPrinceSales.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblJohnGuerson = new JLabel("John Guerson - johnguerson@gmail.com");
		lblJohnGuerson.setHorizontalAlignment(SwingConstants.CENTER);
		labelAuthor1 = new JLabel("Antognoni Albuquerque  - antognoni.albuquerque@gmail.com");
		labelAuthor1.setForeground(Color.BLACK);
		labelAuthor1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblContributors = new JLabel("Main Contibutors:");
		lblContributors.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContributors, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
						.addComponent(labelAuthor1, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
						.addComponent(lblJohnGuerson, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
						.addComponent(lblTiagoPrinceSales, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
						.addComponent(lblOthers, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
						.addComponent(lblViniciusSobralVictor, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblContributors)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelAuthor1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblJohnGuerson)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTiagoPrinceSales)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOthers)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblViniciusSobralVictor)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		CenterPanel.setLayout(gl_CenterPanel);
		
		NorthPanel = new JPanel();
		NorthPanel.setPreferredSize(new Dimension(100, 220));
		getContentPane().add(NorthPanel, BorderLayout.NORTH);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(AboutDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/nemo.png")));
		NorthPanel.add(label);
	}
}
