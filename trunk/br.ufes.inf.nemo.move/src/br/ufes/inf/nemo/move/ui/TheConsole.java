package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufes.inf.nemo.common.file.FileUtil;

/**
 * @author John Guerson
 */

public class TheConsole extends JPanel {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrollpane;	
	private JTextArea output;
	private JButton btnClear;
	private JButton btnSave;
	
	private TheFrame frame;
	private JLabel lbldate;
	
	public TheConsole(TheFrame frame)
	{
		this();
		this.frame = frame;
	}
	
	public String getCurrentDateAndTime()
	{
		String result = new String();
	   
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    //get current date time with Date()
	    Date date = new Date();
	    result += dateFormat.format(date);
	 			   
	    return result;
	}
	
	/**
	 * Constructor.
	 */
	public TheConsole() 
	{
		setBackground(Color.WHITE);
		//setBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST));
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		output = new JTextArea();
		output.setFont(new Font("Tahoma", Font.PLAIN, 12));
		output.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		output.setEditable(false);
		output.setMargin(new Insets(6, 6, 6, 6));
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		output.setBackground(Color.WHITE);
		//output.setBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST));
				
		scrollpane = new JScrollPane();
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		setLayout(new BorderLayout(0, 0));
		scrollpane.setViewportView(output);
		scrollpane.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(scrollpane);
		
		JToolBar toolBar = new JToolBar();
		scrollpane.setColumnHeaderView(toolBar);
		FlowLayout fl_toolBar = new FlowLayout(FlowLayout.LEFT);
		fl_toolBar.setVgap(1);
		fl_toolBar.setHgap(3);
		toolBar.setLayout(fl_toolBar);
		
		btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(TheConsole.class.getResource("/resources/icon/save-16x16.png")));
		
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				saveContent();
			}
		});
								
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clear();
			}
		});
		
		btnClear.setIcon(new ImageIcon(TheConsole.class.getResource("/resources/icon/clear-16x16.png")));
		btnClear.setFocusable(false);
		toolBar.add(btnClear);
		
		btnSave.setFocusable(false);
		toolBar.add(btnSave);
		
		lbldate = new JLabel(" ["+getCurrentDateAndTime()+"]");
		lbldate.setHorizontalAlignment(SwingConstants.LEFT);
		lbldate.setPreferredSize(new Dimension(330, 15));
		toolBar.add(lbldate);
		
	}

	/**
	 * Save Txt Location.
	 */
	public String saveTxtLocation()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save");
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
	
	public void saveContent ()
    {    	
    	String path = saveTxtLocation();
    	
    	if(path!=null)
		try {
			
			FileUtil.copyStringToFile(lbldate.getText()+"\n\n"+output.getText(), path);
		
		} catch (IOException e) {
			frame.showErrorMessageDialog("IO", e.getMessage());
			e.printStackTrace();
		}    	
    }
	 
	public void goToInitialPosition()
	{
		output.setCaretPosition(0);		
	}
	
	public void clear()
	{
		output.setText("");
		lbldate.setText(" ["+getCurrentDateAndTime()+"]");
		lbldate.repaint();
		lbldate.validate();
	}
	
	public void append(String text)
	{		
		output.setText("\n"+output.getText() + text);
		lbldate.setText(" ["+getCurrentDateAndTime()+"]");
		lbldate.repaint();
		lbldate.validate();
	}
	
	public void write(String text)
	{		
		output.setText("\n"+text);
		lbldate.setText(" ["+getCurrentDateAndTime()+"]");
		lbldate.repaint();
		lbldate.validate();
	}

}
