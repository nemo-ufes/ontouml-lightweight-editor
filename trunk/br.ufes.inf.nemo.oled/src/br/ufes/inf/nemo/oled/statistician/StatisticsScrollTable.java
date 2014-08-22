/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.statistician;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.oled.palette.ColorPalette;
import br.ufes.inf.nemo.oled.palette.ColorPalette.ThemeColor;

/**
 * @author John Guerson
 */
public class StatisticsScrollTable extends JScrollPane{

	private static final long serialVersionUID = 1732036629191359696L;
	private JTable table;
	private StatisticsTableModel tablemodel;
	private ArrayList<StatisticalElement> resultList = new ArrayList<StatisticalElement>();
	
	public JTable getTable() { return table; }
	public ArrayList<StatisticalElement> getResult() { return resultList; }
	
	public StatisticsScrollTable()
	{				
		String[] columnNames = {"Measure", "Count", "Type Percentage", "Total Percentage"};
        Object[][] data = {};
        
	    setMinimumSize(new Dimension(0, 0));
	    setMinimumSize(new Dimension(0, 0));
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setBorder(new EmptyBorder(0,0,0,0));
		
		table = new JTable(data,columnNames);
		setViewportView(table);
		
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setFillsViewportHeight(true);
		table.setGridColor(Color.LIGHT_GRAY);		
	    table.setSelectionBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM));
	    table.setSelectionForeground(Color.BLACK);
	    table.setFocusable(false);	
	    
	    table.addMouseListener(new MouseAdapter()
	    {
	    	public void mousePressed(MouseEvent e)
	    	{
	    		// Left mouse click
	    		if ( SwingUtilities.isLeftMouseButton(e))
	    		{
	    			// nothing
	    		}
	    		// Right mouse click
	    		else if (SwingUtilities.isRightMouseButton(e))
	    		{
	    			// get the coordinates of the mouse click
	    			Point p = e.getPoint();	     
	    			// get the row index that contains that coordinate
	    			int rowNumber = table.rowAtPoint( p );	     
	    			// Get the ListSelectionModel of the JTable
	    			ListSelectionModel model = table.getSelectionModel();	     
	    			// set the selected interval of rows. Using the "rowNumber"
	    			// variable for the beginning and end selects only that one row.
	    			model.setSelectionInterval( rowNumber, rowNumber );
	    		}
	    	}
	    });
	}
	
	public void reset()
	{
		Object[][] data = {};String[] columnNames = {};
		tablemodel = new StatisticsTableModel(columnNames,data);
		table.setModel(tablemodel);	
		table.repaint();
		table.validate();		
	}
	
	public void selectRow (int row)
	{
		table.setRowSelectionInterval(row, row);
	}
	
	public void setData(ArrayList<StatisticalElement> foundList)
	{
		this.resultList = foundList;
		int rows=foundList.size();
				
		String[][] data = new String[rows][4];
		
		int i=0;		
		for(StatisticalElement elem: this.resultList){						
			data[i][0]="    "+elem.getMeasure();
			data[i][1]=" "+elem.getCount();							
			data[i][2]=" "+elem.getTypePercentage();
			data[i][3]=" "+elem.getAllPercentage();
			i++;
		}
		
		String[] columnNames = {"Measure", "Count", "Type Percentage", "Total Percentage"};
		tablemodel = new StatisticsTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		
		for(int j=0;j<table.getRowCount();j++){
			table.setRowHeight(j, 18);	
	    }
		
		table.repaint();
		table.validate();		
		repaint();
		validate();
	}
	
	public String getTableText(){
		String s = new String();
		int rows = table.getModel().getRowCount();
		
		for (int i = 0; i < rows; i++) {
			s +=table.getValueAt(i, 0).toString().replaceAll("    ", "")+"\t"+table.getValueAt(i, 1)+"\t"+
				table.getValueAt(i, 2)+"\t"+table.getValueAt(i, 3);
			if(i<rows-1)
				s+="\n";
		}
		
		return s;
	}

}
