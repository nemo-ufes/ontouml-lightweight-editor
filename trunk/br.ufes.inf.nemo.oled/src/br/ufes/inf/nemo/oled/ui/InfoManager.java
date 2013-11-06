package br.ufes.inf.nemo.oled.ui;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.editor.ocl.OCLEditorPanel;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

public class InfoManager extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	public static JTabbedPane infoTabbedPane;
	public static PropertyTablePanel properties;
	public static ErrorTablePanel errors;
	public static WarningTablePanel warnings;
	public static OutputPane outputPane;
	public static OCLEditorPanel ocleditor;
	public AppFrame frame;
	public UmlProject project;
	
	public void setProject(UmlProject project)
	{
		this.project = project;
		
		properties.setProject(project);
		errors.setProject(project);
		warnings.setProject(project);
	}
	
	public InfoManager (final AppFrame frame, final UmlProject project)
	{
		this.frame=frame;
		this.project = project;
				
		properties = new PropertyTablePanel(project);		
		errors = new ErrorTablePanel(project);
		warnings = new WarningTablePanel(project);
		outputPane = new OutputPane();
		ocleditor = new OCLEditorPanel(frame);
		
		ocleditor.getTextArea().getDocument().addDocumentListener(new DocumentListener() {			
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
		
		setFont(new Font("Tahoma", Font.PLAIN, 11));
		setBorder(null);
		setBackground(UIManager.getColor("Panel.background"));
					
		add(properties);	
		setTitleAt(0," Properties ");
		setIconAt(0,new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/table.png")));
		
		add(warnings);	
		setTitleAt(1," Warnings ");
		setIconAt(1,new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/warning.png")));
		
		add(errors);	
		setTitleAt(2," Errors ");
		setIconAt(2,new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/error.png")));
		
		add(outputPane);	
		setTitleAt(3," Output ");		
		setIconAt(3,IconLoader.getInstance().getIcon(getResourceString("editortoolbar.output.icon")));
		
		add(ocleditor);	
		setTitleAt(4," OCL Editor ");
		setSelectedIndex(4);
		setIconAt(4,IconLoader.getInstance().getIcon(getResourceString("editortoolbar.ocleditor.icon")));
		
		setTabPlacement(JTabbedPane.BOTTOM);				
	}
	
	public OCLEditorPanel getOcleditor() {
		return ocleditor;
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
		
	public void setTitleWarning(String text)
	{
		setTitleAt(1,text);
	}
	
	public void setTitleErrors(String text)
	{
		setTitleAt(2,text);
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

	public void showOutputText(String text, boolean clear, boolean showOutput)
	{		
		if(clear)
			outputPane.write(text);
		else
			outputPane.apped(text);
				
		if(showOutput){
			outputPane.setVisible(true);
			frame.focusOnOutput();
		}		
	}
	
	public UmlProject getProject(){
		return project;
	}

}
