package br.ufes.inf.nemo.move.panel;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class ConsolePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JTextArea txtrOntoumlModelValidation;
	
	public void Writeln(String str)
	{
		txtrOntoumlModelValidation.setText(txtrOntoumlModelValidation.getText()+"\n"+str);
	}	
	
	public void Clear()
	{
		txtrOntoumlModelValidation.setText("");
	}
	
	/**
	 * Create the panel.
	 */
	public ConsolePanel() 
	{		
		txtrOntoumlModelValidation = new JTextArea();
		
		txtrOntoumlModelValidation.setText("\r\nApplication Initialized...\r\n\r\nOntoUML Model Validation Environment - v0.1\r\n\r\n\r\n");
		txtrOntoumlModelValidation.setEditable(false);
		txtrOntoumlModelValidation.setBackground(Color.WHITE);
		
		txtrOntoumlModelValidation.setBorder(new EmptyBorder(10, 10, 10, 10));
		txtrOntoumlModelValidation.setFont(null);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(txtrOntoumlModelValidation, GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(txtrOntoumlModelValidation, GroupLayout.PREFERRED_SIZE, 107, Short.MAX_VALUE)
		);
		setLayout(groupLayout);

	}
}
