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
package br.ufes.inf.nemo.oled.dialog.properties;

import javax.swing.table.AbstractTableModel;

/**
 * This class defines a generic TableModel to hold NamedElementss
 * 
 * @author Antognoni Albuquerque
 */
public abstract class BaseTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 3094452512095963338L;

	protected String columns[];
	  
	public BaseTableModel(String[] columns)
	{
		this.columns = columns;
	}
	
	/**
	 * Adds an entry (item) to the model.
	 * @param entry the entry to add
	 */
	public abstract void addEntry(Object entry);
	
	/**
	 * Adds an empty entry (item) to the model.
	 * @param entry the entry to add
	 */
	public abstract void addEmptyEntry();
	
	/**
	 * Returns the entries.
	 * @return the entries
	 */
	public abstract Object getEntries();
	
	/**
	 * Moves up an entry.
	 * @param index the index to move up
	 */
	public abstract void moveUpEntry(int index);
	
	/**
	 * Moves down an entry.
	 * @param index the index to move down
	 */
	public abstract void moveDownEntry(int index);
	
	/**
	 * Removes the entry at the specified index.
	 * @param index the index to remove
	 */
	public abstract void removeEntryAt(int index);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}
	
	/**
	 * {@inheritDoc}
	 */
	public abstract Class<?> getColumnClass(int columnIndex);
	
	/**
	 * {@inheritDoc}
	 */
	public abstract int getRowCount();

	/**
	 * {@inheritDoc}
	 */
	public int getColumnCount() { return columns.length; }
}
