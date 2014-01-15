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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.swt.widgets.Display;

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

	/**
	 * Constructs a new loader.
	 * 
	 * @param jarName
	 *            the name of the jar file which contains this class.
	 */
	public BinaryLoader(String jarName) {
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();			
		String fileName = path+jarName;
		
		try{
			String decodedPath = URLDecoder.decode(fileName, "UTF-8");
			
		    jarFile = new File(decodedPath);
		    if(!jarFile.exists()) return;
		}
		catch(UnsupportedEncodingException cantHappen){
			//Never gets here
		}
		System.out.println("Jar file: "+jarFile.getAbsolutePath());
	}

	@SuppressWarnings("unused")
	public void addBinariesToJavaPath() throws LoadingException {		
		try {			
			if (isInstalled() == false) {
				
				if (!jarFile.exists()) return ;
										        
				String[] command = {"java", "-Djava.library.path=" + getOLEDBin(), "-jar", getJarFile().getName()};
				String[] environment = null;
				String workPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
				String decodedPath = URLDecoder.decode(workPath, "UTF-8");
				File workDir = new File(decodedPath);
				
				System.out.println("Working Dir: "+workDir.getAbsolutePath());
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
	 * @return Returns the jarFile.
	 */
	public File getJarFile() {
		return jarFile;
	}

	public void addBinariesToJavaPath2()
	{
		// Add the new JNI location to the java.library.path
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
	
	public File getOLEDBin() throws LoadingException 
	{
		if (BinDir == null) {
			
			String temp = System.getProperty("java.io.tmpdir");
			
			if (temp == null || temp.length() == 0)
				System.out.println("Error. JVM need to specify a temporary directory using java.io.tmpdir property.");
			
			//String username = System.getProperty("user.name");
			File tempfile = new File(temp + File.separatorChar + "oled_bin");
			
			tempfile.mkdirs();
			String ans = ConfigurationHelper.canon(tempfile.getPath());
			
			if (!tempfile.isDirectory()) {
				System.out.println("Error. Cannot create the temporary directory "	+ ans);
			}
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

	/**
	 * Extracts the SWT native files in a temporary directory.
	 */
	@SuppressWarnings("rawtypes")
	public File extractBinaryFiles(String osx) throws LoadingException {
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
		return outputDir;
	}

	public void doExtraction(File outputDir, ZipFile zf, ZipEntry entry, String pathname) throws Exception
	{
		// eliminates parent packages of pathname
		Path p = Paths.get(pathname);
		String outfileName = p.getFileName().toString(); 
		InputStream in = zf.getInputStream(entry);
		File outFile = new File(outputDir, outfileName);
		outFile.deleteOnExit();
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
		//System.out.println("Extracted: "+outFile.getAbsolutePath());
	}
	/**
	 * Checks if SWT isn't already loaded.
	 * 
	 * @return true if SWT is loaded.
	 */
	public boolean isInstalled() {
		try {
			Display.getDefault().dispose();
		} catch (UnsatisfiedLinkError e) {
			return false;
		}
		return true;
	}
}
