/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class DiagramToolbar extends JToolBar {

	private static final long serialVersionUID = 1L;
	public DiagramEditor editor;
	public JButton btnAlignBottom;
	private JButton btnAlignRight;
	private JButton btnAlignTop;
	private JButton btnAlignLeft;
	private JButton btnAlignCenterVertically;
	private JButton btnAlignCenterHorizontally;
	private JButton btnBringToFront;
	private JButton btnPutToBack;
	
	public DiagramToolbar (final DiagramEditor editor)
	{
		this.editor = editor;
		setFloatable(false);
		setMargin(new Insets(5,5,5,5));
		
		btnAlignBottom = new JButton("");
		btnAlignBottom.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignBottom();
        	}
        });
		btnAlignBottom.setToolTipText("Align Bottom");
		btnAlignBottom.setFocusable(false);
		btnAlignBottom.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_bottom.png")));
		add(btnAlignBottom);
		
		btnAlignTop = new JButton("");
		btnAlignTop.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignTop();
        	}
        });
		btnAlignTop.setToolTipText("Align Top");
		btnAlignTop.setFocusable(false);
		btnAlignTop.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_top.png")));
		add(btnAlignTop);
		
		btnAlignLeft = new JButton("");
		btnAlignLeft.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignLeft();
        	}
        });
		btnAlignLeft.setToolTipText("Align Left");
		btnAlignLeft.setFocusable(false);
		btnAlignLeft.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_left.png")));
		add(btnAlignLeft);
		
		btnAlignRight = new JButton("");
		btnAlignRight.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignRight();
        	}
        });
		btnAlignRight.setToolTipText("Align Right");
		btnAlignRight.setFocusable(false);
		btnAlignRight.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_right.png")));
		add(btnAlignRight);
		
		btnAlignCenterVertically = new JButton("");
		btnAlignCenterVertically.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignCenterVertically();
        	}
        });
		btnAlignCenterVertically.setToolTipText("Align Center Vertically");
		btnAlignCenterVertically.setFocusable(false);
		btnAlignCenterVertically.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_center.png")));
		add(btnAlignCenterVertically);
		
		btnAlignCenterHorizontally = new JButton("");
		btnAlignCenterHorizontally.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignCenterHorizontally();
        	}
        });	
		btnAlignCenterHorizontally.setToolTipText("Align Center Horizontally");
		btnAlignCenterHorizontally.setFocusable(false);
		btnAlignCenterHorizontally.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_middle.png")));
		add(btnAlignCenterHorizontally);
		
		btnBringToFront = new JButton("");
		btnBringToFront.setToolTipText("Bring to Front");
		btnBringToFront.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.bringToFront();
        	}
        });		
		btnBringToFront.setFocusable(false);
		btnBringToFront.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_move_front.png")));
		add(btnBringToFront);
		
		btnPutToBack = new JButton("");
		btnPutToBack.setToolTipText("Put to Back");
		btnPutToBack.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.putToBack();
        	}
        });	
		btnPutToBack.setFocusable(false);
		btnPutToBack.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_move_back.png")));
		add(btnPutToBack);
	}
}