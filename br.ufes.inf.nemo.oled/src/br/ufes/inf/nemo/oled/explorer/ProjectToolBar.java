package br.ufes.inf.nemo.oled.explorer;

import java.awt.Insets;

import javax.swing.JToolBar;

import br.ufes.inf.nemo.oled.DiagramManager;

public class ProjectToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private ProjectTree tree;
	@SuppressWarnings("unused")
	private DiagramManager diagramManager;
//	private JButton btnCompleteSelection;
//	private JButton btnRefresh;
		
	public ProjectToolBar (ProjectTree tree, final DiagramManager diagramManager)
	{		
		this.tree = tree;
		this.diagramManager = diagramManager;
		
		setFloatable(false);
		setMargin(new Insets(5,5,5,5));
		
//		btnRefresh = new JButton("");
//		btnRefresh.setFocusable(false);
//		btnRefresh.setIcon(new ImageIcon(ProjectToolBar.class.getResource("/resources/icons/x16/arrow_refresh.png")));
//		add(btnRefresh);
//		
//		btnCompleteSelection = new JButton("");
//		btnCompleteSelection.addActionListener(new ActionListener() {				
//        	@Override
//        	public void actionPerformed(ActionEvent e) {
//        		diagramManager.getEditorDispatcher().autoComplete();
//        	}
//        });		
//		btnCompleteSelection.setToolTipText("<html>Check the elements dependencies on the tree <br>and complete the selection with the missing dependencies</html>");
//		btnCompleteSelection.setFocusable(false);
//		btnCompleteSelection.setIcon(new ImageIcon(ProjectToolBar.class.getResource("/resources/icons/x16/accept.png")));
//		add(btnCompleteSelection);
	}
}