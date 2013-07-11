package br.ufes.inf.nemo.oled.ui.editor.ocl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;

import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionCellRenderer;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.VariableCompletion;

public class OCLCellRenderer extends CompletionCellRenderer {

	private static final long serialVersionUID = 1L;
	
	private Icon operationIcon;	

	/**
	 * Constructor.
	 */
	public OCLCellRenderer() {
		operationIcon = getIcon("/resources/icon/ocl/methpub_obj.gif");
		//functionIcon = getIcon("img/function.png");
		//emptyIcon = new EmptyIcon(16);
	}


	/**
	 * Returns an icon.
	 *
	 * @param resource The icon to retrieve.  This should either be a file,
	 *        or a resource loadable by the current ClassLoader.
	 * @return The icon.
	 */
	private Icon getIcon(String resource) {
		ClassLoader cl = getClass().getClassLoader();
		URL url = cl.getResource(resource);
		if (url==null) {
			File file = new File(resource);
			try {
				url = file.toURI().toURL();
			} catch (MalformedURLException mue) {
				mue.printStackTrace(); // Never happens
			}
		}
		return url!=null ? new ImageIcon(url) : null;
	}


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	protected void prepareForOtherCompletion(JList list,
			Completion c, int index, boolean selected, boolean hasFocus) {
		super.prepareForOtherCompletion(list, c, index, selected, hasFocus);
		setIcon(operationIcon);
	}


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	protected void prepareForVariableCompletion(JList list,
			VariableCompletion vc, int index, boolean selected,
			boolean hasFocus) {
		super.prepareForVariableCompletion(list, vc, index, selected,
										hasFocus);
		//setIcon(variableIcon);
		setIcon(operationIcon);
	}


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	protected void prepareForFunctionCompletion(JList list,
			FunctionCompletion fc, int index, boolean selected,
			boolean hasFocus) {
		super.prepareForFunctionCompletion(list, fc, index, selected,
										hasFocus);
		//setIcon(functionIcon);
		setIcon(operationIcon);
	}


}