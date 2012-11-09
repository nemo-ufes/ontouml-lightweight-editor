package br.ufes.inf.nemo.move.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class TheTextField extends JTextField {
	
	private static final long serialVersionUID = 1L;

	public TheTextField()
	{
		setPreferredSize(new Dimension(30, 20));
		setBorder(new LineBorder(Color.GRAY, 1, true));
		setBackground(Color.WHITE);
		setEditable(false);		
		setColumns(10);		
	}

}
