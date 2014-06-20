package br.ufes.inf.nemo.validator.meronymic.checkers.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufes.inf.nemo.validator.meronymic.checkers.MeronymicError;

public class CheckerTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 3207003781085511173L;
	
	private ArrayList<MeronymicError<?>> errors = new ArrayList<MeronymicError<?>>();
	private String[] columns={"#","Error Type","Description", "Has Fix?"};

	public CheckerTableModel() {}

	public CheckerTableModel(List<MeronymicError<?>> existingDataList) {
		errors.addAll(existingDataList);
	}  
	
	@Override
	public int getRowCount() {
		return errors.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public String getColumnName(int col) {
		return columns[col];
	}
	
//	@Override
//	public Class<?> getColumnClass(int columnIndex) {
//	    return super.getColumnClass(columnIndex);
//	}
	
	public void addRow(MeronymicError<?> derived) {
		errors.add(derived);
		fireTableDataChanged();
	}
	
	public MeronymicError<?> getRow(int i) {
		return errors.get(i);
	}
	
	public ArrayList<MeronymicError<?>> getAllRows() {
		return errors;
	}
	
	public void addRows(List<MeronymicError<?>> list) {
		errors.addAll(list);
		fireTableDataChanged();
	}
	
	public MeronymicError<?> removeRow(int row) {
		MeronymicError<?> removed = errors.remove(row);
		   fireTableDataChanged();
		   return removed;
	}
	
	public void clear() {
		errors.clear();
		fireTableDataChanged();
	}
	
//	@Override
//	public void setValueAt(Object value, int row, int col) {
//		MeronymicError error = errors.get(row);
//        
//		switch (col) {
//		case 6:
//			break;
//
//		default:
//			break;
//		}
//		
//		fireTableCellUpdated(row, col);
//    }
	
	@Override
	public Object getValueAt(int row, int col) {
		MeronymicError<?> error = errors.get(row);
		   switch (col) {
		    case 0:
		     return row+1;
		    case 1:
		     return error.getType();
		    case 2:
		     return error.getDescription();
		    case 3:
			 if(error.hasAction())
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
	

}
