package br.ufes.inf.nemo.move.ocl;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class OCLEditorScrollPane extends JScrollPane {

	private static final long serialVersionUID = 5962452497741459314L;
	
	private JEditorPane editConstraint;
	
	public OCLEditorScrollPane ()
	{
		editConstraint = new JEditorPane();		
				
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setPreferredSize(new java.awt.Dimension(400, 360));
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
		setViewportView(editConstraint);
	}
	
}
