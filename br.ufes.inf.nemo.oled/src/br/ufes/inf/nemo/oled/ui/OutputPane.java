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
package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class OutputPane extends JPanel
{
	private static final long serialVersionUID = -7066838889714939605L;
	JTextPane output = new JTextPane();
	
	public OutputPane()
	{
		super();
		
		output.setEditable(false);
		output.setBackground(new Color(0xF2FCAC));
		output.setMinimumSize(new Dimension(0, 0));
		
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		JScrollPane scrollpane = new JScrollPane(output);
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollpane.setBorder(new EmptyBorder(0,0,0,0));
		
		this.add(scrollpane, BorderLayout.CENTER);
		this.setMinimumSize(new Dimension(0, 0));		
	}
	
	public void append(String text)
	{
		output.setText(output.getText() + text);
	}
	
	public void write(String text)
	{
		output.setText(text);
	}
}