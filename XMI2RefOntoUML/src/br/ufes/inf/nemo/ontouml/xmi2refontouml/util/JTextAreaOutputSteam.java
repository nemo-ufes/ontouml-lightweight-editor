package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class JTextAreaOutputSteam extends OutputStream {
	
	private JTextArea textArea;
	
	public JTextAreaOutputSteam (JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public void write(int b) throws IOException {
		textArea.append(String.valueOf((char) b));
		textArea.setCaretPosition(textArea.getDocument().getLength());
    }
	 
    public void write(byte[] b, int off, int len) throws IOException {
    	textArea.append(new String(b, off, len));
    	textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    public void write(byte[] b) throws IOException {
    	write(b, 0, b.length);
    }
}
