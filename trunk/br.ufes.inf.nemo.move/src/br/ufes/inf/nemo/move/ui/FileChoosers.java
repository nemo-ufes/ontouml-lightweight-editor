package br.ufes.inf.nemo.move.ui;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChoosers {

	/**
	 * Get OntoUML Path Location.
	 */
	public static String getOntoUMLPathLocation(TheFrame frame, String openLastPath)
	{
		JFileChooser fileChooser = new JFileChooser(openLastPath);
		fileChooser.setDialogTitle("Open OntoUML");
		FileNameExtensionFilter ontoumlFilter = new FileNameExtensionFilter("Reference OntoUML Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(ontoumlFilter);		
		fileChooser.setFileFilter(ontoumlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == ontoumlFilter) 
			{
				openLastPath = fileChooser.getSelectedFile().getPath();
				return fileChooser.getSelectedFile().getPath();
			}
		}
		return null;
	}
	
	/**
	 * Save OntoUML Path Location.
	 */
	public static String saveOntoUMLPathLocation(TheFrame frame, String saveLastPath)
	{
		JFileChooser fileChooser = new JFileChooser(saveLastPath);
		fileChooser.setDialogTitle("Save OntoUML");
		FileNameExtensionFilter ontoumlFilter = new FileNameExtensionFilter("Reference OntoUML Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(ontoumlFilter);
		fileChooser.setFileFilter(ontoumlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == ontoumlFilter) 
			{
				String path = fileChooser.getSelectedFile().getPath();				
				if (path.contains(".refontouml")){
					saveLastPath = fileChooser.getSelectedFile().getPath();
					return fileChooser.getSelectedFile().getPath();
				} else {
					saveLastPath = fileChooser.getSelectedFile().getPath()+".refontouml";
					return fileChooser.getSelectedFile().getPath()+".refontouml";
				}
			}
		}
		return null;
	}

	/**
	 * Open OCL Path Location.
	 * 
	 * @return
	 */
	public static String openOCLPathLocation(TheFrame frame, String openLastPath)
	{
		JFileChooser fileChooser = new JFileChooser(openLastPath);
		fileChooser.setDialogTitle("Open OCL");
		FileNameExtensionFilter oclFilter = new FileNameExtensionFilter("OCL Constraints (*.ocl)", "ocl");
		fileChooser.addChoosableFileFilter(oclFilter);
		fileChooser.setFileFilter(oclFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == oclFilter) 
			{
				openLastPath = fileChooser.getSelectedFile().getPath();
				return fileChooser.getSelectedFile().getPath();
			}
		}
		return null;
	}	
	
	/**
	 * Save OCL Path Location.
	 * 
	 * @return
	 */
	public static String saveOCLPathLocation(TheFrame frame, String saveLastPath)
	{
		JFileChooser fileChooser = new JFileChooser(saveLastPath);
		fileChooser.setDialogTitle("Save OCL");
		FileNameExtensionFilter oclFilter = new FileNameExtensionFilter("OCL Constraints (*.ocl)", "ocl");
		fileChooser.addChoosableFileFilter(oclFilter);
		fileChooser.setFileFilter(oclFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == oclFilter) 
			{
				String path = fileChooser.getSelectedFile().getPath();
				if (path.contains(".ocl")){
					saveLastPath = fileChooser.getSelectedFile().getPath();				
					return fileChooser.getSelectedFile().getPath();
				} else {
					saveLastPath = fileChooser.getSelectedFile().getPath()+".ocl";
					return fileChooser.getSelectedFile().getPath()+".ocl";
				}
			}
		}
		return null;
	}	
}
