package br.ufes.inf.nemo.move.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TheConsolePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrollpane;	
	private JTextPane output;
	private JTextPane title;
	
	public void append(String text)
	{
		output.setText(output.getText() + text);
	}
	
	public void write(String text)
	{
		output.setText(text);
	}
	
	/**
	 * Create the panel.
	 */
	public TheConsolePanel() 
	{
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		title = new JTextPane();
		title.setFont(new Font("Tahoma", Font.BOLD, 11));
		title.setText("   Console");
		title.setForeground(Color.WHITE);
		title.setBackground(Color.BLACK);
		title.setEditable(false);
		
		output = new JTextPane();
		output.setBorder(new EmptyBorder(0, 0, 0, 0));
		output.setEditable(false);
		
		scrollpane = new JScrollPane();
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollpane.setViewportView(output);
		scrollpane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
				.addComponent(title, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
		);
		groupLayout.setAutoCreateContainerGaps(false);
		groupLayout.setAutoCreateGaps(false);
		setLayout(groupLayout);

	}
}
