package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.ufes.inf.nemo.ontouml.antipattern.RWORAntiPattern;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;

/**
 * @author John Guerson
 */

public class RoleJTable extends JTable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Classifier> rolesArrayList = new ArrayList<Classifier>();	
	private RWORAntiPattern rwor;
	
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
	 * Set RWORAntiPattern.
	 * 
	 * @param rwor
	 */
	public void setRWORAntiPattern(RWORAntiPattern rwor)
	{
		this.rwor = rwor;	
	}
	
	/**
	 * Get Roles ArrayList.
	 * 
	 * @return
	 */
	public ArrayList<Classifier> getRolesArrayList()
	{
		return rolesArrayList;
	}
	
	/**
	 * Create a Table from Roles.
	 * 
	 * @param roles
	 */
	public void setRolesTableModel (Collection<Classifier> roles)
	{		
		setModel(createTableModel(roles));	
		
		for(Classifier c: roles) rolesArrayList.add(c);		
	}
	
	/**
	 * Get mediations matrix from selected roles in the table.
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<Mediation>> getMediationsMatrixFromRolesTable()
	{
		ArrayList<ArrayList<Mediation>> matrix = new ArrayList<ArrayList<Mediation>>();
		
		for(int i =0; i< getModel().getRowCount(); i++)
		{
			ArrayList<Mediation> row = new ArrayList<Mediation>();
			row.add( rwor.getKeyByValue(rolesArrayList.get(i) ));
			
			for(int j=1; j< getModel().getColumnCount(); j++)
			{
				if( i!=j-1 && (Boolean)getModel().getValueAt(i, j)) row.add( rwor.getKeyByValue(rolesArrayList.get(j-1)) );
			}
			matrix.add(row);
		}
		
		return matrix;
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
