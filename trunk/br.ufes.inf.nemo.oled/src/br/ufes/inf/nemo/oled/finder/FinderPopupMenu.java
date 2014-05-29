package br.ufes.inf.nemo.oled.finder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Element;
import br.ufes.inf.nemo.oled.dialog.DiagramListDialog;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;

public class FinderPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = 2665584279780047982L;
	@SuppressWarnings("unused")
	private EObject context;
	
	public FinderPopupMenu(final EObject context)
	{
		this.context = context;
		
		JMenuItem findInProjectMenuItem = new JMenuItem("Find in Project");
		add(findInProjectMenuItem);
		
		JMenuItem findInDiagramMenuItem = new JMenuItem("Find in Diagrams");
		add(findInDiagramMenuItem);
		
		findInProjectMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				ProjectBrowser.frame.getDiagramManager().getFrame().getBrowserManager().getProjectBrowser().getTree().checkModelElement(context);		
			}
		});
		
		findInDiagramMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				ArrayList<DiagramEditor> diagrams = ProjectBrowser.frame.getDiagramManager().getDiagramEditors((Element)context);
				DiagramListDialog.open(ProjectBrowser.frame, diagrams,(Element) context);
			}
		});
	}
}