package br.ufes.inf.nemo.oled.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import edu.mit.csail.sdg.alloy4.Util;

/**
 * A helper class which provides settings and file management facilities. 
 * @author Antognoni Albuquerque
 */
public final class ConfigurationHelper {
	
	private static String OLED_HOME = null;
	private static Properties propertiesStore;
	
	private ConfigurationHelper() { }
		
	public static boolean saveProperties()
	{
		if(propertiesStore != null)
		{
			File file = new File(getCanonPath(getOLEDHome(), OLEDSettings.OLED_SETTINGS_FILE.getValue()));
			try {
				FileOutputStream out = new FileOutputStream(file);
				propertiesStore.storeToXML(out, "OLED Configuration File", "UTF-8");
				close(out);
				return true;
			} catch (Exception ex) {
			}
		}
		
		return false;
	}
	
	public static Properties getProperties()
	{
		if(propertiesStore != null)
			return propertiesStore;
		
		propertiesStore = new Properties();
		
		File file = new File(getCanonPath(getOLEDHome(), OLEDSettings.OLED_SETTINGS_FILE.getValue()));
		if(file.exists())
		{
			try {
				FileInputStream in = new FileInputStream(file);
				propertiesStore.loadFromXML(in);
				close(in);
			} catch (Exception ex) {
			}
		}

		return propertiesStore;
	}

	public static void addRecentProject(String path)
	{
		if(!OLEDSettings.RECENT_PROJECT_1.getValue().equals(path))
		{
			int histSize = 5;
		
			for (int i = histSize-1; i > 0; i--) {
				OLEDSettings setting = OLEDSettings.valueOf("RECENT_PROJECT_" + i); 
				OLEDSettings nextSetting = OLEDSettings.valueOf("RECENT_PROJECT_" + (i + 1));
				nextSetting.setValue(setting.getValue());
			}
			
			OLEDSettings.RECENT_PROJECT_1.setValue(path);
			saveProperties();
		}
	}
	
	public static String[] getRecentProjects()
	{
		int histSize = 5;
		String[] ans = new String[histSize];
		
		for (int i = 1; i < histSize; i++) {
			ans[i] = OLEDSettings.valueOf("RECENT_PROJECT_" + i).getValue();
		}
		
		return ans;
	}
	
	public static String getSimpleFileName(String filePath)
	{
		return new File(filePath).getName();
	}
    /** 
     * Create an empty temporary directory for use, designate it "deleteOnExit", then return it.
     * It is guaranteed to be a canonical absolute path. 
     */
    public static String makeTempDir() {
        Random r=new Random(new Date().getTime());
        while(true) {
            int i=r.nextInt(1000000);
            String dest = getOLEDHome() + File.separatorChar+i;
            File f=new File(dest);
            if (f.mkdirs()) {
                f.deleteOnExit();
                return Util.canon(dest);
            }
        }
    }
	
	public static String getCanonPath(String dir, String fileName)
	{
		return canon(dir + File.separatorChar + fileName);
	}
	
	public static String getOLEDHome() {

		if (OLED_HOME != null)
			return OLED_HOME;
		
		String temp = System.getProperty("java.io.tmpdir");
		
		if (temp == null || temp.length() == 0)
			System.out.println("Error. JVM need to specify a temporary directory using java.io.tmpdir property.");
		
		//String username = System.getProperty("user.name");
		File tempfile = new File(temp + File.separatorChar + "oled_tmp");
		
		tempfile.mkdirs();
		String ans = canon(tempfile.getPath());
		
		if (!tempfile.isDirectory()) {
			System.out.println("Error. Cannot create the temporary directory "	+ ans);
		}
		if (!onWindows()) {
			String[] args = { "chmod", "700", ans };
			try {
				Runtime.getRuntime().exec(args).waitFor();
			} catch (Throwable ex) {
			} // We only intend to make a best effort.
		}
		
		return OLED_HOME = ans;
	}
	
