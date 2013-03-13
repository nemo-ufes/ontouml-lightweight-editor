package br.ufes.inf.nemo.move.mvc.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.ufes.inf.nemo.move.mvc.model.RWORAntiPatternModel;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;


/**
 * @author John Guerson
 */

public class RoleTableView extends JTable {

	private static final long serialVersionUID = 1L;
	
	private RWORAntiPatternModel rworModel;
	
	private ArrayList<Classifier> rolesArrayList = new ArrayList<Classifier>();	
	
	/**
	 * Constructor.
	 */
	public RoleTableView()
	{
		setBackground(Color.WHITE);
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(false);
		setShowGrid(false);
		setBackground(Color.WHITE);	
		setFillsViewportHeight(true);
	}	

	public void addEmptyRow() 
	{
		Boolean[] row = new Boolean[rolesArrayList.size()];
		for(int i=0; i<rolesArrayList.size();i++){ row[i] = Boolean.FALSE; }
		((DefaultTableModel)getModel()).addRow(row);
	}
	
	/** 
	 * Set RWORAntiPatternModel.
	 * 
	 * @param rwor
	 */
	public void setRWORAntiPatternModel(RWORAntiPatternModel rworModel)
	{
		this.rworModel = rworModel;	
		
		setRolesTableModel(rworModel.getRWORAntiPattern().getMediations().values());
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
	private void setRolesTableModel (Collection<Classifier> roles)
	{		
		setModel(createTableModel(roles));	
		
		for(Classifier c: roles) rolesArrayList.add(c);		
	}
	
	/**
	 * Get Roles matrix from selected roles in the table.
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<Classifier>> getRolesMatrix()
	{
		ArrayList<ArrayList<Classifier>> matrix = new ArrayList<ArrayList<Classifier>>();
		
		for(int i =0; i< getModel().getRowCount(); i++)
		{
			ArrayList<Classifier> row = new ArrayList<Classifier>();			
			
			for(int j=0; j< getModel().getColumnCount(); j++)
			{
				if ((Boolean)getModel().getValueAt(i, j))
					row.add(rolesArrayList.get(j));
			}
			if(row.size()>0) matrix.add(row);
		}
		
		return matrix;
	}
	
	/**
	 * Get mediations matrix from selected roles in the table.
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<Mediation>> getMediationsMatrix()
	{
		ArrayList<ArrayList<Mediation>> matrix = new ArrayList<ArrayList<Mediation>>();
		
		for(int i =0; i< getModel().getRowCount(); i++)
		{
			ArrayList<Mediation> row = new ArrayList<Mediation>();			
			
			for(int j=0; j< getModel().getColumnCount(); j++)
			{
				if ((Boolean)getModel().getValueAt(i, j))
					row.add(rworModel.getRWORAntiPattern().getKeyByValue(rolesArrayList.get(j)));
			}
			if(row.size()>0) matrix.add(row);
		}
		
		return matrix;
	}
	
	/**
	 * Create Table Model from Classifiers i.e. Roles.
	 * 
	 * @param roles
	 * @return
	 */
	private TableModel createTableModel(Collection<Classifier> roles)
	{
		String[] columns = new String[roles.size()];
		for(int i = 0; i< roles.size(); i++)
		{			
			columns[i] = ((Classifier)roles.toArray()[i]).getName();
		}
		
		TableModel dtm = new DefaultTableModel(null,columns) 
		{
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int index) 
			{	
				return Boolean.class;
	        }
		};
		return dtm;
	}
	
}
