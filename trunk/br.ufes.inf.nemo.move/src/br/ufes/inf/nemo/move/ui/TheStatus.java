package br.ufes.inf.nemo.move.ui;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;


public class TheStatus extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel label;
	
	public void setText(String text)
	{
		label.setText(text);
	}
	
	public void getText()
	{
		label.getText();
	}
	
	/**
	 * Create the panel.
	 */
	public TheStatus() {
		
		setPreferredSize(new Dimension(450, 38));
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setPreferredSize(new Dimension(200,30));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		
	}

}
