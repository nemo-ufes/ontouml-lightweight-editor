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
package br.ufes.inf.nemo.oled;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Locale;
import java.util.jar.Manifest;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import br.ufes.inf.nemo.common.file.TimeHelper;
import br.ufes.inf.nemo.oled.util.BinaryLoader;
import br.ufes.inf.nemo.oled.util.ExtractorUtil;
import br.ufes.inf.nemo.oled.util.LoadingException;

/**
 * This is the start class of the OLED application. OLED is based on the TinyUML project by Wei-ju Wu.
 * It also benefits from MIT's project called Alloy developed by Daniel Jackson (see http://alloy.mit.edu/)
 * 
 * @author John Guerson
 */
public final class Main {
	
	public static AppFrame frame; 

	public static String OLED_VERSION = "1.0.06"; 
	public static String OLED_COMPILATION_DATE = "Fev 02 2015";	
	
	public static boolean USE_LOG_FILE = false;
	public static PrintStream psOut;
	public static PrintStream psErr;
	
	/** This caches the result of the call to get all fonts. */
	private static String[] allFonts = null;
	    	   
	/**
	 * Private constructor.
	 */
	private Main() { }

	/** Returns true iff running on Windows **/
	public static boolean onWindows() {
      return System.getProperty("os.name").toLowerCase(Locale.US).startsWith("windows");
	};

	public static String getOSx(){ 
		if (onWindows()) return "win"; else if (onMac()) return "mac"; else return "linux"; 
	}
			
	public static String getArch() 
	{ 
		String osArch = System.getProperty("os.arch").toLowerCase();
		String arch = osArch.contains("64") ? "x64" : "x86";                
        return arch;
	}
	
	/** Returns true iff running on Mac OS X. **/
	public static boolean onMac() {
      return System.getProperty("mrj.version")!=null || System.getProperty("os.name").toLowerCase(Locale.US).startsWith("mac ");                                     
	}	
	
	/** Returns true if a font with that name exists on the system (comparison is case-insensitive). */
	public synchronized static boolean hasFont(String fontname) {
		if (allFonts == null) allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for(int i = 0; i < allFonts.length; i++) if (fontname.compareToIgnoreCase(allFonts[i]) == 0) return true;
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static void setUIFont(FontUIResource f) 
	{
       Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }
	    
	/** Asks the user to choose a font; returns "" if the user cancels the request. */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized static String askFont() {
		if (allFonts == null) allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		JComboBox jcombo = new JComboBox(allFonts);
		Object ans = show("Font", JOptionPane.INFORMATION_MESSAGE,
				new Object[] {"Please choose the new font:", jcombo}, new Object[] {"Ok", "Cancel"}, "Cancel"
				);
		Object value = jcombo.getSelectedItem();
		if (ans=="Ok" && (value instanceof String)) return (String)value; else return "";
	}
	
	/** Helper method for constructing an always-on-top modal dialog. */
	private static Object show(String title, int type, Object message, Object[] options, Object initialOption) {
		if (options == null) { options = new Object[]{"Ok"};  initialOption = "Ok"; }
		JOptionPane p = new JOptionPane(message, type, JOptionPane.DEFAULT_OPTION, null, options, initialOption);
		p.setInitialValue(initialOption);
		JDialog d = p.createDialog(null, title);
		p.selectInitialValue();
		d.setAlwaysOnTop(true);
		d.setVisible(true);
		d.dispose();
		return p.getValue();
	}
	
	/** Set System properties according to each Operating System */
	public static void setSystemProperties() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		//Needed for the embedded SWT Browser in Linux systems
		System.setProperty("sun.awt.xembedserver", "true");		
		// Enable better look-and-feel
        if (onMac() || onWindows()) {
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "OLED");
            System.setProperty("com.apple.mrj.application.growbox.intrudes","true");
            System.setProperty("com.apple.mrj.application.live-resize","true");
            System.setProperty("com.apple.macos.useScreenMenuBar","true");
            System.setProperty("apple.laf.useScreenMenuBar","true");                        
            System.setProperty("com.apple.eawt.CocoaComponent.CompatibilityMode","false");
            System.setProperty("apple.awt.fileDialogForDirectories", "true");
        }        
		
