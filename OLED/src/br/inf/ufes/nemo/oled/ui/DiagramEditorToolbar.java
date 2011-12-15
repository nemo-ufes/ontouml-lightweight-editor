
package br.inf.ufes.nemo.oled.ui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import br.inf.ufes.nemo.oled.util.AppCommandListener;
import br.inf.ufes.nemo.oled.util.ApplicationResources;
import br.inf.ufes.nemo.oled.util.IconLoader;

public class DiagramEditorToolbar implements ActionListener {
  private JToolBar toolbar = new JToolBar();
  private List<AppCommandListener> listeners = new ArrayList<AppCommandListener>();
  private Map<String, AbstractButton> buttonMap = new HashMap<String, AbstractButton>();
  private ButtonGroup validationGroup;
  //private ButtonGroup diagramOptionsGroup;

  public DiagramEditorToolbar() {
    validationGroup = new ButtonGroup();
    //diagramOptionsGroup = new ButtonGroup();
    
    toolbar.add(createButton(validationGroup, "showoutput"));
    toolbar.add(createButton(validationGroup, "validate"));
    toolbar.add(createButton(validationGroup, "verify")); 
    toolbar.add(createButton(validationGroup, "verifyfile")); 
    toolbar.add(createButton(validationGroup, "viewoutput")); 
    //toolbar.addSeparator(new Dimension(10, 25));
    //toolbar.add(createToggleButton(diagramOptionsGroup, "rectilinear"));
    //toolbar.add(createToggleButton(diagramOptionsGroup, "straight"));
  }

  public void addCommandListener(AppCommandListener l) {
    listeners.add(l);
  }

  public void removeCommandListener(AppCommandListener l) {
    listeners.remove(l);
  }

  public JToolBar getToolbar() { return toolbar; }

  public void actionPerformed(ActionEvent e) {
    for (AppCommandListener l : listeners) {
      l.handleCommand(e.getActionCommand());
    }
  }

  public void setEnabled(String actionCommand, boolean flag) {
    buttonMap.get(actionCommand).setEnabled(flag);
  }

  public void setSelected(String actionCommand, boolean flag) {
	    buttonMap.get(actionCommand).setSelected(flag);
  }
  
  public void doClick(String actionCommand) {
    buttonMap.get(actionCommand).requestFocusInWindow();
    buttonMap.get(actionCommand).doClick();
  }

  @SuppressWarnings("unused")
private JToggleButton createToggleButton(ButtonGroup aButtonGroup,
    String name) {
    String prefix = "editortoolbar." + name;
    JToggleButton button = new JToggleButton(
      IconLoader.getInstance().getIcon(getResourceString(prefix + ".icon")));
    button.setMargin(new Insets(3, 3, 3, 3));
    String actionCommand = getResourceString(prefix + ".command");
    button.setActionCommand(actionCommand);
    button.addActionListener(this);
    toolbar.add(button);
    button.setToolTipText(getResourceString(prefix + ".tooltip"));
    button.setFocusable(false);
    buttonMap.put(actionCommand, button);
    if (aButtonGroup != null) {
      aButtonGroup.add(button);
    }
    return button;
  }
  
  private JButton createButton(ButtonGroup aButtonGroup,
    String name) {
    String prefix = "editortoolbar." + name;
    JButton button = new JButton(
      IconLoader.getInstance().getIcon(getResourceString(prefix + ".icon")));
    button.setMargin(new Insets(3, 3, 3, 3));
    String actionCommand = getResourceString(prefix + ".command");
    button.setActionCommand(actionCommand);
    button.addActionListener(this);
    toolbar.add(button);
    button.setToolTipText(getResourceString(prefix + ".tooltip"));
    button.setFocusable(false);
    buttonMap.put(actionCommand, button);
    if (aButtonGroup != null) {
      aButtonGroup.add(button);
    }
    return button;
  }
  
  private String getResourceString(String property) {
    return ApplicationResources.getInstance().getString(property);
  }
}
