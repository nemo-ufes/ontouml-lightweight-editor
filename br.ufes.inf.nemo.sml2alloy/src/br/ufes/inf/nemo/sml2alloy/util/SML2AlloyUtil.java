package br.ufes.inf.nemo.sml2alloy.util;

import java.io.File;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class SML2AlloyUtil extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RefOntoUML.Package importOntoUML()
	{
		RefOntoUML.Package p = null;
		
		JFileChooser fileChooser = new JFileChooser("C:\\Users\\Sobral\\Dropbox\\UFES\\Mestrado\\Dissertação\\");
		fileChooser.setDialogTitle("Import OntoUML");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Reference OntoUML Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			if (fileChooser.getFileFilter() == filter)
			{
				try
				{
					ResourceSet resourceSet = new ResourceSetImpl();
					resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,new OLEDResourceFactory());
					resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI, RefOntoUML.RefOntoUMLPackage.eINSTANCE);
					
					File ecoreFile = new File(fileChooser.getSelectedFile().getPath());					
					URI fileURI = URI.createFileURI(ecoreFile.getAbsolutePath());		
					Resource resource = resourceSet.createResource(fileURI);		
					resource.load(Collections.emptyMap());
					
					p = (RefOntoUML.Package)resource.getContents().get(0);
					
				} catch (Exception ex)
				{
					JOptionPane.showMessageDialog(this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		return p;
	}
	
	public sml2.SMLModel importSMLModel()
	{
		sml2.SMLModel sml_model = null;
		
		JFileChooser fileChooser = new JFileChooser("C:\\Users\\Sobral\\Dropbox\\UFES\\Mestrado\\Dissertação\\");
		fileChooser.setDialogTitle("Import SML Model");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SML Model (*.sml, *.sml2)", "sml", "sml2");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			if (fileChooser.getFileFilter() == filter)
			{
				try
				{
					ResourceSet resourceSet = new ResourceSetImpl();
					resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,new OLEDResourceFactory());
					resourceSet.getPackageRegistry().put(sml2.Sml2Package.eNS_URI, sml2.Sml2Package.eINSTANCE);
					
					File ecoreFile = new File(fileChooser.getSelectedFile().getPath());					
					URI fileURI = URI.createFileURI(ecoreFile.getAbsolutePath());		
					Resource resource = resourceSet.createResource(fileURI);		
					resource.load(Collections.emptyMap());
					
					sml_model = (sml2.SMLModel)resource.getContents().get(0);
					
				} catch (Exception ex)
				{
					JOptionPane.showMessageDialog(this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		return sml_model;
	}
}
