package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.model.UmlProject;

public class TextEditor extends JPanel implements Editor {

	private static final long serialVersionUID = -1832428183354138999L;
	private JTextArea textArea = new JTextArea();
	private UmlProject project;
	
	public TextEditor(UmlProject project)
	{
		super(new BorderLayout());
		JScrollPane scroll = new JScrollPane(textArea);
		add(scroll, BorderLayout.CENTER);
		
		this.project = project;
	}

	public void loadFile(String filePath) {
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			textArea.read(br, null);
			br.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setText(String text) {
		textArea.setText(text);
	}

	public String getTextArea() {
		return textArea.getText();
	}

	@Override
	public boolean isSaveNeeded() {
		return false;
	}

	@Override
	public EditorNature getEditorNature() {
		return EditorNature.TEXT;
	}

	@Override
	public Diagram getDiagram() {
		return null;
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public UmlProject getProject() {
		return project;
	}
		
}
