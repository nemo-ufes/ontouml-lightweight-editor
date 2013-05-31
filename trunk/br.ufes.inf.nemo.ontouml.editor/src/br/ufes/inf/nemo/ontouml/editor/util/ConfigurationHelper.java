package br.ufes.inf.nemo.ontouml.editor.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Pattern;

public final class ConfigurationHelper {

	private static String EDITOR_HOME = null;
	private static Properties properties;

	public static String getEditorHome() {

		if (EDITOR_HOME != null)
			return EDITOR_HOME;

		String temp = System.getProperty("java.io.tmpdir");

		if (temp == null || temp.length() == 0)
			System.out.println("Error. JVM need to specify a temporary directory using java.io.tmpdir property.");

		//String username = System.getProperty("user.name");
		File tempfile = new File(temp + File.separatorChar + "ontouml_editor_tmp");

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

		return EDITOR_HOME = ans;
	}

	public static boolean saveProperties()
	{
		if(properties != null)
		{
			File file = new File(getCanonPath(getEditorHome(), "settings.xml"));
			try {
				FileOutputStream out = new FileOutputStream(file);
				properties.storeToXML(out, "OLED Configuration File", "UTF-8");
				close(out);
				return true;
			} catch (Exception ex) {
			}
		}

		return false;
	}

	public static Properties getProperties()
	{
		if(properties != null)
			return properties;

		properties = new Properties();

		File file = new File(getCanonPath(getEditorHome(), "settings.xml"));
		if(file.exists())
		{
			try {
				FileInputStream in = new FileInputStream(file);
				properties.loadFromXML(in);
				close(in);
			} catch (Exception ex) {
			}
		}

		return properties;
	}

	public static String getCanonPath(String dir, String fileName)
	{
		return canon(dir + File.separatorChar + fileName);
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

	public static File getFileWithExtension(File file, String extension) {
		String path = file.getPath();
		File result = file;
		Pattern pattern = Pattern.compile(".*\\" + extension);
		if (!pattern.matcher(path).matches()) {
			path = path + extension;
			result = new File(path);
		}
		return result;
	}

	public static String getSuffix()
	{
		return "onto";
	}
	
	public static String getExtension()
	{
		return "." + getSuffix();
	}

	public static boolean onWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.US).startsWith("windows");
	};

	public static boolean onMac() {
		return System.getProperty("mrj.version") != null || System.getProperty("os.name").toLowerCase(Locale.US).startsWith("mac ");
	}

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
