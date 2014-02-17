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

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.Main;


public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -251319551154959770L;
	private JLabel labelDescription; 
	private JButton btnOk;
	private JPanel SouthPanel;
	private JPanel CenterPanel;
	private JLabel labelAuthor1;
	private JPanel NorthPanel;
	private JLabel label;
	private JLabel lblVersion;
	
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
		setBounds(100, 100, 338, 502);
		
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
		
		lblVersion = new JLabel("OLED v"+Main.OLED_VERSION);
		lblVersion.setFont(new Font(lblVersion.getFont().getFontName(), Font.BOLD, 11));
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNemoinfufesbr = new JLabel("nemo.inf.ufes.br");
		lblNemoinfufesbr.setHorizontalAlignment(SwingConstants.CENTER);
				
		GroupLayout gl_CenterPanel = new GroupLayout(CenterPanel);
		gl_CenterPanel.setHorizontalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_CenterPanel.createSequentialGroup()
							.addComponent(labelDescription, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
							.addGap(11))
						.addGroup(Alignment.TRAILING, gl_CenterPanel.createSequentialGroup()
							.addComponent(lblVersion, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addComponent(panel, 0, 0, Short.MAX_VALUE)
							.addGap(11))
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addComponent(lblNemoinfufesbr, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_CenterPanel.setVerticalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addComponent(labelDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblVersion)
					.addGap(3)
					.addComponent(lblNemoinfufesbr)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		JLabel lblViniciusSobralVictor = new JLabel("Vinicius Sobral - EA Compatibility");
		lblViniciusSobralVictor.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblTiagoPrinceSales = new JLabel("Tiago Prince Sales - Alloy and Anti-Patterns");
		lblTiagoPrinceSales.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblJohnGuerson = new JLabel("John Guerson - Author");
		lblJohnGuerson.setHorizontalAlignment(SwingConstants.LEFT);
		labelAuthor1 = new JLabel("Antognoni Albuquerque - Author");
		labelAuthor1.setForeground(Color.BLACK);
		labelAuthor1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblVictorAmorim = new JLabel("Victor Amorim - OWL and Modeling Assistant");
		lblVictorAmorim.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblCssioReginato = new JLabel("Cássio Reginato - OWL and Derived Types");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVictorAmorim, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
						.addComponent(lblViniciusSobralVictor, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
						.addComponent(lblTiagoPrinceSales, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
						.addComponent(lblJohnGuerson, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
						.addComponent(labelAuthor1, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
						.addComponent(lblCssioReginato, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCssioReginato)
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
