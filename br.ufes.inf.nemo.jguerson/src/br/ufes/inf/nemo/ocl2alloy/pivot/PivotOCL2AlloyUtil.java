package br.ufes.inf.nemo.ocl2alloy.pivot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class PivotOCL2AlloyUtil {

	public static Resource readOntoUML (String refontoumlpath) throws IOException
	{
		ResourceSet rset = new ResourceSetImpl();					
			
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("refontouml",new XMIResourceFactoryImpl());
		
		rset.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,	RefOntoUML.RefOntoUMLPackage.eINSTANCE);		
		
	    File file = new File(refontoumlpath);
		URI fileURI = URI.createFileURI(file.getAbsolutePath());		
		Resource resource = rset.createResource(fileURI);		
		
		resource.load(Collections.emptyMap());
		
		return resource;		
	}
	
	public static Resource saveOntoUML (String refontoumlpath, RefOntoUML.Package refmodel) 
	{
		ResourceSet rset = new ResourceSetImpl();					
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("refontouml",new XMIResourceFactoryImpl());
		
		rset.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,	RefOntoUML.RefOntoUMLPackage.eINSTANCE);
    	
		URI fileURI = URI.createFileURI(refontoumlpath);    	
	    final Resource resource = rset.createResource(fileURI);    	
	    resource.getContents().add(refmodel);    	
	
	    try{
	    	resource.save(Collections.emptyMap());
	    }catch(IOException e){
	    	e.printStackTrace();
	    }
	    
	    return resource;		   	
	}
	
	public static String readFile (String filePath) throws IOException
	{
		String result = new String();
		
		FileInputStream fstream = new FileInputStream(filePath);			
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;			
		while ((strLine = br.readLine()) != null)   
		{
			result += strLine+"\n";
		}
		in.close();		
		return result;
	}
    		
    public static File createFile (String path) 
    {    	
		File file = new File(path);		
		if (!file.exists()) {			
			try{
				file.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return file;
	}
    
    /** Add content to the end of the file */
    public static void appendToFile (String content, String filePath) throws IOException
	{
		File file = createFile(filePath);
		FileWriter fw = new FileWriter(file,true);
		PrintWriter pWriter = new PrintWriter(new BufferedWriter(fw));		
		pWriter.println(content);		
		pWriter.close();
		fw.close();						
	}

    /** It empties the original file content*/ 
	public static void writeOverTheFile(String content, String FilePath) throws IOException
	{
		FileWriter fstream = new FileWriter(FilePath);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);
		out.close();
	}
	
	/** This is probably the path of the folder "MyDocuments" */
	public static String getDefaultDirectoryPath() 
	{
		JFileChooser fr = new JFileChooser();
	    FileSystemView fw = fr.getFileSystemView();
	    return fw.getDefaultDirectory().getAbsolutePath();
	}
}
