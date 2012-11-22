package br.ufes.inf.nemo.move.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author John Guerson
 */

public class TitleTextField extends JTextField {

	private static final long serialVersionUID = -7631161118535364426L;

	public TitleTextField()
	{		
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Tahoma", Font.BOLD, 11));
		setForeground(SystemColor.window);
		setEditable(false);
		setBackground(Color.BLACK);
		setColumns(10);
		setBorder(new EmptyBorder(0, 0, 0, 0));
	}
}
