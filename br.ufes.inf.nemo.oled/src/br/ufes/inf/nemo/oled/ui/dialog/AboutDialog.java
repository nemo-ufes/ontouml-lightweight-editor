package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import br.ufes.inf.nemo.oled.AppFrame;


public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -251319551154959770L;
	private JLabel labelDescription; 
	private JButton btnOk;
	private JPanel SouthPanel;
	private JPanel CenterPanel;
	private JLabel labelAuthor1;
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
		setBounds(100, 100, 338, 469);
		
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
		
		labelDescription = new JLabel("https://code.google.com/p/ontouml-lightweight-editor/");
		labelDescription.setHorizontalAlignment(SwingConstants.CENTER);
		labelDescription.setForeground(Color.BLACK);
				
		JPanel panel = new JPanel();
				
		GroupLayout gl_CenterPanel = new GroupLayout(CenterPanel);
		gl_CenterPanel.setHorizontalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, 0, 0, Short.MAX_VALUE)
						.addComponent(labelDescription, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
					.addGap(11))
		);
		gl_CenterPanel.setVerticalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addComponent(labelDescription)
					.addGap(11)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(61))
		);
		
		JLabel lblViniciusSobralVictor = new JLabel("Vinicius Sobral - EA Compatibility");
		lblViniciusSobralVictor.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblTiagoPrinceSales = new JLabel("Tiago Prince Sales - Alloy and AntiPatterns");
		lblTiagoPrinceSales.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblJohnGuerson = new JLabel("John Guerson - Co-Author");
		lblJohnGuerson.setHorizontalAlignment(SwingConstants.LEFT);
		labelAuthor1 = new JLabel("Antognoni Albuquerque - Author");
		labelAuthor1.setForeground(Color.BLACK);
		labelAuthor1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblVictorAmorim = new JLabel("Victor Amorim - OWL and Modeling Assistant");
		lblVictorAmorim.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVictorAmorim, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
								.addComponent(lblViniciusSobralVictor, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTiagoPrinceSales, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
								.addComponent(lblJohnGuerson, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
								.addComponent(labelAuthor1, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelAuthor1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblJohnGuerson)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTiagoPrinceSales)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblViniciusSobralVictor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblVictorAmorim, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		CenterPanel.setLayout(gl_CenterPanel);
		
		NorthPanel = new JPanel();
		NorthPanel.setPreferredSize(new Dimension(100, 220));
		getContentPane().add(NorthPanel, BorderLayout.NORTH);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(AboutDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/nemo.png")));
		NorthPanel.add(label);
	}
}
