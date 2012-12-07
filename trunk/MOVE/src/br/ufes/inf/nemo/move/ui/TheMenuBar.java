package br.ufes.inf.nemo.move.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.ufes.inf.nemo.move.ui.dialog.AboutDialog;


/**
 * @author John Guerson
 */

public class TheMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
		
	private TheFrame frame;	
	private JMenuItem mntmAbout; 
	
	/**
	 * Constructor.
	 * 
	 * @param parent
	 */
	public TheMenuBar (TheFrame parent)
	{
		this();
		
		this.frame = parent;
	}
	
	/**
	 * Constructor.
	 */
	public TheMenuBar()
	{	
		super();
		
		JMenu mnHelp = new JMenu("Help");
		add(mnHelp);
		
		mntmAbout = new JMenuItem("About MOVE");
		mnHelp.add(mntmAbout);
		
		addActionListeners();
	}
	
	/**
	 * Add Action Listener on Menu Items.
	 */
	private void addActionListeners()
	{
		mntmAbout.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			AboutDialog.open(frame);
       		}
		});
	}
}
