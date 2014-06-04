package br.ufes.inf.nemo.meronymic_validation.forbidden.ui;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import br.ufes.inf.nemo.meronymic_validation.ui.JTableButtonMouseListener;
import br.ufes.inf.nemo.meronymic_validation.ui.JTableButtonRenderer;

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
		getColumnModel().getColumn(3).setPreferredWidth(100);
		getColumnModel().getColumn(4).setPreferredWidth(100);
		getColumnModel().getColumn(5).setPreferredWidth(100);
		getColumnModel().getColumn(6).setPreferredWidth(50);
		
		getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
	    this.getColumn("Fix").setCellRenderer(buttonRenderer);
		
		getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		addMouseListener(new JTableButtonMouseListener(this));
	}
	
	@Override
	public ForbiddenTableModel getModel(){
		return tableModel;
	}	
	
}
