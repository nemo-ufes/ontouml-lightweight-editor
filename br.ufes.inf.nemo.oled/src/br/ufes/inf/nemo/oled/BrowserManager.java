package br.ufes.inf.nemo.oled;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import javax.swing.border.EmptyBorder;

public class BrowserManager extends JTabbedPane {

	private static final long serialVersionUID = 1752050268631906319L;
	@SuppressWarnings("unused")
	private AppFrame frame;
	private ProjectBrowser browser;
	
	public BrowserManager(AppFrame frame)
	{
		super();
		
		this.frame = frame;	
		
		setFocusable(false);
		
		browser = new ProjectBrowser(frame,null);
		browser.setBorder(new EmptyBorder(0, 0, 0, 0));
		browser.setPreferredSize(new Dimension(230,250));
		
		addTab("Project Browser", browser); 
		setIconAt(indexOfComponent(browser),new ImageIcon(BrowserManager.class.getResource("/resources/icons/x16/drawer.png")));
	}
	
	public ProjectBrowser getProjectBrowser() { return browser; }
	
}
