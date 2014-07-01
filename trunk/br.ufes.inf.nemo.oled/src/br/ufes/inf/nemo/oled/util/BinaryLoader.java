/***************************************************************************
 * SWT Autoloader - everything in a jar                                    *
 * Copyright (C) 2005 by Silvio Moioli                                     *
 * silvio@moioli.net                                                       * 
 *                                                                         *
 * A brief tutorial on how to use this class is provided in the readme     *
 * file included in the distribution package.                              *
 * Use of this software is subject to the terms in the LICENSE.txt file    *
 ***************************************************************************/
package br.ufes.inf.nemo.oled.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.swt.widgets.Display;

import br.ufes.inf.nemo.oled.Main;

/**
 * 
 * Enables SWT auto-loading from the jar file. A brief tutorial on how to use
 * this class is provided in the readme file included in the distribution
 * package.
 * 
 * @author Silvio Moioli
 * @version 1.0
 */
public class BinaryLoader {

	public File BinDir = null;
	public File jarFile = null;
	public String osx = "undef";
	public String arch = "undef";
	public String binTemp="oled_bin";
	
	public File getJarFile() {
		return jarFile;
	}
		
	/**
	 * Constructs a new loader.
	 * 
	 * @param jarName
	 *            the name of the jar file which contains this class.
	 * @throws URISyntaxException 
	 */
	public BinaryLoader(String jarName, String osx, String arch, String binTemp) throws URISyntaxException {
		this.osx = osx;
		this.binTemp = binTemp;
		this.arch = arch;
		if (jarName!=null  && !jarName.isEmpty()) 
		{		
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();		
			String fileName = path+jarName;		
			try{
				String decodedPath = URLDecoder.decode(fileName, "UTF-8");			
			    jarFile = new File(decodedPath);
			    if(!jarFile.exists()) return;
			}
			catch(UnsupportedEncodingException cantHappen){ /*Never gets here*/ }
			Main.printOutLine("Jar file: "+jarFile.getAbsolutePath());
		}
	}
	
	public File getOLEDBin() throws LoadingException 
	{
		if (BinDir == null) {			
			String temp = System.getProperty("java.io.tmpdir");			
			if (temp == null || temp.length() == 0) System.out.println("Error. JVM need to specify a temporary directory using java.io.tmpdir property.");			
			//String username = System.getProperty("user.name");
			File tempfile = new File(temp + File.separatorChar + binTemp);			
			tempfile.mkdirs();
			String ans = ConfigurationHelper.canon(tempfile.getPath());			
			if (!tempfile.isDirectory()) System.out.println("Error. Cannot create the temporary directory "	+ ans);
			if (!ConfigurationHelper.onWindows()) {
				String[] args = { "chmod", "700", ans };
				try {
					Runtime.getRuntime().exec(args).waitFor();
				} catch (Throwable ex) {
				} // We only intend to make a best effort.
			}				
			BinDir = tempfile.getAbsoluteFile();
		}
		return BinDir;
	}
	
	public void addBinariesToJavaPathBySystem()
	{
        try {
            System.setProperty("java.library.path", getOLEDBin().getAbsolutePath());
            // The above line is actually useless on Sun JDK/JRE (see Sun's bug ID 4280189)
            // The following 4 lines should work for Sun's JDK/JRE (though they probably won't work for others)
            String[] newarray = new String[]{getOLEDBin().getAbsolutePath()};
            java.lang.reflect.Field old = ClassLoader.class.getDeclaredField("usr_paths");
            old.setAccessible(true);
            old.set(null,newarray);
        } catch (Throwable ex) { }
	}
	
