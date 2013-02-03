package br.ufes.inf.nemo.oled.ui.diagram;

import javax.swing.table.AbstractTableModel;

/**
 * This class defines a generic TableModel to hold Namedelementss
 * @author Antognoni Albuquerque
 * @version 1.0
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
