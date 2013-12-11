package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class OutputPane extends JPanel
{
	private static final long serialVersionUID = -7066838889714939605L;
	JTextPane output = new JTextPane();
	
	public OutputPane()
	{
		super();
		
		output.setEditable(false);
		output.setBackground(new Color(0xF2FCAC));
		output.setMinimumSize(new Dimension(0, 0));
		
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		JScrollPane scrollpane = new JScrollPane(output);
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollpane.setBorder(new EmptyBorder(0,0,0,0));
		
		this.add(scrollpane, BorderLayout.CENTER);
		this.setMinimumSize(new Dimension(0, 0));		
	}
	
	public void append(String text)
	{
		output.setText(output.getText() + text);
	}
	
	public void write(String text)
	{
		output.setText(text);
	}
}