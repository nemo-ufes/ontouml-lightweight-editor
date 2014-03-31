package br.ufes.inf.nemo.oled;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

public class AppSecondToolbar implements ActionListener {
	
	JToolBar toolbar = new JToolBar();
	private List<AppCommandListener> listeners = new ArrayList<AppCommandListener>();
	private Map<String, JButton> buttonMap = new HashMap<String, JButton>();
	final JTextField findField;
	@SuppressWarnings("unused")
	private AppFrame frame;
	
	public String getText()
	{
		return findField.getText();
	}
	
	public AppSecondToolbar(final AppFrame frame)
	{
		this.frame = frame;
		
		toolbar.addSeparator();
		
		findField = new JTextField();
		findField.setPreferredSize(new Dimension(250,22));
		//toolbar.add(Box.createHorizontalStrut(50));
		//toolbar.add(Box.createHorizontalStrut(7));
		toolbar.add(findField);		
		toolbar.setMargin(new Insets(5,5,5,5));
		toolbar.setFloatable(false);
		//toolbar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);  
		//toolbar.add(Box.createHorizontalStrut(7));		
		
		findField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	frame.getDiagramManager().find();
            }
		});
		
		findField.getDocument().addDocumentListener(new DocumentListener() {			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				frame.getDiagramManager().find();
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				frame.getDiagramManager().find();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				
			}
		});
	}
	
	/**
	 * Returns the toolbar.
	 * @return the toolbar
	 */
	public JToolBar getToolbar() { return toolbar; }

	/**
	 * Adds a CommandListener.
	 * @param l the CommandListener to add
	 */
	public void addCommandListener(AppCommandListener l) {
		listeners.add(l);
	}
	
	/**
	 * Creates the button with the specified name.
	 * @param name the resource name
	 * @return the button
	 */
	@SuppressWarnings("unused")
	private JButton createButton(String name) {
		String prefix = "maintoolbar." + name;
		JButton button = new JButton(
				IconLoader.getInstance().getIcon(getResourceString(prefix + ".icon")));
		button.setMargin(new Insets(1, 1, 1, 1));
		String command = getResourceString(prefix + ".command");
		button.setActionCommand(command);
		button.addActionListener(this);
		button.setBorderPainted(false);
	    button.setFocusable(false);
		buttonMap.put(command, button);
		toolbar.add(button);
		button.setToolTipText(getResourceString(prefix + ".tooltip"));
		return button;
	}

	/**
	 * Returns the specified resource as a String object.
	 * @param property the property name
	 * @return the property value or null if not found
	 */
	private String getResourceString(String property) {
		return ApplicationResources.getInstance().getString(property);
	}

	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		for (AppCommandListener l : listeners) {
			l.handleCommand(e.getActionCommand());
		}
	}

	/**
	 * Enables or disables the specified button associated with a command.
	 * @param command the action command string
	 * @param flag true if enabled, false to disable
	 */
	public void enableButton(String command, boolean flag) {
		buttonMap.get(command).setEnabled(flag);
	}
}
