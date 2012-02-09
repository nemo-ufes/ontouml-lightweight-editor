package br.ufes.inf.nemo.oled.ui.diagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import refontouml2alloy.bts.simulation.SimulationAttribute;
import refontouml2alloy.bts.simulation.SimulationElement;
import RefOntoUML.impl.AssociationImpl;

/**
 * This class implements a TableModel for class Simulation Elements
 *
 * @author Antognoni Albuquerque
 * @version 1.0
 */
public class VerificationModel extends AbstractTableModel implements Serializable {
	
	private static final long serialVersionUID = 156864519388945910L;
	private List<SimulationElement> entries = new ArrayList<SimulationElement>();
	private String headerList[] = new String[]{"Simulate", "Element", "Color", "Style", "Shape"};
	
	public VerificationModel(List<SimulationElement> simulaionElements)
	{
		super();
		this.entries = simulaionElements;
	}
	
	/**
	 * Adds an entry.
	 * @param entry the entry to add
	 */
	public void addEntry(SimulationElement entry) {
		int size = entries.size();
		entries.add(entry);
		fireTableRowsInserted(size, size);
	}
	
	/**
	 * Returns the entries.
	 * @return the entries
	 */
	public List<SimulationElement> getEntries() {
		return entries;
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
	public int getColumnCount() { return 5; }

	/**
	 * {@inheritDoc}
	 */
	public Class<?> getColumnClass(int columnIndex) {
        //return getValueAt(0, c).getClass();
        
        if(entries.size() > 0)
		{
			if(columnIndex == 0)
				return Boolean.class;
			else if(columnIndex == 1)
				return String.class;
			else if(columnIndex == 2)
				return SimulationAttribute.class;
			else if(columnIndex == 3)
				return SimulationAttribute.class;
			else if(columnIndex == 4)
				return SimulationAttribute.class;
		}
		return null;
    }
	
	/**
	 * {@inheritDoc}
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(entries.size() > 0)
		{
			if(columnIndex == 0)
				return entries.get(rowIndex).isSimulate();
			else if(columnIndex == 1)
				return entries.get(rowIndex).getElement().getName();
			else if(columnIndex == 2)
				return entries.get(rowIndex).getColor();
			else if(columnIndex == 3)
				return entries.get(rowIndex).getStyle();
			else if(columnIndex == 4)
				return entries.get(rowIndex).getShape();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		
		if(columnIndex == 0)
			entries.get(rowIndex).setSimulate((Boolean) value);
		else if(columnIndex == 1)
			entries.get(rowIndex).getElement().setName((String) value);
		else if(columnIndex == 2)
			entries.get(rowIndex).setColor((SimulationAttribute) value);
		else if(columnIndex == 3)
			entries.get(rowIndex).setStyle((SimulationAttribute) value);
		else if(columnIndex == 4)
			entries.get(rowIndex).setShape((SimulationAttribute) value);
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
		if(columnIndex == 4 && entries.get(rowIndex).getElement() instanceof AssociationImpl)
			return false;
		return true;
	}	
	
}
