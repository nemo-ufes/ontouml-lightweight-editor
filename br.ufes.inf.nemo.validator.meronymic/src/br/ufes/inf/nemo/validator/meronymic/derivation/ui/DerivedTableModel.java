package br.ufes.inf.nemo.validator.meronymic.derivation.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.validator.meronymic.derivation.DerivedMeronymic;

public class DerivedTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 3207003781085511173L;
	
	private ArrayList<DerivedMeronymic> derivedMeronymic = new ArrayList<DerivedMeronymic>();
	private String[] columns={"Whole","Part","Stereotype","Pattern","Path","Exists?","Has Fix?"};

	public DerivedTableModel() {
		   
	}

	public DerivedTableModel(List<DerivedMeronymic> existingDataList) {
		derivedMeronymic.addAll(existingDataList);
	}  
	
	@Override
	public int getRowCount() {
		return derivedMeronymic.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public String getColumnName(int col) {
		return columns[col];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
	    if (columnIndex == 5 || columnIndex == 6)
	        return Boolean.class;
	    return super.getColumnClass(columnIndex);
	}
	
	public void addRow(DerivedMeronymic derived) {
		derivedMeronymic.add(derived);
		fireTableDataChanged();
	}
	
	public DerivedMeronymic getRow(int i) {
		return derivedMeronymic.get(i);
	}
	
	public void addRows(List<DerivedMeronymic> list) {
		derivedMeronymic.addAll(list);
		fireTableDataChanged();
	}
	
	public DerivedMeronymic removeRow(int row) {
		   DerivedMeronymic removed = derivedMeronymic.remove(row);
		   fireTableDataChanged();
		   return removed;
	}
	
	public void clear() {
		derivedMeronymic.clear();
		fireTableDataChanged();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		DerivedMeronymic derived = derivedMeronymic.get(row);
		   switch (col) {
		    case 0:
		     return OntoUMLNameHelper.getTypeAndName(derived.getWhole(), true, false);
		    case 1:
		     return OntoUMLNameHelper.getTypeAndName(derived.getPart(), true, false);
		    case 2:
		     return derived.getStereotype().toString();
		    case 3:
		    	return derived.getPatternString();
		    case 4:
		    	return derived.getPathString();
		    case 5:
		    	return derived.existsMeronymic();
		    case 6:
		     return derived.hasAction();
		    default:
		     return null;
		   }
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public ArrayList<DerivedMeronymic> getAllRows() {
		return derivedMeronymic;
	}
	

}