        if(onMac()){
        	System.setProperty("org.eclipse.emf.ecore.EPackage.Registry.INSTANCE", "org.eclipse.emf.ecore.impl.EPackageRegistryImpl");
        }else{
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());	
        }
        
		//if (!onMac()&&!onWindows()) UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");		
		UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0));
	
		UIManager.put("OptionPane.yesButtonText","Yes");
		UIManager.put("OptionPane.noButtonText", "No");
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		UIManager.put("OptionPane.titleText","Title");
	}

	/** Choose the appropriate Font according to the operating system */
	public static void chooseFont()
	{
	   // Choose the appropriate font		
	   int fontSize=11;
       String fontName="Tahoma";
       while(true) {
           if (!hasFont(fontName)) { fontName="Arial"; fontSize = 11;} else break; 
           if (!hasFont(fontName)) { fontName="Calibri"; fontSize=11; } else break; 
           if (!hasFont(fontName)) { fontName="Courier New"; } else break; 
           if (!hasFont(fontName)) { fontName="Lucida Grande"; fontSize=10; } 
           break;
       }			        
       setUIFont(new FontUIResource(new Font(fontName, Font.PLAIN, fontSize)));
	}
	
	/** For now, useless. */
	public static Manifest getManifest() throws IOException
	{
		Class<?> clazz = Main.class;
		String className = clazz.getSimpleName() + ".class";
		String classPath = clazz.getResource(className).toString();
		if (!classPath.startsWith("jar")) {
			// Class not from JAR
			return null;
		}else{
			String manifestPath = classPath.substring(0, classPath.lastIndexOf("!") + 1) +"/META-INF/MANIFEST.MF";
			return new Manifest(new URL(manifestPath).openStream());
		}
	}
			
	/** SWT working directory. 
	 *  At runtime this is the jar's directory, otherwise if at development in eclipse this is the path of the swt library folder. */
	public static String getSwtWorkingDir()
	{
		String dir = System.getProperty("user.dir");
		if (dir.contains("br.ufes.inf.nemo.oled")) dir = dir.replace("br.ufes.inf.nemo.oled","org.eclipse.swt").concat(File.separator).concat("src"+File.separator);			
		else dir = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();		
		return dir;
	}
	
	/** SWT working directory. 
	 *  At runtime this is the jar's directory, otherwise if at development in eclipse this is the path of the swt library folder. */
	public static String getSwtWorkingDir2()
	{
		String dir = System.getProperty("user.dir");
		if (dir.contains("br.ufes.inf.nemo.oled")) dir = dir.replace("br.ufes.inf.nemo.oled","org.eclipse.swt").concat(File.separator).concat("src"+File.separator);			
		else dir = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();		
		return dir;
	}
	
	/** SWT file name according to the architecture and the operation system. */
	public static String getSwtFileName()
	{
		return "swt"+File.separator+getOSx()+File.separator+getArch()+File.separator+"swt.jar";        		
	}
	
	/** SWT file name according to the architecture and the operation system. */
	public static String getSwtFileName2()
	{
		return "swt-"+getOSx()+"-"+getArch()+".jar";    	
	}
	
	/** Add the correct SWT Jar to the classpath according to the Operating System*/
	public static URL addSwtJarToClassPath() 
	{
		String swtFileName = "<empty>";
	    try {	    	
	        swtFileName = getSwtFileName2();	        	        	        
	        URLClassLoader classLoader = (URLClassLoader) Main.class.getClassLoader();
	        Method addUrlMethod = URLClassLoader.class.getDeclaredMethod ("addURL", URL.class);
	        addUrlMethod.setAccessible (true);	        	        
	        URL swtFileUrl = null;
	        try{
	        	swtFileUrl = new URL("rsrc:"+swtFileName);
	        }catch(MalformedURLException e){
	        	String workingDir = getSwtWorkingDir2();		        
	        	File file = new File(workingDir.concat(swtFileName));
	        	swtFileUrl = file.toURI().toURL();
	        	if (!file.exists ()) Main.printErrLine("Can't locate SWT Jar File" + file.getAbsolutePath());
	    	}	    	
	        Main.printOutLine("Adding to classpath: " + swtFileUrl);            
            addUrlMethod.invoke (classLoader, swtFileUrl);		
            
            return swtFileUrl;
	    }
	    catch(Exception e) {
	        System.err.println("Unable to add the swt jar to the class path: "+swtFileName);
	        e.printStackTrace();
	    }	    
	    return null;
	}

	/** Load the correct SWT Jar to the classpath according to the OS*/
	@SuppressWarnings({ "unchecked", "unused", "rawtypes", "resource" })
	public static void loadSwtJar(URL[] swtJarURLs){		
		try {			
			ClassLoader parent = Main.class.getClassLoader();			
			ClassLoader cl = new URLClassLoader(swtJarURLs,parent);
			Class classToLoad=null;
			try {
				classToLoad = cl.loadClass("org.eclipse.swt.widgets.Display");
				if(classToLoad!=null) Main.printOutLine("SWT loaded from org.eclipse.swt.widgets.Display in "+swtJarURLs[0]);						        
			} catch (ClassNotFoundException exx) {
				Main.printErrLine("Launch failed: Failed to load SWT class from jar: " + swtJarURLs[0]);
				throw new RuntimeException(exx);
			}
			Method method = classToLoad.getDeclaredMethod ("getDefault");
			Object instance = classToLoad.newInstance();
			Object result = method.invoke (instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Load the SWT binaries (*.dlls, *.jnilib, *.so) according to the appropriate Operating System.  */
	public static BinaryLoader copyBinaryFilesTo(String binTemp) throws LoadingException, URISyntaxException
	{
		BinaryLoader loader = new BinaryLoader(null, getOSx(), getArch(), binTemp);
		loader.extractSWTBinaryFiles();
		loader.addBinariesToJavaPathBySystem();
		return loader;
	}
	
	/** Add and load the appropriate SWT jar to the classpath according to the operating system. */
	public static void loadAppropriateSwtJar() throws LoadingException, URISyntaxException
	{
		//add and load the appropriate SWT jar to the classpath according to the OS
		final URL[] urls = new URL[1];
		URL swtJarURL = addSwtJarToClassPath();
        urls[0] = swtJarURL;
        if(onMac()){
	        com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute( new Runnable(){        	
				@Override
				public void run() {
					loadSwtJar(urls);
				}
			});        
        }else{
        	loadSwtJar(urls);
        }
	}
	
	private static void redirectSystemToALog() throws SecurityException, IOException 
	{
		 // initialize logging to go to rolling log file
        LogManager logManager = LogManager.getLogManager();
        logManager.reset();

        File file = new File("oled.log");
        if(file.exists()) file.delete();
        
        // log file max size 10M, 1 rolling files, append-on-open
        Handler fileHandler = new FileHandler("oled.log", 10000000, 1, true);
        SimpleFormatter formatter = new SimpleFormatter();        
        fileHandler.setFormatter(formatter);
        Logger.getLogger("").addHandler(fileHandler);        

        // preserve old stdout/stderr streams in case they might be useful
        //PrintStream stdout = System.out;
        //PrintStream stderr = System.err;
	
        // now rebind stdout/stderr to logger
        Logger logger;
        
        logger = Logger.getLogger("stdout");
        LoggingOutputStream losOut = new LoggingOutputStream(logger, StdOutErrLevel.STDOUT);
        psOut = new PrintStream(losOut, true);
        System.setOut(psOut);
	
        logger = Logger.getLogger("stderr");
        LoggingOutputStream losErr = new LoggingOutputStream(logger, StdOutErrLevel.STDERR);
        psErr = new PrintStream(losErr, true);
        System.setErr(psErr);
    }
	
	public static void printOut(String msg)
	{
		Object[] array = msg.split("\n");
		for(Object obj: array){
			if(!USE_LOG_FILE){
				System.out.print(TimeHelper.getTime()+" - "+obj);
			}else{
				System.out.print(obj);
			}
		}
	}

	public static void printErr(String msg)
	{
		Object[] array = msg.split("\n");
		for(Object obj: array){
			if(!USE_LOG_FILE){
				System.err.print(TimeHelper.getTime()+" - "+obj);
			}else{
				System.err.print(obj);
			}
		}
	}
	
	public static void printErrLine(String msg)
	{
		Object[] array = msg.split("\n");
		for(Object obj: array){
			if(!USE_LOG_FILE){
				System.err.println(TimeHelper.getTime()+" - "+obj);
			}else{
				System.err.println(obj);
			}
		}
	}
	
	public static void printOutLine(String msg)
	{
		Object[] array = msg.split("\n");
		for(Object obj: array){
			if(!USE_LOG_FILE){
				System.out.println(TimeHelper.getTime()+" - "+obj);
			}else{
				System.out.println(obj);
			}	
		}		
	}
	
	/**  
	 * The start method for this application.
	 * @param args the command line parameters
	 */
	public static void main(final String[] args) 
	{		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					if(USE_LOG_FILE) redirectSystemToALog();
					setSystemProperties();				
					chooseFont();
					frame = new AppFrame();					
					loadAppropriateSwtJar();
					copyBinaryFilesTo("oled_bin");
					ExtractorUtil.extractAlloyJar();
					frame.setLocationByPlatform(true);
					frame.setVisible(true);
					frame.toFront();										
				} catch (Exception ex) {
					ex.printStackTrace();
				}				
			}
		});		
	}
}
