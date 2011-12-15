package br.inf.ufes.nemo.oled.ui.diagram;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import RefOntoUML.DataType;
import RefOntoUML.Property;
import br.inf.ufes.nemo.oled.util.ModelHelper;

/**
 * This class implements a TableModel for class proprties, at the moment
 *
 * @author Antognoni Albuquerque
 * @version 1.0
 */
public class PropertyTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 156864519388945910L;
	private List<Property> entries = new LinkedList<Property>();
	private String headerList[] = new String[]{"Name", "Type"};
	
	public PropertyTableModel()
	{
		super();
	}
	
	/**
	 * Adds an entry.
	 * @param entry the entry to add
	 */
	public void addEntry(Property entry) {
		int size = entries.size();
		entries.add(entry);
		fireTableRowsInserted(size, size);
	}

	public void addEmptyEntry() {
		Property property = ModelHelper.getFactory().createProperty();
		DataType type = ModelHelper.getFactory().createDataType();
		type.setName("");
		property.setType(type);
		property.setName("");
		
		addEntry(property);
	}
	
	/**
	 * Returns the entries.
	 * @return the entries
	 */
	public List<Property> getEntries() {
		return entries;
	}

	public Set<String> getDataTypesNames() {
		Set<String> dataTypes = new HashSet<String>();
		for (Property entry : entries) {
			dataTypes.add(entry.getDatatype().getName());
		}
		return dataTypes;
	}
	
	/**
	 * Moves up an entry.
	 * @param index the index to move up
	 */
	public void moveUpEntry(int index) {
		Property entry = entries.remove(index);
		entries.add(index - 1, entry);
		fireTableDataChanged();
	}

	/**
	 * Moves down an entry.
	 * @param index the index to move down
	 */
	public void moveDownEntry(int index) {
		Property entry = entries.remove(index);
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
	public int getColumnCount() { return 2; }

	/**
	 * {@inheritDoc}
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(entries.size() > 0)
		{
			if(columnIndex == 0)
				return entries.get(rowIndex).getName();
			else if(columnIndex == 1)
				return entries.get(rowIndex).getType().getName();	
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Property property = entries.get(rowIndex);
		if(columnIndex == 0)
		{
			property.setName((String) value);
		}
		else
		{
			DataType type = (DataType) property.getType();
			type.setName((String) value);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return headerList[columnIndex];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) 
	{ 
		//if(columnIndex >= 1)
			return true;
		//return false;
	}	
}
