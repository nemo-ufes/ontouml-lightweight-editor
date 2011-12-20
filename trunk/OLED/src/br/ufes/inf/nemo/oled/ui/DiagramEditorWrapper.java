package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;

/** 
 * Wrapper class for {@link DiagramEditor} responsible for providing toolbar and handling the model file.  
 */
public class DiagramEditorWrapper extends JPanel {

	private static final long serialVersionUID = -1962960747434759099L;
	private DiagramEditor editor;
	private OutputPane outputPane = new OutputPane();
	private JSplitPane editorArea  = new JSplitPane();
	private File projectFile;
	
	public DiagramEditorWrapper(DiagramEditor editor, EditorCommandDispatcher editorDispatcher)
	{
		super(new BorderLayout());
		this.editor = editor;
		
		DiagramEditorToolbar editorToolbar = new DiagramEditorToolbar();
		JToolBar toolbar = editorToolbar.getToolbar();
		editorToolbar.addCommandListener(editorDispatcher);
		this.add(toolbar, BorderLayout.NORTH);
		
		editorArea.setContinuousLayout(true);
		editorArea.setOneTouchExpandable(true);
		editorArea.setDividerSize(7);
		editorArea.setOrientation(JSplitPane.VERTICAL_SPLIT);
		editorArea.setResizeWeight(1) ;
		editorArea.setDividerLocation(1.0);
		
		this.add(editorArea, BorderLayout.CENTER);

		JScrollPane scrollpane = new JScrollPane(editor);
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		
		editorArea.add(scrollpane, JSplitPane.TOP);
		editorArea.add(outputPane, JSplitPane.BOTTOM);
		
	}
	
	public void setDiagramEditor(DiagramEditor editor) {
		this.editor = editor;
	}

	public DiagramEditor getDiagramEditor() {
		return editor;
	}
	
	public void showOutputText(String text, boolean clear)
	{		
		if(clear)
		{
			outputPane.write(text);
		}
		else
		{
			outputPane.apped(text);
		}
		
		outputPane.setVisible(true);
		
		showOutput();
	}
	
	public void setModelFile(File modelFile) {
		this.projectFile = modelFile;
	}

	public File getModelFile() {
		return projectFile;
	}

	class OutputPane extends JPanel
	{
		private static final long serialVersionUID = -7066838889714939605L;
		JTextPane output = new JTextPane();
		
		public OutputPane()
		{
			super();
			
			output.setEditable(false);
			output.setBackground(new Color(0xF2FCAC));
			output.setMinimumSize(new Dimension(0, 0));
			
			BorderLayout layout = new BorderLayout();
			this.setLayout(layout);
			
			JScrollPane scrollpane = new JScrollPane(output);
			scrollpane.getVerticalScrollBar().setUnitIncrement(10);
			scrollpane.getHorizontalScrollBar().setUnitIncrement(10);
			
			this.add(scrollpane, BorderLayout.CENTER);
			this.setMinimumSize(new Dimension(0, 0));
		}
		
		public void apped(String text)
		{
			output.setText(output.getText() + text);
		}
		
		public void write(String text)
		{
			output.setText(text);
		}
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

	private void showOutput()
	{
		int location = editorArea.getDividerLocation();
		int maxLocation = editorArea.getMaximumDividerLocation();
		if(location >= maxLocation - 1) //1px bug
        {
        	editorArea.setDividerLocation(0.83);
        }
	}
	
	public void showOrHideOutput() {
		
		int location = editorArea.getDividerLocation();
		int maxLocation = editorArea.getMaximumDividerLocation();
		if(location < maxLocation)
        {
        	editorArea.setDividerLocation(1.0);	
        }
        else
        {
        	editorArea.setDividerLocation(0.83);
        }
	}
}
