package br.ufes.inf.nemo.oled.finder;

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

public class FinderScrollTable extends JScrollPane{

	private static final long serialVersionUID = 1732036629191359696L;
	private JTable table;
	private FinderTableModel tablemodel;
	private ArrayList<ElementFound> foundList = new ArrayList<ElementFound>();
	
	public JTable getTable() { return table; }
	public ArrayList<ElementFound> getResult() { return foundList; }
	
	public FinderScrollTable()
	{				
		String[] columnNames = {"Name","Stereotype","Location"};
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
	    			
	    			FinderPopupMenu menu = new FinderPopupMenu(foundList.get(rowNumber).getElement());
	    			menu.show(e.getComponent(),e.getX(),e.getY());
	    		}
	    	}
	    });
	}
	
	public void reset()
	{
		Object[][] data = {};String[] columnNames = {};
		tablemodel = new FinderTableModel(columnNames,data);
		table.setModel(tablemodel);	
		table.repaint();
		table.validate();		
	}
	
	public void selectRow (int row)
	{
		table.setRowSelectionInterval(row, row);
	}
	
	public void setData(ArrayList<ElementFound> foundList)
	{
		this.foundList = foundList;
		int rows=foundList.size();
				
		String[][] data = new String[rows][3];
		
		int i=0;		
		for(ElementFound elem: this.foundList){						
			data[i][0]="    "+elem.getName();
			data[i][1]=" "+elem.getType();
			data[i][2]=" "+elem.getPath();				
			i++;
		}
		
		String[] columnNames = {"Name","Stereotype","Location"};
		tablemodel = new FinderTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		
		for(int j=0;j<table.getRowCount();j++){
			table.setRowHeight(j, 18);	
	    }
		
		table.repaint();
		table.validate();		
		repaint();
		validate();
	}	

}
