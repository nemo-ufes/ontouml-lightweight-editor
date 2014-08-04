/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.palette;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Antognoni Albuquerque
 */
public class PaletteElement extends JPanel implements MouseListener, MouseMotionListener
{

	private static final long serialVersionUID = 5550202293825101613L;
	
	private Palette parent = null;
	private String command = ""; 
	private String caption = new String();
	private String type = new String();
	
	public String getCaption() { return caption; }
	public String getType() { return type; }
	
	private boolean isSelected = false;
	
	public PaletteElement(Icon icon, String caption, String command, Palette palette, String type)
	{
		super();

		this.command = command;
		this.parent = palette;
		this.caption = caption;
		this.type = type;
		
		this.setMaximumSize(new Dimension(32767, 24));
		this.setSize(new Dimension(200, 24));
		this.setPreferredSize(new Dimension(200, 24));
		this.setMinimumSize(new Dimension(0, 24));
		
		
		this.setBorder(PaletteAccordion.getResetBorder());
		this.setBackground(PaletteAccordion.getResetBackground());

		this.setLayout(new BorderLayout());

		JLabel label = new JLabel(caption, icon, JLabel.LEFT);
		label.setIconTextGap(10);
		label.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 1));
		
		this.add(label, BorderLayout.CENTER);
		this.addMouseListener(this);
	}

	public void setSelected(boolean selected) {
		this.isSelected = selected;
		if(selected)
		{
			setSelectedStyle();
			parent.NotifySelection(this);
		}
		else
		{
			resetStyle();
		}
	}

	public boolean isSelected() {
		return isSelected;
	}

	private void setSelectedStyle()
	{
		setBackground(PaletteAccordion.getSelectedItemBackground());
		setBorder(PaletteAccordion.getSelectedItemBorder());
		repaint();
	}

	private void setHoverStyle()
	{
		setBackground(PaletteAccordion.getHoverItemBackground());
		setBorder(PaletteAccordion.getHoverItemBorder());
		repaint();
	}

	private void resetStyle()
	{
		setBackground(PaletteAccordion.getResetBackground());
		setBorder(PaletteAccordion.getResetBorder());
		repaint();
	}

	public String getCommand() {
		return command;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {		
		if(!isSelected)
		setHoverStyle();
	}

	@Override
	public void mouseExited(MouseEvent e) {		
		if(!isSelected)
		resetStyle();
	}

	//==========================================
	
	@Override
	public void mousePressed(MouseEvent e) {
		setSelected(true);				
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {		
		
	}
	
	//============================================
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
}