package br.ufes.inf.nemo.move.ui;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.mvc.view.AntiPatternListView;
import br.ufes.inf.nemo.move.mvc.view.OCLView;

public class CentralTabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	
	public CentralTabbedPane ()
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setFont(new Font("Tahoma", Font.PLAIN, 11));		
		setBackground(UIManager.getColor("Panel.background"));
		setBorder(null);
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	public void add (OCLView oclview, String title, boolean closeButton)
	{
		add(oclview);
		setTitleAt(getTabCount(),title);
		setIconAt(getTabCount(),new ImageIcon(TheFrame.class.getResource("/resources/icon/edit-ocl-16x16.png")));
		setBackgroundAt(getTabCount(),UIManager.getColor("Panel.background"));		
		if (closeButton) setTabComponentAt(getTabCount(),new ButtonTabComponent(this));		
	}
	
	public void add (AntiPatternListView antipatternview, String title, boolean closeButton)
	{
		add(antipatternview);	
		setTitleAt(getTabCount(),title);
		setIconAt(getTabCount(),new ImageIcon(TheFrame.class.getResource("/resources/icon/search-16x16.png")));
		setBackgroundAt(getTabCount(),UIManager.getColor("Panel.background"));
		if (closeButton) setTabComponentAt(getTabCount(),new ButtonTabComponent(this));		
	}
}
