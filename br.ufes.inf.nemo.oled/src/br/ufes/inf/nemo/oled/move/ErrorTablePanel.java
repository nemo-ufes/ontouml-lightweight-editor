package br.ufes.inf.nemo.oled.move;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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

/**
 * @author John Guerson
 */

public class ErrorTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private UmlProject project;
	
	private JScrollPane scrollpane;
	private JTable table;
	private WarningTableModel tablemodel;
	private JLabel errorMessage;
	private JButton saveButton;
	private String content;
	
	public ErrorTablePanel(UmlProject project)
	{
		this();
		this.project = project;
	}
	
	public void selectRow (int row)
	{
		table.setRowSelectionInterval(row, row);
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
	public ErrorTablePanel() 
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
				//saveContentToTxtFile();
			}
		});
		saveButton.setFocusable(false);
		saveButton.setIcon(new ImageIcon(ErrorTablePanel.class.getResource("/resources/icon/export-16x16.png")));
		toolBar.add(saveButton);
		
		errorMessage = new JLabel("    (0 errors)");
		errorMessage.setPreferredSize(new Dimension(100, 25));
		//toolBar.add(errorMessage);		
	}
	
	public void saveContentToTxtFile (Component parent)
    {    	
    	String path = FileChoosersUtil.saveTxtLocation(parent, null);
    	
    	if(path!=null)
		try {
			
			FileUtil.copyStringToFile(
				errorMessage.getText().trim()+"\n"+
				"------------------------------------------------------------"+"\n"+
				content, 
				path
			);
		
		} catch (IOException e) {			
			e.printStackTrace();
		}    	
    }
}
