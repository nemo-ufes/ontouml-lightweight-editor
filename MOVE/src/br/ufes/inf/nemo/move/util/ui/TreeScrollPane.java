package br.ufes.inf.nemo.move.util.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * @author John Guerson
 */

public class TreeScrollPane extends JScrollPane {

	private static final long serialVersionUID = 7677939055645227028L;

	public JPanel treePanel;
	
	public TreeScrollPane ()
	{
		treePanel = new JPanel();
		treePanel.setBackground(Color.WHITE);
		treePanel.setLayout(new BorderLayout(0, 0));
		treePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			
		setPreferredSize(new java.awt.Dimension(400, 360));
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
		setViewportView(treePanel);
					
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		southPanel.setPreferredSize(new Dimension(300, 50));
					
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		northPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		northPanel.setPreferredSize(new Dimension(280, 10));
	
		treePanel.add(BorderLayout.NORTH,northPanel);
		
		GroupLayout gl_northPanel = new GroupLayout(northPanel);
		gl_northPanel.setHorizontalGroup(
			gl_northPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 427, Short.MAX_VALUE)
		);
		gl_northPanel.setVerticalGroup(
			gl_northPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 146, Short.MAX_VALUE)
		);
		northPanel.setLayout(gl_northPanel);
	
		treePanel.add(BorderLayout.SOUTH,southPanel);
				
	}
}
