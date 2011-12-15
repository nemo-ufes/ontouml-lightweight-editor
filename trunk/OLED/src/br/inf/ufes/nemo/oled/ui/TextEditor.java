package br.inf.ufes.nemo.oled.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextEditor extends JPanel {

	private static final long serialVersionUID = -1832428183354138999L;
	private JTextArea textArea = new JTextArea();
	
	public TextEditor()
	{
		super(new BorderLayout());
		JScrollPane scroll = new JScrollPane(textArea);
		add(scroll, BorderLayout.CENTER);
	}

	public void setText(String text) {
		this.textArea.setText(text);
	}

	public String getTextArea() {
		return textArea.getText();
	}
		
}
