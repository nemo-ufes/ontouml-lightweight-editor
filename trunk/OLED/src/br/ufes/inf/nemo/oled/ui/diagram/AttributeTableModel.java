package br.ufes.inf.nemo.oled.ui.diagram;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Property;
import RefOntoUML.impl.DataTypeImpl;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This class implements a BaseTableModel for class RefOntoUML.Proprties
 * @author Antognoni Albuquerque
 * @version 1.0
 */
public class AttributeTableModel extends BaseTableModel {
	
	private static final long serialVersionUID = 156864519388945910L;
	//private Classifier owner;
	private EList<Property> attributes;
	
	public AttributeTableModel(Classifier owner)
	{
		super(new String[]{"Name", "Type"});
		//this.owner = owner;
		
		if(owner instanceof DataTypeImpl)
			attributes = ((DataType) owner).getOwnedAttribute();
		else
			attributes = ((RefOntoUML.Class) owner).getOwnedAttribute();
	}

	//@SuppressWarnings("unchecked")
	public EList<Property> getEntries()
	{
		return attributes;
	}
	
	/**
	 * Adds an entry (item) to the model.
	 * @param entry the entry to add
	 */
	public void addEntry(Object entry)
	{
		int size = attributes.size();
		attributes.add((Property) entry);
		fireTableRowsInserted(size, size);
	}

	@Override
	public void moveUpEntry(int index) {
		attributes.move(index-1, index);
		fireTableRowsUpdated(index-1, index);
	}

	@Override
	public void moveDownEntry(int index) {
		attributes.move(index+1, index);
		fireTableRowsUpdated(index+1, index);
	}

	@Override
	public void removeEntryAt(int index) {
		attributes.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addEmptyEntry() {
		Property property = ModelHelper.getFactory().createProperty();
		DataType type = ModelHelper.getFactory().createDataType();
		type.setName("");
		property.setType(type);
		property.setName("");
		
		addEntry(property);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(attributes.size() > 0)
		{
			Property prp = (Property)attributes.get(rowIndex);
			
			switch(columnIndex) {
				case 0: return prp.getName();
				case 1: return prp.getType().getName();
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<?> getColumnClass(int columnIndex) {
        if(attributes.size() > 0)
		{
        	switch(columnIndex) {
				case 0: return String.class;
				case 1: return String.class;
			}
		}
		return Object.class;
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Property property = (Property) attributes.get(rowIndex);
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
	public boolean isCellEditable(int rowIndex, int columnIndex) 
	{ 
		return true;
	}

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
	public int getRowCount() { return attributes.size(); }

	/**
	 * {@inheritDoc}
	 */
	public int getColumnCount() { return columns.length; }

}
