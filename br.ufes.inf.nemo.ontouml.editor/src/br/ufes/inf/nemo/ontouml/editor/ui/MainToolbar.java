package br.ufes.inf.nemo.ontouml.editor.ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JToolBar;

import br.ufes.inf.nemo.ontouml.editor.util.Resources;

@SuppressWarnings("serial")
public class MainToolbar extends JToolBar implements ActionListener {

	private List<CommandListener> listeners = new ArrayList<CommandListener>();
	private Map<String, JButton> buttonMap = new HashMap<String, JButton>();
	
	public MainToolbar() {
		
		Dimension separatorSize = new Dimension(24,20);
		
		createButton("newproject");
		createButton("newartifact");
		createButton("openproject");
		createButton("saveproject");
		
		addSeparator(separatorSize);
		
		createButton("undo");
		createButton("redo");
		createButton("cut");
		createButton("copy");
		createButton("paste");

		addSeparator(separatorSize);
		
		/*enableButton("UNDO", false);
		enableButton("REDO", false);
		enableButton("CUT", false);
		enableButton("COPY", false);
		enableButton("PASTE", false);
		enableButton("DELETE", false);*/

		setFloatable(false);
	}
	
	public void addCommandListener(CommandListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Creates the button with the specified name.
	 * @param name the resource name
	 * @return the button
	 */
	private JButton createButton(String name) {
		String prefix = "maintoolbar." + name;
		JButton button = new JButton(Resources.getIcon(prefix + ".icon"));
		button.setMargin(new Insets(1, 1, 1, 1));
		String command = Resources.getString(prefix + ".command");
		button.setActionCommand(command);
		button.addActionListener(this);
		button.setFocusable(false);
		buttonMap.put(command, button);
		add(button);
		button.setToolTipText(Resources.getString(prefix + ".tooltip"));
		return button;
	}
	
	public void actionPerformed(ActionEvent evt) {
		for (CommandListener listener : listeners) {
			listener.handleCommand(evt.getActionCommand());
		}
	}
	
	public void enableButton(String command, boolean flag) {
		buttonMap.get(command).setEnabled(flag);
	}
}
