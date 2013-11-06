package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;

/** 
 * Wrapper class for {@link DiagramEditor} responsible for providing toolbar and handling the model file.  
 */
public class DiagramEditorWrapper extends JPanel implements Editor{

	private static final long serialVersionUID = -1962960747434759099L;
	private DiagramEditor editor;		
	//TODO Remove me
	private File projectFile;
	
	public DiagramEditorWrapper(final DiagramEditor editor, DiagramEditorCommandDispatcher editorDispatcher)
	{
		super(new BorderLayout());
		this.editor = editor;		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0,0));
										
		JScrollPane scrollpane = new JScrollPane(editor);
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollpane.setBorder(new EmptyBorder(0,0,0,0));
		
		panel.add(scrollpane);
		panel.setBorder(new EmptyBorder(0,0,0,0));
				
		add(panel,BorderLayout.CENTER);		
	}	
	
	public static int GetScreenWorkingWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
		
	public void setDiagramEditor(DiagramEditor editor) {
		this.editor = editor;
	}

	public DiagramEditor getDiagramEditor() {
		return editor;
	}
	
	
	public void setModelFile(File modelFile) {
		this.projectFile = modelFile;
	}

	public File getModelFile() {
		return projectFile;
	}

	class ProgressPane extends JPanel
	{
		private static final long serialVersionUID = -2139086725410223732L;
		JLabel output = new JLabel();
		JProgressBar progressBar = new JProgressBar();
		
		public ProgressPane()
		{
			super();
			
			BoxLayout layout = new BoxLayout(this, javax.swing.BoxLayout.X_AXIS);
			this.setLayout(layout);
			progressBar.setMaximumSize(new java.awt.Dimension(100, 14));
			progressBar.setLayout(null);
			
			this.add(output);
			this.add(Box.createHorizontalGlue());
			this.add(progressBar);
		}
		
		/*
		 * Display short status messages, with no scrolling
		 */
		public void write(String text)
		{
			output.setText(text);
		}
	}
	
	@Override
	public UmlProject getProject() {
		return editor.getDiagram().getProject();
	}
	
	@Override
	public boolean isSaveNeeded() {
		return editor.isSaveNeeded();
	}

	@Override
	public EditorNature getEditorNature() {
		return editor.getEditorNature();
	}

	@Override
	public Diagram getDiagram() {
		return editor.getDiagram();
	}

	@Override
	public void dispose() {
		
	}
}
