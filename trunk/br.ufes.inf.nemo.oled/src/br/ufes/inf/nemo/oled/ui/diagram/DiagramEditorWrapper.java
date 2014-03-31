package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.DiagramEditorCommandDispatcher;
import br.ufes.inf.nemo.oled.ui.diagram.Editor.EditorNature;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/** 
 * Wrapper class for {@link DiagramEditor} responsible for providing toolbar and handling the model file.  
 */
public class DiagramEditorWrapper extends JPanel implements Editor{

	private static final long serialVersionUID = -1962960747434759099L;
	private DiagramEditor editor;
	private JScrollPane scrollpane;
		
	//TODO Remove me
	private File projectFile;
	
	public DiagramEditorWrapper(final DiagramEditor editor, DiagramEditorCommandDispatcher editorDispatcher)
	{
		super(new BorderLayout(0,0));
		this.editor = editor;		
		
		scrollpane = new JScrollPane();
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollpane.setWheelScrollingEnabled(true);
		scrollpane.setViewportView(editor);
		scrollpane.setBorder(new EmptyBorder(0,0,0,0));
		
		add(scrollpane);
		setBorder(new EmptyBorder(0,0,0,0));
	}	
	
	public void setViewportCenter(Point p) {
	    JViewport vp = (JViewport) scrollpane.getViewport();
	    Rectangle viewRect = vp.getViewRect();

	    viewRect.x = p.x - viewRect.width / 2;
	    viewRect.y = p.y - viewRect.height / 2;

	    scrollpane.scrollRectToVisible(viewRect);
	}
	
	public Point getViewportCenter() {
	    JViewport vp = (JViewport) this.getParent();
	    Point p = vp.getViewPosition();
	    return new Point(p.x+vp.getWidth()/2,p.y+vp.getHeight()/2);
	}
	
	public JScrollPane getScrollPane()
	{
		return scrollpane;
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
	
	public boolean contains(RefOntoUML.Element element){
		return editor.getDiagram().containsChild(ModelHelper.getDiagramElementByEditor(element,editor));
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
