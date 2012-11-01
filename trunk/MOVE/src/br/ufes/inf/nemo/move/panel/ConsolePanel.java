package br.ufes.inf.nemo.move.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class ConsolePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ConsolePanel() 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JTextPane txtpnConsole = new JTextPane();
		txtpnConsole.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnConsole.setText("   Console");
		txtpnConsole.setForeground(Color.WHITE);
		txtpnConsole.setBackground(Color.BLACK);
		txtpnConsole.setEditable(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(txtpnConsole, GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(txtpnConsole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(148, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
