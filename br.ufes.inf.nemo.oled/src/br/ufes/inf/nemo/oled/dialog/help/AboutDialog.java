package br.ufes.inf.nemo.oled.dialog.help;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.ui.JHyperLinkLabel;


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
	public AboutDialog(final AppFrame frame) 
	{
		super(frame);
		
//		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
//		setIconImage(icon);
		setTitle("About OLED");
		setBounds(100, 100, 473, 288);
		
		getContentPane().setLayout(new BorderLayout());
		
		CenterPanel = new JPanel();
		CenterPanel.setBackground(Color.WHITE);
		CenterPanel.setPreferredSize(new Dimension(150, 190));
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		
		lblVersion = new JLabel("Version: "+Main.OLED_VERSION);
		lblVersion.setForeground(Color.BLACK);		
		lblVersion.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblDate = new JLabel("Compilation Date: "+Main.OLED_COMPILATION_DATE);				
		lblDate.setForeground(Color.BLACK);		
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		
		label = new JLabel("");
		label.setBackground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(AboutDialog.class.getResource("/resources/icons/nemo100.png")));
		
		JLabel lblOntoumlLightweightEditor = new JLabel("OntoUML Lightweight Editor (OLED)");
		lblOntoumlLightweightEditor.setForeground(Color.BLACK);
		lblOntoumlLightweightEditor.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lbluthors = new JLabel("Authors:");
		lbluthors.setForeground(Color.BLACK);
		lbluthors.setPreferredSize(new Dimension(280, 16));
		
		JLabel lblJohnGuerson = new JLabel("John Guerson, Tiago Prince and Antognoni Albuquerque");
		lblJohnGuerson.setForeground(Color.BLACK);
		lblJohnGuerson.setHorizontalAlignment(SwingConstants.LEFT);
		lblJohnGuerson.setPreferredSize(new Dimension(280, 16));
		
		JLabel lblActiveCollaborators = new JLabel("Active Collaborators:");
		lblActiveCollaborators.setForeground(Color.BLACK);
		lblActiveCollaborators.setPreferredSize(new Dimension(280, 16));
		lblActiveCollaborators.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblViniciusSobral = new JLabel("Victor Amorim, Cássio Reginato and Freddy Brasileiro");
		lblViniciusSobral.setForeground(Color.BLACK);
		lblViniciusSobral.setPreferredSize(new Dimension(280, 16));
		lblViniciusSobral.setHorizontalAlignment(SwingConstants.LEFT);
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		JHyperLinkLabel lblNewLabel = new JHyperLinkLabel("");
		lblNewLabel.setText("https://code.google.com/p/ontouml-lightweight-editor/");
		lblNewLabel.addMouseListener(new MouseAdapter() {			
			 @Override
			    public void mouseClicked(MouseEvent e) {				 
					frame.getDiagramManager().openLinkWithBrowser("https://code.google.com/p/ontouml-lightweight-editor/");				 
			 }
		});
		
		GroupLayout gl_CenterPanel = new GroupLayout(CenterPanel);
		gl_CenterPanel.setHorizontalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOntoumlLightweightEditor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
								.addComponent(lblDate, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
								.addComponent(lblVersion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblActiveCollaborators, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblViniciusSobral, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbluthors, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
							.addComponent(lblJohnGuerson, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_CenterPanel.setVerticalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addComponent(lblOntoumlLightweightEditor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(lblVersion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbluthors, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblJohnGuerson, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblActiveCollaborators, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblViniciusSobral, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		CenterPanel.setLayout(gl_CenterPanel);
	}
}
