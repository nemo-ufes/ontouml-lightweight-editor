package br.ufes.inf.nemo.sml2alloy.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EValidator.SubstitutionLabelProvider;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;

import sml2.Sml2Package;
import sml2.util.Sml2SubstitutionLabelProvider;

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
	
	public Object[] verifySyntax(sml2.SMLModel smlmodel)
	{		
		String result = "";
		Diagnostician verificator = Diagnostician.INSTANCE;
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put(SubstitutionLabelProvider.class, new Sml2SubstitutionLabelProvider());
		BasicDiagnostic diag = new BasicDiagnostic();				
		long validationStartMilis = System.currentTimeMillis();
		boolean verification = verificator.validate(smlmodel, diag, context);
		long verificationEndMilis = System.currentTimeMillis();		
		if(!verification)
		{
			result += "The model is not valid syntactically. The following error(s) were found:\n\n";			
			for (Diagnostic item : diag.getChildren()) 
			{				
				result += item.getMessage()+"\n\n";
			}
		}		
		result += MessageFormat.format("Model verified in {0} ms, {1} error(s) found", (verificationEndMilis - validationStartMilis),  diag.getChildren().size());
		
		return new Object[] {verification, result};
	}
	
	public void printConstraints() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		for (Field field : Sml2Package.Literals.class.getDeclaredFields())
		{
			if (field.getType().equals(EClass.class))
			{
				Method m = field.getType().getMethod("getEAnnotation", String.class);
				Object ocl = m.invoke(field.get(Sml2Package.eINSTANCE), OCL_ANNOTATION_SOURCE);
				Object comments = m.invoke(field.get(Sml2Package.eINSTANCE), "Comments");
				if (ocl != null)
				{
					for (Map.Entry<String, String> entry : ((EAnnotation)ocl).getDetails())
					{
						String constName = entry.getKey();
						String expr = entry.getValue();
						
						System.out.println(((EAnnotation)comments).getDetails().get(constName));
						System.out.println("context "+field.getName()+" inv\n"+expr);
						System.out.println();
					}
				}
			}
		}
//		EAnnotation ocl = Sml2Package.Literals.FORMAL_LINK.getEAnnotation(OCL_ANNOTATION_SOURCE);
//		String expr = ocl.getDetails().get("sameEndsAsType2");
	}
	
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
}
