package br.ufes.inf.nemo.validator.meronymic.forbidden.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import RefOntoUML.Meronymic;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.validator.meronymic.forbidden.ForbiddenMeronymic;

public class ForbiddenTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -1928067858298060225L;
	
	private ArrayList<ForbiddenMeronymic<? extends Meronymic>> forbiddenMeronymic = new ArrayList<>();
	private static final String[] 	COLUMNS = {"Whole","Part","Name","Stereotype","Description","Has Fix?",};
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {String.class, String.class, String.class, String.class, String.class, String.class};

	public ForbiddenTableModel() { 
	}

	public ForbiddenTableModel(List<ForbiddenMeronymic<?>> existingDataList) {
		forbiddenMeronymic.addAll(existingDataList);
	}  
	
	@Override
	public int getRowCount() {
		return forbiddenMeronymic.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}
	@Override
	public String getColumnName(int col) {
		return COLUMNS[col];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
	   return COLUMN_TYPES[columnIndex];
	}
	
	public void addRow(ForbiddenMeronymic<? extends Meronymic> forbidden) {
		forbiddenMeronymic.add(forbidden);
		fireTableDataChanged();
	}
	
	public ForbiddenMeronymic<?> getRow(int i) {
		return forbiddenMeronymic.get(i);
	}
	
	public void addRows(List<ForbiddenMeronymic<? extends Meronymic>> list) {
		forbiddenMeronymic.addAll(list);
		fireTableDataChanged();
	}
	
	public ForbiddenMeronymic<?> removeRow(int row) {
		   ForbiddenMeronymic<?> removed = forbiddenMeronymic.remove(row);
		   fireTableDataChanged();
		   return removed;
	}
	
	public void clear() {
		forbiddenMeronymic.clear();
		fireTableDataChanged();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		ForbiddenMeronymic<?> forbidden = forbiddenMeronymic.get(row);
		   switch (col) {
		    case 0:
		    	return OntoUMLNameHelper.getName(forbidden.getWhole(), false, false);
		    case 1:
		    	return OntoUMLNameHelper.getName(forbidden.getPart(), false, false);
		    case 2:
		    	return OntoUMLNameHelper.getName(forbidden.getMeronymic(), false, false);
		    case 3:
		    	return OntoUMLNameHelper.getTypeName(forbidden.getMeronymic(), false);
		    case 4:
		    	return forbidden.getDescription();
		    case 5:
		    	if(forbidden.hasAction()) 
		    		return "Yes";
		    	return "No";
		    default:
		    	return null;
		   }
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public ArrayList<ForbiddenMeronymic<? extends Meronymic>> getAllRows() {
		return forbiddenMeronymic;
		
	}
	

	
	
}
