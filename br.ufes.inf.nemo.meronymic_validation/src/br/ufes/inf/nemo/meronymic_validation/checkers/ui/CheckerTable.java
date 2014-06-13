package br.ufes.inf.nemo.meronymic_validation.checkers.ui;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;


public class CheckerTable extends JTable{

	private static final long serialVersionUID = -1555634333563837186L;

	private CheckerTableModel tableModel;
	
	public CheckerTable(){
		tableModel = new CheckerTableModel();
		super.setModel(tableModel);
		setFillsViewportHeight(true);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		
		getColumnModel().getColumn(0).setPreferredWidth(30);
		getColumnModel().getColumn(1).setPreferredWidth(200);
		getColumnModel().getColumn(2).setPreferredWidth(400);
		
		getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	@Override
	public CheckerTableModel getModel(){
		return tableModel;
	}
	
	
	
}
