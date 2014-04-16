package br.ufes.inf.nemo.oled.finder;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class FinderHeadPane extends JPanel {

	private static final long serialVersionUID = -4542364815581324052L;
	private JTextField textField;
	private JButton btnRun;
	
	public String getText(){
		return textField.getText();
	}
	
	public JTextField getTextField()
	{
		return textField;
	}
	
	public JButton getRunButton() {
		return btnRun;
	}
	
	public FinderHeadPane() 
	{
		//setBorder(BorderFactory.createTitledBorder(""));
		setBackground(Color.LIGHT_GRAY);		
		textField = new JTextField();
		textField.setColumns(10);
		
		btnRun = new JButton("");
		btnRun.setIcon(new ImageIcon(FinderHeadPane.class.getResource("/resources/icons/x16/next.png")));
		btnRun.setBackground(Color.LIGHT_GRAY);
				
		JLabel lblTerm = new JLabel("Term:");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTerm, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(364, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTerm, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		setLayout(groupLayout);
		setPreferredSize(new Dimension(710, 44));
	}
}
