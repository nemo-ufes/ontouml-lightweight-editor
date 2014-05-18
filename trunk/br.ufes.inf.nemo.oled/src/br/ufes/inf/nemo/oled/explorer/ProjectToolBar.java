package br.ufes.inf.nemo.oled.explorer;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import br.ufes.inf.nemo.oled.DiagramManager;

public class ProjectToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private ProjectTree tree;
	@SuppressWarnings("unused")
	private DiagramManager diagramManager;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnCompleteSelection;
		
	public ProjectToolBar (ProjectTree tree, final DiagramManager diagramManager)
	{		
		this.tree = tree;
		this.diagramManager = diagramManager;
		
		setFloatable(false);
		setMargin(new Insets(5,5,5,5));
		
		btnCompleteSelection = new JButton("");
		btnCompleteSelection.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.getEditorDispatcher().autoComplete();
        	}
        });		
		btnCompleteSelection.setToolTipText("<html>Check the elements dependencies on the tree <br>and complete the selection with the missing dependencies</html>");
		btnCompleteSelection.setFocusable(false);
		btnCompleteSelection.setIcon(new ImageIcon(ProjectToolBar.class.getResource("/resources/icons/x16/accept-black.png")));
		add(btnCompleteSelection);
		
		btnUp = new JButton("");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
		btnUp.setToolTipText("Move the current element up within its package");
		btnUp.setFocusable(false);
		btnUp.setIcon(new ImageIcon(ProjectToolBar.class.getResource("/resources/icons/x16/arrow_up.png")));
		add(btnUp);
		
		btnDown = new JButton("");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
		btnDown.setToolTipText("Move the current element down within its package");
		btnDown.setFocusable(false);
		btnDown.setIcon(new ImageIcon(ProjectToolBar.class.getResource("/resources/icons/x16/arrow_down.png")));
		add(btnDown);
		
		}
}