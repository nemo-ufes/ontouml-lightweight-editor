package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufes.inf.nemo.ontouml.editor.struct.Project;
import br.ufes.inf.nemo.ontouml.editor.ui.MainFrame;
import br.ufes.inf.nemo.ontouml.editor.util.ConfigurationHelper;
import br.ufes.inf.nemo.ontouml.editor.util.Logger;
import br.ufes.inf.nemo.ontouml.editor.util.Resources;

public class ProjectManager {
	
	private MainFrame frame;
	private Project currentProject;
	
	public ProjectManager(MainFrame frame)
	{
		this.frame = frame;
	}
	
 	public void newProject()
	{
		currentProject = new ProjectImpl();
		frame.registerProject(currentProject);
		frame.showStatus(Resources.getString("status.created"));
	}
	
	public void newArtifact()
	{
		System.out.println("New Artifact...");
	}
	
	public void openProject(String path)
	{
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(Resources.getString("stdcaption.projecttype"), ConfigurationHelper.getSuffix());
		fileChooser.setDialogTitle(Resources.getString("dialog.open.title"));
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				currentProject = ProjectReader.getInstance().readProject(file);				
				frame.showStatus(Resources.getString("status.opened"));
			} catch (Exception ex) {
				Logger.logException(ex);
				JOptionPane.showMessageDialog(frame, Resources.getString("error.readfile"), Resources.getString("stdcaption.error"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void saveProjectAs()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(Resources.getString("dialog.saveas.title"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(Resources.getString("stdcaption.projecttype"), ConfigurationHelper.getSuffix());
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			saveProjectFile(file);
			currentProject.setProjectFile(file);
		}
	}
	
	public void saveProject()
	{
		File file = currentProject.getProjectFile();
		
		if (file == null) {
			saveProjectAs();
		} else {
			saveProjectFile(file);
		}
	}
	
	private void saveProjectFile(File file) {
		try {
			ProjectWriter.getInstance().writeProject(file, currentProject);				
			frame.showStatus(Resources.getString("status.saved"));
		} catch (Exception ex) {
			Logger.logException(ex);
			JOptionPane.showMessageDialog(frame, Resources.getString("error.savefile"), Resources.getString("stdcaption.error"), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Project getProject() {
		return currentProject;
	}
}
