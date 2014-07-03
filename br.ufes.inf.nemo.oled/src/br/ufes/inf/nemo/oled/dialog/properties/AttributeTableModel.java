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

import java.text.ParseException;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Property;
import RefOntoUML.impl.DataTypeImpl;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This class implements a BaseTableModel for class RefOntoUML.Proprties
 * 
 * @author Antognoni Albuquerque, John Guerson
 */
public class AttributeTableModel extends BaseTableModel {
	
	private static final long serialVersionUID = 156864519388945910L;
	private EList<Property> attributes = new BasicEList<Property>();
	public static boolean isPrimitive = true;
	
	public AttributeTableModel(Classifier owner)
	{
		super(new String[]{"Name", "Type", "Multiplicity"});
		
		if(owner instanceof DataTypeImpl) attributes.addAll(((DataType) owner).getOwnedAttribute());
		else attributes.addAll(((RefOntoUML.Class) owner).getOwnedAttribute());
	}

	public EList<Property> getEntries()
	{
		return attributes;
	}
	
	public Property getEntry(int index)
	{
		return attributes.get(index);
	}
	
	public Property getEntry(Property property)
	{
		for(Property p: attributes)
		{
			if (p.equals(property))
				return p;
		}
		return null;
	}
	
	/**
	 * Adds an entry (item) to the model.
	 * @param entry the entry to add
	 */
	public void addEntry(Object entry)
	{
		int size = attributes.size();
		if(!attributes.contains((Property)entry)){
			attributes.add((Property) entry);			
			fireTableRowsInserted(size, size);
		}
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
		DataType type = null;		
		if (isPrimitive) type = ModelHelper.getFactory().createPrimitiveType();		
		else type = ModelHelper.getFactory().createDataType();
		type.setName("");
		property.setType(type);
		property.setName("");
		ModelHelper.setMultiplicity(property, 1, 1);		
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
				case 1: 
				{
					String type = new String(); 
					if(prp.getType()!=null) type = prp.getType().getName();
					return type;
				}
				case 2: {
					if (prp.getLower()==prp.getUpper() && prp.getUpper()!=-1) return Integer.toString(prp.getLower());
					else if (prp.getLower()==prp.getUpper() && prp.getUpper()==-1) return "*";
					else if (prp.getUpper()==-1) return prp.getLower()+".."+"*";
					else return prp.getLower()+".."+prp.getUpper();
				}
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
				case 2: return String.class;
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
		if(columnIndex == 0) {
			property.setName((String) value);
		} 
		if(columnIndex == 1){			 
			if(property.getType()!=null){
				DataType type = (DataType) property.getType();
				type.setName((String) value);
			}else{
				DataType type = null;	
				if (isPrimitive) type = ModelHelper.getFactory().createPrimitiveType();		
				else type = ModelHelper.getFactory().createDataType();
				property.setType(type);
				type.setName((String) value);
			}
		}
		if(columnIndex == 2){
			try {
				ModelHelper.setMultiplicityFromString(property, (String)value);
			} catch (ParseException e) {				
				e.printStackTrace();
			}
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
