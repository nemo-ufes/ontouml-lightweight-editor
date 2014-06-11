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

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.Main;


public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -251319551154959770L;
	private JPanel CenterPanel;
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
		setBounds(100, 100, 473, 284);
		
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
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(AboutDialog.class.getResource("/resources/icons/nemo100.png")));
		
		JLabel lblOntoumlLightweightEditor = new JLabel("OntoUML Lightweight Editor (OLED)");
		lblOntoumlLightweightEditor.setForeground(Color.BLACK);
		lblOntoumlLightweightEditor.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbluthors = new JLabel("Authors:");
		lbluthors.setForeground(Color.BLACK);
		lbluthors.setPreferredSize(new Dimension(280, 16));
		
		JLabel lblJohnGuerson = new JLabel("John Guerson, Tiago Prince and Antognoni Albuquerque");
		lblJohnGuerson.setForeground(Color.BLACK);
		lblJohnGuerson.setHorizontalAlignment(SwingConstants.LEFT);
		lblJohnGuerson.setPreferredSize(new Dimension(280, 16));
		
		JLabel lblActiveCollaborators = new JLabel("(Active) Collaborators:");
		lblActiveCollaborators.setForeground(Color.BLACK);
		lblActiveCollaborators.setPreferredSize(new Dimension(280, 16));
		lblActiveCollaborators.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblViniciusSobral = new JLabel("Vinicius Sobral, Victor Amorim, Cássio Reginato and Freddy Brasileiro");
		lblViniciusSobral.setForeground(Color.BLACK);
		lblViniciusSobral.setPreferredSize(new Dimension(280, 16));
		lblViniciusSobral.setHorizontalAlignment(SwingConstants.LEFT);
				
		GroupLayout gl_CenterPanel = new GroupLayout(CenterPanel);
		gl_CenterPanel.setHorizontalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblOntoumlLightweightEditor, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVersion, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_CenterPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblActiveCollaborators, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblViniciusSobral, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
							.addComponent(lblJohnGuerson, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lbluthors, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_CenterPanel.setVerticalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addGap(11)
							.addComponent(lblOntoumlLightweightEditor)
							.addGap(18)
							.addComponent(lblVersion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDate)))
					.addGap(28)
					.addComponent(lbluthors, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblJohnGuerson, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblActiveCollaborators, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblViniciusSobral, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		CenterPanel.setLayout(gl_CenterPanel);
	}
}
