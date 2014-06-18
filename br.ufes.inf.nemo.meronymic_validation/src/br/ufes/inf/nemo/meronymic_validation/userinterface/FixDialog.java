package br.ufes.inf.nemo.meronymic_validation.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.meronymic_validation.checkers.GeneralizationError;

public class FixDialog extends JDialog {

	private static final long serialVersionUID = -7889331554525024052L;

	private final JPanel contentPanel = new JPanel();
	private JButton saveButton;
	private JButton cancelButton;
	
	private GeneralizationError error;
	
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public FixDialog(JDialog parent, GeneralizationError error) {
		super(parent);
		setTitle("Dialog Title");
		this.error = error;
		
		setBounds(100, 100, 624, 555);
		
		JPanel titlePane = new JPanel();
		titlePane.setBackground(Color.WHITE);
		
		title = new JLabel("Main Title");
		title.setBackground(Color.WHITE);
		
		title.setFont(new Font("Dialog", title.getFont().getStyle() | Font.BOLD, title.getFont().getSize() + 8));
		title.setHorizontalAlignment(SwingConstants.LEFT);

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 608, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 344, Short.MAX_VALUE)
		);
		
		
		contentPanel.setLayout(gl_contentPanel);
		
		buttonPane = new JPanel();
			
		saveButton = new JButton("Save");
		saveButton.setEnabled(false);
		getRootPane().setDefaultButton(saveButton);
			
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(cancelAction);
			
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(titlePane, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
				.addComponent(contentPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
				.addComponent(buttonPane, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(titlePane, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
		);
		
		separator_1 = new JSeparator();
		
		JButton btnNewButton = new JButton("Help");
		btnNewButton.setIcon(new ImageIcon(FixDialog.class.getResource("/br/ufes/inf/nemo/meronymic_validation/resources/help.png")));
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
					.addComponent(saveButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cancelButton)
					.addContainerGap())
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(2)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton)
						.addComponent(saveButton)
						.addComponent(btnNewButton))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		buttonPane.setLayout(gl_buttonPane);
		
		txtpnSubtitleGoesHere = new JTextPane();
		txtpnSubtitleGoesHere.setEditable(false);
		txtpnSubtitleGoesHere.setText("Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here...");
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_titlePane = new GroupLayout(titlePane);
		gl_titlePane.setHorizontalGroup(
			gl_titlePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_titlePane.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtpnSubtitleGoesHere, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
						.addComponent(title, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
					.addContainerGap())
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
		);
		gl_titlePane.setVerticalGroup(
			gl_titlePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePane.createSequentialGroup()
					.addContainerGap()
					.addComponent(title)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnSubtitleGoesHere, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		titlePane.setLayout(gl_titlePane);
		getContentPane().setLayout(groupLayout);
	}
	
	
	private ActionListener cancelAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			FixDialog.this.dispose();
			
		}
	};
	
	
	private JPanel buttonPane;
	private JSeparator separator_1;
	private JLabel title;
	private JTextPane txtpnSubtitleGoesHere;
}
