package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.editor.ocl.OCLEditorPanel;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

/** 
 * Wrapper class for {@link DiagramEditor} responsible for providing toolbar and handling the model file.  
 */
public class DiagramEditorWrapper extends JPanel implements Editor{

	private static final long serialVersionUID = -1962960747434759099L;
	private DiagramEditor editor;	
	public JSplitPane editorArea  = new JSplitPane();	
	public JSplitPane mainEditorArea = new JSplitPane();
	
	public static JTabbedPane infoTabbedPane;
	public static PropertyTablePanel properties;
	public static ErrorTablePanel errors;
	public static WarningTablePanel warnings;
	public static OutputPane outputPane;
	public static OCLEditorPanel ocleditor;
	public static ModelTree modeltree;
	public static ToolManager toolManager;
	
	//TODO Remove me
	private File projectFile;
	
	public DiagramEditorWrapper(final DiagramEditor editor, DiagramEditorCommandDispatcher editorDispatcher)
	{
		super(new BorderLayout());
		this.editor = editor;		
		
		DiagramEditorToolbar editorToolbar = new DiagramEditorToolbar();
		JToolBar toolbar = editorToolbar.getToolbar();
		editorToolbar.addCommandListener(editorDispatcher);
		toolbar.setFloatable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0,0));
		panel.add(toolbar, BorderLayout.SOUTH);
		
		editorArea.setContinuousLayout(true);
		editorArea.setOneTouchExpandable(true);
		editorArea.setDividerSize(7);
		editorArea.setOrientation(JSplitPane.VERTICAL_SPLIT);
		editorArea.setResizeWeight(1) ;
		editorArea.setDividerLocation(0.5);
						
		JScrollPane scrollpane = new JScrollPane(editor);
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		
		panel.add(scrollpane);
						
		properties = new PropertyTablePanel(editor.getProject());		
		errors = new ErrorTablePanel(editor.getProject());
		warnings = new WarningTablePanel(editor.getProject());
		outputPane = new OutputPane();
		ocleditor = new OCLEditorPanel(editor.getManager().getFrame());
		
		infoTabbedPane = new JTabbedPane();
		infoTabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		infoTabbedPane.setBorder(null);
		infoTabbedPane.setPreferredSize(new Dimension(400,600));
		infoTabbedPane.setBackground(UIManager.getColor("Panel.background"));
					
		infoTabbedPane.add(properties);	
		infoTabbedPane.setTitleAt(0," Properties ");
		infoTabbedPane.setIconAt(0,new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/table.png")));
		
		infoTabbedPane.add(warnings);	
		infoTabbedPane.setTitleAt(1," Warnings ");
		infoTabbedPane.setIconAt(1,new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/warning.png")));
		
		infoTabbedPane.add(errors);	
		infoTabbedPane.setTitleAt(2," Errors ");
		infoTabbedPane.setIconAt(2,new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/error.png")));
		
		infoTabbedPane.add(outputPane);	
		infoTabbedPane.setTitleAt(3," Output ");		
		infoTabbedPane.setIconAt(3,IconLoader.getInstance().getIcon(getResourceString("editortoolbar.output.icon")));
		
		infoTabbedPane.add(ocleditor);	
		infoTabbedPane.setTitleAt(4," OCL Editor ");
		infoTabbedPane.setSelectedIndex(4);
		infoTabbedPane.setIconAt(4,IconLoader.getInstance().getIcon(getResourceString("editortoolbar.ocleditor.icon")));
		
		infoTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		
		modeltree = ModelTree.getTreeFor(editor.getManager().getFrame(),getProject());
				
		infoTabbedPane.setPreferredSize(new Dimension(100,300));
		
		editorArea.add(panel, JSplitPane.TOP);		
		editorArea.add(infoTabbedPane,JSplitPane.BOTTOM);
		
		mainEditorArea.setContinuousLayout(true);
		mainEditorArea.setOneTouchExpandable(true);
		mainEditorArea.setDividerSize(7);
		mainEditorArea.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		mainEditorArea.setResizeWeight(1) ;

		Dimension minimumSize = new Dimension(0, 0);
		editorArea.setMinimumSize(minimumSize);
		modeltree.setMinimumSize(minimumSize);
		
		mainEditorArea.add(editorArea,JSplitPane.LEFT);
		mainEditorArea.add(modeltree, JSplitPane.RIGHT);
		
		this.add(mainEditorArea,BorderLayout.CENTER);		
		
	}	
	
	public static PropertyTablePanel getProperties(){
		return properties;
	}

	public OutputPane getOutput(){
		return outputPane;
	}
	
	public WarningTablePanel getWarnings(){
		return warnings;
	}
	
	public ModelTree getModelTree()
	{
		return modeltree;
	}
	
	public void setTitleWarning(String text)
	{
		infoTabbedPane.setTitleAt(1,text);
	}
	
	public void setTitleErrors(String text)
	{
		infoTabbedPane.setTitleAt(2,text);
	}
	
	/**
	 * Get Constraints from the editor view.
	 */
	public String getConstraints() { 
		return ocleditor.textArea.getText(); 
	}
	
	public void setConstraints(String text) { 
		ocleditor.textArea.setText(text);
		ocleditor.validate();
		ocleditor.repaint();
	}
	
	public void addConstraints(String text) { 
		ocleditor.textArea.append(text);
		ocleditor.validate();
		ocleditor.repaint();
	}
	
	public ErrorTablePanel getErrors(){
		return errors;
	}
	
	private String getResourceString(String property) {
	    return ApplicationResources.getInstance().getString(property);
	}
	
	public static void focusOnProperties()
	{
		infoTabbedPane.setSelectedIndex(0);
	}
	
	public void focusOnErrors()
	{
		infoTabbedPane.setSelectedIndex(2);
	}
	
	public void focusOnWarnings()
	{
		infoTabbedPane.setSelectedIndex(1);
	}
	
	public void focusOnOutput()
	{
		infoTabbedPane.setSelectedIndex(3);
	}
	
	public void focusOnOclEditor()
	{
		infoTabbedPane.setSelectedIndex(4);
	}
	
	public void setDiagramEditor(DiagramEditor editor) {
		this.editor = editor;
	}

	public DiagramEditor getDiagramEditor() {
		return editor;
	}
	
	public void showOutputText(String text, boolean clear, boolean showOutput)
	{		
		if(clear)
		{
			outputPane.write(text);
		}
		else
		{
			outputPane.apped(text);
		}
		
		if(showOutput)
		{
			outputPane.setVisible(true);
			showOutput();
		}
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

	private void showOutput()
	{
		int location = editorArea.getDividerLocation();
		int maxLocation = editorArea.getMaximumDividerLocation();
		if(location >= maxLocation - 1) //1px bug
        {
			editorArea.setDividerLocation(0.75);
        }
		focusOnOutput();
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
        	editorArea.setDividerLocation(0.75);
        }
	}

	public void showOrHideOclEditor() {
		
		int location = editorArea.getDividerLocation();
		int maxLocation = editorArea.getMaximumDividerLocation();
		if(location < maxLocation)
        {
			editorArea.setDividerLocation(1.0);	
        }
        else
        {
        	editorArea.setDividerLocation(0.75);
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
