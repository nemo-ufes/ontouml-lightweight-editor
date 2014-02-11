package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.util.ColorPalette;
import br.ufes.inf.nemo.oled.util.ColorPalette.ThemeColor;
import br.ufes.inf.nemo.oled.util.FileChoosersUtil;

/**
 * @author John Guerson
 */

public class WarningTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private UmlProject project;
	
	private JScrollPane scrollpane;
	private JTable table;
	private WarningTableModel tablemodel;
	private JLabel warningMessage;
	private JButton saveButton;
	private String content;
	
	public WarningTablePanel(UmlProject project)
	{
		this();
		this.project = project;
	}
	
	public void selectRow (int row)
	{
		table.setRowSelectionInterval(row, row);
	}
	
	public void setProject(UmlProject project)
	{
		this.project = project;
	}
	
	public void reset()
	{
		Object[][] data = {};String[] columnNames = {};
		tablemodel = new WarningTableModel(columnNames,data);
		table.setModel(tablemodel);	
		table.repaint();
		table.validate();		
	}
	
	/**
	 * Set data
	 * 
	 * @param elem
	 */
	public void setData(ArrayList<ArrayList<String>> matrix, int warnings)
	{
		int rows=matrix.size();
		
		warningMessage.setText("    ("+warnings+" warnings)");
		warningMessage.repaint();
		warningMessage.validate();
		
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
		
		for(int j=0;j<table.getRowCount();j++){
			table.setRowHeight(j, 18);	
	    }
		
		table.repaint();
		table.validate();
		
		content = tablemodel.getData();
		
	}	
	
	public String getContent()
	{
		return content;
	}
	
	/**
	 * Constructor.
	 */
	public WarningTablePanel() 
	{
		setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(0, 0, 0, 0));
				
		setLayout(new BorderLayout(0, 0));		

		String[] columnNames = {"Description","Element","Path"};
        Object[][] data = {};
        
        scrollpane = new JScrollPane();		
	    scrollpane.setMinimumSize(new Dimension(0, 0));
	    setMinimumSize(new Dimension(0, 0));
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setBorder(new EmptyBorder(0,0,0,0));
		
		table = new JTable(data,columnNames);
		scrollpane.setViewportView(table);
		
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		//table.setPreferredScrollableViewportSize(new Dimension(500, 150));		
		table.setFillsViewportHeight(true);
		table.setGridColor(Color.LIGHT_GRAY);		
	    table.setSelectionBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM));
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
				//saveContentToTxtFile();
			}
		});
		saveButton.setFocusable(false);
		//saveButton.setIcon(new ImageIcon(WarningTablePanel.class.getResource("/resources/icon/export-16x16.png")));
		//toolBar.add(saveButton);
		
		warningMessage = new JLabel("    (0 warnings)");
		warningMessage.setPreferredSize(new Dimension(100, 25));
		//toolBar.add(warningMessage);		
	}
	
	public void saveContentToTxtFile (Component parent)
    {    	
    	String path = FileChoosersUtil.saveTxtLocation(parent,null);
    	
    	if(path!=null)
		try {
			
			FileUtil.copyStringToFile(
				warningMessage.getText().trim()+"\n"+
				"------------------------------------------------------------"+"\n"+
				content, 
				path
			);
		
		} catch (IOException e) {			
			e.printStackTrace();
		}    	
    }	
}
