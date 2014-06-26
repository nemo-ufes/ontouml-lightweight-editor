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
package br.ufes.inf.nemo.oled.util;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * This class implements a BaseTableModel for class proprties, at the moment
 * it simply holds strings.
 *
 * @author Wei-ju Wu
 */
public class StringTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 156864519388945910L;
	private List<String> entries = new LinkedList<String>();

	/**
	 * Adds an entry.
	 * @param entry the entry to add
	 */
	public void addEntry(String entry) {
		int size = entries.size();
		entries.add(entry);
		fireTableRowsInserted(size, size);
	}

	/**
	 * Returns the entries.
	 * @return the entries
	 */
	public List<String> getEntries() {
		return entries;
	}

	/**
	 * Moves up an entry.
	 * @param index the index to move up
	 */
	public void moveUpEntry(int index) {
		String entry = entries.remove(index);
		entries.add(index - 1, entry);
		fireTableDataChanged();
	}

	/**
	 * Moves down an entry.
	 * @param index the index to move down
	 */
	public void moveDownEntry(int index) {
		String entry = entries.remove(index);
		entries.add(index + 1, entry);
		fireTableDataChanged();
	}

	/**
	 * Removes the entry at the specified index.
	 * @param index the index to remove
	 */
	public void removeEntryAt(int index) {
		entries.remove(index);
		fireTableRowsDeleted(index, index);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRowCount() { return entries.size(); }

	/**
	 * {@inheritDoc}
	 */
	public int getColumnCount() { return 1; }

	/**
	 * {@inheritDoc}
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		return entries.get(rowIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		entries.set(rowIndex, value.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) { return true; }
}
