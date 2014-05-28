package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.model.UmlProject;

public class TextEditor extends JPanel implements Editor {

	private static final long serialVersionUID = -1832428183354138999L;
	private RSyntaxTextArea textArea = new RSyntaxTextArea();
	private RTextScrollPane scrollPane;
	private UmlProject project;
	
	public TextEditor(UmlProject project)
	{		
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		textArea = new RSyntaxTextArea(5, 30);		
	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
	    textArea.setAntiAliasingEnabled(true);
	    textArea.setCodeFoldingEnabled(false);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(new Color(255, 255, 255));				
		setTheme(textArea,"/resources/text/editor/themes/rsyntaxarea.xml");
	    setLayout(new BorderLayout(0, 0));
      			
      	scrollPane = new RTextScrollPane(textArea);
      	scrollPane.getGutter().setBorder(new EmptyBorder(0, 0, 0, 0));
      	scrollPane.getGutter().setBorderColor(Color.LIGHT_GRAY);
      	scrollPane.setIconRowHeaderEnabled(true);
      	scrollPane.getGutter().setLineNumberColor(Color.GRAY);
      	scrollPane.getTextArea().setRows(5);
      	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    	scrollPane.setBorder(null);
    	
    	textArea.setMinimumSize(new Dimension(0, 0));
    	scrollPane.setMinimumSize(new Dimension(0, 0));
 	    setMinimumSize(new Dimension(0, 0));
      	add(scrollPane); 
				
		this.project = project;
	}

	 public void setTheme(RSyntaxTextArea textArea, String xmlPath)
	 {
		 Theme theme;
		 try {
			theme = Theme.load(getClass().getResourceAsStream(xmlPath));
				theme.apply(textArea);
			} catch (IOException e) {
				e.printStackTrace();
			}   
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
