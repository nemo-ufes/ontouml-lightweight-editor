/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.util;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author John Guerson
 *
 */
public class FileChoosersUtil {

	/**
	 * Get OntoUML Path Location.
	 */
	public static String getOntoUMLPathLocation(Component frame, String openLastPath)
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
	public static String saveOntoUMLPathLocation(Component frame, String saveLastPath)
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
	public static String openOCLPathLocation(Component frame, String openLastPath)
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
	public static String saveOCLPathLocation(Component frame, String saveLastPath)
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
	
	/**
	 * Save Txt Location.
	 */
	public static String saveTxtLocation(Component frame, String lastLocation)
	{
		JFileChooser fileChooser = new JFileChooser(lastLocation);
		fileChooser.setDialogTitle("Export");
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Document (*.txt)", "txt");
		fileChooser.addChoosableFileFilter(txtFilter);
		fileChooser.setFileFilter(txtFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == txtFilter) 
			{
				String path = fileChooser.getSelectedFile().getPath();
				if (path.contains(".txt"))
					return fileChooser.getSelectedFile().getPath();
				else
					return fileChooser.getSelectedFile().getPath()+".txt";				
			}
		}
		return null;
	}
}
