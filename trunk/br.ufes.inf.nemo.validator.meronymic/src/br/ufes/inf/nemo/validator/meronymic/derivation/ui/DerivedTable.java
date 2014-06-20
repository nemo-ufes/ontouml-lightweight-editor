package br.ufes.inf.nemo.validator.meronymic.derivation.ui;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class DerivedTable extends JTable{

	private static final long serialVersionUID = -1555634333563837186L;

	private DerivedTableModel tableModel;
	
	public DerivedTable(){
		tableModel = new DerivedTableModel();
		super.setModel(tableModel);
		setFillsViewportHeight(true);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		
		getColumnModel().getColumn(4).setPreferredWidth(200);
		getColumnModel().getColumn(5).setPreferredWidth(50);
		getColumnModel().getColumn(6).setPreferredWidth(50);
		
		getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	@Override
	public DerivedTableModel getModel(){
		return tableModel;
	}
	
	
	
}
