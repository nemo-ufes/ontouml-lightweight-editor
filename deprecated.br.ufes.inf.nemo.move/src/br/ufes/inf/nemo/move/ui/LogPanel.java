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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import br.ufes.inf.nemo.common.file.FileUtil;

/**
 * @author John Guerson
 */

public class LogPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrollpane;	
	private JTextArea output;
	private JButton btnClear;
	private JButton btnSave;
	
	private TheFrame frame;
	private JLabel lbldate;
	
	public LogPanel(TheFrame frame)
	{
		this();
		this.frame = frame;
		
		lbldate.setText("  "+frame.getCurrentDateAndTime()+"  ");
	}	
	
	/**
	 * Constructor.
	 */
	public LogPanel() 
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
		toolBar.setFloatable(false);
		scrollpane.setColumnHeaderView(toolBar);
		FlowLayout fl_toolBar = new FlowLayout(FlowLayout.LEFT);
		fl_toolBar.setVgap(1);
		fl_toolBar.setHgap(3);
		toolBar.setLayout(fl_toolBar);
		
		btnSave = new JButton();
		btnSave.setToolTipText("export to a txt file");
		btnSave.setIcon(new ImageIcon(LogPanel.class.getResource("/resources/icon/export-16x16.png")));
		
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				saveContent();
			}
		});
								
		btnClear = new JButton();
		btnClear.setToolTipText("clear console");
		btnClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clear();
			}
		});
				
		btnSave.setFocusable(false);
		toolBar.add(btnSave);
		
		btnClear.setIcon(new ImageIcon(LogPanel.class.getResource("/resources/icon/clear-16x16.png")));
		btnClear.setFocusable(false);
		toolBar.add(btnClear);
		
		lbldate = new JLabel("  ");
		lbldate.setHorizontalAlignment(SwingConstants.LEFT);
		lbldate.setPreferredSize(new Dimension(330, 15));
		toolBar.add(lbldate);
		
	}

	public void saveContent ()
    {    	
    	String path = FileChoosersUtil.saveTxtLocation(frame, null);
    	
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
		lbldate.setText("  "+frame.getCurrentDateAndTime()+"  ");
		lbldate.repaint();
		lbldate.validate();
	}
	
	public void append(String text)
	{		
		output.setText("\n"+output.getText() + text);
		lbldate.setText("  "+frame.getCurrentDateAndTime()+"  ");
		lbldate.repaint();
		lbldate.validate();
	}
	
	public void write(String text)
	{		
		output.setText("\n"+text);
		lbldate.setText("  "+frame.getCurrentDateAndTime()+"  ");
		lbldate.repaint();
		lbldate.validate();
	}

}
