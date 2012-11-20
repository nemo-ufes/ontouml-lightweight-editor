package br.ufes.inf.nemo.move.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.util.ColorPalette;
import br.ufes.inf.nemo.move.util.ColorPalette.ThemeColor;

/**
 * @author John Guerson
 */

public class TheConsole extends JPanel {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrollpane;	
	private JTextArea output;
		
	/**
	 * Constructor.
	 */
	public TheConsole() 
	{
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		output = new JTextArea();
		output.setFont(new Font("Tahoma", Font.PLAIN, 12));
		output.setBorder(new EmptyBorder(0, 0, 0, 0));
		output.setEditable(false);
		output.setMargin(new Insets(6, 6, 6, 6));
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		output.setBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST));
		
		setBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST));
		
		scrollpane = new JScrollPane();
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollpane.setViewportView(output);
		scrollpane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
		);
		groupLayout.setAutoCreateContainerGaps(false);
		groupLayout.setAutoCreateGaps(false);
		setLayout(groupLayout);
	}

	public void append(String text)
	{		
		output.setText(output.getText() + text);
	}
	
	public void write(String text)
	{		
		output.setText(text);
	}

}
