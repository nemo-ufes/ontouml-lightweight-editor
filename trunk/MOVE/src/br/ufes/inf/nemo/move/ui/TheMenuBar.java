package br.ufes.inf.nemo.move.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This Panel was created using the Windows Builder in Eclipse. 
 * 
 * @author John Guerson
 */

public class TheMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public TheMenuBar()
	{
		JMenu mnHelp = new JMenu("Help");
		add(mnHelp);
	
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}

}