	public void extractSWTBinaryFiles()
	{
		try {
			if(osx=="win"&& arch=="x64"){		
				doExtraction("swt-awt-win32-4332-x64.dll");
				doExtraction("swt-gdip-win32-4332-x64.dll");								
				doExtraction("swt-wgl-win32-4332-x64.dll");
				doExtraction("swt-win32-4332-x64.dll");
				doExtraction("swt-xulrunner-win32-4332-x64.dll");
			}
			if(osx=="win" && arch=="x86"){
				doExtraction("swt-awt-win32-4332-x86.dll");
				doExtraction("swt-gdip-win32-4332-x86.dll");								
				doExtraction("swt-wgl-win32-4332-x86.dll");
				doExtraction("swt-win32-4332-x86.dll");
				doExtraction("swt-xulrunner-win32-4332-x86.dll");
				doExtraction("swt-webkit-win32-4332-x86.dll");
			}
			if(osx=="mac" && arch=="x86"){
				doExtraction("libswt-awt-cocoa-4332-x86.jnilib");
				doExtraction("libswt-cocoa-4332-x86.jnilib");				
				doExtraction("libswt-pi-cocoa-4332-x86.jnilib");
				doExtraction("libswt-xulrunner-cocoa-4332-x86.jnilib");				
			}
			if(osx=="mac" && arch=="x64"){
				doExtraction("libswt-awt-cocoa-4332-x64.jnilib");
				doExtraction("libswt-cocoa-4332-x64.jnilib");				
				doExtraction("libswt-pi-cocoa-4332-x64.jnilib");
				doExtraction("libswt-xulrunner-cocoa-4332-x64.jnilib");				
			}
			if(osx=="linux" && arch=="x64"){
				doExtraction("libswt-atk-gtk-4332-x64.so");
				doExtraction("libswt-awt-gtk-4332-x64.so");				
				doExtraction("libswt-cairo-gtk-4332-x64.so");
				doExtraction("libswt-glx-gtk-4332-x64.so");				
				doExtraction("libswt-gnome-gtk-4332-x64.so");
				doExtraction("libswt-gtk-4332-x64.so");
				doExtraction("libswt-mozilla-gtk-4332-x64.so");
				doExtraction("libswt-pi-gtk-4332-x64.so");
				doExtraction("libswt-pi3-gtk-4332-x64.so");
				doExtraction("libswt-webkit-gtk-4332-x64.so");
				doExtraction("libswt-xpcominit-gtk-4332-x64.so");
				doExtraction("libswt-xulrunner-fix-x64.so");
				doExtraction("libswt-xulrunner-gtk-4332-x64.so");
			}
			if(osx=="linux" && arch=="x86"){
				doExtraction("libswt-atk-gtk-4332-x86.so");
				doExtraction("libswt-awt-gtk-4332-x86.so");				
				doExtraction("libswt-cairo-gtk-4332-x86.so");
				doExtraction("libswt-glx-gtk-4332-x86.so");				
				doExtraction("libswt-gnome-gtk-4332-x86.so");
				doExtraction("libswt-gtk-4332-x86.so");
				doExtraction("libswt-mozilla-gtk-4332-x86.so");
				doExtraction("libswt-pi-gtk-4332-x86.so");
				doExtraction("libswt-pi3-gtk-4332-x86.so");
				doExtraction("libswt-webkit-gtk-4332-x86.so");
				doExtraction("libswt-xpcominit-gtk-4332-x86.so");
				doExtraction("libswt-xulrunner-fix-x86.so");
				doExtraction("libswt-xulrunner-gtk-4332-x86.so");
			}
			
		} catch (LoadingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** More specific: Particularly to SWT libraries in the project org.eclipse.swt. */
	private String doExtraction(String binName) throws LoadingException, IOException
	{
		//String binPackage = "swt"+File.separator+osx+File.separator+arch+File.separator;
		String binPackage = "";
		
		String outFolder = getOLEDBin().getAbsolutePath()+File.separator+binName;		
		String outPath = URLDecoder.decode(outFolder, "UTF-8");		
		File outFile = new File(outPath);
		if (outFile.exists()) return outFile.getAbsolutePath();
				 
		// Copy 		
		String binResource = URLDecoder.decode(binPackage+binName,"UTF-8");
		String binWorkingDir = URLDecoder.decode(getBinWorkingDir()+binPackage+File.separator+binName,"UTF-8");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(binResource);	
		if(is==null) is = this.getClass().getClassLoader().getResourceAsStream(File.separator+binResource);	
		if(is !=null) Main.printOutLine("Reading from: "+binResource);
		if(is ==null) Main.printOutLine("Reading from: "+binWorkingDir);
		if(is == null) is = new FileInputStream(binWorkingDir);
		
		OutputStream out = new FileOutputStream(outFile);
				
		// copy data flow -> MB x MB
		byte[] src = new byte[1024];
		int read = 0;
		while ((read = is.read(src)) != -1){
			out.write(src, 0, read);
		}
		is.close();
		out.flush();
		out.close();
		
		Main.printOutLine("Extracted: "+outFile.getAbsolutePath());
		return outFile.getAbsolutePath();

	}

	@SuppressWarnings("unused")
	public void addBinariesToJavaPathByCmd() throws LoadingException {		
		try {			
			if (isInstalled() == false) 
			{				
				if (!jarFile.exists()) return ;
				String[] command = {"java", "-Djava.library.path=" + getOLEDBin(), "-jar", getJarFile().getName()};
				String[] environment = null;
				String workPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
				String decodedPath = URLDecoder.decode(workPath, "UTF-8");
				File workDir = new File(decodedPath);				
				Main.printOutLine("Working Dir: "+workDir.getAbsolutePath());
				Process p = Runtime.getRuntime().exec(command, environment, workDir);
				// Implementation to get the standard output, requires two VMs
				// running
				/*
				 * BufferedInputStream i = new
				 * BufferedInputStream(p.getInputStream()); while(true){ try{
				 * System.exit(p.exitValue()); }
				 * catch(IllegalThreadStateException e){ //Spawned VM is still
				 * running, wait some time and continue } if (i.available() !=
				 * 0){ System.out.write(i.read()); System.out.flush(); } }
				 */
				Runtime.getRuntime().exit(0);
			}
		} catch (IOException e) {
			throw new LoadingException(e);
		}
	}

	/**
	 * Extracts the SWT native files in a temporary directory.
	 */
	@SuppressWarnings("rawtypes")
	public File extractBinaryFiles() throws LoadingException {		
		if (jarFile==null) return null;
		if (!jarFile.exists()) return null;		
		File outputDir = getOLEDBin();
		try {
			File currentArchive = getJarFile();			

			ZipFile zf = new ZipFile(currentArchive);
			Enumeration entries = zf.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String pathname = entry.getName();				
				if(osx=="win" && pathname.endsWith(".dll")){
					doExtraction(outputDir,zf,entry,pathname);
				}
				if(osx=="linux" && pathname.endsWith(".so")){
					doExtraction(outputDir,zf,entry,pathname);
				}
				if(osx=="mac" &&  pathname.endsWith(".jnilib")){
					doExtraction(outputDir,zf,entry,pathname);					
				}
			}
			zf.close();
		} catch (ZipException e) {
			throw new LoadingException(e);
		} catch (IOException e) {
			throw new LoadingException(e);
		} catch (IllegalStateException e) {
			throw new LoadingException(e);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		addBinariesToJavaPathBySystem();		
		return outputDir;
	}

	/** More general: for each entry in the Jar file */
	private void doExtraction(File outputDir, ZipFile zf, ZipEntry entry, String pathname) throws Exception
	{
		// eliminates parent packages of pathname
		Path p = Paths.get(pathname);
		String outfileName = p.getFileName().toString();		
		InputStream in = zf.getInputStream(entry);
		File outFile = new File(outputDir, outfileName);		
		if (outFile.exists()) return;
		FileOutputStream out = new FileOutputStream(outFile);
		byte[] buf = new byte[1024];
		while (true) {
			int nRead = in.read(buf, 0, buf.length);
			if (nRead <= 0)
				break;
			out.write(buf, 0, nRead);
		}
		in.close();
		out.close();
		Main.printOutLine("Extracted: "+outFile.getAbsolutePath());
	}	
	
	public String getBinWorkingDir()
	{
		String dir = System.getProperty("user.dir");
		if (dir.contains("br.ufes.inf.nemo.oled")) 
			dir = dir.replace("br.ufes.inf.nemo.oled","org.eclipse.swt").concat(File.separator).concat("src"+File.separator);
		else
			dir = "";
		return dir;
	}
	
	/**
	 * Checks if SWT isn't already loaded.
	 * 
	 * @return true if SWT is loaded.
	 */
	public boolean isInstalled() {
		try {
			Display.getDefault().syncExec(new Runnable() {
			    public void run() {
			    	Display display = Display.getDefault();	 
			    	display.dispose();
			    }
			});
		} catch (UnsatisfiedLinkError e) {
			return false;
		}
		return true;
	}
}
