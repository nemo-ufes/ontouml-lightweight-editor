package br.ufes.inf.nemo.move.ui.antipattern;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

/**
 * @author John Guerson
 */

public class AntiPatternToolBar extends JToolBar {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JButton btnRun;
	
	public AntiPatternToolBar() 
	{
		setBackground(Color.WHITE);
		setFloatable(false);
		setPreferredSize(new Dimension(30, 30));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)));
		
		btnRun = new JButton("");
		btnRun.setBackground(Color.WHITE);
		btnRun.setFocusable(false);
		btnRun.setToolTipText("Run antipatterns manager");
		btnRun.setIcon(new ImageIcon(AntiPatternToolBar.class.getResource("/resources/icon/play-red-16x16.png")));
				
		JSeparator toolbarSeparator1 = new JToolBar.Separator();
		toolbarSeparator1.setOrientation(SwingConstants.VERTICAL);
		
		add(btnRun);
		add(toolbarSeparator1);
	}	
}
