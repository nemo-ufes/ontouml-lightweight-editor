package br.ufes.inf.nemo.move.util.ontoumlview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
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
		setBorder(new EmptyBorder(0, 0, 0, 0));
		textPath = new JTextField();
		textPath.setToolTipText("");
		textPath.setBackground(Color.WHITE);
		textPath.setEditable(false);
		textPath.setBorder(new MatteBorder(0, 0, 1, 1, (Color) Color.LIGHT_GRAY));
		textPath.setColumns(10);		
		setPreferredSize(new Dimension(360, 25));
		setLayout(new BorderLayout(0, 0));
		add(textPath, BorderLayout.CENTER);		
		Component rigidArea = Box.createRigidArea(new Dimension(41, 20));
		add(rigidArea, BorderLayout.WEST);
	}	
}
