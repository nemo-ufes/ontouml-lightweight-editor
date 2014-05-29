package br.ufes.inf.nemo.oled.finder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;

public class FinderPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = 2665584279780047982L;
	@SuppressWarnings("unused")
	private EObject context;
	
	public FinderPopupMenu(final EObject context)
	{
		this.context = context;
		
		JMenuItem findInProjectMenuItem = new JMenuItem("Find in Project");
		add(findInProjectMenuItem);
		
		findInProjectMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				ProjectBrowser.frame.getDiagramManager().getFrame().getBrowserManager().getProjectBrowser().getTree().checkModelElement(context);		
			}
		});
	}
}