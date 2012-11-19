package br.ufes.inf.nemo.move.ocl.constraint;

import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class OCLEditorScrollPane extends JScrollPane {

	private static final long serialVersionUID = 5962452497741459314L;
	
	private JEditorPane editConstraint;
	
	public OCLEditorScrollPane ()
	{
		editConstraint = new JEditorPane();		
		editConstraint.setFont(new Font("Consolas", Font.PLAIN, 12));
		setPreferredSize(new java.awt.Dimension(400, 360));
		setViewportView(editConstraint);
	}
	
	public void write(String text)
	{
		editConstraint.setText(text);
	}	
}
