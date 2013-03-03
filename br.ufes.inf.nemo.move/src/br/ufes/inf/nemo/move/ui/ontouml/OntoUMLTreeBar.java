package br.ufes.inf.nemo.move.ui.ontouml;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

/**
 * @author John Guerson
 */

public class OntoUMLTreeBar extends JPanel {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JTextField textPath;	
	public JButton btnOpen;
	public JButton btnVerify;
	public JButton btnShowUnique;
	public JButton btnSaveAs;
	private JToolBar toolBar;
	private JPanel panel;
	
	public OntoUMLTreeBar() 
	{
		setBorder(new LineBorder(Color.LIGHT_GRAY));		
		textPath = new JTextField();
		textPath.setBackground(Color.WHITE);
		textPath.setEditable(false);
		textPath.setColumns(10);
		
		setPreferredSize(new Dimension(360, 61));
		setLayout(new BorderLayout(0, 0));
		
		toolBar = new JToolBar();
		
		btnOpen = new JButton("Open");
		btnOpen.setFocusable(false);
		toolBar.add(btnOpen);
		btnOpen.setToolTipText("Open OntoUML Model (*.refontouml)");
		btnOpen.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/icon/open-16x16.png")));
		
		btnSaveAs = new JButton("Save");
		btnSaveAs.setFocusable(false);
		toolBar.add(btnSaveAs);
		btnSaveAs.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/icon/save-16x16.png")));
		btnSaveAs.setToolTipText("Save OntoUML Model (*.refontouml)");
		
		btnVerify = new JButton("Diagnostic");
		btnVerify.setFocusable(false);
		toolBar.add(btnVerify);
		btnVerify.setToolTipText("See some problems found in your model");
		btnVerify.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/icon/diagnostic-16x16.png")));
		
		btnShowUnique = new JButton("Aliases");
		btnShowUnique.setFocusable(false);
		toolBar.add(btnShowUnique);
		btnShowUnique.setToolTipText("Show Generated Aliases");
		btnShowUnique.setIcon(new ImageIcon(OntoUMLTreeBar.class.getResource("/resources/icon/label2-16x16.png")));
		add(toolBar, BorderLayout.NORTH);
		add(textPath);
		
		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
							
	}	
}
