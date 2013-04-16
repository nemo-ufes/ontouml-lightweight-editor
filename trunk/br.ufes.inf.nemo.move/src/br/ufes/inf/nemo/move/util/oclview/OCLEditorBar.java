package br.ufes.inf.nemo.move.util.oclview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

/**
 * @author John Guerson
 */

public class OCLEditorBar extends JPanel {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JTextField textPath;	
	public JButton btnOpen;
	public JButton btnSave;
	public JButton btnNew;
	public JButton btnParse;
	private JPanel panel;
	private JPanel westPanel;
	private JPanel EastPanel;
	
	public OCLEditorBar() 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		textPath = new JTextField();
		textPath.setToolTipText("");
		textPath.setBackground(Color.WHITE);
		textPath.setEditable(false);
		textPath.setColumns(10);		
		setPreferredSize(new Dimension(360, 61));
		
		JToolBar toolBar = new JToolBar();
		FlowLayout fl_toolBar = new FlowLayout(FlowLayout.LEFT);
		fl_toolBar.setVgap(1);
		fl_toolBar.setHgap(3);
		toolBar.setLayout(fl_toolBar);
		
		btnNew = new JButton("New");
		btnNew.setFocusable(false);
		toolBar.add(btnNew);
		btnNew.setToolTipText("New OCL textual document (*.ocl)");
		btnNew.setIcon(new ImageIcon(OCLEditorBar.class.getResource("/resources/icon/doc-16x16.png")));
		
		btnOpen = new JButton("Open");
		btnOpen.setFocusable(false);
		toolBar.add(btnOpen);
		btnOpen.setToolTipText("Open OCL textual document (*.ocl)");
		btnOpen.setIcon(new ImageIcon(OCLEditorBar.class.getResource("/resources/icon/open-16x16.png")));
		
		btnSave = new JButton("Save");
		btnSave.setFocusable(false);
		toolBar.add(btnSave);
		btnSave.setToolTipText("Save constraints into an OCL textual document (*.ocl)");
		btnSave.setIcon(new ImageIcon(OCLEditorBar.class.getResource("/resources/icon/save-16x16.png")));
		
		btnParse = new JButton("Parse");
		btnParse.setFocusable(false);
		toolBar.add(btnParse);
		btnParse.setToolTipText("Verify constraints syntactically");
		btnParse.setIcon(new ImageIcon(OCLEditorBar.class.getResource("/resources/icon/check-16x16.png")));
		setLayout(new BorderLayout(0, 0));
		add(textPath, BorderLayout.CENTER);
		add(toolBar, BorderLayout.NORTH);
		
		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		westPanel = new JPanel();
		add(westPanel, BorderLayout.WEST);
		
		EastPanel = new JPanel();
		add(EastPanel, BorderLayout.EAST);
	}	
}
