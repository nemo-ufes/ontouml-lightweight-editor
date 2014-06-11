package br.ufes.inf.nemo.oled.dialog.help;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.Main;


public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -251319551154959770L;
	private JPanel CenterPanel;
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
		
//		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
//		setIconImage(icon);
		setTitle("About");
		setBounds(100, 100, 364, 537);
		
		getContentPane().setLayout(new BorderLayout());
		
		CenterPanel = new JPanel();
		CenterPanel.setPreferredSize(new Dimension(150, 190));
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		
		lblVersion = new JLabel("Version: "+Main.OLED_VERSION);
		lblVersion.setForeground(Color.BLACK);		
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDate = new JLabel("Compilation Date: "+Main.OLED_COMPILATION_DATE);				
		lblDate.setForeground(Color.BLACK);		
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Panel.background"));
				
		GroupLayout gl_CenterPanel = new GroupLayout(CenterPanel);
		gl_CenterPanel.setHorizontalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVersion, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_CenterPanel.setVerticalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblVersion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lbluthors = new JLabel("Authors:");
		lbluthors.setForeground(Color.BLUE);
		lbluthors.setHorizontalAlignment(SwingConstants.CENTER);
		lbluthors.setPreferredSize(new Dimension(280, 16));
		panel.add(lbluthors);
		
		JLabel lblJohnGuerson = new JLabel("John Guerson");
		lblJohnGuerson.setHorizontalAlignment(SwingConstants.CENTER);
		lblJohnGuerson.setPreferredSize(new Dimension(280, 16));
		panel.add(lblJohnGuerson);
		
		JLabel lblTiagoPrinc = new JLabel("Tiago Prince");
		lblTiagoPrinc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiagoPrinc.setPreferredSize(new Dimension(280, 16));
		panel.add(lblTiagoPrinc);
		
		JLabel lblAntognoniAlbuquerque = new JLabel("Antognoni Albuquerque");
		lblAntognoniAlbuquerque.setPreferredSize(new Dimension(280, 16));
		lblAntognoniAlbuquerque.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblAntognoniAlbuquerque);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setPreferredSize(new Dimension(280, 10));
		panel.add(lblNewLabel);
		
		JLabel lblActiveCollaborators = new JLabel("Active Collaborators:");
		panel.add(lblActiveCollaborators);
		lblActiveCollaborators.setForeground(Color.RED);
		lblActiveCollaborators.setPreferredSize(new Dimension(280, 16));
		lblActiveCollaborators.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblViniciusSobral = new JLabel("Vinicius Sobral");
		panel.add(lblViniciusSobral);
		lblViniciusSobral.setPreferredSize(new Dimension(280, 16));
		lblViniciusSobral.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblVictorAmorim = new JLabel("Victor Amorim");
		panel.add(lblVictorAmorim);
		lblVictorAmorim.setPreferredSize(new Dimension(280, 16));
		lblVictorAmorim.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblCssioReginato = new JLabel("Cássio Reginato");
		panel.add(lblCssioReginato);
		lblCssioReginato.setPreferredSize(new Dimension(280, 16));
		lblCssioReginato.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblFreddyBrasileiro = new JLabel("Freddy Brasileiro");
		panel.add(lblFreddyBrasileiro);
		lblFreddyBrasileiro.setPreferredSize(new Dimension(280, 14));
		lblFreddyBrasileiro.setHorizontalAlignment(SwingConstants.CENTER);
		CenterPanel.setLayout(gl_CenterPanel);
		
		NorthPanel = new JPanel();
		NorthPanel.setPreferredSize(new Dimension(100, 220));
		getContentPane().add(NorthPanel, BorderLayout.NORTH);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(AboutDialog.class.getResource("/resources/icons/nemo.png")));
		NorthPanel.add(label);
	}
}
