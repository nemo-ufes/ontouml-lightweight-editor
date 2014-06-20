package br.ufes.inf.nemo.validator.meronymic.forbidden.ui;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class ForbiddenTable extends JTable{

	private static final long serialVersionUID = -1555634333563837186L;

	private ForbiddenTableModel tableModel;
	
	public ForbiddenTable(){
		tableModel = new ForbiddenTableModel();
		super.setModel(tableModel);
		setFillsViewportHeight(true);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		getColumnModel().getColumn(0).setPreferredWidth(100);
		getColumnModel().getColumn(1).setPreferredWidth(100);
		getColumnModel().getColumn(2).setPreferredWidth(100);
		getColumnModel().getColumn(3).setPreferredWidth(50);
		getColumnModel().getColumn(4).setPreferredWidth(200);
		getColumnModel().getColumn(5).setPreferredWidth(20);
		
		getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		
		getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	@Override
	public ForbiddenTableModel getModel(){
		return tableModel;
	}	
	
}
