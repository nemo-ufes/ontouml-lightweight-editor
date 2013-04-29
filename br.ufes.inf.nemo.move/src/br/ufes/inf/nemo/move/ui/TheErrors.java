package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.ui.util.ColorPalette;
import br.ufes.inf.nemo.move.ui.util.ColorPalette.ThemeColor;

/**
 * @author John Guerson
 */

public class TheErrors extends JPanel {

	private static final long serialVersionUID = 1L;

	private TheFrame frame;
	
	private JScrollPane scrollpane;
	private JTable table;
	private WarningTableModel tablemodel;
	private JLabel errorMessage;
	private JButton saveButton;
	private String content;
	
	public TheErrors(TheFrame frame)
	{
		this();
		this.frame = frame;
	}
	
	/**
	 * Set data
	 * 
	 * @param elem
	 */
	public void setData(ArrayList<ArrayList<String>> matrix, int errors)
	{
		int rows=matrix.size();
		
		errorMessage.setText("    ("+errors+" errors)");
		errorMessage.repaint();
		errorMessage.validate();
		
		String[][] data = new String[rows][3];
		
		int i=0;		
		for(ArrayList<String> row: matrix){
			int j=0;
			for(String str: row){
				if (j==0) data[i][j]="    "+str;
				else data[i][j]=" "+str;
				j++;
			}
			i++;
		}
		
		String[] columnNames = {"Description","Element","Path"};
		tablemodel = new WarningTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		table.repaint();
		table.validate();
		
		content = tablemodel.getData();
		
		repaint();
		validate();
	}	
	
	public String getContent()
	{
		return content;
	}
	
	/**
	 * Constructor.
	 */
	public TheErrors() 
	{
		setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(0, 0, 0, 0));
				
		setLayout(new BorderLayout(0, 0));		

		String[] columnNames = {"Description","Element","Path"};
        Object[][] data = {};
        
        scrollpane = new JScrollPane();		
	    scrollpane.setPreferredSize(new Dimension(100,70));
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table = new JTable(data,columnNames);
		scrollpane.setViewportView(table);
		
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setPreferredScrollableViewportSize(new Dimension(500, 150));		
		table.setFillsViewportHeight(true);
		table.setGridColor(Color.LIGHT_GRAY);		
	    table.setSelectionBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHT));
	    table.setSelectionForeground(Color.BLACK);
	    table.setFocusable(false);
	    					
		add(scrollpane,BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.NORTH);
				
		saveButton = new JButton("");
		saveButton.setToolTipText("export to a txt file");
		saveButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				saveContentToTxtFile();
			}
		});
		saveButton.setFocusable(false);
		saveButton.setIcon(new ImageIcon(TheErrors.class.getResource("/resources/icon/export-16x16.png")));
		toolBar.add(saveButton);
		
		errorMessage = new JLabel("    (0 errors)");
		errorMessage.setPreferredSize(new Dimension(100, 25));
		toolBar.add(errorMessage);		
	}
		
	/**
	 * Save Txt Location.
	 */
	public String saveTxtLocation()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Export");
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Document (*.txt)", "txt");
		fileChooser.addChoosableFileFilter(txtFilter);
		fileChooser.setFileFilter(txtFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == txtFilter) 
			{
				String path = fileChooser.getSelectedFile().getPath();
				if (path.contains(".txt"))
					return fileChooser.getSelectedFile().getPath();
				else
					return fileChooser.getSelectedFile().getPath()+".txt";				
			}
		}
		return null;
	}
	
	public void saveContentToTxtFile ()
    {    	
    	String path = saveTxtLocation();
    	
    	if(path!=null)
		try {
			
			FileUtil.copyStringToFile(
				frame.getCurrentDateAndTime()+" - "+ errorMessage.getText().trim()+"\n"+
				"------------------------------------------------------------"+"\n"+
				content, 
				path
			);
		
		} catch (IOException e) {
			frame.showErrorMessageDialog("IO", e.getMessage());
			e.printStackTrace();
		}    	
    }
	
	/**
	 * 
	 * My own TableModel...
	 * 
	 * @author John
	 *
	 */
	class WarningTableModel extends AbstractTableModel 
	{
		private static final long serialVersionUID = 1L;
		
		public WarningTableModel(String[] columnNames, Object[][] data)
		{
			this.columnNames=columnNames;
			this.data=data;
		}
		
		private String[] columnNames = {"Description","Element","Path"};
        private Object[][] data = {};
 
        public int getColumnCount() {
            return columnNames.length;
        }
 
        public int getRowCount() {
            return data.length;
        }
 
        public String getColumnName(int col) {
            return columnNames[col];
        }
 
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
 
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
 
        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }
 
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) 
        { 
            data[row][col] = value;
            fireTableCellUpdated(row, col); 
        }
 

		private String getData() 
        {
            int numRows = getRowCount();
            int numCols = getColumnCount();
            String result = new String();            
            
            for (int i=0; i < numRows; i++) 
            {            	
                for (int j=0; j < numCols; j++) {                	
                	if(j>0){                		
                		String elem = (String)data[i][j];
                		result += "  " + elem.trim()+"\n";                		
                	}else if (j==0){
                		String title = (String)data[i][j];                		
                		if(!title.trim().isEmpty()){
                			result += "  "+"\n"+title+"\n\n";
                		}else{
                			result += "\n";
                		}
                	}
                }                
            }
            return result;
        }
    }
}
