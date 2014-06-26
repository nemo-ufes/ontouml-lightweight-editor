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
package br.ufes.inf.nemo.oled.ui.diagram.commands;

import javax.swing.undo.UndoableEdit;

/**
 * This is the Command interface extends the UndoableEdit and the Runnable
 * interface. Since there is no do() method, but instead only redo and undo,
 * the run() command contains the action that is to be performed. Redo()
 * should simply call run(). Before the "Chocobo" release, TinyUML used an
 * own implementation of the Command pattern, this was abandoned in favor
 * of the Swing Undo framework, since it is more flexible and can be easily
 * integrated with operations on standard Swing components.
 *
 * @author Wei-ju Wu
 */
public interface Command extends Runnable, UndoableEdit {
		
}