	public static final String canon(String filename) {
		if (filename == null || filename.length() == 0)
			return "";
		
		File file = new File(filename);
		try {
			return file.getCanonicalPath();
		} catch (IOException ex) {
			return file.getAbsolutePath();
		}
	}

	public static boolean onWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.US).startsWith("windows");
	};

	public static boolean onMac() {
		return System.getProperty("mrj.version") != null || System.getProperty("os.name").toLowerCase(Locale.US).startsWith("mac ");
	}

	//Exemplo de uso
	// Util.copy(false, true, alloyHome(),
    //"models/book/appendixA/addressBook1.als", "models/book/appendixA/addressBook2.als",
	/**
	 * Copy the list of files from JAR into the destination directory, then set
	 * the correct permissions on them if possible.
	 * 
	 * @param executable - if true, we will attempt to set the file's "executable" permission (failure to do this is ignored)
	 * @param keepPath   - if true, the full path will be created for the destination file
	 * @param destdir    - the destination directory
	 * @param names      - the files to copy from the JAR
	 */
	public static void copy(boolean executable, boolean keepPath, String destdir, String... names) {
		String[] args = new String[names.length + 2];
		args[0] = "/bin/chmod"; // This does not work on Windows, but the  "executable" bit is not needed on Windows anyway.
		args[1] = (executable ? "700" : "600"); // 700 means read+write+executable; 600 means read+write.
		int j = 2;
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			String destname = name;
			if (!keepPath) {
				int ii = destname.lastIndexOf('/');
				if (ii >= 0)
					destname = destname.substring(ii + 1);
			}
			destname = (destdir + '/' + destname).replace('/',
					File.separatorChar);
			int last = destname.lastIndexOf(File.separatorChar);
			new File(destname.substring(0, last + 1)).mkdirs(); // Error will be caught later by the file copy
			if (copy(name, destname)) {
				args[j] = destname;
				j++;
			}
		}
		if (onWindows() || j <= 2)
			return;
		String[] realargs = new String[j];
		for (int i = 0; i < j; i++)
			realargs[i] = args[i];
		try {
			Runtime.getRuntime().exec(realargs).waitFor();
		} catch (Throwable ex) {
			// We only intend to make a best effort
		}
	}

	/**
	 * Copy the given file from JAR into the destination file; if the
	 * destination file exists, we then do nothing. Returns true iff a file was
	 * created and written.
	 */
	private static boolean copy(String sourcename, String destname) {
		File destfileobj = new File(destname);
		if (destfileobj.isFile() && destfileobj.length() > 0)
			return false;
		boolean result = true;
		InputStream in = null;
		FileOutputStream out = null;
		try {
			in = ConfigurationHelper.class.getClassLoader().getResourceAsStream(sourcename);
			if (in == null)
				return false; // This means the file is not relevant for this setup, so we don't pop up a fatal dialog
			out = new FileOutputStream(destname);
			byte[] b = new byte[16384];
			while (true) {
				int numRead = in.read(b);
				if (numRead < 0)
					break;
				if (numRead > 0)
					out.write(b, 0, numRead);
			}
		} catch (IOException e) {
			result = false;
		}
		if (!close(out))
			result = false;
		if (!close(in))
			result = false;
		if (!result)
			System.out.println("Error occurred in creating the file \"" + destname + "\"");
		return true;
	}

	/**
	 * Attempt to close the file/stream/reader/writer and return true if and
	 * only if we successfully closed it. (If object==null, we return true right
	 * away)
	 */
	public static boolean close(Closeable object) {
		if (object == null)
			return true;
		boolean ans = true;
		try {
			if (object instanceof PrintStream
					&& ((PrintStream) object).checkError())
				ans = false;
			if (object instanceof PrintWriter
					&& ((PrintWriter) object).checkError())
				ans = false;
			object.close();
			return ans;
		} catch (Throwable ex) {
			return false;
		}
	}
}
