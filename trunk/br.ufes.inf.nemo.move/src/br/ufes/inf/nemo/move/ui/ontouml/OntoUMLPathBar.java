package br.ufes.inf.nemo.move.ui.ontouml;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

/**
 * @author John Guerson
 */

public class OntoUMLPathBar extends JPanel {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JTextField textPath;	
	
	public OntoUMLPathBar() 
	{
		setBackground(UIManager.getColor("Panel.background"));
		setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.GRAY));
		textPath = new JTextField();
		textPath.setFont(new Font("Tahoma", Font.ITALIC, 11));
		textPath.setToolTipText("");
		textPath.setBackground(Color.WHITE);
		textPath.setEditable(false);
		textPath.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(128, 128, 128)));
		textPath.setColumns(10);		
		setPreferredSize(new Dimension(360, 25));
		setLayout(new BorderLayout(0, 0));
		add(textPath, BorderLayout.CENTER);
		
		setBorder(null);
		
		Component rigidArea = Box.createRigidArea(new Dimension(15, 20));		
		add(rigidArea, BorderLayout.WEST);
	}	
}
