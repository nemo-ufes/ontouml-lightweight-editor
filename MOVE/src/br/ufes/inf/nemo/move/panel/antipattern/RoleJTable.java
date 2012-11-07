package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.Color;
import java.util.Collection;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import RefOntoUML.Classifier;

/**
 * @author John Guerson
 */

public class RoleJTable extends JTable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a Table.
	 */
	public RoleJTable()
	{
		setBackground(Color.WHITE);
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(false);
		setShowGrid(false);
		setBackground(Color.WHITE);	
		setFillsViewportHeight(true);
	}	

	/**
	 * Create a Table from Roles.
	 * 
	 * @param roles
	 */
	public void setRolesTableModel (Collection<Classifier> roles)
	{		
		setModel(createTableModel(roles));		
	}
	
	/**
	 * Create Table Model from Classifiers i.e. Roles.
	 * 
	 * @param roles
	 * @return
	 */
	public TableModel createTableModel(Collection<Classifier> roles)
	{
		String[] columns = new String[roles.size()+1];
		for(int i = 0; i< roles.size()+1; i++)
		{
			if (i==0) columns[i] = "";
			else columns[i] = ((Classifier)roles.toArray()[i-1]).getName();
		}
		
		Object[][] data = new Object[roles.size()][roles.size()+1];
		for (int i = 0; i < roles.size(); i++) 
		{
			for(int j = 0; j< roles.size()+1 ;j++)
			{
				if(j==0) data[i][j] = columns[i+1];
				else data[i][j] = Boolean.FALSE;
			}
		}
		
		TableModel dtm = new DefaultTableModel(data,columns) 
		{
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int index) 
			{	
				if (index==0) return String.class;
				else return Boolean.class;
	        }
		};
		return dtm;
	}
	
}
