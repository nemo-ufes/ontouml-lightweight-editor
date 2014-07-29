package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.oled.DiagramManager;

public class ParticipationPatternTypeChoice extends JDialog {

	private static final long serialVersionUID = 4743589970356381789L;
	
	private final JPanel contentPanel = new JPanel();
	JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("The type is derived by a PART-WHOLE relation (ex: Team Member, Team)");
	JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("The derivation is BIDIRECTIONAL (ex: Husband, Wife)");
	JRadioButton rdbtnNewRadioButton = new JRadioButton("The derivation is UNIDIRECTIONAL (ex: Manager, Department)");
	private DiagramManager dman;
	Point2D.Double location = new Point2D.Double();
	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the dialog.
	 */
	public ParticipationPatternTypeChoice(DiagramManager dm) {
		dman=dm;
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ParticipationPatternTypeChoice.class.getResource("/resources/icons/x16/sitemap.png")));
		setTitle("Participation Options");
		setBounds(100, 100, 486, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		ButtonGroup bt = new ButtonGroup();
		rdbtnNewRadioButton.setSelected(true);
		
		
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		bt.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		bt.add(rdbtnNewRadioButton_1);
		
		
		rdbtnNewRadioButton_2.setBackground(Color.WHITE);
		bt.add(rdbtnNewRadioButton_2);
		
		JTextPane txtrWhatsTheType = new JTextPane();
		txtrWhatsTheType.setBackground(Color.WHITE);
		txtrWhatsTheType.setEditable(false);
		txtrWhatsTheType.setForeground(Color.BLACK);
		txtrWhatsTheType.setText("What's the type of the derivation by participation?\r\n");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(rdbtnNewRadioButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtrWhatsTheType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
						.addComponent(rdbtnNewRadioButton_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnNewRadioButton_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrWhatsTheType, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnNewRadioButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnNewRadioButton_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnNewRadioButton_2)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
						JDialog dialog = null;
						
						if(rdbtnNewRadioButton.isSelected()){
							dialog = new UnidirectionalParticipationPattern(dman);
							dman.setCenterDialog(dialog);
							((UnidirectionalParticipationPattern) dialog).setPosition(new Point2D.Double(location.x, location.y));
							dialog.setModal(true);
							dialog.setVisible(true);
						}else if(rdbtnNewRadioButton_1.isSelected()){
							dialog = new BidirectionalParticipationPattern(dman);
							dman.setCenterDialog(dialog);
							((BidirectionalParticipationPattern) dialog).setPosition(new Point2D.Double(location.x, location.y));
							dialog.setModal(true);
							dialog.setVisible(true);
						}else if(rdbtnNewRadioButton_2.isSelected()){
							dialog = new CompositionParticipationPattern(dman);
							dman.setCenterDialog(dialog);
							((CompositionParticipationPattern) dialog).setPosition(new Point2D.Double(location.x, location.y));
							dialog.setModal(true);
							dialog.setVisible(true);
						}
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void setPosition(double x, double y) {
		// TODO Auto-generated method stub
		location.x= x;
		location.y= y;
		
	}
}
