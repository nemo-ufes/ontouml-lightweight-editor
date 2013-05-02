package br.ufes.inf.nemo.move.ui.ocl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

/**
 * @author John Guerson
 */

public class OCLPathBar extends JPanel {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JTextField textPath;	
	
	public OCLPathBar() 
	{
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		textPath = new JTextField();
		textPath.setFont(new Font("Tahoma", Font.ITALIC, 11));
		textPath.setToolTipText("");
		textPath.setBackground(Color.WHITE);
		textPath.setEditable(false);
		textPath.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(128, 128, 128)));
		textPath.setColumns(10);		
		setPreferredSize(new Dimension(360, 25));
		setLayout(new BorderLayout(0, 0));
		add(textPath, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(16,20));
		panel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(128, 128, 128)));
		panel.setBackground(UIManager.getColor("Panel.background"));
		add(panel, BorderLayout.WEST);
	}	
}
