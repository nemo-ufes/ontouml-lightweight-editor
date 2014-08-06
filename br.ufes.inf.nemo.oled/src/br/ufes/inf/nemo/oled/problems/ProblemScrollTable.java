package br.ufes.inf.nemo.oled.problems;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.ufes.inf.nemo.oled.finder.FoundScrollTable;
import br.ufes.inf.nemo.oled.finder.FoundTableModel;

public class ProblemScrollTable extends FoundScrollTable {

	private static final long serialVersionUID = -7920882969650069279L;
	protected ArrayList<ProblemElement> problemList = new ArrayList<ProblemElement>();
	
	protected ProblemScrollTable(String[] columns) 
	{
		super(columns);
	}
	 
	public ArrayList<ProblemElement>  getProblems() {
		return problemList;
	};
	
	public void setProblems(ArrayList<ProblemElement> problemList)
	{
		this.foundList.clear();
		this.foundList.addAll(problemList);
		this.problemList = problemList;				
		int rows=this.problemList.size();
				
		String[][] data = new String[rows][columnNames.length];
		
		int i=0;		
		for(ProblemElement elem: this.problemList){
			data[i][0]=" "+elem.getTypeProblemString();
			data[i][1]="    "+elem.getIdentifierString()+". "+elem.getDescription();
			data[i][2]=" "+elem.getType();
			data[i][3]=" "+elem.getName();							
			data[i][4]=" "+elem.getPath();			
			i++;
		}
		
		tablemodel = new FoundTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		
		for(int j=0;j<table.getRowCount();j++){
			table.setRowHeight(j, 18);	
	    }
		
		TableColumn column = null;
		for (int j = 0; j < columnNames.length; j++) {
	        column = table.getColumnModel().getColumn(j);	        
	        if (j == 1) column.setPreferredWidth(370);
		}   
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		table.repaint();
		table.validate();		
		repaint();
		validate();
	}	
}
