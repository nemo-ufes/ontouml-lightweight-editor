package br.ufes.inf.nemo.oled.popupmenu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import org.eclipse.emf.edit.provider.IDisposable;

public class TabPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -7280793052746143720L;
	private JTabbedPane pane;
	private Component comp;
	
	public TabPopupMenu(final JTabbedPane pane, final Component comp)
	{
		this.pane = pane;
		this.comp = comp;
		
		JMenuItem close = new JMenuItem("Close");
		JMenuItem closeOthers = new JMenuItem("Close Others");
		JMenuItem closeAll = new JMenuItem("Close All");
		
		add(close);
		add(closeOthers);
		add(closeAll);
		
		close.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {				
				closeTab(pane.indexOfComponent(comp));
			}
		});
		closeOthers.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {				
				closeOthers();
			}
		});
		closeAll.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {				
				closeAll();
			}
		});
	}
	
	public void closeAll()
	{
		 int tabCount = pane.getTabCount();
         
         for (int i = 1; i < tabCount; i++) {
             closeTab(1);
         }
	}
	
	public void closeOthers()
	{	
		int selectedTabIndex = pane.indexOfComponent(comp);
		
		 // First remove higher indexes 
        int tabCount = pane.getTabCount();
         
        if (selectedTabIndex < tabCount - 1) {
            for (int i = selectedTabIndex + 1; i < tabCount; i++) {
                closeTab(selectedTabIndex + 1);
            }
        }
         
        if (selectedTabIndex > 0) {
            for (int i = 1; i < selectedTabIndex; i++) {
                closeTab(1);
            }
        }
	}
	
	public void closeTab(int i)
	{		
		if (i != -1) {
			IDisposable disposable = (IDisposable) pane.getComponentAt(i);
			if(disposable != null) disposable.dispose();			
			pane.remove(i);
		}
	}
}
