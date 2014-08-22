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
package br.ufes.inf.nemo.oled.statistician;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * @author John Guerson
 */
public class StatisticsHeadPane extends JPanel {

	private static final long serialVersionUID = -4542364815581324052L;
	private JButton btnRun;
	private JButton btnCopy;
	
	public JButton getRunButton() {
		return btnRun;
	}
	
	public JButton getCopyButton() {
		return btnCopy;
	}
	
	public StatisticsHeadPane() 
	{
		//setBorder(BorderFactory.createTitledBorder(""));
		setBackground(Color.LIGHT_GRAY);
		
		btnRun = new JButton("Collect statistics");
		btnRun.setBackground(Color.LIGHT_GRAY);
		btnRun.setFocusable(false);
		
		btnCopy = new JButton("Copy to Clipboard");
		btnCopy.setBackground(Color.LIGHT_GRAY);
		btnCopy.setEnabled(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnRun)
					.addPreferredGap(ComponentPlacement.RELATED, 492, Short.MAX_VALUE)
					.addComponent(btnCopy)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRun, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addComponent(btnCopy))
					.addGap(8))
		);
		
		setLayout(groupLayout);
		setPreferredSize(new Dimension(710, 44));
	}

	
}
