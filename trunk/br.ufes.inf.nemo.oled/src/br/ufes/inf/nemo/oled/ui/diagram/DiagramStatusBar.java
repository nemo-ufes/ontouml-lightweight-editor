package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.oled.ui.StatusListener;
import java.awt.Color;

public class DiagramStatusBar extends JPanel implements StatusListener{
	
	private static final long serialVersionUID = 2153837501881399529L;
	
	public JLabel statusLabel = new JLabel();
	public DiagramEditor editor;
	
	public void clearStatus()
	{
		statusLabel.setText("");
	}
		
	public DiagramStatusBar(DiagramEditor d)
	{
		super(new BorderLayout());		
		setBackground(Color.WHITE);
		this.editor = d;
		setBorder(new EmptyBorder(3, 3, 3, 3));		
		statusLabel.setBackground(Color.WHITE);
		add(statusLabel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(450, 28));		
	}

	public void reportStatus(String status)
	{
		statusLabel.setText(status);
	}	
}