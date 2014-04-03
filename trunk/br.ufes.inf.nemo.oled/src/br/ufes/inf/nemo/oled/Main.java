package br.ufes.inf.nemo.oled;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Locale;
import java.util.jar.Manifest;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import br.ufes.inf.nemo.oled.util.BinaryLoader;
import br.ufes.inf.nemo.oled.util.ExtractorUtil;
import br.ufes.inf.nemo.oled.util.LoadingException;

/**
 * This is the start class of the OLED application. OLED is based on the TinyUML project by Wei-ju Wu.
 * It also benefits from MIT's project called Alloy developed by Daniel Jackson (see http://alloy.mit.edu/)
 */
public final class Main {
	
	public static AppFrame frame; 

	public static String OLED_VERSION = "0.9.15"; //Build: 02-04-2014
	
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
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
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
	
	/** Makes System.out content to be printed in the output pane of the app. */
	public static void redirectSystemOut()
	{
		frame.createSysOutInterceptor();
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
	        	if (!file.exists ()) System.err.println("Can't locate SWT Jar File" + file.getAbsolutePath());
	    	}	    	
            System.out.println("Adding to classpath: " + swtFileUrl);            
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
				if(classToLoad!=null) System.out.println("SWT loaded from org.eclipse.swt.widgets.Display in "+swtJarURLs[0]);						        
			} catch (ClassNotFoundException exx) {
				System.err.println("Launch failed: Failed to load SWT class from jar: " + swtJarURLs[0]);
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
	public static BinaryLoader loadBinaryFiles(String binTemp) throws LoadingException, URISyntaxException
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
		
	/**  
	 * The start method for this application.
	 * @param args the command line parameters
	 */
	public static void main(final String[] args) 
	{				
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {										
					setSystemProperties();				
					chooseFont();	
					frame = new AppFrame();	
					loadAppropriateSwtJar();
					loadBinaryFiles("oled_bin");
					ExtractorUtil.extractAlloyJar();
					frame.setLocationByPlatform(true);
					frame.setVisible(true);
					frame.toFront();
					//redirectSystemout();					
				} catch (Exception ex) {
					ex.printStackTrace();
				}				
			}
		});		
	}
}
