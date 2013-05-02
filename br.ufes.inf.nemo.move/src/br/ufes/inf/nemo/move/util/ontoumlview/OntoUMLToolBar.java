package br.ufes.inf.nemo.move.util.ontoumlview;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * @author John Guerson
 */

public class OntoUMLToolBar extends JToolBar {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JButton btnOpen;
	public JButton btnVerify;
	public JButton btnShowUnique;
	public JButton btnSaveAs;
	
	public OntoUMLToolBar() 
	{
		setBackground(Color.WHITE);
		setFloatable(false);
		setPreferredSize(new Dimension(30,50));
		
		btnOpen = new JButton("");
		btnOpen.setBackground(Color.WHITE);
		btnOpen.setFocusable(false);
		add(btnOpen);
		
		btnOpen.setToolTipText("Open OntoUML Model (*.refontouml)");
		btnOpen.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/open-16x16.png")));
		
		btnSaveAs = new JButton("");
		btnSaveAs.setBackground(Color.WHITE);
		btnSaveAs.setFocusable(false);
		
		add(btnSaveAs);
		btnSaveAs.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/save-16x16.png")));
		btnSaveAs.setToolTipText("Save OntoUML Model (*.refontouml)");
		
		btnVerify = new JButton("");
		btnVerify.setBackground(Color.WHITE);
		btnVerify.setFocusable(false);
		
		add(btnVerify);
		btnVerify.setToolTipText("See some problems found in your model");
		btnVerify.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/diagnostic-16x16.png")));
		
		btnShowUnique = new JButton("");
		btnShowUnique.setBackground(Color.WHITE);
		btnShowUnique.setFocusable(false);
		
		add(btnShowUnique);
		btnShowUnique.setToolTipText("Show Generated Aliases");
		btnShowUnique.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/label2-16x16.png")));
									
	}	
}
