package br.ufes.inf.nemo.oled;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.ufes.inf.nemo.ocl.editor.OCLEditorPanel;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.DiagramEditorWrapper;
import br.ufes.inf.nemo.oled.ui.ErrorTablePanel;
import br.ufes.inf.nemo.oled.ui.OutputPane;
import br.ufes.inf.nemo.oled.ui.WarningTablePanel;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

public class InfoManager extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	public static ErrorTablePanel errors;
	public static WarningTablePanel warnings;
	public static OutputPane outputPane;
	public static OCLEditorPanel ocleditor;
	public AppFrame frame;
	public UmlProject project;
	
	public void setProject(UmlProject project)
	{
		this.project = project;
		
		errors.setProject(project);
		warnings.setProject(project);
	}
	
	public void eraseProject()
	{
		this.project = null;
		
		errors.setProject(null);
		warnings.setProject(null);
		
		errors.reset();
		warnings.reset();
		outputPane.write("");
		ocleditor.setText("");
		
		updateUI();
	}
	
	public InfoManager (final AppFrame frame, final UmlProject project)
	{
		this.frame=frame;
		this.project = project;
				
		errors = new ErrorTablePanel(project);
		warnings = new WarningTablePanel(project);
		outputPane = new OutputPane();
		ocleditor = new OCLEditorPanel(frame);
		
		ocleditor.addDocumentListener(new DocumentListener() {			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
			}		
			@Override
			public void insertUpdate(DocumentEvent arg0) {
			}			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				/*InfoManager.this.frame.getDiagramManager().getCurrentDiagramEditor().getDiagram().setSaveNeeded(true);
				InfoManager.this.project.setSaveModelNeeded(true);
				InfoManager.this.frame.getDiagramManager().updateUI();*/
			}
		});
		
		JMenuItem parserMenuItem = new JMenuItem("Parse",new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/check.png")));
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem saveMenuItem = new JMenuItem("Save As...");		
		ocleditor.getPopupMenu().add(parserMenuItem);
		ocleditor.getPopupMenu().add(openMenuItem);
		ocleditor.getPopupMenu().add(saveMenuItem);
				
		saveMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getDiagramManager().exportOCL();
			}
		});
		openMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getDiagramManager().importOCL();
			}
		});
		parserMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getDiagramManager().parseOCL(true);
			}
		});
		
		setBorder(null);
		setBackground(UIManager.getColor("Panel.background"));
					
		addTab(" Warnings ",warnings);		
		setIconAt(indexOfComponent(warnings),new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/warning.png")));
		
		addTab(" Errors ",errors);	
		setIconAt(indexOfComponent(errors),new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/error.png")));
		
		addTab(" Output ",outputPane);	
		setIconAt(indexOfComponent(outputPane),IconLoader.getInstance().getIcon(getResourceString("editortoolbar.output.icon")));
		
		addTab(" OCL Editor ",ocleditor);	
		setIconAt(indexOfComponent(ocleditor),IconLoader.getInstance().getIcon(getResourceString("editortoolbar.ocleditor.icon")));
		
		setTabPlacement(JTabbedPane.BOTTOM);				
	}
	
	public br.ufes.inf.nemo.ocl.editor.OCLEditorPanel getOcleditor() {
		return ocleditor;
	}

	public OutputPane getOutput(){
		return outputPane;
	}
	
	public WarningTablePanel getWarnings(){
		return warnings;
	}
		
	public void setTitleWarning(String text)
	{
		setTitleAt(indexOfComponent(warnings),text);
	}
	
	public void setTitleErrors(String text)
	{
		setTitleAt(indexOfComponent(errors),text);
	}
	
	/**
	 * Get Constraints from the editor view.
	 */
	public String getConstraints() { 
		return ocleditor.getText(); 
	}
	
	public void setConstraints(String text) { 
		ocleditor.setText(text);
		ocleditor.validate();
		ocleditor.repaint();
	}
	
	public void addConstraints(String text) { 
		ocleditor.addText(text);
		ocleditor.validate();
		ocleditor.repaint();
	}
	
	public ErrorTablePanel getErrors(){
		return errors;
	}
	
	private String getResourceString(String property) {
	    return ApplicationResources.getInstance().getString(property);
	}

	public void showOutputText(String text, boolean clear, boolean showOutput)
	{		
		if(clear)
			outputPane.write(text);
		else
			outputPane.append(text);
				
		if(showOutput){
			outputPane.setVisible(true);
			frame.focusOnOutput();
		}		
	}
	
	public UmlProject getProject(){
		return project;
	}

}
