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
package br.ufes.inf.nemo.oled.validator.meronymic;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

/**
 * @author Tiago Sales
 *
 * @param <T>
 */
public abstract class ValidationPanel<T> extends JPanel {

	private static final long serialVersionUID = -510586972834941043L;
	JDialog dialogParent;
	JButton saveButton;
	JButton applyButton;
	
	public ValidationPanel(JDialog dialog, JButton saveButton, JButton applyButton){
		this.saveButton = saveButton;
		this.applyButton = applyButton;
		this.dialogParent = dialog;
	}
	
	public abstract ArrayList<T> getTableResults();
	public abstract Fix runFixes();
	public abstract void clearTable();
	
}
